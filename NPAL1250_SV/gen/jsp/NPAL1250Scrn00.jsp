<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160531140013 --%>
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
			<input type="hidden" name="pageID" value="NPAL1250Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Big Deal Setup">

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
                                            <td width="107px" align="center" class="same">Big Deal</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>

                    <div class="pTab_BODY">
                    <!-- ################################################## Search Criteria - [START] ################################################## -->
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 5px">
                            <col width="80" align="left">
                            <col width="82" align="left">
                            <col width="5" align="left">
                            <col width="100" align="left">
                            <col width="240" align="left">
                            <col width="5" align="left">
                            <col width="110" align="left">
                            <col width="190" align="left">
                            <col width="5" align="left">
                            <col width="160" align="right">
                            
                            <!-- 1 -->
                            <tr height="20">
                                <td class="stab">AccountCd(*)</td>
                                <td><ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="1094205" otherAttr=" size=\"8\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                <td>&nbsp;</td>
                                <td class="stab">Customer Name(*)</td>
                                <td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="ARIZONA STATE UNIVERSITY" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                            <!-- 2 -->
                            <tr height="20">
                                <td class="stab">Ship To Cd(*)</td>
                                <td><ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="1094205" otherAttr=" size=\"8\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                <td>&nbsp;</td>
                                <td class="stab">Location Name(*)</td>
                                <td><ezf:inputText name="locNm" ezfName="locNm" value="ARIZONA STATE UNIVERSITY" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
                                <td>&nbsp;</td>
                                <td class="stab">Big Deal Customer#(*)</td>
                                <td><ezf:inputText name="bigDealNum" ezfName="bigDealNum" value="1094205" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                <td>&nbsp;</td>
                                <td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
                            </tr>
                        </table>

                        <hr>

                        <%-- ######################################## DETAIL ######################################## --%>
                        <div>
                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>
                                        <div style="overflow-y:none; height:; overflow-x:none; ">
                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0">
                                                <col width="70" align="center"><!-- Customer Name Cd-->
                                                <col width="250" align="center"><!-- Customer Name -->
                                                <col width="70" align="center"><!-- Account# -->
                                                <col width="250" align="center"><!-- Account# -->
                                                <col width="320" align="center"><!-- Big Deal Customer# -->
                                                <tr height="28">
                                                    <td class="pClothBs">AccountCd</td>
                                                    <td class="pClothBs">Customer Name</td>
                                                    <td class="pClothBs">Ship to Cd</td>
                                                    <td class="pClothBs">Location Name</td>
                                                    <td class="pClothBs">Big Deal Customer#</td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="vertical-align:top;">
                                        <div style="overflow-y:scroll; height:470; overflow-x:none;">
                                            <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" id="A">
                                                <col width="70" align="center"><!-- Customer Name Cd-->
                                                <col width="250" align="center"><!-- Customer Name -->
                                                <col width="70" align="center"><!-- Account# -->
                                                <col width="250" align="center"><!-- Account# -->
                                                <col width="320" align="center"><!-- Big Deal Customer# -->
                                                <ezf:row ezfHyo="A">
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><ezf:inputText name="sellToCustCd_A" ezfName="sellToCustCd_A" value="100001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"60\""/></td>
                                                        <td><ezf:inputText name="dsAcctNm_A" ezfName="dsAcctNm_A" value="TIAA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\" maxlength=\"60\""/></td>
                                                        <td><ezf:inputText name="shipToCustCd_A" ezfName="shipToCustCd_A" value="1330700" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"60\""/></td>
                                                        <td><ezf:inputText name="locNm_A" ezfName="locNm_A" value="TIAA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"33\" maxlength=\"60\""/></td>
                                                        <td><ezf:inputText name="bigDealNum_A" ezfName="bigDealNum_A" value="857937" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"42\" maxlength=\"30\""/></td>
                                                    </tr>
                                                </ezf:row>
                                                <ezf:skip>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="TIAA" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"  class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1330700" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="TIAA" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"  class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="TIAA" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1104360" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="TIAA" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="PROGRESS ENERGY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="ARIZONA STATE UNIVERSITY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="ARIZONA STATE UNIVERSITY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="ARIZONA STATE UNIVERSITY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="ARIZONA STATE UNIVERSITY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="30" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                    <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                                        <td><input type="text" size="8" maxlength="60" value="100001" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="ARIZONA STATE UNIVERSITY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="8" maxlength="60" value="1325895" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="33" maxlength="60" value="ARIZONA STATE UNIVERSITY" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A" class="pPro"></td>
                                                        <td><input type="text" size="42" maxlength="30" value="857937" name="rtlSwhNm_A1" ezfname="rtlSwhNm_A1" ezfhyo="A"></td>
                                                    </tr>
                                                </ezf:skip>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>



<%-- **** Task specific area ends here **** --%>
