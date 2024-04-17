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
                            <%-- Column Index 40 --%>
                                    <%  if (cIdx == 40) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_40" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_40" ezfName="xxDate_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_40" ezfName="xxString_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_40" ezfName="xxNumber_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_40" ezfName="xxYearmonth_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_40" ezfName="xxYear_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_40" ezfName="xxTime_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_40" ezfName="xxTs_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_40" ezfName="xxString_40" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_40" ezfName="xxString_40" ezfCodeName="xxListCd_40" ezfDispName="xxListNm_40" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_40" ezfName="xxString_40" ezfCodeName="xxListCd_40" ezfDispName="xxListNm_40" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_40" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_40" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_40" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_40" + "';\"";
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
                                                    <ezf:inputText name="xxString_40" ezfName="xxString_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_40" ezfName="xxNumber_40" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 41 --%>
                                    <%  if (cIdx == 41) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_41" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_41" ezfName="xxDate_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_41" ezfName="xxString_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_41" ezfName="xxNumber_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_41" ezfName="xxYearmonth_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_41" ezfName="xxYear_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_41" ezfName="xxTime_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_41" ezfName="xxTs_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_41" ezfName="xxString_41" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_41" ezfName="xxString_41" ezfCodeName="xxListCd_41" ezfDispName="xxListNm_41" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_41" ezfName="xxString_41" ezfCodeName="xxListCd_41" ezfDispName="xxListNm_41" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_41" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_41" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_41" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_41" + "';\"";
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
                                                    <ezf:inputText name="xxString_41" ezfName="xxString_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_41" ezfName="xxNumber_41" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 42 --%>
                                    <%  if (cIdx == 42) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_42" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_42" ezfName="xxDate_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_42" ezfName="xxString_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_42" ezfName="xxNumber_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_42" ezfName="xxYearmonth_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_42" ezfName="xxYear_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_42" ezfName="xxTime_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_42" ezfName="xxTs_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_42" ezfName="xxString_42" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_42" ezfName="xxString_42" ezfCodeName="xxListCd_42" ezfDispName="xxListNm_42" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_42" ezfName="xxString_42" ezfCodeName="xxListCd_42" ezfDispName="xxListNm_42" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_42" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_42" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_42" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_42" + "';\"";
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
                                                    <ezf:inputText name="xxString_42" ezfName="xxString_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_42" ezfName="xxNumber_42" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 43 --%>
                                    <%  if (cIdx == 43) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_43" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_43" ezfName="xxDate_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_43" ezfName="xxString_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_43" ezfName="xxNumber_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_43" ezfName="xxYearmonth_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_43" ezfName="xxYear_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_43" ezfName="xxTime_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_43" ezfName="xxTs_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_43" ezfName="xxString_43" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_43" ezfName="xxString_43" ezfCodeName="xxListCd_43" ezfDispName="xxListNm_43" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_43" ezfName="xxString_43" ezfCodeName="xxListCd_43" ezfDispName="xxListNm_43" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_43" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_43" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_43" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_43" + "';\"";
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
                                                    <ezf:inputText name="xxString_43" ezfName="xxString_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_43" ezfName="xxNumber_43" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 44 --%>
                                    <%  if (cIdx == 44) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_44" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_44" ezfName="xxDate_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_44" ezfName="xxString_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_44" ezfName="xxNumber_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_44" ezfName="xxYearmonth_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_44" ezfName="xxYear_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_44" ezfName="xxTime_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_44" ezfName="xxTs_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_44" ezfName="xxString_44" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_44" ezfName="xxString_44" ezfCodeName="xxListCd_44" ezfDispName="xxListNm_44" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_44" ezfName="xxString_44" ezfCodeName="xxListCd_44" ezfDispName="xxListNm_44" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_44" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_44" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_44" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_44" + "';\"";
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
                                                    <ezf:inputText name="xxString_44" ezfName="xxString_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_44" ezfName="xxNumber_44" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 45 --%>
                                    <%  if (cIdx == 45) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_45" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_45" ezfName="xxDate_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_45" ezfName="xxString_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_45" ezfName="xxNumber_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_45" ezfName="xxYearmonth_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_45" ezfName="xxYear_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_45" ezfName="xxTime_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_45" ezfName="xxTs_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_45" ezfName="xxString_45" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_45" ezfName="xxString_45" ezfCodeName="xxListCd_45" ezfDispName="xxListNm_45" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_45" ezfName="xxString_45" ezfCodeName="xxListCd_45" ezfDispName="xxListNm_45" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_45" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_45" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_45" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_45" + "';\"";
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
                                                    <ezf:inputText name="xxString_45" ezfName="xxString_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_45" ezfName="xxNumber_45" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 46 --%>
                                    <%  if (cIdx == 46) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_46" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_46" ezfName="xxDate_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_46" ezfName="xxString_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_46" ezfName="xxNumber_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_46" ezfName="xxYearmonth_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_46" ezfName="xxYear_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_46" ezfName="xxTime_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_46" ezfName="xxTs_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_46" ezfName="xxString_46" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_46" ezfName="xxString_46" ezfCodeName="xxListCd_46" ezfDispName="xxListNm_46" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_46" ezfName="xxString_46" ezfCodeName="xxListCd_46" ezfDispName="xxListNm_46" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_46" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_46" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_46" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_46" + "';\"";
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
                                                    <ezf:inputText name="xxString_46" ezfName="xxString_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_46" ezfName="xxNumber_46" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 47 --%>
                                    <%  if (cIdx == 47) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_47" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_47" ezfName="xxDate_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_47" ezfName="xxString_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_47" ezfName="xxNumber_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_47" ezfName="xxYearmonth_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_47" ezfName="xxYear_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_47" ezfName="xxTime_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_47" ezfName="xxTs_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_47" ezfName="xxString_47" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_47" ezfName="xxString_47" ezfCodeName="xxListCd_47" ezfDispName="xxListNm_47" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_47" ezfName="xxString_47" ezfCodeName="xxListCd_47" ezfDispName="xxListNm_47" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_47" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_47" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_47" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_47" + "';\"";
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
                                                    <ezf:inputText name="xxString_47" ezfName="xxString_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_47" ezfName="xxNumber_47" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 48 --%>
                                    <%  if (cIdx == 48) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_48" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_48" ezfName="xxDate_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_48" ezfName="xxString_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_48" ezfName="xxNumber_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_48" ezfName="xxYearmonth_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_48" ezfName="xxYear_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_48" ezfName="xxTime_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_48" ezfName="xxTs_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_48" ezfName="xxString_48" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_48" ezfName="xxString_48" ezfCodeName="xxListCd_48" ezfDispName="xxListNm_48" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_48" ezfName="xxString_48" ezfCodeName="xxListCd_48" ezfDispName="xxListNm_48" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_48" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_48" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_48" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_48" + "';\"";
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
                                                    <ezf:inputText name="xxString_48" ezfName="xxString_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_48" ezfName="xxNumber_48" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 49 --%>
                                    <%  if (cIdx == 49) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_49" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_49" ezfName="xxDate_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_49" ezfName="xxString_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_49" ezfName="xxNumber_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_49" ezfName="xxYearmonth_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_49" ezfName="xxYear_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_49" ezfName="xxTime_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_49" ezfName="xxTs_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_49" ezfName="xxString_49" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_49" ezfName="xxString_49" ezfCodeName="xxListCd_49" ezfDispName="xxListNm_49" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_49" ezfName="xxString_49" ezfCodeName="xxListCd_49" ezfDispName="xxListNm_49" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_49" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_49" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_49" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_49" + "';\"";
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
                                                    <ezf:inputText name="xxString_49" ezfName="xxString_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_49" ezfName="xxNumber_49" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
