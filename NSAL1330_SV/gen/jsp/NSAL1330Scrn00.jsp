<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181113194916 --%>
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
			<input type="hidden" name="pageID" value="NSAL1330Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Pricing Detail">

<center>

<!--      HEADER      
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Service Pricing Detail" class="pTab_ON"><a href="#">SvcPrcDtl</a></li>
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
-->
			<!-- Header Tabs -->
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
<!--
			<div class="pTab_HEAD">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
								</div>
							</td>
						</tr>
					</table>
			</div>
-->

<div class="pTab_BODY" style="width:100%;">

	<table width="100%" align="left" style="margin-top:-3px;">
		<tr><!-- Header Info -->
			<td>
				<table width="100%" id="shellHeader" style="table-layout:fixed" border="0">
					<col width="100">
					<col width="095">
					<col width="095">
					<col width="120">
					<col width="100">
					<col width="100">
					<col width="080">
					<col width="005">
					<col width="135">
					<col width="100">
					<col width="135">
					<tr>
									<td class="stab">
										<ezf:label name="xxPageNm" ezfName="xxPageNm" />
									</td>
									<td colspan="2">
										<ezf:inputText name="xxScrItem50Txt" ezfName="xxScrItem50Txt" value="1234567890" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/>
									</td>
					</tr>
					<tr>
						<td class="stab">
							Maintenance Shell#
						</td>
						<td>
							<ezf:inputText name="shellLineNum" ezfName="shellLineNum" value="1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
						</td>
						<td class="stab">
							Service Program
						</td>
						<td colspan="3">
							<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="123456790" otherAttr=" size=\"30\" maxlength=\"250\""/>
							<ezf:inputText name="svcPgmMdseCd" ezfName="svcPgmMdseCd" value="153ZZ884" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
						</td>
						<td colspan="2" class="stab">
							Contract Type
						</td>
						<td>
							<ezf:select name="prcSvcContrTpCd" ezfName="prcSvcContrTpCd" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_L" ezfDispName="prcSvcContrTpDescTxt_L" otherAttr=" style=\"width:120px\" id=\"prcSvcContrTpCd#{EZF_ROW_NUMBER}\""/>
						</td>
						<td class="stab">
							Plan Type
						</td>
						<td>
							<ezf:select name="prcSvcPlnTpCd" ezfName="prcSvcPlnTpCd" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_L" ezfDispName="prcSvcPlnTpDescTxt_L" otherAttr=" style=\"width:120px\" id=\"prcSvcPlnTpCd#{EZF_ROW_NUMBER}\""/>
						</td>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td class="stab">
							Term From/To/Mon
						</td>
						<td>
							<ezf:inputText name="fromPerMthNum" ezfName="fromPerMthNum" value="1" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/><ezf:inputText name="toPerMthNum" ezfName="toPerMthNum" value="12" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
							<ezf:inputText name="termMthAot" ezfName="termMthAot" value="12" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
						</td>
						<td class="stab">
							Contract Indicator
						</td>
						<td>
							<ezf:select name="dsContrCatgCd" ezfName="dsContrCatgCd" ezfBlank="1" ezfCodeName="dsContrCatgCd_L" ezfDispName="dsContrCatgDescTxt_L" otherAttr=" style=\"width:110px\" id=\"dsContrCatgCd#{EZF_ROW_NUMBER}\""/>
						</td>
	                    <td class="stab" style="text-align:right">Add To Contract</td>
	                    <td>
	                    	<ezf:inputText name="dsContrNum_AD" ezfName="dsContrNum_AD" value="123456789012345" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
	                    </td>

						<td colspan="2" class="stab">
							Base Bill Cycle
						</td>
						<td>
							<ezf:select name="baseBllgCycleCd" ezfName="baseBllgCycleCd" ezfBlank="1" ezfCodeName="bllgCycleCd_L" ezfDispName="bllgCycleDescTxt_L" otherAttr=" style=\"width:120px\" id=\"baseBllgCycleCd#{EZF_ROW_NUMBER}\""/>
						</td>
						<td class="stab">
							Usage Bill Cycle
						</td>
						<td>
							<ezf:select name="usgBllgCycleCd" ezfName="usgBllgCycleCd" ezfBlank="1" ezfCodeName="bllgCycleCd_L" ezfDispName="bllgCycleDescTxt_L" otherAttr=" style=\"width:120px\" id=\"usgBllgCycleCd#{EZF_ROW_NUMBER}\""/>
						</td>
					</tr>

					<tr>
                        <td class="stab">
                            Fixed Months
                        </td>
						<td>
							<ezf:inputText name="fixTermInMthAot" ezfName="fixTermInMthAot" value="12" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2" class="stab">
							Billed w/ Eq
						</td>
						<td>
							<ezf:select name="billWithEquipFlg" ezfName="billWithEquipFlg" ezfCodeName="xxFlgNm_L" ezfDispName="billWithEquipFlg_L" otherAttr=" style=\"width:50px\" id=\"billWithEquipFlg\""/>
						</td>
						<td class="stab">
							Billed by
						</td>
						<td>
							<ezf:select name="billByTpCd" ezfName="billByTpCd" ezfBlank="1" ezfCodeName="billByTpCd_L" ezfDispName="billByTpDescTxt_L" otherAttr=" style=\"width:120px\" id=\"billByTpCd\""/>
						</td>
					</tr>

					<tr>
						<td class="stab">
							Base Total
						</td>
						<td>
							<ezf:inputText name="xxTotAmt" ezfName="xxTotAmt" value="1,234.56" otherAttr=" size=\"12\" maxlength=\"15\" style=\"text-align:right;\""/>
						</td>
						<td class="stab">
							Customer
						</td>
						<td colspan="3">
							<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="TITLE RESOURCE GROUP" otherAttr=" size=\"30\" maxlength=\"360\""/>
							<ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="1539044" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
						</td>
						<td colspan="2" class="stab">
							Bill To Code
						</td>
						<td colspan="4">
							<ezf:inputText name="xxGenlFldAreaTxt_BI" ezfName="xxGenlFldAreaTxt_BI" value="2408 N HIGHWAY 67 US TITLE FLORISSANT,MO 63033-2036" otherAttr=" size=\"32\" maxlength=\"1000\""/>
							<ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="2485753" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/>
							<ezf:inputButton name="ApplyAllBillToFromHeader" value="Apply All" htmlClass="pBtn4" />
						</td>
					</tr>

					<tr>
						<td colspan="2">
						</td>
						<td class="stab">
							Document#
						</td>
						<td colspan="7">
							<ezf:inputText name="cpoSvcAgmtVerNum" ezfName="cpoSvcAgmtVerNum" value="12345678901234567890" otherAttr=" maxlength=\"50\" size=\"40\""/>
						</td>
					</tr>

				</table>
				<hr style="background-color:#A00000; height:5px;margin-bottom:-10px">
			</td>
		</tr><!-- Header Info -->

		<tr>
			<td><!-- Body Info -->
	            <div id="body" style="overflow-y:scroll; height:375px; overflow-x:hidden; width:1110; float:left; word-break: break-all;">

		            <table border="0" cellpadding="0" cellspacing="0" width="%" >
			            <tr>
			                <td valign="top">
			                    <div id="TopTBL" style="overflow-x:none; overflow-y:none; width:1089; float:left;">

									<span id="mdlSvcPrc">
			                        <table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
			                            <col width="1089" align="left">
			                            <tr>
			                                <td class="pClothBs" style="font-size:9pt;">Model Service Pricing</td>
			                            </tr>
			                        </table>

<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
<%@ page import="business.servlet.NSAL1330.NSAL1330BMsg" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG"%>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP"%>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc"%>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant"%>
<% NSAL1330BMsg bMsg = (NSAL1330BMsg)databean.getEZDBMsg(); %>

<% BigDecimal modelId   = BigDecimal.ZERO; %>
<% BigDecimal modelId_Z = BigDecimal.ZERO; %>

<% String actvFlg       = ""; %>
<% String xxFlgNm       = ""; %>
<% String hdlFuncNm     = ""; %>
<% String rdoClsVal     = ""; %>

