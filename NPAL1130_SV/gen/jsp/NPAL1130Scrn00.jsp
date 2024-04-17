<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230508162739 --%>
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
    <input type="hidden" name="pageID" value="NPAL1130Scrn00">
    <!-- Set Page Name -->
    <input type="hidden" name="pageName" value="Supply / Demand">

    <center>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td>
                <!-- ######################################## HEADER ######################################## -->
                <!-- =============== TAB HEADER of Rplsh Inq DI  =============== -->
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <li class="pTab_ON">
                                <a>Rplsh Inq DI</a>
                        </li>
                    </ul>
                </div>
                </ezf:skip>
                <!-- =============== TAB BODY of Rplsh Inq DI  =============== -->
                <div class="pTab_BODY">
                <table width="100%">
                    <col valign="top">
                    <tr>
                        <td>
                            <table width="98%" align="center">
                                <tr>
                                    <td>
                                        <!-- ##### Search Criteria [START] ##### -->
                                        <table height="20">
                                            <tr>
                                                <td class="stab" width="80"><ezf:anchor name="xxLinkAncr_H1" ezfName="xxLinkAncr_H1" ezfEmulateBtn="1" ezfGuard="OpenWinMdse" >Item Number</ezf:anchor></td>
                                                <td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="6067B001" otherAttr=" size=\"12\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                <td><ezf:inputButton name="SearchMdseName" value=">>" htmlClass="pBtn0" /></td>
                                                <td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="---------1---------2---------3" otherAttr=" size=\"26\" maxlength=\"250\""/></td>
                                                <td>&nbsp;</td>
                                                <td class="stab"><ezf:anchor name="xxLinkAncr_H2" ezfName="xxLinkAncr_H2" ezfEmulateBtn="1" ezfGuard="OpenWinLocation" >WH Code</ezf:anchor></td>
                                                <td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="1Z1" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                <td><ezf:inputButton name="SearchLocationName" value=">>" htmlClass="pBtn0" /></td>
                                                <td><ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="---------1---------2---------3" otherAttr=" size=\"20\""/></td>
                                                <td>&nbsp;</td>
                                                <td class="stab"><ezf:anchor name="xxLinkAncr_H2" ezfName="xxLinkAncr_H2" ezfEmulateBtn="1" ezfGuard="OpenWinLocation" >Sub WH Code</ezf:anchor></td>
                                                <td><ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="------" otherAttr=" size=\"8\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                <td width="20"></td>
                                                <td><ezf:inputButton name="Search" ezfName="Search" value="Search" htmlClass="pBtn6" /></td>
                                            </tr>
                                        </table>
                                        <!-- ##### Search Criteria [END] ##### -->
                                        <!-- ##### Header [START] ##### -->
                                        <hr>
                                        <table height="20">
                                            <tr>
                                                <td>
                                                    <table border="0" cellpadding="0" cellspacing="0">
                                                        <col width="70">
                                                        <col width="200">
                                                        <col width="20">
                                                        <col width="70">
                                                        <col width="80">
                                                        <col width="20">
                                                        <col width="70">
                                                        <col width="80">

                                                        <tr height="10">
                                                            <td class="stab" width="70">Plan Name</td>
                                                            <td><ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="XXXXXXXXXXXXXXXXXXXX" otherAttr=" size=\"30\""/></td>
                                                            <td></td>
                                                            <td class="stab" width="70">Min Qty</td>
                                                            <td><ezf:inputText name="ropQty" ezfName="ropQty" value="1,234,567,890" otherAttr=" size=\"13\""/></td>
                                                            <td></td>
                                                            <td class="stab" width="50">Max Qty</td>
                                                            <td><ezf:inputText name="maxOrdQty" ezfName="maxOrdQty" value="1,234,567,890" otherAttr=" size=\"13\""/></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table border="0" cellpadding="0" cellspacing="0">
                                                        <col width="70">
                                                        <col width="200">
                                                        <col width="20">
                                                        <col width="70">
                                                        <col width="70">
                                                        <col width="5">
                                                        <col width="200">
                                                        <col width="20">
                                                        <col width="150">
                                                        <col width="40">

                                                        <tr height="10">
                                                            <td class="stab" width="70">Source Type</td>
                                                            <td><ezf:inputText name="procrTpDescTxt" ezfName="procrTpDescTxt" value="Warehouse" otherAttr=" size=\"30\""/></td>
                                                            <td></td>
                                                            <td class="stab" width="70">Source WH</td>
                                                            <td><ezf:inputText name="srcRtlWhCd_S1" ezfName="srcRtlWhCd_S1" value="1Z1" otherAttr=" size=\"10\""/></td>
                                                            <td></td>
                                                            <td><ezf:inputText name="rtlWhNm_S1" ezfName="rtlWhNm_S1" value="MONROE WAREHOUSE - CSA, MONROE " otherAttr=" size=\"30\""/></td>
                                                            <td></td>
                                                            <td class="stab" width="160">Include Entered Sales Order</td>
                                                            <td><ezf:inputCheckBox name="xxChkBox_CO" ezfName="xxChkBox_CO" value="Y" onClick="sendServer('OnChange_DplyConfOrdOnly')" /></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <hr>
                                        <!-- ##### Header [END] ##### -->
                                        <!-- ##### Search Result [START] ##### -->
                                        <table style="width: 100%;" >
                                            <colgroup>
                                                <col align="left" width="">
                                                <col align="right" width="">
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <!-- ##### Preferred View [START] ##### -->
                                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                                        <ezf:skip>
                                                        <select name="xxViewSet_SE" ezfname="xxViewSet_SE" ezflist="xxViewSet_CD,xxViewSet_DI,99, ,noblank">
                                                            <option>Default</option>
                                                            <option>----+----1</option>
                                                        </select>
                                                        </ezf:skip>
                                                        <!-- ##### Preferred View [END] ##### -->
                                                    </td>
                                                    <td>
                                                        <!-- ##### Pagination [START] ##### -->
                                                        <ezf:skip>
                                                        <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 21px;">
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
                                                        <table cellSpacing="0" cellPadding="0" border="0" style="float: right;">
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
                                                        <!-- ##### Pagination [END] ##### -->
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- =============== TABLE AREA =============== -->
                                        <div id="parentBoxA">
                                            <!-- =============== TABLE HEADER =============== -->
                                            <table border="0" cellpadding="0" cellspacing="0" style="margin-left:4px; margin-right:4px;">
                                                <tr>
                                                    <td valign="top">
                                                        <div style="float:left; display:block"> <!-- left table -->
                                                              <div id='leftTblHead' style="display:block;">
                                                              </div>
                                                              <div id='leftTbl' style="float:left; display:block; height:400px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
                                                              </div>
                                                        </div>  <!-- left table -->
                                                    </td>
                                                    <td valign="top">
                                                        <div style="float:left"> <!-- right table -->
                                                            <div id='rightTblHead' style="width:1080px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                                                <!-- ******************************** -->
                                                                <!-- *** Right Table Area(Header) *** -->
                                                                <!-- ******************************** -->
                                                                <table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1370" style="margin-right:20px" >
                                                                    <col width="120px" align="center"><!-- Item Number -->
                                                                    <col width="100px" align="center"><!-- Location -->
                                                                    <col width="100px" align="center"><!-- ETA or ETD Date -->
                                                                    <col width="100px" align="center"><!-- Inbd/Outbd/Stock -->
                                                                    <col width="150px" align="center"><!-- Trx Type -->
                                                                    <col width="90"  align="center"><!-- Order# -->
                                                                    <col width="70px" align="center"><!-- Line# -->
                                                                    <col width="150px"  align="center"><!-- Order Type -->
                                                                    <col width="150px"  align="center"><!-- Order Header Status -->
                                                                    <col width="150px"  align="center"><!-- Allocation Status -->
                                                                    <col width="150px"  align="center"><!-- Approval Status -->
                                                                    <col width="100px" align="center"><!-- Order Qty -->
                                                                    <col width="100px" align="center"><!-- Available Qty -->
                                                                    <col width="40px" align="center"><!-- Supersede -->
                                                                    <col width="100px"  align="center"><!-- Request Date -->
                                                                    <col width="100px"  align="center"><!-- Need By Date -->
                                                                    <col width="100px" align="center"><!-- Plannning Note -->
                                                                    <tr height="28">
                                                                        <td id="AH0" class="pClothBs">Item Number</td>
                                                                        <td id="AH1" class="pClothBs">Location</td>
                                                                        <td id="AH2" class="pClothBs">ETA or ETD Date</td>
                                                                        <td id="AH3" class="pClothBs">Inbd/Outbd/Stock</td>
                                                                        <td id="AH4" class="pClothBs">Trx Type</td>
                                                                        <td id="AH5" class="pClothBs">Order#</td>
                                                                        <td id="AH6" class="pClothBs">Line#</td>
                                                                        <td id="AH7" class="pClothBs">Order Type</td>
                                                                        <td id="AH8" class="pClothBs">Order Header Status</td>
                                                                        <td id="AH9" class="pClothBs">Allocation Status</td>
                                                                        <td id="AH10" class="pClothBs">Approval Status</td>
                                                                        <td id="AH11" class="pClothBs">Order Qty</td>
                                                                        <td id="AH12" class="pClothBs">Available Qty</td>
                                                                        <td id="AH13" class="pClothBs">Sup</td>
                                                                        <td id="AH14" class="pClothBs">Ord Crt Date</td>
                                                                        <td id="AH15" class="pClothBs">Need By Date</td>
                                                                        <td id="AH16" class="pClothBs">Plannning Note</td>
                                                                    </tr>
                                                                </table>
                                                            </div>
                                                            <!-- ******************************** -->
                                                            <!-- *** Right Table Area(Detail) *** -->
                                                            <!-- ******************************** -->
                                                            <div id="rightTbl" style="width:1097px; height:400px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                                <table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1220" >
                                                                    <col width="120px" align="left"><!-- Item Number -->
                                                                    <col width="100px" align="left"><!-- Loacation -->
                                                                    <col width="100px" align="left"><!-- ETA or ETD Date -->
                                                                    <col width="100px" align="left"><!-- Inbd/Outbd/Stock -->
                                                                    <col width="150px" align="center"><!-- Trx Type -->
                                                                    <col width="90"  align="center"><!-- Order# -->
                                                                    <col width="70px" align="center"><!-- Line# -->
                                                                    <col width="150px"  align="center"><!-- Order Type -->
                                                                    <col width="150px"  align="center"><!-- Order Header Status -->
                                                                    <col width="150px"  align="center"><!-- Allocation Status -->
                                                                    <col width="150px"  align="center"><!-- Approval Status -->
                                                                    <col width="100px" align="right"><!-- Order Qty -->
                                                                    <col width="100px" align="right"><!-- Available Qty -->
                                                                    <col width="40px" align="center"><!-- Supersede -->
                                                                    <col width="100px"  align="center"><!-- Request Date -->
                                                                    <col width="100px"  align="center"><!-- Need By Date -->
                                                                    <col width="100px" align="center"><!-- Attachments -->
                                                                    <ezf:row ezfHyo="A">
                                                                        <tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
                                                                            <!-- Item Number -->
                                                                            <td><ezf:label name="mdseCd_A0" ezfName="mdseCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Sub WH -->
                                                                            <td><ezf:label name="invtyLocCd_A0" ezfName="invtyLocCd_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- ETA or ETD Date -->
                                                                            <td><ezf:label name="etaEtdDt_A0" ezfName="etaEtdDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Inbd/Outbd/Stock -->
                                                                            <td><ezf:label name="inbdOtbdNm_A0" ezfName="inbdOtbdNm_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Trx Type -->
                                                                            <td><ezf:inputText name="arTrxTpNm_A0" ezfName="arTrxTpNm_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\""/></td>
                                                                            <!-- Order# -->
                                                                            <td><ezf:inputText name="origOrdNum_A0" ezfName="origOrdNum_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\""/></td>
                                                                            <!-- Line# -->
                                                                            <td><ezf:inputText name="xxDplyOrdLineNum_A0" ezfName="xxDplyOrdLineNum_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\""/></td>
                                                                            <!-- Order Type -->
                                                                            <td><ezf:inputText name="ordTpDescTxt_A0" ezfName="ordTpDescTxt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
                                                                            <!-- Order Header Status -->
                                                                            <td><ezf:inputText name="ordHdrDplyStsDescTxt_A0" ezfName="ordHdrDplyStsDescTxt_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\""/></td>
                                                                            <!-- Allocation Status -->
                                                                            <td><ezf:inputText name="shpgStsNm_A0" ezfName="shpgStsNm_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\""/></td>
                                                                            <!-- Approval Status -->
                                                                            <td><ezf:inputText name="apvlStsNm_A0" ezfName="apvlStsNm_A0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\""/></td>
                                                                            <!-- Order Qty -->
                                                                            <td><ezf:label name="ordQty_A0" ezfName="ordQty_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Available Qty -->
                                                                            <td><ezf:label name="avalQty_A0" ezfName="avalQty_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Supersede -->
                                                                            <td><ezf:anchor name="xxAncrCtrlFlg_A0" ezfName="xxAncrCtrlFlg_A0" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SupdInvtySearch" otherAttr=" tabindex=\"-1\" id=\"OpenWin_SupdInvtySearch{EZF_ROW_NUMBER}\""><ezf:label name="supdFlg_A0" ezfName="supdFlg_A0" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                                            <!-- Request Date -->
                                                                            <td><ezf:label name="ordDt_A0" ezfName="ordDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Need By Date -->
                                                                            <td><ezf:label name="rqstRcvDt_A0" ezfName="rqstRcvDt_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                            <!-- Attachments -->
                                                                            <td><ezf:inputButton name="OpenWin_Attachments" value="Attachments" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" />
                                                                            <td class="pAuditInfo">
                                                                                <ezf:inputHidden name="xxRecHistCratTs_A0" ezfName="xxRecHistCratTs_A0" ezfHyo="A" ezfArrayNo="0" />
                                                                                <ezf:inputHidden name="xxRecHistCratByNm_A0" ezfName="xxRecHistCratByNm_A0" ezfHyo="A" ezfArrayNo="0" />
                                                                                <ezf:inputHidden name="xxRecHistUpdTs_A0" ezfName="xxRecHistUpdTs_A0" ezfHyo="A" ezfArrayNo="0" />
                                                                                <ezf:inputHidden name="xxRecHistUpdByNm_A0" ezfName="xxRecHistUpdByNm_A0" ezfHyo="A" ezfArrayNo="0" />
                                                                                <ezf:inputHidden name="xxRecHistTblNm_A0" ezfName="xxRecHistTblNm_A0" ezfHyo="A" ezfArrayNo="0" />
                                                                            </td>
                                                                        </tr>
                                                                    </ezf:row>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div><!-- parentBoxA -->
                                        <script type="text/javascript" defer>
                                            S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
                                        </script>
                                   </td>
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


<%-- **** Task specific area ends here **** --%>
