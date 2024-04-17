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
                            <%-- Column Index 70 --%>
                                    <%  if (cIdx == 70) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_70" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_70" ezfName="xxDate_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_70" ezfName="xxString_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_70" ezfName="xxNumber_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_70" ezfName="xxYearmonth_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_70" ezfName="xxYear_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_70" ezfName="xxTime_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_70" ezfName="xxTs_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_70" ezfName="xxString_70" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_70" ezfName="xxString_70" ezfCodeName="xxListCd_70" ezfDispName="xxListNm_70" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_70" ezfName="xxString_70" ezfCodeName="xxListCd_70" ezfDispName="xxListNm_70" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_70" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_70" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_70" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_70" + "';\"";
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
                                                    <ezf:inputText name="xxString_70" ezfName="xxString_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_70" ezfName="xxNumber_70" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 71 --%>
                                    <%  if (cIdx == 71) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_71" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_71" ezfName="xxDate_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_71" ezfName="xxString_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_71" ezfName="xxNumber_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_71" ezfName="xxYearmonth_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_71" ezfName="xxYear_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_71" ezfName="xxTime_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_71" ezfName="xxTs_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_71" ezfName="xxString_71" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_71" ezfName="xxString_71" ezfCodeName="xxListCd_71" ezfDispName="xxListNm_71" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_71" ezfName="xxString_71" ezfCodeName="xxListCd_71" ezfDispName="xxListNm_71" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_71" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_71" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_71" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_71" + "';\"";
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
                                                    <ezf:inputText name="xxString_71" ezfName="xxString_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_71" ezfName="xxNumber_71" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 72 --%>
                                    <%  if (cIdx == 72) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_72" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_72" ezfName="xxDate_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_72" ezfName="xxString_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_72" ezfName="xxNumber_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_72" ezfName="xxYearmonth_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_72" ezfName="xxYear_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_72" ezfName="xxTime_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_72" ezfName="xxTs_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_72" ezfName="xxString_72" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_72" ezfName="xxString_72" ezfCodeName="xxListCd_72" ezfDispName="xxListNm_72" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_72" ezfName="xxString_72" ezfCodeName="xxListCd_72" ezfDispName="xxListNm_72" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_72" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_72" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_72" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_72" + "';\"";
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
                                                    <ezf:inputText name="xxString_72" ezfName="xxString_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_72" ezfName="xxNumber_72" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 73 --%>
                                    <%  if (cIdx == 73) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_73" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_73" ezfName="xxDate_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_73" ezfName="xxString_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_73" ezfName="xxNumber_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_73" ezfName="xxYearmonth_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_73" ezfName="xxYear_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_73" ezfName="xxTime_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_73" ezfName="xxTs_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_73" ezfName="xxString_73" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_73" ezfName="xxString_73" ezfCodeName="xxListCd_73" ezfDispName="xxListNm_73" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_73" ezfName="xxString_73" ezfCodeName="xxListCd_73" ezfDispName="xxListNm_73" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_73" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_73" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_73" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_73" + "';\"";
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
                                                    <ezf:inputText name="xxString_73" ezfName="xxString_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_73" ezfName="xxNumber_73" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 74 --%>
                                    <%  if (cIdx == 74) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_74" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_74" ezfName="xxDate_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_74" ezfName="xxString_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_74" ezfName="xxNumber_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_74" ezfName="xxYearmonth_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_74" ezfName="xxYear_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_74" ezfName="xxTime_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_74" ezfName="xxTs_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_74" ezfName="xxString_74" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_74" ezfName="xxString_74" ezfCodeName="xxListCd_74" ezfDispName="xxListNm_74" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_74" ezfName="xxString_74" ezfCodeName="xxListCd_74" ezfDispName="xxListNm_74" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_74" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_74" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_74" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_74" + "';\"";
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
                                                    <ezf:inputText name="xxString_74" ezfName="xxString_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_74" ezfName="xxNumber_74" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 75 --%>
                                    <%  if (cIdx == 75) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_75" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_75" ezfName="xxDate_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_75" ezfName="xxString_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_75" ezfName="xxNumber_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_75" ezfName="xxYearmonth_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_75" ezfName="xxYear_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_75" ezfName="xxTime_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_75" ezfName="xxTs_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_75" ezfName="xxString_75" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_75" ezfName="xxString_75" ezfCodeName="xxListCd_75" ezfDispName="xxListNm_75" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_75" ezfName="xxString_75" ezfCodeName="xxListCd_75" ezfDispName="xxListNm_75" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_75" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_75" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_75" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_75" + "';\"";
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
                                                    <ezf:inputText name="xxString_75" ezfName="xxString_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_75" ezfName="xxNumber_75" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 76 --%>
                                    <%  if (cIdx == 76) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_76" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_76" ezfName="xxDate_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_76" ezfName="xxString_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_76" ezfName="xxNumber_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_76" ezfName="xxYearmonth_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_76" ezfName="xxYear_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_76" ezfName="xxTime_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_76" ezfName="xxTs_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_76" ezfName="xxString_76" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_76" ezfName="xxString_76" ezfCodeName="xxListCd_76" ezfDispName="xxListNm_76" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_76" ezfName="xxString_76" ezfCodeName="xxListCd_76" ezfDispName="xxListNm_76" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_76" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_76" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_76" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_76" + "';\"";
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
                                                    <ezf:inputText name="xxString_76" ezfName="xxString_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_76" ezfName="xxNumber_76" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 77 --%>
                                    <%  if (cIdx == 77) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_77" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_77" ezfName="xxDate_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_77" ezfName="xxString_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_77" ezfName="xxNumber_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_77" ezfName="xxYearmonth_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_77" ezfName="xxYear_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_77" ezfName="xxTime_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_77" ezfName="xxTs_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_77" ezfName="xxString_77" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_77" ezfName="xxString_77" ezfCodeName="xxListCd_77" ezfDispName="xxListNm_77" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_77" ezfName="xxString_77" ezfCodeName="xxListCd_77" ezfDispName="xxListNm_77" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_77" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_77" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_77" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_77" + "';\"";
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
                                                    <ezf:inputText name="xxString_77" ezfName="xxString_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_77" ezfName="xxNumber_77" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 78 --%>
                                    <%  if (cIdx == 78) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_78" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_78" ezfName="xxDate_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_78" ezfName="xxString_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_78" ezfName="xxNumber_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_78" ezfName="xxYearmonth_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_78" ezfName="xxYear_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_78" ezfName="xxTime_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_78" ezfName="xxTs_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_78" ezfName="xxString_78" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_78" ezfName="xxString_78" ezfCodeName="xxListCd_78" ezfDispName="xxListNm_78" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_78" ezfName="xxString_78" ezfCodeName="xxListCd_78" ezfDispName="xxListNm_78" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_78" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_78" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_78" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_78" + "';\"";
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
                                                    <ezf:inputText name="xxString_78" ezfName="xxString_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_78" ezfName="xxNumber_78" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
                            <%-- Column Index 79 --%>
                                    <%  if (cIdx == 79) { %>
                                        <%
                                            if (dplyCtrlCd.equals("TextBox") && colTpCd.equals("Date")) {
                                                String onclick = "calendar('" + "xxDate_79" + "#" + String.valueOf(bIdx) + "', 4);"; 
                                        %>
                                                <td>
                                                    <ezf:inputText name="xxDate_79" ezfName="xxDate_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="<%= onclick %>">
                                                </td>
                                        <%
                                            } else if (dplyCtrlCd.equals("TextBox")) {
                                                if (colTpCd.equals("String")) {
                                        %>
                                                <td><ezf:inputText name="xxString_79" ezfName="xxString_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                <td><ezf:inputText name="xxNumber_79" ezfName="xxNumber_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("YearMonth")) { %>
                                                <td><ezf:inputText name="xxYearmonth_79" ezfName="xxYearmonth_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Year")) { %>
                                                <td><ezf:inputText name="xxYear_79" ezfName="xxYear_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Time")) { %>
                                                <td><ezf:inputText name="xxTime_79" ezfName="xxTime_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%      } else if (colTpCd.equals("Ts")) { %>
                                                <td><ezf:inputText name="xxTs_79" ezfName="xxTs_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                                }
                                            } else if (dplyCtrlCd.equals("CheckBox")) {
                                        %>
                                                <td><ezf:inputCheckBox name="xxString_79" ezfName="xxString_79" value="Y" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" /></td>
                                        <%
                                            } else if (dplyCtrlCd.equals("PullDown")) {
                                                if (!ZYPConstant.FLG_ON_Y.equals(bMsg.C.no(cIdx).mndFlg.getValue())) {
                                        %>
                                                <td><ezf:select name="xxString_79" ezfName="xxString_79" ezfCodeName="xxListCd_79" ezfDispName="xxListNm_79" ezfHyo="B" ezfBlank="1" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } else { %>
                                                <td><ezf:select name="xxString_79" ezfName="xxString_79" ezfCodeName="xxListCd_79" ezfDispName="xxListNm_79" ezfHyo="B" ezfBlank="0" ezfArrayNo="<%= bIdx %>" otherAttr="<%= otherAttr %>" /></td>
                                        <%      } %>
                                        <%
                                            } else if (dplyCtrlCd.equals("Popup")) {
                                                String otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_79" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxString_79" + "';\"";
                                                if (colTpCd.equals("Number")) {
                                                    otherAttrButton = "onmousedown=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_79" + "';\" onkeypress=\"document.getElementById('xxPopPrm_NM').value = '" + "xxNumber_79" + "';\"";
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
                                                    <ezf:inputText name="xxString_79" ezfName="xxString_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } else if (colTpCd.equals("Number")) { %>
                                                    <ezf:inputText name="xxNumber_79" ezfName="xxNumber_79" value="" ezfHyo="B" ezfArrayNo="<%= bIdx %>" otherAttr="<%=otherAttr %>" />
                                        <%      } %>
                                                </td>
                                        <%  } else { %>
                                                <td class="stab">'dplyCtrlCd' is not match</td>
                                        <%  } %>
                                    <%  } %>