<% for( int i = 0; i < bMsg.A.getValidCount(); i++ ) { %>
    <% modelId = bMsg.A.no( i ).mdlId_A.getValue(); %>
    <% if (bMsg.A.no(i).xxSelFlg_A.isInputProtected()) {
           hdlFuncNm = "fncRadio(this);";
           rdoClsVal = "pPro";
       } else  {
           hdlFuncNm = "toggleServicePriceSettingLevel(this);";
           rdoClsVal = "";
       } 
     %>

	    <%-- Header --%>
	    				<div style="margin-bottom:5px;">
		<%--	A <ezf:row ezfHyo="A">	--%>
                                    <table cellpadding="0" cellspacing="0" border="0" style="table-layout:fixed;">
                                        <col width="140">
                                        <col width="400">
                                        <tr>
                                            <td class="stab">
                                                Service Price Setting Level&nbsp;:
                                            </td>
                                            <td class="stab">
<% out.println("                                            <span id=\"" + i + "\">"); %>
<% out.println("                                                &nbsp;Model&nbsp;"); %>
<% out.println("                                                <" + "input type=\"radio\" name=\"rdoSelFlg_A_" + i + "\" value=\"N\" " + ("N".equals(bMsg.A.no(i).xxSelFlg_A.getValue()) ? "checked" : "") + " class=\"" + rdoClsVal + "\" onclick=\"" + hdlFuncNm + "\">"); %>
<% out.println("                                                &nbsp;Config&nbsp;"); %>
<% out.println("                                                <" + "input type=\"radio\" name=\"rdoSelFlg_A_" + i + "\" value=\"Y\" " + ("Y".equals(bMsg.A.no(i).xxSelFlg_A.getValue()) ? "checked" : "") + " class=\"" + rdoClsVal + "\" onclick=\"" + hdlFuncNm + "\">"); %>
<% out.println("                                            </span>"); %>
                                                <ezf:inputHidden name="xxSelFlg_A" ezfName="xxSelFlg_A" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                            </td>
                                        </tr>
                                    </table>
	    							<div style="BACKGROUND-COLOR: #ffffbb;">
	                                <table width="1089" cellpadding="0" cellspacing="0" border="0" style="table-leyout:fixed;">
	                                    <col width="040"><!-- Model -->
	                                    <col width="150">
	                                    <col width="020"><!-- Qty -->
	                                    <col width="040">
	                                    <col width="090"><!-- Service Price List  -->
	                                    <col width="240">
	                                    <col width="088"><!-- Service Package -->
	                                    <col width="210">
	                                    <col width="082"><!-- Tiered Pricing -->
	                                    <col width="">
	                                    <tr>
	                                        <td class="stab">Model</td>
	                                        <td><ezf:inputText name="t_MdlNm_A" ezfName="t_MdlNm_A" value="IRADV4251" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                        <td class="stab">Qty </td>
	                                        <td><ezf:inputText name="xxTotQtyCnt_A" ezfName="xxTotQtyCnt_A" value="3" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
	                                        <td class="stab">
						                    	<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Service_Price_List_Service_Pricing" >
	                                        		Service Price List
	                                        	</ezf:anchor>
	                                        </td>
	                                        <td>
	                                           <ezf:inputText name="prcCatgNm_A" ezfName="prcCatgNm_A" value="ESS PUBLIC SERVICE" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_ServicePriceList_SvcPrc" otherAttr=" size=\"30\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_SvcPrc\""/>
	                                        </td>
	                                        <td class="stab">
	                                        	Service Package
	                                        </td>
	                                        <td>
	                                            <span id="<%= i %>"> 
                                                    <ezf:select name="prcMtrPkgPk_A" ezfName="prcMtrPkgPk_A" ezfHyo="A" ezfCodeDispHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" otherEvent1=" onchange=\"sendServer('OnChange_SvcPkg', this.parentNode.id)\"" otherAttr=" style=\"width: 170px; \"" ezfCodeName="prcMtrPkgPk_KP" ezfDispName="prcMtrPkgNm_VW" />
	                                            </span>
	                                        </td>
	                                        <td class="stab">Tiered Pricing </td>
	                                        <td>
	                                            <span id="<%= i %>"> 
                                                    <ezf:select name="prcTierSvcOfferCd_A" ezfName="prcTierSvcOfferCd_A" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" otherEvent1=" onchange=\"sendServer('OnChange_Tierd', this.parentNode.id)\""  ezfCodeName="prcTierSvcOfferCd_KP" ezfDispName="prcTierSvcOfferDescTxt_VW" />
	                                            </span>
	                                        </td>
	                                        <td></td>
	                                    </tr>
	                                </table>
	                                <table width="1089" cellpadding="0" cellspacing="0" border="0" style="table-layout:fixed;">
	                                    <col width="100">
	                                    <col width="150">
	                                    <col width="090">
	                                    <col width="242">
	                                    <col width="086">
	                                    <col width="211">
	                                    <col width="041">
	                                    <col width="040">
	                                    <col width="">
	                                    <tr>
	                                        <td class="stab">Unit Periodic Base</td>
	                                        <td>
	                                        	<ezf:inputText name="xxTotPrcAmt_PB" ezfName="xxTotPrcAmt_PB" value="100.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"20\" ezftoupper=\"\""/>
	                                        </td>
	                                        <td class="stab">Extended Base </td>
	                                        <td>
	                                        	<ezf:inputText name="xxTotPrcAmt_EB" ezfName="xxTotPrcAmt_EB" value="100.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"20\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
	                                        </td>
	                                        <td class="stab">Total Base </td>
	                                        <td>
	                                        	<ezf:inputText name="xxTotPrcAmt_TB" ezfName="xxTotPrcAmt_TB" value="1,200.00" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"18\" maxlength=\"20\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
	                                        </td>
	                                        <td class="stab">Term </td>
	                                        <td>
	                                        	<ezf:inputText name="termMthAot_A" ezfName="termMthAot_A" value="12" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
	                                        </td>
	                                        <td>
	                                        	<ezf:inputButton name="OpenWin_Supply_Agreement_forModelPricing" value="Supply Agreement" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn9" />
	                                        </td>
	                                    </tr>
	                                </table>
	                                </div>

	                            <div style="overflow-x:none;width:0854; margin-left:30px;" id="<%= "usgPrc" + i %>" >
	                                <table width="0840" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;}">
	                                    <col width="032" align="center" valign="bottom">
	                                    <col width="074" align="center" valign="bottom"><!-- Band -->
	                                    <col width="160" align="center" valign="bottom"><!-- Billing Counter Name Hard Counter Name/Multiplier -->
	                                    <col width="094" align="center" valign="bottom"><!-- Per Unit Periodic Covered Images -->
	                                    <col width="069" align="center" valign="bottom"><!-- Excess Per Image Charge -->
	                                    <col width="040" align="center" valign="bottom"><!-- Tier -->
	                                    <col width="080" align="center" valign="bottom"><!-- Free Copy -->
	                                    <col width="080" align="center" valign="bottom"><!-- Min. Vol -->
	                                    <col width="080" align="center" valign="bottom"><!-- Min. Amt -->
	                                    <col width="080" align="center" valign="bottom"><!-- Roll Over % -->
	                                    <tr>
	                                        <td class="pClothBs">&nbsp;</td>
	                                        <td class="pClothBs">Band</td>
	                                        <td class="pClothBs">Billing Counter Name<br>Hard Counter Name/Multiplier</td>
	                                        <td class="pClothBs">Covered<br>Images</td>
	                                        <td class="pClothBs">Image<br>Charge</td>
	                                        <td class="pClothBs">Tier</td>
	                                        <td class="pClothBs">Free Copy</td>
	                                        <td class="pClothBs">Min. Vol</td>
	                                        <td class="pClothBs">Min. Amt</td>
	                                        <td class="pClothBs">Roll<br>Over %</td>
	                                    </tr>
	                                </table>

	                                <table width="0840" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal; margin-bottom:0px;}">
	                                    <col width="032" align="center">
	                                    <col width="074" align="center">
	                                    <col width="160" align="center">
	                                    <col width="094" align="center">
	                                    <col width="069" align="center">
	                                    <col width="040" align="center">
	                                    <col width="080" align="center">
	                                    <col width="080" align="center">
	                                    <col width="080" align="center">
	                                    <col width="080" align="center">

