<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161206104945 --%>
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
            <input type="hidden" name="pageID" value="NSBL0300Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Skill Level Maintenance">

<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
                                        <li title="Resource Skill Maintenance" class="pTab_ON"><a href="#">Rsrc Skl Mnt</a></li>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </ul>
                </div>
                </ezf:skip>

                <div class="pTab_BODY">

<!-- ######################################## BODY(TAB) ################################## -->
                <div class="pTab_HEAD">
                    <ul>
                        <li id="Resrc_Skill" title="Resource Skill" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_ResrcSkillMaint" otherAttr=" tabindex=\"-1\"">Resource Skill</ezf:anchor></li>
                        <li id="Skills" title="Skills" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_SkillMaint" otherAttr=" tabindex=\"-1\"">Skills</ezf:anchor></li>
                        <li id="Skill_Levels" title="Skill Levels" class="pTab_ON"><a href="#" tabindex="-1">Skill Levels</a></li>
                        <li id="Access_Permits" title="Access Permits" class="pTab_OFF"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="MoveWin_AccessPermits" otherAttr=" tabindex=\"-1\"">Access Permits</ezf:anchor></li>
                    </ul>
                </div>

<!-- ######################################## TAB(Main) ################################## -->
                <div class="pTab_BODY_In">
                    <table width="99%">
                        <tr>
                            <td align="left">
                                <table border="0" cellpadding="1" cellspacing="0">
                                    <col width="40">
                                    <col width="150">
                                    <col width="16">
                                    <col width="80">
                                    <col width="16">
                                    <col width="40">
                                    <col width="16">
                                    <col width="40">
                                    <col width="10">
                                    <col width="10">
                                    <col width="16">
                                    <col width="40">
                                    <col width="10">
                                    <tr>
                                        <td></td>
                                        <td class="stab">Rating Scale Type</td>
                                        <td></td>
                                        <td>
                                            <ezf:select name="svcSkillLvlGrpCd_H3" ezfName="svcSkillLvlGrpCd_H3" ezfBlank="1" ezfCodeName="svcSkillLvlGrpCd_H1" ezfDispName="svcSkillLvlGrpNm_H2" otherAttr=" style=\"width: 300px\""/>
                                        </td>
                                        <td></td>
                                        <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td class="stab">Rating Scale Type Description</td>
                                        <td></td>
                                        <td><ezf:inputText name="svcSkillLvlGrpDescTxt" ezfName="svcSkillLvlGrpDescTxt" value="Field Service Standard Levels" otherAttr=" size=\"42\""/></td>
                                        <td></td>
                                        <td class="stab">Effective Date</td>
                                        <td></td>
                                        <td><ezf:inputText name="effFromDt" ezfName="effFromDt" value="02/02/2015" otherAttr=" size=\"12\" maxlength=\"10\" tabindex=\"1\""/></td>
                                        <td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" tabindex="1"></td>
                                        <td></td>
                                        <td>-</td>
                                        <td><ezf:inputText name="effThruDt" ezfName="effThruDt" value="02/02/2015" otherAttr=" size=\"12\" maxlength=\"10\" tabindex=\"1\""/></td>
                                        <td><img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" tabindex="1"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <hr />
<!-- ######################################## DETAIL ######################################## -->
                    <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0" align="center" width="712px">
                        <!-- =============== TABLE HEADER =============== -->
                        <tr>
                            <td align="left" valign="top">
                                <table border="1" cellpadding="1" cellspacing="0" width="905px" style="table-layout: fixed;">
                                    <col width="25px"  align="center">
                                    <col width="50px"  align="center">
                                    <col width="220px" align="center">
                                    <col width="370px"  align="center">
                                    <col width="120px"  align="center">
                                    <col width="120px"  align="center">
                                    <tr height="25px">
                                        <td class="pClothBs">&nbsp;</td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillLvlSortNum_A' )">Order<img id="sortIMG.svcSkillLvlSortNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillLvlNm_A' )">Skill Level Name<img id="sortIMG.svcSkillLvlNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcSkillLvlDescTxt_A' )">Skill Level Description<img id="sortIMG.svcSkillLvlDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">Effective Date From<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">Effective Date Thru<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    </tr>
                                </table>
                                <div style="overflow-y:scroll; height:405px; width:922px;">
                                <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
                                        <col width="25px"  align="center">
                                        <col width="50px"  align="center">
                                        <col width="220px"  align="center">
                                        <col width="370px"  align="center">
                                        <col width="120px" align="center">
                                        <col width="120px"  align="center">
                                        <ezf:row ezfHyo="A">
                                            <tr height="25px">
                                                <td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                <td><ezf:inputText name="svcSkillLvlSortNum_A" ezfName="svcSkillLvlSortNum_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"text-align:right\" size=\"3\" maxlength=\"3\""/></td>
                                                <td><ezf:inputText name="svcSkillLvlNm_A" ezfName="svcSkillLvlNm_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
                                                <td><ezf:inputText name="svcSkillLvlDescTxt_A" ezfName="svcSkillLvlDescTxt_A" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\""/></td>                                            
                                                <td>
                                                    <ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/>
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, '{EZF_ROW_NUMBER}');">
                                                </td>                                            
                                                <td>
                                                    <ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="99/99/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/>
                                                    <img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, '{EZF_ROW_NUMBER}');">
                                                </td>
                                                <td class="pAuditInfo">
                                                    <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistCratByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdTs_A\""/>
                                                    <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistUpdByNm_A\""/>
                                                    <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"0\" id=\"xxRecHistTblNm_A\""/>
                                                </td>
                                            </tr>
                                        </ezf:row>
                                        <ezf:skip>
                                        </ezf:skip>
                                </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <table border="0">
                        <tr height="2">
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align:bottom;">
                                <table border="0" cellpadding="1" cellspacing="0" width="100%">
                                    <col width="100">
                                    <col width="40">
                                    <col width="5">
                                    <col width="40">
                                    <tr>
                                        <td></td>
                                        <td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
                                        <td></td>
                                        <td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn7" /></td>
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

<style TYPE="text/css">
<!--
    tr.checkLineBGcolor{background-color:yellow;}
    a img{border-style:none;}
-->
</style>

<script type="text/javascript">

    function clickImg(prntObj, idx) {
        var eventNm = prntObj.all(0).value;
        sendServer(eventNm, idx);
    }

    function changeBGColor(chkObj, idx) {
        var bgColorCls  = "";
        var origBgColor = document.getElementById("xxTblItemTxt_A#" + idx).value;
        
        if (chkObj.checked) {
            bgColorCls = "checkLineBGcolor";
            if (bgColorCls != origBgColor) {
                document.getElementById("xxTblItemTxt_A#" + idx).value =
                    document.getElementById("A_RightTr#"  + idx).className;
            }
        } else {
            bgColorCls = origBgColor;
        }
        document.getElementById("A_RightTr#" + idx).className = bgColorCls;
    }
</script>


<%-- **** Task specific area ends here **** --%>
