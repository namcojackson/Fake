<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180111132908 --%>
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
			<input type="hidden" name="pageID" value="NPAL1220Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="ASL Search">

<center>
    <table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
        <tbody>
            <tr>
                <td>
                    <%-- ********************** --%>
                    <%-- *** Upper Tab Area *** --%>
                    <%-- ********************** --%>
                    <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    <ezf:skip>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                            <tr>
                                <td width="1070px" height="28px" valign="bottom">
                                    <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                        <tr title="PR Entry Screen">
                                            <td width="107px" align="center" class="same">ASL Srch</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
                        <table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
                            <col width="152">
                            <col width="345">
                            <col width="110">
                            <col width="300">
                            <col width="">
                            <tr>
                                <td class="stab">Saved Search Options</td>
                                <td>
                                    <ezf:select name="srchOptPk_SL" ezfName="srchOptPk_SL" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>

                    <hr>

                    <!-- header -->
                    <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                        <col width="500"  align="left">
                        <col width="597"  align="left">
                        <tr>
                            <td valign="top">
                                <table border="0" cellpadding="0" cellspacing="0" >
                                    <col width="115" align="left">
                                    <col width=""    align="left">
                                    <col width="30"    align="center">
                                    <col width=""    align="left">
                                    <!-- 1 -->
                                    <tr height="20">
                                        <td class="stab">ASL Name(*)</td>
                                        <td colspan="3"><ezf:inputText name="aslNm" ezfName="aslNm" value="AZERTY" otherAttr=" size=\"20\" maxlength=\"64\" ezftoupper=\"\""/></td>
                                    </tr>
                                    <!-- 2 -->
                                    <tr height="20">
                                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" >Item Number(*)</ezf:anchor></TD>
                                        <td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="EPST513011" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                        <td><ezf:inputButton name="SetItemDescription" ezfName="Display_MDSEName" value=">>" /></td>
                                        <td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="EPSON, OEM, MAGENTA INK, T513011, QTY 1" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\""/></td>
                                        <td></td>
                                    </tr>
                                    <!-- 3 -->
                                    <tr height="20">
                                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier</ezf:anchor></td>
                                        <td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="9125" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                        <td><ezf:inputButton name="SetSupplierName" value=">>" /></td>
                                        <td><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="AZERTY Site01" otherAttr=" size=\"30\" maxlength=\"60\" tabindex=\"-1\""/></td>
                                    </tr>
                                    <!-- 4 -->
                                    <tr height="20">
                                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Site</ezf:anchor></td>
                                        <td><ezf:inputText name="vndCd" ezfName="vndCd" value="11111111" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                        <td><ezf:inputButton name="SetSupplierSiteName" value=">>" /></td>
                                        <td><ezf:inputText name="locNm" ezfName="locNm" value="XXXXXXX" otherAttr=" size=\"30\" maxlength=\"60\" tabindex=\"-1\""/></td>
                                    </tr>
                                </table>
                            </td>
                            <td valign="top">
                                <table border="0" cellpadding="0" cellspacing="0" >
                                    <col width="130" align="left">
                                    <col width="120" align="left">
                                    <col width="30"  align="center">
                                    <col width="100" align="left">
                                    <col width="190" align="left">
                                    <!-- 1 -->
                                    <tr height="20">
                                        <td class="stab">Supplier Item(*)</td>
                                        <td><ezf:inputText name="splyItemNum" ezfName="splyItemNum" value="161132" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                        <td></td>
                                        <td class="stab">Active Lines Only</td>
                                        <td ><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
                                    </tr>
                                    <!-- 2 -->
                                    <tr height="20">
                                        <td class="stab">Qualifier Type</TD>
                                        <td colspan="3">
                                            <ezf:select name="aslQlfyTpCd_SL" ezfName="aslQlfyTpCd_SL" ezfBlank="1" ezfCodeName="aslQlfyTpCd_PD" ezfDispName="aslQlfyTpDescTxt_PD" otherAttr=" style=\"width:160px;\""/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <!-- 3 -->
                                    <tr height="20">
                                        <td class="stab">Qualifier Reference#(*)</td>
                                        <td colspan="3"><ezf:inputText name="aslQlfyRefCd" ezfName="aslQlfyRefCd" otherAttr=" size=\"22\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                        <td></td>
                                    </tr>
                                    <!-- 4 -->
                                    <tr>
                                        <td class="stab">Merchandise Type</td>
                                        <td>
                                            <ezf:select name="coaMdseTpCd_SL" ezfName="coaMdseTpCd_SL" ezfBlank="1" ezfCodeName="coaMdseTpCd_PD" ezfDispName="coaProjDescTxt_PD" otherAttr=" style=\"width:160px;\""/>
                                        </td>
                                        <td colspan="3" style="text-align: right;">
                                            <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                                            <ezf:inputButton name="Add" value="New" htmlClass="pBtn6" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                    <hr>

                    <%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
                    <table style="margin-left: 5px; width: 1108;" >
                        <colgroup>
                            <col align="left" width="200px">
                            <col align="right" width="">
                        </colgroup>
                        <tbody>
                            <tr>
                                <td>
                                    <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                </td>
                                <td>
                                    <ezf:skip>
                                        <table cellspacing="0" cellpadding="0" border="0" style="float: right;">
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
                                                    <td class="stab"><label>to</LABEL></td>
                                                    <td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</label></td>
                                                    <td class="stab"><label>of</LABEL></td>
                                                    <td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</label></td>
                                                    <td>&nbsp;</td>
                                                    <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev" ></td>
                                                    <td></td>
                                                    <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext" ></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </ezf:skip>
                                    <table cellspacing="0" cellpadding="0" border="0" style="float: right;">
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
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>

                    <%-- ######################################## DETAIL ######################################## --%>
