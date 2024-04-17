<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230308145745 --%>
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
        <input type="hidden" name="pageID" value="NMAL6890Scrn00">
        <!-- Set Page Name -->
        <input type="hidden" name="pageName" value="Warehouse Search">
        
        <%-- ********************** --%>
        <%-- *** Upper Tab Area *** --%>
        <%-- ********************** --%>
        <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

        <%-- ######################################## HEADER ######################################## --%>
        <center>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <ezf:skip>
                        <div class="pTab_HEAD">
                            <ul>
                                <li class="pTab_ON"><a href="./NMAL6890Scrn00.html">WH Search</a></li>
                            </ul>
                        </div>

                        <!-- include S21BusinessProcessTAB -->
                        <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
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
                                    <ezf:select name="srchOptPk_SS" ezfName="srchOptPk_SS" ezfBlank="1" ezfCodeName="srchOptPk_SC" ezfDispName="srchOptNm_SC" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\" style=\"width:225px\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>
                            <hr>
                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px"border="0">
                                <tr>
                                    <col width="390"  align="left">
                                    <col width="430"  align="left">
                                    <col width="293"  align="left">
                                </tr>
                                <tr>
                                    <td valign="top">
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <col width="180" align="left">
                                                <col width="180" align="left">
                                                <col width="20"  align="center">
                                            </tr>
                                            <!-- 1 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse Name (*)</td>
                                                <td colspan="3"><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="NORCROSS WH - CSA" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 2 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse Description (*)</td>
                                                <td colspan="3"><ezf:inputText name="rtlWhDescTxt" ezfName="rtlWhDescTxt" value="NORCROSS WH - CSA" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 3 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse Code (*)</td>
                                                <td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="1Z1" otherAttr=" size=\"31\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <!-- 4 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse Type</td>
                                                <td colspan="3">
                                                    <ezf:select name="rtlWhCatgCd_RS" ezfName="rtlWhCatgCd_RS" ezfBlank="1" ezfCodeName="rtlWhCatgCd_RC" ezfDispName="rtlWhCatgDescTxt_RC" otherAttr=" style=\"width:223px;\""/>
                                                </td>
                                            </tr>
                                            <!-- 5 -->
                                            <tr height="20">
                                                <td class="stab"><ezf:anchor name="fullPsnNm_P1" ezfName="fullPsnNm_P1" ezfEmulateBtn="1" ezfGuard="OpenWin_Owner" >Owner (*)</ezf:anchor></td>
                                                <td colspan="3">
                                                    <ezf:inputText name="fullPsnNm_O1" ezfName="fullPsnNm_O1" otherAttr=" size=\"31\" maxlength=\"60\""/>
                                                </td>
                                            </tr>
                                            <!-- 6 -->
                                            <tr height="20">
                                                <td class="stab"><ezf:anchor name="fullPsnNm_P2" ezfName="fullPsnNm_P2" ezfEmulateBtn="1" ezfGuard="OpenWin_AltOwner" >Alternate Owner (*)</ezf:anchor></td>
                                                <td colspan="3">
                                                    <ezf:inputText name="fullPsnNm_O2" ezfName="fullPsnNm_O2" otherAttr=" size=\"31\" maxlength=\"60\""/>
                                                </td>
                                            </tr>
                                            <!-- 7 -->
                                            <tr height="20">
                                                <td class="stab">Inventory Account</td>
                                                <td colspan="3">
                                                    <ezf:select name="invtyAcctCd_IS" ezfName="invtyAcctCd_IS" ezfBlank="1" ezfCodeName="invtyAcctCd_IC" ezfDispName="invtyAcctDescTxt_IC" otherAttr=" style=\"width:223px;\""/>
                                                </td>
                                            </tr>
                                            <!-- 8 -->
                                            <tr height="20">
                                                <td class="stab">Ship To Location Name (*)</td>
                                                <td colspan="3"><ezf:inputText name="shipToLocNm" ezfName="shipToLocNm" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 9 -->
                                            <tr height="20">
                                                <td class="stab">Return Location Name (*)</td>
                                                <td colspan="3"><ezf:inputText name="rtrnToLocNm" ezfName="rtrnToLocNm" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 10 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse Phone Number (*)</td>
                                                <td colspan="3"><ezf:inputText name="telNum" ezfName="telNum" otherAttr=" size=\"31\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td valign="top">
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <col width="185" align="left">
                                            </tr>
                                            <!-- 11 -->
                                            <tr height="20">
                                                <td class="stab">Default Source Type</td>
                                                <td>
                                                    <ezf:select name="procrTpCd_SS" ezfName="procrTpCd_SS" ezfBlank="1" ezfCodeName="procrTpCd_SC" ezfDispName="procrTpDescTxt_SC" otherAttr=" style=\"width:223px;\""/>
                                                </td>
                                            </tr>
                                            <!-- 12 -->
                                            <tr height="20">
                                                <td class="stab">Source Warehouse Name (*)</td>
                                                <td><ezf:inputText name="rtlWhNm_H2" ezfName="rtlWhNm_H2" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 13 -->
                                            <tr height="20">
                                                <td class="stab">Default Return Type</td>
                                                <td>
                                                    <ezf:select name="procrTpCd_RS" ezfName="procrTpCd_RS" ezfBlank="1" ezfCodeName="procrTpCd_RC" ezfDispName="procrTpDescTxt_RC" otherAttr=" style=\"width:223px;\""/>
                                                </td>
                                            </tr>
                                            <!-- 14 -->
                                            <tr height="20">
                                                <td class="stab">Return To Warehouse Name (*)</td>
                                                <td><ezf:inputText name="rtlWhNm_H3" ezfName="rtlWhNm_H3" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 15 -->
                                            <tr height="20">
                                                <td class="stab">Tech Reference Satellite</td>
                                                <td>
                                                    <ezf:select name="procrTpCd_ES" ezfName="procrTpCd_ES" ezfBlank="1" ezfCodeName="procrTpCd_EC" ezfDispName="procrTpDescTxt_EC" otherAttr=" style=\"width:223px;\""/>
                                                </td>
                                            </tr>
                                            <!-- 16 -->
                                            <tr height="20">
                                                <td class="stab">Tech Reference Satellite Name (*)</td>
                                                <td><ezf:inputText name="rtlWhNm_H4" ezfName="rtlWhNm_H4" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                            <!-- 17 -->
                                            <tr height="20">
                                                <td class="stab">Allow MIN-MAX Parts For Insourcing</td>
                                                <td>
                                                    <ezf:select name="plnItemInsrcCd_MS" ezfName="plnItemInsrcCd_MS" ezfBlank="1" ezfCodeName="plnItemInsrcCd_MC" ezfDispName="plnItemInsrcDescTxt_MC" otherAttr=" style=\"width:223px;\""/>
                                                </td>
                                            </tr>
                                             <tr height="20">
                                                <td class="stab">City (*)</td>
                                                <td><ezf:inputText name="ctyAddr" ezfName="ctyAddr" otherAttr=" size=\"31\" maxlength=\"60\""/></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td valign="top">
                                        <table border="0" cellpadding="0" cellspacing="0" >
                                            <tr>
                                                <col width="115" align="left">
                                                <col width="40" align="left">
                                                <col width="30" align="left">
                                            </tr>
                                            <!-- 18 -->
                                            <tr height="20">
                                                <td class="stab">Trading Partner ID (*)</td>
                                                <td colspan="2"><ezf:inputText name="vndLocCd" ezfName="vndLocCd" otherAttr=" size=\"20\" maxlength=\"90\""/></td>
                                            </tr>
                                            <!-- 19 -->
                                            <tr height="20">
                                                <td class="stab">Inventory Ownership</td>
                                                <td colspan="2">
                                                    <ezf:select name="invtyOwnrCd_OS" ezfName="invtyOwnrCd_OS" ezfBlank="1" ezfCodeName="invtyOwnrCd_OC" ezfDispName="invtyOwnrDescTxt_OC" otherAttr=" style=\"width:145px;\""/>
                                                </td>
                                            </tr>
                                            <!-- 20 -->
                                            <tr height="20">
                                                <td class="stab">Space (*)</td>
                                                <td colspan="2"><ezf:inputText name="firstRefCmntTxt" ezfName="firstRefCmntTxt" otherAttr=" size=\"20\" maxlength=\"90\""/></td>
                                            </tr>
                                            <!-- 21 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse Start Date</td>
                                                <td class="stab" colspan="2"><ezf:inputText name="effFromDt" ezfName="effFromDt" value="01/01/2015" otherAttr=" size=\"12\" maxlength=\"90\" ezftoupper=\"\""/>
                                                    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);"/></td>
                                            </tr>
                                            <!-- 22 -->
                                            <tr height="20">
                                                <td class="stab">Warehouse End Date</td>
                                                <td class="stab" colspan="2"><ezf:inputText name="effThruDt" ezfName="effThruDt" value="12/31/9999" otherAttr=" size=\"12\" maxlength=\"90\" ezftoupper=\"\""/>
                                                    <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);"/></td>
                                            </tr>
                                            <!-- START 2023/03/07 S.Dong [QC#61205, MOD] -->
                                            <!-- 23 -->
                                            <tr height="20">
                                                <td class="stab">WH Operation</td>
                                                <td colspan="2">
                                                    <ezf:select name="whOwnrCd_H1" ezfName="whOwnrCd_H1" ezfBlank="1" ezfCodeName="whOwnrCd_L1" ezfDispName="whOwnrNm_L1" otherAttr=" style=\"width:145px;\""/>
                                                </td>
                                            </tr>
                                            <!-- END 2023/03/07 S.Dong [QC#61205, MOD] -->
                                            <tr height="20">
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr height="20">
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr height="20">
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <!-- START 2023/03/07 S.Dong [QC#61205, MOD] -->
                                            <!-- 24 -->
                                            <!-- END 2023/03/07 S.Dong [QC#61205, MOD] -->
                                            <tr height="20">
                                                <td class="stab">Show Sub WH Level</td>
                                                <td><ezf:inputCheckBox name="rtlSwhCd" ezfName="rtlSwhCd" value="Y" /></td>
                                                <td align="right">
                                                    <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
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
                                                <col width="54"  align="center">
                                                <col width="40"  align="right">
                                                <col width="16"  align="center">
                                                <col width="40"  align="right">
                                                <col width="26"  align="right">
                                                <col width="10">
                                                <col>
                                                <col width="20">
                                                <col>
                                                <col width="1">
                                                <col>
                                            </colgroup>
                                            <tbody>
                                                <tr>
                                                        <td class="stab">Showing</td>
                                                        <td><input type="text" name="xxPageShowCurNum" value="1" size="4" maxlength="4" style="text-align:right" id="txtShowingCur" class="pPro" readOnly ezfname="xxPageShowCurNum"></td>
                                                        <td class="stab">/</td>
                                                        <td><input type="text" name="xxPageShowTotNum" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly ezfname="xxPageShowTotNum"></td>
                                                        <td class="stab">page</td>
                                                        <td>&nbsp;</td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" id="btnJump" class="pBtn3" type="button" value="Jump" name="PageJump"></td>
                                                        <td></td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                        <td></td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        </ezf:skip>
                                        <table cellspacing="0" cellpadding="0" border="0" style="float: right;">
                                            <tbody>
                                                <tr align="right">
                                                    <td>
                                                        <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                                            <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                                            <jsp:param name="TableNm"     value="A" />
                                                            <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
                                                            <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
                                                            <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
                                                            <jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
                                                            <jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
                                                            <jsp:param name="ViewMode"       value="FULL" />
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
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
                            <col valign="top">
                            <tr>
                                <td>
                                    <div id="parentBoxA">
                                        <!-- <div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:1106;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('btmTBL'));"> -->
                                        <div style="float:left; display:block"> <!-- left table -->
                                            <div id='leftTblHead' style="display:block;">
                                            </div>
                                            <div id='leftTbl' style="float:left; display:block; height:211px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
                                            </div>
                                        </div>  <!-- left table -->
                                        <div style="float:left"> <!-- right table -->
                                            <div id='rightTblHead' style="width:1108px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" style="margin-right:20px" width="1105px">
                                                    <col width="70"   align="center">
                                                    <col width="230"  align="center">
                                                    <col width="230"  align="center">
                                                    <col width="130"  align="center">
                                                    <col width="120"  align="center">
                                                    <col width="120"  align="center">
                                                    <col width="120"  align="center">
                                                    <col width="140"  align="center">
                                                    <col width="140"  align="center">
                                                    <col width="80"   align="center">
                                                    <col width="120"  align="center">
                                                    <col width="80"   align="center">
                                                    <col width="120"  align="center">
                                                    <col width="100"   align="center">
                                                    <col width="100"  align="center">
                                                    <col width="100"  align="center">
                                                    <col width="120"  align="center">
                                                    <col width="80"   align="center">
                                                    <col width="80"   align="center">
                                                    <col width="80"   align="center">
                                                    <col width="80"   align="center">
                                                    <col width="80"   align="center">
                                                    <col width="80"   align="center">
                                                    <tr height="35px">
                                                        <td id="AH0" class="pClothBs colFix">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhCd_A' )">Warehouse<br/>Code
                                                                <img id="sortIMG.rtlWhCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH1" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Warehouse Name
                                                                <img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH2" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhDescTxt_A' )">Warehouse Description
                                                                <img id="sortIMG.rtlWhDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH3" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhCatgDescTxt_A' )">Warehouse Type
                                                                <img id="sortIMG.rtlWhCatgDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH4" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'fullPsnNm_AO' )">Owner
                                                                <img id="sortIMG.fullPsnNm_AO" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH5" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'fullPsnNm_AA' )">Alternate Owner
                                                                <img id="sortIMG.fullPsnNm_AA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH6" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyAcctDescTxt_A' )">Inventory Account
                                                                <img id="sortIMG.invtyAcctDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH7" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToLocNm_A' )">Ship To Location
                                                                <img id="sortIMG.shipToLocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH8" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtrnToLocNm_A' )">Return Location
                                                                <img id="sortIMG.rtrnToLocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH9" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'procrTpDescTxt_AD' )">Default<br/>Source Type
                                                                <img id="sortIMG.procrTpDescTxt_AD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH10" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A2' )">Source Warehouse
                                                                <img id="sortIMG.rtlWhNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH11" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'procrTpDescTxt_AR' )">Default<br/>Return Type
                                                                <img id="sortIMG.procrTpDescTxt_AR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH12" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A3' )">Return Warehouse
                                                                <img id="sortIMG.rtlWhNm_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH13" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'procrTpDescTxt_AE' )">Tech Reference Satellite
                                                                <img id="sortIMG.procrTpDescTxt_AE" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH14" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A4' )">Tech Reference <br/>Satellite Name
                                                                <img id="sortIMG.rtlWhNm_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH15" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'plnItemInsrcDescTxt_A' )">Allow Min-Max for<br/>Insourcing
                                                                <img id="sortIMG.plnItemInsrcDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH22" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'ctyAddr_A' )">City
                                                                <img id="sortIMG.ctyAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH16" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'vndLocCd_A' )">Trading<br/>Partner ID
                                                                <img id="sortIMG.vndLocCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH17" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyOwnrDescTxt_A' )">Inventory<br/>Ownership
                                                                <img id="sortIMG.invtyOwnrDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH18" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'firstRefCmntTxt_A' )">Space
                                                                <img id="sortIMG.firstRefCmntTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH19" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">WH Start Date
                                                                <img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH20" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">WH End Date
                                                                <img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                        <td id="AH21" class="pClothBs">
                                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhCd_A' )">Sub WH Code
                                                                <img id="sortIMG.rtlSwhCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <!-- <div id="btmTBL" style="overflow-y:scroll; height:200; overflow-x:scroll; width:1125;" onScroll="onScroll=synchroScrollLeft(this.id, new Array('topTBL'));"> -->
                                            <div id="rightTbl" style="width:1125px; height:228px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A" width="1106px" >
                                                    <col width="70"   align="left">
                                                    <col width="230"  align="left">
                                                    <col width="230"  align="left">
                                                    <col width="130"  align="left">
                                                    <col width="120"  align="left">
                                                    <col width="120"  align="left">
                                                    <col width="120"  align="left">
                                                    <col width="140"  align="left">
                                                    <col width="140"  align="left">
                                                    <col width="80"   align="left">
                                                    <col width="120"  align="left">
                                                    <col width="80"   align="left">
                                                    <col width="120"  align="left">
                                                    <col width="100"   align="left">
                                                    <col width="100"  align="left">
                                                    <col width="100"  align="left">
                                                    <col width="120"  align="left">
                                                    <col width="80"   align="left">
                                                    <col width="80"   align="left">
                                                    <col width="80"   align="left">
                                                    <col width="80"   align="left">
                                                    <col width="80"   align="left">
                                                    <col width="80"   align="left">
                                                    <ezf:row ezfHyo="A">
                                                        <tr id="id_row${EZF_ROW_NUMBER}" height="23px">
                                                            <td>
                                                                <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_WHSetup', '{EZF_ROW_NUMBER}" ><ezf:label name="rtlWhCd_A" ezfName="rtlWhCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="NORCROSS WH - CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlWhDescTxt_A" ezfName="rtlWhDescTxt_A" value="NORCROSS WH - CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"32\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlWhCatgDescTxt_A" ezfName="rtlWhCatgDescTxt_A" value="Equipment" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="fullPsnNm_AO" ezfName="fullPsnNm_AO" value="ED PETNER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="fullPsnNm_AA" ezfName="fullPsnNm_AA" value="ED PETNER" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="invtyAcctDescTxt_A" ezfName="invtyAcctDescTxt_A" value="Inventory" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="shipToLocNm_A" ezfName="shipToLocNm_A" value="MONROE DOCK NJ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtrnToLocNm_A" ezfName="rtrnToLocNm_A" value="MONROE DOCK NJ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="procrTpDescTxt_AD" ezfName="procrTpDescTxt_AD" value="Supplier" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlWhNm_A2" ezfName="rtlWhNm_A2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="procrTpDescTxt_AR" ezfName="procrTpDescTxt_AR" value="Supplier" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlWhNm_A3" ezfName="rtlWhNm_A3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="procrTpDescTxt_AE" ezfName="procrTpDescTxt_AE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlWhNm_A4" ezfName="rtlWhNm_A4" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="plnItemInsrcDescTxt_A" ezfName="plnItemInsrcDescTxt_A" value="Skip" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="ctyAddr_A" ezfName="ctyAddr_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="vndLocCd_A" ezfName="vndLocCd_A" value="aaa" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="invtyOwnrDescTxt_A" ezfName="invtyOwnrDescTxt_A" value="CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="firstRefCmntTxt_A" ezfName="firstRefCmntTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="01/01/2000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="01/01/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                            <td>
                                                                <ezf:inputText name="rtlSwhCd_A" ezfName="rtlSwhCd_A" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"60\""/>
                                                            </td>
                                                        </tr>
                                                    </ezf:row>
                                                </table>
                                            </div><!-- rightTbl -->
                                        </div><!-- right table -->
                                    </div> <!-- parent box -->
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </center>
            
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
</script>

<%-- **** Task specific area ends here **** --%>
