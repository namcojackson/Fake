<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160506081343 --%>
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
            <input type="hidden" name="pageID" value="NPAL1010Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Location Info Popup">

<center>
    <table width="100%">
        <tr>
            <td valign="top">
<%-- ######################################## HEADER ######################################## --%>
                <table width="100%" cellpadding="1" cellspacing="1" align="center" border="0" bordercolor="red">
                    <col width="84"  align="left">
                    <col width=""  align="left">
                    <col width="84"  align="left">
                    <col width=""  align="left">
                    <col width="68"  align="left">
                    <col width=""  align="left">
                    <col width="255"  align="left">
                    <col width="5"  align="left">
                    <tr>
                        <td class="stab">WH Type</td>
                        <td ><ezf:select name="rtlWhCatgCd" ezfName="rtlWhCatgCd" ezfBlank="1" ezfCodeName="rtlWhCatgCd_B2" ezfDispName="rtlWhCatgDescTxt_B3" otherAttr=" id=\"8\" ezftoupper=\"\" style=\"width: 146px\""/>
                        </td>
                        <td  class ="stab">Inv Ownership</td>
                        <td><ezf:select name="invtyOwnrCd" ezfName="invtyOwnrCd" ezfBlank="1" ezfCodeName="invtyOwnrCd_A2" ezfDispName="invtyOwnrDescTxt_A3" otherAttr=" id=\"8\" ezftoupper=\"\" style=\"width: 145px\""/>
                        </td>
                        <td class="stab">WH Operation</td>
                        <td><ezf:select name="whOwnrCd" ezfName="whOwnrCd" ezfBlank="1" ezfCodeName="whOwnrCd_A2" ezfDispName="whOwnrDescTxt_A3" otherAttr=" id=\"8\" ezftoupper=\"\" style=\"width: 146px\""/>
                        </td>
                        <td>
                            <ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Mgr">Owner Code(*)</ezf:anchor>
                            <ezf:inputText name="whMgrPsnCd_H1" ezfName="whMgrPsnCd_H1" value="Q99999" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="stab">Location #(*)</td>
                        <td ><ezf:inputText name="xxRtrnInvtyLocSrchTxt" ezfName="xxRtrnInvtyLocSrchTxt" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                        <td class="stab">Location Name(*)</td>
                        <td ><ezf:inputText name="rtlWhNmSrchTxt" ezfName="rtlWhNmSrchTxt" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                        <td class="stab">Inv Accnt</td>
                        <td><ezf:select name="invtyAcctCd" ezfName="invtyAcctCd" ezfBlank="1" ezfCodeName="invtyAcctCd_A2" ezfDispName="invtyAcctNm_A3" otherAttr=" id=\"8\" ezftoupper=\"\" style=\"width: 146px\""/>
                        </td>
                        <td>
                            <ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Mgr">Owner Name(*)</ezf:anchor>
                            <ezf:inputText name="fullPsnNm_H1" ezfName="fullPsnNm_H1" value="ED PETNER" otherAttr=" size=\"20\" maxlength=\"62\" ezftoupper=\"\""/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="stab">WH Code(*)</td>
                        <td ><ezf:inputText name="xxRtrnInvtyLocSrchTxt_RW" ezfName="xxRtrnInvtyLocSrchTxt_RW" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                        <td class="stab">WH Name(*)</td>
                        <td ><ezf:inputText name="rtlWhNmSrchTxt_RW" ezfName="rtlWhNmSrchTxt_RW" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                        <td class="stab">Postal Code</td>
                        <td ><ezf:inputText name="postCd" ezfName="postCd" otherAttr=" size=\"20\" maxlength=\"15\" ezftoupper=\"\""/></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="stab">SWH Code(*)</td>
                        <td ><ezf:inputText name="xxRtrnInvtyLocSrchTxt_SW" ezfName="xxRtrnInvtyLocSrchTxt_SW" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                        <td class="stab">SWH Name(*)</td>
                        <td ><ezf:inputText name="rtlWhNmSrchTxt_SW" ezfName="rtlWhNmSrchTxt_SW" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\""/></td>
                        <td class="stab">City(*)</td>
                        <td ><ezf:inputText name="ctyAddr" ezfName="ctyAddr" otherAttr=" size=\"20\" maxlength=\"25\" ezftoupper=\"\""/></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="stab">Display WH Level</td>
                        <td align="left"><ezf:inputCheckBox name="xxChkBox" ezfName="xxChkBox" value="Y"/></td>
                        <td class="stab">Address(*)</td>
                        <td ><ezf:inputText name="firstLineAddr" ezfName="firstLineAddr" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/></td>
                        <td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Search_StateCode">State</ezf:anchor></td>
                        <td ><ezf:inputText name="stCd" ezfName="stCd" otherAttr=" size=\"20\" maxlength=\"2\" ezftoupper=\"\""/></td>
                        <td valign="bottom" align="right"><ezf:inputButton name="Search_Location" value="Search" htmlClass="pBtn6"/></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NPAL1010.NPAL1010BMsg" %>
