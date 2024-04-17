<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170516174802 --%>
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
			<input type="hidden" name="pageID" value="NMAL7250Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Adjustment Search">
<center>
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
										<li title="Price List Search" class="pTab_ON"><a href="#">Prc List Srch</a></li>
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
<%------------------------------------%>
<%-- Save Option					--%>
<%------------------------------------%>
					<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
						<col width="152">
						<col width="345">
						<col width="110">
						<col width="300">
						<col width="">
						<tr>
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk" ezfName="srchOptPk" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_SavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td class="stab"><ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
							<td>
								<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
					<table border="0" cellpadding="0" cellspacing="0" height="120">
						<tr>
							<td valign="top">
								<table cellpadding="0" border="0">
									<col align="left" width="140">
									<col align="left" width="120">
									<col align="center" width="60">
									<col align="left" width="150">
									<col align="left" width="5">
									<col align="left" width="110">
									<col align="left" width="120">
									<col align="left" width="5">
									<col align="left" width="120">
									<col align="left" width="60">
									<col align="left" width="60">
									<tr>
										<td class="stab">Rule or Table Name(*)</td>
										<td colspan="3"><ezf:inputText name="prcRuleNm" ezfName="prcRuleNm" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="XX_LINK_ANCR_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_AccountNum" >Account Number</ezf:anchor></td>
										<td><ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Effective Date From</td>
										<td colspan="2">
											<ezf:inputText name="effFromDt" ezfName="effFromDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab">Precedence#</td>
										<td colspan="3"><ezf:inputText name="defRulePrcdNum" ezfName="defRulePrcdNum" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="XX_LINK_ANCR_AN" ezfEmulateBtn="1" ezfGuard="OpenWin_AccountName" >Account Name(*)</ezf:anchor></td>
										<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" otherAttr=" size=\"20\" maxlength=\"360\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Effective Date To</td>
										<td colspan="2">
											<ezf:inputText name="effThruDt" ezfName="effThruDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
										</td>
									</tr>
									<tr>
										<td class="stab">Price Adjustment Category</td>
										<td colspan="3"><ezf:select name="prcRuleCatgCd" ezfName="prcRuleCatgCd" ezfBlank="1" ezfCodeName="prcRuleCatgCd_P" ezfDispName="prcRuleCatgDescTxt_P" otherAttr=" style=\"width:200;\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CN" ezfEmulateBtn="1" ezfGuard="OpenWin_CSMPNum" >CSMP#</ezf:anchor></td>
										<td><ezf:inputText name="csmpContrNum" ezfName="csmpContrNum" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Status</td>
										<td colspan="2"><ezf:select name="prcDispRecTpCd" ezfName="prcDispRecTpCd" ezfCodeName="prcDispRecTpCd_P" ezfDispName="prcDispRecTpDescTxt_P" otherAttr=" style=\"width:200;\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Transaction Group</td>
										<td colspan="3"><ezf:select name="prcRuleTrxCatgCd" ezfName="prcRuleTrxCatgCd" ezfBlank="1" ezfCodeName="prcRuleTrxCatgCd_P" ezfDispName="prcRuleTrxCatgDescTxt_P" />
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CG" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerGroup" >Customer Group</ezf:anchor></td>
										<td><ezf:inputText name="prcGrpPk_CG" ezfName="prcGrpPk_CG" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Price Adjustment Type</td>
										<td colspan="2"><ezf:select name="prcRuleCondTpCd" ezfName="prcRuleCondTpCd" ezfCodeName="prcRuleCondTpCd_P" ezfDispName="prcRuleCondTpDescTxt_P" />
										</td>
									</tr>
									<tr>
										<td class="stab">Line of Business</td>
										<td colspan="3"><ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_P" ezfDispName="lineBizTpDescTxt_P" />
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_BR" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" >Branch#</ezf:anchor></td>
										<td><ezf:inputText name="coaBrCd" ezfName="coaBrCd" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Rule Attribute</td>
										<td><ezf:select name="prcRuleAttrbCd" ezfName="prcRuleAttrbCd" ezfBlank="1" ezfCodeName="prcRuleAttrbCd_P" ezfDispName="prcRuleAttrbDescTxt_P" />
										</td>
										<td class="stab">Value</td>
										<td><ezf:inputText name="prcRuleCondFromTxt" ezfName="prcRuleCondFromTxt" otherAttr=" size=\"15\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_MA" ezfEmulateBtn="1" ezfGuard="OpenWin_MaterialGroup" >Material Group</ezf:anchor></td>
										<td colspan="2"><ezf:inputText name="prcGrpPk_MA" ezfName="prcGrpPk_MA" otherAttr=" size=\"20\" maxlength=\"28\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="3">&nbsp;</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_OC" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" >Order Categories</ezf:anchor></td>
										<td><ezf:inputText name="dsOrdCatgCd" ezfName="dsOrdCatgCd" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_OT" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderReason" >Order Reason</ezf:anchor></td>
										<td><ezf:inputText name="dsOrdTpCd" ezfName="dsOrdTpCd" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
										<td align="right" valign="middle" height="24">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
