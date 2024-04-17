<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230220120147 --%>
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
			<input type="hidden" name="pageID" value="NWAL1520Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Hold Apply & Release">


<center>
	<table width="100%">
		<tr>
			<td>
			
<%-- ######################################## HEADER ######################################## --%>
<%--    <div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Hold Apply & Release" class="pTab_ON"><a href="#">Order Entry</a></li>
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
--%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
		  </td>
		</tr>
	</table>
	<div class="pTab_BODY">
		<table style="table-layout:fixed;">
			<tr>
				<col valign="top">
				<col valign="top">
				<td>
	<%-- ##### BODY(HEADER) ##### --%>
					<fieldset>
						<legend style="font-size:12px;">View Hold</legend>
						<table>
							<tr valign="top">
								<td>
									<table style="table-layout:fixed;">
										<col width="100">
										<col width="154">
										<tr>
											<td class="stab">Order Number</td>
											<td><ezf:inputText name="cpoOrdNum_V" ezfName="cpoOrdNum_V" value="100123" otherAttr=" size=\"10\" maxlength=\"8\""/></td>
										</tr>
										<tr>
											<td class="stab">Category Type</td>
											<td>
												<ezf:select name="configCatgCd_V" ezfName="configCatgCd_V" ezfBlank="1" ezfCodeName="configCatgCd_L1" ezfDispName="configCatgDescTxt_L1" otherAttr=" style=\"width:150\""/>
											</td>
										</tr>
										<tr>
											<td class="stab">Line Num</td>
											<td><ezf:inputText name="condSqlTxt_V" ezfName="condSqlTxt_V" value="1.1" otherAttr=" size=\"5\" maxlength=\"3000\""/></td>
										</tr>
										<tr>
											<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_ViewHold', 'ViewHold')" tabindex="-1">Hold Name</a></td>
											<td><ezf:inputText name="hldRsnDescTxt_V" ezfName="hldRsnDescTxt_V" value="BILL HOLD" /></td>
										</tr>
										<tr>
											<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_ApplyReason', 'HoldReason_View')" tabindex="-1">Hold Reason Code</a></td>
											<td><ezf:inputText name="hldApplyRsnDescTxt_V" ezfName="hldApplyRsnDescTxt_V" value="D&A" /></td>
										</tr>
										<tr>
											<td class="stab">Hold Until Date</td>
											<td><ezf:inputText name="hldUntilDt_V" ezfName="hldUntilDt_V" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"88\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('hldUntilDt_V', 4);"></td>
										</tr>
										<tr>
											<td><ezf:inputButton name="Search_Hold" value="Search" htmlClass="pBtn6" /></td>
										</tr>
									</table>
								</td>
								<td>
									<table style="table-layout:fixed;">
										<col width="260">
											<td class="stab">
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col align="left" valign="top">
													<tr>
														<td>
															<fieldset>
																<table border="0" cellpadding="0" cellspacing="0">
																	<col align="center" width="80">
																	<col align="center" width="100">
																	<col align="center" width="100">
																	<tr> 
																		<td class="stab"><ezf:inputRadio name="xxRadioBtn_V" ezfName="xxRadioBtn_V" value="0" />Released</td>
																		<td class="stab"><ezf:inputRadio name="xxRadioBtn_V" ezfName="xxRadioBtn_V" value="1" />Not Released</td>
																		<td class="stab"><ezf:inputRadio name="xxRadioBtn_V" ezfName="xxRadioBtn_V" value="2" />Display All</td>
																	</tr>
																</table>
															</fieldset>
														</td>
													</tr>
													<tr>
														<td>
															<div style="overflow-x:hidden; width:210px;">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col align="center" width="024">
																	<col align="center" width="080">
																	<col align="center" width="104">
																	<tr height="24">
																		<td class="pClothBs">Cnt</td>
																		<td class="pClothBs">Hold Level</td>
																		<td class="pClothBs">Hold Name</td>
																	</tr>
																</table>
															</div>
															<div style="overflow-x:hidden; width:227px; height:097px; overflow-y:scroll;">
																<table id="V_Right" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="024" align="right">
																	<col width="080">
																	<col width="104">
																	<ezf:row ezfHyo="V">
																		<tr id="id_row2${EZF_ROW_NUMBER}">
																			<td><ezf:label name="xxRefTblNum_VH" ezfName="xxRefTblNum_VH" ezfHyo="V" ezfArrayNo="0" /></td>
																			<td><ezf:label name="hldLvlDescTxt_VH" ezfName="hldLvlDescTxt_VH" ezfHyo="V" ezfArrayNo="0" /></td>
																			<!-- <td><label ezfout name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V">BILL HOLD</label></td> -->
																			<td><ezf:inputText name="hldRsnDescTxt_VH" ezfName="hldRsnDescTxt_VH" value="123456789A123456789B123456789C" ezfHyo="V" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"50\" style=\"border:none; background-color:transparent\" tabindex=\"-1\""/></td>
																		</tr>
																	</ezf:row>
																	<ezf:skip>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																		<tr>
																			<td><label ezfout>2</label></td>
																			<td><label ezfout>HEADER</label></td>
																			<td><input type="text" class="pPro" size="14" maxlength="50" value="123456789A123456789B123456789C" style="border:none; background-color:transparent" name="hldRsnDescTxt_VH" ezfname="hldRsnDescTxt_VH" ezfhyo="V" tabindex="-1"></td>
																		</tr>
																	</ezf:skip>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
				<td>
					<fieldset>
						<legend style="font-size:12px;">Apply Hold</legend>
						<table height="170">
							<col width="100">
							<col width="170">
							<col width="350" valign="top">
							<tr>
								<td class="stab">Order Number</td>
								<td><ezf:inputText name="cpoOrdNum_A" ezfName="cpoOrdNum_A" value="100123" otherAttr=" size=\"10\" maxlength=\"8\""/></td>
								<td class="stab" rowspan="5">Hold Comments<br>
									<ezf:textArea name="hldDtlTxt_A" ezfName="hldDtlTxt_A" otherAttr=" rows=\"5\" cols=\"35\" tabindex=\"21\" ezftoupper=\"\""/>
								</td>
							</tr>
							<tr>
								<td class="stab">Category Type</td>
								<td>
									<ezf:select name="configCatgCd_A" ezfName="configCatgCd_A" ezfBlank="1" ezfCodeName="configCatgCd_L2" ezfDispName="configCatgDescTxt_L2" otherAttr=" style=\"width:148\""/>
								</td>
							</tr>
							<tr>
								<td class="stab">Line Num</td>
								<td><ezf:inputText name="condSqlTxt_A" ezfName="condSqlTxt_A" value="1.1" otherAttr=" size=\"5\" maxlength=\"3000\""/></td>
							</tr>
							<tr>
								<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_ApplyHold', 'ApplyHold')" tabindex="-1">Hold Name</a></td>
								<td><ezf:inputText name="hldRsnDescTxt_A" ezfName="hldRsnDescTxt_A" value="BILL HOLD" /></td>
							</tr>
							<tr>
								<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_ApplyReason', 'HoldReason_Apply')" tabindex="-1">Hold Reason Code</a></td>
								<td><ezf:inputText name="hldApplyRsnDescTxt_A" ezfName="hldApplyRsnDescTxt_A" value="D&A" /></td>
							</tr>
							<tr>
								<td class="stab">Hold Until Date</td>
								<td><ezf:inputText name="hldUntilDt_A" ezfName="hldUntilDt_A" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"88\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('hldUntilDt_A', 4);"></td>
							</tr>
							<tr>
								<td><ezf:inputButton name="Apply_Hold" value="Apply Hold" htmlClass="pBtn8" /></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<fieldset>
						<legend style="font-size:12px;">Release Hold</legend>
								<%--------------------------------------------%>
								<%---------------- List START ----------------%>
								<%--------------------------------------------%>
						<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
							<col align="left" valign="top">
							<tr>
								<td>
									<div id="RightTop" style="overflow-x:hidden; width:1090;"
										onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col align="center" width="24">
											<col align="center" width="50">
											<col align="center" width="70">
											<col align="center" width="80">
											<col align="center" width="60">
											<col align="center" width="80">
											<col align="center" width="200">
											<col align="center" width="130">
											<col align="center" width="170">
											<col align="center" width="100">
											<col align="center" width="130">
											<col align="center" width="100">
											<col align="center" width="170">
											<col align="center" width="100">
											<col align="center" width="150">
											<col align="center" width="150">
											<tr height="24">
												<td class="pClothBs">&nbsp</td>
												<td class="pClothBs">Row#</td>
												<td class="pClothBs">Released</td>
												<td class="pClothBs">Hold Level</td>
												<td class="pClothBs">Line Num</td>
												<td class="pClothBs">Hold Source</td>
												<td class="pClothBs">Hold Name</td>
												<td class="pClothBs">Hold Applied Date</td>
												<td class="pClothBs">Hold Applied By</td>
												<td class="pClothBs">Hold Until Date</td>
												<td class="pClothBs">Hold Reason Code</td>
												<td class="pClothBs">Hold Comment</td>
												<td class="pClothBs">Released By</td>
												<td class="pClothBs">Released Date</td>
												<td class="pClothBs">Released Reason Code</td>
												<td class="pClothBs">Released Comment</td>
											</tr>
										</table>
									</div>
									<div id="Right" style="overflow-x:scroll; width:1106; height:240px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
										<table id="A_Right" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
											<col width="24">
											<col width="50">
											<col width="70">
											<col width="80">
											<col width="60">
											<col width="80">
											<col width="200">
											<col width="130">
											<col width="170">
											<col width="100">
											<col width="130">
											<col width="100">
											<col width="170">
											<col width="100">
											<col width="150">
											<col width="150">
											<ezf:row ezfHyo="L">
											<tr id="id_row${EZF_ROW_NUMBER}">
												<td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="L" ezfArrayNo="0" otherAttr=" id=\"xxChkBox{EZF_ROW_NUMBER}\""/></td>
												<td><ezf:label name="xxNewRowNum" ezfName="xxNewRowNum" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:label name="relFlg" ezfName="relFlg" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:label name="hldLvlDescTxt" ezfName="hldLvlDescTxt" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxLineNum" ezfName="xxLineNum" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="hldRsnDescTxt" ezfName="hldRsnDescTxt" value="123456789A123456789B123456789C" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"50\" style=\"border:none; background-color:transparent\" tabindex=\"-1\""/></td>
												<td><ezf:label name="hldDt_AP" ezfName="hldDt_AP" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="xxPsnNm_AP" ezfName="xxPsnNm_AP" value="123456789A123456789B123456789C" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"62\" style=\"border:none; background-color:transparent\" tabindex=\"-1\""/></td>
												<td><ezf:label name="hldUntilDt_AP" ezfName="hldUntilDt_AP" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:label name="hldApplyRsnDescTxt_AP" ezfName="hldApplyRsnDescTxt_AP" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="hldDtlTxt_AP" ezfName="hldDtlTxt_AP" value="123456789A123456789B123456789C" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"4000\" style=\"border:none; background-color:transparent\" tabindex=\"-1\""/></td>
												<td><ezf:inputText name="xxPsnNm_RE" ezfName="xxPsnNm_RE" value="123456789A123456789B123456789C" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"62\" style=\"border:none; background-color:transparent\" tabindex=\"-1\""/></td>
												<td><ezf:label name="relDt_RE" ezfName="relDt_RE" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:label name="hldRelRsnDescTxt_RE" ezfName="hldRelRsnDescTxt_RE" ezfHyo="L" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="relMemoTxt_RE" ezfName="relMemoTxt_RE" value="123456789A123456789B123456789C" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"400\" style=\"border:none; background-color:transparent\" tabindex=\"-1\""/></td>
											</tr>
											</ezf:row>
											<ezf:skip>
											<tr>
												<td><input type="checkbox" value="Y"></td>
												<td><label ezfout>2</label></td>
												<td><label ezfout>N</label></td>
												<td><label ezfout>LINE</label></td>
												<td><label ezfout>1.1</label></td>
												<td><label ezfout>2</label></td>
												<td><label ezfout>WMS RELEASE</label></td>
												<td><label ezfout>03/15/15 03:30 PM</label></td>
												<td><label ezfout>C11590 [BLOOM,JOSHUA]</label></td>
												<td><label ezfout>03/15/15</label></td>
												<td><label ezfout>D&A</label></td>
												<td><label ezfout>PENDING</label></td>
												<td><label ezfout></label></td>
												<td><label ezfout></label></td>
												<td><label ezfout></label></td>
												<td><label ezfout></label></td>
											</tr>
											</ezf:skip>
										</table>
									</div>
								</td>
							</tr>
						</table>
						<table style="table-layout:fixed;">
							<col width="100" valign="top">
							<col width="100" valign="top">
							<col width="10" valign="top">
							<col width="110" valign="top">
							<col width="200" valign="top">
							<col width="10" valign="top">
							<col width="100" valign="top">
							<col width="320" valign="top">
							<col width="30" valign="top">
							<col width="100" valign="top">
							<tr>
								<td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn8" /></td>
								<td><ezf:inputButton name="Un_Select_All" value="Un Select All" htmlClass="pBtn8" /></td>
								<td>&nbsp</td>
								<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_ReleaseReason', 'ReleaseReason')" tabindex="-1">Release Reason Code</a></td>
								<!-- Mod Start 2017/08/21 H.Sugawara QC#19952 -->
								<!-- <td class="pOut"><label ezfout name="hldRelRsnDescTxt_RH" ezfname="hldRelRsnDescTxt_RH">Pased Pricing Check</label></td> -->
								<td><ezf:textArea name="hldRelRsnDescTxt_RH" ezfName="hldRelRsnDescTxt_RH" otherAttr=" rows=\"4\" cols=\"25\" style=\"overflow:auto;\""/></td>
								<!-- Mod End 2017/08/21 H.Sugawara QC#19952 -->
								<td>&nbsp</td>
								<td class="stab">Release Comments</td>
								<td><ezf:textArea name="relMemoTxt_RH" ezfName="relMemoTxt_RH" otherAttr=" rows=\"4\" cols=\"40\" tabindex=\"21\" ezftoupper=\"\""/></td>
								<td>&nbsp</td>
								<td><ezf:inputButton name="Release_Hold" value="Release Hold" htmlClass="pBtn8" /></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function synchroScroll_fromRightTblAction() {
		var rightTopTBL = this.document.getElementById( 'rightTopTBL' );
		var rightTBL    = this.document.getElementById( 'rightTBL'     );
		var leftTBL     = this.document.getElementById( 'leftTBL' );
		
		// synchronize scroll - Y
		leftTBL.scrollTop = rightTBL.scrollTop;
		// synchronize scroll - X
		rightTopTBL.scrollLeft = rightTBL.scrollLeft;
	}
	
	function synchroScroll_fromLeftTblAction() {
		var leftTBL  = this.document.getElementById( 'leftTBL'  );
		var rightTBL = this.document.getElementById( 'rightTBL' );
		
		// synchronize scroll - Y
		rightTBL.scrollTop = leftTBL.scrollTop;
	}

	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
		item.select();
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
