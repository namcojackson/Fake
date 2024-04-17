<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160207180038 --%>
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
            <input type="hidden" name="pageID" value="NMAL6840Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Sub Warehouse Setup">
            
<%-- ######################################## UPPER TAB AREA ######################################## --%>

            <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

<%-- ######################################## HEADER AREA ######################################## --%>
            <center>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <ezf:skip>
                            <div class="pTab_HEAD">
                                <ul>
                                    <li class="pTab_ON">
                                            <a href="./NMAL6840Scrn00.html">Sub&nbsp;WH</a>
                                    </li>
                                </ul>
                            </div>
                            </ezf:skip>
                            
                            <div class="pTab_BODY">
                                <table  style="margin-left:5px; margin-top:0px;">
                                    <col width="400px"  align="left">
                                    <tr>
                                        <td>
                                            <table border="0">
                                                <col width="80px"  align="left">
                                                <col width="60px"  align="left">
                                                <col width="10px"  align="left">
                                                <col width="110px" align="left">
                                                <%-- =============== Warehouse Category =============== --%>
                                                <tr height="20px">
                                                    <td class="stab">WH&nbsp;Category</td>
                                                    <td>
                                                        <ezf:select name="rtlWhCatgCd_H1" ezfName="rtlWhCatgCd_H1" ezfCodeName="rtlWhCatgCd_L1" ezfDispName="rtlWhCatgNm_L1" />
                                                    </td>
                                                    <td></td>
                                                    <td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <hr>

                            <%-- ######################################## DETAIL AREA ######################################## --%>
                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td align="left" valign="top">
                                        <%-- ******************************* --%>
                                        <%-- *** Left Table Area(Header) *** --%>
                                        <%-- ******************************* --%>
                                        <table style="table-layout:fixed; margin-left: 5px;" border="1" cellpadding="1" cellspacing="0" height="35">
                                            <col width="40px"  align="center">
                                            <col width="80px"  align="center">
                                            <col width="150px"  align="center">
                                            <col width="220px"  align="center">
                                            <col width="150px"  align="center">
                                            <col width="50px"  align="center">
                                            <col width="50px"  align="center">
                                            <col width="50px"  align="center">
                                            <col width="65px"  align="center">
                                            <tr>
                                                <td class="pClothBs">&nbsp;</td>
                                                <td class="pClothBs">Sub Warehouse</td>
                                                <td class="pClothBs">Sub Warehouse Name</td>
                                                <td class="pClothBs">Sub Warehouse Description</td>
                                                <td class="pClothBs">Cost&nbsp;Type</td>
                                                <td class="pClothBs">Cost&nbsp;(%)</td>
                                                <td class="pClothBs">Sort#</td>
                                                <td class="pClothBs">Disable</td>
                                                <td class="pClothBs">Mandatory</td>
                                            </tr>
                                        </table>
                                        <%-- ******************************* --%>
                                        <%-- *** Left Table Area(Detail) *** --%>
                                        <%-- ******************************* --%>
                                        <div id="LeftTBL" style="overflow-y:scroll; height:450; overflow-x:hidden; width:; margin-left: 5px;"
                                            onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
                                            <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                                <col width="40px"  align="center">
                                                <col width="80px"  align="center">
	                                            <col width="150px"  align="center">
	                                            <col width="220px"  align="center">
	                                            <col width="150px"  align="center">
	                                            <col width="50px"  align="center">
	                                            <col width="50px"  align="center">
	                                            <col width="50px"  align="center">
	                                            <col width="65px"  align="center">
                                                <ezf:row ezfHyo="A">
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    </tr>
                                                </ezf:row>
                                                <ezf:skip>
                                                    <tr height="28">
                                                        <td><input type="checkbox" name="xxChkBox_A1" value="Y" ezfname="xxChkBox_A1" ezfhyo="A"></td>
                                                        <td>
                                                            <select class="pHsu" name="rtlSwhCd_A1" ezfname="rtlSwhCd_A1" ezfhyo="A" ezflist="rtlSwhCd_L1,rtlSwhCd_LD,99, ,blank" style="width:75px" onchange="sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')" id="OnChange_SWH{EZF_ROW_NUMBER}">
                                                            <ezf:skip>
                                                                <option selected>NEW</option>
                                                                <option>U90</option>
                                                                <option>U70</option>
                                                                <option>U30</option>
                                                                <option>U00</option>
                                                                <option>G</option>
                                                                <option>R</option>
                                                            </ezf:skip>
                                                            </select>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="1" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="1" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="1" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="1" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="1" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="2" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="2" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="2" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="2" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="2" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="3" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="3" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="3" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="3" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="3" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="4" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="4" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="4" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="4" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="4" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="5" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="5" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="5" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="5" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="5" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="6" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="6" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="6" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="6" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="6" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="7" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="7" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="7" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="7" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="7" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="8" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="8" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="8" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="8" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="8" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="9" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="9" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="9" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="9" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="9" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="10" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="10" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="10" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="10" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="10" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="10" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="10" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="10" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="10" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="11" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="11" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="11" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="11" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="11" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="11" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="11" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="11" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="11" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="12" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="12" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="12" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="12" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="12" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="12" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="12" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="12" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="12" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="13" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="13" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="13" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="13" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="13" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="13" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="13" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="13" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="13" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="14" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="14" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="14" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="14" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="14" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="14" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="14" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="14" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="14" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="15" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="15" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="15" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="15" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="15" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="15" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="15" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="15" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="15" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="16" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="16" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="16" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="16" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="16" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="16" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="16" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="16" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="16" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="17" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="17" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="17" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="17" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="17" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="17" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="17" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="17" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="17" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="18" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="18" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="18" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="18" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="18" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="18" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="18" /></td>
                                                    </tr>
                                                    <tr height="28">
                                                        <td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="18" /></td>
                                                        <td>
                                                            <ezf:select name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="18" ezfBlank="1" ezfCodeName="rtlSwhCd_L1" ezfDispName="rtlSwhCd_LD" otherEvent1=" onchange=\"sendServer('OnChange_SWH', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75px\" id=\"OnChange_SWH{EZF_ROW_NUMBER}\""/>
                                                        </td>
	                                                    <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="NEW" ezfHyo="A" ezfArrayNo="19" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhDescTxt_A1" ezfName="rtlSwhDescTxt_A1" value="NEW" ezfHyo="A" ezfArrayNo="19" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseCostTpNm_A1" ezfName="mdseCostTpNm_A1" value="Standard" ezfHyo="A" ezfArrayNo="19" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
	                                                    <td><ezf:inputText name="mdseInvtyCostPct_A1" ezfName="mdseInvtyCostPct_A1" value="100" ezfHyo="A" ezfArrayNo="19" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputText name="rtlSwhSortNum_A1" ezfName="rtlSwhSortNum_A1" value="1" ezfHyo="A" ezfArrayNo="19" otherAttr=" size=\"5\" maxlength=\"3\" style=\"text-align:right;\""/></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhDsblFlg_A1" ezfName="rtlSwhDsblFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="19" /></td>
	                                                    <td><ezf:inputCheckBox name="rtlSwhMndFlg_A1" ezfName="rtlSwhMndFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="19" /></td>
                                                    </tr>
                                                </ezf:skip>
                                            </table>
                                        </div>
                                    </td>
                                    <%-- =============== TABLE HEADER =============== --%>
                                </tr>
                            </table>
                                <%-- =============== Button Group =============== --%>
                            <table border="0" cellpadding="1" cellspacing="0" width="885">
                                <tr height="40">
                                    <td align="right">
                                        <ezf:inputButton name="OnClick_InsertRow" value="Insert Row" htmlClass="pBtn8" />
                                        <ezf:inputButton name="OnClick_DeleteRow" value="Delete Row" htmlClass="pBtn8" />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </center>

<%-- **** Task specific area ends here **** --%>
