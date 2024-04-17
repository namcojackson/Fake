<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160511150031 --%>
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
			<input type="hidden" name="pageID" value="NWAL1740Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Profitability Maintenance Setup">
			
<center>
	<table width="100%">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
<%--				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Order Entry" class="pTab_ON"><a href="#">Order Entry</a></li>
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
				<%@ page import="business.servlet.NWAL1740.NWAL1740BMsg" %>
				<% NWAL1740BMsg scrnMsg = (NWAL1740BMsg)databean.getEZDBMsg(); %>
					
<%-- ##### BODY(HEADER) ##### --%>
							<div class="pTab_BODY">
								<table border="0" cellspacing="0">
									<tr>
										
										<td>
											<table border="0" cellspacing="0">
												<col width="" valign="top">
												<col width="5">
												<col width="" valign="top">
												<tr>
													<td>
														<table border="0" cellspacing="0">
															<col width="250" align="left" valign="top">
															<col width="160" align="left" valign="top">
															<col width="160" align="left" valign="top">
															<tr>
																<td class="stab">
																	&nbsp;
																</td>
																<td class="stab">
																	&nbsp;
																</td>
																<td class="stab">
																	&nbsp;
																</td>
															</tr>
															<tr>
																<td class="stab">Profitability Process Rule Name</td>
																<td class="stab">
																	<ezf:select name="ordProcNodePrflCd" ezfName="ordProcNodePrflCd" ezfBlank="1" ezfCodeName="ordProcNodePrflCd_P" ezfDispName="ordProcNodePrflNm_P" otherAttr=" style=\"width:140px\""/>
																</td>
																<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
															</tr>
															<tr>
																<td class="stab">
																	&nbsp;
																</td>
																<td class="stab">
																	&nbsp;
																</td>
																<td class="stab">
																	&nbsp;
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
<%-- ##### BODY(TAB) ##### --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="Mdse" title="Mdse" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Mdse" otherAttr=" tabindex=\"-1\"">Mdse Tp Rules</ezf:anchor></li>
										<li id="Line" title="Line" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Line" otherAttr=" tabindex=\"-1\"">Line Catg Rules</ezf:anchor></li>
										<li id="WH" title="WH" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_WH" otherAttr=" tabindex=\"-1\"">WH Rules</ezf:anchor></li>
									</ul>
									
								</div>

<%-- ##### TAB(Mdse) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Mdse'}">
										<script type="text/javascript">
											document.getElementById( "Mdse" ).className="pTab_ON";
											document.getElementById( "Line" ).className="pTab_OFF";
											document.getElementById( "WH" ).className="pTab_OFF";
										</script>
										<div class="pTab_BODY_In">
												<table>
												<col align="center" width="55">
												<col align="center" width="55">
												<col align="right" width="950">
												<td><ezf:inputButton name="Add_Mdse" value="+" htmlClass="pBtn4" /></td>
												<td><ezf:inputButton name="Remove_Mdse" value="-" htmlClass="pBtn4" /></td>
												<td align="right"></td>
												</table>
												<%--------------------------------------------%>
												<%---------------- List1 START ---------------%>
												<%--------------------------------------------%>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col align="left" valign="top">
													<tr>
														<td colspan="2">
															<div id="RightTop" style="overflow-x:hidden; width:1090;"
																onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col align="center" width="30">
																	<col align="center" width="150">
																	<col align="center" width="250">
																	<col align="center" width="120">
																	<col align="center" width="130">
																	<col align="center" width="60">
																	<col align="center" width="80">
																	<col align="center" width="120">
																	<col align="center" width="130">
																	<col align="center" width="130">
																	<col align="center" width="130">
																	<tr height="30">
																		<td class="pClothBs">&nbsp;</td>
																		<td class="pClothBs">Merchandise Type</td>
																		<td class="pClothBs">Merch Type Name</td>
																		<td class="pClothBs">Floor Price - Include</td>
																		<td class="pClothBs">Rep Equip Revenue -<br> Include</td>
																		<td class="pClothBs">Discount</td>
																		<td class="pClothBs">Floor Adjustment</td>
																		<td class="pClothBs">Rep Revenue<br> Adjustment</td>
																		<td class="pClothBs">Adjustment <br> Allocation Type</td>
																		<td class="pClothBs">Include in Dealer<br> Credit Profit SUM</td>
																		<td class="pClothBs">Reduction in Compensation Amt</td>
																	</tr>
																</table>
															</div>
															<div id="Right" style="overflow-x:scroll; width:1108; height:420px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																<table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="30">
																	<col width="150">
																	<col width="250">
																	<col width="120">
																	<col width="130">
																	<col width="60">
																	<col width="80">
																	<col width="120">
																	<col width="130">
																	<col width="130">
																	<col width="130">
																	<ezf:row ezfHyo="A">
																	<tr>
																		<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:inputText name="coaMdseTpCd_A" ezfName="coaMdseTpCd_A" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"2\""/><ezf:inputButton name="OpenWin_Mdse" ezfName="OpenWin_Mdse" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"xxBtnFlg_A\""/>
																		</td>
																		<td>
																			<ezf:inputText name="coaProjNm_A" ezfName="coaProjNm_A" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\" maxlength=\"33\""/>
																		</td>
																		<td>
																			<ezf:select name="flPrcCalcInclFlg_A" ezfName="flPrcCalcInclFlg_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:110px\""/>
																		</td>
																		<td>
																			<ezf:select name="repRevCalcInclFlg_AR" ezfName="repRevCalcInclFlg_AR" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:120px\""/>
																		</td>
																		<td>
																			<ezf:select name="repRevCalcInclFlg_AD" ezfName="repRevCalcInclFlg_AD" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:55px\""/>
																		</td>
																		<td>
																			<ezf:select name="repRevCalcInclFlg_AF" ezfName="repRevCalcInclFlg_AF" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:75px\""/>
																		</td>
																		<td>
																			<ezf:select name="redRepRevFlg_A" ezfName="redRepRevFlg_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:110px\""/>
																		</td>
																		<td>
																			<ezf:select name="discAllocMethDescTxt_A" ezfName="discAllocMethDescTxt_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="discAllocMethCd_P" ezfDispName="discAllocMethDescTxt_P" otherAttr=" style=\"width:120px\""/>
																		</td>
																		<td>
																			<ezf:select name="dlrCrPrftInclFlg_A" ezfName="dlrCrPrftInclFlg_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:120px\""/>
																		</td>
																		<td>
																			<ezf:select name="redCompAmtFlg_A" ezfName="redCompAmtFlg_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="xxYesNoCd_P" ezfDispName="xxYesNoNm_P" otherAttr=" style=\"width:120px\""/>
																		</td>
																	</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
										</div>
									</c:when>
								</c:choose>
