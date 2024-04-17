<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160621174741 --%>
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
            <input type="hidden" name="pageID" value="NSAL0910Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="CFS Invoice Error Correction">
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## HEADER ######################################## --%>
                <%-- include S21BusinessProcessTAB --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="CFS Invoice Error Correction" class="pTab_ON"><a href="#">CFS Inv</a></li>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </ul>
                </div>
                </ezf:skip>

                <div class="pTab_BODY">
<!-- ################################################## Search Criteria - [START] ################################################## -->
                    <table width="99%"  style="margin-top: 0px; margin-left: 5px" border="0">
                        <tr>
                            <td>
                                <table cellSpacing="0" cellPadding="1" border="0">
                                    <colgroup>
                                        <col width="100px">
                                        <col width="220px">
                                        <col width="20px">
                                        <col width="170px">
                                        <col width="120px">
                                        <col width="10px">
                                        <col width="10px">
                                        <col width="120px">
                                        <col width="70px">
                                        <col width="180px">
                                        <col width="5px">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <td class="stab"><Div Align="left">Dealer Code</Div></td>
                                            <td>
                                                <ezf:select name="dsAcctDlrCd" ezfName="dsAcctDlrCd" ezfBlank="1" ezfCodeName="dsAcctDlrCd_PC" ezfDispName="dsAcctDlrCd_PD" />
                                            </td>
                                            <td></td>
                                            <td class="stab"><Div Align="left">As of Date</Div></td>
                                            <td>
                                                <ezf:inputText name="procDt_FR" ezfName="procDt_FR" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
                                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('procDt_FR', 4);">
                                            </td>
                                            <td>-</td>
                                            <td></td>
                                            <td>
                                                <ezf:inputText name="procDt_TO" ezfName="procDt_TO" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
                                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('procDt_TO', 4);">
                                            </td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="stab"><Div Align="left">Status</Div></td>
                                            <td>
                                                <ezf:select name="cfsInvProcStsCd" ezfName="cfsInvProcStsCd" ezfBlank="1" ezfCodeName="cfsInvProcStsCd_PC" ezfDispName="cfsInvProcStsDescTxt_PD" />
                                            </td>
                                            <td></td>
                                            <td class="stab"><Div Align="left">Creation Date</Div></td>
                                            <td>
                                                <ezf:inputText name="xxCratDt_FR" ezfName="xxCratDt_FR" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
                                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_FR', 4);">
                                            </td>
                                            <td>-</td>
                                            <td></td>
                                            <td>
                                                <ezf:inputText name="xxCratDt_TO" ezfName="xxCratDt_TO" value="99/99/9999" otherAttr=" size=\"12\" maxlength=\"10\""/>
                                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxCratDt_TO', 4);">
                                            </td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <table cellSpacing="0" cellPadding="1" border="0">
                                    <colgroup>
                                        <col width="100px">
                                        <col width="220px">
                                        <col width="20px">
                                        <col width="170px">
                                        <col width="120px">
                                        <col width="10px">
                                        <col width="10px">
                                        <col width="120px">
                                        <col width="70px">
                                        <col width="100px">
                                        <col width="5px">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <td class="stab"><Div Align="left">Invoice Number(*)</Div></td>
                                            <td>
                                                <ezf:inputText name="csaInvNum" ezfName="csaInvNum" value="1234567890" otherAttr=" size=\"30\" ezftoupper=\"\""/>
                                            </td>
                                            <td></td>
                                            <td class="stab"><Div Align="left">Contract Number(*)</Div></td>
                                            <td>
                                                <ezf:inputText name="csaContrNum" ezfName="csaContrNum" value="1234567890" otherAttr=" size=\"30\" ezftoupper=\"\""/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td class="stab"><Div Align="left">Serial Number(*)</Div></td>
                                            <td>
                                                <ezf:inputText name="cfsSerNum" ezfName="cfsSerNum" value="1234567890" otherAttr=" size=\"30\" ezftoupper=\"\""/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->
                    <hr />