<%--	Z <ezf:row ezfHyo="Z">	--%>

    <% boolean isSmryLine = false; %>
    <% boolean isConfigPrcExpanded = false; %>
    <% for( int j = 0; j < bMsg.Z.getValidCount(); j++ ) { %>

        <% xxFlgNm       = bMsg.Z.no(j).xxFlgNm_Z.getValue(); %>

        <% modelId_Z = bMsg.Z.no( j ).mdlId_Z.getValue(); %>
        <% String xxSmryLineFlg = bMsg.Z.no(j).xxSmryLineFlg_Z.getValue(); %>

        <% if (ZYPCommonFunc.hasValue(bMsg.usgBllgCycleCd) &&
                (DS_CONTR_CATG.FLEET.equals(bMsg.dsContrCatgCd.getValue()) ||
                	(ZYPCommonFunc.hasValue(modelId) && ZYPCommonFunc.hasValue(modelId_Z) && modelId.compareTo(modelId_Z) == 0))) { %>

	                                    <% if ("P".equals(xxFlgNm)) { %>
	                                    <tr valign="top">
	                                    	<td>
                                                <% isSmryLine = "Y".equals(xxSmryLineFlg); %>
                                                <% if (isSmryLine) { %>
                                                    <img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Expand_Line','<%= j %>')" ezfHyo="Z" height="14">
                                                <% } else if (!isSmryLine) { %>
                                                    <img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Collapse_Line','<%= j %>')" ezfHyo="Z" height="14">
                                                <% } %>
	                                    	</td>
	                                        <td>
	                                            <ezf:inputText name="prcListBandDescTxt_Z" ezfName="prcListBandDescTxt_Z" value="A" ezfHyo="Z" ezfArrayNo="<%= j %>" otherEvent1="OnChange_Band" otherAttr=" size=\"3\" maxlength=\"50\" ezffocusout=\"OnChange_Band\""/>
	                                            <ezf:inputButton name="OpenWin_Band_Search" value="B" ezfHyo="Z" ezfArrayNo="<%= j %>" htmlClass="pBtn0" />
	                                        </td>
	                                        <td>
	                                            <ezf:inputText name="mtrLbDescTxt_ZB" ezfName="mtrLbDescTxt_ZB" value="BIL BW" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputHidden name="mtrLbDescTxt_Z" ezfName="mtrLbDescTxt_Z" value="REG BW SMALL" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputHidden name="contrMtrMultRate_Z" ezfName="contrMtrMultRate_Z" value="1.25" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"7\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
												<ezf:inputText name="mlyCopyInclPrcQty_Z" ezfName="mlyCopyInclPrcQty_Z" value="1,000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"11\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
												<ezf:inputText name="xsMtrAmtRate_Z" ezfName="xsMtrAmtRate_Z" value="0.05000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
						                    	<ezf:anchor name="xxScrEdtTpCd_ZL" ezfName="xxScrEdtTpCd_ZL" ezfHyo="Z" ezfArrayNo="<%= j %>" ezfEmulateBtn="1" ezfGuard="OpenWin_TierPricing" >
													<ezf:label name="xxScrEdtTpCd_Z" ezfName="xxScrEdtTpCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>" />
												</ezf:anchor>
	                                        </td>
	                                        <td>
	                                            <!-- Free Copy -->
	                                        	<ezf:inputText name="bllgFreeCopyCnt_Z" ezfName="bllgFreeCopyCnt_Z" value="12000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Vol -->
	                                        	<ezf:inputText name="bllgMinCnt_Z" ezfName="bllgMinCnt_Z" value="200000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Amt -->
	                                        	<ezf:inputText name="bllgMinAmtRate_Z" ezfName="bllgMinAmtRate_Z" value="150" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"19\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Roll Over % -->
	                                        	<ezf:inputText name="bllgRollOverRatio_Z" ezfName="bllgRollOverRatio_Z" value="0.05000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"9\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>

	                                    </tr>
	                                    <% } else { %>
                                                <% if (!isSmryLine) { %>
	                                    <tr>
	                                    	<td>
	                                        	&nbsp;
	                                    	</td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="prcListBandDescTxt_Z" ezfName="prcListBandDescTxt_Z" value="A" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"3\" maxlength=\"50\" ezftoupper=\"\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="mtrLbDescTxt_ZB" ezfName="mtrLbDescTxt_ZB" value="BIL BW" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputText name="mtrLbDescTxt_Z" ezfName="mtrLbDescTxt_Z" value="REG BW SMALL" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputText name="contrMtrMultRate_Z" ezfName="contrMtrMultRate_Z" value="1.25" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"7\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="mlyCopyInclPrcQty_Z" ezfName="mlyCopyInclPrcQty_Z" value="1,000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="xsMtrAmtRate_Z" ezfName="xsMtrAmtRate_Z" value="0.05000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
	                                        </td>
                                            <td>
	                                            <!-- Free Copy -->
	                                            &nbsp;
	                                            <ezf:inputHidden name="bllgFreeCopyCnt_Z" ezfName="bllgFreeCopyCnt_Z" value="12000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Vol -->
	                                            &nbsp;
	                                            <ezf:inputHidden name="bllgMinCnt_Z" ezfName="bllgMinCnt_Z" value="200000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Amt -->
	                                            &nbsp;
	                                            <ezf:inputHidden name="bllgMinAmtRate_Z" ezfName="bllgMinAmtRate_Z" value="150" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Roll Over % -->
	                                            &nbsp;
	                                            <ezf:inputHidden name="bllgRollOverRatio_Z" ezfName="bllgRollOverRatio_Z" value="0.05000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                    </tr>
                                                <% } else { %>
                                                <ezf:inputHidden name="prcListBandDescTxt_Z" ezfName="prcListBandDescTxt_Z" value="A" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"3\" maxlength=\"50\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="prcBookMdseCd_Z" ezfName="prcBookMdseCd_Z" value="153ZZ884" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="mtrLbDescTxt_ZB" ezfName="mtrLbDescTxt_ZB" value="BIL BW" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="mtrLbDescTxt_Z" ezfName="mtrLbDescTxt_Z" value="REG BW SMALL" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="contrMtrMultRate_Z" ezfName="contrMtrMultRate_Z" value="1.25" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
                                                <ezf:inputHidden name="mlyCopyInclPrcQty_Z" ezfName="mlyCopyInclPrcQty_Z" value="1,000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                                <ezf:inputHidden name="xsMtrAmtRate_Z" ezfName="xsMtrAmtRate_Z" value="0.05000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"18\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                                <ezf:inputHidden name="bllgFreeCopyCnt_Z" ezfName="bllgFreeCopyCnt_Z" value="12000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <ezf:inputHidden name="bllgMinCnt_Z" ezfName="bllgMinCnt_Z" value="200000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <ezf:inputHidden name="bllgMinAmtRate_Z" ezfName="bllgMinAmtRate_Z" value="150" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <ezf:inputHidden name="bllgRollOverRatio_Z" ezfName="bllgRollOverRatio_Z" value="0.05000" ezfHyo="Z" ezfArrayNo="<%= j %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <% } %>
	                                    <% } %>

                                        <ezf:inputHidden name="xxSmryLineFlg_Z" ezfName="xxSmryLineFlg_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="t_MdlNm_Z" ezfName="t_MdlNm_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="mdlId_Z" ezfName="mdlId_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="dsContrDtlPk_Z" ezfName="dsContrDtlPk_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="dsContrBllgMtrPk_Z" ezfName="dsContrBllgMtrPk_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="contrXsCopyPk_Z" ezfName="contrXsCopyPk_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="prcCatgCd_Z" ezfName="prcCatgCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="prcListTpCd_Z" ezfName="prcListTpCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="prcMtrPkgPk_Z" ezfName="prcMtrPkgPk_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="bllgMtrLbCd_Z" ezfName="bllgMtrLbCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="mdseDescShortTxt_Z" ezfName="mdseDescShortTxt_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="usgMdseCd_Z" ezfName="usgMdseCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="regMtrLbCd_Z" ezfName="regMtrLbCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="xxChkBox_Z" ezfName="xxChkBox_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="prcSvcTierTpCd_Z" ezfName="prcSvcTierTpCd_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="minCopyVolCnt_Z" ezfName="minCopyVolCnt_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="maxCopyVolCnt_Z" ezfName="maxCopyVolCnt_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="xxFlgNm_Z" ezfName="xxFlgNm_Z" ezfHyo="Z" ezfArrayNo="<%= j %>"/>

                                        <ezf:inputHidden name="contrMtrMultRate_BK" ezfName="contrMtrMultRate_BK" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="mlyCopyInclPrcQty_BK" ezfName="mlyCopyInclPrcQty_BK" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="minCopyVolCnt_BK" ezfName="minCopyVolCnt_BK" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="maxCopyVolCnt_BK" ezfName="maxCopyVolCnt_BK" ezfHyo="Z" ezfArrayNo="<%= j %>"/>
                                        <ezf:inputHidden name="xsMtrAmtRate_BK" ezfName="xsMtrAmtRate_BK" ezfHyo="Z" ezfArrayNo="<%= j %>"/>

	    <% } %>
    <% } %><!-- loop j -->
