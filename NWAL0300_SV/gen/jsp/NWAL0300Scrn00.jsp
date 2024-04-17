<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20091003041418 --%>
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
<fmt:setBundle basename="I18N_NWAL0300Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
            <input type="hidden" name="pageID" value="NWAL0300Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NWAL0300Scrn00.title" bundle="${I18N_SCREEN_ID}">Error Detail Pop Up</fmt:message>">
            
<center>
    <table>
        <tr>
            <td>
                
<%-- ######################################## HEADER ######################################## --%>
                <br>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="88">
                    <col width="420">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Reviewer</fmt:message></td>
                        <td class="pOut"><ezf:label name="rvwPsnCd" ezfName="rvwPsnCd" /></td>
                        <td><ezf:inputText name="rvwPsnNm" ezfName="rvwPsnNm" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"60\" maxlength=\"90\""/></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="88">
                    <col width="73">
                    <col width="231">
                    <col width="85">
                    <col width="84">

                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Order Number</fmt:message></td>
                        <td class="pOut"><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" /></td>
                        <td class="pOut"><ezf:label name="xxDtlSubNum" ezfName="xxDtlSubNum" /></td>
                        <td></td>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Review Date</fmt:message></td>
                        <td class="pOut"><ezf:label name="rvwDt" ezfName="rvwDt" /></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="166">
                    <col width="232">
                    <col width="85">
                    <col width="84">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Order Type</fmt:message></td>
                        <td class="pOut"><ezf:label name="cpoOrdTpNm" ezfName="cpoOrdTpNm" /></td>
                        <td></td>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Screen Date</fmt:message></td>
                        <td class="pOut"><ezf:label name="hldDt" ezfName="hldDt" /></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="88">
                    <col width="488">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Sell-To Customer</fmt:message></td>
                        <td class="pOut"><ezf:label name="sellToCustCd" ezfName="sellToCustCd" /></td>
                        <td><ezf:inputText name="xxLocAddrNm_H1" ezfName="xxLocAddrNm_H1" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"60\" maxlength=\"121\""/></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="88">
                    <col width="484">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Bill-To Customer</fmt:message></td>
                        <td class="pOut"><ezf:label name="billToCustCd" ezfName="billToCustCd" /></td>
                        <td><ezf:inputText name="xxLocAddrNm_H2" ezfName="xxLocAddrNm_H2" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"60\" maxlength=\"121\""/></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="88">
                    <col width="484">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Ship-To Customer</fmt:message></td>
                        <td class="pOut"><ezf:label name="shipToCustCd" ezfName="shipToCustCd" /></td>
                        <td><ezf:inputText name="xxLocAddrNm_H3" ezfName="xxLocAddrNm_H3" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW60" otherAttr=" size=\"60\" maxlength=\"121\""/></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="130">
                    <col width="88">
                    <col width="248">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Merchandise</fmt:message></td>
                        <td class="pOut"><ezf:label name="mdseCd" ezfName="mdseCd" /></td>
                        <td class="pOut"><ezf:label name="mdseNm" ezfName="mdseNm" /></td>
                    </tr>
                </table>
                <br>
                <table border="0" cellpadding="1" cellspacing="0">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Review Comments</fmt:message></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <tr>
                        <td><ezf:textArea name="rvwMemoTxt" ezfName="rvwMemoTxt" otherAttr=" cols=\"86\" rows=\"8\""/></td>
                    </tr>
                </table>
                <br>
                <table border="0" cellpadding="1" cellspacing="0">
                    <tr>
                        <td class="stab"><fmt:message key="i18n.NWAL0300Scrn00.label.11" bundle="${I18N_SCREEN_ID}">STC Hold Details</fmt:message></td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <tr>
                        <td><ezf:textArea name="hldDtlTxt" ezfName="hldDtlTxt" otherAttr=" cols=\"86\" rows=\"8\""/></td>
                    </tr>
                </table>
<%-- ######################################## DETAIL ######################################## --%>
                
            </td>
        </tr>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
