<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171205171413 --%>
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
			<input type="hidden" name="pageID" value="NMAL2610Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Territory Detail">
            <%-- include S21BusinessProcessTAB --%>
            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"></jsp:include>
            
            <ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" />
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" />
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" />
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" />
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" />
<center>
    <div class="pTab_BODY">
    <table width="100%">
        <col valign="top">
        <tr>
            <td>
                <table style="table-layout:fixed;">
                    <col valign="top" width="130">
                    <col valign="top" width="310">
                    <col valign="top" width="40">
                    <col valign="top" width="90">
                    <col valign="top" width="150">
                    <col valign="top" width="200">
                    <tr>
                        <td class="stab">Territory ID</td>
                        <td><table cellspacing="0" cellpadding="0"><col width="100"><col width="100"><tr>
                            <td class="pOut"><ezf:label name="orgCd_H1" ezfName="orgCd_H1" /></td>
                            <td><ezf:inputButton name="OpenWin_Territory" value="T" htmlClass="pBtn1" /></td>
                        </tr></table></td>
                        <td></td>
                        <td class="stab">Territory Type</td>
                        <td><ezf:select name="trtyTpCd_P1" ezfName="trtyTpCd_P1" ezfBlank="1" ezfCodeName="trtyTpCd_H1" ezfDispName="trtyTpDescTxt_H1" />
                        </td>
                        <td><ezf:inputButton name="OpenWin_Attachments" value="Attachments" htmlClass="pBtn6" /></td>
                    </tr>
                    <tr>
                        <td class="stab">Business Area</td>
                        <td><ezf:select name="bizAreaOrgCd_P1" ezfName="bizAreaOrgCd_P1" ezfCodeName="bizAreaOrgCd_H1" ezfDispName="bizAreaOrgNm_H1" otherEvent1=" onchange=\"sendServer('OnChange_BusinessArea')\"" />
                        </td>
                        <td>&nbsp</td>
                        <td class="stab">Territory Group</td>
                        <td><ezf:select name="trtyGrpTpCd_P1" ezfName="trtyGrpTpCd_P1" ezfBlank="1" ezfCodeName="trtyGrpTpCd_H1" ezfDispName="trtyGrpTpDescTxt_H1" otherEvent1=" onchange=\"sendServer('OnChange_TerritoryGroup')\"" />
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Territory Name</td>
                        <td><ezf:inputText name="orgNm_H1" ezfName="orgNm_H1" otherAttr=" ezftoupper=\"\" size=\"40\" maxlength=\"50\""/></td>
                        <td></td>
                        <td class="stab">Tier (LEVEL)</td>
                        <td>
                            <ezf:select name="orgTierCd_P1" ezfName="orgTierCd_P1" ezfBlank="1" ezfCodeName="orgTierCd_H1" ezfDispName="orgTierNm_H1" otherEvent1=" onchange=\"sendServer('OnChange_Tier')\"" />
                        </td>
                    </tr>
                    <tr>
                        <td class="stab">Territory Description</td>
                        <td>
                            <ezf:inputText name="orgDescTxt_H1" ezfName="orgDescTxt_H1" otherAttr=" size=\"40\" maxlength=\"50\""/>
                        </td>
                        <td>&nbsp</td>
                        <td class="stab">Tier Description</td>
                        <td class="pOut"><ezf:label name="struDfnDescTxt_H1" ezfName="struDfnDescTxt_H1" /></td>
                    </tr>
                    <tr>
                        <td class="stab">Start Date</td>
                        <td>
                            <ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                            <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
                        </td>
                        <td>&nbsp</td>
                        <td class="stab">End Date</td>
                        <td>
                            <ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="02/02/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
                            <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
                        </td>
                    </tr>
                    <tr>
                        <td class="stab"></td>
                        <td>
                        </td>
                        <td>&nbsp</td>
                        <td class="stab" colspan="2">
                        </td>
                        <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
                    </tr>
                </table>
                <%-- ##### BODY(TAB) ##### --%>
                <div class="pTab_HEAD">
                    <ul>
                        <li id="Relation" title="Relation" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_01" ezfEmulateBtn="1" ezfGuard="TAB_Relation" >Territory</ezf:anchor></li>
                        <li id="Rules" title="Rules" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Rules" >Territory Rules</ezf:anchor></li>
                        <li id="Resource" title="Resource" class="pTab_OFF"><ezf:anchor name="" ezfName="xxTabProt_03" ezfEmulateBtn="1" ezfGuard="TAB_Resource" >Resrc Asign</ezf:anchor></li>
                    </ul>
                </div>
                <%-- ##### TAB(Main) ##### --%>
                <c:choose>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='Relation'}">
                        <script type="text/javascript">
                            document.getElementById( "Relation" ).className="pTab_ON";
                            document.getElementById( "Rules" ).className="pTab_OFF";
                            document.getElementById( "Resource" ).className="pTab_OFF";
                        </script>
                        <div class="pTab_BODY_In">
                            <table style="table-layout:fixed;">
                                <col valign="top">
                                <tr>
                                    <td>
                                        <%-- ##### Upper Table ##### --%>
                                        <table style="table-layout:fixed;">
                                            <col width="500" valign="middle">
                                            <col width="150" valign="middle">
                                            <col width="160" valign="middle">
                                            <tr>
                                                <td class="stab">
                                                    <label><ezf:inputCheckBox name="xxChkBox_F1" ezfName="xxChkBox_F1" value="Y" onClick="sendServer('OnChange_TrtyActvFlg')" />Show All Assignments</label>
                                                </td>
                                                <td class="stab">
                                                </td>
                                                <td>
                                                    <ezf:inputButton name="InsertRow_Territory" value="Add" htmlClass="pBtn5" />
                                                    <ezf:inputButton name="DeleteRow_Territory" value="Delete" htmlClass="pBtn5" />
                                                </td>
                                            </tr>
                                        </table>
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <col align="left" valign="top">
                                            <tr>
                                                <td>
                                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                        <col align="center" width="30">
                                                        <col align="center" width="100">
                                                        <col align="center" width="450">
                                                        <col align="center" width="100">
                                                        <col align="center" width="100">
                                                        <tr height="18">
                                                            <td class="pClothBs">&nbsp</td>
                                                            <td class="pClothBs">Territory ID</td>
                                                            <td class="pClothBs">Parent Territory Name</td>
                                                            <td class="pClothBs">Start Date</td>
                                                            <td class="pClothBs">End Date</td>
                                                        </tr>
                                                    </table>
                                                    <div style="overflow-x:hidden; width:798; height:135px; overflow-y:scroll;">
                                                        <table id="A" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                            <col width="30" align="center">
                                                            <col width="100">
                                                            <col width="450">
                                                            <col width="100">
                                                            <col width="100">
                                                            <ezf:row ezfHyo="A">
                                                            <tr>
                                                                <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
                                                                <td><ezf:anchor name="orgCd_L1" ezfName="orgCd_L1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenMultiModeScreen_Parent" otherAttr=" ezfanchortext=\"\""><ezf:label name="orgCd_A1" ezfName="orgCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                                <td>
                                                                    <ezf:inputText name="orgNm_A1" ezfName="orgNm_A1" value="PROVIDENCE MGR1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"57\" maxlength=\"50\" ezftoupper=\"\""/>
                                                                    <ezf:inputButton name="OpenWin_Territory_Detail" value="T" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_Territory_Detail#{EZF_ROW_NUMBER}\""/>
                                                                </td>
                                                                <td><ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td><ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="02/26/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td class="pAuditInfo">
                                                                    <ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                </td>
                                                            </tr>
                                                            </ezf:row>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <table style="table-layout:fixed;">
                                <col valign="top">
                                <tr>
                                    <td>
                                        <%-- ##### Upper Table ##### --%>
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <col align="left" valign="top">
                                            <tr>
                                                <td>
                                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                        <col align="center" width="30">
                                                        <col align="center" width="100">
                                                        <col align="center" width="450">
                                                        <col align="center" width="100">
                                                        <col align="center" width="100">
                                                        <tr height="18">
                                                            <td class="pClothBs">&nbsp</td>
                                                            <td class="pClothBs">Territory ID</td>
                                                            <td class="pClothBs">Child Territory Name</td>
                                                            <td class="pClothBs">Start Date</td>
                                                            <td class="pClothBs">End Date</td>
                                                        </tr>
                                                    </table>
                                                    <div style="overflow-x:hidden; width:798; height:135px; overflow-y:scroll;">
                                                        <table id="B" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                            <col width="30" align="center">
                                                            <col width="100">
                                                            <col width="450">
                                                            <col width="100">
                                                            <col width="100">
                                                            <ezf:row ezfHyo="B">
                                                            <tr>
                                                                <td>&nbsp</td>
                                                                <td><ezf:anchor name="orgCd_L2" ezfName="orgCd_L2" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenMultiModeScreen_Child" otherAttr=" ezfanchortext=\"\""><ezf:label name="orgCd_B1" ezfName="orgCd_B1" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></td>
                                                                <td class="pOut"><ezf:label name="orgNm_B1" ezfName="orgNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
                                                                <td><ezf:inputText name="effFromDt_B1" ezfName="effFromDt_B1" value="02/26/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td><ezf:inputText name="effThruDt_B1" ezfName="effThruDt_B1" value="02/26/2015" ezfHyo="B" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td class="pAuditInfo">
                                                                    <ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
                                                                </td>
                                                            </tr>
                                                            </ezf:row>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when>

                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='Rules'}">
                        <script type="text/javascript">
                            document.getElementById( "Relation" ).className="pTab_OFF";
                            document.getElementById( "Rules" ).className="pTab_ON";
                            document.getElementById( "Resource" ).className="pTab_OFF";
                        </script>
                        </script>
                        <div class="pTab_BODY_In">
                            <table style="table-layout:fixed;">
                                <col valign="top">
                                <tr>
                                    <td>
                                        <%-- ##### Upper Table ##### --%>
                                        <table style="table-layout:fixed;">
                                            <col width="800" valign="middle">
                                            <col width="230" valign="middle">
                                            <tr>
                                                <td class="stab">
                                                    <label><ezf:inputCheckBox name="xxChkBox_F2" ezfName="xxChkBox_F2" value="Y" onClick="sendServer('OnChange_RuleActvFlg')" />Show All Rules</label>
                                                    <span style="width:301px"></span>
                                                    <span style="width:30px">Filter</span>
                                                    <ezf:inputText name="trtyRuleFromValTxt_F1" ezfName="trtyRuleFromValTxt_F1" otherAttr=" size=\"13\" maxlength=\"50\""/>
                                                    <ezf:inputText name="trtyRuleThruValTxt_F1" ezfName="trtyRuleThruValTxt_F1" otherAttr=" size=\"13\" maxlength=\"50\""/>
                                                    <ezf:inputButton name="Filter" value="Apply" htmlClass="pBtn4" />
                                                    <ezf:inputButton name="ClearFilter" value="Clear" htmlClass="pBtn4" />
                                                </td>
                                                <td>
                                                    <ezf:inputButton name="InsertRow_Rules" value="Add" htmlClass="pBtn5" />
                                                    <ezf:inputButton name="DeleteRow_Rules" value="Delete" htmlClass="pBtn5" />
                                                    <ezf:inputButton name="Link_CopyTerritory" value="Copy Territory" htmlClass="pBtn8" />
                                                </td>
                                            </tr>
                                        </table>
                                        <table cellpadding="0" cellspacing="0">
                                            <col width="130">
                                            <col width="900">
                                            <tr>
                                                <td></td>
                                                <td align="right">
                                                    <table cellpadding="0" cellspacing="0">
                                                        <tr>
                                                            <td>
                                                                <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                                                    <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                                                    <jsp:param name="TableNm"     value="C" />
                                                                    <jsp:param name="ShowingFrom" value="xxPageShowFromNum_C1" />
                                                                    <jsp:param name="ShowingTo"   value="xxPageShowToNum_C1" />
                                                                    <jsp:param name="ShowingOf"   value="xxPageShowOfNum_C1" />
                                                                    <jsp:param name="ShowingCurrent" value="xxPageShowCurNum_C1" />
                                                                    <jsp:param name="ShowingTotal"   value="xxPageShowTotNum_C1" />
                                                                    <jsp:param name="ViewMode"       value="FULL" />
                                                                </jsp:include>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <col align="left" valign="top">
                                            <tr>
                                                <td>
                                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                        <col align="center" width="30">
                                                        <col align="center" width="200">
                                                        <col align="center" width="180">
                                                        <col align="center" width="100">
                                                        <col align="center" width="40">
                                                        <col align="center" width="100">
                                                        <col align="center" width="40">
                                                        <col align="center" width="180">
                                                        <col align="center" width="100">
                                                        <col align="center" width="100">
                                                        <tr height="18">
                                                            <td class="pClothBs">&nbsp</td>
                                                            <td class="pClothBs">Rule Type</td>
                                                            <td class="pClothBs">Operator</td>
                                                            <td class="pClothBs" colspan="2">Value From</td>
                                                            <td class="pClothBs" colspan="2">Value To</td>
                                                            <td class="pClothBs">Logic</td>
                                                            <td class="pClothBs">Start Date</td>
                                                            <td class="pClothBs">End Date</td>
                                                        </tr>
                                                    </table>
                                                    <div style="overflow-x:hidden; width:1090; height:310px; overflow-y:scroll;">
                                                        <table id="C" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                            <col width="30" align="center">
                                                            <col width="200">
                                                            <col width="180">
                                                            <col width="100">
                                                            <col width="40" >
                                                            <col width="100">
                                                            <col width="40" >
                                                            <col width="180">
                                                            <col width="100">
                                                            <col width="100">
                                                            <ezf:row ezfHyo="C">
                                                            <tr>
                                                                <td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C1#{EZF_ROW_NUMBER}\""/></td>
                                                                <td>
                                                                    <ezf:select name="trtyRuleTpCd_P1" ezfName="trtyRuleTpCd_P1" ezfHyo="C" ezfArrayNo="0" ezfCodeName="trtyRuleTpCd_C1" ezfDispName="trtyRuleTpDescTxt_C1" otherEvent1=" onchange=\"sendServer('OnChange_TerritoryRuleType', {EZF_ROW_NUMBER})\"" />
                                                                </td>
                                                                <td>
                                                                    <ezf:select name="trtyRuleOprdTpCd_P1" ezfName="trtyRuleOprdTpCd_P1" ezfHyo="C" ezfArrayNo="0" ezfCodeName="trtyRuleOprdTpCd_C1" ezfDispName="trtyRuleOprdTpDescTxt_C1" ezfCodeDispHyo="C" />
                                                                </td>
                                                                <td>
                                                                    <ezf:inputText name="trtyRuleFromValTxt_C1" ezfName="trtyRuleFromValTxt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/>
                                                                </td>
                                                                <td>
                                                                	<ezf:inputButton name="OpenWin_TargetFrom" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TargetFrom{EZF_ROW_NUMBER}\""/>
                                                                </td>
                                                                <td>
                                                                    <ezf:inputText name="trtyRuleThruValTxt_C1" ezfName="trtyRuleThruValTxt_C1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\""/>
                                                                </td>
                                                                <td>
                                                                	<ezf:inputButton name="OpenWin_TargetTo" value="..." ezfHyo="C" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_TargetTo{EZF_ROW_NUMBER}\""/>
                                                                </td>
                                                                <td>
                                                                    <ezf:select name="trtyRuleLogicTpCd_P1" ezfName="trtyRuleLogicTpCd_P1" ezfHyo="C" ezfArrayNo="0" ezfCodeName="trtyRuleLogicTpCd_C1" ezfDispName="trtyRuleLogicTpDescTxt_C1" />
                                                                </td>
                                                                <td><ezf:inputText name="effFromDt_C1" ezfName="effFromDt_C1" value="02/26/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_C1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td><ezf:inputText name="effThruDt_C1" ezfName="effThruDt_C1" value="02/26/2015" ezfHyo="C" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_C1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td class="pAuditInfo">
                                                                    <ezf:inputHidden name="xxRecHistCratTs_C1" ezfName="xxRecHistCratTs_C1" ezfHyo="C" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistCratByNm_C1" ezfName="xxRecHistCratByNm_C1" ezfHyo="C" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdTs_C1" ezfName="xxRecHistUpdTs_C1" ezfHyo="C" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdByNm_C1" ezfName="xxRecHistUpdByNm_C1" ezfHyo="C" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistTblNm_C1" ezfName="xxRecHistTblNm_C1" ezfHyo="C" ezfArrayNo="0" />
                                                                </td>
                                                            </tr>
                                                            </ezf:row>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                    <c:when test="${pageScope._ezddatabean.xxDplyTab=='Resource'}">
                        <script type="text/javascript">
                            document.getElementById( "Relation" ).className="pTab_OFF";
                            document.getElementById( "Rules" ).className="pTab_OFF";
                            document.getElementById( "Resource" ).className="pTab_ON";
                        </script>
                        </script>
                        <div class="pTab_BODY_In">
                            <table style="table-layout:fixed;">
                                <col valign="top">
                                <tr>
                                    <td>
                                        <%-- ##### Upper Table ##### --%>
                                        <table style="table-layout:fixed;">
                                            <col width="730" valign="middle">
                                            <col width="160" valign="middle">
                                            <tr>
                                                <td class="stab">
                                                    <label><ezf:inputCheckBox name="xxChkBox_F3" ezfName="xxChkBox_F3" value="Y" onClick="sendServer('OnChange_ResrcActvFlg')" />Show All Assignments</label>
                                                </td>
                                                <td>
                                                    <ezf:inputButton name="InsertRow_Resource" value="Add" htmlClass="pBtn5" />
                                                    <ezf:inputButton name="DeleteRow_Resource" value="Delete" htmlClass="pBtn5" />
                                                </td>
                                            </tr>
                                        </table>
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <col align="left" valign="top">
                                            <tr>
                                                <td>
                                                    <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                        <col align="center" width="30">
                                                        <col align="center" width="300">
                                                        <col align="center" width="150">
                                                        <col align="center" width="180">
                                                        <col align="center" width="100">
                                                        <col align="center" width="100">
                                                        <tr height="18">
                                                            <td class="pClothBs">&nbsp</td>
                                                            <td class="pClothBs">Resource Name</td>
                                                            <td class="pClothBs">Employee ID</td>
                                                            <td class="pClothBs">Resource Role in Territory</td>
                                                            <td class="pClothBs">Start Date</td>
                                                            <td class="pClothBs">End Date</td>
                                                        </tr>
                                                    </table>
                                                    <div style="overflow-x:hidden; width:880; height:330px; overflow-y:scroll;">
                                                        <table id="D" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
                                                            <col width="30" align="center">
                                                            <col width="300">
                                                            <col width="150">
                                                            <col width="180">
                                                            <col width="100">
                                                            <col width="100">
                                                            <ezf:row ezfHyo="D">
                                                            <tr>
                                                                <td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D1#{EZF_ROW_NUMBER}\""/></td>
                                                                <td><ezf:label name="xxPsnNm_D1" ezfName="xxPsnNm_D1" ezfHyo="D" ezfArrayNo="0" /></td>
                                                                <td>
                                                                    <table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
                                                                        <col width="110">
                                                                        <col width="40">
                                                                        <tr>
                                                                            <td><ezf:label name="psnCd_D1" ezfName="psnCd_D1" ezfHyo="D" ezfArrayNo="0" /></td>
                                                                            <td><ezf:inputButton name="OpenWin_ResourceLookup" value="Rsc" ezfHyo="D" ezfArrayNo="0" htmlClass="pBtn1" otherAttr=" id=\"OpenWin_ResourceLookup#{EZF_ROW_NUMBER}\""/></td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                                <td>
                                                                    <ezf:select name="acctTeamRoleTpCd_P1" ezfName="acctTeamRoleTpCd_P1" ezfHyo="D" ezfArrayNo="0" ezfBlank="1" ezfCodeName="acctTeamRoleTpCd_D1" ezfDispName="acctTeamRoleTpDescTxt_D1" />
                                                                </td>
                                                                <td><ezf:inputText name="effFromDt_D1" ezfName="effFromDt_D1" value="02/26/2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_D1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td><ezf:inputText name="effThruDt_D1" ezfName="effThruDt_D1" value="02/26/2015" ezfHyo="D" ezfArrayNo="0" otherAttr=" ezftoupper=\"\" size=\"10\" maxlength=\"10\""/><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_D1', 4, '{EZF_ROW_NUMBER}');" ></td>
                                                                <td class="pAuditInfo">
                                                                    <ezf:inputHidden name="xxRecHistCratTs_D1" ezfName="xxRecHistCratTs_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistCratByNm_D1" ezfName="xxRecHistCratByNm_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdTs_D1" ezfName="xxRecHistUpdTs_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistUpdByNm_D1" ezfName="xxRecHistUpdByNm_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                    <ezf:inputHidden name="xxRecHistTblNm_D1" ezfName="xxRecHistTblNm_D1" ezfHyo="D" ezfArrayNo="0" />
                                                                </td>
                                                            </tr>
                                                            </ezf:row>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </table>
    </div>
</center>

<%-- **** Task specific area ends here **** --%>
