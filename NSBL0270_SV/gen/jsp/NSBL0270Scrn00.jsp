<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170412114924 --%>
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
            <input type="hidden" name="pageID" value="NSBL0270Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Service Pricing Shift Maintenance">

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
                                        <li title="Service Pricing Shift Maintenance" class="pTab_ON"><a href="#">Svc Prc Sft</a></li>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </ul>
                </div>
                </ezf:skip>

                <div class="pTab_BODY">

<!-- ######################################## HEADER ######################################## -->
                <table width="99%">
                    <tr>
                        <td align="left">
                            <table border="0" cellpadding="1" cellspacing="0">
                                <col width="80">
                                <col width="100">
                                <col width="16">
                                <col width="400">
                                <col width="16">
                                <col width="40">
                                <tr>
                                    <td></td>
                                   <td class="stab"><ezf:anchor name="xxLinkAncr" ezfName="xxLinkAncr" ezfEmulateBtn="1" ezfGuard="TemplateDownload" >Template Download</ezf:anchor></td>
                                    <td></td>
                                    <td><ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"80\" maxlength=\"9999\""/></td>
                                    <td></td>
                                    <td><ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

<!-- ######################################## DETAIL ######################################## -->
                <table style="MARGIN-LEFT: 10px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                    <!-- =============== TABLE HEADER =============== -->
                    <tr>
                        <td align="left" valign="top">
                            <!-- ******************************* -->
                            <!-- *** Left Table Area(Header) *** -->
                            <!-- ******************************* -->
                            <div id="topLeftTBL_A" style="overflow-y:none; overflow-x:none; height:; width:;" onScroll="synchroScrollTop(this.id, new Array('RightTBL_A'));">
                                <table border="1" cellpadding="1" cellspacing="0" width="430" style="table-layout: fixed;">
                                    <col width="25"  align="center">
                                    <col width="40"  align="center">
                                    <col width="45" align="center">
                                    <col width="35"  align="center">
                                    <col width="70"  align="center">
                                    <col width="215"  align="center">
                                    <tr height="50">
                                        <td class="pClothBs">&nbsp;</td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcPrcShiftNum_A' )">#<img id="sortIMG.svcPrcShiftNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_A1' )">Active<img id="sortIMG.xxChkBox_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxChkBox_A2' )">AHS<img id="sortIMG.xxChkBox_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcLineBizCd_A' )">Line of<br>Business<img id="sortIMG.svcLineBizCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                        <td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcPrcShiftDescTxt_A' )">Description<img id="sortIMG.svcPrcShiftDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                    </tr>
                                </table>
                            </div>
                            <!-- ******************************* -->
                            <!-- *** Left Table Area(Detail) *** -->
                            <!-- ******************************* -->
                            <div id="LeftTBL_A" style="overflow-y:hidden; height:440; overflow-x:hidden; width:"
                                onScroll="synchroScrollTop( this.id, new Array( 'RightTBL_A' ) );">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="430" id="A_Left">
                                    <col width="25"  align="center">
                                    <col width="40"  align="center">
                                    <col width="45"  align="center">
                                    <col width="35"  align="center">
                                    <col width="70" align="center">
                                    <col width="215"  align="center">
                                    <ezf:row ezfHyo="A">
                                        <tr height="25" id="A_leftTBLRow#{EZF_ROW_NUMBER}">
                                            <td><ezf:inputCheckBox name="xxChkBox_AD" ezfName="xxChkBox_AD" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_AD#{EZF_ROW_NUMBER}\""/></td>
                                            <td><ezf:label name="svcPrcShiftNum_A" ezfName="svcPrcShiftNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td><ezf:inputCheckBox name="xxChkBox_A2" ezfName="xxChkBox_A2" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                            <td>
                                                <ezf:select name="svcLineBizCd_A" ezfName="svcLineBizCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="lineBizTpCd_H1" ezfDispName="lineBizTpDescTxt_H2" otherAttr=" style=\"width: 55px\""/>
                                            </td>
                                            <td><ezf:inputText name="svcPrcShiftDescTxt_A" ezfName="svcPrcShiftDescTxt_A" value="After Hours Mon - Fri Shift1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"28\" maxlength=\"50\" id=\"svcPrcShiftDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
                                        </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                        <tr height="25">
                                            <td><input type="checkbox" value="Y" name="xxChkBox_AD" ezfname="xxChkBox_AD" ezfHyo="A" id="xxChkBox_AD#{EZF_ROW_NUMBER}"></td>
                                            <td><label ezfout name="svcPrcShiftNum_A" ezfname="svcPrcShiftNum_A" ezfhyo="A">1002</label></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfHyo="A"></td>
                                            <td>
                                                <select style="width: 55px" name="svcLineBizCd_A" ezfname="svcLineBizCd_A" ezflist="lineBizTpCd_H1,lineBizTpDescTxt,H2, ,blank" ezfHyo="A">
                                                    <option value=""></option>
                                                    <option value="Y">ESS</option>
                                                    <option value="N">PPS</option>
                                                </select>
                                            </td>
                                            <td><input type="text" size="28" maxlength="50" value="After Hours Mon - Fri Shift2" name="svcPrcShiftDescTxt_A" ezfname="svcPrcShiftDescTxt_A" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                        <tr height="25">
                                            <td><input type="checkbox" value="Y" name="xxChkBox_AD" ezfname="xxChkBox_AD" ezfHyo="A" id="xxChkBox_AD#{EZF_ROW_NUMBER}"></td>
                                            <td><label ezfout name="svcPrcShiftNum_A" ezfname="svcPrcShiftNum_A" ezfhyo="A">1003</label></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfHyo="A"></td>
                                            <td>
                                                <select style="width: 55px" name="svcLineBizCd_A" ezfname="svcLineBizCd_A" ezflist="lineBizTpCd_H1,lineBizTpDescTxt,H2, ,blank" ezfHyo="A">
                                                    <option value=""></option>
                                                    <option value="Y">ESS</option>
                                                    <option value="N">PPS</option>
                                                </select>
                                            </td>
                                            <td><input type="text" size="28" maxlength="50" value="After Hours Mon - Fri Shift3" name="svcPrcShiftDescTxt_A" ezfname="svcPrcShiftDescTxt_A" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                        <tr height="25">
                                            <td><input type="checkbox" value="Y" name="xxChkBox_AD" ezfname="xxChkBox_AD" ezfHyo="A" id="xxChkBox_AD#{EZF_ROW_NUMBER}"></td>
                                            <td><label ezfout name="svcPrcShiftNum_A" ezfname="svcPrcShiftNum_A" ezfhyo="A">1004</label></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfHyo="A"></td>
                                            <td>
                                                <select style="width: 55px" name="svcLineBizCd_A" ezfname="svcLineBizCd_A" ezflist="lineBizTpCd_H1,lineBizTpDescTxt,H2, ,blank" ezfHyo="A">
                                                    <option value=""></option>
                                                    <option value="Y">ESS</option>
                                                    <option value="N">PPS</option>
                                                </select>
                                            </td>
                                            <td><input type="text" size="28" maxlength="50" value="After Hours Saturday Shift1" name="svcPrcShiftDescTxt_A" ezfname="svcPrcShiftDescTxt_A" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                        <tr height="25">
                                            <td><input type="checkbox" value="Y" name="xxChkBox_AD" ezfname="xxChkBox_AD" ezfHyo="A" id="xxChkBox_AD#{EZF_ROW_NUMBER}"></td>
                                            <td><label ezfout name="svcPrcShiftNum_A" ezfname="svcPrcShiftNum_A" ezfhyo="A">1005</label></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A1" ezfname="xxChkBox_A1" ezfHyo="A"></td>
                                            <td><input type="checkbox" value="Y" name="xxChkBox_A2" ezfname="xxChkBox_A2" ezfHyo="A"></td>
                                            <td>
                                                <select style="width: 55px" name="svcLineBizCd_A" ezfname="svcLineBizCd_A" ezflist="lineBizTpCd_H1,lineBizTpDescTxt,H2, ,blank" ezfHyo="A">
                                                    <option value=""></option>
                                                    <option value="Y">ESS</option>
                                                    <option value="N">PPS</option>
                                                </select>
                                            </td>
                                            <td><input type="text" size="28" maxlength="50" value="After Hours Saturday Shift2" name="svcPrcShiftDescTxt_A" ezfname="svcPrcShiftDescTxt_A" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                    </ezf:skip>
                                </table>
                            </div>
                        </td>
                        <td valign="top">
                            <!-- ******************************** -->
                            <!-- *** Right Table Area(Header) *** -->
                            <!-- ******************************** -->
                            <div id="topRightTBL_A" style="overflow-y:hidden; height:; overflow-x:hidden; width:665"
                                onScroll="synchroScrollLeft( this.id, new Array( 'RightTBL_A' ) );">
                                <table border="1" cellpadding="1" cellspacing="0" width="1120">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <tr height="25">
                                        <td class="pClothBs" colspan="2">Monday</td>
                                        <td class="pClothBs" colspan="2">Tuesday</td>
                                        <td class="pClothBs" colspan="2">Wednesday</td>
                                        <td class="pClothBs" colspan="2">Thursday</td>
                                        <td class="pClothBs" colspan="2">Friday</td>
                                        <td class="pClothBs" colspan="2">Saturday</td>
                                        <td class="pClothBs" colspan="2">Sunday</td>
                                        <td class="pClothBs" colspan="2">Holiday</td>
                                    </tr>
                                    <tr height="25">
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                        <td class="pClothBs">Start</td>
                                        <td class="pClothBs">End</td>
                                    </tr>
                                </table>
                            </div>
                            <!-- ******************************** -->
                            <!-- *** Right Table Area(Detail) *** -->
                            <!-- ******************************** -->
                            <div id="RightTBL_A" style="overflow-y:scroll; height:457; overflow-x:scroll; width:682" 
                                onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL_A' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL_A' ) );">
                                <table border="1" cellpadding="1" cellspacing="0" width="1120" id="A_Right">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <col width="70" align="center">
                                    <ezf:row ezfHyo="A">
                                        <tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A1" ezfName="xxStartDplyTmTxt_A1" value="08:30:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\" id=\"xxStartDplyTmTxt_A1#{EZF_ROW_NUMBER}\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A1" ezfName="xxEndDplyTmTxt_A1" value="17:00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A2" ezfName="xxStartDplyTmTxt_A2" value="08:30:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A2" ezfName="xxEndDplyTmTxt_A2" value="17:00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A3" ezfName="xxStartDplyTmTxt_A3" value="08:30:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A3" ezfName="xxEndDplyTmTxt_A3" value="17:00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A4" ezfName="xxStartDplyTmTxt_A4" value="08:30:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A4" ezfName="xxEndDplyTmTxt_A4" value="17:00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A5" ezfName="xxStartDplyTmTxt_A5" value="08:30:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A5" ezfName="xxEndDplyTmTxt_A5" value="17:00:00" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A6" ezfName="xxStartDplyTmTxt_A6" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A6" ezfName="xxEndDplyTmTxt_A6" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A7" ezfName="xxStartDplyTmTxt_A7" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A7" ezfName="xxEndDplyTmTxt_A7" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxStartDplyTmTxt_A8" ezfName="xxStartDplyTmTxt_A8" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\""/></td>
                                            <td><ezf:inputText name="xxEndDplyTmTxt_A8" ezfName="xxEndDplyTmTxt_A8" ezfHyo="A" ezfArrayNo="0" otherEvent1=" onblur=\"onBlurEvent(this);\" onfocus=\"onFocusEvent(this);\"" otherAttr=" size=\"8\" ezftoupper=\"\" id=\"xxEndDplyTmTxt_A8#{EZF_ROW_NUMBER}\""/></td>
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
                                        <tr height="25">
                                            <td><input type="text" size="8" value="17:00:01" name="xxStartDplyTmTxt_A1" ezfname="xxStartDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="23:59:59" name="xxEndDplyTmTxt_A1" ezfname="xxEndDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="17:00:01" name="xxStartDplyTmTxt_A2" ezfname="xxStartDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="23:59:59" name="xxEndDplyTmTxt_A2" ezfname="xxEndDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="17:00:01" name="xxStartDplyTmTxt_A3" ezfname="xxStartDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="23:59:59" name="xxEndDplyTmTxt_A3" ezfname="xxEndDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="17:00:01" name="xxStartDplyTmTxt_A4" ezfname="xxStartDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="23:59:59" name="xxEndDplyTmTxt_A4" ezfname="xxEndDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="17:00:01" name="xxStartDplyTmTxt_A5" ezfname="xxStartDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="23:59:59" name="xxEndDplyTmTxt_A5" ezfname="xxEndDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A6" ezfname="xxStartDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A6" ezfname="xxEndDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A7" ezfname="xxStartDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A7" ezfname="xxEndDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A8" ezfname="xxStartDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A8" ezfname="xxEndDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                        <tr height="25">
                                            <td><input type="text" size="8" value="00:00:00" name="xxStartDplyTmTxt_A1" ezfname="xxStartDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="08:29:59" name="xxEndDplyTmTxt_A1" ezfname="xxEndDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="00:00:00" name="xxStartDplyTmTxt_A2" ezfname="xxStartDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="08:29:59" name="xxEndDplyTmTxt_A2" ezfname="xxEndDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="00:00:00" name="xxStartDplyTmTxt_A3" ezfname="xxStartDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="08:29:59" name="xxEndDplyTmTxt_A3" ezfname="xxEndDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="00:00:00" name="xxStartDplyTmTxt_A4" ezfname="xxStartDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="08:29:59" name="xxEndDplyTmTxt_A4" ezfname="xxEndDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="00:00:00" name="xxStartDplyTmTxt_A5" ezfname="xxStartDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="08:29:59" name="xxEndDplyTmTxt_A5" ezfname="xxEndDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A6" ezfname="xxStartDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A6" ezfname="xxEndDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A7" ezfname="xxStartDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A7" ezfname="xxEndDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A8" ezfname="xxStartDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A8" ezfname="xxEndDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                        <tr height="25">
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A1" ezfname="xxStartDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A1" ezfname="xxEndDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A2" ezfname="xxStartDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A2" ezfname="xxEndDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A3" ezfname="xxStartDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A3" ezfname="xxEndDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A4" ezfname="xxStartDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A4" ezfname="xxEndDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A5" ezfname="xxStartDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A5" ezfname="xxEndDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="08:30:00" name="xxStartDplyTmTxt_A6" ezfname="xxStartDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="17:00:00" name="xxEndDplyTmTxt_A6" ezfname="xxEndDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A7" ezfname="xxStartDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A7" ezfname="xxEndDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A8" ezfname="xxStartDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A8" ezfname="xxEndDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                        </tr>
                                        <tr height="25">
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A1" ezfname="xxStartDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A1" ezfname="xxEndDplyTmTxt_A1" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A2" ezfname="xxStartDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A2" ezfname="xxEndDplyTmTxt_A2" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A3" ezfname="xxStartDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A3" ezfname="xxEndDplyTmTxt_A3" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A4" ezfname="xxStartDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A4" ezfname="xxEndDplyTmTxt_A4" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A5" ezfname="xxStartDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A5" ezfname="xxEndDplyTmTxt_A5" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="17:01:00" name="xxStartDplyTmTxt_A6" ezfname="xxStartDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="23:59:59" name="xxEndDplyTmTxt_A6" ezfname="xxEndDplyTmTxt_A6" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A7" ezfname="xxStartDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A7" ezfname="xxEndDplyTmTxt_A7" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxStartDplyTmTxt_A8" ezfname="xxStartDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                            <td><input type="text" size="8" value="" name="xxEndDplyTmTxt_A8" ezfname="xxEndDplyTmTxt_A8" ezftoupper ezfHyo="A"></td>
                                        </tr>
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
                                <col width="40">
                                <col width="40">
                                <tr>
                                    <td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn7" /></td>
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

	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		} else if (item.value.length === 8){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4) + item.value.charAt(6) + item.value.charAt(7);
		}
		item.select();
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
		}else if(item.value.length === 5 && item.value.charAt(2) != (':')){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3) + ':' + item.value.charAt(4) + '0';
		}else if(item.value.length === 6){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3) + ':' + item.value.charAt(4) + item.value.charAt(5);
		}
	}
</script>


<%-- **** Task specific area ends here **** --%>
