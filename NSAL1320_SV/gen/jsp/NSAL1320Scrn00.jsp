<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190118140835 --%>
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
			<input type="hidden" name="pageID" value="NSAL1320Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Pricing Entry">

			<center>
			<%-- include S21BusinessProcessTAB --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<div class="pTab_BODY" style="width:100%;">
				<table width="100%">
					<col width="070">
					<col width="180">
					<col width="10">
					<col width="185">
					<col width="">
					<tr>
						<td class="stab">
							<ezf:select name="xxPageCd" ezfName="xxPageCd" ezfBlank="0" ezfCodeName="xxPageCd_L" ezfDispName="xxPageNm_L" >
							</ezf:select>
						</td>
						<td>
							<ezf:inputText name="xxScrItem50Txt" ezfName="xxScrItem50Txt" value="1234567890" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\""/>
						</td>
						<td>&nbsp;</td>
						<td>
							<ezf:inputButton name="SearchMaintenance" value="Search" htmlClass="pBtn6" />
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">
							<ezf:inputButton name="OpenWin_ShellConfigSelection" value="+" htmlClass="pBtn1" />
							<ezf:inputButton name="DelMaintenanceShell" value="-" htmlClass="pBtn1" />
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				</div>

				<div style="width:100%; height:520px; overflow-y:scroll; margin:0px; padding:0px;">
				<table width="100%" border="1" cellpadding="1" cellspacing="0">
					<col width="25">
					<col width="100%">


<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
<%@ page import="business.servlet.NSAL1320.NSAL1320BMsg" %>
<%@ page import="java.math.BigDecimal" %>
<% NSAL1320BMsg bMsg = (NSAL1320BMsg)databean.getEZDBMsg(); %>
<% BigDecimal shellLineNum_Pre = BigDecimal.ZERO; %>
<% BigDecimal shellLineNum     = BigDecimal.ZERO; %>
<% BigDecimal shellLineNum_Cur = BigDecimal.ZERO; %>
<% int ixRRow = 0; %>
<% int ixLRow = 0; %>
<% String hdlFuncNm = ""; %>