<%--	Z </ezf:row>	--%>

	                                </table>
	                            </div>
		<table style="margin-left:10px">
			<tr>
				<td>
		        <% String xxConfigPrcExpandFlg = bMsg.A.no(i).xxSmryLineFlg_A.getValue(); %>
		        <% isConfigPrcExpanded = "Y".equals(xxConfigPrcExpandFlg); %>
		        <% if ("N".equals(bMsg.A.no(i).xxSelFlg_A.getValue())) { %>
		            <img src="./img/wfcomp/S21WfPlus.gif" ezfHyo="A" height="14">
		        <% } else if (!isConfigPrcExpanded) { %>
		            <img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Expand_ConfigPricing','<%= i %>')" ezfHyo="A" height="14">
		        <% } else if (isConfigPrcExpanded) { %>
		            <img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Collapse_ConfigPricing','<%= i %>')" ezfHyo="A" height="14">
		        <% } %>
		        </td>
				<td class="stab">Config Pricing</td>
			</tr>
		</table>

	<div id=<%= "configPricing" + i %>>
			<% for ( int ixR = 0; ixR < bMsg.R.getValidCount(); ixR++ ) { %>
			    <% BigDecimal modelId_R = bMsg.R.no( ixR ).mdlId_R.getValue(); %>
			    <% BigDecimal dsContrDtlPk_R = bMsg.R.no( ixR ).dsContrDtlPk_R.getValue(); %>

		        <% if ((!DS_CONTR_CATG.FLEET.equals(bMsg.dsContrCatgCd.getValue())
		          		&& (ZYPCommonFunc.hasValue(modelId) && ZYPCommonFunc.hasValue(modelId_R) && modelId.compareTo(modelId_R) == 0))) { %>

			<div id=<%= "config" + ixR %> style="border-top:#aaAAff solid 2px;border-left:#aaAAff solid 1px;margin-left:30px;">
			    <%-- Header --%>
				<div style="margin-bottom:5px;">
				<%--	R <ezf:row ezfHyo="R">	--%>
	    			<div id=<%= "configSvcPrc" + ixR %> style="BACKGROUND-COLOR: #e9E9ff;">
		                <table width="1059" cellpadding="0" cellspacing="0" border="0" style="table-leyout:fixed;">
		                    <col width="040"><!-- Config# -->
		                    <col width="150">
		                    <col width="020">
		                    <col width="040">
		                    <col width="090"><!-- Service Price List  -->
		                    <col width="210">
		                    <col width="088"><!-- Service Package -->
		                    <col width="210">
		                    <col width="082"><!-- Tiered Pricing -->
		                    <col width="">
	                        <tr>
	                            <td class="stab">Config#</td>
	                            <td><ezf:inputText name="dsOrdPosnNum_R" ezfName="dsOrdPosnNum_R" value="1" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                            <td class="stab"></td>
	                            <td></td>
	                            <td class="stab">
						        	<ezf:anchor name="" ezfName="" ezfHyo="R" ezfArrayNo="<%= ixR %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Service_Price_List_Service_Pricing_forConfigPricing" >
	                            		Service Price List
	                            	</ezf:anchor>
	                            </td>
	                            <td>
	                               <ezf:inputText name="prcCatgNm_R" ezfName="prcCatgNm_R" value="ESS PUBLIC SERVICE" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherEvent1="OnBlur_ServicePriceList_SvcPrc_forConfigPricing" otherAttr=" size=\"28\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_SvcPrc_forConfigPricing\""/>
	                            </td>
	                            <td class="stab">
	                            	Service Package
	                            </td>
	                            <td>
	                                <span id="<%= ixR %>">
                                        <ezf:select name="prcMtrPkgPk_R" ezfName="prcMtrPkgPk_R" ezfHyo="R" ezfCodeDispHyo="R" ezfArrayNo="<%= ixR %>" ezfBlank="1" otherEvent1=" onchange=\"sendServer('OnChange_SvcPkg_forConfigPricing', this.parentNode.id)\"" otherAttr=" style=\"width: 170px; \"" ezfCodeName="prcMtrPkgPk_RL" ezfDispName="prcMtrPkgNm_RL" />
	                                </span>
	                            </td>
	                            <td class="stab">Tiered Pricing </td>
	                            <td>
	                                <span id="<%= ixR %>"> 
                                        <ezf:select name="prcTierSvcOfferCd_R" ezfName="prcTierSvcOfferCd_R" ezfHyo="R" ezfArrayNo="<%= ixR %>" ezfBlank="1" otherEvent1=" onchange=\"sendServer('OnChange_Tiered_forConfigPricing', this.parentNode.id)\""  ezfCodeName="prcTierSvcOfferCd_KP" ezfDispName="prcTierSvcOfferDescTxt_VW" />
	                                </span>
	                            </td>
	                            <td></td>
	                        </tr>
		                </table>
	                    <table width="1059" cellpadding="0" cellspacing="0" border="0" style="table-layout:fixed;">
	                        <col width="100">
	                        <col width="150">
	                        <col width="090">
	                        <col width="212">
	                        <col width="086">
	                        <col width="211">
	                        <col width="041">
	                        <col width="040">
	                        <col width="">
	                        <tr>
	                            <td class="stab">Unit Periodic Base</td>
	                            <td>
	                            	<ezf:inputText name="xxTotPrcAmt_PR" ezfName="xxTotPrcAmt_PR" value="100.00" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"18\" maxlength=\"20\" ezftoupper=\"\""/>
	                            </td>
	                            <td class="stab">Extended Base </td>
	                            <td>
	                            	<ezf:inputText name="xxTotPrcAmt_ER" ezfName="xxTotPrcAmt_ER" value="100.00" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"18\" maxlength=\"20\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
	                            </td>
	                            <td class="stab">Total Base </td>
	                            <td>
	                            	<ezf:inputText name="xxTotPrcAmt_TR" ezfName="xxTotPrcAmt_TR" value="1,200.00" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"18\" maxlength=\"20\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
	                            </td>
	                            <td class="stab">Term </td>
	                            <td>
	                            	<ezf:inputText name="termMthAot_R" ezfName="termMthAot_R" value="12" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
	                            </td>
	                            <td>
	                            	<ezf:inputButton name="OpenWin_Supply_Agreement" value="Supply Agreement" ezfHyo="R" ezfArrayNo="<%= ixR %>" htmlClass="pBtn9" />
	                            </td>
	                        </tr>
	                    </table>
	                    <table width="1059" cellpadding="0" cellspacing="0" border="0" style="table-layout:fixed;">
	                        <col width="100">
	                        <col width="150">
	                        <col width="090">
	                        <col width="212">
	                        <col width="086">
	                        <col width="076">
	                        <col width="159">
	                        <col width="040">
	                        <col width="">
	                        <tr>
	                            <td class="stab"></td>
	                            <td></td>
	                            <td class="stab">
	                                <ezf:anchor name="" ezfName="" ezfHyo="R" ezfArrayNo="<%= ixR %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerConfig" otherAttr=" id=\"dsAcctNum_LK#{EZF_ROW_NUMBER}\"">
	                                Customer (*)
	                                </ezf:anchor>
	                            </td>
	                            <td colspan="2">
	                                <ezf:inputText name="dsAcctNm_R" ezfName="dsAcctNm_R" value="TITLE RESOURCE GROUP" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"28\" maxlength=\"360\" ezffocusout=\"OnBlur_DeriveFromDsAcctNmConfig\"" otherEvent1="OnBlur_DeriveFromDsAcctNmConfig" />
	                                <ezf:inputText name="billToCustCd_R" ezfName="billToCustCd_R" value="1539044" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"8\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromDsAcctNumConfig\"" otherEvent1="OnBlur_DeriveFromDsAcctNumConfig" />
	                            </td>
	                            <td class="stab">
                                    <ezf:anchor name="" ezfName="" ezfHyo="R" ezfArrayNo="<%= ixR %>" ezfEmulateBtn="1" ezfGuard="OpenWin_LocationConfig" otherAttr=" id=\"shipToCustLocCd_LK#{EZF_ROW_NUMBER}\"">
                                    Bill To Code (*)
                                    </ezf:anchor>
	                            </td>
	                            <td colspan="3">
                                    <ezf:inputText name="xxGenlFldAreaTxt_R" ezfName="xxGenlFldAreaTxt_R" value="2408 N HIGHWAY 67 US TITLE FLORISSANT,MO 63033-2036" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" tabIndex=\"-1\" size=\"29\" maxlength=\"1000\""/>
                                    <ezf:inputText name="billToLocNum_R" ezfName="billToLocNum_R" value="2485753" ezfHyo="R" ezfArrayNo="<%= ixR %>" otherAttr=" size=\"8\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromCustLocCdConfig\"" otherEvent1="OnBlur_DeriveFromCustLocCdConfig" />
	                            </td>
	                        </tr>
	                    </table>
	    			</div>
	                <div style="overflow-x:none;width:1074; margin-left:0px;" id="<%= "usgPrcConfig" + ixR %>" >
		                <table width="1058" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;}">
	                        <col width="026" align="center" valign="bottom">
	                        <col width="070" align="center" valign="bottom"><!-- Band -->
	                        <col width="160" align="center" valign="bottom"><!-- Billing Counter Name Hard Counter Name/Multiplier -->
	                        <col width="094" align="center" valign="bottom"><!-- Per Unit Periodic Covered Images -->
	                        <col width="069" align="center" valign="bottom"><!-- Excess Per Image Charge -->
	                        <col width="032" align="center" valign="bottom"><!-- Tier -->
	                        <col width="080" align="center" valign="bottom"><!-- Free Copy -->
	                        <col width="080" align="center" valign="bottom"><!-- Min. Vol -->
	                        <col width="080" align="center" valign="bottom"><!-- Min. Amt -->
	                        <col width="080" align="center" valign="bottom"><!-- Roll Over % -->
		                    <col width="210" align="center" valign="bottom"><!-- Customer -->
		                    <tr>
		                            <td class="pClothBs">&nbsp;</td>
                                    <td class="pClothBs">Band</td>
	                                <td class="pClothBs">Billing Counter Name<br>Hard Counter Name/Multiplier</td>
	                                <td class="pClothBs">Covered<br>Images</td>
	                                <td class="pClothBs">Image<br>Charge</td>
	                                <td class="pClothBs">Tier</td>
	                                <td class="pClothBs">Free Copy</td>
	                                <td class="pClothBs">Min. Vol</td>
	                                <td class="pClothBs">Min. Amt</td>
	                                <td class="pClothBs">Roll<br>Over %</td>
		                            <td class="pClothBs">Customer</td>
		                    </tr>
		                </table>
	                    <table width="1058" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal; margin-bottom:0px;}">
	                        <col width="026" align="center">
	                        <col width="070" align="center">
	                        <col width="160" align="center">
	                        <col width="090" align="center">
	                        <col width="070" align="center">
	                        <col width="032" align="center">
	                        <col width="080" align="center">
	                        <col width="080" align="center">
	                        <col width="080" align="center">
	                        <col width="080" align="center">
	                        <col width="210" align="center">

