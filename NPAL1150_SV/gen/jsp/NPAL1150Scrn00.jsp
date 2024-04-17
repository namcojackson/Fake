<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171027115051 --%>
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
<fmt:setBundle basename="I18N_NPAL1150Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
            <input type="hidden" name="pageID" value="NPAL1150Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="ASN Error Correction">
            
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## Search Criteria - [START] ######################################## --%>
<%--
                <div class="pTab_HEAD">
                    <ul>
                        <li class="pTab_ON"><a href="./NPAL1150Scrn00.html"><fmt:message key="i18n.NPAL1150Scrn00.label.1" bundle="${I18N_SCREEN_ID}">ACK Reprc</fmt:message></a></li>
                    </ul>
                </div>
--%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <div class="pTab_BODY" style="WIDTH: 1133px; HEIGHT: 35px; word-break:kepp-all">
                    <!-- Group1 -->

                    <table border="0"  cellspacing="0" cellpadding="0" style="height: 33px;">
                        <COLGROUP>
                          <col width="5">
                          <col width="70"><!-- Vendor -->
                          <col width="2">
                          <col width="148">
                          <col width="5">
                          <col width="48"><!-- EDI PO# -->
                          <col width="2">
                          <col width="50">
                          <col width="5">
                          <col width="84"><!-- EDI Status -->
                          <col width="2">
                          <col width="180">
                          <col width="5">
                          <col width="72"><!-- EDI Rcv Date -->
                          <col width="2">
                          <col width="74">
                          <col width="5">
                          <col width="15">
                          <col width="5">
                          <col width="5">
                          <col width="5">
                          <col width="74">
                          <col width="5">
                          <col width="15">
                          <col width="5">
                          <col width="30"><!-- Search -->
                          <col width="30"><!-- Reprocess -->
                          <col width="30"><!-- Cancel -->
                        </COLGROUP>
                        <tr>
                            <td/>
                            <td class="stab">
                              <ezf:anchor name="xxLinkNm" ezfName="xxLinkNm" ezfEmulateBtn="1" ezfGuard="OpenWin_Vendor" >
                                <fmt:message key="i18n.NPAL1150Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Supplier Code</fmt:message>
                              </ezf:anchor>
                            </td>
                            <td/>
                            <td><ezf:inputText name="vndCd_A1" ezfName="vndCd_A1" value="123456789A123456789B" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                            <td/>
                            <td class="stab"><fmt:message key="i18n.NPAL1150Scrn00.label.3" bundle="${I18N_SCREEN_ID}">ASN PO#</fmt:message></td>
                            <td/>
                            <td><ezf:inputText name="ediPoOrdNum_A1" ezfName="ediPoOrdNum_A1" value="123456789A" otherAttr=" size=\"8\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td/>
                            <td class="stab"><fmt:message key="i18n.NPAL1150Scrn00.label.4" bundle="${I18N_SCREEN_ID}">ASN Proc Status</fmt:message></td>
                            <td/>
                            <td>
                              <ezf:select name="asnEdiProcStsCd_SV" ezfName="asnEdiProcStsCd_SV" ezfBlank="1" ezfCodeName="asnEdiProcStsCd_CD" ezfDispName="asnEdiProcStsNm_DI" />
                            </td>
                            <td/>
                            <td class="stab"><fmt:message key="i18n.NPAL1150Scrn00.label.5" bundle="${I18N_SCREEN_ID}">ASN Rcv Date</fmt:message></td>
                            <td/>
                            <td><ezf:inputText name="xxFromDt_A1" ezfName="xxFromDt_A1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                            <td/>
                            <td><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxFromDt_A1', 4 );"></TD>
                            <td/>
                            <td class="stab">-</td>
                            <td/>
                            <td><ezf:inputText name="xxToDt_A1" ezfName="xxToDt_A1" value="MM/DD/YYYY" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                            <td/>
                            <td><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxToDt_A1', 4 );"></TD>
                            <td/>

                            <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                            <td><ezf:inputButton name="Reprocess" value="Reprocess" htmlClass="pBtn6" /></td>
                            <td><ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn6" /></td>
                        </tr>
                    </table>
                    
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
                    <TABLE cellSpacing="0" cellPadding="0" border="0">
                        <COLGROUP>
                            <COL width="5">
                            <COL align="middle" width="70">
                            <COL align="middle" width="70">
                            <COL align="middle" width="68">
                            <COL align="middle" width="60">
                            <COL width="5">
                            <COL align="left" width="485">
                            <COL align="right" width="40">
                            <COL align="right" width="30">
                            <COL align="middle" width="20">
                            <COL align="right" width="30">
                            <COL align="middle" width="20">
                            <COL align="middle" width="30">
                            <COL width="10">
                            <COL width="0">
                            <COL width="1">
                            <COL width="0">
                        </COLGROUP>
                        <TBODY>
                            <TR>
