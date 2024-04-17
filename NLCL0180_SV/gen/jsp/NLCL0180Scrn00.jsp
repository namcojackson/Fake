<%-- This file was internationalized by the I18NConverer 1.0 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20131120140421 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NLCL0180Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

            <!-- Set Page ID  -->
            <input type="hidden" name="pageID" value="NLCL0180Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NLCL0180Scrn00.title" bundle="${I18N_SCREEN_ID}">Receiving Entry by Item(SP)</fmt:message>">


<center>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
<%-- ######################################## HEADER ######################################## --%>
                <%-- include S21BusinessProcessTAB --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                
                <div class="pTab_BODY">
                    <table height="528" width="100%">
                        <col valign="top">
                        <tr>
                            <td>
                                <table width="100%" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td>
<%-- ## HEADER 1 ## --%>
                                            <table border="0">
                                                <%-- 2013/05/13 R-OM025 Inventory Transaction Mod Start --%>
                                                <%-- <col width="62"> --%>
                                                <%-- <col width="330"> --%>
                                                <%-- <col width="30"> --%>
                                                <%-- <col width="45"> --%>
                                                <%-- <col width="128"> --%>
                                                <%-- <col width="30"> --%>
                                                <%-- <col width="62"> --%>
                                                <%-- <col width="111"> --%>
                                                <%-- <col width="30"> --%>
                                                <%-- <col width="45"> --%>
                                                <%-- <col width="62"> --%>
                                                <%-- <col width="30"> --%>
                                                <%-- <col width="168"> --%>

                                                <col width="40">
                                                <col width="48">
                                                <col width="220">
                                                <col width="48">
                                                <col width="45">
                                                <col width="112">
                                                <col width="56">
                                                <col width="62">
                                                <col width="56">
                                                <col width="56">
                                                <col width="42">
                                                <col width="58">
                                                <col width="30">
                                                <col width="150">
                                                <%-- 2013/05/13 R-OM025 Inventory Transaction Mod End --%>
                                                <tr>
                                                    <%-- 2013/05/13 R-OM025 Inventory Transaction Mod Start --%>
                                                        <%-- <td class="stab">Loc Cd</td> --%>
                                                        <%-- <td> --%>
                                                        <%-- <ezf:select name="whCd_P1" ezfName="whCd_P1" ezfCodeName="whCd_H1" ezfDispName="xxLocTxt_H1" otherAttr=" style=\"width:267px;\""/> --%>
                                                        <%-- <td> --%>
                                                    <td class="stab"><ezf:anchor name="whCd_LK" ezfName="whCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_NPAL1010" ><fmt:message key="i18n.NLCL0180Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Loc Cd</fmt:message></ezf:anchor></td>
                                                    <td><ezf:inputText name="whCd_P1" ezfName="whCd_P1" value="WWWWWWWWW0WWWWWWWWW1" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                    <td><ezf:inputText name="locNm_P1" ezfName="locNm_P1" value="WWWWWWWWW0WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW1" otherAttr=" size=\"30\" maxlength=\"60\""/></td>
                                                    <%-- 2013/05/13 R-OM025 Inventory Transaction Add End --%>
                                                    </td>
                                                    <td></td>
                                                    <td class="stab"><fmt:message key="i18n.NLCL0180Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Invoice#</fmt:message></td>
                                                    <td><ezf:inputText name="apVndInvNum" ezfName="apVndInvNum" value="WWWWWWWWWWWWWWW" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
                                                    <td></td>
                                                    <td class="stab"><fmt:message key="i18n.NLCL0180Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Arrive Date</fmt:message></td>
                                                    <td>
                                                        <ezf:inputText name="arvDt" ezfName="arvDt" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
                                                    </td>
                                                    <td></td>
                                                    <td class="stab"><ezf:anchor name="vndCd_LK" ezfName="vndCd_LK" ezfEmulateBtn="1" ezfGuard="Open_NMAL6050" ><fmt:message key="i18n.NLCL0180Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Vendor</fmt:message></ezf:anchor></td>
                                                    <td><ezf:inputText name="vndCd" ezfName="vndCd" value="17071" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
                                                    <td><ezf:inputButton name="Display_VendorName" value=">>" htmlClass="pBtn0" /></td>
                                                    <td><ezf:inputText name="vndNm" ezfName="vndNm" value="CANON INC." otherAttr=" size=\"20\" maxlength=\"20\" tabindex=\"-1\""/></td>
                                                    
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <hr>
<%-- ######################################## DETAIL ######################################## --%>
                                <table cellpadding="0" cellspacing="0">
                                    <col width="90">
                                    <col width="">
                                    <tr>
                                        <td></td>
                                        <td align="right">
                                            <table cellpadding="1" cellspacing="0">
                                                <col width="198" align="right">
                                                <col width="248">
                                                <col width="5">
                                                <col width="52">
                                                <col width="112">
                                                <col width="5">
                                                <col width="55">
                                                <col width="112">
                                                <col width="72">
                                                <col width="13">
                                                <tr>
                                                    <td>
                                                        <table cellpadding="0" cellspacing="0">
                                                            <col width="51">
                                                            <col width="104">
                                                            <col width="30">
                                                            <tr>
                                                                <td class="stab"><ezf:anchor name="mdseCd_LK" ezfName="mdseCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_NMAL6030" ><fmt:message key="i18n.NLCL0180Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Mdse Cd</fmt:message></ezf:anchor></td>
                                                                <td><ezf:inputText name="mdseCd" ezfName="mdseCd" value="2534B004AA" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                                <td><ezf:inputButton name="Display_MDSEName" value=">>" htmlClass="pBtn0" /></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td><ezf:inputText name="mdseNm" ezfName="mdseNm" value="INK JET PRINTER" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\""/></td>
                                                    <td></td>
                                                    <td class="stab"><fmt:message key="i18n.NLCL0180Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Good Qty</fmt:message></td>
                                                    <td><ezf:inputText name="xxRqstQty_H1" ezfName="xxRqstQty_H1" value="1,234,567,890" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
                                                    <td></td>
                                                    <td class="stab"><fmt:message key="i18n.NLCL0180Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Defect Qty</fmt:message></td>
                                                    <td><ezf:inputText name="xxRqstQty_H2" ezfName="xxRqstQty_H2" value="1,234,567,890" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
                                                    <td><ezf:inputButton name="Add_Dtail_Line" value="Add" htmlClass="pBtn6" /></td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                                <table cellpadding="0" cellspacing="0">
                                    <col width="130">
                                    <col width="">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <table cellpadding="1" cellspacing="0">
                                                <col width="">
                                                <tr>
                                                    <td valign="top">
                                                        <div style="overflow-y:none; overflow-x:hidden; width:811;">
                                                            <table border="1" cellpadding="1" cellspacing="0" width="794">
                                                                <col width="40"  align="center">
                                                                <col width="48" align="center">
                                                                <col width="126" align="center">
                                                                <col width="320" align="center">
                                                                <col width="112" align="center">
                                                                <col width="112" align="center">
                                                                <tr height="28">
                                                                    <td class="pClothBs"><fmt:message key="i18n.NLCL0180Scrn00.label.8" bundle="${I18N_SCREEN_ID}">D</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NLCL0180Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Line</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NLCL0180Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Mdse Cd</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NLCL0180Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Merchandise Name</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NLCL0180Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Good Qty</fmt:message></td>
                                                                    <td class="pClothBs"><fmt:message key="i18n.NLCL0180Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Defect Qty</fmt:message></td>
                                                                </tr>
                                                            </table>
                                                        </div>
    	                                                <%-- 2013/05/13 R-OM025 Inventory Transaction Mod Start --%>
                                                        <%-- <div style="overflow-y:auto; height:444;"> --%>
                                                        <div style="overflow-y:auto; height:458;">
	                                                    <%-- 2013/05/13 R-OM025 Inventory Transaction Mod End --%>
                                                            <table border="1" cellpadding="1" cellspacing="0" width="794" id="A">
                                                                <col width="40" align="center">
                                                                <col width="48" align="right">
                                                                <col width="126">
                                                                <col width="320" align="center">
                                                                <col width="112" align="center">
                                                                <col width="112" align="center">
                                                                <ezf:row ezfHyo="A">
                                                                <tr>
                                                                    <td>
                                                                        <ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" />
                                                                    </td>
                                                                    <td><ezf:label name="itemLineNum_A1" ezfName="itemLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                    <td><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                    <td><ezf:inputText name="mdseNm_A1" ezfName="mdseNm_A1" value="PSA1000IS(BLN)" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"39\" maxlength=\"30\" tabindex=\"-1\""/></td>
                                                                    <td><ezf:inputText name="xxRqstQty_A1" ezfName="xxRqstQty_A1" value="3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
                                                                    <td><ezf:inputText name="xxRqstQty_A2" ezfName="xxRqstQty_A2" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
                                                                </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>123</label></td>
                                                                    <td><label ezfout>WWWWWWWWWWWWWWWW</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3"></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value="1,234,567,890"></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value="1,234,567,890"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    </td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                </tr>
                                                                <tr class="pEvenNumberBGcolor">
                                                                    <td>
                                                                        <input type="checkbox" value="Y">
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><label ezfout>&nbsp;</label></td>
                                                                    <td><input type="text" readonly class="pPro" size="39" maxlength="30" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
                                                                    <td><input type="text" class="pHsu pTxtR" size="13" maxlength="13" value=""></td>
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
<%-- ######################################## FOOTER ######################################## --%>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