<%-- ##### TAB(Line) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='Line'}">
										<script type="text/javascript">
											document.getElementById( "Mdse" ).className="pTab_OFF";
											document.getElementById( "Line" ).className="pTab_ON";
											document.getElementById( "WH" ).className="pTab_OFF";
										</script>
										<div class="pTab_BODY_In">
											<!--<div style="OVERFLOW-Y: scroll; HEIGHT: 480px" >-->
												<table style="table-layout:fixed;" cellspacing="0">
													<col align="left" valign="top">
													
													<tr>
														<td >
																<table border="0" cellpadding="0">
																<col align="center" width="55">
																<col align="center" width="55">
																<col align="right" width="950">
																<td><ezf:inputButton name="Add_Line" value="+" htmlClass="pBtn4" /></td>
																<td><ezf:inputButton name="Remove_Line" value="-" htmlClass="pBtn4" /></td>
																<td class="stab" align="left">&nbsp;&nbsp;The following Line Categories are NOT included in FLOOR and REVENUE nor CSMP profitability calculations.</td>
																</table>
																<%--------------------------------------------%>
																<%---------------- List2 START ----------------%>
																<%--------------------------------------------%>
																<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
																	<col align="left" valign="top">
																	<tr>
																		<td>
																			<div id="RightTop" style="overflow-x:hidden; width:825;"
																				onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
																				<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																					<col align="center" width="30">
																					<col align="center" width="250">
																					<col align="center" width="250">
																					<col align="center" width="50">
																					<col align="center" width="120">
																					<col align="center" width="120">
																					
																					<tr height="30">
																						<td class="pClothBs">&nbsp;</td>
																						<td class="pClothBs">Line Category</td>
																						<td class="pClothBs">Description</td>
																						<td class="pClothBs">Active</td>
																						<td class="pClothBs">Effective Date</td>
																						<td class="pClothBs">End Date</td>
																						
																					</tr>
																				</table>
																			</div>
																			<div id="Right" style="overflow-x:hidden; width:841; height:420px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																				<table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																					<col align="center" width="30">
																					<col width="250">
																					<col width="250">
																					<col align="center" width="50">
																					<col width="120">
																					<col width="120">
																					
																					<ezf:row ezfHyo="B">
																					<tr>
																						<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																						<td>
																							<ezf:inputText name="dsOrdLineCatgNm_B" ezfName="dsOrdLineCatgNm_B" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"30\""/>
																							<ezf:inputButton name="OpenWin_Line" ezfName="OpenWin_Line" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"xxBtnFlg_B\""/>
																						</td>
																						<td>
																							<ezf:inputText name="dsOrdLineCatgDescTxt_B" ezfName="dsOrdLineCatgDescTxt_B" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"33\" maxlength=\"30\""/>
																						</td>
																						<td>
																							<ezf:inputCheckBox name="actvFlg_B" ezfName="actvFlg_B" value="Y" ezfHyo="B" ezfArrayNo="0" />
																						</td>
																						<td>
																							<ezf:inputText name="effFromDt_B" ezfName="effFromDt_B" value="12/31/9999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													    									<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B', 4, {EZF_ROW_NUMBER});" >
																						</td>
																						<td>
																							<ezf:inputText name="effThruDt_B" ezfName="effThruDt_B" value="12/31/9999" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													    									<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B', 4, {EZF_ROW_NUMBER});" >
																						</td>
																						
																					</tr>
																					</ezf:row>
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
														</td>
													</tr>
												</table>
											<!--</div>-->
										</div>
									</c:when>
								</c:choose>
