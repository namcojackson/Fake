<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140304043053 --%>
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
            <input type="hidden" name="pageID" value="NLGL0040Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Ship Via Mapping from HOST to WMS">

<center>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
<%-- ######################################## HEADER ######################################## --%>
                <%-- include S21BusinessProcessTAB --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <div class="pTab_BODY">
                    <table height="570" width="100%">
                        <col valign="top">
                        <tr>
                            <td>
                                <div class="pTab_HEAD">
<%-- ## HEADER 1(WH Cd) ## --%>
                                    <table border="0">
                                        <col width="32">
                                        <col width="70">
                                        <col width="5">
                                        <col width="812">
                                        <tr>
                                            <td class="stab">WH</td>
                                            <td>
                                                    <ezf:select name="whCd_H1" ezfName="whCd_H1" ezfCodeName="whCd_L1" ezfDispName="xxEdtCdNm_L1" />
                                            </td>
                                            <td></td>
                                            <td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
                                            <td></td>
                                            </tr>
                                    </table>
                                    <hr>
                                    <ul>
                                        <li id = "Ship Via List" title = "Ship Via List" class="pTab_ON"><ezf:anchor name="xxTabShipVia_List" ezfName="xxTabShipVia_List" ezfEmulateBtn="1" ezfGuard="OnClick_Ship_Via_List_Tab" >Ship Via List</ezf:anchor></li>
                                        <li id = "Ship Via Edit" title = "Ship Via Edit" class="pTab_OFF"><ezf:anchor name="xxTabShipVia_Edit" ezfName="xxTabShipVia_Edit" ezfEmulateBtn="1" ezfGuard="OnClick_Ship_Via_Edit_Tab" >Ship Via Edit</ezf:anchor></li>
                                    </ul>
                                </div>
                                <c:choose>
