<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240214104912 --%>
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
            <input type="hidden" name="pageID" value="NFBL2060Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Invoice Inquiry">

<%@ page import="business.servlet.NFBL2060.NFBL2060BMsg" %>
<%@ page import="business.servlet.NFBL2060.common.NFBL2060CommonLogic" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% NFBL2060BMsg  bMsg = (NFBL2060BMsg)databean.getEZDBMsg(); %>
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## HEADER ######################################## --%>
                <!-- ###TAB - HEAD -->
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="Mach Mst Inq" class="pTab_ON"><a href="#">Invoice Inquiry</a></li>
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
                <%-- include S21BusinessProcessTAB --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <!-- ###TAB - BODY -->
                <div class="pTab_BODY">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:20px;">
						<col width="115" align="left">
						<col width="305" align="left">
						<col width="105" align="left">
						<col width="300" align="left">
						<col width="83"  align="left">
						<col width="83"  align="left">
						<col width="">
						<tr height="25">
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:290px;\" tabindex=\"1\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td><ezf:inputButton name="SaveSearch" ezfName="SaveSearch" value="SaveSearch" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
							<td><ezf:inputButton name="DeleteSearch" ezfName="DeleteSearch" value="DeleteSearch" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
							<td>&nbsp;</td>
						</tr>
					</table>
                    <table border="0" cellpadding="0" cellspacing="1"width="1130">
                        <col width="80" align="left">
                        <col width="100" align="left">
                        <col width="70" align="left">
                        <col width="100" align="left">
                        <col width="50" align="left">
                        <col width="100" align="left">
                        <col width="140" align="left">
                        <col width="100" align="left">
                        <col width="40" align="left">
                        <col width="140" align="left">
                        <col width="180" align="left">
                        <tr height="20">
                            <td class="pClothBs" colspan="6" align="center">Supplier</td>
                            <td class="pClothBs" colspan="4" align="center">Invoice</td>
                            <td class="pClothBs" colspan="1" align="center">Invoice With</td>
                        <tr>
                        <tr>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V1" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Name</ezf:anchor></td>
                            <td colspan="3"><ezf:inputText name="dplyVndNm" ezfName="dplyVndNm" value="CANON USA NP / CLC DIVISION" otherAttr=" size=\"35\" maxlength=\"320\" ezftoupper=\"\""/></td>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V2" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Site Code</ezf:anchor></td>
                            <td><ezf:inputText name="apVndCd" ezfName="apVndCd" value="CAN02" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                            <td class="stab">Invoice Number</td>
                            <td><ezf:inputText name="apVndInvNum" ezfName="apVndInvNum" value="64207196" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                            <td class="stab">Status</td>
                            <td>
                                <ezf:select name="acctInvStsCd_S" ezfName="acctInvStsCd_S" ezfBlank="1" ezfCodeName="acctInvStsCd_C" ezfDispName="acctInvStsDescTxt_D" otherAttr=" style=\"width:100px\""></ezf:select>
                            </td>
                            <td class="stab">
                                <ezf:inputCheckBox name="xxChkBox_01" ezfName="xxChkBox_01" value="Y" />
                                Holds
                                <ezf:inputCheckBox name="xxChkBox_02" ezfName="xxChkBox_02" value="Y" />
                                PO Variance
                            </td>

                        </tr>
                        <tr>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V3" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Number</ezf:anchor></td>
                            <td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="235" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V5" ezfEmulateBtn="1" ezfGuard="OpenWin_PurchaseOrder" >PO Number</ezf:anchor></td>
                            <td><ezf:inputText name="poNum" ezfName="poNum" value="2913389" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V6" ezfEmulateBtn="1" ezfGuard="OpenWin_Receipt" >Receipt</ezf:anchor></td>
                            <td><ezf:inputText name="delyOrdNum" ezfName="delyOrdNum" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                            <td class="stab">Invoice Date From</td>
                            <td>
                                <ezf:inputText name="invDt_FR" ezfName="invDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_FR', 4);" >
                            </td>
                            <td class="stab" align="center">to</td>
                            <td>
                                <ezf:inputText name="invDt_TO" ezfName="invDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_TO', 4);" >
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="stab">PO Date From</td>
                            <td colspan="1">
                                <ezf:inputText name="poDt_FR" ezfName="poDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('poDt_FR', 4);" >
                            </td>
                            <td class="stab" align="center">to</td>
                            <td>
                                <ezf:inputText name="poDt_TO" ezfName="poDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('poDt_TO', 4);" >
                            </td>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V4" ezfEmulateBtn="1" ezfGuard="OnClick_TermLink" >Terms</ezf:anchor></td>
                            <td><ezf:inputText name="vndPmtTermDescTxt" ezfName="vndPmtTermDescTxt" value="30 NET" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab">Linked To ARCS Date From</td>
                            <td>
                                <ezf:inputText name="invAuthDt_FR" ezfName="invAuthDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('invAuthDt_FR', 4);" >
                            </td>
                            <td class="stab" align="center">to</td>
                            <td>
                                <ezf:inputText name="invAuthDt_TO" ezfName="invAuthDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('invAuthDt_TO', 4);" >
                            </td>
                            <td class="stab" align="center" height="18"></td>
                        </tr>
                        <tr>
                        	<td class="stab">VR Date From</td>
                            <td colspan="1">
                                <ezf:inputText name="vndRtrnSubmtDt_FR" ezfName="vndRtrnSubmtDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('vndRtrnSubmtDt_FR', 4);" >
                            </td>
                            <td class="stab" align="center">to</td>
                            <td>
                                <ezf:inputText name="vndRtrnSubmtDt_TO" ezfName="vndRtrnSubmtDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('vndRtrnSubmtDt_TO', 4);" >
                            </td>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_V7" ezfEmulateBtn="1" ezfGuard="OpenWin_VndRtrnOrder" >VR #</ezf:anchor></td>
                            <td><ezf:inputText name="vndRtrnNum" ezfName="vndRtrnNum" value="235" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                             <td class="stab">Invoice Amount From</td>
                             <td colspan="1">
                                <ezf:inputText name="acInvTotCostAmt_FR" ezfName="acInvTotCostAmt_FR" otherAttr=" size=\"10\" maxlength=\"30\""/>
                            </td>
                            <td class="stab" align="center">to</td>
                            <td>
                                <ezf:inputText name="acInvTotCostAmt_TO" ezfName="acInvTotCostAmt_TO" otherAttr=" size=\"10\" maxlength=\"30\""/>
                        </tr>
                        <tr>
                            <td class="stab">Line #</td>
                            <td><ezf:inputText name="dispPoDtlLineNum" ezfName="dispPoDtlLineNum" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_GlChrgAcctFrom" >Charge Account From</ezf:anchor></td>
                            <td colspan="3"><ezf:inputText name="xxCmntTxt_FR" ezfName="xxCmntTxt_FR" value="000.000.000.31200.00000.00.00.000.00000" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab" align="center" height="18"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_GlChrgAcctTo" >Charge Account to</ezf:anchor></td>
                            <td colspan="3"><ezf:inputText name="xxCmntTxt_TO" ezfName="xxCmntTxt_TO" value="000.000.000.31200.00000.00.00.000.00000" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_I1" ezfEmulateBtn="1" ezfGuard="OnClick_PaymentMethodLink" >Payment Method</ezf:anchor></td>
                            <td><ezf:inputText name="vndPmtMethDescTxt" ezfName="vndPmtMethDescTxt" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab">Source</td>
                            <td>
                                <ezf:select name="apInvCatgCd_S" ezfName="apInvCatgCd_S" ezfBlank="1" ezfCodeName="apInvCatgCd_C" ezfDispName="apInvCatgDescTxt_D" otherAttr=" style=\"width:100px\""/>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="stab">Payment Number</td>
                            <td><ezf:inputText name="apPmtChkNum" ezfName="apPmtChkNum" otherAttr=" size=\"10\" maxlength=\"15\" ezftoupper=\"\""/></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="stab">Payment Date From</td>
                            <td>
                                <ezf:inputText name="pmtDt_FR" ezfName="pmtDt_FR" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('pmtDt_FR', 4);" >
                            </td>
                            <td class="stab" align="center">to</td>
                            <td>
                                <ezf:inputText name="pmtDt_TO" ezfName="pmtDt_TO" value="08/11/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('pmtDt_TO', 4);" >
                            </td>
                            <td align="right">
