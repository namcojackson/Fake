<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20231101134528 --%>
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
            <input type="hidden" name="pageID" value="NLBL3060Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Warehouse Permissions Setup">


<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">
<%-- ######################################## HEADER ######################################## --%>
                <%-- ###TAB - HEAD --%>
                <ezf:skip>
                <div class="pTab_HEAD">
                    <ul>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="96%">
                                    <div>
                                        <li title="Wh Perms" class="pTab_ON"><a href="#">Wh Perms</a></li>
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
                <table width="1100" height="65" border = "0">
                    <!--<col align="left" width="100">
                    <col align="left" width="500">
                    <col align="left" width="200">
                    <col align="left" width="15">-->
                    <col align="left" width="50">
                    <col align="left" width="50">
                    <col align="left" width="35">
                    <col align="left" width="80">
                    <col align="left" width="50">
                    <col align="left" width="20">
                    <col align="left" width="15">
                    <tr>
                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_RtlWh" otherAttr=" id=\"\" ezfanchortext=\"\"">Retail Warehouse</ezf:anchor></td>
                        <td><ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="XXXX1XXXX2XXX" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/>
                            <ezf:inputButton name="Click_RtlWhName" value=">>" htmlClass="pBtn0" />
                            <ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="XXXX1XXXX2XXX" otherAttr=" size=\"33\" maxlength=\"13\" ezftoupper=\"\""/>
                        </td>
                        <td class="stab">WH Type</td>
                        <td>
                        <ezf:select name="rtlWhCatgCd_HD" ezfName="rtlWhCatgCd_HD" ezfBlank="1" ezfCodeName="rtlWhCatgCd_PD" ezfDispName="rtlWhCatgNm_PD" otherAttr=" style=\"width:120\""/>
                        </td>
                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_GroupName" otherAttr=" id=\"\" ezfanchortext=\"\"">Group Name(*)</ezf:anchor></td>
                        <td><ezf:inputText name="physWhCd_HD" ezfName="physWhCd_HD" otherAttr=" size=\"12\" maxlength=\"20\" ezftoupper=\"\""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Link_Person" otherAttr=" id=\"\" ezfanchortext=\"\"">Permitted User</ezf:anchor></td>
                        <td><ezf:inputText name="schdCoordPsnCd" ezfName="schdCoordPsnCd" value="XXXX1XXXX2XXX" otherAttr=" size=\"12\" maxlength=\"8\" ezftoupper=\"\""/>
                            <ezf:inputButton name="Click_PersonName" value=">>" htmlClass="pBtn0" />
                            <ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="XXXX1XXXX2XXX" otherAttr=" size=\"33\" maxlength=\"13\" ezftoupper=\"\""/>
                        </td>
                        <td></td>
                        <td></td>
                        <td colspan="2"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y" /> Open Setup Only</td>
                        <td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
                    </tr>
                </table>

<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NLBL3060.NLBL3060BMsg" %>
<%@ page import="business.servlet.NLBL3060.NLBL3060_ABMsg" %>
<%@ page import="business.servlet.NLBL3060.common.NLBL3060CommonLogic" %>
<%  NLBL3060BMsg bMsg = (NLBL3060BMsg)databean.getEZDBMsg(); %>
                    <table>
                        <hr>
                        <col valign="top">
                        <tr>
                            <td>
                                <table width="610px" cellSpacing="0" cellPadding="0" border="0" style="float: left;">
                                    <colgroup>
                                        <col width="80">
                                        <col width="80">
                                        <col width="450">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <td><ezf:inputButton name="OnClick_CheckAll" value="CheckAll" htmlClass="pBtn6" /></td>
                                            <td><ezf:inputButton name="OnClick_UnCheck" value="UnCheck" htmlClass="pBtn6" /></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <ezf:skip>
                                <TABLE width="342px" cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 27px;">
                                    <COLGROUP>
                                        <COL align="center" width="54">
                                        <COL align="right" width="40">
                                        <COL align="center" width="16">
                                        <COL align="right" width="40">
                                        <COL align="center" width="16">
                                        <COL align="right" width="40">
                                        <COL width="10">
                                        <COL>
                                        <COL width="1">
                                        <COL>
                                    </COLGROUP>
                                    <TBODY>
                                    <TR>
                                        <TD class="stab"><LABEL>Showing</LABEL></TD>
                                        <TD class="pOut">1</TD>
                                        <TD class="stab"><LABEL>to</LABEL></TD>
                                        <TD class="pOut">6</TD>
                                        <TD class="stab"><LABEL>of</LABEL></TD>
                                        <TD class="pOut">6</TD>
                                        <TD>&nbsp;</TD>
                                        <TD><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></TD>
                                        <TD></TD>
                                        <TD><INPUT onclick="tablePagenation(this.name, 'A')" disabled id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></TD>
                                    </TR>
                                    </TBODY>
                                </TABLE>
                                </ezf:skip>
                                <jsp:include page="../tablePagenation/S21TablePagenation.jsp">
                                    <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                    <jsp:param name="TableNm"     value="A" />
                                    <jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
                                    <jsp:param name="ShowingTo"   value="xxPageShowToNum" />
                                    <jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
                                </jsp:include>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <!--<div id="topTBL" style="overflow-x:hidden; width:990; overflow-y:none; height:30;">-->
                                <div id="topTBL" style="overflow-x:hidden; width:1080; overflow-y:none; height:30;">
                                    <table border="1" cellpadding="1" cellspacing="0">
                                        <!--<col align="center" width="30">
                                        <col align="center" width="324">
                                        <col align="center" width="334">
                                        <col align="center" width="135">
                                        <col align="center" width="135">-->
                                        <col align="center" width="20">
                                        <col align="center" width="100">
                                        <col align="center" width="150">
                                        <col align="center" width="290">
                                        <col align="center" width="290">
                                        <col align="center" width="100">
                                        <col align="center" width="100">
                                        
                                        <tr style="height:30;">
                                            <td class="pClothBs">&nbsp;</td>
                                            <td class="pClothBs">WH Type</td>
                                            <td class="pClothBs">Group Name</td>
                                            <td class="pClothBs">Retail Warehouse</td>
                                            <td class="pClothBs">Permitted User</td>
                                            <td class="pClothBs">Eff From Date</td>
                                            <td class="pClothBs">Eff Thru Date</td>
                                        </tr>
                                    </table>
                                </div>
