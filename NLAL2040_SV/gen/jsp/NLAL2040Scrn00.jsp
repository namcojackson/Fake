<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180425153904 --%>
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

			<!-- Add row F11  -->
			<div style="display:none;">
				<ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
			</div>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NLAL2040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="MODELS-CLICKS Inventory Valuation Entry">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
		
<%-- ######################################## HEADER ######################################## --%>
			<%-- ###TAB - HEAD --%>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<ezf:skip>
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url(./img/tab/uppertabbackground.jpg);">
					<tr>
						<td height="28px" width="1070px" valign="bottom">
							<table class="pTab_UPPER_ON" border="0" cellpadding="0" cellspacing="0" >
								<tr title="WO Search">
									<td align="center" class="same">MDL Click Entry</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</ezf:skip>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<!-- ################################################## HEADER - [START] ################################################## -->
					
					<table border="0" cellpadding="0" cellspacing="0" style="margin-top: 10px; margin-left: 10px;">
						<col width="135">
						<col width="250">
						<col width="15">
						<col width="120">
						<col width="98">
						<col width="250">
						<col width="150">
						<!-- 1 -->
						<tr height="20px">
							<td><ezf:anchor name="kitItmCd" ezfName="kitItmCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdse" >Model (*)</ezf:anchor></td>
							<td style="text-align: left;">
								<ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="IR6000" otherAttr=" size=\"31\" maxlength=\"50\" ezftoupper=\"\""/>
							</td>
							<td></td>
							<td class="stab">Active Records Only</td>
							<td colspan="2"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" otherAttr=" size=\"40\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<!-- 2 -->
						<tr height="20px">
							<td class="stab">Speed Segment</td>
							<td style="text-align: left;">
								<ezf:select name="svcSegCd_PS" ezfName="svcSegCd_PS" ezfBlank="1" ezfCodeName="svcSegCd_PC" ezfDispName="svcSegDescTxt_PD" otherAttr=" style=\"width: 223px\""/>
							</td>
							<td></td>
							<td>Date Active From </td>
							<td colspan="3">
								<ezf:inputText name="effFromDt" ezfName="effFromDt" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"12\""/>
								<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);"/>
							</td>
						</tr>
						<!-- 3 -->
						<tr height="20px">
							<td class="stab">Merchandise Type</td>
							<td style="text-align: left;">
								<ezf:select name="coaMdseTpCd_PS" ezfName="coaMdseTpCd_PS" ezfBlank="1" ezfCodeName="coaMdseTpCd_PC" ezfDispName="coaMdseTpDescTxt_PD" otherAttr=" style=\"width: 223px\""/>
							</td>
							<td></td>
							<td>Date Active To </td>
							<td colspan="3">
								<ezf:inputText name="effThruDt" ezfName="effThruDt" value="12/31/9999" otherAttr=" maxlength=\"10\" size=\"12\""/>
								<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);"/>
							</td>
						</tr>
						<!-- 4 -->
						<tr height="20px">
							<td class="stab">Product Code </td>
							<td style="text-align: left;">
								<ezf:select name="coaProdCd_PS" ezfName="coaProdCd_PS" ezfBlank="1" ezfCodeName="coaProdCd_PC" ezfDispName="coaProdDescTxt_PD" otherAttr=" style=\"width: 223px\""/>
							</td>
							<td style="text-align: right" colspan="5">
								<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
							</td>
						</tr>
						</tr>
					</table>
					<hr>
					
	<%-- ######################################## DETAIL ######################################## --%>
					<table border="0" cellpadding="1" cellspacing="0" width="100%" style="margin-left:5px;">
						<tr>
							<td>

<%-- Pagenation area start ============================================================== --%>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="305px"  align="left">
									<col width="500px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
									<col width="295px"  align="right">
									<tr>
										<%-- =============== Filters Parts =============== --%>
										<td>
											<table>
												<tr height="20px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
												</tr>
											</table>
										</td>
										<%-- The horizontal space between Fileters Parts and Paging Parts --%>
										<td>
											<ezf:inputButton name="AddLine" value="New Line" htmlClass="pBtn6" />
											<ezf:inputButton name="DeleteLine" value="Delete Line" htmlClass="pBtn6" />
										</td>
										<%-- =============== Paging Parts =============== --%>
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
									</tr>
								</table>
