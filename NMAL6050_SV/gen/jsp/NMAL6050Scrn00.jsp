<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100622005806 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%  pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NMAL6050Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
            <input type="hidden" name="pageID" value="NMAL6050Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL6050Scrn00.title" bundle="${I18N_SCREEN_ID}">Common Pop Up</fmt:message>">
            
<center>
    <table>
        <tr>
            <td>
                
<%-- ######################################## HEADER ######################################## --%>
                    <table border="0">
                        <col width="160">
                        <col width="136">
                    
                        <tr>
                            <td class="stab"><ezf:label name="xxHdrCdLbNm" ezfName="xxHdrCdLbNm" />(*)</td>
                            <td><ezf:inputText name="xxCondCd" ezfName="xxCondCd" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                        </tr>
                    </table>
                    <table border="0">
                        <col width="160">
                        <col width="410">
                    
                        <tr>
                            <td class="stab"><ezf:label name="xxHdrNmLbNm" ezfName="xxHdrNmLbNm" />(*)</td>
                            <td><ezf:inputText name="xxCondNm" ezfName="xxCondNm" otherAttr=" size=\"70\" maxlength=\"70\""/></td>
                        </tr>
                    </table>
                    <table border="0">
                        <col width="695">
                        <col width="72">
                        <tr>
                            <td>&nbsp;</td>
                            <td><ezf:inputButton name="Search_ConditionCode" value="Search" htmlClass="pBtn6" /></td>
                        </tr>
                    </table>
                <hr>
<%-- ######################################## PAGE ######################################## --%>
                <table width="100%">
                    <tr align="right">
                        <td>
                            <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                <jsp:param name="TableNm"     value="A" />
                                <jsp:param name="ShowingFrom" value="xxPageShowFromNum_10" />
                                <jsp:param name="ShowingTo"   value="xxPageShowToNum_10" />
                                <jsp:param name="ShowingOf"   value="xxPageShowOfNum_10" />
                            </jsp:include>
                        </td>
                    </tr>
                </table>
<%-- ######################################## DETAIL ######################################## --%>
                <table border="1" cellpadding="1" cellspacing="0">
                    <col width="160"  align="center">
                    <col width="592" align="center">
                    <tr>
                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtlCd'   )"><ezf:label name="xxDtlCdLbNm" ezfName="xxDtlCdLbNm" /><img id="sortIMG.xxDtlCd"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtlNm'   )"><ezf:label name="xxDtlNmLbNm" ezfName="xxDtlNmLbNm" /><img id="sortIMG.xxDtlNm"   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                    </tr>
                </table>
                
                <div style="overflow:auto; height:402;">
                    <table border="1" cellpadding="1" cellspacing="0" id="A">
                    <col width="160"  align="center">
                    <col width="592" align="center">
                        <tbody>
                            <ezf:row ezfHyo="A">
                                <tr id="id_row{EZF_ROW_NUMBER}">
                                    <td align="left"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_ConditionCode" ><ezf:label name="xxDtlCd" ezfName="xxDtlCd" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                    <td align="left"><ezf:label name="xxDtlNm" ezfName="xxDtlNm" ezfHyo="A" ezfArrayNo="0" /></td>
                                </tr>
                            </ezf:row>
                            <ezf:skip>
                                <tr class="pEvenNumberBGcolor">
                                    <td><label ezfout>A00000000000002</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>
                                
                                <tr>
                                    <td><label ezfout>A00000000000003</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>

                                <tr class="pEvenNumberBGcolor">
                                    <td><label ezfout>A00000000000004</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>
                                
                                <tr>
                                    <td><label ezfout>A00000000000005</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>

                                <tr class="pEvenNumberBGcolor">
                                    <td><label ezfout>B1111111111111Z</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>
                                
                                <tr>
                                    <td><label ezfout>B2222222222222Z</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>

                                <tr class="pEvenNumberBGcolor">
                                    <td><label ezfout>C2222222222222J</label></td>
                                    <td><label ezfout>WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5</label></td>
                                </tr>
                            </ezf:skip>
                        </tbody>
                    </table>
                </div>
                
            </td>
        </tr>
    </table>
</center>
            

<%-- **** Task specific area ends here **** --%>
