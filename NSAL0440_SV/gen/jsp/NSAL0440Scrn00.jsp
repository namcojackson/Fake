<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220805102216 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

            <!-- Set Page ID  -->
            <input type="hidden" name="pageID" value="NSAL0440Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Terms & Conditions">

<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- #################################################### HEADER ################################################### --%>
                <%-- ###TAB - HEAD --%>
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="Terms & Conditions" class="pTab_ON"><a href="#">Terms & Conds</a></li>
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
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <%-- ###TAB - BODY --%>
                <div class="pTab_BODY">
                    <table style="table-layout:fixed;" width="98%" align="center">
                        <col width="400">
                        <tr>
                            <td valign="top">
                                <table border="0" style="table-layout:fixed;">
                                    <col width="80">
                                    <col width="320">
                                    <tr height="21">
                                        <td class="stab">Contract#</td>
                                        <td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="NFL-1253456789" otherAttr=" size=\"50\" style=\"border:none; background-color:transparent;\""/></td>
                                    </tr>
                                    <tr height="21">
                                        <td class="stab">Customer Name</td>
                                        <td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"50\" style=\"border:none; background-color:transparent;\""/></td>
                                    </tr>
                                </table>
                            </td>
                            <td valign="top">
                                <table border="0" style="table-layout:fixed;">
                                    <col width="80">
                                    <col width="120">
                                    <tr height="21">
                                        <td class="stab">Start Date</td>
                                        <td><ezf:inputText name="contrVrsnEffFromDt" ezfName="contrVrsnEffFromDt" value="01/01/2014" otherAttr=" size=\"50\" style=\"border:none; background-color:transparent;\""/></td>
                                    </tr>
                                    <tr height="21">
                                        <td class="stab">End Date</td>
                                        <td><ezf:inputText name="contrVrsnEffThruDt" ezfName="contrVrsnEffThruDt" value="12/31/2014" otherAttr=" size=\"50\" style=\"border:none; background-color:transparent;\""/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <table border="1" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center" >
                        <tr>
                            <td class="pClothBs">Search</td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <table border="0" style="table-layout:fixed;">
                                    <col width="60">
                                    <col width="260">
                                    <col width="80">
                                    <col width="80">
                                    <col width="200">
                                    <col width="120">
                                    <col width="60">
                                    <tr height="21">
                                        <td><Div Align="left"><ezf:anchor name="serNum_AR" ezfName="serNum_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" >Serial#</ezf:anchor></Div></td>
                                        <td><ezf:inputText name="serNum" ezfName="serNum" value="MSK101010" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
                                        <td>&nbsp;</td>
                                        <td class="stab">T & C Category</td>
                                        <td rowspan="3" colspan="2">
                                            <div id="Region" style="width:298; overflow-x:hidden; overflow-y:scroll; height:98;">
                                                <table id="B" border="1" cellpadding="1" cellspacing="0" align="left">
                                                    <col width="22" align="left">
                                                    <col width="298" align="left">
                                                    <ezf:row ezfHyo="X">
                                                        <tr height="12">
                                                            <td><ezf:inputCheckBox name="xxChkBox_X" ezfName="xxChkBox_X" value="Y" ezfHyo="X" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_X#{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:inputText name="svcTermCondCatgDescTxt_X" ezfName="svcTermCondCatgDescTxt_X" value="SLA: Service Coverage" ezfHyo="X" ezfArrayNo="0" otherAttr=" size=\"32\" id=\"svcTermCondCatgDescTxt_X#{EZF_ROW_NUMBER}\""/></td>
                                                        </tr>
                                                    <ezf:skip>
                                                        <tr height="12">
                                                            <td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
                                                            <td><input type="text" class="pPro" size="32" value="SLA: Negotiated Commitments" id="svcTermCondCatgDescTxt_B#{EZF_ROW_NUMBER}" name="svcTermCondCatgDescTxt_B" ezfname="svcTermCondCatgDescTxt_B" ezfhyo="B"></td>
                                                        </tr>
                                                        <tr height="12">
                                                            <td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
                                                            <td><input type="text" class="pPro" size="32" value="Agreement" id="svcTermCondCatgDescTxt_B#{EZF_ROW_NUMBER}" name="svcTermCondCatgDescTxt_B" ezfname="svcTermCondCatgDescTxt_B" ezfhyo="B"></td>
                                                        </tr>
                                                        <tr height="12">
                                                            <td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
                                                            <td><input type="text" class="pPro" size="32" value="Toner" id="svcTermCondCatgDescTxt_B#{EZF_ROW_NUMBER}" name="svcTermCondCatgDescTxt_B" ezfname="svcTermCondCatgDescTxt_B" ezfhyo="B"></td>
                                                        </tr>
                                                        <tr height="12">
                                                            <td><input type="checkbox" class="nsal0620_sm_chk" name="xxChkBox_B" ezfname="xxChkBox_B"></td>
                                                            <td><input type="text" class="pPro" size="32" value="Generic" id="svcTermCondCatgDescTxt_B#{EZF_ROW_NUMBER}" name="svcTermCondCatgDescTxt_B" ezfname="svcTermCondCatgDescTxt_B" ezfhyo="B"></td>
                                                        </tr>
                                                    </ezf:skip>
                                                    </ezf:row>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr height="21">
                                        <td colspan="5"><ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Show Only Terms Different at Machine Level</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr height="50">
                                        <td colspan="5"><ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />Show Only Active Machines</td>
                                        <td></td>
                                        <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                                        
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <br />
<%-- #################################################### DETAIL ################################################### --%>
                    <ezf:skip>
                    <table border="0" cellpadding="1" cellspacing="0">
                        <col width="740">
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
                            <td></td>
                            <td class="stab">Showing</td>
                            <td class="pOut">1</td>
                            <td class="stab">to</td>
                            <td class="pOut">18</td>
                            <td class="stab">of</td>
                            <td class="pOut">18</td>
                            <td>&nbsp;</td>
                            <td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
                            <td></td>
                            <td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
                        </tr>
                    </table>
                    </ezf:skip>
                    <table width="100%">
                        <tr>
                            <td>
                                <table width="98%">
                                    <tr align="right">
                                        <td>
                                            <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                                <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                                <jsp:param name="TableNm"     value="A" />
                                                <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
                                                <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
                                                <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
                                            </jsp:include>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <%@ page import="business.servlet.NSAL0440.NSAL0440BMsg" %>
                    <%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP" %>
                    <% NSAL0440BMsg bMsg = (NSAL0440BMsg)databean.getEZDBMsg(); %>
                    <%String xxFilePathTxt = bMsg.xxFilePathTxt.getValue();%>
                    <table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; " width="98%" align="center" >
                        <col valign="top">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" >
                                    <tr>
                                        <td>
                                            <div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1090; float:left;">
                                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;">
                                                        <col align="center" width="145"><!-- Category -->
                                                        <col align="center" width="345"><!-- Term Detail -->
                                                        <col align="center" width="225"><!-- Contract T & Cs -->
                                                        <col align="center" width=" 30"><!-- icon -->
                                                        <col align="center" width="120"><!-- Machine -->
                                                        <col align="center" width="225"><!-- Machine T & Cs -->
                                                        <tr>
                                                            <td class="pClothBs">Category</td>
                                                            <td class="pClothBs">Term Detail</td>
                                                            <td class="pClothBs">Contract T & Cs</td>
                                                            <td class="pClothBs">
                                                            <% if (bMsg.A.getValidCount() != 0) { %>
                                                                <ezf:anchor name="xxLinkProt_M" ezfName="xxLinkProt_M" ezfEmulateBtn="1" ezfGuard="ExpandAll" >
                                                                    <img src="<%=xxFilePathTxt%>" border="0" value="Expand Machine All" width="15" height="15">
                                                                </ezf:anchor>
                                                            <% } %>
                                                            </td>
                                                            <td class="pClothBs">Machine</td>
                                                            <td class="pClothBs">Machine T & Cs</td>
                                                        </tr>
                                                </table>
                                            </div>
                                            <div id="LeftTable_A" style="overflow-y:scroll; height:310; overflow-x:hidden; width:1109; float:left;">
                                                <table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
                                                        <col align="left" width="145"><!-- Category -->
                                                        <col align="left" width="345"><!-- Term Detail -->
                                                        <col align="left" width="225"><!-- Contract T & Cs -->
                                                        <col align="center" width=" 30"><!-- icon -->
                                                        <col align="left" width="120"><!-- Machine -->
                                                        <col align="left" width="225"><!-- Machine T & Cs -->
                                                    <%@ page import="java.math.BigDecimal" %>
                                                    <% int idx=-1; %>
                                                    <ezf:row ezfHyo="A">
                                                    <% idx++; %>
                                                        <tr ezfhyo="A">
                                                        <% if (bMsg.A.no(idx).xxListNum_A.getValue().compareTo(BigDecimal.valueOf(1)) == 0) { %>
                                                            <td><ezf:inputText name="svcTermCondCatgDescTxt_A" ezfName="svcTermCondCatgDescTxt_A" value="Agreement" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"19\""/></td>
                                                        <% } else { %>
                                                            <td>&nbsp;</td>
                                                        <% } %>
                                                        <% if ("Y".equals((String)bMsg.A.no(idx).xxDplyCtrlFlg_A.getValue())) { %>
                                                            <td><ezf:inputText name="svcTermAttrbDescTxt_A" ezfName="svcTermAttrbDescTxt_A" value="CBS Standard From Version#" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"48\""/></td>
                                                        <% } else { %>
                                                            <td>&nbsp;</td>
                                                        <% } %>
                                                        <% if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td><ezf:inputText name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" value="Agreement" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/>
                                                                <ezf:inputHidden name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td><ezf:select name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" ezfBlank="1" ezfCodeName="svcTermCondDataValCd_PC" ezfDispName="svcTermCondDataDispTxt_PN" ezfCodeDispHyo="A" otherAttr=" style=\"width:216\""/>
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td><ezf:inputText name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\" style=\"text-align:right\""/></td>
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td><ezf:inputText name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxTrxDt_A', 4, '<%=idx%>');">
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td><ezf:select name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" ezfBlank="1" ezfCodeName="svcTermCondDataValCd_PC" ezfDispName="svcTermCondDataDispTxt_PN" ezfCodeDispHyo="A" otherAttr=" style=\"width:216\""/>
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td>
                                                                <table border="0" cellpadding="0" cellspacing="0">
                                                                    <col width="188">
                                                                    <col width="20">
                                                                    <col width="20">
                                                                    <tr>
                                                                        <td><ezf:inputText name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"25\""/></td>
                                                                        <td><ezf:inputButton name="Clear_ContrCondVal" value="CL" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" style=\"height:17px;width:17px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
                                                                        <td><ezf:inputButton name="OpenWin_ContrCondVal" value="..." ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" style=\"height:17px;width:17px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
                                                                            <ezf:inputHidden name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                            <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                            <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals((String)bMsg.A.no(idx).svcTermDataTpCd_A.getValue())) { %>
                                                            <td><ezf:inputText name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" value="Agreement" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/>
                                                                <ezf:inputHidden name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else { %>
                                                            <td>&nbsp;
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_A" ezfName="svcTermAttrbMapValCd_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="svcTermCondDataValCd_PS" ezfName="svcTermCondDataValCd_PS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_A" ezfName="condValNum_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_A" ezfName="xxTrxDt_A" ezfHyo="A" ezfArrayNo="${idx}" />
                                                            </td>
                                                        <% } %>
                                                        <% if ("Y".equals((String)bMsg.A.no(idx).xxDplyCtrlFlg_A.getValue())) { %>
                                                            <td>
                                                                <ezf:anchor name="xxBtnFlg_A" ezfName="xxBtnFlg_A" ezfHyo="A" ezfArrayNo="${idx}" ezfEmulateBtn="1" ezfGuard="Collapse" otherAttr=" ezfanchortext=\"\"">
                                                                <%if ("Y".equals(bMsg.A.no(idx).xxBtnFlg_A.getValue())) { %>
                                                                    <img src="./img/biz/downarrow2.png" border="0" value="Y">
                                                                <% } else { %>
                                                                    <img src="./img/biz/rightarrow2.png" border="0" value="N">
                                                                <% } %>
                                                                </ezf:anchor>
                                                            </td>
                                                        <% } else { %>
                                                            <td>&nbsp;</td>
                                                        <% } %>
                                                        <% if ("N".equals((String)bMsg.A.no(idx).xxDplyCtrlFlg_A.getValue())) { %>
                                                            <td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="CBS Standard From Version#" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"16\""/></td>
                                                        <% } else { %>
                                                            <td>&nbsp;</td>
                                                        <% } %>
                                                        <% if (SVC_TERM_ATTRB_DATA_TP.TEXTBOX.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td><ezf:inputText name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" value="Agreement" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/>
                                                                <ezf:inputHidden name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td><ezf:select name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" ezfBlank="1" ezfCodeName="svcTermCondDataValCd_MC" ezfDispName="svcTermCondDataDispTxt_MN" ezfCodeDispHyo="A" otherAttr=" style=\"width:216\""/>
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.NUMBER.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td><ezf:inputText name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\" style=\"text-align:right\""/></td>
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.DATE.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td><ezf:inputText name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxTrxDt_M', 4, '<%=idx%>');">
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td><ezf:select name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" ezfBlank="1" ezfCodeName="svcTermCondDataValCd_MC" ezfDispName="svcTermCondDataDispTxt_MN" ezfCodeDispHyo="A" otherAttr=" style=\"width:216\""/>
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td>
                                                                <table border="0" cellpadding="0" cellspacing="0">
                                                                    <col width="188">
                                                                    <col width="20">
                                                                    <col width="20">
                                                                    <tr>
                                                                        <td><ezf:inputText name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"25\""/></td>
                                                                        <td><ezf:inputButton name="Clear_MachCondVal" value="CL" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" style=\"height:17px;width:17px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
                                                                        <td><ezf:inputButton name="OpenWin_MachCondVal" value="..." ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" style=\"height:17px;width:17px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
                                                                            <ezf:inputHidden name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                            <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                            <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        <% } else if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals((String)bMsg.A.no(idx).svcTermDataTpCd_M.getValue())) { %>
                                                            <td><ezf:inputText name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" value="Agreement" ezfHyo="A" ezfArrayNo="${idx}" otherAttr=" size=\"30\""/>
                                                                <ezf:inputHidden name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" /></td>
                                                        <% } else { %>
                                                            <td>&nbsp;
                                                                <ezf:inputHidden name="svcTermAttrbMapValCd_M" ezfName="svcTermAttrbMapValCd_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="svcTermCondDataValCd_MS" ezfName="svcTermCondDataValCd_MS" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="condValNum_M" ezfName="condValNum_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                                <ezf:inputHidden name="xxTrxDt_M" ezfName="xxTrxDt_M" ezfHyo="A" ezfArrayNo="${idx}" />
                                                            </td>
                                                        <% } %>
                                                            <td class="pAuditInfo">
                                                                <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
                                                            </td>
                                                        </tr>
                                                    </ezf:row>
                                                    <ezf:skip>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="48" value="Form of Agreement" name="svcTermAttrbDescTxt_A" ezfname="svcTermAttrbDescTxt_A" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" size="30" value="Standard" name="categoryNm_A1" ezfname="categoryNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><a href="#"><img src="./img/biz/rightarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="48" value="Supplemental Terms to CBS Standard Forms" name="termNm_A1" ezfname="termNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option selected>N/A</option></select></td>
                                                            <td><a href="#"><img src="./img/biz/rightarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="text" class="pPro" size="19" value="Pricing" name="categoryNm_A1" ezfname="categoryNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" class="pPro" size="48" value="Contract Price Type" name="termNm_A1" ezfname="termNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option selected>Fixed</option></select></td>
                                                            <td><a href="#"><img src="./img/biz/rightarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="48" value="Hard Drive Cleaning Price Guarantee" name="termNm_A1" ezfname="termNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option selected>N/A</option></select></td>
                                                            <td><a href="#"><img src="./img/biz/rightarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="48" value="Hard Drive Replacement Price Guarantee" name="termNm_A1" ezfname="termNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option selected>N/A</option></select></td>
                                                            <td><a href="#"><img src="./img/biz/rightarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td><input type="text" class="pPro" size="19" value="Technician" name="categoryNm_A1" ezfname="categoryNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><input type="text" class="pPro" size="48" value="Onsite Tech Include" name="termNm_A1" ezfname="termNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option>Y</option><option selected>N</option></select></td>
                                                            <td><a href="#"><img src="./img/biz/downarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="16" value="MSK101010" name="serNum_A1" ezfname="serNum_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option>Y</option><option selected>N</option></select></td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="16" value="MNB05459" name="serNum_A1" ezfname="serNum_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option selected>Y</option><option>N</option></select></td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="16" value="TSTG38989" name="serNum_A1" ezfname="serNum_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option selected>Y</option><option>N</option></select></td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td><input type="text" class="pPro" size="48" value="Primary Tech Included" name="termNm_A1" ezfname="termNm_A1" id="termNm_A1#{EZF_ROW_NUMBER}" ezfhyo="A"></td>
                                                            <td><select name="xxTpCd_A3" ezfname="xxTpCd_A3" ezflist="xxTpCd_A1,xxTpCd_A2,99,A,blank" ezfhyo="A" ezfArrayNo="A"><option></option><option>Y</option><option selected>N</option></select></td>
                                                            <td><a href="#"><img src="./img/biz/rightarrow2.png" border="0" value="Expansion"></a></td>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                        </tr>
                                                    </ezf:skip>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</center>
<%-- ###SCRIPT --%>
<script type="text/javascript">
    function onFocusEvent(item){
        if(item.value.length === 5){
            item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
        }
    }

    function onBlurEvent(item){
        if(item.value.length === 1){
            item.value = '0' + item.value.charAt(0) + ':00';
        }else if(item.value.length === 2){
            item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
        }else if(item.value.length === 3){
            item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
        }else if(item.value.length === 4){
            item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
        }else if(item.value.length === 5){
            item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
        }
    }
</script>

<%-- **** Task specific area ends here **** --%>