<%-- Pagenation area end ================================================================ --%>
					<div id="parentBoxA">

								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="left" valign="top">
											<%-- ### A:Left Table HEAD--%>
											<div style="float:left; display:block"><!-- left table -->
												<div id='leftTblHead' style="display:block;">
												</div>
											<%-- ### A:Left Table BODY--%>
												<div id='leftTbl' style="float:left; display:block; height:380px; overflow:hidden; margin-left: 0px; padding:0px;">
												</div>
											</div><!-- left table -->
										</td>

										<td valign="top">
											<%-- ### A:Right Table HEAD--%>
											<div style="float:left"><!-- right table -->
												<div id="rightTblHead" style="width:1109px; display:block; overflow:hidden; margin:0px;padding:0px;">

												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1299px" style="margin-right:20px">
													<col width="40"  align="Center">  <!-- Check Box -->
													<col width="110"  align="Center"> <!-- Date Active From -->
													<col width="110" align="Center">  <!-- Date Active To -->
													<col width="220" align="Center">  <!-- Model -->
													<col width="150"  align="Center"> <!-- Speed Segment -->
													<col width="50" align="Center">   <!-- MT -->
													<col width="50" align="Center">   <!-- PC -->
													<col width="120"  align="Center"> <!-- METER FROM -->
													<col width="120"  align="Center"> <!-- METER TO -->
													<col width="120"  align="Center"> <!-- AGE FROM (MONTHS) -->
													<col width="120" align="Center">  <!-- AGE TO (MONTHS) -->
													<col width="105"  align="Center"> <!-- Owner -->
													<col width="105"  align="Center"> <!-- Sub Warehouse -->
													<col width="105" align="Center">  <!-- RMA Disposition -->

													<tr height="28">
														<td id="AH0" class="pClothBs colFix"></td>
														<td id="AH1" class="pClothBs">Date Active From</td>
														<td id="AH2" class="pClothBs">Date Active To</td>
														<td id="AH3" class="pClothBs">Model</td>
														<td id="AH4" class="pClothBs">Speed Segment</td>
														<td id="AH5" class="pClothBs">MT</td>
														<td id="AH6" class="pClothBs">PC</td>
														<td id="AH7" class="pClothBs">METER FROM</td>
														<td id="AH8" class="pClothBs">METER TO</td>
														<td id="AH9" class="pClothBs">AGE FROM (MONTHS)</td>
														<td id="AH10" class="pClothBs">AGE TO (MONTHS)</td>
														<td id="AH11" class="pClothBs">Owner</td>
														<td id="AH12" class="pClothBs">Sub Warehouse</td>
														<td id="AH13" class="pClothBs">RMA Disposition</td>
													</tr>
												</table>
											</div>

											<%-- ### Right Table BODY--%>
											<div id="rightTbl" style="width:1126; height:380; display:block; overflow:scroll; margin:0px; padding:0px;" >
												<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" style="width:700px;">
													<col width="40"  align="center"> <!-- Check Box -->
													<col width="110"  align="left">  <!-- Date Active From -->
													<col width="110" align="left">   <!-- Date Active To -->
													<col width="220" align="left">   <!-- Model -->
													<col width="150"  align="left">  <!-- Speed Segment -->
													<col width="50" align="center">  <!-- MT -->
													<col width="50" align="center">  <!-- PC -->
													<col width="120"  align="left">  <!-- METER FROM -->
													<col width="120"  align="left">  <!-- METER TO -->
													<col width="120"  align="left">  <!-- AGE FROM (MONTHS) -->
													<col width="120" align="left">   <!-- AGE TO (MONTHS) -->
													<col width="105" align="left">   <!-- Owner -->
													<col width="105" align="left">   <!-- Sub Warehouse -->
													<col width="105" align="left">   <!-- RMA Disposition -->
													
													<tbody>

														<ezf:row ezfHyo="A">
														<tr height="25" id="id_row_right{EZF_ROW_NUMBER}">
															<!-- Check Box -->
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<!-- Date Active From -->
															<td class="stab">
																<ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="01/01/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"11\""/>
																<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');"  ezfhyo="A" /></td>
															</td>
															<!-- Date Active To -->
															<td class="stab">
																<ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"10\" size=\"11\""/>
																<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');"  ezfhyo="A" /></td>
															</td>
															<!-- Model -->
															<td class="stab">
																<ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/>
																<ezf:inputButton name="OpenWin_MdseDetail" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
															</td>
															<!-- Speed Segment -->
															<td class="stab">
																<ezf:inputText name="svcSegDescTxt_A1" ezfName="svcSegDescTxt_A1" value="21 - 30" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/>
															</td>
															<!-- MT -->
															<td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<!-- PC -->
															<td><ezf:label name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<!-- METER FROM -->
															<td class="stab">
																<ezf:inputText name="fromMtrCnt_A1" ezfName="fromMtrCnt_A1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"13\" style=\"text-align:right;\""/>
															</td>
															<!-- METER TO -->
															<td class="stab">
																<ezf:inputText name="toMtrCnt_A1" ezfName="toMtrCnt_A1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"13\" style=\"text-align:right;\""/>
															</td>
															<!-- AGE FROM (MONTHS) -->
															<td class="stab">
																<ezf:inputText name="fromElpsMthAot_A1" ezfName="fromElpsMthAot_A1" value="999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"3\" style=\"text-align:right;\""/>
															</td>
															<!-- AGE TO (MONTHS) -->
															<td class="stab">
																<ezf:inputText name="toElpsMthAot_A1" ezfName="toElpsMthAot_A1" value="999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"3\" style=\"text-align:right;\""/>
															</td>
															<!-- Owner -->
															<td class="stab">
																<ezf:select name="invtyOwnrCd_AS" ezfName="invtyOwnrCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="invtyOwnrCd_PC" ezfDispName="invtyOwnrDescTxt_PD" otherAttr=" style=\"width: 98px\""/>
															</td>
															<!-- Sub Warehouse -->
															<td class="stab">
																<ezf:select name="rtlSwhCd_AS" ezfName="rtlSwhCd_AS" ezfHyo="A" ezfArrayNo="0" ezfCodeName="rtlSwhCd_PC" ezfDispName="rtlSwhCd_PD" otherAttr=" style=\"width: 98px\""/>
															</td>
															<!-- RMA Disposition -->
															<td class="stab">
																<ezf:select name="thirdPtyDspTpCd_AS" ezfName="thirdPtyDspTpCd_AS" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="thirdPtyDspTpCd_PC" ezfDispName="thirdPtyDspTpDescTxt_PD" otherAttr=" style=\"width: 98px\""/>
															</td>
														</tr>
														</ezf:row>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				</div><!-- parentBoxA -->
				<script type="text/javascript" defer>
					S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true, true);
				</script>
