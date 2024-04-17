<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20150527182041 --%>
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
<fmt:setBundle basename="I18N_NSAL9900Scrn00" var="I18N_SCREEN_ID" scope="request" />
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
            <input type="hidden" name="pageID" value="NSAL9900Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="<fmt:message key="i18n.NSAL9900Scrn00.title" bundle="${I18N_SCREEN_ID}">General Business Master Maintenance</fmt:message>">
<center>
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
            <td>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="General Business Master Maintenance" class="pTab_ON"><a href="#">Mstr Maint</a></li>
                                    </div>
                                </td>
                                <td valign="bottom" align="center">
                                    <a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
                                </td>
                                <td valign="bottom" align="center">
                                    <a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
                                </td>
                            </tr>
                        </table>
                    </ul>
                </div>
                </ezf:skip>
                <div class="pTab_BODY">

<%@ page import="business.servlet.NSAL9900.NSAL9900BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant"%>
<%@ page import="java.util.ArrayList" %>
<% NSAL9900BMsg bMsg = (NSAL9900BMsg)databean.getEZDBMsg(); %>

<%-- **************************************************************************************** --%>
<%-- **************************************** Header **************************************** --%>

                    <ezf:inputHidden name="xxPopPrm_NM" ezfName="xxPopPrm_NM" value="" />
                    <table border="0">
                        <col width="120">
                        <col width="200">
                        <tr>
                            <td class="stab"><fmt:message key="i18n.NSAL9900Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Master Name</fmt:message></td>
                            <td><ezf:inputText name="tblNm" ezfName="tblNm" value=""  otherAttr=" size=\"30\" maxlength=\"60\"" /></td>
                        </tr>
                    </table>
                    <table>
                        <col width="120">
                        <col width="160">
                        <col width="120">
                        <col width="160">
                        <col width="120">
                        <col width="160">
                        <col width="120">
                        <col width="160">
                        <tr>
                        <%
                            int srchCondRowCnt = 1;
                            int aIdx = 0;
                        %>
                        <%-- Loop for count of search criteria --%>
                        <ezf:row ezfHyo="A">

                        <%
                            int cIdx = bMsg.A.no(aIdx).xxRowNum_A.getValueInt(); 
                            String ezfName = bMsg.C.no(cIdx).searchItemNm.getValue();
                            String labelNm = EZDCommonFunc.toHTMLString(bMsg.C.no(cIdx).logicMaintTrgtColNm.getValue());
                            String likeSrchFlg = bMsg.C.no(cIdx).likeSrchFlg.getValue();
                            if (ZYPConstant.FLG_ON_Y.equals(likeSrchFlg)) {
                                labelNm += " (*)";
                            }
                            String colTpCd = bMsg.C.no(cIdx).colTpCd.getValue();
                            String dplyCtrlCd = bMsg.C.no(cIdx).dplyCtrlCd.getValue();
                            int size = bMsg.C.no(cIdx).itemDplyLg.getValueInt();
                            int maxSize = bMsg.C.no(cIdx).itemMaxLg.getValueInt();
                            int scale = bMsg.C.no(cIdx).itemSclNum.getValueInt();
                            if (colTpCd.equals("Date")) {
                                maxSize += 2; // add slash
                            } else if (colTpCd.equals("YearMonth")) {
                                maxSize += 1; // add slash
                            } else if (colTpCd.equals("Number")) {
                                if (scale > 0) {
                                    maxSize += 1; // add period
                                }
                            }
                            // Other Attribute
                            String otherAttr = "";
                            String _size = " size=\"" + String.valueOf(size) + "\"";
                            String _maxlength = " maxlength=\"" + maxSize + "\"";
                            String _id = " id=\"" + ezfName + "#" + String.valueOf(aIdx) + "\"";
                            String _toUpper = "";
                            if (ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).toUpperFlg.getValue())) {
                                _toUpper = " ezftoupper=\"\"";
                            }
                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                otherAttr = _size + _maxlength + _id;
                            } else if (dplyCtrlCd.equals("TextBox")) {
                                otherAttr = _size + _maxlength + _id + _toUpper;
                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                otherAttr = _id;
                            } else if (dplyCtrlCd.equals("PullDown")) {
                                otherAttr = "style=\"width:" + String.valueOf(size * 8) + "\"" + _id;
                            } else if (dplyCtrlCd.equals("Popup")) {
                                otherAttr += _size + _maxlength + _id + _toUpper;
                            }

                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                String onclick = "calendar('" + ezfName + "#" + String.valueOf(aIdx) + "', 4);";
                        %>
                            <td class="stab"><%= labelNm %></td>
                            <td>
                                <ezf:inputText name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                <ezf:inputHidden name="xxString_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%
                            } else if (dplyCtrlCd.equals("TextBox")) {
                        %>
                            <td class="stab"><%= labelNm %></td>
                        <%      if (colTpCd.equals("String")) { %>
                            <td>
                                <ezf:inputText name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%      } else if (colTpCd.equals("Number")) { %>
                            <td>
                                <ezf:inputHidden name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputText name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                            <td>
                                <ezf:inputHidden name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputText name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%      } else if (colTpCd.equals("Year")) { %>
                            <td>
                                <ezf:inputHidden name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputText name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%      } else if (colTpCd.equals("Time")) { %>
                            <td>
                                <ezf:inputHidden name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputText name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%      } else if (colTpCd.equals("Ts")) { %>
                            <td>
                                <ezf:inputHidden name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputText name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%
                                }
                            } else if (dplyCtrlCd.equals("CheckBox")) {
                        %>
                            <td class="stab"><%= labelNm %></td>
                            <td>
                                <ezf:inputCheckBox name="xxString_A" ezfName="xxString_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%
                            } else if (dplyCtrlCd.equals("PullDown")) {
                        %>
                            <td class="stab"><%= labelNm %></td>
                            <td>
                                <ezf:select name="xxString_A" ezfName="xxString_A" ezfCodeName="xxListCd_A" ezfDispName="xxListNm_A" ezfHyo="A" ezfCodeDispHyo="A" ezfBlank="1" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxLinkAncr_A" ezfName="xxLinkAncr_A" value="" ezfHyo="A" ezfArrayNo="0" />
                            </td>
                        <%
                            } else if (dplyCtrlCd.equals("Popup")) {
                                String otherAttrAnchor = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + ezfName + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + ezfName + "';\"";
                        %>
                            <td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_A" ezfEmulateBtn="1" ezfHyo="A" ezfArrayNo="0" ezfGuard="OpenWin_CommonCodePopup" otherAttr="<%=otherAttrAnchor %>"><%= labelNm %></ezf:anchor></td>
                            <td>
                        <%      if (colTpCd.equals("String")) { %>
                                <ezf:inputText name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                        <%      } else if (colTpCd.equals("Number")) { %>
                                <ezf:inputHidden name="xxString_A" ezfName="xxString_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputText name="xxNumber_A" ezfName="xxNumber_A" value="" ezfHyo="A" ezfArrayNo="0" otherAttr="<%=otherAttr %>" />
                                <ezf:inputHidden name="xxDate_A" ezfName="xxDate_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYearmonth_A" ezfName="xxYearmonth_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxYear_A" ezfName="xxYear_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTime_A" ezfName="xxTime_A" value="" ezfHyo="A" ezfArrayNo="0" />
                                <ezf:inputHidden name="xxTs_A" ezfName="xxTs_A" value="" ezfHyo="A" ezfArrayNo="0" />
                        <%      } %>
                            </td>
                        <%
                            } else {
                        %>
                            <td class="stab">'dplyCtrlCd' is not match</td>
                        <%
                            }
                        %>

                        <%
                            int itemCount = aIdx + 1;
                            if (itemCount % 4 == 0 && itemCount < bMsg.A.getValidCount()) {
                                // exist next Search Condition Row
                                srchCondRowCnt++;
                        %>
                        </tr>
                        <tr>
                        <%
                            }
                            aIdx++;
                        %>
                        </ezf:row>
                        </tr>
                    </table>

                    <table  border="0" cellpadding="0" cellspacing="0" width="100%">
                        <col width="" align="right" valign="top">
                        <tr>
                            <td>
                                <table border="0" cellpadding="1" cellspacing="0">
                                    <col width="80">
                                    <tr>
                                        <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <hr>

