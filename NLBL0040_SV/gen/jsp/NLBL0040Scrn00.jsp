<%-- This file was internationalized by the I18NConverer 1.2 automatically. --%>
<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20151005234438 --%>
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
<fmt:setBundle basename="I18N_NLBL0040Scrn00" var="I18N_SCREEN_ID" scope="request" />
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

            <!-- Set Page ID -->
            <input type="hidden" name="pageID" value="NLBL0040Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NLBL0040Scrn00.title" bundle="${I18N_SCREEN_ID}">Trans Lead Time</fmt:message>">
<center>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td>

<%-- ######################################## HEADER ######################################## --%>
                <%-- ###TAB - HEAD --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <%-- ###TAB - BODY --%>
                <div class="pTab_BODY">
                    <table cellpadding="1" cellspacing="3" >
                        <col width="3">
                        <col width="16">
                        <col width="48">
                        <col width="131">
                        <col width="10">
                        <col width="120">
                        <col width="211">
                        <col width="48"> <%-- Rerlesh Button --%>
                        <col width="10">
                        <col width="28">
                        <col width="227">
                        <col width="120">
                        <col width="151">

                        <tr height="30">
            <td />
                            
                        <%-- ###### HEADER - Loc Cd --%>
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Add Start --%>
                        <td class="stab"><ezf:anchor name="xxLinkProt_H2" ezfName="xxLinkProt_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_RetailWarehouse" ><fmt:message key="i18n.NLBL0040Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Loc&nbsp;Cd</fmt:message></ezf:anchor></td>
                        <td><ezf:inputText name="whCd_H2" ezfName="whCd_H2" value="WWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/></td>
                        <td><ezf:inputText name="locNm_H2" ezfName="locNm_H2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" otherAttr=" size=\"40\" maxlength=\"60\" tabindex=\"-1\""/></td>
                        <%-- 2013/05/20 R-OM025 Inventory Transaction Add End --%>
                        <td />
                            <td class="stab"><fmt:message key="i18n.NLBL0040Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Effective Period</fmt:message></td>
                            <td>
                                <ezf:select name="effFromDt_H2" ezfName="effFromDt_H2" ezfCodeName="effFromDt_H1" ezfDispName="xxEdtCdNm_H2" />
                            </td>
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Add Start --%>
                            <td align="right"><ezf:inputButton name="OnClick_Reflesh_EffectivePeriod" value="Refresh" htmlClass="pBtn6" /></td>
                            <%-- 2013/05/20 R-OM025 Inventory Transaction Add End --%>
                            
                            <td />
                            <td class="stab"><fmt:message key="i18n.NLBL0040Scrn00.label.3" bundle="${I18N_SCREEN_ID}">State</fmt:message></td>
                            <td>
                                <ezf:select name="stCd_H2" ezfName="stCd_H2" ezfCodeName="stCd_H1" ezfDispName="stNm_H1" />
                            </td>
                            <td />
                            <td align="right"><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
                        </tr>
                    </table>

                    <hr>