<% for( int i = 0; i < bMsg.A.getValidCount(); i++ ) { %>

    <% shellLineNum = bMsg.A.no( i ).shellLineNum.getValue(); %>
    <% if (bMsg.A.no(i).manContrOvrdFlg.isInputProtected()) {
           hdlFuncNm = "fncRadio(this);";
       } else {
           hdlFuncNm = "toggleManualOverride(this);";
       }
    %>

    <% if ( shellLineNum == null ) { continue; } %>
    <% if ( shellLineNum_Pre.compareTo( shellLineNum ) != 0) { %>

                <%--    A1 <ezf:row ezfHyo="A"> --%>
                    <tr id="id_row{EZF_ROW_NUMBER}">
                        <td>
                            <ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" />
                        </td>
                        <td>
                            <table id= <%= "shellHeader" + i %> style="BACKGROUND-COLOR: #ffffbb;;width:100%;table-layout:fixed" border="0">
                                <col width="100">
                                <col width="115">
                                <col width="100">
                                <col width="115">
                                <col width="105">
                                <col width="090">
                                <col width="080">
                                <col width="130">
                                <col width="089">
                                <col width="130">
                                <col width="">
                                <tr>
                                    <td class="stab">
                                        Contract#
                                    </td>
                                    <td>
                                        <ezf:label name="dsContrNum" ezfName="dsContrNum" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                    </td>
                                    <td class="stab" colspan="2">
                                        Manual Override &nbsp;&nbsp;
<% out.println("                                        <span id=\"" + i + "\">"); %>
<% out.println("                                            Yes"); %>
<% out.println("                                            <" + "input type=\"radio\" name=\"manContrOvrdFlg_" + i + "\" value=\"Y\" " + ("Y".equals(bMsg.A.no(i).manContrOvrdFlg.getValue()) ? "checked" : "") + " onclick=\"" + hdlFuncNm + "\">"); %>
<% out.println("                                            No"); %>
<% out.println("                                            <" + "input type=\"radio\" name=\"manContrOvrdFlg_" + i + "\" value=\"N\" " + ("N".equals(bMsg.A.no(i).manContrOvrdFlg.getValue()) ? "checked" : "") + " onclick=\"" + hdlFuncNm + "\">"); %>
<% out.println("                                        </span>"); %>
                                                <ezf:inputHidden name="manContrOvrdFlg" ezfName="manContrOvrdFlg" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                    </td>
                                    <td class="stab" colspan="7" id="<%= "manOvrd_" + i %>">
                                        <ezf:anchor name="svcMemoRsnDescTxt_LK" ezfName="svcMemoRsnDescTxt_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Override_Reason" otherAttr=" id=\"svcMemoRsnDescTxt_LK#{EZF_ROW_NUMBER}\"">
                                        Manual Override Reason
                                        </ezf:anchor>
                                        &nbsp;
                                        <ezf:inputText name="svcMemoRsnDescTxt" ezfName="svcMemoRsnDescTxt" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
                                        &nbsp;
                                        Manual Override Comments
                                        &nbsp;
                                        <ezf:textArea name="svcCmntTxt" ezfName="svcCmntTxt" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" maxlength=\"2000\" rows=\"2\" cols=\"30\""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="stab">
                                        Maintenance Shell#
                                    </td>
                                    <td>
                                        <ezf:inputText name="shellLineNum" ezfName="shellLineNum" value="1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabIndex=\"-1\" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
                                    </td>
                                    <td class="stab">
                                        <ezf:anchor name="svcPgmMdseCd_LK" ezfName="svcPgmMdseCd_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CmnBigShellItem" otherAttr=" id=\"svcPgmMdseCd_LK#{EZF_ROW_NUMBER}\"">
                                        Service Program (*)
                                        </ezf:anchor>
                                    </td>
                                    <td colspan="3">
                                        <ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="123456790" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromSvcPgmNm" otherAttr=" size=\"30\" maxlength=\"250\" ezffocusout=\"OnBlur_DeriveFromSvcPgmNm\""/>
                                        <ezf:inputText name="svcPgmMdseCd" ezfName="svcPgmMdseCd" value="153ZZ884" ezfHyo="A" ezfArrayNo="<%= i %>" otherEvent1="OnBlur_DeriveFromSvcPgmCd" otherAttr=" size=\"10\" maxlength=\"30\" ezffocusout=\"OnBlur_DeriveFromSvcPgmCd\""/>
                                    </td>
                                    <td class="stab">
                                        Contract Type
                                    </td>
                                    <td>
                                        <span id="<%= i %>">
                                        <ezf:select name="prcSvcContrTpCd" ezfName="prcSvcContrTpCd" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="prcSvcContrTpCd_L" ezfDispName="prcSvcContrTpDescTxt_L" otherEvent1=" onFocus=\"setPrevValContrTp(this.value);\" onchange=\"if (sendCheckContrTp(this)) {sendServer('OnChange_ContractType', this.parentNode.id);} else {return false;}\"" otherAttr=" style=\"width:110px\" id=\"prcSvcContrTpCd#{EZF_ROW_NUMBER}\""/>
                                        </span>
                                    </td>
                                    <td class="stab">
                                        Plan Type
                                    </td>
                                    <td>
                                        <span id="<%= i %>">
                                        <ezf:select name="prcSvcPlnTpCd" ezfName="prcSvcPlnTpCd" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="prcSvcPlnTpCd_L" ezfDispName="prcSvcPlnTpDescTxt_L" otherEvent1=" onFocus=\"setPrevValPlnTp(this.value);\" onchange=\"if (sendCheckPlnTp(this)) {sendServer('OnChange_PlanType', this.parentNode.id);} else {return false;}\"" otherAttr=" style=\"width:120px\" id=\"prcSvcPlnTpCd#{EZF_ROW_NUMBER}\""/>
                                        </span>
                                    </td>
                                    <td>&nbsp;</td>
                                </tr>

                                <tr>
                                    <td class="stab">
                                        Term From/To/Mon
                                    </td>
                                    <td>
                                        <ezf:inputText name="fromPerMthNum" ezfName="fromPerMthNum" value="1" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/><ezf:inputText name="toPerMthNum" ezfName="toPerMthNum" value="12" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
                                        <ezf:inputText name="termMthAot" ezfName="termMthAot" value="12" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabIndex=\"-1\" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
                                    </td>
                                    <td class="stab">
                                        Contract Indicator
                                    </td>
                                    <td>
                                        <span id="<%= i %>">
                                        <ezf:select name="dsContrCatgCd" ezfName="dsContrCatgCd" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="dsContrCatgCd_L" ezfDispName="dsContrCatgDescTxt_L" otherEvent1=" onFocus=\"setPrevVal(this.value);\" onchange=\"if (sendCheck(this)) {sendServer('OnChange_ContractIndicator', this.parentNode.id);} else {return false;}\"" otherAttr=" style=\"width:110px\" id=\"dsContrCatgCd#{EZF_ROW_NUMBER}\""/>
                                        </span>
                                    </td>
                                    <td class="stab" style="text-align:right">
                                        <ezf:anchor name="dsContrNum_LK" ezfName="dsContrNum_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_ContractSearchPopup" otherAttr=" id=\"dsContrNum_LK#{EZF_ROW_NUMBER}\"">
                                        Add To Contract
                                        </ezf:anchor>
                                    </td>
                                    <td>
                                        <ezf:inputText name="dsContrNum_AD" ezfName="dsContrNum_AD" value="123456789012345" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"10\" maxlength=\"30\" ezffocusout=\"OnBlur_DeriveFromAddToContract\"" otherEvent1="OnBlur_DeriveFromAddToContract"/>
                                    </td>

                                    <td class="stab">
                                        Base Bill Cycle
                                    </td>
                                    <td>
                                        <ezf:select name="baseBllgCycleCd" ezfName="baseBllgCycleCd" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="bllgCycleCd_L" ezfDispName="bllgCycleDescTxt_L" otherAttr=" style=\"width:110px\" id=\"baseBllgCycleCd#{EZF_ROW_NUMBER}\""/>
                                    </td>
                                    <td class="stab">
                                        Usage Bill Cycle
                                    </td>
                                    <td>
                                        <ezf:select name="usgBllgCycleCd" ezfName="usgBllgCycleCd" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="bllgCycleCd_L" ezfDispName="bllgCycleDescTxt_L" otherAttr=" style=\"width:120px\" id=\"usgBllgCycleCd#{EZF_ROW_NUMBER}\""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="stab">
                                        Fixed Months
                                    </td>
                                    <td>
                                        <ezf:inputText name="fixTermInMthAot" ezfName="fixTermInMthAot" value="12" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabIndex=\"-1\" size=\"3\" maxlength=\"3\" style=\"text-align:right;\""/>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="stab">
                                        Billed w/ Eq
                                    </td>
                                    <td>
                                        <ezf:select name="billWithEquipFlg" ezfName="billWithEquipFlg" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="xxFlgNm_L" ezfDispName="billWithEquipFlg_L" otherAttr=" style=\"width:50px\" id=\"billWithEquipFlg#{EZF_ROW_NUMBER}\""/>
                                    </td>
                                    <td class="stab">
                                        Billed by
                                    </td>
                                    <td>
                                        <span id="<%= i %>">
                                        <ezf:select name="billByTpCd" ezfName="billByTpCd" ezfHyo="A" ezfArrayNo="<%= i %>" ezfBlank="1" ezfCodeName="billByTpCd_L" ezfDispName="billByTpDescTxt_L" ezfCodeDispHyo="A" otherEvent1="onchange=\"sendServer('OnChange_BilledBy', this.parentNode.id);\"" otherAttr=" style=\"width:120px\" id=\"billByTpCd#{EZF_ROW_NUMBER}\""/>
                                        </span>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="stab" style="text-align:center;">
                                        Base Total
                                    </td>
                                    <td>
                                        <ezf:label name="xxTotAmt_S" ezfName="xxTotAmt_S" ezfHyo="A" ezfArrayNo="<%= i %>" />
                                    </td>
                                    <td class="stab">
                                        <ezf:anchor name="dsAcctNum_LK" ezfName="dsAcctNum_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Customer" otherAttr=" id=\"dsAcctNum_LK#{EZF_ROW_NUMBER}\"">
                                        Customer (*)
                                        </ezf:anchor>
                                    </td>
                                    <td colspan="3">
                                        <span id="<%= i %>">
                                        <ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="TITLE RESOURCE GROUP" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"30\" maxlength=\"360\" ezffocusout=\"OnBlur_DeriveFromDsAcctNm\"" otherEvent1=" onFocus=\"setPrevValDsAcct(this.value);\" onchange=\"if (sendCheckDsAcct(this)) {sendServer('OnBlur_DeriveFromDsAcctNm', this.parentNode.id);  } else {return false;}\"" />
                                        </span>
                                        <span id="<%= i %>">
                                        <ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="1539044" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"8\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromDsAcctNum\"" otherEvent1=" onFocus=\"setPrevValDsAcct(this.value);\" onchange=\"if (sendCheckDsAcct(this)) {sendServer('OnBlur_DeriveFromDsAcctNum', this.parentNode.id);  } else {return false;}\"" />
                                        </span>
                                    </td>
                                    <td class="stab">
                                        <ezf:anchor name="shipToCustLocCd_LK" ezfName="shipToCustLocCd_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_Location" otherAttr=" id=\"shipToCustLocCd_LK#{EZF_ROW_NUMBER}\"">
                                        Bill To Code (*)
                                        </ezf:anchor>
                                    </td>
                                    <td colspan="3">
                                        <ezf:inputText name="xxGenlFldAreaTxt_BI" ezfName="xxGenlFldAreaTxt_BI" value="2408 N HIGHWAY 67 US TITLE FLORISSANT,MO 63033-2036" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" tabIndex=\"-1\" size=\"30\" maxlength=\"1000\""/>
                                        <ezf:inputText name="soldToCustLocCd" ezfName="soldToCustLocCd" value="2485753" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" size=\"8\" maxlength=\"20\" ezffocusout=\"OnBlur_DeriveFromCustLocCd\"" otherEvent1="OnBlur_DeriveFromCustLocCd" />
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <ezf:inputButton name="OpenWin_SvcPricing" value="$" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn1" />
                                        <ezf:inputButton name="OpenWin_ShellConfigSelectionForDetail" value="+" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn1" />
                                        <ezf:inputButton name="DelDetail" value="-" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn1" />
                                        &nbsp;
                                        <ezf:inputButton name="OpenWin_MassApply" value="Mass Apply" ezfHyo="A" ezfArrayNo="<%= i %>" htmlClass="pBtn6" />
                                    </td>
                                    <td class="stab">
                                        <ezf:anchor name="cpoSvcAgmtVerNum_LK" ezfName="cpoSvcAgmtVerNum_LK" ezfHyo="A" ezfArrayNo="<%= i %>" ezfEmulateBtn="1" ezfGuard="OpenWin_CmnBigDocNum" otherAttr=" id=\"maintAgmtVerNum_LK#{EZF_ROW_NUMBER}\"">
                                        Document#
                                        </ezf:anchor>
                                    <td colspan="3">
                                        <ezf:inputText name="cpoSvcAgmtVerNum" ezfName="cpoSvcAgmtVerNum" value="12345678901234567890" ezfHyo="A" ezfArrayNo="<%= i %>" otherAttr=" maxlength=\"50\" size=\"40\""/>
                                    </td>
                                    <td class="stab" colspan="3">
                                        <div id="<%= i %>">
                                        <ezf:inputCheckBox name="applyEquipBillToFlg" ezfName="applyEquipBillToFlg" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" onClick="sendServer('OnClick_MntBillAsEquip', this.parentNode.id)" otherAttr=" id=\"applyEquipBillToFlg#{EZF_ROW_NUMBER}\""/>
                                        Maint Bill to Same as Equipment
                                        </div>
                                    </td>
                                </tr>
                            </table>

                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <col valign="top" align="right">
                                <col valign="top" align="left">
                                <tr>
                                    <td>
                                        <%-- LEFT-TABLE(TOP) --%>
                                        <div id="LeftTBL_TopB1" style="height:24; overflow-x:hidden; width:086;">
                                            <table border="1" cellpadding="1" cellspacing="0" width="086" style="table-layout:fixed;">
                                                <col align="center" width="026">
                                                <col align="center" width="060">
                                                <tr height="35">
                                                    <td class="pClothBs">
                                                        <span id="<%= i %>">
                                                        <ezf:inputCheckBox name="xxChkBox_BH" ezfName="xxChkBox_BH" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" onClick="sendServer('OnClick_CheckConfigHeader', this.parentNode.id)" otherAttr=" id=\"xxChkBox_BH#{EZF_ROW_NUMBER}\""/>
                                                        </span>
                                                    </td>
                                                    <td class="pClothBs">Config#</td>
                                                </tr>
                                            </table>
                                        </div>

                                        <%-- LEFT-TABLE(MID) --%>
                                        <div id="LeftTBLB1" style="overflow-x:hidden; width:086;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBLB1' ) );">
                                            <table border="1" cellpadding="1" cellspacing="0" width="086" id="B1" style="table-layout:fixed;">
                                                <col align="center" width="026">
                                                <col align="center" width="060">
                                                <col align="center" width="260">
                                                <col align="center" width="260">
                <%--    A1  --%>

                                            <%--    B1 <ezf:row ezfhyo="B"> --%>

<% int ixB = 0; %>
<% boolean existDtl = false; %>
<% for( int x = 0; x < bMsg.B.getValidCount(); x++ ) { %>
    <% shellLineNum_Cur = bMsg.B.no( x ).shellLineNum_B.getValue(); %>
    <% if( shellLineNum_Cur == null ) { continue; } %>
    <% if (shellLineNum.compareTo(shellLineNum_Cur) == 0) { %>
    <% existDtl = true; %>

                                                    <tr id=<%= "lineBL" + shellLineNum + "#" + ixB %>>
                                                        <td>
                                                            <ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="<%= ixLRow %>" otherAttr=" tabIndex=\"<%= 100 + x %>\" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\""/>
                                                        </td>
                                                        <td>
                                                            <% if ("Y".equals(bMsg.rntlOrdFlg.getValue())) { %>
                                                                <ezf:anchor name="dsOrdPosnNum_BX" ezfName="dsOrdPosnNum_BX" ezfEmulateBtn="1" ezfGuard="OpenWin_RentalShellDetail" ezfHyo="B" ezfArrayNo="<%= ixLRow %>" otherAttr=" ezfanchortext=\"\"">
                                                                    <ezf:label name="dsOrdPosnNum" ezfName="dsOrdPosnNum" ezfHyo="B" ezfArrayNo="<%= ixLRow %>" />
                                                                </ezf:anchor>
                                                            <% } else { %>
                                                                <ezf:inputText name="dsOrdPosnNum" ezfName="dsOrdPosnNum" value="1" ezfHyo="B" ezfArrayNo="<%= ixLRow %>" otherAttr=" tabIndex=\"-1\" size=\"6\" maxlength=\"6\" style=\"border:none;background-color:transparent;padding:0px;\""/>
                                                            <% } %>
                                                        </td>

                                                        <td style="text-align:left; word-break:break-all;  word-wrap:break-word;">
                                                            <ezf:label name="xxGenlFldAreaTxt_SH" ezfName="xxGenlFldAreaTxt_SH" ezfHyo="B" ezfArrayNo="<%= ixLRow %>"/>
                                                        </td>
	                                                    <td>
                                                            <ezf:inputText name="contrAvalFlg" ezfName="contrAvalFlg" value="1" ezfHyo="B" ezfArrayNo="<%= ixLRow %>" otherAttr=" tabIndex=\"-1\" size=\"1\" maxlength=\"1\""/>
	                                                        <img src="./img/calendar.gif" class="pCalendar">
	                                                    </td>
                                                    </tr>

        <% ixB++; %>
        <% ixLRow++; %>
    <% } %>
<% } %>

                                            <%--    B1 </ezf:row>   --%>

                <%--    A2  --%>
                                            </table>
                                        </div>
                                    </td>

                                    <td>
                                        <%-- RIGHT-TABLE(TOP) --%>
                                        <div id="RightTBL_TopB<%= i %>" style="overflow-x:hidden; width:992" onScroll="synchroScrollLeft( this.id, new Array( 'RightTBLB<%= i %>' ) );">
                                            <table style="width:2330px;table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                <col align="center" width="120">
                                                <col align="center" width="060">
                                                <col align="center" width="080">
                                                <col align="center" width="260">
                                                <col align="center" width="150">
                                                <col align="center" width="150">
                                                <col align="center" width="090">
                                                <col align="center" width="110">
                                                <col align="center" width="110">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <tr height="35">
                                                    <td class="pClothBs" align="center">Model</td>
                                                    <td class="pClothBs" align="center">Line#</td>
                                                    <td class="pClothBs" align="center">Item#</td>
                                                    <td class="pClothBs" align="center">Ship To</td>
                                                    <td class="pClothBs" align="center">Service Branch</td>
                                                    <td class="pClothBs" align="center">Meter Method</td>
                                                    <td class="pClothBs" align="center">PO Number</td>
                                                    <td class="pClothBs" align="center">PO Date</td>
                                                    <td class="pClothBs" align="center">PO Expr Date</td>
                                                    <td class="pClothBs" align="center"># of payments</td>
                                                    <td class="pClothBs" align="center">Frequency</td>
                                                    <td class="pClothBs" align="center">Total<br>Service/period</td>
                                                    <td class="pClothBs" align="center">Equip Base/period</td>
                                                    <td class="pClothBs" align="center">Rental Base/period</td>
                                                    <td class="pClothBs" align="center">Accessory<br>Base/period</td>
                                                    <td class="pClothBs" align="center">Additional/period</td>
                                                    <td class="pClothBs" align="center">Term Total</td>
                                                    <td class="pClothBs" align="center">Term Equip</td>
                                                    <td class="pClothBs" align="center">Term Rental</td>
                                                    <td class="pClothBs" align="center">Term Accessory</td>
                                                    <td class="pClothBs" align="center">Term Addl</td>
                                                </tr>
                                            </table>
                                        </div>

                                        <%-- RIGHT-TABLE(MID) --%>
                                        <div id="RightTBLB<%= i %>" style=" overflow-x:scroll; width:992" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBLB1' ) );synchroScrollLeft( this.id, new Array( 'RightTBL_TopB<%= i %>' ) );">
                                            <table style="width:2330px;table-layout:fixed;font-size:6" border="1" cellpadding="1" cellspacing="0" id="B2">
                                                <col align="center" width="120">
                                                <col align="center" width="060">
                                                <col align="center" width="080">
                                                <col align="left"   width="260">
                                                <col align="center" width="150">
                                                <col align="center" width="150">
                                                <col align="center" width="090">
                                                <col align="center" width="110">
                                                <col align="center" width="110">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                                                <col align="center" width="120">
                <%--    A2  --%>

                                        <%--    B2 <ezf:row ezfhyo="B"> --%>

<% ixB = 0; %>
<% for( int x = 0; x < bMsg.B.getValidCount(); x++ ) { %>
    <% shellLineNum_Cur = bMsg.B.no( x ).shellLineNum_B.getValue(); %>
    <% if( shellLineNum_Cur == null ) { continue; } %>
    <% if (shellLineNum.compareTo(shellLineNum_Cur) == 0) { %>

                                                <tr id=<%= "lineBR" + shellLineNum + "#" + ixB %>>
                                                    <td>
                                                        <ezf:inputText name="t_MdlNm" ezfName="t_MdlNm" value="IPC810" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"15\" maxlength=\"90\" style=\"border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="dplyLineNum" ezfName="dplyLineNum" value="1.1" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"7\" maxlength=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234567890" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"10\" maxlength=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td style="text-align:left; word-break:break-all;  word-wrap:break-word;">
                                                        <ezf:label name="xxGenlFldAreaTxt_SH" ezfName="xxGenlFldAreaTxt_SH" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="svcContrBrDescTxt" ezfName="svcContrBrDescTxt" value="LFS-201-CORPRATE" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"20\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:select name="mtrReadMethCd" ezfName="mtrReadMethCd" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" ezfBlank="1" ezfCodeName="mtrReadMethCd_L" ezfDispName="mtrReadMethDescTxt_L" otherAttr=" style=\"width:145px\" id=\"mtrReadMethCd#{EZF_ROW_NUMBER}\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="custIssPoNum" ezfName="custIssPoNum" value="123456789" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="custIssPoDt" ezfName="custIssPoDt" value="11/12/2015" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" id=\"custIssPoDt\" size=\"10\" maxlength=\"10\""/>
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('custIssPoDt', 4, '<%= ixRRow %>');">
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="dsPoExprDt" ezfName="dsPoExprDt" value="11/12/2015" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" id=\"dsPoExprDt\" size=\"10\" maxlength=\"10\""/>
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('dsPoExprDt', 4, '<%= ixRRow %>');">
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxFreqCycleCnt" ezfName="xxFreqCycleCnt" value="123456789" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" size=\"10\" maxlength=\"3\" ezftoupper=\"\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="baseBllgCycleDescTxt" ezfName="baseBllgCycleDescTxt" value="123456789" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\" style=\"text-align:center;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxDealAmt_TT" ezfName="xxDealAmt_TT" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxDealAmt_EQ" ezfName="xxDealAmt_EQ" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxDealAmt_RT" ezfName="xxDealAmt_RT" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxDealAmt_AC" ezfName="xxDealAmt_AC" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxDealAmt_AD" ezfName="xxDealAmt_AD" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxTermAmt_TT" ezfName="xxTermAmt_TT" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxTermAmt_EQ" ezfName="xxTermAmt_EQ" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxTermAmt_RT" ezfName="xxTermAmt_RT" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxTermAmt_AC" ezfName="xxTermAmt_AC" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="xxTermAmt_AD" ezfName="xxTermAmt_AD" value="100.00" ezfHyo="B" ezfArrayNo="<%= ixRRow %>" otherAttr=" tabIndex=\"-1\" size=\"14\" maxlength=\"22\" style=\"text-align:right;border:none;background-color:transparent;padding:0px;\""/>
                                                    </td>
                                                </tr>

                                        <ezf:inputHidden name="dsContrPk_B" ezfName="dsContrPk_B" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="shellLineNum_B" ezfName="shellLineNum_B" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="mdlId" ezfName="mdlId" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="cpoDtlLineNum" ezfName="cpoDtlLineNum" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="cpoDtlLineSubNum" ezfName="cpoDtlLineSubNum" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="crRebilCd" ezfName="crRebilCd" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>
                                        <ezf:inputHidden name="dsPoReqFlg" ezfName="dsPoReqFlg" ezfHyo="B" ezfArrayNo="<%= ixRRow %>"/>

        <% ixB++; %>
        <% ixRRow++; %>

    <% } %>
<% } %>

                                        <%--    B2 </ezf:row>   --%>

                <%--    A3  --%>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>

                        </td>
                    </tr>

    <% } %>
    <% shellLineNum_Pre = shellLineNum; %>
<% } %>
<%-- +++++ [END] Programming JSP for Nesting Records Table +++++ --%>
				<%--	A3 </ezf:row>	--%>

				</table>
				</div>

			</center>