<ezf:skip>
                              <TD/>
                              <TD><input class="pBtn7" onclick="sendServer(this)" type="button" value="Select All" name="SelectAll"></TD>
                              <TD><input class="pBtn7" onclick="sendServer(this)" type="button" value="Un Select All" name="UnSelectAll"></TD>
                              <TD/>
                              <TD class="stab"><fmt:message key="i18n.NPAL1150Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Message(*)</fmt:message></TD>
                              <TD/>
                              <TD><input type="text" size="65" maxlength="400" value="123456789012345678901234567890123456789012345678901234567890123456" name="batErrMsgTxt"></TD>
                              <TD>&nbsp;</TD>
                              <TD class="stab">Showing</TD>
                              <TD class="pOut">1</TD>
                              <TD class="stab">to</TD>
                              <TD class="pOut">200</TD>
                              <TD class="stab">of</TD>
                              <TD class="pOut">1000</TD>
                              <TD>&nbsp;</TD>
                              <TD><INPUT class="pBtn3" disabled onclick="sendServer(this)" type="button" value="Prev" name="PagePrev"></TD>
                              <TD></TD>
                              <TD><INPUT class="pBtn3" onclick="sendServer(this)" type="button" value="Next" name="PageNext"></TD>
</ezf:skip>
                            </TR>
                        </TBODY>
                    </TABLE>
                    <TABLE cellSpacing="0" cellPadding="0" border="0">
                        <COLGROUP>
                            <COL width="5">
                            <COL align="middle" width="70">
                            <COL align="middle" width="70">
                            <COL align="middle" width="68">
                            <COL align="middle" width="60">
                            <COL width="5">
                            <COL align="left" width="485">
                            <COL align="right" width="337">
                        </COLGROUP>
                        <TBODY>
                            <tr>
                              <TD/>
                              <TD><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" /></TD>
                              <TD><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn7" /></TD>
                              <TD/>
                              <TD class="stab"><fmt:message key="i18n.NPAL1150Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Message(*)</fmt:message></TD>
                              <TD/>
                              <TD><ezf:inputText name="batErrMsgTxt_A1" ezfName="batErrMsgTxt_A1" value="123456789012345678901234567890123456789012345678901234567890123456" otherAttr=" size=\"65\" maxlength=\"400\""/></TD>
                              <td align="right">
                                <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                <jsp:param name="TableNm"     value="A" />
                                <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
                                <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
                                <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
                                </jsp:include>
                              </td>
                            </tr>
                        </TBODY>
                    </TABLE>
                <hr>
<%-- ######################################## Search Criteria - [END] ######################################## --%>

