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

<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant"%>
<%@ page import="business.servlet.NSAL9900.NSAL9900BMsg" %>
<%
    NSAL9900BMsg bMsg = (NSAL9900BMsg)databean.getEZDBMsg();

    String strBIdx = request.getParameter("bIdx");
    int bIdx = Integer.parseInt(strBIdx);
    String strCIdx = request.getParameter("cIdx");
    int cIdx = Integer.parseInt(strCIdx);
    String dplyCtrlCd = request.getParameter("dplyCtrlCd");
    String colTpCd = request.getParameter("colTpCd");
    String otherAttr = request.getParameter("otherAttr");
%>
                            <%-- Column Index 20 --%>
                                    <%  if (cIdx == 20) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_20" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_20" ezfName="xxDate_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_20" ezfName="xxString_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_20" ezfName="xxNumber_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_20" ezfName="xxYearmonth_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_20" ezfName="xxYear_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_20" ezfName="xxTime_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_20" ezfName="xxTs_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_20" ezfName="xxString_20" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_20" ezfName="xxString_20" ezfCodeName="xxListCd_20" ezfDispName="xxListNm_20" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_20" ezfName="xxString_20" ezfCodeName="xxListCd_20" ezfDispName="xxListNm_20" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_20" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_20" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_20" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_20" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_20" ezfName="xxString_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_20" ezfName="xxNumber_20" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 21 --%>
                                    <%  if (cIdx == 21) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_21" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_21" ezfName="xxDate_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_21" ezfName="xxString_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_21" ezfName="xxNumber_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_21" ezfName="xxYearmonth_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_21" ezfName="xxYear_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_21" ezfName="xxTime_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_21" ezfName="xxTs_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_21" ezfName="xxString_21" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_21" ezfName="xxString_21" ezfCodeName="xxListCd_21" ezfDispName="xxListNm_21" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_21" ezfName="xxString_21" ezfCodeName="xxListCd_21" ezfDispName="xxListNm_21" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_21" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_21" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_21" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_21" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_21" ezfName="xxString_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_21" ezfName="xxNumber_21" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 22 --%>
                                    <%  if (cIdx == 22) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_22" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_22" ezfName="xxDate_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_22" ezfName="xxString_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_22" ezfName="xxNumber_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_22" ezfName="xxYearmonth_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_22" ezfName="xxYear_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_22" ezfName="xxTime_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_22" ezfName="xxTs_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_22" ezfName="xxString_22" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_22" ezfName="xxString_22" ezfCodeName="xxListCd_22" ezfDispName="xxListNm_22" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_22" ezfName="xxString_22" ezfCodeName="xxListCd_22" ezfDispName="xxListNm_22" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_22" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_22" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_22" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_22" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_22" ezfName="xxString_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_22" ezfName="xxNumber_22" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 23 --%>
                                    <%  if (cIdx == 23) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_23" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_23" ezfName="xxDate_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_23" ezfName="xxString_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_23" ezfName="xxNumber_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_23" ezfName="xxYearmonth_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_23" ezfName="xxYear_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_23" ezfName="xxTime_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_23" ezfName="xxTs_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_23" ezfName="xxString_23" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_23" ezfName="xxString_23" ezfCodeName="xxListCd_23" ezfDispName="xxListNm_23" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_23" ezfName="xxString_23" ezfCodeName="xxListCd_23" ezfDispName="xxListNm_23" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_23" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_23" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_23" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_23" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_23" ezfName="xxString_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_23" ezfName="xxNumber_23" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 24 --%>
                                    <%  if (cIdx == 24) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_24" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_24" ezfName="xxDate_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_24" ezfName="xxString_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_24" ezfName="xxNumber_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_24" ezfName="xxYearmonth_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_24" ezfName="xxYear_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_24" ezfName="xxTime_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_24" ezfName="xxTs_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_24" ezfName="xxString_24" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_24" ezfName="xxString_24" ezfCodeName="xxListCd_24" ezfDispName="xxListNm_24" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_24" ezfName="xxString_24" ezfCodeName="xxListCd_24" ezfDispName="xxListNm_24" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_24" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_24" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_24" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_24" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_24" ezfName="xxString_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_24" ezfName="xxNumber_24" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 25 --%>
                                    <%  if (cIdx == 25) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_25" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_25" ezfName="xxDate_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_25" ezfName="xxString_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_25" ezfName="xxNumber_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_25" ezfName="xxYearmonth_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_25" ezfName="xxYear_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_25" ezfName="xxTime_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_25" ezfName="xxTs_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_25" ezfName="xxString_25" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_25" ezfName="xxString_25" ezfCodeName="xxListCd_25" ezfDispName="xxListNm_25" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_25" ezfName="xxString_25" ezfCodeName="xxListCd_25" ezfDispName="xxListNm_25" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_25" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_25" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_25" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_25" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_25" ezfName="xxString_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_25" ezfName="xxNumber_25" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 26 --%>
                                    <%  if (cIdx == 26) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_26" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_26" ezfName="xxDate_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_26" ezfName="xxString_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_26" ezfName="xxNumber_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_26" ezfName="xxYearmonth_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_26" ezfName="xxYear_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_26" ezfName="xxTime_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_26" ezfName="xxTs_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_26" ezfName="xxString_26" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_26" ezfName="xxString_26" ezfCodeName="xxListCd_26" ezfDispName="xxListNm_26" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_26" ezfName="xxString_26" ezfCodeName="xxListCd_26" ezfDispName="xxListNm_26" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_26" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_26" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_26" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_26" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_26" ezfName="xxString_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_26" ezfName="xxNumber_26" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 27 --%>
                                    <%  if (cIdx == 27) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_27" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_27" ezfName="xxDate_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_27" ezfName="xxString_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_27" ezfName="xxNumber_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_27" ezfName="xxYearmonth_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_27" ezfName="xxYear_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_27" ezfName="xxTime_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_27" ezfName="xxTs_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_27" ezfName="xxString_27" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_27" ezfName="xxString_27" ezfCodeName="xxListCd_27" ezfDispName="xxListNm_27" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_27" ezfName="xxString_27" ezfCodeName="xxListCd_27" ezfDispName="xxListNm_27" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_27" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_27" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_27" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_27" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_27" ezfName="xxString_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_27" ezfName="xxNumber_27" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 28 --%>
                                    <%  if (cIdx == 28) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_28" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_28" ezfName="xxDate_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_28" ezfName="xxString_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_28" ezfName="xxNumber_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_28" ezfName="xxYearmonth_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_28" ezfName="xxYear_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_28" ezfName="xxTime_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_28" ezfName="xxTs_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_28" ezfName="xxString_28" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_28" ezfName="xxString_28" ezfCodeName="xxListCd_28" ezfDispName="xxListNm_28" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_28" ezfName="xxString_28" ezfCodeName="xxListCd_28" ezfDispName="xxListNm_28" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_28" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_28" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_28" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_28" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_28" ezfName="xxString_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_28" ezfName="xxNumber_28" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 29 --%>
                                    <%  if (cIdx == 29) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_29" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_29" ezfName="xxDate_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_29" ezfName="xxString_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_29" ezfName="xxNumber_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_29" ezfName="xxYearmonth_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_29" ezfName="xxYear_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_29" ezfName="xxTime_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_29" ezfName="xxTs_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_29" ezfName="xxString_29" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_29" ezfName="xxString_29" ezfCodeName="xxListCd_29" ezfDispName="xxListNm_29" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_29" ezfName="xxString_29" ezfCodeName="xxListCd_29" ezfDispName="xxListNm_29" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_29" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_29" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_29" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_29" + "';\"";
                                                }
                                                String inacFlg = bMsg.C.no(cIdx).itemInacFlg.getValue();
                                                String uniqKeyFlg = bMsg.C.no(cIdx).uniqKeyFlg.getValue();
                                                if (ZYPConstant.FLG_ON_Y.equals(inacFlg)
                                                        || (ZYPCommonFunc.hasValue(bMsg.B.no(bIdx).ezUpTime) && ZYPConstant.FLG_ON_Y.equals(uniqKeyFlg))) {
                                                    otherAttrButton += otherAttrButton + " disabled";
                                                }
                                        %>
                                                <td>
                                                    <ezf:inputButton name="OpenWin_CommonCodePopup" value="..." htmlClass="pBtn0" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttrButton %>" />
                                        <%      if (colTpCd.equals("String")) { %>
                                                    <ezf:inputText name="xxString_29" ezfName="xxString_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_29" ezfName="xxNumber_29" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>