<%-- ######################################## DETAIL ######################################## --%>
                                <%-- #Ship Via List TAB Start#  --%>
                                <c:when test="${pageScope._ezddatabean.xxDplyTab=='xxTabShipVia_List'}">
                                <script type="text/javascript">
                                    document.getElementById( "Ship Via List" ).className ="pTab_ON";
                                    document.getElementById( "Ship Via Edit" ).className ="pTab_OFF";
                                </script>

                                <table width="1126">
                                    <col valign="top">
                                    <tr>
                                        <td>
                                            <%-- ###TAB - BODY --%>
                                            <div class="pTab_BODY_In">
                                                <table height="474" width="100%">
                                                    <tr valign="top">
                                                        <td>
                                                            <%-- Pagenation --%>
                                                            <table width="100%">
                                                                <col width="154" align="left">
                                                                <col width="750"  align="right">
                                                                <tr align="right">
                                                                    <td><ezf:inputButton name="OnClick_New" value="Insert Row" htmlClass="pBtn6" /></td>
                                                                    <td>
                                                                        <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                                                        <jsp:param name="beanId" value='<%= request.getParameter( "beanId" ) %>' />
                                                                        <jsp:param name="TableNm" value="A" />
                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_D1" />
                                                                        <jsp:param name="ShowingTo"value="xxPageShowToNum_D1" />
                                                                        <jsp:param name="ShowingOf"value="xxPageShowOfNum_D1" />
                                                                        </jsp:include>
                                                                    </td>
                                                                </tr>
                                                            </table>

                                                            <table border="1" cellpadding="0" cellspacing="0">
                                                                <tr>
                                                                    <%-- ########## Table:A --%>
                                                                    <td align="left" valign="top">
                                                                        <div id="topTBL" style="overflow-y:hidden; height:; overflow-x:hidden; width:396;" onScroll="synchroScrollLeft(this.id, new Array('topTBL'));"> 
                                                                            <table border="1" cellpadding="1" cellspacing="0" width="826">
                                                                                <col width="40">
                                                                                <col width="44" align="center">
                                                                                <col width="735" align="left">
                                                                                <tr height="37">
                                                                                        <td class="pClothBs">&nbsp;</td>
                                                                                        <td class="pClothBs">Ship<br>Via</td>
                                                                                        <td class="pClothBs">&nbsp;&nbsp;Description</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>

                                                                        <%-- id:leftTBL --%>
                                                                        <div id="leftTBL" style="overflow-y:hidden; height:375; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
                                                                        <table border="1" cellpadding="1" cellspacing="0" width="397" id="A_left">
                                                                            <col width="40" align="center">
                                                                            <col width="44" align="center">
                                                                            <col width="300" align="left">
                                                                            <ezf:row ezfHyo="A">
                                                                                <tr height="37">
                                                                                    <td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
                                                                                    <td><ezf:label name="wmsShipViaTpCd_D1" ezfName="wmsShipViaTpCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                    <td><ezf:inputText name="wmsDescTxt_D1" ezfName="wmsDescTxt_D1" value="Camera Video Carrier Program" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"100\""/></td>
                                                                                </tr>
                                                                            </ezf:row>
                                                                            <ezf:skip>
                                                                                <tr height="37" class="pEvenNumberBGcolor">
                                                                                    <td><input type="checkbox" name="checkBox"value="Y"></td>
                                                                                    <td><label ezfout>B</label></td>
                                                                                    <td><input type="text" size="40" maxlength="100" value="X---10---XX---10---XX---10---XX---10---XX---10---XX---10---XX---10---XX---10---XX---10---XX---10---X" name="wmsDescTxt_D1" ezfname="wmsDescTxt_D1" ezfhyo="A"></td>
                                                                                </tr>
                                                                            </ezf:skip>
                                                                        </table>
                                                                        </div>
                                                                    </td>

                                                                    <%-- ########## Table:B --%>
                                                                    <td valign="top">
                                                                        <%-- id:topTBL --%>
                                                                        <div id="topTBL2" style="overflow-y:none; height:; overflow-x:hidden; width:697;" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));"> 
                                                                            <table border="1" cellpadding="1" cellspacing="0" width="1804">
                                                                                <col width="84" align="center">
                                                                                <col width="88" align="center">
                                                                                <col width="180" align="center">
                                                                                <col width="100" align="center">
                                                                                <col width="96" align="center">
                                                                                <col width="180" align="center">
                                                                                <col width="100" align="center">
                                                                                <col width="96" align="center">
                                                                                <col width="180" align="center">
                                                                                <col width="100" align="center">
                                                                                <col width="96" align="center">
                                                                                <col width="140" align="center">
                                                                                <col width="142" align="center">
                                                                                <tr height="37">
                                                                                    <td class="pClothBs">Mode<br>Break Type</td>
                                                                                    <td class="pClothBs">Rte<br>Guide#</td>
                                                                                    <td class="pClothBs">PCL Carrier</td>
                                                                                    <td class="pClothBs">PCL Max<br>Capacity</td>
                                                                                    <td class="pClothBs">PCL Priority<br>Code</td>
                                                                                    <td class="pClothBs">LTL Carrier</td>
                                                                                    <td class="pClothBs">LTL Max<br>Capacity</td>
                                                                                    <td class="pClothBs">LTL Priority<br>Code</td>
                                                                                    <td class="pClothBs">FTL Carrier</td>
                                                                                    <td class="pClothBs">FTL Max<br>Capacity</td>
                                                                                    <td class="pClothBs">FTL Priority<br>Code</td>
                                                                                    <td class="pClothBs">Updated By</td>
                                                                                    <td class="pClothBs">Updated On</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <%-- id:rightTBL --%>
                                                                        <div id="rightTBL" style="overflow-y:scroll; height:392; overflow-x:scroll; width:714;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL2'));">
                                                                        <table border="1" cellpadding="1" cellspacing="0" width="1804" id="A_right">
                                                                                <col width="84" align="center">
                                                                                <col width="87" align="left">
                                                                                <col width="177" align="left">
                                                                                <col width="97" align="right">
                                                                                <col width="96" align="center">
                                                                                <col width="177" align="left">
                                                                                <col width="97" align="right">
                                                                                <col width="96" align="center">
                                                                                <col width="177" align="left">
                                                                                <col width="97" align="right">
                                                                                <col width="96" align="center">
                                                                                <col width="140" align="center">
                                                                                <col width="142" align="center">

                                                                                <ezf:row ezfHyo="A">
                                                                                    <tr height="37">
                                                                                        <td><ezf:label name="mdBreakTpCd_D1" ezfName="mdBreakTpCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:label name="rteGuideNum_D1" ezfName="rteGuideNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:inputText name="pclCarrCd_D1" ezfName="pclCarrCd_D1" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"40\""/></td>
                                                                                        <td><ezf:label name="pclMaxCapNum_D1" ezfName="pclMaxCapNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:label name="pclPrtyCd_D1" ezfName="pclPrtyCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:inputText name="ltlCarrCd_D1" ezfName="ltlCarrCd_D1" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"40\""/></td>
                                                                                        <td><ezf:label name="ltlMaxCapNum_D1" ezfName="ltlMaxCapNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:label name="ltlPrtyCd_D1" ezfName="ltlPrtyCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:inputText name="tlCarrCd_D1" ezfName="tlCarrCd_D1" value="1234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"40\""/></td>
                                                                                        <td><ezf:label name="tlMaxCapNum_D1" ezfName="tlMaxCapNum_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:label name="tlPrtyCd_D1" ezfName="tlPrtyCd_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:label name="ezUpUserID_D1" ezfName="ezUpUserID_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                        <td><ezf:label name="xxDtTm_D1" ezfName="xxDtTm_D1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                                    </tr>
                                                                                </ezf:row>
                                                                                <ezf:skip>
                                                                                <tr height="37" class="pEvenNumberBGcolor">
                                                                                    <td><label ezfout>12</label></td>
                                                                                    <td><label ezfout>12</label></td>
                                                                                    <td><label ezfout>BB1108 </label></td>
                                                                                    <td><label ezfout>B20S</label></td>
                                                                                    <td><label ezfout>B20S</label></td>
                                                                                    <td><label ezfout>0000</label></td>
                                                                                    <td><label ezfout>0</label></td>
                                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                                </tr>
                                                                                </ezf:skip>
                                                                            </table>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