<!-- ################################################## Search Result   - [START] ################################################## -->
<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
                <%-- Pagenation --%>
                <table width="100%">
                    <tr>
                        <td>
                            <table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
                                <col width="20">
                                <col width="150">
                                <col width="40">
                                <col width="208">
                                <col width="80">
                                <col width="600">
                                
                                <tr height='25px'>
                                    <td>&nbsp;</td>
                                    <td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td align="right" class="stab">
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
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
<%-- ######################################## DETAIL ######################################## --%>
                <div id="parentBoxA">
                <table>
                    <tr>
                        <td width="10px"></td>
                        <td align="left" valign="top">
                            <div style="float:left; display:block"> <!-- left table -->
                                  <div id="leftTblHead" style="display:block;">
                                  </div>
                                  <div id="leftTbl" style="float:left; display:block; height:376px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
                                  </div>
                            </div>  <!-- left table -->
                            <div style="float:left"> <!-- right table -->
                                <div id="rightTblHead" style="width:1065px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" id="AHEAD" width="5130px" style="margin-right:20px" >
                                        <col width="80px"   align="center"> <!-- Dealer Code -->
                                        <col width="90px"   align="center"> <!-- As of Date -->
                                        <col width="90px"   align="center"> <!-- Invoice Date -->
                                        <col width="150px"  align="center"> <!-- CFS Payable Number -->
                                        <col width="60px"   align="center"> <!-- Line No -->
                                        <col width="100px"  align="center"> <!-- Invoice Number -->
                                        <col width="120px"  align="center"> <!-- Contract Number -->
                                        <col width="100px"  align="center"> <!-- Serial Number -->
                                        <col width="150px"  align="center"> <!-- Status -->
                                        <col width="200px"  align="center"> <!-- Error Message -->
                                        <col width="150px"  align="center"> <!-- Billing Period From Date -->
                                        <col width="140px"  align="center"> <!-- Billing Period To Date -->
                                        <col width="70px"   align="center"> <!-- FTG Flag -->
                                        <col width="90px"   align="center"> <!-- Meter Type -->
                                        <col width="90px"   align="center"> <!-- Billing Type -->
                                        <col width="90px"   align="center"> <!-- Credit Flag -->
                                        <col width="150px"  align="center"> <!-- CFS Lease Number -->
                                        <col width="110px"  align="center"> <!-- CFS Line Amount -->
                                        <col width="150px"  align="center"> <!-- Customer Name -->
                                        <col width="130px"  align="center"> <!-- S21 Contract Number -->
                                        <col width="140px"  align="center"> <!-- S21 Contract Modifier -->
                                        <col width="100px"  align="center"> <!-- DS Contract PK -->
                                        <col width="120px"  align="center"> <!-- Usage Item Code -->
                                        <col width="120px"  align="center"> <!-- Service Item Code -->
                                        <col width="100px"  align="center"> <!-- FTR Item Code -->
                                        <col width="140px"  align="center"> <!-- Account Rule Code -->
                                        <col width="140px"  align="center"> <!-- Invoice Rule Code -->
                                        <col width="190px"  align="center"> <!-- Bill To Customer Account Code -->
                                        <col width="100px"  align="center"> <!-- Bill To Location -->
                                        <col width="190px"  align="center"> <!-- Ship to Customer Account Code -->
                                        <col width="110px"  align="center"> <!-- Ship To Location -->
                                        <col width="190px"  align="center"> <!-- Service Line Contract Detail PK -->
                                        <col width="160px"  align="center"> <!-- Usage Line Contract PK -->
                                        <col width="100px"  align="center"> <!-- UoM Code -->
                                        <col width="140px"  align="center"> <!-- Bill To Customer Code -->
                                        <col width="140px"  align="center"> <!-- DS Invoice Type Code -->
                                        <col width="240px"  align="center"> <!-- Contract Version Effective From Date -->
                                        <col width="100px"  align="center"> <!-- CFS Fleet Flag -->
                                        <col width="180px"  align="center"> <!-- CFS Invoice Batch Number -->
                                        <col width="120px"  align="center"> <!-- S21 Invoice Number -->
                                        <tr height="25px">
                                            <td id="AH00" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctDlrCd_A' )">Dealer Code<img id="sortIMG.dsAcctDlrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH01" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'procDt_A' )">As of Date<img id="sortIMG.procDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH02" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDt_A' )">Invoice Date<img id="sortIMG.invDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH03" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsLeasePblNum_A' )">CFS Payable Number<img id="sortIMG.cfsLeasePblNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH04" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invLineNum_A' )">Line No<img id="sortIMG.invLineNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH05" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'csaInvNum_A' )">Invoice Number<img id="sortIMG.csaInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH06" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'csaContrNum_A' )">Contract Number<img id="sortIMG.csaContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH07" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsSerNum_A' )">Serial Number<img id="sortIMG.cfsSerNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH08" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsInvProcStsDescTxt_A' )">Status<img id="sortIMG.cfsInvProcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH09" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'intfcErrMsgTxt_A' )">Error Message<img id="sortIMG.intfcErrMsgTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerFromDt_A' )">Billing Period From Date<img id="sortIMG.bllgPerFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgPerThruDt_A' )">Billing Period To Date<img id="sortIMG.bllgPerThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ftrFlgOrigTxt_A' )">FTG Flag<img id="sortIMG.ftrFlgOrigTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'bllgMtrLbNm_A' )">Meter Type<img id="sortIMG.bllgMtrLbNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsBllgTpTxt_A' )">Billing Type<img id="sortIMG.cfsBllgTpTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cmFlgOrigTxt_A' )">Credit Flag<img id="sortIMG.cmFlgOrigTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsLeaseNum_A' )">CFS Lease Number<img id="sortIMG.cfsLeaseNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invTotDealNetAmt_A' )">CFS Line Amount<img id="sortIMG.invTotDealNetAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'endCustNm_A' )">Customer Name<img id="sortIMG.endCustNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsContrNum_A' )">S21 Contract Number<img id="sortIMG.dsContrNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'csaDsContrModTxt_A' )">S21 Contract Modifier<img id="sortIMG.csaDsContrModTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsContrPk_A' )">DS Contract PK<img id="sortIMG.dsContrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'usgItemCd_A' )">Usage Item Code<img id="sortIMG.usgItemCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcItemCd_A' )">Service Item Code<img id="sortIMG.svcItemCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'ftrItemCd_A' )">FTR Item Code<img id="sortIMG.ftrItemCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dfrdAcctgRuleCd_A' )">Account Rule Code<img id="sortIMG.dfrdAcctgRuleCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dfrdInvRuleCd_A' )">Invoice Rule Code<img id="sortIMG.dfrdInvRuleCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustAcctCd_A' )">Bill To Customer Account Code<img id="sortIMG.billToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToLocNum_A' )">Bill To Location<img id="sortIMG.billToLocNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToCustAcctCd_A' )">Ship to Customer Account Code<img id="sortIMG.shipToCustAcctCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToLocNum_A' )">Ship To Location<img id="sortIMG.shipToLocNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcLineContrDtlPk_A' )">Service Line Contract Detail PK<img id="sortIMG.svcLineContrDtlPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'usgLineContrDtlPk_A' )">Usage Line Contract PK<img id="sortIMG.usgLineContrDtlPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'uomCd_A' )">UoM Code<img id="sortIMG.uomCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH34" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A' )">Bill To Customer Code<img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH35" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsInvTpCd_A' )">DS Invoice Type Code<img id="sortIMG.dsInvTpCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH36" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'contrVrsnEffFromDt_A' )">Contract Version Effective From Date<img id="sortIMG.contrVrsnEffFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH37" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsFleetFlg_A' )">CFS Fleet Flag<img id="sortIMG.cfsFleetFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH38" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'cfsInvBatNum_A' )">CFS Invoice Batch Number<img id="sortIMG.cfsInvBatNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                            <td id="AH39" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invNum_A' )">S21 Invoice Number<img id="sortIMG.invNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="rightTbl" style="width:1082px; height:393px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A" width="5130px" >
                                        <col width="80px"   align="center"> <!-- Dealer Code -->
                                        <col width="90px"   align="center"> <!-- As of Date -->
                                        <col width="90px"   align="center"> <!-- Invoice Date -->
                                        <col width="150px"  align="center"> <!-- CFS Payable Number -->
                                        <col width="60px"   align="center"> <!-- Line No -->
                                        <col width="100px"  align="center"> <!-- Invoice Number -->
                                        <col width="120px"  align="center"> <!-- Contract Number -->
                                        <col width="100px"  align="center"> <!-- Serial Number -->
                                        <col width="150px"  align="center"> <!-- Status -->
                                        <col width="200px"  align="center"> <!-- Error Message -->
                                        <col width="150px"  align="center"> <!-- Billing Period From Date -->
                                        <col width="140px"  align="center"> <!-- Billing Period To Date -->
                                        <col width="70px"   align="center"> <!-- FTG Flag -->
                                        <col width="90px"   align="center"> <!-- Meter Type -->
                                        <col width="90px"   align="center"> <!-- Billing Type -->
                                        <col width="90px"   align="center"> <!-- Credit Flag -->
                                        <col width="150px"  align="center"> <!-- CFS Lease Number -->
                                        <col width="110px"  align="center"> <!-- CFS Line Amount -->
                                        <col width="150px"  align="center"> <!-- Customer Name -->
                                        <col width="130px"  align="center"> <!-- S21 Contract Number -->
                                        <col width="140px"  align="center"> <!-- S21 Contract Modifier -->
                                        <col width="100px"  align="center"> <!-- DS Contract PK -->
                                        <col width="120px"  align="center"> <!-- Usage Item Code -->
                                        <col width="120px"  align="center"> <!-- Service Item Code -->
                                        <col width="100px"  align="center"> <!-- FTR Item Code -->
                                        <col width="140px"  align="center"> <!-- Account Rule Code -->
                                        <col width="140px"  align="center"> <!-- Invoice Rule Code -->
                                        <col width="190px"  align="center"> <!-- Bill To Customer Account Code -->
                                        <col width="100px"  align="center"> <!-- Bill To Location -->
                                        <col width="190px"  align="center"> <!-- Ship to Customer Account Code -->
                                        <col width="110px"  align="center"> <!-- Ship To Location -->
                                        <col width="190px"  align="center"> <!-- Service Line Contract Detail PK -->
                                        <col width="160px"  align="center"> <!-- Usage Line Contract PK -->
                                        <col width="100px"  align="center"> <!-- UoM Code -->
                                        <col width="140px"  align="center"> <!-- Bill To Customer Code -->
                                        <col width="140px"  align="center"> <!-- DS Invoice Type Code -->
                                        <col width="240px"  align="center"> <!-- Contract Version Effective From Date -->
                                        <col width="100px"  align="center"> <!-- CFS Fleet Flag -->
                                        <col width="180px"  align="center"> <!-- CFS Invoice Batch Number -->
                                        <col width="120px"  align="center"> <!-- S21 Invoice Number -->
                                        <ezf:row ezfHyo="A">
                                            <tr height="25px">
                                                <td><ezf:inputText name="dsAcctDlrCd_A" ezfName="dsAcctDlrCd_A" value="1000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\""/></td>
                                                <td><ezf:inputText name="procDt_A" ezfName="procDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
                                                <td><ezf:inputText name="invDt_A" ezfName="invDt_A" value="12/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
                                                <td><ezf:inputText name="cfsLeasePblNum_A" ezfName="cfsLeasePblNum_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="invLineNum_A" ezfName="invLineNum_A" value="001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\""/></td>
                                                <td><ezf:inputText name="csaInvNum_A" ezfName="csaInvNum_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                <td><ezf:inputText name="csaContrNum_A" ezfName="csaContrNum_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                <td><ezf:inputText name="cfsSerNum_A" ezfName="cfsSerNum_A" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"25\" ezftoupper=\"\""/></td>
                                                <td><ezf:inputText name="cfsInvProcStsDescTxt_A" ezfName="cfsInvProcStsDescTxt_A" value="E" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="intfcErrMsgTxt_A" ezfName="intfcErrMsgTxt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\""/></td>
                                                <td><ezf:inputText name="bllgPerFromDt_A" ezfName="bllgPerFromDt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
                                                <td><ezf:inputText name="bllgPerThruDt_A" ezfName="bllgPerThruDt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
                                                <td><ezf:inputText name="ftrFlgOrigTxt_A" ezfName="ftrFlgOrigTxt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/></td>
                                                <td><ezf:inputText name="bllgMtrLbNm_A" ezfName="bllgMtrLbNm_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
                                                <td><ezf:inputText name="cfsBllgTpTxt_A" ezfName="cfsBllgTpTxt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
                                                <td><ezf:inputText name="cmFlgOrigTxt_A" ezfName="cmFlgOrigTxt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/></td>
                                                <td><ezf:inputText name="cfsLeaseNum_A" ezfName="cfsLeaseNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="invTotDealNetAmt_A" ezfName="invTotDealNetAmt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\""/></td>
                                                <td><ezf:inputText name="endCustNm_A" ezfName="endCustNm_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
                                                <td><ezf:inputText name="dsContrNum_A" ezfName="dsContrNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
                                                <td><ezf:inputText name="csaDsContrModTxt_A" ezfName="csaDsContrModTxt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
                                                <td><ezf:inputText name="dsContrPk_A" ezfName="dsContrPk_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
                                                <td><ezf:inputText name="usgItemCd_A" ezfName="usgItemCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
                                                <td><ezf:inputText name="svcItemCd_A" ezfName="svcItemCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
                                                <td><ezf:inputText name="ftrItemCd_A" ezfName="ftrItemCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
                                                <td><ezf:inputText name="dfrdAcctgRuleCd_A" ezfName="dfrdAcctgRuleCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="dfrdInvRuleCd_A" ezfName="dfrdInvRuleCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
                                                <td><ezf:inputText name="billToCustAcctCd_A" ezfName="billToCustAcctCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="billToLocNum_A" ezfName="billToLocNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
                                                <td><ezf:inputText name="shipToCustAcctCd_A" ezfName="shipToCustAcctCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="shipToLocNum_A" ezfName="shipToLocNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
                                                <td><ezf:inputText name="svcLineContrDtlPk_A" ezfName="svcLineContrDtlPk_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\""/></td>
                                                <td><ezf:inputText name="usgLineContrDtlPk_A" ezfName="usgLineContrDtlPk_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
                                                <td><ezf:inputText name="uomCd_A" ezfName="uomCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\""/></td>
                                                <td><ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
                                                <td><ezf:inputText name="dsInvTpCd_A" ezfName="dsInvTpCd_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\""/></td>
                                                <td><ezf:inputText name="contrVrsnEffFromDt_A" ezfName="contrVrsnEffFromDt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
                                                <td><ezf:inputText name="cfsFleetFlg_A" ezfName="cfsFleetFlg_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/></td>
                                                <td><ezf:inputText name="cfsInvBatNum_A" ezfName="cfsInvBatNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
                                                <td><ezf:inputText name="invNum_A" ezfName="invNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\""/></td>
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
                </div>
<!-- ################################################## Search Result   - [E N D] ################################################## -->
                </div>
            </td>
        </tr>
    </table>
</center>

<style TYPE="text/css">
<!--
    tr.checkLineBGcolor{background-color:yellow;}
    a img{border-style:none;}
-->
</style>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
</script>


<%-- **** Task specific area ends here **** --%>
