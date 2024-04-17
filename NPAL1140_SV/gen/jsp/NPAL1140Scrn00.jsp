<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171027115305 --%>
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
<fmt:setBundle basename="I18N_NPAL1140Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
            <input type="hidden" name="pageID" value="NPAL1140Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NPAL1140Scrn00.title" bundle="${I18N_SCREEN_ID}">ACK Error Correction</fmt:message>">
            
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## Search Criteria - [START] ######################################## --%>
<%--
                <div class="pTab_HEAD">
                    <ul>
                        <li class="pTab_ON"><a href="./NPAL1140Scrn00.html"><fmt:message key="i18n.NPAL1140Scrn00.label.1" bundle="${I18N_SCREEN_ID}">ACK Reprc</fmt:message></a></li>
                    </ul>
                </div>
--%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

                <div class="pTab_BODY" style="WIDTH: 1133px; HEIGHT: 35px; word-break:kepp-all">
                    <table width="1121" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="5">
                        <col width="70">
                        <col width="2">
                        <col width="143">
                        <col width="10">
                        <col width="50">
                        <col width="2">
                        <col width="50">
                        <col width="10">
                        <col width="85">
                        <col width="2">
                        <col width="180">
                        <col width="5">
                        <col width="75">
                        <col width="2">
                        <col width="76">
                        <col width="1">
                        <col width="15">
                        <col width="1">
                        <col width="5">
                        <col width="1">
                        <col width="76">
                        <col width="1">
                        <col width="15">
                        <col width="10">
                        <col width="30">
                        <col width="30">
                        <col width="30">
                        <tr>
                            <td/>
                            <td class="stab">
                              <ezf:anchor name="xxLinkNm" ezfName="xxLinkNm" ezfEmulateBtn="1" ezfGuard="OpenWin_Vendor" >
                                <fmt:message key="i18n.NPAL1140Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Supplier Code</fmt:message>
                              </ezf:anchor>
                            </td>
                            <td/>
                            <td><ezf:inputText name="vndCd_H1" ezfName="vndCd_H1" value="123456789A123456789B" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/></td>
                            <td/>
                            <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.3" bundle="${I18N_SCREEN_ID}">ACK PO#</fmt:message></td>
                            <td/>
                            <td><ezf:inputText name="ediPoOrdNum" ezfName="ediPoOrdNum" value="123456789A123456789B123456789C12345" otherAttr=" size=\"8\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td/>
                            <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.4" bundle="${I18N_SCREEN_ID}">ACK Proc Status</fmt:message></td>
                            <td/>
                            <td><ezf:select name="ediProcStsCd_0V" ezfName="ediProcStsCd_0V" ezfBlank="1" ezfCodeName="ediProcStsCd_0C" ezfDispName="ediProcStsNm_0D" otherAttr=" style=\"width:180\"">
                                <ezf:skip>
                                <OPTION selected>&nbsp;</OPTION>
                                <OPTION>Processed with warning</OPTION>
                                </ezf:skip>
                                </ezf:select></td>
                            <td/>
                            <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.5" bundle="${I18N_SCREEN_ID}">ACK Rcv Date</fmt:message></td>
                            <td/>
                            <td><ezf:inputText name="xxCratDt_H1" ezfName="xxCratDt_H1" value="2013/04/30" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                            <td/>
                            <td><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxCratDt_H1', 4 );"></TD>
                            <td/>
                            <td class="stab">-</td>
                            <td/>
                            <td><ezf:inputText name="xxCratDt_H2" ezfName="xxCratDt_H2" value="2013/05/02" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                            <td/>
                            <td><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxCratDt_H2', 4 );"></TD>
                            <td/>
                            <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                              <td><ezf:inputButton name="ackReprocess" value="Reprocess" htmlClass="pBtn6" /></td>
                              <td><ezf:inputButton name="ackCancel" value="Cancel" htmlClass="pBtn6" /></td>
                        </tr>
                    </table>
                </div>    