<%------------------------------------%>
<%-- Button							--%>
<%------------------------------------%>
					<table border="0">
						<col width="800">
						<col width="300">
						<tr>
							<td align="left" valign="middle" height="24">
								<ezf:inputButton name="MoveWin_PriceRulePrecedence" value="Maintain Price Adjustment Precedence" htmlClass="pBtn18" />&nbsp;&nbsp;
								<ezf:inputButton name="MoveWin_PricingRules" value="Create Rule" htmlClass="pBtn18" />&nbsp;&nbsp;
								<ezf:inputButton name="MoveWin_PriceAdjustment" value="Create Adjustment Table" htmlClass="pBtn18" />&nbsp;&nbsp;
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table border="0" cellpadding="1" cellspacing="0" width="1105" align="center"  rules="none"  style="padding: 10px; margin-bottom: 10px;">
					<tr align="right">
						<col width="150"  align="center">
						<col width="400"  align="center">
						<col width="550"  align="right">
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
						<td>&nbsp;</td>
						<td>
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"          value="A" />
								<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
								<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
								<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
								<jsp:param name="ViewMode"         value="FULL" />
							</jsp:include>
							<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
									<col>
									<col width="54"  align="center">
									<col width="40"  align="center">
									<col width="16"  align="center">
									<col width="40"  align="center">
									<col width="26"  align="center">
									<col width="10">
									<col>
									<col width="20">
									<col>	
									<col width="1">
									<col>
									<tr>
										<td>Results 990 - 1000 of 1000</td>
										<td class="stab">Showing</td>
										<td><input type="text" value="1" size="4" maxlength="4" style="text-align:right" class="pPro" readOnly name="xxPageShowCurNum" ezfname="xxPageShowCurNum"></td>
										<td class="stab">/</td>
										<td><input type="text" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly name="xxPageShowTotNum" ezfname="xxPageShowTotNum"></td>
										<td class="stab">page</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Jump" name="PageJump" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" disabled></td>
									</tr>
								</table>
							</ezf:skip>
						</td>
					</tr>
				</table>
				<table>
				<tr>
				<td width="5"></td>
				<td>
				<div id="parentBoxA">
				<div style="float:left; display:block"> <!-- left table -->
					<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;">
					</div>
					<div id="leftTbl" style="float:left; display:block; height:203px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
					</div>
				</div>  <!-- left table -->
				<div style="float:left"> <!-- right table -->
					<div id="rightTblHead" style="width:1085px; display:block; overflow:hidden; margin:0px;padding:0px;">
						<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1085px" style="margin-right:20px">
							<col align="center" width="25">		<!-- Select						-->
							<col align="center" width="55">		<!-- ID							-->
							<col align="center" width="210">	<!-- Rule or Table Name			-->
							<col align="center" width="120">	<!-- Price Adjustment Type		-->
							<col align="center" width="100">	<!-- Line of Business			-->
							<col align="center" width="100">	<!-- price Adjustment Category	-->
							<col align="center" width="80">		<!-- Apply Automatically		-->
							<col align="center" width="80">		<!-- Override					-->
							<col align="center" width="70">		<!-- Status						-->
							<col align="center" width="80">		<!-- Default rule Precedence	-->
							<col align="center" width="80">		<!-- Effective Date From		-->
							<col align="center" width="80">		<!-- Effective Date To			-->
							<tr height="35">
								<td id="AH0"  class="pClothBs">&nbsp;<br>&nbsp;</td>
								<td id="AH1"  class="pClothBs">ID</td>
								<td id="AH2"  class="pClothBs">Rule or Table Name</td>
								<td id="AH3"  class="pClothBs">Price Adjustment<br>Type</td>
								<td id="AH4"  class="pClothBs">Line of<br>Business</td>
								<td id="AH5"  class="pClothBs">Price Adjustment Category</td>
								<td id="AH6"  class="pClothBs">Apply Automatically</td>
								<td id="AH7"  class="pClothBs">Override</td>
								<td id="AH8"  class="pClothBs">Status</td>
								<td id="AH9"  class="pClothBs">Default Rule Precedence</td>
								<td id="AH10"  class="pClothBs">Effective Date From</td>
								<td id="AH11" class="pClothBs">Effective Date To</td>
							</tr>
						</table>
					</div>
					<div id="rightTbl" style="width:1102px; height:220px; display:block; overflow:scroll; margin:0px; padding:0px;" >
						<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="1085px">
							<col width="25" align="center">		<!-- Select						-->
							<col width="55">					<!-- ID							-->
							<col width="210">					<!-- Rule or Table Name			-->
							<col width="120">					<!-- Price Adjustment Type		-->
							<col width="100">					<!-- Line of Business			-->
							<col width="100">					<!-- price Adjustment Category	-->
							<col width="80" align="center">		<!-- Apply Automatically		-->
							<col width="80" align="center">		<!-- Override					-->
							<col width="70" align="center">		<!-- Status						-->
							<col width="80" align="center">		<!-- Default rule Precedence	-->
							<col width="80" align="center">		<!-- Effective Date From		-->
							<col width="80" align="center">		<!-- Effective Date To			-->
							<ezf:row ezfHyo="A">
								<tr  height="24" id="id_leftA_row{EZF_ROW_NUMBER}">
									<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:anchor name="" ezfName="xxLinkAncr_ID" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveWin_RecordScreen" ><ezf:label name="prcRuleHdrPk_A1" ezfName="prcRuleHdrPk_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
									<td><ezf:label name="prcRuleNm_A1" ezfName="prcRuleNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="prcRuleCondTpDescTxt_A1" ezfName="prcRuleCondTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mod Start 2016/12/13 T.Aoi S21_NA#16394 -->
									<!-- 
									<td><label ezfout name="dsBizLineDescTxt_A1" ezfname="dsBizLineDescTxt_A1" ezfhyo="A">LFS</label></td>
									-->
									<td><ezf:label name="lineBizTpDescTxt_A1" ezfName="lineBizTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<!-- Mod End   2016/12/13 T.Aoi S21_NA#16394 -->
									<td><ezf:label name="prcRuleCatgDescTxt_A1" ezfName="prcRuleCatgDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxFlgNm_AP" ezfName="xxFlgNm_AP" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="xxFlgNm_OV" ezfName="xxFlgNm_OV" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="prcDispRecTpDescTxt_A1" ezfName="prcDispRecTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="defRulePrcdNum_A1" ezfName="defRulePrcdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
									<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
								</tr>
							</ezf:row>
							<ezf:skip>
							</ezf:skip>
						</table>
					</div>
				</div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);
</script>

<%-- **** Task specific area ends here **** --%>