<%--	U <ezf:row ezfHyo="U">	--%>

    <% boolean isSmryLineConfig = false; %>
    <% BigDecimal modelId_U = BigDecimal.ZERO; %>
    <% String dsOrdPosnNum_U = null; %>
    <% String dsOrdPosnNum_R = null; %>
    <% for( int ixU = 0; ixU < bMsg.U.getValidCount(); ixU++ ) { %>

        <% actvFlg       = bMsg.U.no(ixU).actvFlg_U.getValue(); %>
        <% xxFlgNm       = bMsg.U.no(ixU).xxFlgNm_U.getValue(); %>

        <% modelId_U = bMsg.U.no( ixU ).mdlId_U.getValue(); %>
        <% dsOrdPosnNum_U = bMsg.U.no( ixU ).dsOrdPosnNum_U.getValue(); %>
        <% dsOrdPosnNum_R = bMsg.R.no( ixR ).dsOrdPosnNum_R.getValue(); %>

        <% String xxSmryLineFlgConfig = bMsg.U.no(ixU).xxSmryLineFlg_U.getValue(); %>

        <% if (ZYPCommonFunc.hasValue(bMsg.usgBllgCycleCd)
                  && (ZYPCommonFunc.hasValue(modelId) && ZYPCommonFunc.hasValue(modelId_U) && modelId.compareTo(modelId_U) == 0)
                  && (ZYPCommonFunc.hasValue(dsOrdPosnNum_R) && ZYPCommonFunc.hasValue(dsOrdPosnNum_U) && dsOrdPosnNum_R.compareTo(dsOrdPosnNum_U) == 0)) { %>

                                 <% if (ZYPConstant.FLG_ON_Y.equals(actvFlg)) { %>
	                                    <% if ("P".equals(xxFlgNm)) { %>
	                                    <tr valign="top">
	                                    	<td>
                                                <% isSmryLineConfig = "Y".equals(xxSmryLineFlgConfig); %>
                                                <% if (isSmryLineConfig) { %>
                                                    <img src="./img/wfcomp/S21WfPlus.gif" onclick="sendServer('Expand_LineConfig','<%= ixU %>')" ezfHyo="U" height="14">
                                                <% } else if (!isSmryLineConfig) { %>
                                                    <img src="./img/wfcomp/S21WfMinus.gif" onclick="sendServer('Collapse_LineConfig','<%= ixU %>')" ezfHyo="U" height="14">
                                                <% } %>
	                                    	</td>
	                                        <td>
	                                            <ezf:inputText name="prcListBandDescTxt_U" ezfName="prcListBandDescTxt_U" value="A" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherEvent1="OnChange_BandConfig" otherAttr=" size=\"3\" maxlength=\"50\" ezffocusout=\"OnChange_BandConfig\""/>
	                                            <ezf:inputButton name="OpenWin_Band_SearchConfig" value="B" ezfHyo="U" ezfArrayNo="<%= ixU %>" htmlClass="pBtn0" />
	                                        </td>
	                                        <td>
	                                            <ezf:inputText name="mtrLbDescTxt_UB" ezfName="mtrLbDescTxt_UB" value="BIL BW" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputHidden name="mtrLbDescTxt_U" ezfName="mtrLbDescTxt_U" value="REG BW SMALL" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputHidden name="contrMtrMultRate_U" ezfName="contrMtrMultRate_U" value="1.25" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"7\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
												<ezf:inputText name="mlyCopyInclPrcQty_U" ezfName="mlyCopyInclPrcQty_U" value="1,000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"11\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
												<ezf:inputText name="xsMtrAmtRate_U" ezfName="xsMtrAmtRate_U" value="0.05000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"8\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
						                    	<ezf:anchor name="xxScrEdtTpCd_UL" ezfName="xxScrEdtTpCd_UL" ezfHyo="U" ezfArrayNo="<%= ixU %>" ezfEmulateBtn="1" ezfGuard="OpenWin_TierPricingByConfig" >
													<ezf:label name="xxScrEdtTpCd_U" ezfName="xxScrEdtTpCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>" />
												</ezf:anchor>
	                                        </td>
	                                        <td>
	                                            <!-- Free Copy -->
	                                        	<ezf:inputText name="bllgFreeCopyCnt_U" ezfName="bllgFreeCopyCnt_U" value="12000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Vol -->
	                                        	<ezf:inputText name="bllgMinCnt_U" ezfName="bllgMinCnt_U" value="200000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Amt -->
	                                        	<ezf:inputText name="bllgMinAmtRate_U" ezfName="bllgMinAmtRate_U" value="150" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Roll Over % -->
	                                        	<ezf:inputText name="bllgRollOverRatio_U" ezfName="bllgRollOverRatio_U" value="0.05000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"12\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <ezf:inputText name="dsAcctNm_U" ezfName="dsAcctNm_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"360\" ezffocusout=\"OnBlur_DeriveFromDsAcctNmMeter\"" otherEvent1="OnBlur_DeriveFromDsAcctNmMeter" />
	                                            <ezf:inputText name="billToCustCd_U" ezfName="billToCustCd_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromDsAcctNumMeter\"" otherEvent1="OnBlur_DeriveFromDsAcctNumMeter" />
	                                            <ezf:anchor name="" ezfName="" ezfHyo="U" ezfArrayNo="<%= ixU %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CustomerMeter" >
	                                            C
	                                            </ezf:anchor>
	                                            <ezf:inputText name="xxGenlFldAreaTxt_U" ezfName="xxGenlFldAreaTxt_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"1000\" ezftoupper=\"\""/>
	                                            <ezf:inputText name="billToLocNum_U" ezfName="billToLocNum_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromCustLocCdMeter\"" otherEvent1="OnBlur_DeriveFromCustLocCdMeter" />
	                                            <ezf:anchor name="" ezfName="" ezfHyo="U" ezfArrayNo="<%= ixU %>" ezfEmulateBtn="1" ezfGuard="OpenWin_LocationMeter" >
	                                            L
												</ezf:anchor>
	                                        </td>
	                                    </tr>
	                                    <% } else { %>
                                                <% if (!isSmryLineConfig) { %><!-- Hard Counter -->
	                                    <tr>
	                                    	<td>
	                                        	&nbsp;
	                                    	</td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="prcListBandDescTxt_U" ezfName="prcListBandDescTxt_U" value="A" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"3\" maxlength=\"50\" ezftoupper=\"\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="mtrLbDescTxt_UB" ezfName="mtrLbDescTxt_UB" value="BIL BW" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputText name="mtrLbDescTxt_U" ezfName="mtrLbDescTxt_U" value="REG BW SMALL" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
	                                            <ezf:inputText name="contrMtrMultRate_U" ezfName="contrMtrMultRate_U" value="1.25" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"7\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="mlyCopyInclPrcQty_U" ezfName="mlyCopyInclPrcQty_U" value="1,000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
                                                <ezf:inputHidden name="xsMtrAmtRate_U" ezfName="xsMtrAmtRate_U" value="0.05000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                        	&nbsp;
	                                        </td>
	                                        <td>
	                                            <!-- Free Copy -->
	                                        	&nbsp;
	                                        	<ezf:inputHidden name="bllgFreeCopyCnt_U" ezfName="bllgFreeCopyCnt_U" value="12000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Vol -->
	                                        	&nbsp;
	                                        	<ezf:inputHidden name="bllgMinCnt_U" ezfName="bllgMinCnt_U" value="200000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Min. Amt -->
	                                        	&nbsp;
	                                        	<ezf:inputHidden name="bllgMinAmtRate_U" ezfName="bllgMinAmtRate_U" value="150" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
	                                            <!-- Roll Over % -->
	                                        	&nbsp;
	                                        	<ezf:inputHidden name="bllgRollOverRatio_U" ezfName="bllgRollOverRatio_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                        </td>
	                                        <td>
		                                        <ezf:inputHidden name="billToLocNum_U" ezfName="billToLocNum_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
		                                        <ezf:inputHidden name="xxGenlFldAreaTxt_U" ezfName="xxGenlFldAreaTxt_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
		                                        <ezf:inputHidden name="billToCustCd_U" ezfName="billToCustCd_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
		                                        <ezf:inputHidden name="dsAcctNm_U" ezfName="dsAcctNm_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
	                                        </td>
	                                    </tr>
                                                <% } else { %>
                                                <ezf:inputHidden name="prcListBandDescTxt_U" ezfName="prcListBandDescTxt_U" value="A" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"3\" maxlength=\"50\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="prcBookMdseCd_U" ezfName="prcBookMdseCd_U" value="153ZZ884" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="mtrLbDescTxt_UB" ezfName="mtrLbDescTxt_UB" value="BIL BW" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="mtrLbDescTxt_U" ezfName="mtrLbDescTxt_U" value="REG BW SMALL" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
                                                <ezf:inputHidden name="contrMtrMultRate_U" ezfName="contrMtrMultRate_U" value="1.25" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"8\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
                                                <ezf:inputHidden name="mlyCopyInclPrcQty_U" ezfName="mlyCopyInclPrcQty_U" value="1,000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                                <ezf:inputHidden name="xsMtrAmtRate_U" ezfName="xsMtrAmtRate_U" value="0.05000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                                <ezf:inputHidden name="bllgFreeCopyCnt_U" ezfName="bllgFreeCopyCnt_U" value="12000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <ezf:inputHidden name="bllgMinCnt_U" ezfName="bllgMinCnt_U" value="200000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <ezf:inputHidden name="bllgMinAmtRate_U" ezfName="bllgMinAmtRate_U" value="150" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                            <ezf:inputHidden name="bllgRollOverRatio_U" ezfName="bllgRollOverRatio_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>

		                                        <ezf:inputHidden name="billToLocNum_U" ezfName="billToLocNum_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
		                                        <ezf:inputHidden name="xxGenlFldAreaTxt_U" ezfName="xxGenlFldAreaTxt_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
		                                        <ezf:inputHidden name="billToCustCd_U" ezfName="billToCustCd_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
		                                        <ezf:inputHidden name="dsAcctNm_U" ezfName="dsAcctNm_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                                <% } %>
	                                    <% } %>

                                 <% } else { %><!-- actvFlg_U != "Y" -->
	                                 <ezf:inputHidden name="prcListBandDescTxt_U" ezfName="prcListBandDescTxt_U" value="A" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"3\" maxlength=\"50\" ezftoupper=\"\""/>
	                                 <ezf:inputHidden name="prcBookMdseCd_U" ezfName="prcBookMdseCd_U" value="153ZZ884" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"16\" ezftoupper=\"\""/>
	                                 <ezf:inputHidden name="mtrLbDescTxt_UB" ezfName="mtrLbDescTxt_UB" value="BIL BW" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
	                                 <ezf:inputHidden name="mtrLbDescTxt_U" ezfName="mtrLbDescTxt_U" value="REG BW SMALL" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
	                                 <ezf:inputHidden name="contrMtrMultRate_U" ezfName="contrMtrMultRate_U" value="1.25" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"8\" maxlength=\"4\" ezftouppe=\"\" style=\"text-align:right;padding:0px;\""/>
	                                 <ezf:inputHidden name="mlyCopyInclPrcQty_U" ezfName="mlyCopyInclPrcQty_U" value="1,000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"11\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
	                                 <ezf:inputHidden name="xsMtrAmtRate_U" ezfName="xsMtrAmtRate_U" value="0.05000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"18\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                     <ezf:inputHidden name="bllgFreeCopyCnt_U" ezfName="bllgFreeCopyCnt_U" value="12000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                     <ezf:inputHidden name="bllgMinCnt_U" ezfName="bllgMinCnt_U" value="200000" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                     <ezf:inputHidden name="bllgMinAmtRate_U" ezfName="bllgMinAmtRate_U" value="150" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>
                                     <ezf:inputHidden name="bllgRollOverRatio_U" ezfName="bllgRollOverRatio_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right;padding:0px;\""/>

	                                 <ezf:inputHidden name="billToLocNum_U" ezfName="billToLocNum_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
	                                 <ezf:inputHidden name="xxGenlFldAreaTxt_U" ezfName="xxGenlFldAreaTxt_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
	                                 <ezf:inputHidden name="billToCustCd_U" ezfName="billToCustCd_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
	                                 <ezf:inputHidden name="dsAcctNm_U" ezfName="dsAcctNm_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                 <% } %>

                                        <ezf:inputHidden name="xxSmryLineFlg_U" ezfName="xxSmryLineFlg_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="t_MdlNm_U" ezfName="t_MdlNm_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="mdlId_U" ezfName="mdlId_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="prcCatgCd_U" ezfName="prcCatgCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="prcListTpCd_U" ezfName="prcListTpCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="prcMtrPkgPk_U" ezfName="prcMtrPkgPk_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="bllgMtrLbCd_U" ezfName="bllgMtrLbCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="mdseDescShortTxt_U" ezfName="mdseDescShortTxt_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="usgMdseCd_U" ezfName="usgMdseCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="regMtrLbCd_U" ezfName="regMtrLbCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="xxChkBox_U" ezfName="xxChkBox_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="prcSvcTierTpCd_U" ezfName="prcSvcTierTpCd_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="minCopyVolCnt_U" ezfName="minCopyVolCnt_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="maxCopyVolCnt_U" ezfName="maxCopyVolCnt_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
                                        <ezf:inputHidden name="xxFlgNm_U" ezfName="xxFlgNm_U" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>

                                        <ezf:inputHidden name="actvFlg_U" ezfName="actvFlg_U" value="" ezfHyo="U" ezfArrayNo="<%= ixU %>"/>
	    <% } %>
    <% } %><!-- end loop ixU -->
<%--	U </ezf:row>	--%>

	                    </table>
	                </div>

			</div><!-- config1 -->
			</div>

				<% } %><!-- end if -->
			<% } %><!-- end loop config ixR-->

			</div><!-- config2 -->
		</div><!-- configPricing -->
	</div>

		<%--	A </ezf:row>	--%>
	    				</div>
