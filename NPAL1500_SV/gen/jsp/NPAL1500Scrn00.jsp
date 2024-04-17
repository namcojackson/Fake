<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230412192111 --%>
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
			<input type="hidden" name="pageID" value="NPAL1500Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Entry">
<!-- Application Parts Start -->
            <%-- ********************** --%>
            <%-- *** Upper Tab Area *** --%>
            <%-- ********************** --%>
<center>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <%-- ********************** --%>
                <%-- *** PO Header Area *** --%>
                <%-- ********************** --%>
                <div class="pTab_BODY">
                    <table style="margin-left:5; margin-top:0;">
                        <tr valign="top">
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" >
                                    <col width="105">
                                    <col width="145">
                                    <col width="80">
                                    <col width="15">
                                    <col width="120">
                                    <col width="225">
                                    <col width="15">
                                    <col width="90">
                                    <col width="80">
                                    <col width="30">
                                    <col width="200">
                                    <tr height="20">
                                        <td class="stab">PO Number</td>
                                        <td colspan="3"><ezf:inputText name="poNum" ezfName="poNum" value="50123456" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
                                        	<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                                        </td>
                                        <td class="stab">Document Source Type</td>
                                        <td><ezf:inputText name="poOrdSrcDescTxt" ezfName="poOrdSrcDescTxt" value="Sales Order" otherAttr=" size=\"31\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="prntVndCd_L1" ezfName="prntVndCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier</ezf:anchor></td>
                                        <td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="11331" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                        <td><ezf:inputButton name="Get_SupplierName" ezfName="Get_ShipToInfo" value=">>" htmlClass="pBtn0" /></td>
                                        <td><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="CANON USA NP / CLC" otherAttr=" size=\"28\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                    </tr>
                                    <tr height="20">
                                        <td class="stab">PO Type</td>
                                        <td>
                                            <ezf:select name="dsPoTpCd" ezfName="dsPoTpCd" ezfBlank="1" ezfCodeName="dsPoTpCd_CD" ezfDispName="dsPoTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_PoType')\"" otherAttr=" style=\"width:136\" ezftoupper=\"\""/>
                                        </td>
                                        <td style="text-align:right;"></td>
                                        <td></td>
                                        <td class="stab">Source Doc# / Qualifier</td>
                                        <td>
                                            <ezf:inputText name="trxRefNum" ezfName="trxRefNum" value="CP000001" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
                                            <ezf:inputText name="poQlfyCd" ezfName="poQlfyCd" value="DS" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/>
                                        </td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="vndCd_L1" ezfName="vndCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Site</ezf:anchor></td>
                                        <td><ezf:inputText name="vndCd" ezfName="vndCd" value="1133133" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                        <td><ezf:inputButton name="Get_SupplierSiteName" ezfName="Get_ShipToInfo" value=">>" htmlClass="pBtn0" /></td>
                                        <td><ezf:inputText name="vndNm" ezfName="vndNm" value="CAN02" otherAttr=" size=\"28\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                    </tr>
                                    <tr height="20">
                                        <td class="stab">Header Status</td>
                                        <td colspan="2">
                                            <ezf:inputText name="poHdrStsDescTxt" ezfName="poHdrStsDescTxt" value="Open" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
                                        </td>
                                        <td></td>
                                        <td class="stab">Buyer</td>
                                        <td><ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="Yasuto Nishiwaki" otherAttr=" size=\"31\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="srcRtlWhCd_L1" ezfName="srcRtlWhCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_LocationFromWH" >Source WH</ezf:anchor></td>
                                        <td><ezf:inputText name="srcRtlWhCd_SC" ezfName="srcRtlWhCd_SC" value="A02" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td></td>
                                        <td><ezf:inputText name="rtlWhNm_SC" ezfName="rtlWhNm_SC" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"28\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                        <td></td>
                                    </tr>
                                    <tr height="20">
                                        <td class="stab">Approval Status</td>
                                        <td colspan="3">
                                            <ezf:inputText name="poApvlStsDescTxt" ezfName="poApvlStsDescTxt" value="Approved" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
                                            <ezf:inputText name="poApvlDt" ezfName="poApvlDt" value="05/10/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
                                        </td>
                                        <td class="stab">Planning Group</td>
                                        <td>
                                            <ezf:select name="prchGrpCd" ezfName="prchGrpCd" ezfBlank="1" ezfCodeName="prchGrpCd_CD" ezfDispName="prchGrpDescTxt_NM" otherAttr=" style=\"width:225\""/>
                                        </td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="destRtlWhCd_L1" ezfName="destRtlWhCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_LocationToWH" >Destination WH</ezf:anchor></td>
                                        <td><ezf:inputText name="destRtlWhCd_DS" ezfName="destRtlWhCd_DS" value="C01" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td style="text-align:center;"><ezf:inputButton name="Get_ShipToInfo" ezfName="Get_ShipToInfo" value=">>" htmlClass="pBtn0" /></td>
                                        <td><ezf:inputText name="rtlWhNm_DS" ezfName="rtlWhNm_DS" value="C01 WOOD, SPARES" otherAttr=" size=\"28\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                    </tr>
                                    <tr height="20">
                                        <td class="stab">Date Created</td>
                                        <td><ezf:inputText name="xxOrdTs" ezfName="xxOrdTs" value="05/15/2015" otherAttr=" maxlength=\"10\" size=\"10\""/></td>
                                        <td ></td>
                                        <td></td>
                                        <td class="stab" style="vertical-align: top;">Description</td>
                                        <td rowspan="2" style="vertical-align: top;">
                                            <ezf:textArea name="poOrdCmntTxt" ezfName="poOrdCmntTxt" otherAttr=" cols='29' rows='2'"/>
                                            <ezf:skip>
                                            <textarea name="poOrdCmntTxt" rows="2" cols="29" ezfname="poOrdCmntTxt">aaaaa&#13;bbbbb</textarea>
                                            </ezf:skip>
                                        </td>
                                        <td></td>
                                        <td class="stab"><ezf:anchor name="shipToCustCd_L1" ezfName="shipToCustCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCustomer" >Ship To Customer</ezf:anchor></td>
                                        <td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                        <td style="text-align:center;"><ezf:inputButton name="Get_ShipToInfoCustomer" ezfName="Get_ShipToInfoCustomer" value=">>" htmlClass="pBtn0" /></td>
                                        <td><ezf:inputText name="shipToLocNm" ezfName="shipToLocNm" otherAttr=" size=\"28\" maxlength=\"45\" ezftoupper=\"\""/></td>
                                    </tr>
                                    <tr height="20">
                                        <td class="stab">Date & Time Needed</td>
                                        <td class="stab">
                                            <ezf:inputText name="rqstRcvDt" ezfName="rqstRcvDt" value="05/15/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
                                            <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt', 4);"/>
                                            <ezf:inputText name="xxDtTm" ezfName="xxDtTm" value="13:00" otherAttr=" maxlength=\"5\" size=\"5\""/>
                                        </td>
                                        <td colspan="3">
                                            <ezf:select name="xxTpCd_SL" ezfName="xxTpCd_SL" ezfCodeName="xxTpCd_PD" ezfDispName="xxTpNm_PD" htmlClass="stab" otherAttr=" style=\"width:50\""/>
                                            <ezf:inputButton name="Apply" value="Apply" htmlClass="pBtn3" />
                                        </td>
                                        <td colspan="7" style="text-align:right;">
                                            <ezf:inputButton name="Copy" value="Copy" htmlClass="pBtn6" />
                                            <ezf:inputButton name="OpenWin_AppvlHistory" value="Apprvl Hist" htmlClass="pBtn6" />
                                            <ezf:inputButton name="OpenWin_TextEntry" value="Comment/Text" htmlClass="pBtn10" />
                                            <ezf:inputButton name="Attachments" value="Attachments" htmlClass="pBtn6" />
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <!-- START 2023/02/14 S.Dong [QC#60966, ADD] -->
                                    <tr height="20">
                                        <td class="stab">Vendor Ship Date</td>
                                        <td colspan="3" class="stab">
                                            <ezf:inputText name="rqstShipDt" ezfName="rqstShipDt" value="05/15/2015" otherAttr=" maxlength=\"10\" size=\"10\""/>
                                            <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt', 4);"/>
                                            <ezf:inputButton name="RqstShipDt_Apply" value="Apply" htmlClass="pBtn3" />
                                    </tr>
                                    <!-- END 2023/02/14 S.Dong [QC#60966, ADD] -->
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <hr>
                <!-- ################################################## Header - [E N D] ################################################## -->

                <!-- ################################################## Detail   - [START] ################################################## -->
                <%-- ###TAB - HEAD --%>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="900" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="Detail" id="Detail" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_11" ezfEmulateBtn="1" ezfGuard="TAB_Detail" >Detail</ezf:anchor></li>
                                        <li title="AddlHeader" id="AddlHeader" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_21" ezfEmulateBtn="1" ezfGuard="TAB_AddlHeader" >Addl Header</ezf:anchor></li>
                                        <li title="APInvoice" id="APInvoice" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_31" ezfEmulateBtn="1" ezfGuard="TAB_APInvoice" >AP Invoice</ezf:anchor></li>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </ul>
                </div>
                
                <div class="pTab_BODY_In">
                    <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                    <!-- <div style="height: 390px" > -->
                    <div style="height: 360px" >
                    <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                        <!-- ######################################## Additional Header - [S T A R T] #################################### -->
                        <c:choose>
                            <c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_AddlHeader'}">
                                <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                <!-- <div id="TabContent-AddlHeader" style="height:400px ;overflow-y:scroll;"> -->
                                <div id="TabContent-AddlHeader" style="height:380px ;overflow-y:scroll;">
                                <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                    <script type="text/javascript">
                                        document.getElementById( "Detail"       ).className = "pTab_OFF";
                                        document.getElementById( "AddlHeader"   ).className = "pTab_ON";
                                        document.getElementById( "APInvoice"       ).className = "pTab_OFF";
                                    </script>
                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top:3px;margin-left:10px;">
                                        <col width="350">
                                        <col width="10">
                                        <col width="350">
                                        <col width="10">
                                        <col width="350">
                                        <tr>
                                            <td style="vertical-align: top;">
                                                <fieldset>
                                                    <legend><font color="black">Ship To Location</font></legend>
                                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
                                                        <col width="85">
                                                        <col width="255">
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Additional Name</td>
                                                            <td><ezf:inputText name="shipToAddlLocNm_ST" ezfName="shipToAddlLocNm_ST" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Address Line</td>
                                                            <td>
                                                                <ezf:textArea name="xxAllLineAddr_ST" ezfName="xxAllLineAddr_ST" otherAttr=" cols='33' rows='2'"/>
                                                                <ezf:skip>
                                                                <textarea name="xxAllLineAddr_ST" rows="3" cols="33" ezfname="xxAllLineAddr_ST" ezftoupper>1 ALLIED WAY</textarea>
                                                                </ezf:skip>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_PostCd" >Postal Code</ezf:anchor></td>
                                                            <td>
                                                                <ezf:inputText name="shipToPostCd_ST" ezfName="shipToPostCd_ST" value="06042-8933" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                                                <ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn2" />
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
                                                            <td><ezf:inputText name="shipToCtyAddr_ST" ezfName="shipToCtyAddr_ST" value="MANCHESTER" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_Cnty" >County</ezf:anchor></td>
                                                            <td><ezf:inputText name="shipToCntyNm_ST" ezfName="shipToCntyNm_ST" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AL" ezfName="xxLinkAncr_AL" ezfEmulateBtn="1" ezfGuard="OpenWin_ST" >State</ezf:anchor></td>
                                                            <td><ezf:inputText name="shipToStCd_ST" ezfName="shipToStCd_ST" value="CT" otherAttr=" size=\"10\" maxlength=\"2\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Province</td>
                                                            <td><ezf:inputText name="shipToProvNm_ST" ezfName="shipToProvNm_ST" otherAttr=" size=\"35\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="shipToCtryCd_L1" ezfName="shipToCtryCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_CTRY" >Country</ezf:anchor></td>
                                                            <td class="stab">
                                                                <ezf:inputText name="shipToCtryCd_ST" ezfName="shipToCtryCd_ST" value="US" otherAttr=" size=\"5\" maxlength=\"50\" ezftoupper=\"\""/>&nbsp;&nbsp;
                                                                <ezf:inputText name="ctryDescTxt_ST" ezfName="ctryDescTxt_ST" value="UNITED STATES AMERICA" otherAttr=" size=\"28\" maxlength=\"50\""/>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Contact Person</td>
                                                            <td><ezf:inputText name="ctacPsnNm" ezfName="ctacPsnNm" value="Narui Kazumichi" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">&nbsp;</td>
                                                            <td class="stab">&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                            </td>
                                            <td>&nbsp;</td>
                                            <td style="vertical-align: top;">
                                                <fieldset>
                                                    <legend><font color="black">Bill To Customer</font></legend>
                                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
                                                        <col width="85">
                                                        <!--<col width="255">-->
                                                        <col width="100">
                                                        <col width="70">
                                                        <col width="85">
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Code</td>
                                                            <td colspan="3"><ezf:inputText name="varCharConstVal_B1" ezfName="varCharConstVal_B1" value="00000" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Name</td>
                                                            <td colspan="3"><ezf:inputText name="varCharConstVal_B2" ezfName="varCharConstVal_B2" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Additional Name</td>
                                                            <td colspan="3"><ezf:inputText name="varCharConstVal_B3" ezfName="varCharConstVal_B3" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Address Line</td>
                                                            <td colspan="3">
                                                                <ezf:textArea name="xxAllLineAddr_BT" ezfName="xxAllLineAddr_BT" otherAttr=" cols='33' rows='2'"/>
                                                                <ezf:skip>
                                                                <textarea name="xxAllLineAddr_BT" rows="3" cols="33" ezfname="xxAllLineAddr_BT">3000 COMMERCE SQ. DRIVE</textarea>
                                                                </ezf:skip>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Postal Code</td>
                                                            <td colspan="3"><ezf:inputText name="varCharConstVal_B4" ezfName="varCharConstVal_B4" value="08016" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                        </tr>

                                                        <tr style="height: 24px;">
                                                            <td class="stab">City</td>
                                                            <td><ezf:inputText name="varCharConstVal_B5" ezfName="varCharConstVal_B5" value="BURLINGTON" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
                                                            <td class="stab">County</td>
                                                            <td><ezf:inputText name="varCharConstVal_B6" ezfName="varCharConstVal_B6" otherAttr=" size=\"10\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">State</td>
                                                            <td><ezf:inputText name="varCharConstVal_B7" ezfName="varCharConstVal_B7" value="NJ" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                            <td class="stab">Province</td>
                                                            <td><ezf:inputText name="varCharConstVal_B8" ezfName="varCharConstVal_B8" otherAttr=" size=\"10\" maxlength=\"50\""/></td>
                                                        </tr>

                                                        <tr style="height: 24px;">
                                                            <td class="stab">Country</td>
                                                            <td class="stab" colspan="3">
                                                                <ezf:inputText name="varCharConstVal_B9" ezfName="varCharConstVal_B9" value="US" otherAttr=" size=\"5\" maxlength=\"50\""/>&nbsp;&nbsp;
                                                                <ezf:inputText name="ctryDescTxt_BT" ezfName="ctryDescTxt_BT" value="UNITED STATES AMERICA" otherAttr=" size=\"28\" maxlength=\"50\""/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                                <%--
                                                <fieldset>
                                                    <legend><font color="black">CSA Return Address</font></legend>
                                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
                                                        <col width="85">
                                                        <!--<col width="255">-->
                                                        <col width="100">
                                                        <col width="70">
                                                        <col width="85">
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Code</td>
                                                            <td colspan="3"><ezf:inputText name="rtrnShipToRtlWhCd_RW" ezfName="rtrnShipToRtlWhCd_RW" value="00000" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Name</td>
                                                            <td colspan="3"><ezf:inputText name="rtlWhNm_RW" ezfName="rtlWhNm_RW" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Additional Name</td>
                                                            <td colspan="3"><ezf:inputText name="addlLocNm_RW" ezfName="addlLocNm_RW" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Address Line</td>
                                                            <td colspan="3">
                                                                <ezf:textArea name="xxAllLineAddr_RW" ezfName="xxAllLineAddr_RW" otherAttr=" cols='33' rows='2'"/>
                                                                <ezf:skip>
                                                                <textarea name="xxAllLineAddr_RW" rows="3" cols="33" ezfname="xxAllLineAddr_RW">3000 COMMERCE SQ. DRIVE</textarea>
                                                                </ezf:skip>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Postal Code</td>
                                                            <td colspan="3"><ezf:inputText name="postCd_RW" ezfName="postCd_RW" value="08016" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                        </tr>

                                                        <tr style="height: 24px;">
                                                            <td class="stab">City</td>
                                                            <td><ezf:inputText name="ctyAddr_RW" ezfName="ctyAddr_RW" value="BURLINGTON" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
                                                            <td class="stab">County</td>
                                                            <td><ezf:inputText name="cntyNm_RW" ezfName="cntyNm_RW" otherAttr=" size=\"10\" maxlength=\"50\""/></td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">State</td>
                                                            <td><ezf:inputText name="stCd_RW" ezfName="stCd_RW" value="NJ" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                            <td class="stab">Province</td>
                                                            <td><ezf:inputText name="provNm_RW" ezfName="provNm_RW" otherAttr=" size=\"10\" maxlength=\"50\""/></td>
                                                        </tr>

                                                        <tr style="height: 24px;">
                                                            <td class="stab">Country</td>
                                                            <td class="stab" colspan="3">
                                                                <ezf:inputText name="ctryCd_RW" ezfName="ctryCd_RW" value="US" otherAttr=" size=\"5\" maxlength=\"50\""/>&nbsp;&nbsp;
                                                                <ezf:inputText name="ctryDescTxt_RW" ezfName="ctryDescTxt_RW" value="UNITED STATES AMERICA" otherAttr=" size=\"28\" maxlength=\"50\""/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                                --%>
                                            </td>
                                            <td>&nbsp;</td>
                                            <td style="vertical-align: top;">
                                                <fieldset>
                                                    <legend><font color="black">Freight Information</font></legend>
                                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;">
                                                        <col width="85">
                                                        <col width="255">
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Freight Term</td>
                                                            <td>
                                                                <ezf:select name="frtCondCd" ezfName="frtCondCd" ezfBlank="1" ezfCodeName="frtCondCd_CD" ezfDispName="frtCondDescTxt_NM" otherAttr=" style=\"width:252\""/>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Service Level</td>
                                                            <td>
                                                                <ezf:select name="shpgSvcLvlCd" ezfName="shpgSvcLvlCd" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_CD" ezfDispName="shpgSvcLvlDescTxt_NM" otherAttr=" style=\"width:252\""/>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="carrCd_L1" ezfName="carrCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_CarrierCode" >Carrier</ezf:anchor></td>
                                                            <td class="stab">
                                                                <ezf:inputText name="carrCd" ezfName="carrCd" value="10044" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>&nbsp;&nbsp;
                                                                <ezf:inputText name="carrNm" ezfName="carrNm" value="Fedex ABC" otherAttr=" size=\"25\" maxlength=\"50\""/>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 24px;">
                                                            <td class="stab">Carrier Account</td>
                                                            <td><ezf:inputText name="carrAcctNum" ezfName="carrAcctNum" value="1232423" otherAttr=" size=\"35\" maxlength=\"50\""/></td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                                <fieldset>
                                                    <legend><font color="black">Header Details</font></legend>
                                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;  margin-bottom: 4px;">
                                                        <col width="85">
                                                        <col width="255">
                                                        <tr style="height: 24px;">
                                                            <td class="stab"><ezf:anchor name="vndPmtTermDescTxt_L1" ezfName="vndPmtTermDescTxt_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_PoTerm" >PO Term</ezf:anchor></td>
                                                            <td>
                                                                <ezf:inputText name="vndPmtTermDescTxt" ezfName="vndPmtTermDescTxt" otherAttr=" style=\"width:252\""/>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 36px;">
                                                            <td class="stab">Special Instructions</td>
                                                            <td colspan="3">
                                                                <ezf:textArea name="xxDsMultMsgDplyTxt_SI" ezfName="xxDsMultMsgDplyTxt_SI" otherAttr=" cols='33' rows='2'"/>
                                                                <ezf:skip>
                                                                <textarea name="xxDsMultMsgDplyTxt_SI" rows="3" cols="33" ezfname="xxDsMultMsgDplyTxt_SI">aaaaa&#13;bbbbb</textarea>
                                                                </ezf:skip>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 36px;">
                                                            <td class="stab">Receiver Note</td>
                                                            <td colspan="3">
                                                                <ezf:textArea name="xxDsMultMsgDplyTxt_RN" ezfName="xxDsMultMsgDplyTxt_RN" otherAttr=" cols='33' rows='2'"/>
                                                                <ezf:skip>
                                                                <textarea name="xxDsMultMsgDplyTxt_RN" rows="3" cols="33" ezfname="xxDsMultMsgDplyTxt_RN">aaaaa&#13;bbbbb</textarea>
                                                                </ezf:skip>
                                                            </td>
                                                        </tr>
                                                        <tr style="height: 36px;">
                                                            <td class="stab">Shipper Note</td>
                                                            <td colspan="3">
                                                                <ezf:textArea name="xxDsMultMsgDplyTxt_SN" ezfName="xxDsMultMsgDplyTxt_SN" otherAttr=" cols='33' rows='2'"/>
                                                                <ezf:skip>
                                                                <textarea name="xxDsMultMsgDplyTxt_SN" rows="3" cols="33" ezfname="xxDsMultMsgDplyTxt_SN">aaaaa&#13;bbbbb</textarea>
                                                                </ezf:skip>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </fieldset>
                                                <!-- TODO Start ADD -->
                                                <fieldset>
                                                    <legend><font color="black">PO Transmission</font></legend>
                                                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top: 5px; margin-left: 5px;  margin-bottom: 4px;">
                                                        <col width="85">
                                                        <col width="255">
                                                            <tr style="height: 24px;">
                                                                <td class="stab">Sent Date</td>
                                                                <td colspan="3">
                                                                    <ezf:inputText name="xxDtTm_HP" ezfName="xxDtTm_HP" value="05/15/2015 01:10 PM" otherAttr=" maxlength=\"10\" size=\"20\""/>
                                                                </td>
                                                            </tr>
                                                            <tr style="height: 24px;">
                                                                <td class="stab">Error Message</td>
                                                                <td colspan="3">
                                                                    <ezf:select name="poDtlIntfcErrMsgTxt_SL" ezfName="poDtlIntfcErrMsgTxt_SL" ezfCodeName="poDtlIntfcErrMsgTxt_P0" ezfDispName="poDtlIntfcErrMsgTxt_P1" otherAttr=" style=\"width:255\""/>
                                                                </td>
                                                            </tr>
                                                       </table>
                                                </fieldset>
                                                <!-- TODO End ADD -->
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </c:when>
                        </c:choose>
                        
                        <!-- ######################################## Detail - [S T A R T] #################################### -->
                        <c:choose>
                            <c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Detail'}">
                                <div id="TabContent-Detail">
                                    <script type="text/javascript">
                                        document.getElementById( "AddlHeader"   ).className = "pTab_OFF";
                                        document.getElementById( "Detail"       ).className = "pTab_ON";
                                        document.getElementById( "APInvoice"       ).className = "pTab_OFF";
                                    </script>
                                    <table border="0" cellpadding="1" cellspacing="0" width="1120" align="center">
                                        <col width="" align="left">
                                        <tr>
                                            <td valign="top">
                                                <table border="0">
                                                    <col width="165"  align="left">
                                                    <col width="70"  align="left">
                                                    <col width="90"  align="left">
                                                    <col width="70"  align="left">
                                                    <col width="70"  align="left">
                                                    <col width="65"  align="left">
                                                    <col width="70"  align="left">
                                                    <col width="50"  align="left">
                                                    <col width="40"  align="left">
                                                    <col width="40"  align="left">
                                                    <col width=""  align="right">
                                                    <tr height="20">
                                                        <td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
                                                        <td><ezf:inputButton name="Add_NewLine" value="Add(F11)" htmlClass="pBtn6" /></td>
                                                        <td class="stab"><ezf:anchor name="svcConfigMstrPk_L1" ezfName="svcConfigMstrPk_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_ConfigID" >Select from Config</ezf:anchor></td>
                                                        <td><ezf:inputText name="svcConfigMstrPk" ezfName="svcConfigMstrPk" otherAttr=" size=\"9\" maxlength=\"20\""/></td>
                                                        <td><ezf:inputButton name="AddConfig" value="Add Config" htmlClass="pBtn6" /></td>
                                                        <td class="stab">Total Amount</td>
                                                        <td><ezf:inputText name="xxTotAmt" ezfName="xxTotAmt" value="7,097.25" otherAttr=" style=\"text-align: right;\" size=\"11\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                        <td class="stab">Discount%</td>
                                                        <td><ezf:inputText name="poDtlDiscPct" ezfName="poDtlDiscPct" value="10.25" otherAttr=" style=\"text-align: right;\" size=\"5\" maxlength=\"5\" ezftoupper=\"\""/></td>
                                                        <td><ezf:inputButton name="Apply_Disc" value="Apply" htmlClass="pBtn3" /></td>
                                                        <td>
															<div align="right">
				                                                <ezf:skip>
				                                                    <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 0px;">
				                                                        <colgroup>
				                                                            <col align="center" width="54">
				                                                            <col align="right" width="40">
				                                                            <col align="center" width="16">
				                                                            <col align="right" width="40">
				                                                            <col align="center" width="16">
				                                                            <col align="right" width="40">
				                                                            <col width="10">
				                                                            <col>
				                                                            <col width="1">
				                                                            <col>
				                                                        </colgroup>
				                                                        <tbody>
				                                                            <tr>
				                                                                <td class="stab"><label>Showing</label></td>
				                                                                <td class="pOut"><label ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</label></td>
				                                                                <td class="stab"><label>to</label></td>
				                                                                <td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</label></td>
				                                                                <td class="stab"><label>of</label></td>
				                                                                <td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</label></td>
				                                                                <td>&nbsp;</td>
				                                                                <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
				                                                                <td></td>
				                                                                <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
				                                                            </tr>
				                                                        </tbody>
				                                                    </table>
				                                                </ezf:skip>
				                                                <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-left: 35px;">
				                                                    <tbody>
				                                                        <tr align="right">
				                                                            <td>
				                                                                <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
				                                                                    <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
				                                                                    <jsp:param name="TableNm"     value="A" />
				                                                                    <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
				                                                                    <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
				                                                                    <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
				                                                                </jsp:include>
				                                                            </td>
				                                                        </tr>
				                                                    </tbody>
				                                                </table>
															</div>
			                                            </td>
			                                            
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- ################################################## Add - [E N D] ################################################## -->

                                    <!-- ################################################## Detail   - [START] ################################################## -->
                                    <table border="0" cellpadding="1" cellspacing="0" width="1120" align="center">
                                        <tr>
                                            <td>
                                            <div id="parentBoxA">
                                                <div style="float:left; display:block"><!-- left table -->
                                                    <div id="leftTblHead" style="display:block;"></div>
                                                    <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                    <!-- <div id="leftTbl" style="float:left; display:block; height:255px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div> -->
                                                    <div id="leftTbl" style="float:left; display:block; height:245px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
                                                    <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                </div><!-- left table -->
                                                <div style="float:left;"><!-- right table -->
                                                    <%-- ******************************** --%>
                                                    <%-- *** Right Table Area(Header) *** --%>
                                                    <%-- ******************************** --%>
                                                    <div id="rightTblHead" style="width:1086px; height:28px; display:block; overflow:hidden; margin:0px; padding:0px;">
                                                        <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                        <!-- <table style="table-layout:fixed; height:28px; margin-right:20px;" border="1" cellpadding="1" cellspacing="0" width="2650px" id="AHEAD"> -->
                                                        <table style="table-layout:fixed; height:28px; margin-right:20px;" border="1" cellpadding="1" cellspacing="0" width="2770px" id="AHEAD">
                                                        <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                        <col width="40"  align="center">
                                                        <col width="46"  align="center">
                                                        <col width="100"  align="center">
                                                        <col width="175" align="center">
                                                        <col width="115" align="center">
                                                        <col width="220" align="center">
                                                        <col width="79" align="center">
                                                        <col width="79" align="center">
                                                        <col width="79" align="center">
                                                        <col width="79" align="center">
                                                        <col width="70" align="center">
                                                        <col width="75"  align="center">
                                                        <col width="108"  align="center">
                                                        <!-- START 2023/02/14 S.Dong [QC#60966, ADD] -->
                                                        <col width="120"  align="center">
                                                        <!-- END 2023/02/14 S.Dong [QC#60966, ADD] -->
                                                        <col width="96"  align="center">
                                                        <col width="140"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="125" align="center">
                                                        <col width="88"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="96"  align="center">
                                                        <col width="105"  align="center">
                                                        <col width="150"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="420" align="center">
                                                        <col width="125"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="88"  align="center">
                                                        <col width="108"  align="center">
                                                        <col width="108"  align="center">
                                                        <tr valign="middle">
                                                            <td id="AH0" class="pClothBs"></td>
                                                            <td id="AH1" class="pClothBs">Line#</td>
                                                            <td id="AH2" class="pClothBs">Line Type</td>
                                                            <td id="AH3" class="pClothBs">Item#</td>
                                                            <td id="AH4" class="pClothBs">Supplier Item#</td>
                                                            <td id="AH5" class="pClothBs">Item Description</td>
                                                            <td id="AH6" class="pClothBs">ASL Price</td>
                                                            <td id="AH7" class="pClothBs">Disc Price</td>
                                                            <td id="AH8" class="pClothBs">Discount%</td>
                                                            <td id="AH9" class="pClothBs">Line Price</td>
                                                            <td id="AH10" class="pClothBs">Order Qty</td>
                                                            <td id="AH11" class="pClothBs">UOM</td>
                                                            <td id="AH12" class="pClothBs">Date Needed</td>
                                                            <!-- START 2023/02/14 S.Dong [QC#60966, MOD] -->
                                                            <td id="AH13" class="pClothBs">Vendor Ship Date</td>
                                                            <td id="AH14" class="pClothBs">Dest SWH</td>
                                                            <td id="AH15" class="pClothBs">Match Opt</td>
                                                            <td id="AH16" class="pClothBs">Line Status</td>
                                                            <td id="AH17" class="pClothBs">Ext. Total</td>
                                                            <td id="AH18" class="pClothBs">Received Qty</td>
                                                            <td id="AH19" class="pClothBs">Cancel Qty</td>
                                                            <td id="AH20" class="pClothBs">Invoiced Qty</td>
                                                            <td id="AH21" class="pClothBs">Vendor WH</td>
                                                            <td id="AH22" class="pClothBs">Src WH</td>
                                                            <td id="AH23" class="pClothBs">Stock Status</td>
                                                            <td id="AH24" class="pClothBs">Serial</td>
                                                            <td id="AH25" class="pClothBs">Config ID</td>
                                                            <td id="AH26" class="pClothBs">Charge ACC</td>
                                                            <td id="AH27" class="pClothBs">Text</td>
                                                            <td id="AH28" class="pClothBs">PO Req#</td>
                                                            <td id="AH29" class="pClothBs">PO Req Line#</td>
                                                            <td id="AH30" class="pClothBs">Received WO Qty</td>
                                                            <td id="AH31" class="pClothBs">Invoiced WO Qty</td>
                                                            <!-- END 2023/02/14 S.Dong [QC#60966, MOD] -->
                                                        </tr>
                                                        </table>
                                                    </div>
                                                    <%-- ******************************** --%>
                                                    <%-- *** Right Table Area(Detail) *** --%>
                                                    <%-- ******************************** --%>
                                                    <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                    <!-- <div id="rightTbl" style="width:1103px; height:272px; display:block; overflow:scroll; margin:0px; padding:0px;"> -->
                                                    <div id="rightTbl" style="width:1103px; height:262px; display:block; overflow:scroll; margin:0px; padding:0px;">
                                                        <!-- <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="2650px" id="A"> -->
                                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="2770px" id="A">
                                                    <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                        <col width="40"  align="center">
                                                        <col width="46"  align="left">
                                                        <col width="100"  align="left">
                                                        <col width="175" align="left">
                                                        <col width="115" align="center">
                                                        <col width="220" align="center">
                                                        <col width="79" align="center">
                                                        <col width="79" align="center">
                                                        <col width="79" align="center">
                                                        <col width="79" align="center">
                                                        <col width="70" align="center">
                                                        <col width="75"  align="left">
                                                        <col width="108" align="center">
                                                        <!-- START 2023/02/14 S.Dong [QC#60966, ADD] -->
                                                        <col width="120"  align="center">
                                                        <!-- END 2023/02/14 S.Dong [QC#60966, ADD] -->
                                                        <col width="96"  align="center">
                                                        <col width="140" align="left">
                                                        <col width="88"  align="center">
                                                        <col width="125" align="right">
                                                        <col width="88"  align="right">
                                                        <col width="88"  align="right">
                                                        <col width="88"  align="right">
                                                        <col width="88"  align="center">
                                                        <col width="96"  align="center">
                                                        <col width="105"  align="center">
                                                        <col width="150" align="center">
                                                        <col width="88"  align="center">
                                                        <col width="420" align="center">
                                                        <col width="125" align="center">
                                                        <col width="88"  align="left">
                                                        <col width="88"  align="left">
                                                        <col width="108"  align="right">
                                                        <col width="108"  align="right">
                                                        <ezf:row ezfHyo="A">
                                                            <tr height="28" id="id_row{EZF_ROW_NUMBER}">
                                                                <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"chkBox{EZF_ROW_NUMBER}\""/></td>
                                                                <td><ezf:label name="dispPoDtlLineNum_A1" ezfName="dispPoDtlLineNum_A1" ezfHyo="A" /></td>
                                                                <td>
                                                                    <ezf:select name="poLineTpCd_A1" ezfName="poLineTpCd_A1" value="Goods" ezfHyo="A" ezfArrayNo="0" ezfCodeName="poLineTpCd_CD" ezfDispName="poLineTpDescTxt_NM" otherEvent1=" onchange=\"sendServerForPreferredView('OnChange_LineType', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:96\""/>
                                                                </td>
                                                                <td>
                                                                    <ezf:inputButton name="OpenWin_Mdse" value="Mdse" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                    <ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="5559B003" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="Get_MdseInfo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" style=\"margin-left: -3px\" id=\"Get_MdseInfo#{EZF_ROW_NUMBER}\""/>
                                                                </td>
                                                                <td><ezf:inputText name="aslMdseCd_A1" ezfName="aslMdseCd_A1" value="5559B003AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"12\" id=\"aslMdseCd_A1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                                <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IMAGERUNNER ADVANCED C5250" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                <td><ezf:inputText name="aslUnitPrcAmt_A1" ezfName="aslUnitPrcAmt_A1" value="9000.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                <td><ezf:inputText name="poDtlDiscPrcAmt_A1" ezfName="poDtlDiscPrcAmt_A1" value="800.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                <td ><ezf:inputText name="poDtlDiscPct_A1" ezfName="poDtlDiscPct_A1" value="10.25" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"5\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="Get_LinePriceInfo" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" style=\"margin-left: -5px\" id=\"Get_LinePriceInfo#{EZF_ROW_NUMBER}\""/>
                                                                </td>
                                                                <td><ezf:inputText name="entDealNetUnitPrcAmt_A1" ezfName="entDealNetUnitPrcAmt_A1" value="8715.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"10\" maxlength=\"30\" id=\"entDealNetUnitPrcAmt_A1#{EZF_ROW_NUMBER}\""/></td>
                                                                <td><ezf:inputText name="poDispQty_A1" ezfName="poDispQty_A1" value="2" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right; \" size=\"8\" maxlength=\"30\""/></td>
                                                                <td><ezf:label name="poDispUomCd_A1" ezfName="poDispUomCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:inputText name="rqstRcvDt_A1" ezfName="rqstRcvDt_A1" value="05/20/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/>
                                                                    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_A1', 4, '{EZF_ROW_NUMBER}');"  ezfhyo="A" /></td>
                                                                <!-- START 2023/02/14 S.Dong [QC#60966, ADD] -->
                                                                <td><ezf:inputText name="rqstShipDt_A1" ezfName="rqstShipDt_A1" value="05/20/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/>
                                                                    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt_A1', 4, '{EZF_ROW_NUMBER}');"  ezfhyo="A" /></td>
                                                                <!-- END 2023/02/14 S.Dong [QC#60966, ADD] -->
                                                                <td>
                                                                    <ezf:inputButton name="OpenWin_LocationToDetailSWH" value="SWH" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                    <ezf:inputText name="destRtlSwhCd_A1" ezfName="destRtlSwhCd_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                </td>
                                                                <td><ezf:label name="poMatchTpDescTxt_A1" ezfName="poMatchTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:inputText name="poLineStsDescTxt_A1" ezfName="poLineStsDescTxt_A1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                <td><ezf:label name="entPoDtlDealNetAmt_A1" ezfName="entPoDtlDealNetAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:inputText name="poRcvQty_A1" ezfName="poRcvQty_A1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" style=\"text-align: right; \" ezftoupper=\"\""/></td>
                                                                <td><ezf:label name="poCancQty_A1" ezfName="poCancQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:label name="poInvQty_A1" ezfName="poInvQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:label name="vndInvtyLocCd_A1" ezfName="vndInvtyLocCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td>
                                                                    <ezf:inputButton name="OpenWin_LocationFromDetailSWH" value="SWH" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                    <ezf:inputText name="srcRtlSwhCd_A1" ezfName="srcRtlSwhCd_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                </td>
                                                                <td>
                                                                     <ezf:select name="stkStsCd_A1" ezfName="stkStsCd_A1" value="AAA" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="stkStsCd_CD" ezfDispName="stkStsDescTxt_NM" otherAttr=" style=\"width:100px\""/>
                                                                </td>
                                                                <td>
                                                                    <ezf:inputText name="serNumListTxt_A1" ezfName="serNumListTxt_A1" value="123456,123457" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="OpenWin_SerEnt" value="Ser" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                                </td>
                                                                <td><ezf:label name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td>
                                                                    <ezf:inputButton name="OpenWin_AccountChrg" value="Acct" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn3" />
                                                                    <ezf:inputText name="xxScrItem130Txt_CH" ezfName="xxScrItem130Txt_CH" value="XXX.XXX.XXX.XXXX.XXXXX.XX.XX.XXX.XXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"48\" maxlength=\"50\" ezftoupper=\"\""/>
                                                                </td>
                                                                <td><ezf:inputText name="poOrdDtlCmntTxt_A1" ezfName="poOrdDtlCmntTxt_A1" value="comment" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"poOrdDtlCmntTxt_A1#{EZF_ROW_NUMBER}\""/></td>
                                                                <td><ezf:label name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:label name="prchReqLineNum_A1" ezfName="prchReqLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:label name="poRcvQty_WO" ezfName="poRcvQty_WO" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:label name="poInvQty_WO" ezfName="poInvQty_WO" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            </tr>
                                                        </ezf:row>
                                                        </table>
                                                    </div>
                                                </div><!-- right table -->
                                                </div><!-- parent box -->
                                                <script type="text/javascript" defer>
                                                    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 5);
                                                </script>
                                            </td>
                                        </tr>
                                    </table>
                        <!-- ################################################## Detail   - [START] ################################################## -->
                                    
                                    <%@ page import="business.servlet.NPAL1500.NPAL1500BMsg" %>
                                    <% NPAL1500BMsg bMsg = (NPAL1500BMsg)databean.getEZDBMsg(); %>
                                    <!-- ######################################## Footer - [START] #################################### -->
                                    <table style="margin-left: 8px;">
                                        <col width="" align="left">
                                        <col width="" align="left">
                                        <col width="" align="left">
                                        <col width="" align="left">
                                        <col width="" align="left">
                                        <col width="750" align="right">
                                        <tr>
                                            <td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
                                            <td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
                                            <td><ezf:inputButton name="MoveTo_Componet" value="Component" htmlClass="pBtn6" /></td>
                                            <td><ezf:inputCheckBox name="xxChkBox_LO" ezfName="xxChkBox_LO" value="Y" /></td>
                                            <td class="stab">Print Open Line Only</td>
                                            <td><div align="right"><ezf:inputButton name="Apply_ASL" value="Apply ASL" htmlClass="pBtn6" /></div></td>
                                        </tr>
                                    </table>
                                    <table style="margin-left: 5px;">
                                        <colgroup>
                                            <col width="570" align="left">
                                            <col width="500" align="right">
                                        </colgroup>
                                        <tr>
                                            <td>
                                                <table border="0">
                                                    <col width="">
                                                    <col width="">
                                                    <col width="">
                                                    <col width="">
                                                    <col width="">
                                                    <col width="">
                                                    <tr>
                                                        <td><ezf:inputButton name="OpenWin_History" value="History" htmlClass="pBtn6" /></td>
                                                        <td><ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn6" /></td>
                                                        <td><ezf:inputButton name="PoClose" value="Close" htmlClass="pBtn6" /></td>
                                                        <td><ezf:inputButton name="Print" value="Print" htmlClass="pBtn6" /></td>
                                                        <td>
                                                            <ezf:select name="trsmtMethTpCd" ezfName="trsmtMethTpCd" ezfBlank="1" ezfCodeName="trsmtMethTpCd_CD" ezfDispName="trsmtMethTpDescTxt_NM" otherEvent1=" onchange=\"sendServer('OnChange_TrsmtMethod')\"" otherAttr=" ezftoupper=\"\""/>
                                                        </td>
                                                        <td>
                                                        <% if (bMsg.trsmtMethTpCd.getValue().equals("00")) { %>

                                                     <% } else if (bMsg.trsmtMethTpCd.getValue().equals("05")) { %>
                                                            <ezf:inputText name="sendPoEmlAddr" ezfName="sendPoEmlAddr" otherAttr=" size=\"50\" maxlength=\"700\""/>
                                                     <% } else if (bMsg.trsmtMethTpCd.getValue().equals("06")) { %>
                                                            <ezf:inputText name="sendPoFaxNum" ezfName="sendPoFaxNum" otherAttr=" size=\"30\" maxlength=\"20\""/>
                                                     <% } else if (bMsg.trsmtMethTpCd.getValue().equals("07")) { %>
                                                            <ezf:select name="rptDestId" ezfName="rptDestId" value="AAA" ezfBlank="1" ezfCodeName="rptDestId_CD" ezfDispName="rptDestDescTxt_NM" />
                                                     <% } %>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td></td>
                                        </tr>
                                    </table>
                                    <!-- ######################################## Footer - [E N D] #################################### -->
                                </div>
                            </c:when>
                        </c:choose>
                        <!-- QC#18602 ADD START-->
                                    <!-- ######################################## AP Invoice - [S T A R T] #################################### -->
                        <c:choose>
                            <c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_APInvoice'}">
                                <div id="TabContent-APInvoice">
                                    <script type="text/javascript">
                                        document.getElementById( "AddlHeader"   ).className = "pTab_OFF";
                                        document.getElementById( "Detail"       ).className = "pTab_OFF";
                                        document.getElementById( "APInvoice"       ).className = "pTab_ON";
                                    </script>
                                    <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                                        <col width="" align="left">
                                        <tr>
                                            <td valign="top">
                                                <table border="0">
                                                    <col width=""   align="left">
                                                    <col width=""   align="left">
                                                    <col width="400"  align="left">
                                                    <col width=""   align="left">
                                                    <col width=""   align="left">
                                                    <col width="10" align="center">
                                                    <col width=""   align="left">
                                                    <col width="5"  align="left">
                                                    <col width=""   align="left">
                                                    <col width=""   align="left">
                                                    <col width=""   align="left">
                                                    <col width=""   align="left">
                                                    <col width="120"  align="left">
                                                    <col width="140"   align="left">
                                                    <col width=""  align="right">
                                                    <tr height="20">
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td></td>
                                                        <td class="stab">Total Invoice Amount</td>
                                                        <td><ezf:inputText name="xxTotAmt_AP" ezfName="xxTotAmt_AP" value="7,097.25" otherAttr=" style=\"text-align: right;\" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                        <td></td>
                                                        <td>
															<div align="right">
				                                                <ezf:skip>
				                                                    <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 0px;">
				                                                        <colgroup>
				                                                            <col align="center" width="54">
				                                                            <col align="right" width="40">
				                                                            <col align="center" width="16">
				                                                            <col align="right" width="40">
				                                                            <col align="center" width="16">
				                                                            <col align="right" width="40">
				                                                            <col width="10">
				                                                            <col>
				                                                            <col width="1">
				                                                            <col>
				                                                        </colgroup>
				                                                        <tbody>
				                                                            <tr>
				                                                                <td class="stab"><label>Showing</label></td>
				                                                                <td class="pOut"><label ezfout name="xxPageShowFromNum_B1" ezfname="xxPageShowFromNum_B1">1</label></td>
				                                                                <td class="stab"><label>to</label></td>
				                                                                <td class="pOut"><label ezfout name="xxPageShowToNum_B1" ezfname="xxPageShowToNum_B1">20</label></td>
				                                                                <td class="stab"><label>of</label></td>
				                                                                <td class="pOut"><label ezfout name="xxPageShowOfNum_B1" ezfname="xxPageShowOfNum_B1">200</label></td>
				                                                                <td>&nbsp;</td>
				                                                                <td><input onclick="tablePagenation(this.name, 'B')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrevAP"></td>
				                                                                <td></td>
				                                                                <td><input onclick="tablePagenation(this.name, 'B')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNextAP"></td>
				                                                            </tr>
				                                                        </tbody>
				                                                    </table>
				                                                </ezf:skip>
				                                                <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-left: 35px;">
				                                                    <tbody>
				                                                        <tr align="right">
				                                                            <td>
				                                                                <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
				                                                                    <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
				                                                                    <jsp:param name="TableNm"     value="B" />
				                                                                    <jsp:param name="ShowingFrom" value="xxPageShowFromNum_B1" />
				                                                                    <jsp:param name="ShowingTo"   value="xxPageShowToNum_B1" />
				                                                                    <jsp:param name="ShowingOf"   value="xxPageShowOfNum_B1" />
				                                                                </jsp:include>
				                                                            </td>
				                                                        </tr>
				                                                    </tbody>
				                                                </table>
															</div>
			                                            </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- ################################################## Add - [E N D] ################################################## -->

                                    <!-- ################################################## AP Invoice Detail   - [START] ################################################## -->
                                    <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td align="left" valign="top">
                                                <%-- ******************************* --%>
                                                <%-- *** Left Table Area(Header) *** --%>
                                                <%-- ******************************* --%>
                                                <table style="table-layout:fixed; margin-left: 5px;" border="1" cellpadding="1" cellspacing="0" height="28">
                                                    <col width="105"  align="center">
                                                    <col width="105"  align="center">
                                                    <col width= "70"  align="center">                                                    
                                                    <col width="105"  align="center">
                                                    <col width="170"  align="center">
                                                    <col width= "95"  align="center">
                                                    <col width= "95"  align="center">
                                                    <col width="125"  align="center">
                                                    <col width= "60"  align="center">
                                                    <col width= "95"  align="center">
                                                    <col width= "60"  align="center">
                                                    <tr>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'apVndInvNum_B1' )">AP Invoice#<img id="sortIMG.apVndInvNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'delyOrdNum_B1' )">Delivery Order#<img id="sortIMG.delyOrdNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'poOrdDtlLineNum_B1' )">PO Line#<img id="sortIMG.poOrdDtlLineNum_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseCd_B1' )">Item#<img id="sortIMG.mdseCd_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mdseDescShortTxt_B1' )">Item Description<img id="sortIMG.mdseDescShortTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'invDt_B1' )">Invoice Date<img id="sortIMG.invDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pmtDueDt_B1' )">Due Date<img id="sortIMG.pmtDueDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'acInvJrnlCostAmt_B1' )">Invoice Amount<img id="sortIMG.acInvJrnlCostAmt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'apBillQty_B1' )">QTY<img id="sortIMG.apBillQty_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'invAuthDt_B1' )">Auth Date<img id="sortIMG.invAuthDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'pmtHldFlg_B1' )">Hold<img id="sortIMG.pmtHldFlg_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    </tr>
                                                </table>
                                                <%-- ******************************* --%>
                                                <%-- *** Left Table Area(Detail) *** --%>
                                                <%-- ******************************* --%>
                                                <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                <!-- <div id="LeftTBL_DB" style="overflow-y:scroll; height:300; overflow-x:hidden; width:; margin-left: 5px;"> -->
                                                <div id="LeftTBL_DB" style="overflow-y:scroll; height:320; overflow-x:hidden; width:; margin-left: 5px;">
                                                <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                                    <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="B_Left">
                                                    <col width="105"  align="left">
                                                    <col width="105"  align="left">
                                                    <col width= "70"  align="left">
                                                    <col width="105"  align="left">
                                                    <col width="170"  align="left">
                                                    <col width= "95"  align="left">
                                                    <col width= "95"  align="left">
                                                    <col width="125"  align="right">
                                                    <col width= "60"  align="right">
                                                    <col width= "95"  align="left">
                                                    <col width= "60"  align="center">
                                                        <ezf:row ezfHyo="B">
                                                            <tr height="28" id="b_row_left{EZF_ROW_NUMBER}">
                                                                    <td>
                                                                        <ezf:anchor name="apVndInvNum_B1" ezfName="apVndInvNum_B1" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ApInv" otherAttr=" ezfanchortext=\"\"">
                                                                            <ezf:label name="apVndInvNum_B1" ezfName="apVndInvNum_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"cursor: pointer\""/>
                                                                        </ezf:anchor>
                                                                    </td>
                                                                    <td><ezf:label name="delyOrdNum_B1" ezfName="delyOrdNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:label name="dispPoDtlLineNum_B1" ezfName="dispPoDtlLineNum_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:label name="mdseCd_B1" ezfName="mdseCd_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:inputText name="mdseDescShortTxt_B1" ezfName="mdseDescShortTxt_B1" value="---------1---------2---------3" ezfHyo="B" ezfArrayNo="0" otherAttr=" maxlength=\"30\" size=\"22\" ezftoupper=\"\""/></td>
                                                                    <td><ezf:label name="invDt_B1" ezfName="invDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:label name="pmtDueDt_B1" ezfName="pmtDueDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:inputText name="acInvJrnlCostAmt_B1" ezfName="acInvJrnlCostAmt_B1" value="9,999,999,999.00" ezfHyo="B" ezfArrayNo="0" otherAttr=" maxlength=\"50\" size=\"16\" ezftoupper=\"\""/></td>
                                                                    <td><ezf:label name="apBillQty_B1" ezfName="apBillQty_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:label name="invAuthDt_B1" ezfName="invAuthDt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                    <td><ezf:label name="pmtHldFlg_B1" ezfName="pmtHldFlg_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                            </tr>
                                                        </ezf:row>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- ################################################## AP Invoice Detail   - [END] ################################################## -->
                                </div>
                            </c:when>
                        </c:choose>
                        <!-- QC#18602 ADD END-->
                    </div>
                </div>
                
            </td>
        </tr>
    </table>
</center>
<div style="display:none;">
    <ezf:inputButton name="btn11" value="btn11" otherAttr=" id=\"btn11\""/>
</div>
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
<!-- Application Parts End -->


<%-- **** Task specific area ends here **** --%>