<%-- **************************************************************************************** --%>
<%-- **************************************** Detail **************************************** --%>
                    <table>
                        <cal width="70">
                        <cal width="70">
                        <cal width="980" align="right">
                        <cal width="100">
                        <tr>
                            <td><ezf:inputButton name="Select_All" value="Select All" htmlClass="pBtn7" /></td>
                            <td><ezf:inputButton name="Un_Select_All" value="Unselect All" htmlClass="pBtn7" /></td>

                            <%-- **************************************** Pagination **************************************** --%>
                            <td align="right" width="935">
                                <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                    <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                    <jsp:param name="TableNm"     value="B" />
                                    <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
                                    <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
                                    <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
                                </jsp:include>
                                <ezf:skip>
                                <table border="0" cellpadding="1" cellspacing="0">
                                    <col width="54"  align="center">
                                    <col width="40"  align="right">
                                    <col width="16"  align="center">
                                    <col width="40"  align="right">
                                    <col width="16"  align="center">
                                    <col width="40"  align="right">
                                    <col width="10">
                                    <col>
                                    <col width="1">
                                    <col>
                                    <tr>
                                        <td class="stab">Showing</td>
                                        <td class="pOut">1</td>
                                        <td class="stab">to</td>
                                        <td class="pOut">20</td>
                                        <td class="stab">of</td>
                                        <td class="pOut">1000</td>
                                        <td>&nbsp;</td>
                                        <td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
                                        <td></td>
                                        <td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
                                    </tr>
                                </table>
                                </ezf:skip>
                            </td>
                        </tr>
                    </table>

                    <table>
                        <tr>
                            <td>
                                <%-- **************************************** Table Top **************************************** --%>
                                    <%
                                    int tableTopWidth = 2 + 40;
                                    for (int cIdx = 0; cIdx < bMsg.C.getValidCount(); cIdx++) {
                                        if (!ZYPCommonFunc.hasValue(bMsg.C.no(cIdx).dplySortNum)) {
                                            continue;
                                        }
                                        tableTopWidth += bMsg.C.no(cIdx).xxWidth.getValueInt();
                                    }
                                    if (tableTopWidth > 1110) {
                                        tableTopWidth = 1110;
                                    }
                                    int tableMainWidth = tableTopWidth + 17;
                                    int tableMainHigh = 369 - 25 * srchCondRowCnt;
                                    String tableTopStyle = "overflow-x:hidden; width:" + tableTopWidth + "; overflow-y:none; height:20; word-wrap:break-word;";
                                    String tableMainStyle = "overflow-x:scroll; width:" + tableMainWidth + "; overflow-y:scroll; height:" + String.valueOf(tableMainHigh) + ";";
                                    %>
                                
                                <div id="topTBL" style="<%= tableTopStyle %>">
                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">

                                        <col width="40" align="center">
                                    <%
                                    for (int cIdx = 0; cIdx < bMsg.C.getValidCount(); cIdx++) {
                                        if (!ZYPCommonFunc.hasValue(bMsg.C.no(cIdx).dplySortNum)) {
                                            continue;
                                        }
                                        int width = bMsg.C.no(cIdx).xxWidth.getValueInt();
                                    %>
                                        <col width="<%=width %>" align="center">
                                    <%
                                    }
                                    %>
                                        <tr height="37">
                                            <td class="pClothBs">&nbsp;</td>
                                    <%
                                    for (int cIdx = 0; cIdx < bMsg.C.getValidCount(); cIdx++) {
                                        if (!ZYPCommonFunc.hasValue(bMsg.C.no(cIdx).dplySortNum)) {
                                            continue;
                                        }
                                        String labelNm = bMsg.C.no(cIdx).logicMaintTrgtColNm.getValue();
                                        String labelId = bMsg.C.no(cIdx).detailItemNm.getValue();
                                    %>
                                            <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('B', '<%= labelId %>')"><%= labelNm %></a><img id="sortIMG.<%= labelId %>" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></td>
                                    <%
                                    }
                                    %>
                                        </tr>

                                    </table>
                                </div>

                                <%-- **************************************** Table Main **************************************** --%>
                                <div id="mainTBL" style="<%= tableMainStyle %>" onScroll="synchroScrollLeft( this.id, new Array( 'topTBL' ) );">
                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="B1">
                                        <col width="40" align="center">
                                    <%
                                    for (int cIdx = 0; cIdx < bMsg.C.getValidCount(); cIdx++) {
                                        if (!ZYPCommonFunc.hasValue(bMsg.C.no(cIdx).dplySortNum)) {
                                            continue;
                                        }
                                        int width = bMsg.C.no(cIdx).xxWidth.getValueInt();
                                    %>
                                        <col width="<%=width %>" align="center">
                                    <%
                                    }
                                    int bIdx = 0;
                                    %>
                                    <%-- Loop for search result count --%>
                                    <ezf:row ezfHyo="B">
                                        <tr height="25">
                                            <td><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox#{EZF_ROW_NUMBER}\""/></td>
                                    
                                    <%-- Loop for column count --%>
                                    <%
                                    for (int cIdx = 0; cIdx < bMsg.C.getValidCount(); cIdx++) {

                                        if (!ZYPCommonFunc.hasValue(bMsg.C.no(cIdx).dplySortNum)) {
                                            continue;
                                        }
                                        String ezfName = bMsg.C.no(cIdx).detailItemNm.getValue();
                                        String colTpCd = bMsg.C.no(cIdx).colTpCd.getValue();
                                        String dplyCtrlCd = bMsg.C.no(cIdx).dplyCtrlCd.getValue();
                                        int size = bMsg.C.no(cIdx).itemDplyLg.getValueInt();
                                        int maxSize = bMsg.C.no(cIdx).itemMaxLg.getValueInt();
                                        int scale = bMsg.C.no(cIdx).itemSclNum.getValueInt();
                                        if (colTpCd.equals("Date")) {
                                            maxSize += 2; // add slash
                                        } else if (colTpCd.equals("YearMonth")) {
                                            maxSize += 1; // add slash
                                        } else if (colTpCd.equals("Number")) {
                                            if (scale > 0) {
                                                maxSize += 1; // add period
                                            }
                                        }
                                        // Other Attribute
                                        String otherAttr = "";
                                        String _size = " size=\"" + String.valueOf(size) + "\"";
                                        String _maxlength = " maxlength=\"" + maxSize + "\"";
                                        String _id = " id=\"" + ezfName + "#" + String.valueOf(bIdx) + "\"";
                                        String _toUpper = "";
                                        if (ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).toUpperFlg.getValue())) {
                                            _toUpper = " ezftoupper=\"\"";
                                        }
                                        if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                            otherAttr = _size + _maxlength + _id;
                                        } else if (dplyCtrlCd.equals("TextBox")) {
                                            otherAttr = _size + _maxlength + _id + _toUpper;
                                        } else if (dplyCtrlCd.equals("CheckBox")) {
                                            otherAttr = _id;
                                        } else if (dplyCtrlCd.equals("PullDown")) {
                                            otherAttr = "style=\"width:" + String.valueOf(size * 8) + "\"" + _id;
                                        } else if (dplyCtrlCd.equals("Popup")) {
                                            otherAttr = _size + _maxlength + _id + _toUpper;
                                        }
                                    %>
                                        <%-- Column Information --%>
                                    <%  if (cIdx < 10) { %>
	                                        <jsp:include page="NSAL9900Scrn01.jsp" flush="true" >
	                                            <jsp:param name="bIdx" value="<%= bIdx %>" />
	                                            <jsp:param name="cIdx" value="<%= cIdx %>" />
	                                            <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
	                                            <jsp:param name="colTpCd" value="<%= colTpCd %>" />
	                                            <jsp:param name="otherAttr" value="<%= otherAttr %>" />
	                                        </jsp:include>
                                    <%  } %>
                                    <%  if (9 < cIdx && cIdx < 20) { %>
	                                        <jsp:include page="NSAL9900Scrn02.jsp" flush="true" >
	                                            <jsp:param name="bIdx" value="<%= bIdx %>" />
	                                            <jsp:param name="cIdx" value="<%= cIdx %>" />
	                                            <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
	                                            <jsp:param name="colTpCd" value="<%= colTpCd %>" />
	                                            <jsp:param name="otherAttr" value="<%= otherAttr %>" />
	                                        </jsp:include>
                                    <%  } %>
                                    <%  if (19 < cIdx && cIdx < 30) { %>
	                                        <jsp:include page="NSAL9900Scrn03.jsp" flush="true" >
	                                            <jsp:param name="bIdx" value="<%= bIdx %>" />
	                                            <jsp:param name="cIdx" value="<%= cIdx %>" />
	                                            <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
	                                            <jsp:param name="colTpCd" value="<%= colTpCd %>" />
	                                            <jsp:param name="otherAttr" value="<%= otherAttr %>" />
	                                        </jsp:include>
                                    <%  } %>
                                    <%  if (29 < cIdx && cIdx < 40) { %>
                                            <jsp:include page="NSAL9900Scrn04.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%  if (39 < cIdx && cIdx < 50) { %>
                                            <jsp:include page="NSAL9900Scrn05.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%  if (49 < cIdx && cIdx < 60) { %>
                                            <jsp:include page="NSAL9900Scrn06.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%  if (59 < cIdx && cIdx < 70) { %>
                                            <jsp:include page="NSAL9900Scrn07.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%  if (69 < cIdx && cIdx < 80) { %>
                                            <jsp:include page="NSAL9900Scrn08.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%  if (79 < cIdx && cIdx < 90) { %>
                                            <jsp:include page="NSAL9900Scrn09.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%  if (89 < cIdx) { %>
                                            <jsp:include page="NSAL9900Scrn10.jsp" flush="true" >
                                                <jsp:param name="bIdx" value="<%= bIdx %>" />
                                                <jsp:param name="cIdx" value="<%= cIdx %>" />
                                                <jsp:param name="dplyCtrlCd" value="<%= dplyCtrlCd %>" />
                                                <jsp:param name="colTpCd" value="<%= colTpCd %>" />
                                                <jsp:param name="otherAttr" value="<%= otherAttr %>" />
                                            </jsp:include>
                                    <%  } %>
                                    <%
                                    }
                                    %>
                                            <td class="pAuditInfo">
                                                <ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" />
                                                <ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" />
                                                <ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" />
                                                <ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" />
                                                <ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" />
                                           </td>
                                        </tr>
                                    <%
                                    bIdx++;
                                    %>
                                    </ezf:row>
                                        <ezf:skip>
                                        </ezf:skip>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <table>
                        <cal width="120">
                        <cal width="120">
                        <tr>
                            <td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
                            <td><ezf:inputButton name="CopyRow" value="Copy Row" htmlClass="pBtn7" /></td>
                            <td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
                        </tr>
                    </table>

                    <table>
                        <cal width="120">
                        <cal width="120">
                        <tr>
                            <td class="stab"><ezf:anchor name="DownloadTemplate" ezfName="DownloadTemplate" ezfEmulateBtn ="1" ezfGuard="DownloadTemplate" ><fmt:message key="i18n.NSAL9900Scrn00.label.2" bundle="${I18N_SCREEN_ID}">Download Template</fmt:message></ezf:anchor></td>
                            <td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"90\" maxlength=\"9999\""/>    </td>
                            <td><ezf:inputButton name="UploadCsv" value="Upload" htmlClass="pBtn7" /></td>
                        </tr>
                    </table>

                </div>
            </td>
        </tr>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