<% } %>
									</span><!-- mdlSvcPrc -->

								<hr style="background-color:#A00000; height:5px;margin-bottom:0; margin-top:0px;">
<!-- Accessory Charge -->
                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;margin-top:-8px;">
                                    <col width="1089" align="left">
                                    <tr>
                                        <td class="pClothBs" style="font-size:9pt;">Accessory Charge</td>
                                    </tr>
                                </table>

                        <table width="1089" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td width="035"></td>
                                        <td width="055"></td>
                                        <td width="100"></td>
                                        <td width="810"></td>
                                        <td width="089"></td>
                                    </tr>
                                    <tr>
                                        <td class="stab" >
                                            <ezf:inputButton name="Add_Accessory" value="+" htmlClass="pBtn0" /></td>
                                        <td class="stab" >
                                            <ezf:inputButton name="Del_Accessory" value="-" htmlClass="pBtn0" /></td>
                                        <td class="stab">
					                    	<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Service_Price_List_Accessory_Charge" >
                                        	Service Price List
                                        	</ezf:anchor>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="prcCatgNm_HJ" ezfName="prcCatgNm_HJ" otherEvent1="OnBlur_ServicePriceList_AsryChrg" otherAttr=" size=\"75\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_AsryChrg\""/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </table>
                                <table width="924" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;}">
                                    <col width="025" align="center" valign="bottom">
                                    <col width="055" align="center" valign="bottom">
                                    <col width="153" align="center" valign="bottom">
                                    <col width="210" align="center" valign="bottom">
                                    <col width="110" align="center" valign="bottom">
                                    <col width="260" align="center" valign="bottom">
                                    <tr>
                                        <td class="pClothBs"></td>
                                        <td class="pClothBs">Line#</td>
                                        <td class="pClothBs">Covered Item</td>
                                        <td class="pClothBs">Item Description</td>
                                        <td class="pClothBs">Periodic Service Price</td>
                                        <td class="pClothBs">Price List</td>
                                    </tr>
                                </table>
                                <table width="924" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;">
                                    <col width="025" align="center">
                                    <col width="055" align="center">
                                    <col width="153" align="center">
                                    <col width="210" align="center">
                                    <col width="110" align="center">
                                    <col width="260" align="center">