<%-- ### TABLE A ### --%>
                               <!--<div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:1000; height:390;">-->
                                <div id="bottomTBL" style="overflow-x:none; overflow-y:scroll; width:1096; height:390;">
                                    <table border="1" cellpadding="1" cellspacing="0" id="A">
                                        <!--<col align="center" width="30">
                                        <col align="left" width="324">
                                        <col align="left" width="334">
                                        <col align="center" width="135">
                                        <col align="center" width="135">-->
                                        <col align="center" width="20">
                                        <col align="center" width="100">
                                        <col align="center" width="150">
                                        <col align="left" width="290">
                                        <col align="left" width="290">
                                        <col align="center" width="100">
                                        <col align="center" width="100">
                                        <% int i = 0; %>
                                        <ezf:row ezfHyo="A">
                                        <% NLBL3060_ABMsg detailMsg = bMsg.A.no(i++); %>
                                        <tr height="28">
                                            <td>
                                                <ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/>
                                            </td>
                                            <td>
                                                <ezf:select name="rtlWhCatgCd_A1" ezfName="rtlWhCatgCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rtlWhCatgCd_PD" ezfDispName="rtlWhCatgNm_PD" otherAttr=" id=\"\" style=\"width:90px;\""/>
                                            </td>
                                            <td>
                                                <ezf:inputButton name="Btn_GroupName" value="Gr" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
                                                <ezf:inputText name="physWhCd_A1" ezfName="physWhCd_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"20\" id=\"physWhCd_A1#{EZF_ROW_NUMBER}\""/>
                                            </td>   
                                            <td>
                                                <ezf:inputButton name="Btn_DtlRtlWh" value="RWH" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
                                                <ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"8\" id=\"rtlWhCd_A1#{EZF_ROW_NUMBER}\""/>
                                                <ezf:inputButton name="Click_DtlRtlWhName" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                <ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"13\" id=\"rtlWhNm_A1#{EZF_ROW_NUMBER}\""/>
                                            </td>
                                            <td>
                                                <ezf:inputButton name="Btn_DtlPerson" value="PSN" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
                                                <ezf:inputText name="schdCoordPsnCd_A1" ezfName="schdCoordPsnCd_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"8\" id=\"schdCoordPsnCd_A1#{EZF_ROW_NUMBER}\""/>
                                                <ezf:inputButton name="Click_DtlPersonName" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" />
                                                <ezf:inputText name="fullPsnNm_A1" ezfName="fullPsnNm_A1" value="XXXX1XXXX2XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"13\" id=\"fullPsnNm_A1#{EZF_ROW_NUMBER}\""/>
                                            </td>
                                            <td class="stab">
                                                <ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" value="10/10/09" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_A1#{EZF_ROW_NUMBER}\""/>
                                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');">
                                            </td>   
                                            <td class="stab">
                                                <ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" value="10/10/09" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_A1#{EZF_ROW_NUMBER}\""/>
                                                <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');">
                                            </td>
                                        </tr>
                                        </ezf:row>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr>
							<td width="950" height="35">
							  <table border="0" cellpadding="0" cellspacing="0" style="float: left;">
                                        <col width="115" align="left">
                                        <col width=""    align="left">
                                        <col width="5"   align="left">
                                        <col width=""    align="left">
                                        <tr>
                                            <td class="stab">
                                                <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OnClick_TemplateDownload" >Upload Request</ezf:anchor>
                                            </td>
                                            <td><ezf:inputFile name="xxFileData_UP" ezfName="xxFileData_UP" otherAttr=" size=\"50\" maxlength=\"9999\""/></td>
                                            <td></td>
                                            <td><ezf:inputButton name="OnClick_Upload" value="Upload" htmlClass="pBtn6" /></td>
                                        </tr>
                                </table>
								<table border="0" cellpadding="0" cellspacing="0" style="float: right;">
									<col width="81">
									<col width="10">
									<col width="81">
									<tr>
										<td align="left"><ezf:inputButton name="Insert_Row" value="Insert Row" htmlClass="pBtn7" /></td>
										<td align="left"><ezf:inputButton name="Delete_Row" value="Delete Row" htmlClass="pBtn7" /></td>
									</tr>
								</table>
							</td>
						</tr>
<%-- ######################################## FOOTER ######################################## --%>
                    </table>
                </div><!-- pTab_BODY -->
            </td>
        </tr>
    </table>
</center>

<%-- **** Task specific area ends here **** --%>