<div id="parentBoxA">
                    <!--<div>-->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
    <div style="float:left; display:block"> <!-- left table -->
         <div id='leftTblHead' style="display:block;">
         </div>
         <div id='leftTbl' style="float:left; display:block; height:340px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
         </div>
    </div>  <!-- left table -->
    <div style="float:left"> <!-- right table -->
        <div id='rightTblHead' style="width:1110px; display:block; overflow-y:none; overflow-x:hidden; margin:0px;padding:0px;">
                                    <!-- <div style="overflow-y:none; height:; overflow-x:none; width:1110px;"> -->
                                        <table id="AHEAD" style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="38" width="1312">
                                            <col width="100" align="center"><!-- ASL Name -->
                                            <col width="110" align="center"><!-- Item Number -->
                                            <col width="170" align="center"><!-- Item Description -->
                                            <col width="80"  align="center"><!-- Supplier Code -->
                                            <col width="100"  align="center"><!-- Supplier Name -->
                                            <col width="80"  align="center"><!-- Supplier Site Code -->
                                            <col width="100"  align="center"><!-- Supplier Site Name -->
                                            <col width="50"  align="center"><!-- Primary Supplier -->
                                            <col width="164" align="center"><!-- Supplier Item -->
                                            <col width="68"  align="center"><!-- Price -->
                                            <col width="100" align="center"><!-- Qualifier Type -->
                                            <col width="110"  align="center"><!-- Qualifier Reference# -->
                                            <col width="80" align="center"><!-- Merchandise Type -->
                                            <tr height="28">
                                                <td id="AH0" class="pClothBs">ASL Name</td>
                                                <td id="AH1" class="pClothBs">Item Number</td>
                                                <td id="AH2" class="pClothBs">Item Description</td>
                                                <td id="AH3" class="pClothBs">Supplier<br>Code</td>
                                                <td id="AH4" class="pClothBs">Supplier<br>Name</td>
                                                <td id="AH5" class="pClothBs">Supplier<br>Site Code</td>
                                                <td id="AH6" class="pClothBs">Supplier<br>Site Name</td>
                                                <td id="AH7" class="pClothBs">Primary<br>Supplier</td>
                                                <td id="AH8" class="pClothBs">Supplier Item</td>
                                                <td id="AH9" class="pClothBs">Price</td>
                                                <td id="AH10" class="pClothBs">Qualifier<br>Type</td>
                                                <td id="AH11" class="pClothBs">Qualifier<br>Reference#</td>
                                                <td id="AH12" class="pClothBs">Merchandise<br>Type</td>
                                            </tr>
                                        </table>
                                    <!-- </div>-->
        </div> <!-- rightTblHead -->
                                    <div id="rightTbl" style="overflow-x:scroll; overflow-y:scroll; height:340px; width:1127px; display:block; margin:0px; padding:0px;">
                                        <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A" width="1312">
                                            <col width="100" align="left"><!-- ASL Name -->
                                            <col width="110" align="left"><!-- Item Number -->
                                            <col width="170" align="center"><!-- Item Description -->
                                            <col width="80"  align="right"><!-- Supplier Code -->
                                            <col width="100"  align="center"><!-- Supplier Name -->
                                            <col width="80"  align="right"><!-- Supplier Site Code -->
                                            <col width="100"  align="center"><!-- Supplier Site -->
                                            <col width="50"  align="center"><!-- Primary Supplier Flag -->
                                            <col width="164" align="center"><!-- Supplier Item -->
                                            <col width="68"  align="right"><!-- Price -->
                                            <col width="100" align="left"><!-- Qualifier Type -->
                                            <col width="110"  align="left"><!-- Qualifier Reference# -->
                                            <col width="80" align="right"><!-- Merchandise Type -->
                                            <ezf:row ezfHyo="A">
                                                <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                    <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ASLEntry_ByHdr', '{EZF_ROW_NUMBER}" ><ezf:label name="aslNm_A1" ezfName="aslNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                    <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ASLEntry_ByItem', '{EZF_ROW_NUMBER}" ><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                    <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="HP Q2682" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"60\""/></td>
                                                    <td><ezf:label name="prntVndCd_A1" ezfName="prntVndCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:inputText name="prntVndNm_A1" ezfName="prntVndNm_A1" value="AZERTY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"60\""/></td>
                                                    <td><ezf:label name="vndCd_A1" ezfName="vndCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
                                                    <td><ezf:label name="primSplyFlg_A1" ezfName="primSplyFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:inputText name="splyItemNum_A1" ezfName="splyItemNum_A1" value="EPSON, OEM, MAGENTA INK, T513011, QTY 1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"60\""/></td>
                                                    <td><ezf:label name="unitPrcAmt_A1" ezfName="unitPrcAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:label name="aslQlfyTpDescTxt_A1" ezfName="aslQlfyTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:label name="aslQlfyRefCd_A1" ezfName="aslQlfyRefCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                </tr>
                                            </ezf:row>
                                            <ezf:skip>
                                                <tr height="28">
                                                    <td class="stab"><a href="#" name="rtlWhCd_H2" ezfname="rtlWhCd_H2" onclick="location.href = 'NPAL1230Scrn00.html';">ARLINGTON MAIN</label></a></td>
                                                    <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"/>0436V975</label></td>
                                                    <td><input type="text" class="pPro" size="28" maxlength="60" value="HP Q2682" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><label ezfout name="prntVndCd_A1" ezfname="prntVndCd_A1" ezfhyo="A">11111111</label></td>
                                                    <td><input type="text" class="pPro" size="9" maxlength="60" value="ARLINGTON" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><label ezfout name="vndCd_A1" ezfname="vndCd_A1" ezfhyo="A">11111111</label></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A1" ezfname="locNm_A1" ezfhyo="A"></td>
                                                    <td><label ezfout name="primSplyFlg_A1" ezfname="primSplyFlg_A1" ezfhyo="A">Y</label></td>
                                                    <td><input type="text" class="pPro" size="23" maxlength="60" value="HEQ2682" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"/>36.25</label></td>
                                                    <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"/>Customer</label></td>
                                                    <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"/>10005</label></td>
                                                    <td><label ezfout name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"/>30</label></td>
                                                </tr>
                                            </ezf:skip>
                                        </table>
                                    </div>
    </div><!-- right table -->
                                </td>
                            </tr>
                            <!--
                            <tr>
                                <td style="vertical-align:top;">
                                    
                                </td>
                            </tr>
                            -->
                        </table>
</div><!-- parentBoxA -->
                </td>
            </tr>
        </tbody>
    </table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,false,false);
</script>

<%-- **** Task specific area ends here **** --%>
