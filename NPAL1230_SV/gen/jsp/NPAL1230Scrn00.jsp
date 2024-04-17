<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230201131622 --%>
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
			<input type="hidden" name="pageID" value="NPAL1230Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="ASL Entry">

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
                                            <td width="107px" align="center" class="same">ASL Enty</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                        <!-- ################################################## Header - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                        
                            <col width=""  align="left">
                            <col width=""  align="left">
                            <tr>
                                <td valign="top">
                                    <table border="0">
                                        <col width="60" align="left">
                                        <col width=""   align="left">
                                        <!-- 1 -->
                                        <tr height="20">
                                            <td class="stab"><ezf:anchor name="xxLinkAncr_AN" ezfName="xxLinkAncr_AN" ezfEmulateBtn="1" ezfGuard="OpenWin_ASLName" >ASL Name</ezf:anchor></td>
                                            <td colspan="3"><ezf:inputText name="aslNm" ezfName="aslNm" value="AZERTY MAIN,  AZERTY BIG DEAL 11111 ETC" otherAttr=" size=\"60\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                        </tr>
                                        <!-- 2 -->
                                        <tr height="20">
                                            <td class="stab"><ezf:anchor name="xxLinkAncr" ezfName="xxLinkAncr" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" >Supplier</ezf:anchor></td>
                                            <td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="9125" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputButton name="SetSupplierName" value=">>" /></td>
                                            <td><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="AZERTY Site01" otherAttr=" size=\"35\" maxlength=\"60\" tabindex=\"-1\""/></td>
                                        </tr>
                                    </table>
                                </td>
                                <td valign="top">
                                    <table border="0">
                                        <col width="60" align="left">
                                        <col width=""   align="left">
                                        <col width="60" align="left">
                                        <col width=""   align="left">
                                        <col width="10"   align="left">
                                        <col width="30"   align="left">
                                        <col width="20"   align="center">
                                        <!-- 1 -->
                                        <tr height="20">
                                            <td class="stab">Start Date</td>
                                            <td>
                                                <ezf:inputText name="aslStartDt" ezfName="aslStartDt" value="05/20/2014" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('aslStartDt', 4);"/>
                                            </td>
                                            <td class="stab">End Date</td>
                                            <td>
                                                <ezf:inputText name="aslEndDt" ezfName="aslEndDt" value="05/20/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('aslEndDt', 4);"/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <!-- 2 -->
                                        <tr height="20">
                                            <td class="stab">Description</td>
                                            <td colspan="3">
                                                <ezf:inputText name="aslDescTxt" ezfName="aslDescTxt" value="Azerty Main Supplier List" otherAttr=" size=\"50\" maxlength=\"60\""/>
                                            </td>
                                            <td></td>
                                            <td class="stab">Qualifier</td>
                                            <td class="pOut">
                                                <ezf:anchor name="xxYesNoCd" ezfName="xxYesNoCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Qualifier" >
                                                    <ezf:label name="xxYesNoCd" ezfName="xxYesNoCd" />
                                                </ezf:anchor>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Header - [E N D] ################################################## -->

                        <hr>

                        <!-- ################################################## Detail   - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                            <tr>
                                <td valign="top">
                                    <table border="0" cellpadding="0" cellspacing="0" >
                                        <col width="100" align="left">
                                        <col width="120"    align="left">
                                        <col width="30"    align="center">
                                        <col width="150"    align="left">
                                        <!-- 1 -->
                                        <tr height="20">
                                            <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" >Item Number(*)</ezf:anchor></TD>
                                            <td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="EPST513011" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputButton name="SetItemDescription" ezfName="Display_MDSEName" value=">>" /></td>
                                            <td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="EPSON, OEM, MAGENTA INK, T513011, QTY 1" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\""/></td>
                                        </tr>
                                        <!-- 2 -->
                                        <tr height="20">
                                            <td class="stab">Active Lines Only</td>
                                            <td colspan="3"><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
                                        </tr>
                                    </table>
                                </td>
                                <td valign="top">
                                    <table border="0" cellpadding="0" cellspacing="0" >
                                        <col width="100" align="left">
                                        <col width="160" align="left">
                                        <!-- 1 -->
                                        <tr height="20">
                                            <td class="stab">Supplier Item(*)</td>
                                            <td><ezf:inputText name="splyItemNum" ezfName="splyItemNum" value="161132" otherAttr=" style=\"width:160px;\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                        </tr>
                                        <!-- 2 -->
                                        <tr>
                                            <td class="stab">Merchandise Type</td>
                                            <td>
                                                <ezf:select name="coaMdseTpCd_SL" ezfName="coaMdseTpCd_SL" ezfBlank="1" ezfCodeName="coaMdseTpCd_PD" ezfDispName="coaProjDescTxt_PD" otherAttr=" style=\"width:160px;\""/>
                                            </td>
                                    </table>
                                </td>
                                <td valign="top">
                                    <table border="0" cellpadding="0" cellspacing="0" >
                                        <col width="429"  align="left">
                                        <!-- 1 -->
                                        <tr height="20">
                                            <td></td>
                                        </tr>
                                        <!-- 2 -->
                                        <tr>
                                            <td style="text-align: right;">
                                                <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
                                            </td>
                                    </table>
                                </td>
                            </tr>
                        </table>

                        <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 5px;">
                            <col width="795"  align="left">
                            <col width="293"  align="right">
                            <tr>
                                <td>
                                    <ezf:inputButton name="OnClick_CheckAll" value="CheckAll" htmlClass="pBtn6" />&nbsp;
                                    <ezf:inputButton name="OnClick_UnCheck" value="UnCheck" htmlClass="pBtn6" />&nbsp;
                                    <ezf:inputButton name="OnClick_InsertRow" value="Insert Row" htmlClass="pBtn6" />&nbsp;
                                    <ezf:inputButton name="OnClick_DeleteRow" value="Delete Row" htmlClass="pBtn6" />
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
                        </table>
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="left" valign="top">
                                    <%-- ******************************* --%>
                                    <%-- *** Left Table Area(Header) *** --%>
                                    <%-- ******************************* --%>
                                    <table style="table-layout:fixed; margin-left: 5px;" border="1" cellpadding="1" cellspacing="0" height="46">
                                        <col width="40"  align="center"><!-- checkbox -->
                                        <col width="180" align="center"><!-- Item Number -->
                                        <col width="120" align="center"><!-- Item Description -->
                                        <col width="50"  align="center"><!-- Primary Supplier -->
                                        <col width="120"  align="center"><!-- Mdse Type -->
                                        <col width="100"  align="center"><!-- UOM -->
                                        <col width="110" align="center"><!-- Supplier Item -->
                                        <tr>
                                            <td class="pClothBs">#</td>
                                            <td class="pClothBs">Item Number</td>
                                            <td class="pClothBs">Item Description</td>
                                            <td class="pClothBs">Primary<br>Supplier</td>
                                            <td class="pClothBs">Merchandise Type</td>
                                            <td class="pClothBs">UOM</td>
                                            <td class="pClothBs">Supplier Item</td>
                                        </tr>
                                    </table>
                                    <%-- ******************************* --%>
                                    <%-- *** Left Table Area(Detail) *** --%>
                                    <%-- ******************************* --%>
                                    <div id="LeftTBL" style="overflow-y:hidden; height:310; overflow-x:hidden; width:; margin-left: 5px;"
                                        onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                            <col width="40"  align="center"><!-- checkbox -->
                                            <col width="180" align="center"><!-- Item Number -->
                                            <col width="120" align="center"><!-- Item Description -->
                                            <col width="50"  align="center"><!-- Primary Supplier -->
                                            <col width="120"  align="center"><!-- Mdse Type -->
                                            <col width="100"  align="center"><!-- UOM -->
                                            <col width="110" align="center"><!-- Supplier Item -->
                                            <ezf:row ezfHyo="A">
                                                <tr height="28">
                                                    <td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
                                                    <td>
                                                        <ezf:inputButton name="OpenWin_Item" value="..." ezfHyo="A" ezfArrayNo="0" />
                                                        <ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="0436V975" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"30\" ezftoupper=\"\""/>
                                                        <ezf:inputButton name="GetItemInfo" value=">>" ezfHyo="A" ezfArrayNo="0" />
                                                    </td>
                                                    <td><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="HP Q2682" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"70\""/></td>
                                                    <td><ezf:inputCheckBox name="primSplyFlg_A" ezfName="primSplyFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    <td><ezf:inputText name="xxComnScrFirstValTxt_A" ezfName="xxComnScrFirstValTxt_A" value="30:SUPPLY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"70\""/></td>
                                                    <td>
                                                        <ezf:select name="vndUomCd_A" ezfName="vndUomCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="vndUomCd_PD" ezfDispName="vndUomDescTxt_PD" otherAttr=" ezftoupper=\"\""/>
                                                    </td>
                                                    <td><ezf:inputText name="splyItemNum_A" ezfName="splyItemNum_A" value="HEW Q2682" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\" id=\"splyItemNum_A#{EZF_ROW_NUMBER}\""/></td>
                                                </tr>
                                            </ezf:row>
                                            <ezf:skip>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V976" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"  ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2683" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2683" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="2368V116" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="MOP 8.5 X 11 PCW FLAMBEAU PAPER, SPZMFLM3011, 5000 SHEETS/CARTON" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option>Each</option>
                                                            <option selected>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="SPZMFLM3011" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V978" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2685" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2685" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V979" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2686" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2686" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V980" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2687" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2687" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V981" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2688" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2688" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V982" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2689" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2689" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V983" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2690" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2690" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V984" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2691" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2691" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V985" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2692" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2692" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" size="10" maxlength="30" value="0436V986" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="HP Q2693" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="checkbox" value="Y" name="xxChkBox_R1" ezfname="xxChkBox_R1" ></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="70" value="30:SUPPLY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td>
                                                        <select name="xxSrchNm_SE" ezfname="xxSrchNm_SE" ezflist="xxSrchNm_CD,xxSrchNm_DI,99, ,blank" ezfhyo="A" ezftoupper class="pHsu">
                                                            <option></option>
                                                            <option selected>Each</option>
                                                            <option>Box</option>
                                                            <option>Carton</option>
                                                            <option>Pallet</option>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" size="12" maxlength="30" value="HEW Q2693" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                </tr>
                                            </ezf:skip>
                                        </table>
                                    </div>
                                </td>
                                <td valign="top">
                                    <%-- ******************************** --%>
                                    <%-- *** Right Table Area(Header) *** --%>
                                    <%-- ******************************** --%>
                                    <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:373;">
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="46" width="700">
                                            <col width="180" align="center"><!-- Primary Supplier Site -->
                                            <col width="100" align="center"><!-- Supplier Site Name -->
                                            <col width="100" align="center"><!-- PO Price -->
                                            <col width="120" align="center"><!-- Effectivity Dates -->
                                            <col width="120" align="center"><!-- Effectivity Dates -->
                                            <col width="235"  align="center"><!-- Comment -->
                                            <col width="100" align="center"><!-- Vendor UOM Qty -->
                                            <col width="100" align="center"><!-- Base Qty -->
                                            <col width="100" align="center"><!-- Incr Ord Qty -->
                                            <col width="100" align="center"><!-- Min Ord Qty -->
                                            <col width="100" align="center"><!-- Lead Time -->
                                            <!-- START 2023/01/25 S.Dong [QC#60966, ADD] -->
                                            <col width="100" align="center"><!-- Vendor Ship Lead Time -->
                                            <!-- END 2023/01/25 S.Dong [QC#60966, ADD] -->
                                            <tr>
                                                <td class="pClothBs" rowspan="2">Primary Supplier Site</td>
                                                <td class="pClothBs" rowspan="2">Supplier Site Name</td>
                                                <td class="pClothBs" rowspan="2">PO Price</td>
                                                <td class="pClothBs" colspan="2">Effectivity Dates</td>
                                                <td class="pClothBs" rowspan="2">Comment</td>
                                                <td class="pClothBs" rowspan="2">Vnd UOM Qty</td>
                                                <td class="pClothBs" rowspan="2">Base Qty</td>
                                                <td class="pClothBs" rowspan="2">Incr Ord Qty</td>
                                                <td class="pClothBs" rowspan="2">Min Ord Qty</td>
                                                <td class="pClothBs" rowspan="2">Lead Time</td>
                                                <!-- START 2023/01/25 S.Dong [QC#60966, ADD] -->
                                                <td class="pClothBs" rowspan="2">Vendor Ship Lead Time</td>
                                                <!-- END 2023/01/25 S.Dong [QC#60966, ADD] -->
                                            </tr>
                                            <tr>
                                                <td class="pClothBs">From</td>
                                                <td class="pClothBs">to</td>
                                            </tr>
                                        </table>
                                    </div>
                                    <%-- ******************************** --%>
                                    <%-- *** Right Table Area(Detail) *** --%>
                                    <%-- ******************************** --%>
                                    <div id="RightTBL" style="overflow-y:scroll; height:327; overflow-x:scroll; width:390;" 
                                        onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="700">
                                            <col width="180" align="center"><!-- Primary Supplier Site -->
                                            <col width="100" align="center"><!-- Supplier Site Name -->
                                            <col width="100" align="center"><!-- PO Price -->
                                            <col width="120" align="center"><!-- Effectivity Dates -->
                                            <col width="120" align="center"><!-- Effectivity Dates -->
                                            <col width="235" align="center"><!-- Comment -->
                                            <col width="100" align="center"><!-- Vendor UOM Qty -->
                                            <col width="100" align="center"><!-- Base Qty -->
                                            <col width="100" align="center"><!-- Incr Ord Qty -->
                                            <col width="100" align="center"><!-- Min Ord Qty -->
                                            <col width="100" align="center"><!-- Lead Time -->
                                            <!-- START 2023/01/25 S.Dong [QC#60966, ADD] -->
                                            <col width="100" align="center"><!-- Vendor Ship Lead Time -->
                                            <!-- END 2023/01/25 S.Dong [QC#60966, ADD] -->
                                            <ezf:row ezfHyo="A">
                                                <tr id="id_row_{EZF_ROW_NUMBER}" height="28">
                                                    <td>
                                                        <ezf:inputButton name="OpenWin_Supplier" value="Site" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" />
                                                        <ezf:inputText name="vndCd_A" ezfName="vndCd_A" value="952418" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" id=\"vndCd_A#{EZF_ROW_NUMBER}\""/>
                                                        <ezf:inputButton name="GetSupplierSiteName" value=">>" ezfHyo="A" ezfArrayNo="0" />
                                                    </td>
                                                    <td><ezf:inputText name="locNm_A" ezfName="locNm_A" value="XXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
                                                    <td><ezf:inputText name="unitPrcAmt_A" ezfName="unitPrcAmt_A" value="10.50" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                    <td>
                                                        <ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="05/22/2014" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" ezftoupper=\"\""/>
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');"/>
                                                    </td>
                                                    <td>
                                                        <ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="05/22/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/>
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');"/>
                                                    </td>
                                                    <td><ezf:inputText name="aslItemCmntTxt_A" ezfName="aslItemCmntTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
                                                    <td><ezf:inputText name="vndUomQty_A" ezfName="vndUomQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"10\""/></td>
                                                    <td><ezf:inputText name="baseOrdQty_A" ezfName="baseOrdQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"10\""/></td>
                                                    <td><ezf:inputText name="vndIncrOrdQty_A" ezfName="vndIncrOrdQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"10\""/></td>
                                                    <td><ezf:inputText name="vndMinOrdQty_A" ezfName="vndMinOrdQty_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"10\" id=\"vndMinOrdQty_A#{EZF_ROW_NUMBER}\""/></td>
                                                    <td><ezf:inputText name="vndLtDaysNum_A" ezfName="vndLtDaysNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"10\" id=\"vndLtDaysNum_A#{EZF_ROW_NUMBER}\""/></td>
                                                    <!-- START 2023/01/25 S.Dong [QC#60966, ADD] -->
                                                    <td><ezf:inputText name="vndShipLtDaysNum_A" ezfName="vndShipLtDaysNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align: right;\" size=\"10\" maxlength=\"10\" id=\"vndShipLtDaysNum_A#{EZF_ROW_NUMBER}\""/></td>
                                                    <!-- END 2023/01/25 S.Dong [QC#60966, ADD] -->
                                                </tr>
                                            </ezf:row>
                                            <ezf:skip>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/20/2013" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2014" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/20/2013" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2014" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/20/2013" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2014" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/20/2013" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2014" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/20/2013" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2014" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                                <tr height="28">
                                                    <td><input type="text" size="12" maxlength="30" value="952418" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    <td><input type="text" class="pPro"size="15" maxlength="60" value="XXXXXXX" name="locNm_A" ezfname="locNm_A" ezfhyo="A"></td>
                                                    <td><input type="text" size="12" maxlength="30" value="20.50" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu"></td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/20/2013" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" ezftoupper class="pHsu">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" size="12" maxlength="30" value="05/22/2015" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A">
                                                        <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_H1', 4);"/>
                                                    </td>
                                                    <td><input type="text" size="30" maxlength="60" value="" name="rtlSwhDescTxt_A1" ezfname="rtlSwhDescTxt_A1" ezfhyo="A"></td>
                                                </tr>
                                            </ezf:skip>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <br>

                        <!-- ################################################## Upload - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
                            <col width="1080"  align="left">
                            <tr>
                                <td valign="top">

                                    <table border="0">
                                        <col width="115" align="left">
                                        <col width=""    align="left">
                                        <col width="5"   align="left">
                                        <col width=""    align="left">
                                        <tr height="20">
                                            <td class="stab">
                                                <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_TemplateDownload" >Upload Request</ezf:anchor>
                                            
                                            </td>
                                            <td><ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\""/></td>
                                            <td></td>
                                            <td><ezf:inputButton name="OnClick_Upload" value="Upload" htmlClass="pBtn6" /></td>
                                        </tr>
                                    </table>

                                </td>
                            </tr>
                        </table>
                        <!-- ################################################## Upload - [E N D] ################################################## -->


                        <!-- ################################################## Search Result   - [E N D] ################################################## -->
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>



<%-- **** Task specific area ends here **** --%>