<script type="text/javascript">
var prevVal='';
var prcExist='';
function setPrevVal(val) {
	prevVal  = val;
	if (val != '') {
		prcExist = 'Y';
	}
}

function sendCheck(obj) {

    outputMsg = '<%= bMsg.exMsgTxt_01.getValue() %>';
    if (prcExist != 'Y') {
        return true;
    }
    if(window.confirm(outputMsg)) {
      return true;
    } else {
      obj.value = prevVal;
      return false;
    }
}

var prevValContrTp='';
var prcExistContrTp='';
function setPrevValContrTp(val) {
	prevValContrTp  = val;
	if (val != '') {
		prcExistContrTp = 'Y';
	}
}
function sendCheckContrTp(obj) {

    outputMsg = '<%= bMsg.exMsgTxt_02.getValue() %>';
    if (prcExistContrTp != 'Y') {
        return true;
    }
    if(window.confirm(outputMsg)) {
      return true;
    } else {
      obj.value = prevValContrTp;
      return false;
    }
}

var prevValPlnTp='';
var prcExistPlnTp='';
function setPrevValPlnTp(val) {
	prevValPlnTp  = val;
	if (val != '') {
		prcExistPlnTp = 'Y';
	}
}
function sendCheckPlnTp(obj) {

    outputMsg = '<%= bMsg.exMsgTxt_03.getValue() %>';
    if (prcExistPlnTp != 'Y') {
        return true;
    }
    if(window.confirm(outputMsg)) {
      return true;
    } else {
      obj.value = prevValPlnTp;
      return false;
    }
}

var prevValDsAcct='';
var prcExistDsAcct='';
function setPrevValDsAcct(val) {
	prevValDsAcct  = val;
	if (val != '') {
		prcExistDsAcct = 'Y';
	}
}
function sendCheckDsAcct(obj) {

    outputMsg = '<%= bMsg.exMsgTxt_04.getValue() %>';
    if (prcExistDsAcct != 'Y') {
        return true;
    }
    if(window.confirm(outputMsg)) {
      return true;
    } else {
      obj.value = prevValDsAcct;
      return false;
    }
}
function toggleManualOverride(obj) {
<% if (bMsg.A.getValidCount() > 1) { %>
    document.mainForm.manContrOvrdFlg[obj.parentNode.id].value = obj.value;
<% } else { %>
    document.mainForm.manContrOvrdFlg.value = obj.value;
<% } %>
    sendServer('Manual_Override', obj.parentNode.id);
}
</script>


<%-- **** Task specific area ends here **** --%>
