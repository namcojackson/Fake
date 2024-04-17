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
                            <%-- Column Index 90 --%>
                                    <%  if (cIdx == 90) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_90" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_90" ezfName="xxDate_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_90" ezfName="xxString_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_90" ezfName="xxNumber_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_90" ezfName="xxYearmonth_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_90" ezfName="xxYear_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_90" ezfName="xxTime_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_90" ezfName="xxTs_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_90" ezfName="xxString_90" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_90" ezfName="xxString_90" ezfCodeName="xxListCd_90" ezfDispName="xxListNm_90" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_90" ezfName="xxString_90" ezfCodeName="xxListCd_90" ezfDispName="xxListNm_90" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_90" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_90" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_90" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_90" + "';\"";
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
                                                    <ezf:inputText name="xxString_90" ezfName="xxString_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_90" ezfName="xxNumber_90" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 91 --%>
                                    <%  if (cIdx == 91) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_91" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_91" ezfName="xxDate_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_91" ezfName="xxString_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_91" ezfName="xxNumber_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_91" ezfName="xxYearmonth_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_91" ezfName="xxYear_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_91" ezfName="xxTime_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_91" ezfName="xxTs_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_91" ezfName="xxString_91" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_91" ezfName="xxString_91" ezfCodeName="xxListCd_91" ezfDispName="xxListNm_91" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_91" ezfName="xxString_91" ezfCodeName="xxListCd_91" ezfDispName="xxListNm_91" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_91" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_91" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_91" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_91" + "';\"";
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
                                                    <ezf:inputText name="xxString_91" ezfName="xxString_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_91" ezfName="xxNumber_91" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 92 --%>
                                    <%  if (cIdx == 92) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_92" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_92" ezfName="xxDate_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_92" ezfName="xxString_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_92" ezfName="xxNumber_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_92" ezfName="xxYearmonth_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_92" ezfName="xxYear_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_92" ezfName="xxTime_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_92" ezfName="xxTs_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_92" ezfName="xxString_92" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_92" ezfName="xxString_92" ezfCodeName="xxListCd_92" ezfDispName="xxListNm_92" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_92" ezfName="xxString_92" ezfCodeName="xxListCd_92" ezfDispName="xxListNm_92" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_92" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_92" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_92" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_92" + "';\"";
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
                                                    <ezf:inputText name="xxString_92" ezfName="xxString_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_92" ezfName="xxNumber_92" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 93 --%>
                                    <%  if (cIdx == 93) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_93" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_93" ezfName="xxDate_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_93" ezfName="xxString_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_93" ezfName="xxNumber_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_93" ezfName="xxYearmonth_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_93" ezfName="xxYear_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_93" ezfName="xxTime_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_93" ezfName="xxTs_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_93" ezfName="xxString_93" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_93" ezfName="xxString_93" ezfCodeName="xxListCd_93" ezfDispName="xxListNm_93" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_93" ezfName="xxString_93" ezfCodeName="xxListCd_93" ezfDispName="xxListNm_93" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_93" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_93" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_93" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_93" + "';\"";
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
                                                    <ezf:inputText name="xxString_93" ezfName="xxString_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_93" ezfName="xxNumber_93" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 94 --%>
                                    <%  if (cIdx == 94) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_94" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_94" ezfName="xxDate_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_94" ezfName="xxString_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_94" ezfName="xxNumber_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_94" ezfName="xxYearmonth_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_94" ezfName="xxYear_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_94" ezfName="xxTime_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_94" ezfName="xxTs_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_94" ezfName="xxString_94" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_94" ezfName="xxString_94" ezfCodeName="xxListCd_94" ezfDispName="xxListNm_94" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_94" ezfName="xxString_94" ezfCodeName="xxListCd_94" ezfDispName="xxListNm_94" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_94" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_94" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_94" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_94" + "';\"";
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
                                                    <ezf:inputText name="xxString_94" ezfName="xxString_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_94" ezfName="xxNumber_94" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 95 --%>
                                    <%  if (cIdx == 95) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_95" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_95" ezfName="xxDate_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_95" ezfName="xxString_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_95" ezfName="xxNumber_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_95" ezfName="xxYearmonth_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_95" ezfName="xxYear_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_95" ezfName="xxTime_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_95" ezfName="xxTs_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_95" ezfName="xxString_95" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_95" ezfName="xxString_95" ezfCodeName="xxListCd_95" ezfDispName="xxListNm_95" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_95" ezfName="xxString_95" ezfCodeName="xxListCd_95" ezfDispName="xxListNm_95" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_95" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_95" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_95" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_95" + "';\"";
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
                                                    <ezf:inputText name="xxString_95" ezfName="xxString_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_95" ezfName="xxNumber_95" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 96 --%>
                                    <%  if (cIdx == 96) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_96" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_96" ezfName="xxDate_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_96" ezfName="xxString_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_96" ezfName="xxNumber_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_96" ezfName="xxYearmonth_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_96" ezfName="xxYear_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_96" ezfName="xxTime_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_96" ezfName="xxTs_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_96" ezfName="xxString_96" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_96" ezfName="xxString_96" ezfCodeName="xxListCd_96" ezfDispName="xxListNm_96" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_96" ezfName="xxString_96" ezfCodeName="xxListCd_96" ezfDispName="xxListNm_96" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_96" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_96" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_96" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_96" + "';\"";
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
                                                    <ezf:inputText name="xxString_96" ezfName="xxString_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_96" ezfName="xxNumber_96" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 97 --%>
                                    <%  if (cIdx == 97) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_97" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_97" ezfName="xxDate_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_97" ezfName="xxString_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_97" ezfName="xxNumber_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_97" ezfName="xxYearmonth_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_97" ezfName="xxYear_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_97" ezfName="xxTime_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_97" ezfName="xxTs_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_97" ezfName="xxString_97" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_97" ezfName="xxString_97" ezfCodeName="xxListCd_97" ezfDispName="xxListNm_97" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_97" ezfName="xxString_97" ezfCodeName="xxListCd_97" ezfDispName="xxListNm_97" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_97" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_97" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_97" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_97" + "';\"";
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
                                                    <ezf:inputText name="xxString_97" ezfName="xxString_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_97" ezfName="xxNumber_97" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 98 --%>
                                    <%  if (cIdx == 98) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_98" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_98" ezfName="xxDate_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_98" ezfName="xxString_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_98" ezfName="xxNumber_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_98" ezfName="xxYearmonth_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_98" ezfName="xxYear_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_98" ezfName="xxTime_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_98" ezfName="xxTs_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_98" ezfName="xxString_98" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_98" ezfName="xxString_98" ezfCodeName="xxListCd_98" ezfDispName="xxListNm_98" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_98" ezfName="xxString_98" ezfCodeName="xxListCd_98" ezfDispName="xxListNm_98" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_98" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_98" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_98" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_98" + "';\"";
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
                                                    <ezf:inputText name="xxString_98" ezfName="xxString_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_98" ezfName="xxNumber_98" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 99 --%>
                                    <%  if (cIdx == 99) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_99" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_99" ezfName="xxDate_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_99" ezfName="xxString_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_99" ezfName="xxNumber_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_99" ezfName="xxYearmonth_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_99" ezfName="xxYear_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_99" ezfName="xxTime_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_99" ezfName="xxTs_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_99" ezfName="xxString_99" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_99" ezfName="xxString_99" ezfCodeName="xxListCd_99" ezfDispName="xxListNm_99" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_99" ezfName="xxString_99" ezfCodeName="xxListCd_99" ezfDispName="xxListNm_99" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_99" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_99" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_99" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_99" + "';\"";
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
                                                    <ezf:inputText name="xxString_99" ezfName="xxString_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_99" ezfName="xxNumber_99" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
