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
                            <%-- Column Index 60 --%>
                                    <%  if (cIdx == 60) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_60" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_60" ezfName="xxDate_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_60" ezfName="xxString_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_60" ezfName="xxNumber_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_60" ezfName="xxYearmonth_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_60" ezfName="xxYear_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_60" ezfName="xxTime_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_60" ezfName="xxTs_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_60" ezfName="xxString_60" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_60" ezfName="xxString_60" ezfCodeName="xxListCd_60" ezfDispName="xxListNm_60" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_60" ezfName="xxString_60" ezfCodeName="xxListCd_60" ezfDispName="xxListNm_60" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_60" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_60" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_60" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_60" + "';\"";
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
                                                    <ezf:inputText name="xxString_60" ezfName="xxString_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_60" ezfName="xxNumber_60" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 61 --%>
                                    <%  if (cIdx == 61) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_61" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_61" ezfName="xxDate_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_61" ezfName="xxString_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_61" ezfName="xxNumber_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_61" ezfName="xxYearmonth_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_61" ezfName="xxYear_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_61" ezfName="xxTime_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_61" ezfName="xxTs_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_61" ezfName="xxString_61" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_61" ezfName="xxString_61" ezfCodeName="xxListCd_61" ezfDispName="xxListNm_61" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_61" ezfName="xxString_61" ezfCodeName="xxListCd_61" ezfDispName="xxListNm_61" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_61" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_61" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_61" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_61" + "';\"";
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
                                                    <ezf:inputText name="xxString_61" ezfName="xxString_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_61" ezfName="xxNumber_61" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 62 --%>
                                    <%  if (cIdx == 62) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_62" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_62" ezfName="xxDate_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_62" ezfName="xxString_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_62" ezfName="xxNumber_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_62" ezfName="xxYearmonth_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_62" ezfName="xxYear_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_62" ezfName="xxTime_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_62" ezfName="xxTs_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_62" ezfName="xxString_62" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_62" ezfName="xxString_62" ezfCodeName="xxListCd_62" ezfDispName="xxListNm_62" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_62" ezfName="xxString_62" ezfCodeName="xxListCd_62" ezfDispName="xxListNm_62" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_62" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_62" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_62" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_62" + "';\"";
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
                                                    <ezf:inputText name="xxString_62" ezfName="xxString_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_62" ezfName="xxNumber_62" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 63 --%>
                                    <%  if (cIdx == 63) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_63" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_63" ezfName="xxDate_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_63" ezfName="xxString_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_63" ezfName="xxNumber_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_63" ezfName="xxYearmonth_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_63" ezfName="xxYear_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_63" ezfName="xxTime_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_63" ezfName="xxTs_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_63" ezfName="xxString_63" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_63" ezfName="xxString_63" ezfCodeName="xxListCd_63" ezfDispName="xxListNm_63" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_63" ezfName="xxString_63" ezfCodeName="xxListCd_63" ezfDispName="xxListNm_63" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_63" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_63" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_63" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_63" + "';\"";
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
                                                    <ezf:inputText name="xxString_63" ezfName="xxString_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_63" ezfName="xxNumber_63" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 64 --%>
                                    <%  if (cIdx == 64) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_64" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_64" ezfName="xxDate_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_64" ezfName="xxString_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_64" ezfName="xxNumber_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_64" ezfName="xxYearmonth_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_64" ezfName="xxYear_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_64" ezfName="xxTime_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_64" ezfName="xxTs_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_64" ezfName="xxString_64" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_64" ezfName="xxString_64" ezfCodeName="xxListCd_64" ezfDispName="xxListNm_64" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_64" ezfName="xxString_64" ezfCodeName="xxListCd_64" ezfDispName="xxListNm_64" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_64" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_64" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_64" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_64" + "';\"";
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
                                                    <ezf:inputText name="xxString_64" ezfName="xxString_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_64" ezfName="xxNumber_64" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 65 --%>
                                    <%  if (cIdx == 65) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_65" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_65" ezfName="xxDate_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_65" ezfName="xxString_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_65" ezfName="xxNumber_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_65" ezfName="xxYearmonth_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_65" ezfName="xxYear_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_65" ezfName="xxTime_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_65" ezfName="xxTs_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_65" ezfName="xxString_65" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_65" ezfName="xxString_65" ezfCodeName="xxListCd_65" ezfDispName="xxListNm_65" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_65" ezfName="xxString_65" ezfCodeName="xxListCd_65" ezfDispName="xxListNm_65" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_65" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_65" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_65" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_65" + "';\"";
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
                                                    <ezf:inputText name="xxString_65" ezfName="xxString_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_65" ezfName="xxNumber_65" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 66 --%>
                                    <%  if (cIdx == 66) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_66" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_66" ezfName="xxDate_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_66" ezfName="xxString_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_66" ezfName="xxNumber_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_66" ezfName="xxYearmonth_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_66" ezfName="xxYear_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_66" ezfName="xxTime_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_66" ezfName="xxTs_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_66" ezfName="xxString_66" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_66" ezfName="xxString_66" ezfCodeName="xxListCd_66" ezfDispName="xxListNm_66" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_66" ezfName="xxString_66" ezfCodeName="xxListCd_66" ezfDispName="xxListNm_66" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_66" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_66" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_66" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_66" + "';\"";
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
                                                    <ezf:inputText name="xxString_66" ezfName="xxString_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_66" ezfName="xxNumber_66" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 67 --%>
                                    <%  if (cIdx == 67) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_67" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_67" ezfName="xxDate_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_67" ezfName="xxString_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_67" ezfName="xxNumber_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_67" ezfName="xxYearmonth_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_67" ezfName="xxYear_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_67" ezfName="xxTime_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_67" ezfName="xxTs_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_67" ezfName="xxString_67" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_67" ezfName="xxString_67" ezfCodeName="xxListCd_67" ezfDispName="xxListNm_67" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_67" ezfName="xxString_67" ezfCodeName="xxListCd_67" ezfDispName="xxListNm_67" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_67" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_67" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_67" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_67" + "';\"";
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
                                                    <ezf:inputText name="xxString_67" ezfName="xxString_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_67" ezfName="xxNumber_67" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 68 --%>
                                    <%  if (cIdx == 68) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_68" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_68" ezfName="xxDate_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_68" ezfName="xxString_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_68" ezfName="xxNumber_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_68" ezfName="xxYearmonth_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_68" ezfName="xxYear_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_68" ezfName="xxTime_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_68" ezfName="xxTs_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_68" ezfName="xxString_68" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_68" ezfName="xxString_68" ezfCodeName="xxListCd_68" ezfDispName="xxListNm_68" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_68" ezfName="xxString_68" ezfCodeName="xxListCd_68" ezfDispName="xxListNm_68" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_68" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_68" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_68" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_68" + "';\"";
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
                                                    <ezf:inputText name="xxString_68" ezfName="xxString_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_68" ezfName="xxNumber_68" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 69 --%>
                                    <%  if (cIdx == 69) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_69" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_69" ezfName="xxDate_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_69" ezfName="xxString_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_69" ezfName="xxNumber_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_69" ezfName="xxYearmonth_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_69" ezfName="xxYear_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_69" ezfName="xxTime_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_69" ezfName="xxTs_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_69" ezfName="xxString_69" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_69" ezfName="xxString_69" ezfCodeName="xxListCd_69" ezfDispName="xxListNm_69" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_69" ezfName="xxString_69" ezfCodeName="xxListCd_69" ezfDispName="xxListNm_69" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_69" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_69" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_69" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_69" + "';\"";
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
                                                    <ezf:inputText name="xxString_69" ezfName="xxString_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_69" ezfName="xxNumber_69" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