<%-- ######################################## FOOTER ######################################## --%>
                                        </td>
                                    </tr>
                                </table>
                                </c:when>
                                <%-- #Ship Via List TAB End#  --%>

                                <%-- #Ship Via Edit TAB Start#  --%>
                                <c:when test="${pageScope._ezddatabean.xxDplyTab=='xxTabShipVia_Edit'}">
                                <script type="text/javascript">
                                    document.getElementById( "Ship Via List" ).className ="pTab_OFF";
                                    document.getElementById( "Ship Via Edit" ).className ="pTab_ON";
                                </script>
                                <div class="pTab_BODY_In" style="height:484;">
                                    <table border="0" width="100%">
                                        <col valign="top">
                                        <tr>
                                            <td>
<%-- ######################################## DETAIL2 ######################################## --%>
                                                <table cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td width="30"></td>
                                                        <td>
                                                        <table width ="1052">
                                                            <tr align="right">
                                                                <td></td>
                                                            </tr>
                                                        </table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td width="30"></td>
                                                        <td>
                                                            <table>
                                                                <col width="85">
                                                                <col width="745">
                                                                <tr height="32px">
                                                                        <td class="stab">Ship Via Code</td>
                                                                        <td><ezf:inputText name="wmsShipViaTpCd_D2" ezfName="wmsShipViaTpCd_D2" value="A" otherAttr=" size=\"4\" maxlength=\"4\" enable=\"false\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">Description</td>
                                                                        <td><ezf:inputText name="wmsDescTxt_D2" ezfName="wmsDescTxt_D2" value="Camera Video Carrier Program" otherAttr=" size=\"100\" maxlength=\"100\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">Mode Break Type</td>
                                                                        <td>
                                                                        <ezf:select name="mdBreakTpCd_P2" ezfName="mdBreakTpCd_P2" ezfCodeName="mdBreakTpCd_D2" ezfDispName="xxEdtCdNm_D2" />
                                                                        </td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">Rte Guide#</td>
                                                                        <td><ezf:inputText name="rteGuideNum_D2" ezfName="rteGuideNum_D2" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">PCL Carrier</td>
                                                                        <td><ezf:inputText name="pclCarrCd_D2" ezfName="pclCarrCd_D2" value="*XXX10XXX**XXX10XXX**XXX10XXX**XXX10XXX*" otherAttr=" size=\"40\" maxlength=\"40\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">PCL Max Capacity</td>
                                                                        <td><ezf:inputText name="pclMaxCapNum_D2" ezfName="pclMaxCapNum_D2" value="1234567890123456789012345678" otherAttr=" style=\"text-align:right;\" size=\"28\" al=\"\" maxlength=\"28\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">PCL Priority Code</td>
                                                                        <td><ezf:inputText name="pclPrtyCd_D2" ezfName="pclPrtyCd_D2" value="OQQQ" otherAttr=" size=\"4\" maxlength=\"4\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">LTL Carrier</td>
                                                                        <td><ezf:inputText name="ltlCarrCd_D2" ezfName="ltlCarrCd_D2" value="1234567890123456789012345678901234567890" otherAttr=" size=\"40\" maxlength=\"40\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">LTL Max Capacity</td>
                                                                        <td><ezf:inputText name="ltlMaxCapNum_D2" ezfName="ltlMaxCapNum_D2" value="1234567890123456789012345678" otherAttr=" style=\"text-align:right;\" size=\"28\" maxlength=\"28\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">LTL Priority Code</td>
                                                                        <td><ezf:inputText name="ltlPrtyCd_D2" ezfName="ltlPrtyCd_D2" value="OQQQ" otherAttr=" size=\"4\" maxlength=\"4\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">FTL Carrier</td>
                                                                        <td><ezf:inputText name="tlCarrCd_D2" ezfName="tlCarrCd_D2" value="1234567890123456789012345678901234567890" otherAttr=" size=\"40\" maxlength=\"40\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">FTL Max Capacity</td>
                                                                        <td><ezf:inputText name="tlMaxCapNum_D2" ezfName="tlMaxCapNum_D2" value="1234567890123456789012345678" otherAttr=" style=\"text-align:right;\" size=\"28\" maxlength=\"28\""/></td>
                                                                </tr>
                                                                <tr height="32px">
                                                                        <td class="stab">FTL Priority Code</td>
                                                                        <td><ezf:inputText name="tlPrtyCd_D2" ezfName="tlPrtyCd_D2" value="2010" otherAttr=" size=\"4\" maxlength=\"4\""/></td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
<%-- ######################################## FOOTER ######################################## --%>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <%-- #Ship Via Edit TAB End#  --%>
                                </c:when>
                                </c:choose>

                                <script type="text/javascript">
                                <!--
                                    document.write( "<input type='hidden' name='xxMessageCode' value='<%= databean != null ? databean.getMessageCode() : null %>' >" );
                                    document.write( "<input type='hidden' name='xxMessage'     value='<%= databean != null ? databean.getMessage()     : null %>' >" );
                                -->
                                </script>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</center>



<%-- **** Task specific area ends here **** --%>