<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
                       <ezf:skip>
                          <TABLE cellSpacing="0" cellPadding="0" border="0">
                            <COLGROUP>
                            <COL align="left" width="10">
                            <COL align="left" width="70">
                            <COL align="left" width="70">
                            <COL align="left" width="94">
                            <COL align="left" width="137">
                            <COL align="left" width="300">
                            <COL align="left" width="112">
                            <COL align="right" width="50">
                            <COL align="right" width="30">
                            <COL align="middle" width="20">
                            <COL align="right" width="30">
                            <COL align="middle" width="20">
                            <COL align="middle" width="30">
                            <COL width="10">
                            <COL width="0">
                            <COL width="1">
                            <COL width="0">
                            <TBODY>
                            <TR>
                              <TD>&nbsp;</TD>
                              <TD><INPUT class="pBtn7" disabled onclick="sendServer(this)" type="button" value="Select All" name="selectAll"></TD>
                              <TD><INPUT class="pBtn7" disabled onclick="sendServer(this)" type="button" value="Un Select All" name="unSelectAll"></TD>
                              <TD>&nbsp;</TD>
                              <TD class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Message(*)</fmt:message></td>
                              <TD><input type="text" size="65" maxlength="400" value="123456789A123456789B123456789C12345" name="batErrMsgTxt_H1" ezfname="batErrMsgTxt_H1"></TD>
                              <TD/>
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
                             </TR>
                             </TBODY>
                           </TABLE>
                       </ezf:skip>
                          <table cellSpacing="0" cellPadding="0" border="0">
                            <COLGROUP>
                            <COL align="left"  width="5">
                            <COL align="left"  width="70">
                            <COL align="left"  width="70">
                            <COL align="left"  width="100">
                            <COL align="left"  width="60">
                            <COL align="left"  width="5">
                            <COL align="left"  width="300">
                            <col align="right" width="330" >
                            </COLGROUP>
                            <TBODY>
                            <tr>
                              <TD/>
                              <TD><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn7" /></TD>
                              <TD><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn7" /></TD>
                              <TD/>
                              <TD class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Message(*)</fmt:message></td>
                              <TD/>
                              <TD><ezf:inputText name="batErrMsgTxt_H1" ezfName="batErrMsgTxt_H1" value="123456789A123456789B123456789C12345" otherAttr=" size=\"65\" maxlength=\"400\""/></TD>
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
                          </table>
                    <hr>
<%-- ######################################## Search Criteria - [END] ######################################## --%>