<%-- ##### TAB(WH) ##### --%>
								<c:choose>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='WH'}">
										<script type="text/javascript">
											document.getElementById( "Mdse" ).className="pTab_OFF";
											document.getElementById( "Line" ).className="pTab_OFF";
											document.getElementById( "WH" ).className="pTab_ON";
										</script>
										<div class="pTab_BODY_In">

												<table>
												<col align="center" width="55">
												<col align="center" width="55">
												<col align="right" width="950">
												<td><ezf:inputButton name="Add_Wh" value="+" htmlClass="pBtn4" /></td>
												<td><ezf:inputButton name="Remove_Wh" value="-" htmlClass="pBtn4" /></td>
												<td class="stab" align="left">&nbsp;&nbsp;The following Warehouse & Sub Warehouse Code Combinations are NOT included in FLOOR and REVENUE profitability calculations.</td>
												</table>
												<%--------------------------------------------%>
												<%---------------- List3 START ----------------%>
												<%--------------------------------------------%>
												<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
													<col align="left" valign="top">
													<tr>
														<td>
															<div id="RightTop" style="overflow-x:hidden; width:939;"
																onScroll="synchroScrollLeft( this.id, new Array( 'Right' ));">
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col align="center" width="30">
																	<col align="center" width="250">
																	<col align="center" width="250">
																	<col align="center" width="120">
																	<col align="center" width="50">
																	<col align="center" width="120">
																	<col align="center" width="120">
																	
																	
																	<tr height="30">
																		<td class="pClothBs">&nbsp;</td>
																		<td class="pClothBs">Warehouse</td>
																		<td class="pClothBs">Sub Warehouse</td>
																		<td class="pClothBs">Valuation%</td>
																		<td class="pClothBs">Active</td>
																		<td class="pClothBs">Effective Date</td>
																		<td class="pClothBs">End Date</td>
																		
																		
																	</tr>
																</table>
															</div>
															<div id="Right" style="overflow-x:hidden; width:955; height:420px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
																<table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="30">
																	<col width="250">
																	<col width="250">
																	<col align="center"  width="120">
																	<col align="center" width="50">
																	<col width="120">
																	<col width="120">
																	

																	<ezf:row ezfHyo="C">
																	<tr>
																		<td><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"delFlg_C0#{EZF_ROW_NUMBER}\""/></td>
																		<td>
																			<ezf:inputText name="rtlWhNm_C" ezfName="rtlWhNm_C" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"30\""/><ezf:inputButton name="OpenWin_WH" ezfName="OpenWin_WH" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"xxBtnFlg_C\""/>
																		</td>
																		<td><ezf:inputText name="rtlSwhNm_C" ezfName="rtlSwhNm_C" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"30\""/><ezf:inputButton name="OpenWin_SWH" ezfName="OpenWin_SWH" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" /></td>
																		<td><ezf:label name="mdseInvtyCostPct_C" ezfName="mdseInvtyCostPct_C" ezfHyo="C" ezfArrayNo="0" />%</td>
																		<td>
																			<ezf:inputCheckBox name="actvFlg_C" ezfName="actvFlg_C" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"delFlg_C0#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="effFromDt_C" ezfName="effFromDt_C" value="12/31/9999" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
									    									<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C', 4, {EZF_ROW_NUMBER});" >
																		</td>
																		<td>
																			<ezf:inputText name="effThruDt_C" ezfName="effThruDt_C" value="12/31/9999" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
									    									<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C', 4, {EZF_ROW_NUMBER});" >
																		</td>
																		
																	</tr>
																	</ezf:row>
																</table>
															</div>
														</td>
													</tr>
												</table>
										</div>
									</c:when>
								</c:choose>
							</div>
						</td>
					</tr>
				</table>
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

	function onBlurZeroEvent(item){
		if(item.value.length >= 1 && item.value.length < 6){
			item.value = ("00000"+item.value).slice(-6);
		}
	}
</script>

<%-- **** Task specific area ends here **** --%>