<%--	J <ezf:row ezfHyo="J">	--%>
<% for( int i = 0; i < bMsg.J.getValidCount(); i++ ) { %>
                                    <tr>
                                        <td class="stab" align="center">
                                        	<ezf:inputCheckBox name="xxChkBox_J" ezfName="xxChkBox_J" value="Y" ezfHyo="J" ezfArrayNo="<%= i %>" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="xxLineNum_J" ezfName="xxLineNum_J" value="1.2" ezfHyo="J" ezfArrayNo="<%= i %>" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputButton name="OpenWin_Item_Search_Covered_Item" value="Item" ezfHyo="J" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                            <ezf:inputText name="mdseCd_J" ezfName="mdseCd_J" value="2592B001" ezfHyo="J" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_CoveredItem" otherAttr=" size=\"12\" maxlength=\"16\" ezffocusout=\"OnBlur_CoveredItem\""/>
                                            <ezf:inputButton name="Item_Desc_Covered_Item" value=">>" ezfHyo="J" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                        </td>
                                        <td>
                                        	<ezf:inputText name="mdseDescShortTxt_J" ezfName="mdseDescShortTxt_J" value="PCL PRINTER KIT-AB1" ezfHyo="J" ezfArrayNo="<%= i %>" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="addlBasePrcDealAmt_J" ezfName="addlBasePrcDealAmt_J" value="22.33" ezfHyo="J" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"20\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="prcCatgNm_J" ezfName="prcCatgNm_J" value="WWWWWWWW" ezfHyo="J" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_ServicePriceList_AsryChrg_P" otherAttr=" size=\"34\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_AsryChrg_P\""/>
                                            <ezf:inputButton name="OpenWin_Service_Price_List_Accessory_Charge_P" value="L" ezfHyo="J" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                        </td>
                                    </tr>
<% } %>
<%--	J </ezf:row ezfHyo="J">	--%>

                                </table>

							<span id="rentalEquip">
								<hr style="background-color:#A00000; height:5px;margin-bottom:0; margin-top:5px;">
<!-- Rental Equipment Charge Base & Accessory -->
                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;margin-top:-8px;">
                                    <col width="1089" align="left">
                                    <tr>
                                        <td class="pClothBs" style="font-size:9pt;">Rental Eq Charge Base & Accessory</td>
                                    </tr>
                                </table>

                        <table width="1089" cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td width="035"></td>
                                        <td width="055"></td>
                                        <td width="100"></td>
                                        <td width="800"></td>
                                        <td width="089"></td>
                                    </tr>
                                    <tr>
                                        <td class="stab" >
                                            <ezf:inputButton name="Add_RentalEquip" value="+" htmlClass="pBtn0" /></td>
                                        <td class="stab" >
                                            <ezf:inputButton name="Del_RentalEquip" value="-" htmlClass="pBtn0" /></td>
                                        <td class="stab">
                                        	<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Service_Price_List_RentalEquip" >
                                        	Rental Eq Price List
                                        	</ezf:anchor>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="prcCatgNm_HB" ezfName="prcCatgNm_HB" otherEvent1="OnBlur_ServicePriceList_RentalEquip" otherAttr=" size=\"75\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_RentalEquip\""/>
                                        </td>
                                        <td>
                                        </td>
                                    </tr>
                                </table>
                                <table width="1089" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;}">
                                    <col width="025" align="center" valign="bottom">
                                    <col width="055" align="center" valign="bottom">
                                    <col width="153" align="center" valign="bottom">
                                    <col width="210" align="center" valign="bottom">
                                    <col width="110" align="center" valign="bottom">
                                    <col width="260" align="center" valign="bottom">
                                    <col width="140" align="center" valign="bottom">
                                    <tr>
                                        <td class="pClothBs"></td>
                                        <td class="pClothBs">Line#</td>
                                        <td class="pClothBs">Covered Item</td>
                                        <td class="pClothBs">Item Description</td>
                                        <td class="pClothBs">Periodic Amount</td>
                                        <td class="pClothBs">Rental Eq Price List</td>
                                        <td class="pClothBs">Price Configuration Name</td>
                                    </tr>
                                </table>
                                <table width="1089" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;">
                                    <col width="025" align="center">
                                    <col width="055" align="center">
                                    <col width="153" align="center">
                                    <col width="210" align="center">
                                    <col width="110" align="center">
                                    <col width="260" align="center">
                                    <col width="140" align="center">