<!-- ######################################## FOOTER ######################################## -->
				<div align="left">
					<table border="0">
						<tr height="5">
							<td></td>
						</tr>
						<tr height="20">
							<td valign="bottom">
								<table border="0" cellpadding="1" cellspacing="0" width="100%">
									<col width="40">
									<col width="40">
									<col width="250">
									<col width="90">
									<col width="200">
									<col width="10">
									<col width="40">
									<tr>
										<td>
											<ezf:inputButton name="CheckAll" value="Check All" htmlClass="pBtn7" />
										</td>
										<td>
											<ezf:inputButton name="UncheckAll" value="UnCheck All" htmlClass="pBtn7" />
										</td>
										<td></td>
										<td>
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="TempleteFileForUpload" >Import File</ezf:anchor>
										</td>
										<td>
											<ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td><ezf:inputButton name="Import" value="Import" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	setKeyDownHandler();

	function setKeyDownHandler() {

		if( window.addEventListener ) {
		    window.addEventListener("keyup", emulateFuncKeyDown, false);
		} else if( document.attachEvent ) {
		    document.attachEvent("onkeyup", emulateFuncKeyDown);
		} else {
		    document.onkeyup = emulateFuncKeyDown;
		}
	}

	function emulateFuncKeyDown() {

		var code = event.keyCode;
		//alert("keyCode:["+event.keyCode+"]");

		if(event.keyCode == 122 ) {
			event.keyCode = null;
			event.returnValue = false;
		}

		switch(code) {
		// F11
		case 122:
			//sendServer("Line_Add");
			emulateOnClickEvent("btn11");
			break;
		default:
			break;
		}
		return;
	}

	function emulateOnClickEvent(elementId) {

		var elem = document.getElementById(elementId);
		if( /*@cc_on ! @*/ false ) {
			elem.fireEvent("onclick");
		} else {
			var evt = document.createEvent("MouseEvents");
			evt.initEvent("click", false, true);
			elem.dispatchEvent(evt);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