<!--
                                <input type="button" class="pBtn4" value="Clear" name="" onclick="sendServer(this)" >
                                <input type="button" class="pBtn4" value="Search" name="" onclick="sendServer(this)" >
                                <input type="button" class="pBtn4" value="New" name="" onclick="sendServer(this)" >
-->
                                <ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" />
                                <ezf:inputButton name="OnClick_New" value="New" htmlClass="pBtn6" />
                            </td>
                        </tr>
                    </table>

<%-- ######################################## DETAIL ######################################## --%>

                    <table width="100%">
                        <col valign="top">
                        <tr>
                            <td>
                            <div class="pTab_HEAD">
                                <ul>
                                    <li class="pTab_ON" id="Summary" title="Summary"><ezf:anchor name="" ezfName="xxTabProt_02" ezfEmulateBtn="1" ezfGuard="TAB_Summary" otherAttr=" tabindex=\"-1\"">Summary</ezf:anchor></li>
                                    <li class="pTab_OFF" id="Detailed" title="Detailed"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Detailed" otherAttr=" tabindex=\"-1\"">Detailed</ezf:anchor></li>
                                </ul>
                            </div>
                            <c:choose>
                            <c:when test="${pageScope._ezddatabean.xxDplyTab == 'Summary'}">
                            <script type="text/javascript">
                                document.getElementById( "Detailed" ).className = "pTab_OFF";
                                document.getElementById( "Summary" ).className = "pTab_ON";
                            </script>
                            <div class="pTab_BODY_In"  id="Summary_div">
                                <table border="0" cellpadding="1" cellspacing="0" width="1100">
                                    <col width="10">
                                    <col width="490">
                                    <col width="600" align="right">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                        </td>
                                        <td>
                                            <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                            <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                            <jsp:param name="TableNm"     value="S" />
                                            <jsp:param name="ShowingFrom" value="xxPageShowFromNum_S" />
                                            <jsp:param name="ShowingTo"   value="xxPageShowToNum_S" />
                                            <jsp:param name="ShowingOf"   value="xxPageShowOfNum_S" />
                                            </jsp:include>
                                        </td>
                                    </tr>
                                </table>
                                <div id = "parentBoxB">
                                <table border="0">
                                    <tr>
                                        <td width="5"></td>
                                        <td>
                                            <div style="float:left; display:block"> <!-- left table -->
                                                <div id='leftTblHead' style="display:block;"></div>
                                                <div id='leftTbl' style="float:left; display:block; height:203px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
                                            </div>  <!-- left table -->
                                            <div style="float:left"> <!-- right table -->
                                            <div id='rightTblHead' style="width:1080; display:block; overflow:hidden; margin:0px;padding:0px;" >
                                                <table style="table-layout: fixed" border="1" cellpadding="1" cellspacing="0" width="1750" height="30" id="BHEAD" style="margin-right:20px" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <tr>
                                                        <td id="SH0" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'apInvCatgDescTxt_S1' )">Source<img id="sortIMG.apInvCatgDescTxt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'apVndInvNum_S1' )">Invoice Number<img id="sortIMG.apVndInvNum_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'invDt_S1' )">Invoice Date<img id="sortIMG.invDt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'invAuthDt_S1' )">Linked To ARCS Date<img id="sortIMG.invAuthDt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'prntVndCd_S1' )">Supplier Number<img id="sortIMG.prntVndCd_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'dplyVndNm_S1' )">Supplier Name<img id="sortIMG.dplyVndNm_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'apVndCd_S1' )">Supplier Site Code<img id="sortIMG.apVndCd_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'invAmt_S1' )">Invoice Amount<img id="sortIMG.invAmt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'invHdrDescTxt_S1' )">Header Description<img id="sortIMG.invHdrDescTxt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'acctInvStsDescTxt_S1' )">Status<img id="sortIMG.acctInvStsDescTxt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'poNum_S1' )">PO Number<img id="sortIMG.poNum_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'poApvlDt_S1' )">PO Date<img id="sortIMG.poApvlDt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'xxDplyTrxNumTxt_S1' )">Vendor Return Number<img id="sortIMG.xxDplyTrxNumTxt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'vndRtrnSubmtDt_S1' )">Vendor Return Date<img id="sortIMG.vndRtrnSubmtDt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'entPoDtlDealNetAmt_S1' )">Order Amount<img id="sortIMG.entPoDtlDealNetAmt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'apPmtChkNum_S1' )">Payment Number<img id="sortIMG.apPmtChkNum_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'pmtDt_S1' )">Payment Date<img id="sortIMG.pmtDt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="SH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'S', 'vndPmtTermDescTxt_S1' )">Terms<img id="sortIMG.vndPmtTermDescTxt_S1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div id='rightTbl' style="height:220; width:1097; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="S" width="1750" >
                                                    <col align="center" width="100">
                                                    <col align="center" width="100">
                                                    <col align="center" width="100">
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <% int sIdx = 0; %>
                                                    <ezf:row ezfHyo="S">
                                                        <tr id="S_rightTBLRow#{EZF_ROW_NUMBER}">
                                                            <td><ezf:inputText name="apInvCatgDescTxt_S1" ezfName="apInvCatgDescTxt_S1" value="EDI GATEWAY" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"100\" ezftoupper=\"\""/></td>
                                                            <td><ezf:anchor name="" ezfName="xxLinkProt_S1" ezfHyo="S" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_SummaryTabInvNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="apVndInvNum_S1" ezfName="apVndInvNum_S1" ezfHyo="S" ezfArrayNo="0" /></ezf:anchor></td>
                                                            <td><ezf:inputText name="invDt_S1" ezfName="invDt_S1" value="2016/04/10" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invAuthDt_S1" ezfName="invAuthDt_S1" value="2016/04/10" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prntVndCd_S1" ezfName="prntVndCd_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="dplyVndNm_S1" ezfName="dplyVndNm_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apVndCd_S1" ezfName="apVndCd_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invAmt_S1" ezfName="invAmt_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invHdrDescTxt_S1" ezfName="invHdrDescTxt_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="acctInvStsDescTxt_S1" ezfName="acctInvStsDescTxt_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                        <% if (NFBL2060CommonLogic.isValidPoNumAnchor(bMsg, bMsg.S.no(sIdx))) { %>
                                                            <td><ezf:anchor name="" ezfName="poNum_SL" ezfHyo="S" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_SummaryTabPoNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="poNum_S1" ezfName="poNum_S1" ezfHyo="S" ezfArrayNo="0" /></ezf:anchor></td>
                                                        <% } else { %>
                                                            <td><ezf:label name="poNum_S1" ezfName="poNum_S1" ezfHyo="S" ezfArrayNo="0" /></td>
                                                        <% } %>
                                                            <td><ezf:inputText name="poApvlDt_S1" ezfName="poApvlDt_S1" value="2016/04/11" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                        <% if (NFBL2060CommonLogic.isValidVndRtrnNumAnchor(bMsg.S.no(sIdx))) { %>
                                                            <td><ezf:anchor name="" ezfName="vndRtrnNum_SL" ezfHyo="S" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_SummaryTabVndRtrnNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="xxDplyTrxNumTxt_S1" ezfName="xxDplyTrxNumTxt_S1" ezfHyo="S" ezfArrayNo="0" /></ezf:anchor></td>
                                                        <% } else { %>
                                                            <td><ezf:label name="xxDplyTrxNumTxt_S1" ezfName="xxDplyTrxNumTxt_S1" ezfHyo="S" ezfArrayNo="0" /></td>
                                                        <% } %>
                                                            <td><ezf:inputText name="vndRtrnSubmtDt_S1" ezfName="vndRtrnSubmtDt_S1" value="2016/04/11" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="entPoDtlDealNetAmt_S1" ezfName="entPoDtlDealNetAmt_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apPmtChkNum_S1" ezfName="apPmtChkNum_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"15\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="pmtDt_S1" ezfName="pmtDt_S1" value="2016/04/12" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="vndPmtTermDescTxt_S1" ezfName="vndPmtTermDescTxt_S1" value="HOLD_DESC" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                            <td class="pAuditInfo">
                                                                <ezf:inputHidden name="xxRecHistCratTs_S1" ezfName="xxRecHistCratTs_S1" ezfHyo="S" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistCratByNm_S1" ezfName="xxRecHistCratByNm_S1" ezfHyo="S" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdTs_S1" ezfName="xxRecHistUpdTs_S1" ezfHyo="S" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdByNm_S1" ezfName="xxRecHistUpdByNm_S1" ezfHyo="S" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistTblNm_S1" ezfName="xxRecHistTblNm_S1" ezfHyo="S" ezfArrayNo="0" />
                                                            </td>
                                                        </tr>
                                                    <% sIdx++; %>
                                                    </ezf:row>
                                                </table>
                                            </div>
                                            </div><!-- right table -->
                                        </td>
                                    </tr>
                                </table>
                                </div> <!-- parent box -->
                                <script type="text/javascript" defer>
                                 S21TableUI.initialize("parentBoxB", "BHEAD", "S", 2, true);
                                </script>
                            </div>
                            </c:when>
                            <c:when test="${pageScope._ezddatabean.xxDplyTab == 'Detailed'}">
                            <script type="text/javascript">
                                document.getElementById( "Detailed" ).className = "pTab_ON";
                                document.getElementById( "Summary" ).className = "pTab_OFF";
                            </script>
                            <div class="pTab_BODY_In" border="0"   id="Detailed_div">
                                <table border="0" cellpadding="1" cellspacing="0" width="1100">
                                    <col width="10">
                                    <col width="490">
                                    <col width="600" align="right">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                        </td>
                                        <td>
                                            <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                            <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                            <jsp:param name="TableNm"     value="D" />
                                            <jsp:param name="ShowingFrom" value="xxPageShowFromNum_D" />
                                            <jsp:param name="ShowingTo"   value="xxPageShowToNum_D" />
                                            <jsp:param name="ShowingOf"   value="xxPageShowOfNum_D" />
                                            </jsp:include>
                                        </td>
                                    </tr>
                                </table>
                                <div id="parentBoxA">
                                <table border="0">
                                    <tr>
                                        <td width="5"></td>
                                        <td>
                                            <div style="float:left; display:block"><!-- left table -->
                                                 <div id='leftTblHead' style="display:block;"></div>
                                                 <div id='leftTbl' style="float:left; display:block; height:203px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
                                            </div><!-- left table -->
                                            <div style="float:left"><!-- right table -->
                                            <div id='rightTblHead' style="display:block; overflow:hidden; margin:0px;padding:0px; width:1090;">
                                                <table style="table-layout:fixed; " width="3420" height="30" border="1" cellpadding="1" cellspacing="0" style="margin-right:20px" id="AHEAD" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="120" >
                                                    <col align="center" width="280" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="110" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="110" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <tr>
                                                        <td id="DH0" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apInvCatgDescTxt_D1' )">Source<img id="sortIMG.apInvCatgDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apVndInvNum_D1' )">Invoice Number<img id="sortIMG.apVndInvNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'invDt_D1' )">Invoice Date<img id="sortIMG.invDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'invAuthDt_D1' )">Linked To ARCS Date<img id="sortIMG.invAuthDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'prntVndCd_D1' )">Supplier Number<img id="sortIMG.prntVndCd_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dplyVndNm_D1' )">Supplier Name<img id="sortIMG.dplyVndNm_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apVndCd_D1' )">Supplier Site Code<img id="sortIMG.apVndCd_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'vndPmtTermDescTxt_D1' )">Terms<img id="sortIMG.vndPmtTermDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'acInvTotCostAmt_D1' )">Invoice Amount<img id="sortIMG.acInvTotCostAmt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'invHdrDescTxt_D1' )">Header Description<img id="sortIMG.invHdrDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'acctInvStsDescTxt_D1' )">Status<img id="sortIMG.acctInvStsDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'poNum_D1' )">PO Number<img id="sortIMG.poNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'poDt_D1' )">PO Date<img id="sortIMG.poDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'vndRtrnNum_D1' )">Vendor Return Number<img id="sortIMG.vndRtrnNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'vndRtrnSubmtDt_D1' )">Vendor Return Date<img id="sortIMG.vndRtrnSubmtDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH15" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'delyOrdNum_D1' )">Receipt Number<img id="sortIMG.delyOrdNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH16" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'dispPoDtlLineNum_D1' )">Order Line #<img id="sortIMG.dispPoDtlLineNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH17" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apVndInvLineNum_D1' )">Invoice Line #<img id="sortIMG.apVndInvLineNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH18" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apLineTpDescTxt_D1' )">Line Type<img id="sortIMG.apLineTpDescTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH19" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'mdseDescShortTxt_D1' )">Line Description<img id="sortIMG.mdseDescShortTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH20" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'acInvJrnlCostAmt_D1' )">Line Amount<img id="sortIMG.acInvJrnlCostAmt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH21" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'acInvTaxAmt_D1' )">Line Tax<img id="sortIMG.acInvTaxAmt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH22" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxFldValTxt_D1' )">Account Description<img id="sortIMG.xxFldValTxt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH23" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'xxCmntTxt_D2' )">Charge Account<img id="sortIMG.xxCmntTxt_D2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH24" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'invQty_D1' )">Order Quantity<img id="sortIMG.invQty_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH25" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'invRcvQty_D1' )">Received Quantity<img id="sortIMG.invRcvQty_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH26" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apBillQty_D1' )">Billed Quantity<img id="sortIMG.apBillQty_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH27" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'apPmtChkNum_D1' )">Payment Number<img id="sortIMG.apPmtChkNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH28" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'pmtDt_D1' )">Payment Date<img id="sortIMG.pmtDt_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH29" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'contrNum_D1' )">Contract<img id="sortIMG.contrNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH30" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'custDlrCd_D1' )">Dealer Code<img id="sortIMG.custDlrCd_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH31" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'serNum_D1' )">Serial Number<img id="sortIMG.serNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH32" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'csmpInvNum_D1' )">CSMP Invoice<img id="sortIMG.csmpInvNum_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="DH33" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'D', 'istlLocCtyAddr_D1' )">Install Loc<img id="sortIMG.istlLocCtyAddr_D1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <div id='rightTbl' style="height:220; width:1107; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                <table border="1" cellpadding="1" cellspacing="0" width="3600" id="D"  style="table-layout:fixed;">
                                                    <col align="center" width="100">
                                                    <col align="center" width="100">
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="150" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="120" >
                                                    <col align="center" width="280" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="110" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="110" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <col align="center" width="100" >
                                                    <% int dIdx = 0; %>
                                                    <ezf:row ezfHyo="D">
                                                        <tr id="D_rightTBLRow#{EZF_ROW_NUMBER}">
                                                            <td><ezf:inputText name="apInvCatgDescTxt_D1" ezfName="apInvCatgDescTxt_D1" value="EDI GATEWAY" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                            <td><ezf:anchor name="" ezfName="xxLinkProt_D1" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_DetailedTabInvNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="apVndInvNum_D1" ezfName="apVndInvNum_D1" ezfHyo="D" ezfArrayNo="0" /></ezf:anchor></td>
                                                            <td><ezf:inputText name="invDt_D1" ezfName="invDt_D1" value="08/11/2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invAuthDt_D1" ezfName="invAuthDt_D1" value="08/11/2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prntVndCd_D1" ezfName="prntVndCd_D1" value="235" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="dplyVndNm_D1" ezfName="dplyVndNm_D1" value="CANON USA NP / CLC DIVISION" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apVndCd_D1" ezfName="apVndCd_D1" value="CAN02" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="vndPmtTermDescTxt_D1" ezfName="vndPmtTermDescTxt_D1" value="30 NET" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="acInvTotCostAmt_D1" ezfName="acInvTotCostAmt_D1" value="1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invHdrDescTxt_D1" ezfName="invHdrDescTxt_D1" value="1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="acctInvStsDescTxt_D1" ezfName="acctInvStsDescTxt_D1" value="1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                        <% if (NFBL2060CommonLogic.isValidPoNumAnchor(bMsg, bMsg.D.no(dIdx))) { %>
                                                            <td><ezf:anchor name="" ezfName="poNum_DL" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_DetailedTabPoNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="poNum_D1" ezfName="poNum_D1" ezfHyo="D" ezfArrayNo="0" /></ezf:anchor></td>
                                                        <% } else { %>
                                                            <td><ezf:label name="poNum_D1" ezfName="poNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
                                                        <% } %>
                                                            <td><ezf:inputText name="poDt_D1" ezfName="poDt_D1" value="08/11/2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                        <% if (ZYPCommonFunc.hasValue(bMsg.D.no(dIdx).vndRtrnNum_D1)) { %>
                                                            <td><ezf:anchor name="" ezfName="vndRtrnNum_DL" ezfHyo="D" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_DetailedTabVndRtrnNumLink" otherAttr=" tabindex=\"-1\""><ezf:label name="vndRtrnNum_D1" ezfName="vndRtrnNum_D1" ezfHyo="D" ezfArrayNo="0" /></ezf:anchor></td>
                                                        <% } else { %>
                                                            <td><ezf:label name="vndRtrnNum_D1" ezfName="vndRtrnNum_D1" ezfHyo="D" ezfArrayNo="0" /></td>
                                                        <% } %>
                                                            <td><ezf:inputText name="vndRtrnSubmtDt_D1" ezfName="vndRtrnSubmtDt_D1" value="08/11/2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="delyOrdNum_D1" ezfName="delyOrdNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="dispPoDtlLineNum_D1" ezfName="dispPoDtlLineNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apVndInvLineNum_D1" ezfName="apVndInvLineNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apLineTpDescTxt_D1" ezfName="apLineTpDescTxt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="acInvJrnlCostAmt_D1" ezfName="acInvJrnlCostAmt_D1" value="285.60" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="acInvTaxAmt_D1" ezfName="acInvTaxAmt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="xxFldValTxt_D1" ezfName="xxFldValTxt_D1" value="Charge Account" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="xxCmntTxt_D2" ezfName="xxCmntTxt_D2" value="000.000.000.31200.00000.00.00.000.00000" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"39\" maxlength=\"300\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invQty_D1" ezfName="invQty_D1" value="4" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="invRcvQty_D1" ezfName="invRcvQty_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apBillQty_D1" ezfName="apBillQty_D1" value="4" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="apPmtChkNum_D1" ezfName="apPmtChkNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"15\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="pmtDt_D1" ezfName="pmtDt_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="contrNum_D1" ezfName="contrNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="custDlrCd_D1" ezfName="custDlrCd_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="serNum_D1" ezfName="serNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="csmpInvNum_D1" ezfName="csmpInvNum_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"100\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="istlLocCtyAddr_D1" ezfName="istlLocCtyAddr_D1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"200\" ezftoupper=\"\""/></td>
                                                            <td class="pAuditInfo">
                                                                <ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
                                                            </td>
                                                        </tr>
                                                    <% dIdx++; %>
                                                    </ezf:row>
                                                    <ezf:skip>
                                                    </ezf:skip>
                                                </table>
                                            </div>
                                            </div><!-- right table -->
                                        </td>
                                    </tr>
                                </table>
                                </div><!-- top -->
	                            <script type="text/javascript" defer>
	                                 S21TableUI.initialize("parentBoxA", "AHEAD", "D", 2, true);
	                            </script>
                            </div>
                            </c:when>
                            </c:choose>
                            </td>
                        </tr>
                    </table>

<%-- ######################################## FOOTER ######################################## --%>

                </div>
            </td>
        </tr>
    </table>
</center>

<script type="text/javascript">

    function setTab(tabId) {

        document.getElementById( "Detailed" ).className = "pTab_OFF";
        document.getElementById( "Summary" ).className = "pTab_OFF";

        document.getElementById( tabId ).className = "pTab_ON";

        document.getElementById( "Detailed_div" ).style.display="none";
        document.getElementById( "Summary_div" ).style.display="none";

        document.getElementById( tabId + "_div" ).style.display="block";
        
        return true;
    }
    
</script>
                            

<%-- **** Task specific area ends here **** --%>