<%--	B <ezf:row ezfHyo="B">	--%>
<% for( int i = 0; i < bMsg.B.getValidCount(); i++ ) { %>
                                    <tr>
                                        <td class="stab" align="center">
                                        	<ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="<%= i %>" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="xxLineNum_B" ezfName="xxLineNum_B" value="1.2" ezfHyo="B" ezfArrayNo="<%= i %>" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputButton name="OpenWin_Item_Search_Covered_Item_RentalEquip" value="Item" ezfHyo="B" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                            <ezf:inputText name="mdseCd_B" ezfName="mdseCd_B" value="2592B001" ezfHyo="B" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_CoveredItem_RentalEquip" otherAttr=" size=\"12\" maxlength=\"16\" ezffocusout=\"OnBlur_CoveredItem_RentalEquip\""/>
                                            <ezf:inputButton name="Item_Desc_Covered_Item_RentalEquip" value=">>" ezfHyo="B" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                        </td>
                                        <td>
                                        	<ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" value="PCL PRINTER KIT-AB1" ezfHyo="B" ezfArrayNo="<%= i %>" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="addlBasePrcDealAmt_B" ezfName="addlBasePrcDealAmt_B" value="22.33" ezfHyo="B" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"20\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
                                        </td>
                                        <td>
                                        	<ezf:inputText name="prcCatgNm_B" ezfName="prcCatgNm_B" value="WWWWWWWW" ezfHyo="B" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_ServicePriceList_RentalEquip_P" otherAttr=" size=\"34\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_RentalEquip_P\""/>
                                            <ezf:inputButton name="OpenWin_Service_Price_List_RentalEquip_P" value="L" ezfHyo="B" ezfArrayNo="<%= i %>" htmlClass="pBtn0" />
                                        </td>
                                        <td>
                                        	<ezf:inputText name="prcListEquipConfigNm_B" ezfName="prcListEquipConfigNm_B" value="WWWWWWWW" ezfHyo="B" ezfArrayNo="<%= i %>" otherAttr=" size=\"20\" maxlength=\"50\" "/>
                                        </td>
                                    </tr>
<% } %>
<%--	B </ezf:row ezfHyo="B">	--%>

                                </table>
							</span><!-- rentalEquip -->

							<span id="addlSvcChrg">
								<hr style="background-color:#A00000; height:5px;margin-bottom:0;margin-top:10px;">
<!-- Additional Service Charge -->
                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;margin-top:-8px;">
                                    <col width="1089" align="left">
                                    <tr>
                                        <td class="pClothBs" style="font-size:9pt;">Additional Service Charge</td>
                                    </tr>
                                </table>

		                        <table width="1089" cellpadding="0" cellspacing="0" border="0">
		                            <tr>
                                        <td width="035"></td>
                                        <td width="055"></td>
                                        <td width="100"></td>
                                        <td width="810"></td>
                                        <td width="089"></td>
		                            </tr>
		                            <tr>
		                                <td class="stab" >
		                                    <ezf:inputButton name="Add_Service" value="+" htmlClass="pBtn0" /></td>
		                                <td class="stab" >
		                                    <ezf:inputButton name="Del_Service" value="-" htmlClass="pBtn0" /></td>
		                                <td class="stab">
					                    	<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Service_Price_List_Additional_Service_Charge" >
		                                	Service Price List
		                                	</ezf:anchor>
		                                </td>
		                                <td>
		                                    <ezf:inputText name="prcCatgNm_C" ezfName="prcCatgNm_C" otherEvent1="OnBlur_ServicePriceList_AddlChrg" otherAttr=" size=\"75\" maxlength=\"75\" ezffocusout=\"OnBlur_ServicePriceList_AddlChrg\""/>
		                                </td>
		                                <td></td>
		                            </tr>
		                        </table>

		                        <table width="1089" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;">
		                            <col width="025" align="center" valign="bottom">
		                            <col width="055" align="center" valign="bottom">
		                            <col width="140" align="center" valign="bottom">
		                            <col width="210" align="center" valign="bottom">
		                            <col width="140" align="center" valign="bottom">
		                            <col width="220" align="center" valign="bottom">
		                            <col width="120" align="center" valign="bottom">
		                            <col width="060" align="center" valign="bottom">
		                            <tr>
		                                <td class="pClothBs"></td>
		                                <td class="pClothBs">Line#</td>
		                                <td class="pClothBs">Covered Unit</td>
		                                <td class="pClothBs">Unit Description</td>
		                                <td class="pClothBs">Additional Charge Item</td>
		                                <td class="pClothBs">Charge Description</td>
		                                <td class="pClothBs">Periodic Service Price</td>
		                                <td class="pClothBs">Print Detail<br>on Invoice</td>
		                            </tr>
		                        </table>

		                        <table width="1089" border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;word-break: normal;">
		                            <col width="025" align="center">
		                            <col width="055" align="center">
		                            <col width="140" align="center">
		                            <col width="210" align="center">
		                            <col width="140" align="center">
		                            <col width="220" align="center">
		                            <col width="120" align="center">
		                            <col width="060" align="center">

		                            <ezf:row ezfHyo="C">
		                            <tr>
		                                <td class="stab" align="center">
		                                	<ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C#{EZF_ROW_NUMBER}\""/>
		                                </td>
		                                <td>
		                                	<ezf:inputText name="xxLineNum_C" ezfName="xxLineNum_C" value="1.1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/>
		                                </td>
		                                <td>
		                                	<ezf:inputButton name="OpenWin_Item_Search_Covered_Unit" value="Item" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
		                                    <ezf:inputText name="mdseCd_CU" ezfName="mdseCd_CU" value="2586B001" ezfHyo="C" ezfArrayNo="0" otherEvent1="OnBlur_CoveredUnit" otherAttr=" size=\"10\" maxlength=\"16\" ezffocusout=\"OnBlur_CoveredUnit\""/>
		                                    <ezf:inputButton name="Item_Desc_Covered_Unit" value=">>" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
		                                </td>
		                                <td>
		                                	<ezf:inputText name="mdseDescShortTxt_CU" ezfName="mdseDescShortTxt_CU" value="IMAGERUNNER 1025IF" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/>
		                                </td>
		                                <td>
		                                	<ezf:inputButton name="OpenWin_Item_Search_Additional_Charge_Item" value="Item" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
		                                    <ezf:inputText name="mdseCd_CI" ezfName="mdseCd_CI" value="XXXXXXXXXX" ezfHyo="C" ezfArrayNo="0" otherEvent1="OnBlur_AddlChrgItem" otherAttr=" size=\"10\" maxlength=\"16\" ezffocusout=\"OnBlur_AddlChrgItem\""/>
		                                    <ezf:inputButton name="Item_Desc_Additional_Charge_Item" value=">>" ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" />
		                                </td>
		                                <td>
		                                	<ezf:inputText name="mdseDescShortTxt_CI" ezfName="mdseDescShortTxt_CI" value="WWWWWWWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/>
		                                </td>
		                                <td>
		                                	<ezf:inputText name="addlChrgPrcDealAmt_C" ezfName="addlChrgPrcDealAmt_C" value="12.34" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" style=\"text-align:right;padding:0px;\" ezftoupper=\"\""/>
		                                </td>
		                                <td class="stab" align="center">
		                                	<ezf:inputCheckBox name="printDtlFlg_C" ezfName="printDtlFlg_C" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"printDtlFlg_C#{EZF_ROW_NUMBER}\""/>
		                                </td>
		                            </tr>
		                            </ezf:row>

		                            <ezf:skip>
		                            </ezf:skip>
		                        </table>
							</span><!-- addlSvcChrg -->

			                    </div>
			                </td>
			            </tr>
			        </table>

	            </div>
			</td>
		</tr>
	</table>

</div>
</center>
<script type="text/javascript">
function toggleServicePriceSettingLevel(obj) {
<% if (bMsg.A.getValidCount() > 1) { %>
    document.mainForm.xxSelFlg_A[obj.parentNode.id].value = obj.value;
<% } else { %>
    document.mainForm.xxSelFlg_A.value = obj.value;
<% } %>
    sendServer('Toggle_ServicePriceSettingLevel', obj.parentNode.id);
}
</script>

<%-- **** Task specific area ends here **** --%>
