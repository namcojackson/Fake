<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170302175126 --%>
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
            <input type="hidden" name="pageID" value="NMAL6820Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="WH Setup Screen">

            <%-- ######################################## HEADER ######################################## --%>
            <center>

                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <!-- include S21BusinessProcessTAB -->
                            <div class="pTab_HEAD">
                                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                                <ezf:skip>
                                    <ul>
                                        <li class="pTab_ON">
                                                <a>WH Setup</a>
                                        </li>
                                    </ul>
                                </ezf:skip>
                            </div>

                            <div class="pTab_BODY">

                                <%-- ###TAB - HEAD --%>
                                <table  style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
                                    <tr>
                                        <col width="360"  align="left">
                                        <col width="5"  align="left">
                                        <col width="265"  align="left">
                                        <col width="5"  align="left">
                                        <col width="240"  align="left">
                                        <col width="5"  align="left">
                                        <col width="330"  align="left">
                                    </tr>
                                    <tr>
                                        <td>
                                            <table border="0">
                                                <tr>
                                                    <col width="110" align="left">
                                                    <col width="250"  align="left">
                                                </tr>
                                                <!-- 1 -->
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="rtlWhCd_H2" ezfName="rtlWhCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Wh" >Warehouse Code</ezf:anchor></td>
                                                    <td><ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="5Z1" otherAttr=" size=\"10\" maxlength=\"6\" ezftoupper=\"\""/>
                                                        <ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn5" /></td>
                                                </tr>
                                                <!-- 2 -->
                                                <tr height="20">
                                                    <td class="stab">Warehouse Name</td>
                                                    <td><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="MONROE WAREHOUSE - CSA, MONROE " otherEvent1="OnBlur_WHName" otherAttr=" size=\"35\" maxlength=\"30\" ezffocusout=\"OnBlur_WHName\""/></td>
                                                </tr>
                                                <!-- 2 -->
                                                <tr height="20">
                                                    <td class="stab">Description</td>
                                                    <td><ezf:inputText name="rtlWhDescTxt_H1" ezfName="rtlWhDescTxt_H1" value="MONROE - CSA MAIN EQUIPMENT WAREHOUSE" otherAttr=" size=\"35\" maxlength=\"100\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <!-- 3 -->
                                                <tr height="20">
                                                    <td class="stab">Warehouse Type</td>
                                                    <td>
                                                        <ezf:select name="rtlWhCatgCd_H1" ezfName="rtlWhCatgCd_H1" ezfBlank="1" ezfCodeName="rtlWhCatgCd_L1" ezfDispName="rtlWhCatgNm_L1" otherEvent1=" onchange=\"sendServer('OnBlur_WHCategory')\"" otherAttr=" style=\"width:250\""/>
                                                    </td>
                                                </tr>
                                                <!-- 4 -->
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Mgr" >Owner</ezf:anchor></td>
                                                    <td class="stab"><ezf:inputText name="whMgrPsnCd_H1" ezfName="whMgrPsnCd_H1" value="Q99999" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="OnClick_MgrNm" value=">>" htmlClass="pBtn0" />
                                                                    <ezf:inputText name="fullPsnNm_H1" ezfName="fullPsnNm_H1" value="ED PETNER" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="altPsnCd_H2" ezfName="altPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_AltOwnr" >Altenate Owner</ezf:anchor></td>
                                                    <td class="stab"><ezf:inputText name="altPsnCd_H1" ezfName="altPsnCd_H1" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="OnClick_AltOwnrNm" value=">>" htmlClass="pBtn0" />
                                                                    <ezf:inputText name="fullPsnNm_H2" ezfName="fullPsnNm_H2" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">Phone Number</td>
                                                    <td><ezf:inputText name="telNum_H1" ezfName="telNum_H1" value="1-800-555-1235" otherAttr=" size=\"35\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">Fax Number</td>
                                                    <td><ezf:inputText name="faxNum_H1" ezfName="faxNum_H1" value="1-800-555-1235" otherAttr=" size=\"35\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td></td>
                                        <td>
                                            <table border="0">
                                                <tr>
                                                    <col width="115" align="left">
                                                    <col width="150" align="left">
                                                </tr>
                                                <!-- 2 -->
                                                <tr height="20">
                                                    <td class="stab">Inventory Account</td>
                                                    <td>
                                                        <ezf:select name="invtyAcctCd_H1" ezfName="invtyAcctCd_H1" ezfBlank="1" ezfCodeName="invtyAcctCd_L1" ezfDispName="invtyAcctNm_L1" otherAttr=" style=\"width:152\""/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">Inventory Ownership</td>
                                                    <td>
                                                        <ezf:select name="invtyOwnrCd_H1" ezfName="invtyOwnrCd_H1" ezfBlank="1" ezfCodeName="invtyOwnrCd_L1" ezfDispName="invtyOwnrNm_L1" otherAttr=" style=\"width:152\""/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">WH Operation</td>
                                                    <td>
                                                        <ezf:select name="whOwnrCd_H1" ezfName="whOwnrCd_H1" ezfBlank="1" ezfCodeName="whOwnrCd_L1" ezfDispName="whOwnrNm_L1" otherAttr=" style=\"width:152\""/>
                                                    </td>
                                                </tr>
                                                <!-- 1 -->
                                                <tr height="20">
                                                    <td class="stab">WH System Type</td>
                                                    <td>
                                                        <ezf:select name="whSysTpCd_H1" ezfName="whSysTpCd_H1" ezfBlank="1" ezfCodeName="whSysTpCd_L1" ezfDispName="whSysTpNm_L1" otherAttr=" style=\"width:152\""/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">WMS WH Code</td>
                                                    <td><ezf:inputText name="wmsWhCd_H1" ezfName="wmsWhCd_H1" value="2Z" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">WH Group Name</td>
                                                    <td><ezf:inputText name="physWhCd_H1" ezfName="physWhCd_H1" value="2Z" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">SO Auto Drop Flag</td>
                                                    <td>
                                                        <ezf:select name="autoSoDropFlg_H1" ezfName="autoSoDropFlg_H1" ezfBlank="1" ezfCodeName="xxTpCd_L1" ezfDispName="xxTpNm_L1" otherAttr=" style=\"width:152\""/>
                                                    </td>
                                                </tr>
                                                <!-- 3 -->
                                                <tr height="20">
                                                    <td class="stab">Space</td>
                                                    <td><ezf:inputText name="firstRefCmntTxt_H1" ezfName="firstRefCmntTxt_H1" value="50,000 SQ FT" otherAttr=" size=\"21\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td></td>
                                        <td>
                                            <table border="0">
                                                <tr>
                                                    <col width="90" align="left">
                                                    <col width="100" align="left">
                                                </tr>
                                                <!-- 1 -->
                                                <tr height="20">
                                                    <td class="stab">WH Start Date</td>
                                                    <td class="stab"><ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="01/01/2015" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
                                                        <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);"/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">WH End Date</td>
                                                    <td class="stab"><ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="12/31/9999" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/>
                                                        <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);"/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">WH Start Time</td>
                                                    <td><ezf:inputText name="xxScrItem7Txt_ST" ezfName="xxScrItem7Txt_ST" value="8:00" otherAttr=" size=\"12\" maxlength=\"5\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <!-- 2 -->
                                                <tr height="20">
                                                    <td class="stab">WH End Time</td>
                                                    <td><ezf:inputText name="xxScrItem7Txt_ET" ezfName="xxScrItem7Txt_ET" value="5:00" otherAttr=" size=\"12\" maxlength=\"5\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <!-- 3 -->
                                                <tr height="20">
                                                    <td class="stab">WH Cut Off Time</td>
                                                    <td><ezf:inputText name="xxScrItem7Txt_CT" ezfName="xxScrItem7Txt_CT" value="4:00" otherAttr=" size=\"12\" maxlength=\"5\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">Time Zone</td>
                                                    <td ><ezf:inputText name="tmZoneCd_H1" ezfName="tmZoneCd_H1" value="EDT" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab">GEO Code</td>
                                                    <td><ezf:inputText name="geoCd_H1" ezfName="geoCd_H1" otherAttr=" size=\"12\" maxlength=\"9\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="carrCd_H2" ezfName="carrCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Crr" >Preferred Carrier</ezf:anchor></td>
                                                    <td ><ezf:inputText name="carrCd_H1" ezfName="carrCd_H1" value="UPS" otherAttr=" size=\"20\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td></td>
                                        <td>
                                            <table border="0" valign="top">
                                                <tr>
                                                    <col width="80" align="left">
                                                    <col width="250"  align="left">
                                                </tr>
                                                <!-- 4 -->
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="coaBrCd_H2" ezfName="coaBrCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Branch" >Branch</ezf:anchor></td>
                                                    <td class="stab"><ezf:inputText name="coaBrCd_H1" ezfName="coaBrCd_H1" value="WWWWWWWWW1WWWWW" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="OnClick_Branch" value=">>" htmlClass="pBtn0" />
                                                                    <ezf:inputText name="coaBrNm_H1" ezfName="coaBrNm_H1" otherAttr=" size=\"13\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="prntVndCd_L1" ezfName="prntVndCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier</ezf:anchor></td>
                                                    <td class="stab"><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="11331" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                     <ezf:inputButton name="Get_SupplierName" ezfName="Get_ShipToInfo" value=">>" htmlClass="pBtn0" />
                                                                     <ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="CANON USA NP / CLC" otherAttr=" size=\"10\" maxlength=\"240\" ezftoupper=\"\""/></td>
                                                </tr>
                                                <tr height="20">
                                                    <td class="stab"><ezf:anchor name="vndCd_L1" ezfName="vndCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier Site</ezf:anchor></td>
                                                    <td class="stab"><ezf:inputText name="vndCd" ezfName="vndCd" value="1133133" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/>
                                                    <ezf:inputButton name="Get_SupplierSiteName" ezfName="Get_ShipToInfo" value=">>" htmlClass="pBtn0" />
                                                    <ezf:inputText name="vndNm" ezfName="vndNm" value="CAN02" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                </tr>

                                                <!-- tr height="20"><td></td></tr>
                                                <tr height="20"><td></td></tr-->
                                                <tr height="20"><td></td></tr>
                                                <tr height="20"><td></td></tr>
                                                <tr height="20"><td></td></tr>
                                                <tr height="20"><td></td></tr>
                                                <tr height="20"><td></td></tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <hr>
                            <%-- ######################################## DETAIL ######################################## --%>

                            <%-- ###TAB - HEAD --%>
                            <div class="pTab_HEAD">
                                <ul>
                                    <table width="998" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="96%">
                                                <div>
                                                    <li title="Address" id="Address" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_11" ezfEmulateBtn="1" ezfGuard="TAB_Address" >Address</ezf:anchor></li>
                                                    <li title="Sourcing" id="Sourcing" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_31" ezfEmulateBtn="1" ezfGuard="TAB_Sourcing" >Sourcing</ezf:anchor></li>
                                                    <li title="Sub WH" id="SWH" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_41" ezfEmulateBtn="1" ezfGuard="TAB_SWH" >Sub WH</ezf:anchor></li>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </ul>
                            </div>

                            <div class="pTab_BODY_In">
                                <div style="height: 321px" >
                                <c:choose>
                                    <%-- ADDED FROM HERE --%>
                                    <%-- ---------------------------------- TAB -------------------------------- --%>
                                    <c:when test="${pageScope._ezddatabean.xxDplyTab == 'Address'}">
                                        <div id="TabContent-Address"><!-- Added -->
                                        <script type="text/javascript">
                                            document.getElementById( "Address"    ).className = "pTab_ON";
                                            document.getElementById( "Sourcing"   ).className = "pTab_OFF";
                                            document.getElementById( "SWH"        ).className = "pTab_OFF";
                                        </script>

                                        <div id="RightTBL" style="overflow-y:scroll; height:310; overflow-x:none; width:100%;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
                                        <table  border="0" style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px">
                                            <col width="120">
                                            <col width="300">
                                            <tr height="10">
                                                <td class="stab">Ship To</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </table>
                                        <table  border="0" style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px">
                                            <tr>
                                                <td>
                                                    <table border="0">
                                                        <col width="98">
                                                        <col width="">
                                                        <col width="10">
                                                        <tr height="20">
                                                            <td class="stab"><ezf:anchor name="shipToCustCd_S2" ezfName="shipToCustCd_S2" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCode" >Ship To Loc #</ezf:anchor></td>
                                                            <td>
                                                                <ezf:inputText name="locNum_S1" ezfName="locNum_S1" value="12345" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                <ezf:inputButton name="OnClick_SetShipTo" value="Set" htmlClass="pBtn2" />
                                                                <ezf:inputButton name="OnClick_ClearShipTo" value="Clear" htmlClass="pBtn2" />
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Ship To Loc Name</td>
                                                            <td><ezf:inputText name="dsLocNm_S1" ezfName="dsLocNm_S1" value="MONROE DOCK, JAMESBURG PARTS DOCK" otherAttr=" size=\"25\" maxlength=\"60\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Additional Name</td>
                                                            <td><ezf:inputText name="addlLocNm_S1" ezfName="addlLocNm_S1" otherAttr=" size=\"25\" maxlength=\"60\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line1</td>
                                                            <td><ezf:inputText name="firstLineAddr_S1" ezfName="firstLineAddr_S1" value="24 Englehard Drive Ste. B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line2</td>
                                                            <td><ezf:inputText name="scdLineAddr_S1" ezfName="scdLineAddr_S1" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line3</td>
                                                            <td><ezf:inputText name="thirdLineAddr_S1" ezfName="thirdLineAddr_S1" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line4</td>
                                                            <td><ezf:inputText name="frthLineAddr_S1" ezfName="frthLineAddr_S1" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td>
                                                    <table border="0">
                                                        <col width="">
                                                        <col width="">
                                                        <col width="10">
                                                        <tr height="20">
                                                            <td class="stab">Account#</td>
                                                            <td class="stab">
                                                                <ezf:inputText name="dsAcctNum_S1" ezfName="dsAcctNum_S1" value="1002800" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                <ezf:inputText name="dsAcctNm_S1" ezfName="dsAcctNm_S1" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"23\" maxlength=\"60\" ezftoupper=\"\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">City</td>
                                                            <td><ezf:inputText name="ctyAddr_S1" ezfName="ctyAddr_S1" value="Monroe Township" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">County</td>
                                                            <td><ezf:select name="cntyPk_S1" ezfName="cntyPk_S1" ezfBlank="1" ezfCodeName="cntyPk_L1" ezfDispName="cntyNm_L1" otherAttr=" style=\"width:180\""/>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab"><ezf:anchor name="stCd_S2" ezfName="stCd_S2" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToState" >State</ezf:anchor></td>
                                                            <td><ezf:inputText name="stCd_S1" ezfName="stCd_S1" value="NJ" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Province</td>
                                                            <td><ezf:inputText name="provNm_S1" ezfName="provNm_S1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
                                                            <td>&nbsp;</td>
                                                        <tr height="20">
                                                            <td class="stab">Postal Code</td>
                                                            <td><ezf:inputText name="postCd_S1" ezfName="postCd_S1" value="08831" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\" style=\"width:100\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Country</td>
                                                            <td>
                                                                <ezf:select name="ctryCd_S1" ezfName="ctryCd_S1" ezfBlank="1" ezfCodeName="ctryCd_L1" ezfDispName="ctryNm_L1" otherAttr=" style=\"width:180\""/>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td valign="top">
                                                    <table border="0">
                                                        <col width="105">
                                                        <col width="">
                                                        <tr height="20">
                                                            <td class="stab">Trading Partner ID</td>
                                                            <td><ezf:inputText name="vndLocCd_S1" ezfName="vndLocCd_S1" value="D03 LIVERMORE DOCK" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr height="20" colspan="5">
                                                            <td class="stab">PO Receipt Routing</td>
                                                            <td>
                                                                <ezf:select name="poRcptRteTpCd_S1" ezfName="poRcptRteTpCd_S1" ezfBlank="1" ezfCodeName="poRcptRteTpCd_L1" ezfDispName="poRcptRteTpNm_L1" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="20" colspan="5">
                                                            <td class="stab">RMA Receipt Routing</td>
                                                            <td>
                                                                <ezf:select name="rmaRcptRteTpCd_S1" ezfName="rmaRcptRteTpCd_S1" ezfBlank="1" ezfCodeName="rmaRcptRteTpCd_L1" ezfDispName="rmaRcptRteTpNm_L1" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <hr>
                                        <table  border="0" style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px">
                                            <col width="70">
                                            <col width="300">
                                            <tr height="15">
                                                <td class="stab">Return To</td>
                                                <td class="stab">
                                                    <ezf:inputCheckBox name="xxChkBox_R1" ezfName="xxChkBox_R1" value="Y" />
                                                    Return To Address Is the same with Ship To Address
                                                </td>
                                            </tr>
                                        </table>
                                        <table  border="0" style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px">
                                            <tr>
                                                <td>
                                                    <table border="0">
                                                        <col width="98">
                                                        <col width="">
                                                        <col width="10">
                                                        <tr height="20">
                                                            <td class="stab"><ezf:anchor name="shipToCustCd_R2" ezfName="shipToCustCd_R2" ezfEmulateBtn="1" ezfGuard="OpenWin_ReturnToCode" >Return To Loc #</ezf:anchor></td>
                                                            <td>
                                                                <ezf:inputText name="locNum_R1" ezfName="locNum_R1" value="12345" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                <ezf:inputButton name="OnClick_SetReturnTo" value="Set" htmlClass="pBtn2" />
                                                                <ezf:inputButton name="OnClick_ClearReturnTo" value="Clear" htmlClass="pBtn2" />
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>

                                                        <tr height="20">
                                                            <td class="stab">Return To Loc Name</td>
                                                            <td><ezf:inputText name="dsLocNm_R1" ezfName="dsLocNm_R1" value="MONROE DOCK, JAMESBURG PARTS DOCK" otherAttr=" size=\"25\" maxlength=\"60\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Additional Name</td>
                                                            <td><ezf:inputText name="rtrnToAddlLocNm_R1" ezfName="rtrnToAddlLocNm_R1" otherAttr=" size=\"25\" maxlength=\"60\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line1</td>
                                                            <td><ezf:inputText name="rtrnToFirstLineAddr_R1" ezfName="rtrnToFirstLineAddr_R1" value="24 Englehard Drive Ste. B" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line2</td>
                                                            <td><ezf:inputText name="rtrnToScdLineAddr_R1" ezfName="rtrnToScdLineAddr_R1" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line3</td>
                                                            <td><ezf:inputText name="rtrnToThirdLineAddr_R1" ezfName="rtrnToThirdLineAddr_R1" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Address Line4</td>
                                                            <td><ezf:inputText name="rtrnToFrthLineAddr_R1" ezfName="rtrnToFrthLineAddr_R1" otherAttr=" size=\"25\" maxlength=\"50\" ezftoupper=\"\" style=\"width:250\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td>
                                                    <table border="0">
                                                        <col width="">
                                                        <col width="">
                                                        <col width="10">
                                                        <tr height="20">
                                                            <td class="stab">Account#</td>
                                                            <td class="stab">
                                                                <ezf:inputText name="dsAcctNum_R1" ezfName="dsAcctNum_R1" value="1002800" otherAttr=" size=\"8\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                <ezf:inputText name="dsAcctNm_R1" ezfName="dsAcctNm_R1" value="CANON SOLUTIONS AMERICA" otherAttr=" size=\"23\" maxlength=\"60\" ezftoupper=\"\""/>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                            <td class="stab">City</td>
                                                            <td><ezf:inputText name="rtrnToCtyAddr_R1" ezfName="rtrnToCtyAddr_R1" value="Monroe Township" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">County</td>
                                                            <td><ezf:select name="cntyPk_R1" ezfName="cntyPk_R1" ezfBlank="1" ezfCodeName="cntyPk_L2" ezfDispName="cntyNm_L2" otherAttr=" style=\"width:180\""/>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab"><ezf:anchor name="rtrnToStCd_R2" ezfName="rtrnToStCd_R2" ezfEmulateBtn="1" ezfGuard="OpenWin_ReturnToState" >State</ezf:anchor></td>
                                                            <td><ezf:inputText name="rtrnToStCd_R1" ezfName="rtrnToStCd_R1" value="NJ" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Province</td>
                                                            <td><ezf:inputText name="rtrnToProvNm_R1" ezfName="rtrnToProvNm_R1" otherAttr=" size=\"25\" maxlength=\"25\" ezftoupper=\"\""/></td>
                                                            <td>&nbsp;</td>
                                                        <tr height="20">
                                                            <td class="stab">Postal Code</td>
                                                            <td><ezf:inputText name="rtrnToPostCd_R1" ezfName="rtrnToPostCd_R1" value="08831" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\" style=\"width:100\""/></td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab">Country</td>
                                                            <td>
                                                                <ezf:select name="ctryCd_R1" ezfName="ctryCd_R1" ezfBlank="1" ezfCodeName="ctryCd_L2" ezfDispName="ctryNm_L2" otherAttr=" style=\"width:180\""/>
                                                            </td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td valign="top">
                                                    <table border="0">
                                                        <col width="105">
                                                        <col width="">
                                                        <tr height="20">
                                                            <td class="stab">Trading Partner ID</td>
                                                            <td><ezf:inputText name="vndLocCd_R1" ezfName="vndLocCd_R1" value="D03 LIVERMORE DOCK" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        </div><!-- Added -->
                                        </div><!-- Added -->
                                    </c:when>

                                    <c:when test="${pageScope._ezddatabean.xxDplyTab == 'Sourcing'}">
                                        <div id="TabContent-Sourcing">
                                        <script type="text/javascript">
                                            document.getElementById( "Address"    ).className = "pTab_OFF";
                                            document.getElementById( "Sourcing"   ).className = "pTab_ON";
                                            document.getElementById( "SWH"        ).className = "pTab_OFF";
                                        </script>
                                        <table border="0"  style="MARGIN-LEFT: 10px; MARGIN-TOP: 10px">
                                            <tr>
                                                <col width="600"  align="left">
                                                <col width=""  align="left">
                                            </tr>
                                            <tr>
                                                <td>
                                                    <table border="0">
                                                        <col width="150" align="left">
                                                        <col width="60"  align="left">
                                                        <col width="10"  align="left">
                                                        <col width="190" align="left">
                                                        <tr height="25">
                                                            <td class="stab">Allow MIN-MAX Parts<br>For Insourcing</td>
                                                            <td>
                                                                <ezf:select name="plnItemInsrcCd_S1" ezfName="plnItemInsrcCd_S1" ezfBlank="1" ezfCodeName="plnItemInsrcCd_L1" ezfDispName="plnItemInsrcNm_L1" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="10">
                                                            <td></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td class="stab">Zone</td>
                                                            <td colspan="3">
                                                                <ezf:select name="srcZnCd_S1" ezfName="srcZnCd_S1" ezfBlank="1" ezfCodeName="srcZnCd_L1" ezfDispName="srcZnNm_L1" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="10">
                                                            <td></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td class="stab">Default Source Type</td>
                                                            <td colspan="3">
                                                                <ezf:select name="procrTpCd_S1" ezfName="procrTpCd_S1" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_SourcingDefaultSource')\"" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab"><ezf:anchor name="procrTpCd_S2" ezfName="procrTpCd_S2" ezfEmulateBtn="1" ezfGuard="OpenWin_SourceLocation" >Default Source</ezf:anchor></td>
                                                            <td><ezf:inputText name="prntVndNm_SD" ezfName="prntVndNm_SD" otherAttr=" size=\"10\" maxlength=\"240\" ezftoupper=\"\" style=\"width:150\""/></td>
                                                            <td><ezf:inputText name="vndNm_SD" ezfName="vndNm_SD" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr height="10">
                                                            <td></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td class="stab">Tech Emergency Source Type</td>
                                                            <td colspan="3">
                                                                <ezf:select name="procrTpCd_E1" ezfName="procrTpCd_E1" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_SourcingDefaultEmerSource')\"" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="20">
                                                            <td class="stab"><ezf:anchor name="procrTpCd_E2" ezfName="procrTpCd_E2" ezfEmulateBtn="1" ezfGuard="OpenWin_EmergencyLocation" >Emergency Source</ezf:anchor></td>
                                                            <td><ezf:inputText name="prntVndNm_SE" ezfName="prntVndNm_SE" otherAttr=" size=\"10\" maxlength=\"240\" ezftoupper=\"\" style=\"width:150\""/></td>
                                                            <td><ezf:inputText name="vndNm_SE" ezfName="vndNm_SE" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                        <tr height="10">
                                                            <td></td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td class="stab">Default Return To</td>
                                                            <td colspan="3">
                                                                <ezf:select name="procrTpCd_R1" ezfName="procrTpCd_R1" ezfBlank="1" ezfCodeName="procrTpCd_L3" ezfDispName="procrTpNm_L3" otherEvent1=" onchange=\"sendServer('OnChange_SourcingDefaultReturnTo')\"" otherAttr=" style=\"width:150\""/>
                                                            </td>
                                                        </tr>
                                                        <tr height="25">
                                                            <td class="stab"><ezf:anchor name="procrTpCd_R2" ezfName="procrTpCd_R2" ezfEmulateBtn="1" ezfGuard="OpenWin_ReturnToLocation" >Return To</ezf:anchor></td>
                                                            <td><ezf:inputText name="prntVndNm_SR" ezfName="prntVndNm_SR" otherAttr=" size=\"10\" maxlength=\"240\" ezftoupper=\"\" style=\"width:150\""/></td>
                                                            <td><ezf:inputText name="vndNm_SR" ezfName="vndNm_SR" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        </div>
                                    </c:when>
                                    <c:when test="${pageScope._ezddatabean.xxDplyTab == 'SWH'}">
                                        <div id="TabContent-SWH">
                                            <script type="text/javascript">
                                                document.getElementById( "Address"    ).className = "pTab_OFF";
                                                document.getElementById( "Sourcing"   ).className = "pTab_OFF";
                                                document.getElementById( "SWH"        ).className = "pTab_ON";
                                            </script>
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td align="left" valign="top">
                                                        <%-- ******************************* --%>
                                                        <%-- *** Left Table Area(Header) *** --%>
                                                        <%-- ******************************* --%>
                                                        <table style="table-layout:fixed; margin-left:20px;margin-top:20px;padding:0px;" border="1" cellpadding="1" cellspacing="0" height="35">
                                                            <col width="40" align="center">
                                                            <col width="128" align="center">
                                                            <tr>
                                                                <td align="center" class="pClothBs">SWH<br/>Code</td>
                                                                <td align="center" class="pClothBs">SWH&nbsp;Name</td>
                                                            </tr>
                                                        </table>
                                                        <%-- ******************************* --%>
                                                        <%-- *** Left Table Area(Detail) *** --%>
                                                        <%-- ******************************* --%>
                                                        <div id="LeftTBL" style="overflow-y:hidden; height:243; overflow-x:hidden; width:; margin-left: 20px;"
                                                            onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                                                <col width="40" align="center">
                                                                <col width="128" align="center">
                                                                <ezf:row ezfHyo="A">
                                                                    <tr height="28">
                                                                        <ezf:inputHidden name="xxModeCd_A1" ezfName="xxModeCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\""/>
                                                                        <td align="center"><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"30\" id=\"rtlSwhNm_A1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <input type="hidden" name="xxModeCd_A1" ezfname="xxModeCd_A1" value="" ezfHyo="A" ezftoupper>
                                                                        <td align="center"><input type="text" size="3" maxlength="20" value="NEW" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="text" size="17" maxlength="30" value="SPARES PARTS USED" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfHyo="A" ezftoupper></td>
                                                                    </tr>
                                                                </ezf:skip>
                                                            </table>
                                                        </div>
                                                    </td>
                                                    <%-- =============== TABLE HEADER =============== --%>
                                                    <td valign="top">
                                                        <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:915; margin-top:20px;padding:0px;">
                                                            <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" height="35" width="">
                                                                <col width="165" align="center">
                                                                <col width="50" align="center">
                                                                <col width="50" align="center">
                                                                <col width="50" align="center">
                                                                <col width="55" align="center">
                                                                <col width="95" align="center">
                                                                <col width="170" align="center">
                                                                <col width="115" align="center">
                                                                <col width="95" align="center">
                                                                <col width="170" align="center">
                                                                <col width="115" align="center">
                                                                <tr>
                                                                    <td align="center" class="pClothBs">SWH&nbsp;Description</td>
                                                                    <td align="center" class="pClothBs">Primary</td>
                                                                    <td align="center" class="pClothBs">Disable</td>
                                                                    <td align="center" class="pClothBs">Min-Max<br/>Enabled</td>
                                                                    <td align="center" class="pClothBs">Package<br/>Code</td>
                                                                    <td align="center" class="pClothBs">Default Source From</td>
                                                                    <td align="center" class="pClothBs">Source</td>
                                                                    <td align="center" class="pClothBs">Sub Source</td>
                                                                    <td align="center" class="pClothBs">Default Return To</td>
                                                                    <td align="center" class="pClothBs">Return To</td>
                                                                    <td align="center" class="pClothBs">Return<br>Sub Source</td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                        <%-- =============== TABLE BODY =============== --%>
                                                        <div id="RightTBL" style="overflow-y:scroll; height:260; overflow-x:scroll; width:932;" 
                                                            onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
                                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="">
                                                                <col width="165" align="center">
                                                                <col width="50" align="center">
                                                                <col width="50" align="center">
                                                                <col width="50" align="center">
                                                                <col width="55" align="center">
                                                                <col width="95" align="center">
                                                                <col width="170" align="center">
                                                                <col width="115" align="center">
                                                                <col width="95" align="center">
                                                                <col width="170" align="center">
                                                                <col width="115" align="center">
                                                                <ezf:row ezfHyo="A">
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"50\" id=\"rtlSwhDescTxt_A1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="prntVndNm_AS" ezfName="prntVndNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"240\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="vndNm_AS" ezfName="vndNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="prntVndNm_AR" ezfName="prntVndNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"240\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="vndNm_AR" ezfName="vndNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                    <tr height="28px">
                                                                        <td align="center"><input type="text" size="22" maxlength="50" value="ASSET RECOVERY (Other)" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td align="center"><input type="checkbox" name="xxChkBox_P1" value="1" ezfname="xxChkBox_P1" value="Y" ezfHyo="A" ezfArrayNo="0" ></td>
                                                                        <td align="center"><input type="checkbox" name="xxChkBox_D1" value="1" ezfname="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="0" ></td>
                                                                        <td align="center"><input type="checkbox" name="xxChkBox_M1" value="1" ezfname="xxChkBox_M1" value="Y" ezfHyo="A" ezfArrayNo="0" ></td>
                                                                        <td><input type="text" size="6" maxlength="6" value="CSANEW" name="xxScrItem8Txt_A1" ezfname="xxScrItem8Txt_A1" ezfHyo="A" ezftoupper></td>
                                                                        <td>
                                                                            <select name="procrTpCd_A1" ezfname="procrTpCd_A1" ezfhyo="A" ezflist="procrTpCd_L1,procrTpNm_L1,99, ,blank" style="width:90px" onchange="sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')" id="OnChange_DefaultSouce{EZF_ROW_NUMBER}">
                                                                                <ezf:skip>
                                                                                <option></option>
                                                                                <option selected>Supplier</option>
                                                                                <option>Warehouse</option>
                                                                                </ezf:skip>
                                                                            </select>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="1" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="1" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="1" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="1" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="1" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="1" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="1" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="2" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="2" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="2" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="2" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="2" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="2" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="2" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="3" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="3" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="3" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="3" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="3" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="3" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="3" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="4" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="4" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="4" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="4" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="4" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="4" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="4" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="5" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="5" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="5" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="5" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="5" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="5" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="5" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="6" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="6" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="6" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="6" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="6" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="6" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="6" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="7" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="7" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="7" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="7" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="7" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="7" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="7" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="8" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="8" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="8" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="8" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="8" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="8" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="8" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="9" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="9" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="9" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                    <tr height="28px">
                                                                        <td align="center"><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="ASSET RECOVERY (Other)" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"22\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_P1" ezfName="xxChkBox_P1" value="1" ezfHyo="A" ezfArrayNo="9" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="1" ezfHyo="A" ezfArrayNo="9" /></td>
                                                                        <td align="center"><ezf:inputCheckBox name="xxChkBox_M1" ezfName="xxChkBox_M1" value="1" ezfHyo="A" ezfArrayNo="9" /></td>
                                                                        <td><ezf:inputText name="xxScrItem8Txt_A1" ezfName="xxScrItem8Txt_A1" value="CSANEW" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A1" ezfName="procrTpCd_A1" ezfHyo="A" ezfArrayNo="9" ezfBlank="1" ezfCodeName="procrTpCd_L1" ezfDispName="procrTpNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_DefaultSouce', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultSouce{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AS" ezfName="rtlWhNm_AS" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_SourceLocation_SW" value="…" ezfHyo="A" ezfArrayNo="10" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SourceLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AS" ezfName="rtlSwhNm_AS" value="USED 10 PERCENT" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                        <td>
                                                                            <ezf:select name="procrTpCd_A2" ezfName="procrTpCd_A2" ezfHyo="A" ezfArrayNo="10" ezfBlank="1" ezfCodeName="procrTpCd_L2" ezfDispName="procrTpNm_L2" otherEvent1=" onchange=\"sendServer('OnChange_DefaultReturnTo', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:90px\" id=\"OnChange_DefaultReturnTo{EZF_ROW_NUMBER}\""/>
                                                                        </td>
                                                                        <td class="stab"><ezf:inputText name="rtlWhNm_AR" ezfName="rtlWhNm_AR" value="SAN PEDRO WH - BSD" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"18\" maxlength=\"30\" ezftoupper=\"\""/>
                                                                            <ezf:inputButton name="OpenWin_ReturnToLocation_SW" value="…" ezfHyo="A" ezfArrayNo="10" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ReturnToLocation_SW{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="rtlSwhNm_AR" ezfName="rtlSwhNm_AR" value="SPARES PARTS USED" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                    </tr>
                                                                </ezf:skip>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </c:when>

                                </c:choose>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </center>



<%-- **** Task specific area ends here **** --%>