<%-- ######################################## DETAIL ######################################## --%>

                    <table border="0" cellpadding="1" cellspacing="0">
                        <col width="10">
                        <col width="84">
                        <col width="1">
                        <col width="111">
                        <col width="10" align="center">
                        <col width="111">

                        <tr>
                            <td></td>
                            <td class="stab"><fmt:message key="i18n.NLBL0040Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Effective Period</fmt:message></td>
                            <td />
                            <td>
                                <ezf:inputText name="effFromDt_L1" ezfName="effFromDt_L1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_L1', 4 );">
                            </td>
                            <td class="stab">-</td>
                            <td>
                                <ezf:inputText name="effThruDt_L1" ezfName="effThruDt_L1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effThruDt_L1', 4 );">
                            </td>
                        </tr>
                        <tr height="5">
                            <td />
                            <td />
                            <td />
                            <td />
                            <td />
                            <td />
                        </tr>
                    </table>

                    <table border="0" cellpadding="0" cellspacing="0">
                        <col width="10">
                        <col width="500">
                        <col width="120" align="center">
                        <col width="485">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top">

                                <table border="1" cellpadding="0" cellspacing="0" width="500">
                                    <tr height="400" valign="top">
                                        <td>
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                <col width="10">
                                                <col width="460">

                                                <tr height="10">
                                                    <td />
                                                    <td />
                                                </tr>
                                                <tr>
                                                    <td />
                                                    <td>

                                                        <table border="0" cellpadding="1" cellspacing="0">
                                                            <col width="460" align="right">
                                                            <tr>
                                                                <td>
                                                                    <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                                                        <jsp:param name="beanId"   value='<%= request.getParameter( "beanId" ) %>' />
                                                                        <jsp:param name="TableNm"   value="A" />
                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
                                                                        <jsp:param name="ShowingTo"  value="xxPageShowToNum_A1" />
                                                                        <jsp:param name="ShowingOf"  value="xxPageShowOfNum_A1" />
                                                                    </jsp:include>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                        <table border="1" cellpadding="1" cellspacing="0" width="458">
                                                            <col width="34" align="center">
                                                            <col width="244" align="center">
                                                            <col width="84" align="center">
                                                            <col width="72" align="center">

                                                            <tr height="34">
                                                                <td class="pClothBs">&nbsp;</td>
                                                                <td class="pClothBs"><fmt:message key="i18n.NLBL0040Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Destination State</fmt:message></td>
                                                                <td class="pClothBs"><fmt:message key="i18n.NLBL0040Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Shipping<br>Mode</fmt:message></td>
                                                                <td class="pClothBs"><fmt:message key="i18n.NLBL0040Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Lead Time<br>(day(s))</fmt:message></td>
                                                            </tr>
                                                        </table>

                                                        <div id="leftTBL" style="overflow-y:scroll; height:367;">
                                                            <table border="1" cellpadding="1" cellspacing="0" width="458" id="A">
                                                                <col width="34" align="center">
                                                                <col width="244" align="left">
                                                                <col width="84" align="left">
                                                                <col width="72" align="right">

                                                                <ezf:row ezfHyo="A">
                                                                    <tr height="28">
                                                                        <td><ezf:inputRadio name="xxRadioBtn_A1" ezfName="xxRadioBtn_A1" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" /></td>
                                                                        <td><ezf:label name="stNm_A1" ezfName="stNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td><ezf:label name="shpgModeNm_A1" ezfName="shpgModeNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                        <td><ezf:inputText name="delyLeadAot_A1" ezfName="delyLeadAot_A1" value="12" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"2\" maxlength=\"2\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="2"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>LTL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="3"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>TL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="10"></td>
                                                                        <td><label ezfout>ALBERTA</label></td>
                                                                        <td><label ezfout>Parcel</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="11"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>LTL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="12"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>TL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="10"></td>
                                                                        <td><label ezfout>ALASKA</label></td>
                                                                        <td><label ezfout>Parcel</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="11"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>LTL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="12"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>TL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="10"></td>
                                                                        <td><label ezfout>ALABAMA</label></td>
                                                                        <td><label ezfout>Parcel</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="11"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>LTL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="12"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>TL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="10"></td>
                                                                        <td><label ezfout>ARKANSAS</label></td>
                                                                        <td><label ezfout>Parcel</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="11"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>LTL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="12"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>TL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="10"></td>
                                                                        <td><label ezfout>AMERICAN SOMOA</label></td>
                                                                        <td><label ezfout>Parcel</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="11"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>LTL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>
                                                                    <tr height="28">
                                                                        <td><input type="radio" name="selectRadio" value="12"></td>
                                                                        <td><label ezfout>&nbsp;</label></td>
                                                                        <td><label ezfout>TL</label></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
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
                            </td>

                            <td>
                                <table>
                                    <tr>
                                        <td align="center" valign="middle">
                                            <ezf:inputButton name="OnClick_Detail" value=">> Detail >>" htmlClass="pBtn8" />
                                        </td>
                                    </tr>
                                </table>
                            </td>

                            <td valign="top">
                                <table border="1" cellpadding="0" cellspacing="0" width="485">
                                    <tr height="400" valign="top">
                                        <td>
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                <col width="10">
                                                <col width="">

                                                <tr height="10">
                                                    <td />
                                                    <td />
                                                </tr>

                                                <tr>
                                                    <td />
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                            <col width="101" align="left">
                                                            <col width="200" align="left">
                                                            <tr>
                                                                <td class="stab"><fmt:message key="i18n.NLBL0040Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Destination State</fmt:message></td>
                                                                <td class="pOut"><ezf:label name="stNm_R1" ezfName="stNm_R1" /></td>
                                                            </tr>
                                                        </table>

                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                            <col width="101" align="left">
                                                            <col width="48" align="left">
                                                            <tr>
                                                                <td class="stab"><fmt:message key="i18n.NLBL0040Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Shipping Mode</fmt:message></td>
                                                                <td class="pOut"><ezf:label name="shpgModeNm_R1" ezfName="shpgModeNm_R1" /></td>
                                                            </tr>
                                                        </table>

                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                            <col width="101" align="left">
                                                            <col width="24" align="right">
                                                            <tr>
                                                                <td class="stab"><fmt:message key="i18n.NLBL0040Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Lead Time(day(s))</fmt:message></td>
                                                                <td class="pOut"><ezf:label name="delyLeadAot_R1" ezfName="delyLeadAot_R1" /></td>
                                                            </tr>
                                                        </table>

                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                            <tr height="10">
                                                                <td></td>
                                                            </tr>
                                                        </table>

                                                        <table border="0" cellpadding="1" cellspacing="0">
                                                            <col width="446" align="right">
                                                            <tr>
                                                                <td>
                                                                    <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                                                        <jsp:param name="beanId"   value='<%= request.getParameter( "beanId" ) %>' />
                                                                        <jsp:param name="TableNm"   value="B" />
                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_B1" />
                                                                        <jsp:param name="ShowingTo"  value="xxPageShowToNum_B1" />
                                                                        <jsp:param name="ShowingOf"  value="xxPageShowOfNum_B1" />
                                                                    </jsp:include>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                        <table border="1" cellpadding="1" cellspacing="0" width="446">
                                                            <col width="34" align="center">
                                                            <col width="128" align="center">
                                                            <col width="128" align="center">
                                                            <col width="72" align="center">

                                                            <tr height="34">
                                                                <td class="pClothBs">&nbsp;</td>
                                                                <td class="pClothBs"><fmt:message key="i18n.NLBL0040Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Post Code From</fmt:message></td>
                                                                <td class="pClothBs"><fmt:message key="i18n.NLBL0040Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Post Code To</fmt:message></td>
                                                                <td class="pClothBs"><fmt:message key="i18n.NLBL0040Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Lead Time<br>(day(s))</fmt:message></td>
                                                            </tr>
                                                        </table>

                                                        <div style="overflow-y:scroll; overflow-x:none; height:275; width:463">
                                                            <table border="1" cellpadding="1" cellspacing="0" width="446" id="B">
                                                                <col width="34" align="center">
                                                                <col width="128" align="right">
                                                                <col width="128" align="right">
                                                                <col width="72" align="right">

                                                                <ezf:row ezfHyo="B">
                                                                    <tr height="28">
                                                                        <td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
                                                                        <td><ezf:inputText name="fromZipCd_B1" ezfName="fromZipCd_B1" value="123456789012345" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
                                                                        <td><ezf:inputText name="toZipCd_B1" ezfName="toZipCd_B1" value="123456789012345" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
                                                                        <td><ezf:inputText name="trnspLtAot_B1" ezfName="trnspLtAot_B1" value="12" ezfHyo="B" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"2\" maxlength=\"2\""/></td>
                                                                    </tr>
                                                                </ezf:row>
                                                                <ezf:skip>
                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value="14811"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value="14811"></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="2"></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value="14812"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value="14815"></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value="1"></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                    <tr height="28">
                                                                        <td><input type="checkbox" value="Y"></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" size="15" maxlength="15" value=""></td>
                                                                        <td><input type="text" class="pHsu" style="text-align:right" size="2" maxlength="2" value=""></td>
                                                                    </tr>

                                                                </ezf:skip>
                                                            </table>
                                                        </div>

                                                        <table border="0" cellpadding="0" cellspacing="0" width="446">
                                                            <col width="100">
                                                            <col width="346">
                                                            <tr height="28">
                                                                <td align="left"><ezf:inputButton name="OnClick_DeleteRow" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"btn_deleteRow\""/></td>
                                                                <td align="right"><ezf:inputButton name="OnClick_InsertRow" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"btn_InsertRow\""/></td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                    <table border="0" cellpadding="1" cellspacing="1" align="left">
                        <col width="5">
                        <col width="100">
                        <tr height="5">
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><ezf:inputButton name="OnClick_Upload" value="Upload" htmlClass="pBtn6" otherAttr=" id=\"btn_Upload\""/></td>
                        </tr>
                    </table>
                    <br>
                    <br>
                </div>

<%-- ######################################## FOOTER ######################################## --%>
                <table border="0" cellpadding="5" cellspacing="1">
                    <col width="">
                    <col width="">
                    <col width="">
                    <col width="">
                    <col width="444">

                    <tr>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