<%@ page import="business.servlet.NPAL1010.NPAL1010_ABMsg" %>
<%  NPAL1010BMsg bMsg = (NPAL1010BMsg)databean.getEZDBMsg(); %>
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="left" valign="top">
                            <%-- ******************************* --%>
                            <%-- *** Left Table Area(Header) *** --%>
                            <%-- ******************************* --%>
                            <table style="table-layout:fixed; margin-left:0px;margin-top:0px;padding:0px;" border="1" cellpadding="1" cellspacing="0" height="35">
                                <% if ("Y".equals(bMsg.xxSelFlg_MS.getValue())) { %>
                                <col width="30" align="center">
                                <% } %>
                                <col width="65" align="center">
                                <tr>
                                    <% if ("Y".equals(bMsg.xxSelFlg_MS.getValue())) { %>
                                    <td align="center" class="pClothBs"></td>
                                    <% } %>
                                    <td align="center" class="pClothBs">
                                        <a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyLocCd_A' )">WH<img id="sortIMG.invtyLocCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                    </td>
                                </tr>
                            </table>
                            <%-- ******************************* --%>
                            <%-- *** Left Table Area(Detail) *** --%>
                            <%-- ******************************* --%>
                            <div id="LeftTBL" style="overflow-y:hidden; height:370; overflow-x:hidden; width:;"
                                onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Left">
                                    <% if ("Y".equals(bMsg.xxSelFlg_MS.getValue())) { %>
                                    <col width="30" align="center">
                                    <% } %>
                                    <col width="65" align="center">
                                    <ezf:row ezfHyo="A">
                                        <tr height="28">
                                            <% if ("Y".equals(bMsg.xxSelFlg_MS.getValue())) { %>
                                            <td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"OnChange_ChkBoxSoNum{EZF_ROW_NUMBER}\""/></td>
                                            <% } %>
                                            <td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_LocationCd" ><ezf:label name="invtyLocCd_A" ezfName="invtyLocCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                        </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                    </ezf:skip>
                                </table>
                            </div>
                        </td>
                        <%-- =============== TABLE HEADER =============== --%>
                        <td valign="top">
                            <div id="topRightTBL" style="overflow-y:none; overflow-x:hidden; width:875; margin-top:0px;padding:0px;">
                                <table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" height="35" width="">
                                    <col width="111" align="center">
                                    <col width="111" align="center">
                                    <col width="111" align="center">
                                    <col width="72" align="center">
                                    <col width="65" align="center">
                                    <col width="75" align="center">
                                    <col width="80" align="center">
                                    <col width="150" align="center">
                                    <col width="65" align="center">
                                    <col width="220" align="center">
                                    <col width="160" align="center">
                                    <col width="280" align="center">
                                    <col width="80" align="center">
                                    <tr>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A' )">WH Name<img id="sortIMG.rtlWhNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhNm_A' )">SWH Name<img id="sortIMG.rtlSwhNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhCatgNm_A' )">WH Type<img id="sortIMG.rtlWhCatgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyOwnrNm_A' )">Inv<br>Ownership<img id="sortIMG.invtyOwnrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'whOwnrNm_A' )">WH<br>Operation<img id="sortIMG.whOwnrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyAcctNm_A' )">Inv Accnt<img id="sortIMG.invtyAcctNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'whMgrPsnCd_A' )">Owner Code<img id="sortIMG.whMgrPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'fullPsnNm_A' )">Owner Name<img id="sortIMG.fullPsnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
	                                    <td class="pClothBs">
	                                        <a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNum_A' )">Loc #<img id="sortIMG.locNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
	                                    </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'locNm_A' )">Loc Name<img id="sortIMG.locNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'addlLocNm_A' )">Additional Name<img id="sortIMG.addlLocNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxEdtAddr_A' )">Adress, City, State<img id="sortIMG.xxEdtAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                        <td class="pClothBs">
                                            <a href="#" class="pSortCol" onclick="columnSort( 'A', 'postCd_A' )">Postal Cd<img id="sortIMG.postCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <%-- =============== TABLE BODY =============== --%>
                            <div id="RightTBL" style="overflow-y:scroll; height:387; overflow-x:scroll; width:892;" 
                                onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) );synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A_Right" width="">
                                    <col width="111" align="center">
                                    <col width="111" align="center">
                                    <col width="111" align="center">
                                    <col width="72" align="center">
                                    <col width="65" align="center">
                                    <col width="75" align="center">
                                    <col width="80" align="center">
                                    <col width="150" align="center">
                                    <col width="65" align="center">
                                    <col width="220" align="center">
                                    <col width="160" align="center">
                                    <col width="280" align="center">
                                    <col width="80" align="center">
                                    <ezf:row ezfHyo="A">
                                        <tr height="28px">
                                            <td><ezf:inputText name="rtlWhNm_A" ezfName="rtlWhNm_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"rtlWhNm\""/></td>
                                            <td><ezf:inputText name="rtlSwhNm_A" ezfName="rtlSwhNm_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"rtlSwhNm\""/></td>
                                            <td><ezf:inputText name="rtlWhCatgNm_A" ezfName="rtlWhCatgNm_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" id=\"rtlWhCatgNm\""/></td>
                                            <td><ezf:inputText name="invtyOwnrNm_A" ezfName="invtyOwnrNm_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"30\" id=\"invtyOwnrNm\""/></td>
                                            <td><ezf:inputText name="whOwnrNm_A" ezfName="whOwnrNm_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"20\" id=\"whOwnrNm\""/></td>
                                            <td><ezf:inputText name="invtyAcctNm_A" ezfName="invtyAcctNm_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"9\" maxlength=\"20\" id=\"invtyAcctNm\""/></td>
                                            <td><ezf:inputText name="whMgrPsnCd_A" ezfName="whMgrPsnCd_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"whMgrPsnCd_A\""/></td>
                                            <td><ezf:inputText name="fullPsnNm_A" ezfName="fullPsnNm_A" value="12345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"62\" id=\"fullPsnNm_A\""/></td>
                                            <td><ezf:inputText name="locNum_A" ezfName="locNum_A" value="0000001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" id=\"locNum\""/></td>
                                            <td><ezf:inputText name="locNm_A" ezfName="locNm_A" value="LOC NM Z02" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\" id=\"locNm\""/></td>
                                            <td><ezf:inputText name="addlLocNm_A" ezfName="addlLocNm_A" value="123456789012345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"60\" id=\"addlLocNm\""/></td>
                                            <td><ezf:inputText name="xxEdtAddr_A" ezfName="xxEdtAddr_A" value="1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"39\" maxlength=\"100\" id=\"xxEdtAddr\""/></td>
                                            <td><ezf:inputText name="postCd_A" ezfName="postCd_A" value="123456789012345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\" id=\"postCd\""/></td>
                                        </tr>
                                    </ezf:row>
                                    <ezf:skip>
                                    </ezf:skip>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</center>



<%-- **** Task specific area ends here **** --%>