<%-- ######################################## Search Result - [START] ######################################## --%>
                    <table cellSpacing="0" cellPadding="0" border="1">
                        <tbody>
                            <tr style="VERTICAL-ALIGN: top">
                              <td>
                                <table width="1104" cellSpacing="0" cellPadding="1" border="1" >
                                  <colgroup>
                                    <col align="middle" width="25">
                                    <col align="middle" width="190"><!-- Vendor -->
                                    <col align="middle" width="170"><!-- ASN SO# -->
                                    <col align="middle" width="110"><!-- PO# -->
                                    <col align="middle" width="184"><!-- EDI Status -->
                                    <col align="middle" width="150"><!-- EDI Rcv Date -->
                                    <col align="middle" width="56"><!-- ASN WH -->
                                    <col align="middle" width="150"><!-- ASN Ship Date -->
                                    <col align="middle" width="17"><!-- Scroll -->
                                  </colgroup>
                                  <tbody>
                                  <tr height="30">
                                    <td class="pClothBs"></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Supplier Name</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.8" bundle="${I18N_SCREEN_ID}">ASN SO#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.3" bundle="${I18N_SCREEN_ID}">ASN PO#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.4" bundle="${I18N_SCREEN_ID}">ASN Proc Status</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.5" bundle="${I18N_SCREEN_ID}">ASN Rcv Date</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.9" bundle="${I18N_SCREEN_ID}">ASN WH</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.10" bundle="${I18N_SCREEN_ID}">ASN Ship Date</fmt:message></td>
                                    <td class="pClothBs">&nbsp;</td>
                                  </tr>
                                  </tbody>
                                </table>
                                <div style="OVERFLOW-Y: scroll; WIDTH: 1104px; HEIGHT: 152px; word-break:break-all">
                                <table id="A1" width="1083" cellSpacing="0" cellPadding="1" border="1" >
                                  <colgroup>
                                    <col align="middle" width="25">
                                    <col align="middle" width="190"><!-- Vendor -->
                                    <col align="middle" width="170"><!-- ASN SO# -->
                                    <col align="middle" width="110"><!-- EDI PO# -->
                                    <col align="middle" width="184"><!-- EDI Status -->
                                    <col align="middle" width="150"><!-- EDI Rcv Date -->
                                    <col align="middle" width="56"><!-- ASN WH -->
                                    <col align="middle" width="150"><!-- ASN Ship Date -->
                                  </colgroup>
                                  <tbody>
                                    <ezf:row ezfHyo="A">
                                      <tr id="id_row${EZF_ROW_NUMBER}" height="30">
                                        <td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                        <td align="left"><ezf:inputText name="dplyVndNm_B1" ezfName="dplyVndNm_B1" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZZZZZ5ZZZZZZZZZ6" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"320\" ezftoupper=\"\""/></td>
                                        <td align="left">
                                          <ezf:inputText name="asnSoNum_B1" ezfName="asnSoNum_B1" value="123456789X12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"18\" ezftoupper=\"\""/>
                                          <ezf:inputButton name="SoLink" value="Link" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                        </td>
                                        <td align="left"><ezf:inputText name="ediPoOrdNum_B1" ezfName="ediPoOrdNum_B1" value="123456789A123456789B123456789C12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"35\" ezftoupper=\"\""/></td>
                                        <td align="left"><ezf:inputText name="asnEdiProcStsNm_B1" ezfName="asnEdiProcStsNm_B1" value="Processed with warning" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"400\""/></td>
                                        <td><ezf:label name="xxTsDsp19Txt_BR" ezfName="xxTsDsp19Txt_BR" ezfHyo="A" ezfArrayNo="0" /></td>
                                        <td align="left"><ezf:label name="vndInvtyLocCd_B1" ezfName="vndInvtyLocCd_B1" ezfHyo="A" ezfArrayNo="0" /></td>
                                        <td><ezf:label name="xxTsDsp19Txt_BS" ezfName="xxTsDsp19Txt_BS" ezfHyo="A" ezfArrayNo="0" /></td>
                                    </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                    <tr height="30"><td><input type="checkbox" ezfname="xxChkBox_B1" name="xxChkBox_B1" value="Y" ezfhyo="A"></td></tr>
                                    <tr height="30"><td><input type="checkbox" ezfname="xxChkBox_B1" name="xxChkBox_B1" value="Y" ezfhyo="A"></td></tr>
                                    <tr height="30"><td><input type="checkbox" ezfname="xxChkBox_B1" name="xxChkBox_B1" value="Y" ezfhyo="A"></td></tr>
                                    <tr height="30"><td><input type="checkbox" ezfname="xxChkBox_B1" name="xxChkBox_B1" value="Y" ezfhyo="A"></td></tr>
                                    </ezf:skip>
                                  </tbody>
                                </table>
                                </div>
                              </td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