<%-- ######################################## Search Result - [START] ######################################## --%>
                          <table cellSpacing="0" cellPadding="0" border="1" height ="170">
                            <tbody>
                            <tr style="VERTICAL-ALIGN: top">
                              <td>
                                <div style="WIDTH: 1120px; HEIGHT: 36px; word-break:break-all">
                                <table width="1103" cellSpacing="0" cellPadding="1" border="1" >
                                  <colgroup>
                                  <col align="middle" width="25">
                                  <col align="middle" width="150">
                                  <col align="middle" width="127">
                                  <col align="middle" width="60">
                                  <col align="middle" width="150">
                                  <col align="middle" width="148">
                                  <col align="middle" width="148">
                                  <col align="middle" width="82">
                                  <col align="middle" width="165">
                                  <tbody>
                                  <tr height="30">
                                    <td class="pClothBs"></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Supplier Name</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.3" bundle="${I18N_SCREEN_ID}">ACK PO#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.29" bundle="${I18N_SCREEN_ID}">ACK#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.4" bundle="${I18N_SCREEN_ID}">ACK Proc Status</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.5" bundle="${I18N_SCREEN_ID}">ACK Rcv Date</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.8" bundle="${I18N_SCREEN_ID}">Supplier ACK Update Date</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Supplier CPO#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.10" bundle="${I18N_SCREEN_ID}">Message</fmt:message></td>
                                  </tr></tbody></table>
                                <div style="OVERFLOW-Y: scroll; WIDTH: 1120px; HEIGHT: 140px; word-break:break-all">
                                <table id="A" width="1103" cellSpacing="0" cellPadding="1" border="1" >
                                  <colgroup>
                                  <col align="middle" width="25">
                                  <col align="middle" width="150">
                                  <col align="middle" width="127">
                                  <col align="middle" width="60">
                                  <col align="middle" width="150">
                                  <col align="middle" width="148">
                                  <col align="middle" width="148">
                                  <col align="middle" width="82">
                                  <col align="middle" width="165">
                                  <tbody><ezf:row ezfHyo="A">
                                  <tr id="id_row${EZF_ROW_NUMBER}" height="28">
                                    <td><ezf:inputCheckBox name="xxChkBox_A0" ezfName="xxChkBox_A0" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                    <td><ezf:inputText name="dplyVndNm_A0" ezfName="dplyVndNm_A0" value="123456789A123456789B123456789C" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"320\""/></td>
                                    <td>
                                        <ezf:inputText name="ediPoOrdNum_A0" ezfName="ediPoOrdNum_A0" value="123456789A123456789B123456789C12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"35\""/>
                                        <ezf:inputButton name="ackDisplay" value="Link" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                    </td>
                                    <td><ezf:label name="poAckNum_A0" ezfName="poAckNum_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                    <td><ezf:inputText name="ackEdiProcStsNm_A0" ezfName="ackEdiProcStsNm_A0" value="123456789A123456789B123456789C" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                    <td><ezf:label name="xxTsDsp19Txt_AR" ezfName="xxTsDsp19Txt_AR" ezfHyo="A" ezfArrayNo="0" /></td>
                                    <td><ezf:label name="xxTsDsp19Txt_AU" ezfName="xxTsDsp19Txt_AU" ezfHyo="A" ezfArrayNo="0" /></td>
                                    <td><ezf:label name="vndCpoOrdNum_A0" ezfName="vndCpoOrdNum_A0" ezfHyo="A" ezfArrayNo="0" /></td>
                                    <td>
                                        <ezf:select name="batErrMsgTxt_AV" ezfName="batErrMsgTxt_AV" ezfHyo="A" ezfArrayNo="0" ezfCodeName="batErrMsgTxt_AC" ezfDispName="batErrMsgTxt_AD" ezfCodeDispHyo="A" otherAttr=" style=\"margin-left: 6px; width:159\""/>
                                    </td>
                                  </tr></ezf:row>
                                <ezf:skip>
                                  <tr height="30">
                                    <td><input type="checkbox" ></td>
                                  </tr>
                                  <tr height="30">
                                    <td><input type="checkbox" ></td>
                                  </tr>
                                  <tr height="30">
                                    <td><input type="checkbox" ></td>
                                  </tr>
                                  <tr height="30">
                                    <td><input type="checkbox" ></td>
                                  </tr>
                                  <tr height="30">
                                    <td><input type="checkbox" ></td>
                                  </tr>
                                </ezf:skip>
                                  </tbody></table>
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
                            <li title="<fmt:message key="i18n.NPAL1140Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Header</fmt:message>" id="ackHeader" class="pTab_ON"><ezf:anchor name="xxLinkAncr_T0" ezfName="xxLinkAncr_T0" ezfEmulateBtn="1" ezfGuard="TabAckHeader" ><fmt:message key="i18n.NPAL1140Scrn00.title.1" bundle="${I18N_SCREEN_ID}">Header</fmt:message></ezf:anchor></li>
                            <li title="<fmt:message key="i18n.NPAL1140Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Detail</fmt:message>" id="ackDetail" class="pTab_OFF"><ezf:anchor name="xxLinkAncr_T1" ezfName="xxLinkAncr_T1" ezfEmulateBtn="1" ezfGuard="TabAckDetail" ><fmt:message key="i18n.NPAL1140Scrn00.title.2" bundle="${I18N_SCREEN_ID}">Detail</fmt:message></ezf:anchor></li>
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
                            <col width="30">
                            <col width="80">
                            <col width="2">
                            <col width="216">
                            <col width="20">
                            <col width="90">
                            <col width="2">
                            <col width="220">
                            <col width="20">
                            <col width="70">
                            <col width="2">
                            <col width="105">
                            <tr/>
                            <tr/>
                            <tr/>
                            <tr>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Supplier Name</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="dplyVndNm_HT" ezfName="dplyVndNm_HT" value="123456789A123456789B123456789C" otherAttr=" size=\"30\" maxlength=\"320\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.4" bundle="${I18N_SCREEN_ID}">ACK Proc Status</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="ackEdiProcStsNm" ezfName="ackEdiProcStsNm" value="123456789A123456789B123456789C" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.5" bundle="${I18N_SCREEN_ID}">ACK Rcv Date</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="xxTsDsp19Txt_HR" ezfName="xxTsDsp19Txt_HR" value="2013/04/25 21:55:31" otherAttr=" size=\"19\" maxlength=\"19\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr/>
                            <tr/>
                            <tr>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.3" bundle="${I18N_SCREEN_ID}">ACK PO#</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="ediPoOrdNum_HT" ezfName="ediPoOrdNum_HT" value="123456789A123456789B123456789C12345" otherAttr=" size=\"35\" maxlength=\"35\" ezftoupper=\"\""/>
                              </td>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.13" bundle="${I18N_SCREEN_ID}">PO#</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="poOrdNum_HT" ezfName="poOrdNum_HT" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                                  <ezf:inputButton name="poEntry" value="PoEntry" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" />
                              </td>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.29" bundle="${I18N_SCREEN_ID}">ACK#</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="poAckNum_HT" ezfName="poAckNum_HT" value="1" otherAttr=" size=\"19\" maxlength=\"3\" ezftoupper=\"\""/></td>
                            </tr>
                            <tr>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.9" bundle="${I18N_SCREEN_ID}">Supplier CPO#</fmt:message></td>
                              <td/>
                              <td><ezf:inputText name="vndCpoOrdNum" ezfName="vndCpoOrdNum" value="12345678" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                               </td>
                              <td/>
                              <td/>
                              <td/>
                              <td/>
                              <td/>
                            </tr>
                          </table>
                          <table>
                            <col width="30">
                            <col width="80">
                            <col width="480">
                            <tr>
                              <td/>
                              <td class="stab"><fmt:message key="i18n.NPAL1140Scrn00.label.14" bundle="${I18N_SCREEN_ID}">Header Error Message</fmt:message></td>
                              <td align="left">                                                
                                <ezf:select name="batErrMsgTxt_HV" ezfName="batErrMsgTxt_HV" ezfCodeName="batErrMsgTxt_HC" ezfDispName="batErrMsgTxt_HD" otherAttr=" style=\"margin-left: 6px; width:480\""/>
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
                          <table cellSpacing="0" cellPadding="0" border="0"  height="28" >
                             <colgroup>
                               <col align="left" width="45">
                               <col align="left" width="80">
                               <col align="left" width="10">
                               <col align="left" width="100">
                             <tbody>
                               <tr>
                                 <td/>
                                 <td><ezf:inputCheckBox name="xxChkBox_B0" ezfName="xxChkBox_B0" value="Y" /><fmt:message key="i18n.NPAL1140Scrn00.label.15" bundle="${I18N_SCREEN_ID}">Show all</fmt:message></td>
                                 </td>
                                 <td><ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn6" /></td>
                               </tr>
                             </tbody>
                          </table>
                        <center>
                          <table cellSpacing="0" cellPadding="0" border="1"  height="233" >
                            <tbody>
                            <tr style="VERTICAL-ALIGN: top">
                              <td>
                                <div style="WIDTH: 1097px; HEIGHT: 37px; word-break:break-all">
                                <table cellSpacing="0" cellPadding="1" border="1" width="1076">
                                  <colgroup>
                                  <col align="middle" width="54">
                                  <col align="middle" width="51">
                                  <col align="middle" width="102">
                                  <col align="middle" width="48">
                                  <col align="middle" width="121">
                                  <col align="middle" width="170">
                                  <col align="middle" width="121">
                                  <col align="middle" width="99">
                                  <col align="middle" width="27">
                                  <col align="middle" width="76">
                                  <col align="middle" width="76">
                                  <col align="middle" width="76">
                                  <tbody>
                                  <tr height="37">	
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.16" bundle="${I18N_SCREEN_ID}">ACK<br>POLine#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.17" bundle="${I18N_SCREEN_ID}">PO<br>Line#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.18" bundle="${I18N_SCREEN_ID}">Supplier Shpg Pln#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.19" bundle="${I18N_SCREEN_ID}">Line<br>Status</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.20" bundle="${I18N_SCREEN_ID}">Item#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.21" bundle="${I18N_SCREEN_ID}">Item Name</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.22" bundle="${I18N_SCREEN_ID}">Original<br>Item Code#</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.23" bundle="${I18N_SCREEN_ID}">Ord Qty</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.24" bundle="${I18N_SCREEN_ID}">UOM</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.25" bundle="${I18N_SCREEN_ID}">ETD</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.26" bundle="${I18N_SCREEN_ID}">ETA</fmt:message></td>
                                    <td class="pClothBs"><fmt:message key="i18n.NPAL1140Scrn00.label.27" bundle="${I18N_SCREEN_ID}">Supplier Last<br>Update Date</fmt:message></td>
                                  </tr>
                                  </tbody>
                                </table>
                                </div>
                                <div style="OVERFLOW-Y: scroll; WIDTH: 1093px; HEIGHT: 196px; word-break:break-all">
                                <table id="B" cellSpacing="0" cellPadding="1" border="1" width="1076">
                                  <colgroup>
                                  <col align="middle" width="54">
                                  <col align="middle" width="51">
                                  <col align="middle" width="102">
                                  <col align="middle" width="48">
                                  <col align="middle" width="121">
                                  <col align="middle" width="170">
                                  <col align="middle" width="121">
                                  <col align="middle" width="99">
                                  <col align="middle" width="27">
                                  <col align="middle" width="76">
                                  <col align="middle" width="76">
                                  <col align="middle" width="76">
                                  <tbody>
                                    <ezf:row ezfHyo="B">
                                    <tr id="id_row{EZF_ROW_NUMBER}" height="28" >
                                      <td><ezf:label name="ediPoOrdDtlLineNum_B0" ezfName="ediPoOrdDtlLineNum_B0" ezfHyo="B" ezfArrayNo="0" /></td>
                                      <td><ezf:inputText name="dispPoDtlLineNum_B0" ezfName="dispPoDtlLineNum_B0" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"3\" ezftoupper=\"\""/></td>
                                      <td><ezf:label name="xxRef14Nm_B0" ezfName="xxRef14Nm_B0" ezfHyo="B" ezfArrayNo="0" /></td>
                                      <td><ezf:label name="poAckLineStsCd_B0" ezfName="poAckLineStsCd_B0" ezfHyo="B" ezfArrayNo="0" /></td>
                                      <td><ezf:inputText name="mdseCd_B0" ezfName="mdseCd_B0" value="123456780A123456" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></td>
                                      <td><ezf:inputText name="mdseDescShortTxt_B0" ezfName="mdseDescShortTxt_B0" value="123456780A123456789B123456789C" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"23\" maxlength=\"250\""/></td>
                                      <td><ezf:inputText name="origCusaMdseCd_B0" ezfName="origCusaMdseCd_B0" value="123456780A123456" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/></td>
                                      <td><ezf:inputText name="ordQty_B0" ezfName="ordQty_B0" value="1,234,567,890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
                                      <td><ezf:label name="uomCd_B0" ezfName="uomCd_B0" ezfHyo="B" ezfArrayNo="0" /></td>
                                      <td><ezf:label name="xxDtTxt_BD" ezfName="xxDtTxt_BD" ezfHyo="B" ezfArrayNo="0" /></td>
                                      <td><ezf:label name="xxDtTxt_BA" ezfName="xxDtTxt_BA" ezfHyo="B" ezfArrayNo="0" /></td>
                                      <td><ezf:label name="xxDtTxt_BU" ezfName="xxDtTxt_BU" ezfHyo="B" ezfArrayNo="0" /></td>
                                    </tr>
                                    <tr  height="28" >
                                      <td class="pClothBs" colspan="2"><ezf:label name="xxNum_B0" ezfName="xxNum_B0" ezfHyo="B" ezfArrayNo="0" /><fmt:message key="i18n.NPAL1140Scrn00.label.28" bundle="${I18N_SCREEN_ID}">Message(s)</fmt:message></td>
                                      <td colspan="10" align="left">                                                
                                        <ezf:select name="batErrMsgTxt_BV" ezfName="batErrMsgTxt_BV" ezfHyo="B" ezfArrayNo="0" ezfCodeName="batErrMsgTxt_BC" ezfDispName="batErrMsgTxt_BD" ezfCodeDispHyo="B" otherAttr=" style=\"margin-left: 6px; width:850\""/>
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