<%-- ######################################## Search Result - [START] ######################################## --%>

                    <%-- ###TAB - HEAD --%>
                    <div class="pTab_HEAD">
                        <ul>
                            <li title="<fmt:message key="i18n.NPAL1150Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Header</fmt:message>" id="ackHeader" class="pTab_ON"><ezf:anchor name="xxLinkAncr_T0" ezfName="xxLinkAncr_T0" ezfEmulateBtn="1" ezfGuard="Header" ><fmt:message key="i18n.NPAL1150Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Header</fmt:message></ezf:anchor></li>
                            <li title="<fmt:message key="i18n.NPAL1150Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Detail</fmt:message>" id="ackDetail" class="pTab_OFF"><ezf:anchor name="xxLinkAncr_T1" ezfName="xxLinkAncr_L1" ezfEmulateBtn="1" ezfGuard="Detail" ><fmt:message key="i18n.NPAL1150Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Detail</fmt:message></ezf:anchor></li>
                        </ul>
                    </div>

                    <%-- ###TAB - Header --%>
                    <c:choose>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='H'}">
                        <script type="text/javascript">
                          document.getElementById("ackHeader").className = "pTab_ON";
                          document.getElementById("ackDetail").className = "pTab_OFF";
                        </script>
                        
                        <div class="pTab_BODY">
                          <table>
                            <colgroup>
                              <col width="15">
                              <col width="80">
                              <col width="2">
                              <col width="216">
                              <col width="15">
                              <col width="90">
                              <col width="2">
                              <col width="220">
                              <col width="15">
                              <col width="80">
                              <col width="2">
                              <col width="205">
                            </colgroup>
                            <tr/>
                            <tr/>
                            <tr/>
                            <tr>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Supplier Name</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="dplyVndNm_H1" ezfName="dplyVndNm_H1" value="123456789A123456789B123456789C" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"320\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.4" bundle="${I18N_SCREEN_ID}">ASN Proc Status</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="asnEdiProcStsNm_H1" ezfName="asnEdiProcStsNm_H1" value="123456789A123456789B123456789C" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.5" bundle="${I18N_SCREEN_ID}">ASN Rcv Date</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="xxTsDsp19Txt_HR" ezfName="xxTsDsp19Txt_HR" value="04/25/2013 21:55:31" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr/>
                            <tr/>
                            <tr>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.8" bundle="${I18N_SCREEN_ID}">ASN SO#</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="asnSoNum_H1" ezfName="asnSoNum_H1" value="123456789A123456789B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.3" bundle="${I18N_SCREEN_ID}">ASN PO#</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="ediPoOrdNum_H1" ezfName="ediPoOrdNum_H1" value="123456789A123456789B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.13" bundle="${I18N_SCREEN_ID}">PO#</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="poOrdNum_H1" ezfName="poOrdNum_H1" value="12345678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                                  <ezf:inputButton name="poEntry" value="PoEntry" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" /></td>
                              <td/>
                            </tr>
                            <tr/>
                            <tr/>
                            <tr>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.9" bundle="${I18N_SCREEN_ID}">ASN WH</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="vndInvtyLocCd_H1" ezfName="vndInvtyLocCd_H1" value="12345678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Supplier Delivery#</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="shipFromSoNum_H1" ezfName="shipFromSoNum_H1" value="123456789A123456789B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.10" bundle="${I18N_SCREEN_ID}">ASN Ship Date</fmt:message></a></td>
                              <td/>
                              <td><ezf:inputText name="xxTsDsp19Txt_HS" ezfName="xxTsDsp19Txt_HS" value="04/25/2013 21:55:31" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/></td>
                            </tr>
                          </table>
                          <table>
                            <colgroup>
                              <col width="15">
                              <col width="80">
                              <col width="802">
                            </colgroup>
                            <tr>
                              <td/>
                              <td class="stab"><a><fmt:message key="i18n.NPAL1150Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Header Error Message</fmt:message></a></td>
                              <td align="left">
                                  <ezf:select name="batErrMsgTxt_HV" ezfName="batErrMsgTxt_HV" ezfHyo="B" ezfArrayNo="0" ezfCodeName="batErrMsgTxt_HC" ezfDispName="batErrMsgTxt_HD" ezfCodeDispHyo="B" otherAttr=" style=\"margin-left: 6px;width:802\""/> 
                               </td>
                            </tr>
                          </table>
                        </div>
                    </c:when>

                    <%-- ###TAB - Detail --%>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='D'}">
                        <script type="text/javascript">
                          document.getElementById("ackHeader").className = "pTab_OFF";
                          document.getElementById("ackDetail").className = "pTab_ON";
                        </script>
                        <div class="pTab_BODY">
                        <table>
                          <colgroup>
                            <col width="45">
                            <col width="80">
                            <col width="10">
                            <col width="100">
                          </colgroup>
                          <tr>
                            <td/>
                            <td class="stab">
                              <ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /><fmt:message key="i18n.NPAL1150Scrn00.label.16" bundle="${I18N_SCREEN_ID}">Show all</fmt:message>
                            </td>
                            <td/>
                            <td>
                              <ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn7" />
                            </td>
                          </tr>
                        </table>
                        <center>
                          <table cellSpacing="0" cellPadding="0" border="1"  height="233" >
                            <tbody>
                              <tr style="VERTICAL-ALIGN: top">
                                <td><!-- Left TBL Header -->
                                  <table cellSpacing="0" cellPadding="1" border="1">
                                    <colgroup>
                                      <col align="middle" width="56"><!-- EDI Line# -->
                                      <col align="middle" width="56"><!-- PO Line# -->
                                      <col align="middle" width="72"><!-- ASN SO Slip# -->
                                      <col align="middle" width="121"><!-- MDSE CD -->
                                      <col align="middle" width="56"><!-- MDSE Upd Flg -->
                                      <col align="middle" width="120"><!-- ASN Qty -1 -->
                                      <col align="middle" width="96"><!-- Carrier -->
                                      <col align="middle" width="168"><!-- Pro# -2 -->
                                      <col align="middle" width="168"><!-- BOL# -2 -->
                                      <col align="middle" width="79"><!-- PDD -->
                                      <col align="middle" width="17"><!-- Scroll -->
                                    </colgroup>
                                    <tbody>
                                    <tr height="28">
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.17" bundle="${I18N_SCREEN_ID}">ASN PO<br>Line#</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.18" bundle="${I18N_SCREEN_ID}">PO<br>Line#</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.19" bundle="${I18N_SCREEN_ID}">ASN SO<br>Slip#</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Item#</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Item<br>Upd Flg</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.22" bundle="${I18N_SCREEN_ID}">ASN Qty</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Carrier</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.24" bundle="${I18N_SCREEN_ID}">Pro#</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.25" bundle="${I18N_SCREEN_ID}">B/L#</fmt:message></td>
                                      <td class="pClothBs"><fmt:message key="i18n.NPAL1150Scrn00.label.26" bundle="${I18N_SCREEN_ID}">PDD</fmt:message></td>
                                      <td class="pClothBs">&nbsp;</td>
                                    </tr>
                                    </tbody>
                                  </table>
                                  </div>
                                  <div style="OVERFLOW-Y: scroll; WIDTH: 1054px; HEIGHT: 200px; word-break:break-all">
                                  <table id="B1" cellSpacing="0" cellPadding="1" border="1">
                                    <colgroup>
                                      <col align="middle" width="56"><!-- PO Line# -->
                                      <col align="middle" width="56"><!-- EDI Line# -->
                                      <col align="middle" width="72"><!-- ASN SO Slip# -->
                                      <col align="middle" width="121"><!-- MDSE CD -->
                                      <col align="middle" width="56"><!-- MDSE Upd Flg -->
                                      <col align="middle" width="120"><!-- ASN Qty -1 -->
                                      <col align="middle" width="96"><!-- Carrier -->
                                      <col align="middle" width="168"><!-- Pro# -2 -->
                                      <col align="middle" width="168"><!-- BOL# -2 -->
                                      <col align="middle" width="79"><!-- PDD -->
                                    </colgroup>
                                    <tbody>
                                      <ezf:row ezfHyo="C">
                                      <tr id="id_row{EZF_ROW_NUMBER}" height="28" >
                                        <td><ezf:label name="ediPoOrdDtlLineNum_D1" ezfName="ediPoOrdDtlLineNum_D1" ezfHyo="C" ezfArrayNo="0" /></td>
                                        <td><ezf:inputText name="dispPoDtlLineNum_D1" ezfName="dispPoDtlLineNum_D1" value="12345678" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></input></td>
                                        <td><ezf:inputText name="asnSoSlpNum_D1" ezfName="asnSoSlpNum_D1" value="12345678" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></input></td>
                                        <td><ezf:inputText name="mdseCd_D1" ezfName="mdseCd_D1" value="123456780A123456" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></input></td>
                                        <td><ezf:label name="mdseCdUpdFlg_D1" ezfName="mdseCdUpdFlg_D1" ezfHyo="C" ezfArrayNo="0" /></td>
                                        <td><ezf:inputText name="asnQty_D1" ezfName="asnQty_D1" value="9,999,999,999" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></input></td>
                                        <td><ezf:inputText name="asnCarrCd_D1" ezfName="asnCarrCd_D1" value="123456780A123456" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"20\""/></td>
                                        <td><ezf:inputText name="asnProNum_D1" ezfName="asnProNum_D1" value="123456780A123456789B123456789C" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                        <td><ezf:inputText name="asnBolNum_D1" ezfName="asnBolNum_D1" value="123456780A123456789B123456789C123456789D" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"40\""/></label></td>
                                        <td><ezf:label name="asnPlnDelyDt_D1" ezfName="asnPlnDelyDt_D1" ezfHyo="C" ezfArrayNo="0" /></td>
                                      </tr>
                                      <tr height="28">
                                        <td class="pClothBs" colspan="2"><ezf:label name="xxNum_D1" ezfName="xxNum_D1" ezfHyo="C" ezfArrayNo="0" /><fmt:message key="i18n.NPAL1150Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Message(s)</fmt:message></td>
                                        <td colspan="10" align="left">
                                          <ezf:select name="batErrMsgTxt_DV" ezfName="batErrMsgTxt_DV" ezfHyo="C" ezfArrayNo="0" ezfCodeName="batErrMsgTxt_DC" ezfDispName="batErrMsgTxt_DD" ezfCodeDispHyo="C" otherAttr=" style=\"margin-left: 6px;width:850\""/> 
                                      </td>
                                      </tr>
                                      </ezf:row>
                                    </tbody>
                                  </table>
                                  </div>
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </center>
                        </div>
                    </c:when>
                    </c:choose>
<%-- ######################################## Search Result - [END] ######################################## --%>
                </div>
            </td>
        </tr>
    </table>
</center>


<%-- **** Task specific area ends here **** --%>
