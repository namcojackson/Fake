<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230920134757 --%>
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
            <input type="hidden" name="pageID" value="NFDL0100Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Print Invoice">

<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
<%-- ######################################## Search Criteria - [START] ######################################## --%>
                <%-- ********************** --%>
                <%-- *** Upper Tab Area *** --%>
                <%-- ********************** --%>
                <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                <ezf:skip>
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                        <tr>
                            <td width="1070px" height="28px" valign="bottom">
                                <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                    <tr title="Print Inv">
                                        <td width="107px" align="center" class="same">Print Inv</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </ezf:skip>

                <div class="pTab_BODY" style="WIDTH: 1133px; word-break:kepp-all">
                    <table width="1121" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="30">
                        <col width="120">
                        <col width="60">
                        <col width="210">
                        <col width="40">
                        <col width="45">
                        <col width="60">
                        <col width="210">
                        <col width="70">
                        <col width="50">
                        <col width="">
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">Customer Account#</td>
                            <td><ezf:inputText name="billToCustAcctCd_H" ezfName="billToCustAcctCd_H" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td><ezf:inputText name="dsAcctNm_H" ezfName="dsAcctNm_H" value="123456789A123456789B" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td class="stab"></td>
                            <td class="stab">Bill To</td>
                            <td>
                                <ezf:inputText name="billToCustCd_H" ezfName="billToCustCd_H" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
                            </td>
                            <td><ezf:inputText name="locNm_H" ezfName="locNm_H" value="123456789A123456789B" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td><ezf:inputButton name="OpenWin_Attach" value="Attachment" htmlClass="pBtn6" /></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table width="1121" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="30">
                        <col width="120">
                        <col width="60">
                        <col width="20">
                        <col width="120">
                        <col width="100">
                        <col width="30">
                        <col width="100">
                        <col width="60">
                        <col width="30">
                        <col width="80">
                        <col width="120">
                        <col width="20">
                        <col width="120">
                        <col width="">
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">PO Number(*)</td>
                            <td><ezf:inputText name="custIssPoNum_H1" ezfName="custIssPoNum_H1" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"35\""/></td>
                            <td>&nbsp;</td>
                            <td class="stab">Inv/Bill Number(*)</td>
                            <td><ezf:inputText name="arCustRefNum_H1" ezfName="arCustRefNum_H1" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
							<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                            <!-- <td class="stab">Bill To Loc#(*)</td> -->
                            <td class="stab">Bill To Code(*)</td>
							<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
                            <td><ezf:inputText name="billToCustCd_H1" ezfName="billToCustCd_H1" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td class="stab">Bill Date</td>
                            <td><ezf:inputText name="arTrxDt_H1" ezfName="arTrxDt_H1" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('arTrxDt_H1', 4);"/></td>
                            <td>-</td>
                            <td><ezf:inputText name="arTrxDt_H2" ezfName="arTrxDt_H2" value="MM/DD/YYYY" otherAttr=" size=\"12\" maxlength=\"10\" ezftoupper=\"\""/><IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('arTrxDt_H2', 4);"/></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                    <table width="1121" cellSpacing="0" cellPadding="0" border="0" style="margin-top:3px">
                        <col width="30">
                        <col width="120">
                        <col width="60">
                        <col width="20">
                        <col width="100">
                        <col width="20">
                        <col width="100">
                        <col width="30">
                        <col width="100">
                        <col width="120">
                        <col width="20">
                        <col width="120">
                        <col width="100">
                        <col width="50">
                        <col width="">
                        <tr>
                            <td>&nbsp;</td>
                            <td class="stab">Contract Number(*)</td>
                            <td><ezf:inputText name="xxDplyTrxNumTxt_H1" ezfName="xxDplyTrxNumTxt_H1" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td class="stab">Include Closed<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" /></td>
                            <td>&nbsp;</td>
                            <td class="stab">Include Credits<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" /></td>
                            <td>&nbsp;</td>
                            <td class="stab">Serial Number (*)</td>
                            <td><ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="123456789A123456789B" otherAttr=" size=\"10\" maxlength=\"35\" ezftoupper=\"\""/></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><ezf:inputButton name="Click_Btn_Refresh" value="Refresh" htmlClass="pBtn6" /></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
<%-- ######################################## Search Result - [START] ######################################## --%>
                <hr>
                <table border="0" cellpadding="1" cellspacing="0" width="1100">
                    <tr>
                        <td>
                            <table border="0" cellpadding="1" cellspacing="0" width="100%" border="1">
                                <col width="5">
                                <col width="210">
                                <col width="300">
                                <col width="50">
                                <col width="600" align="right">
                                <col width="100" align="center">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                    </td>
                                    <td>
                                        <ezf:inputButton name="Click_Btn_SelectAll" value="Select All" htmlClass="pBtn8" />
                                        <ezf:inputButton name="Click_Btn_UnselectAll" value="UnSelect All" htmlClass="pBtn8" />
                                    </td>
                                    <td>&nbsp;</td>
                                    <td  align="right">
                                        <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                        <jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
                                        <jsp:param name="TableNm"     value="A" />
                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
                                        <jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
                                        <jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
                                        <jsp:param name="ShowingCurrent" value="xxPageShowCurNum_A" />
                                        <jsp:param name="ShowingTotal"   value="xxPageShowTotNum_A" />
                                        <jsp:param name="ViewMode"       value="FULL" />
                                        </jsp:include>
                                    </td>
                                    <td class=""><ezf:inputButton name="Click_Btn_Email" value="Email" htmlClass="pBtn4" /></td>
                                    <td class="stab">Individual<ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <table border="0" cellpadding="1" cellspacing="0">
                    <col width="5">
                    <col width="">
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <div id="parentBoxA">
                                <div style="float:left; display:block"><!-- left table -->
                                    <div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
                                    <div id="leftTbl" style="float:left; display:block; height:377px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
                                </div><!-- left table -->
                                <div style="float:left"><!-- right table -->
                                    <div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;" >
                                        <table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" width="1830px" style="margin-right:20px">
                                            <col width="30" align="center">
                                            <col width="100" align="center">
                                            <col width="150"  align="center">
                                            <col width="150"  align="center">
                                            <col width="150"  align="center">
                                            <col width="150"  align="center">
                                            <col width="140"  align="center">
                                            <col width="140"  align="center">
                                            <col width="140"  align="center">
                                            <col width="140"  align="center">
                                            <col width="140"  align="center">
                                            <col width="140"  align="center">
                                            <tr height="32">
                                                <td class="pClothBs colFix" id="AH0"></td>
                                                <!-- Mod Start 2020/02/25 M.Furugoori QC#55489 -->
												<!-- Mod Start 2017/10/05 H.Sugawara QC#19922 -->
                                                <!-- <td class="pClothBs"        id="AH1">Bill-To-Loc</td> -->
                                                <!-- <td class="pClothBs"        id="AH1">Bill-To-Code</td> -->
												<!-- Mod End   2017/10/05 H.Sugawara QC#19922 -->
                                                <!-- <td class="pClothBs"        id="AH2">Contract/Order</td> -->
                                                <!-- <td class="pClothBs"        id="AH3">Invoice No</td> -->
                                                <!-- <td class="pClothBs"        id="AH4">Bill No</td> -->
                                                <!-- <td class="pClothBs"        id="AH5">Doc Type</td> -->
                                                <!-- <td class="pClothBs"        id="AH6">Orig Amt</td> -->
                                                <!-- <td class="pClothBs"        id="AH7">Remain Amt</td> -->
                                                <!-- <td class="pClothBs"        id="AH8">Inv Date</td> -->
                                                <!-- <td class="pClothBs"        id="AH9">Due Date</td> -->
                                                <!-- <td class="pClothBs"        id="AH10">Days Past Due</td> -->
                                                <!-- <td class="pClothBs"        id="AH11">PO Number</td> -->
                                                <td class="pClothBs"        id="AH1"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'billToCustCd_A' )">Bill-To-Code<img id="sortIMG.billToCustCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDplyTrxNumTxt_A' )">Contract/Order<img id="sortIMG.xxDplyTrxNumTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH3"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arCustRefNum_A' )">Invoice No<img id="sortIMG.arCustRefNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH4"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'grpInvNum_A' )">Bill No<img id="sortIMG.grpInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH5"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem130Txt_A' )">Doc Type<img id="sortIMG.xxScrItem130Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH6"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealOrigGrsAmt_A' )">Orig Amt<img id="sortIMG.dealOrigGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH7"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dealRmngBalGrsAmt_A' )">Remain Amt<img id="sortIMG.dealRmngBalGrsAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH8"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'arTrxDt_A' )">Inv Date<img id="sortIMG.arTrxDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH9"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDueDt_A' )">Due Date<img id="sortIMG.invDueDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH10"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'daysPastDueAot_A' )">Days Past Due<img id="daysPastDueAot_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <td class="pClothBs"        id="AH11"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'custIssPoNum_A' )">PO Number<img id="sortIMG.custIssPoNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                <!-- Mod End   2020/02/25 M.Furugoori QC#55489 -->
                                            </tr>
                                        </table>
                                    </div>
                                    <div id="rightTbl" style="width:1118px; height:394px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                        <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1830px">
                                            <col width="30" align="center">      <!--Check Box-->      
                                            <col width="100" align="left">       <!--Bill-To-Loc-->
                                            <col width="150"  align="left">      <!--Contract/Order-->
                                            <col width="150"  align="left">      <!--Invoice No-->
                                            <col width="150"  align="left">      <!--Bill Num-->
                                            <col width="150"  align="left">      <!--Doc Type-->
                                            <col width="140"  align="right">     <!--Orig Amt-->
                                            <col width="140"  align="right">     <!--Remain Amt-->
                                            <col width="140"  align="center">    <!--Inv Date-->
                                            <col width="140"  align="center">    <!--Due Date-->
                                            <col width="140"  align="right">     <!--Days Past Due-->
                                            <col width="140"  align="left">      <!--PO Number-->
                                            <%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
											<%@ page import="business.servlet.NFDL0100.NFDL0100BMsg" %>
											<%@ page import="business.servlet.NFDL0100.NFDL0100_ABMsg" %>
											<% NFDL0100BMsg bMsg = (NFDL0100BMsg) databean.getEZDBMsg(); %>
											
											<% int i = 0; %>
											<ezf:row ezfHyo="A">
											    <% NFDL0100_ABMsg abMsg = bMsg.A.no(i++); %>
											    <tr height="25px" id="leftTBL_A_tr_${EZF_ROW_NUMBER}">
											        <td class=""><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/> </td>
											        <td class=""><ezf:inputText name="billToCustCd_A" ezfName="billToCustCd_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"billToCustCd_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="xxDplyTrxNumTxt_A" ezfName="xxDplyTrxNumTxt_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxDplyTrxNumTxt_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
											        <td class="">
											            <% if (ZYPCommonFunc.hasValue(abMsg.arCustRefNum_A) && ZYPCommonFunc.hasValue(abMsg.eipRptRqstPk_AI)) { %>
											                <ezf:anchor name="arCustRefNum_AL" ezfName="arCustRefNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPPrint','{EZF_ROW_NUMBER}" >
											                    <ezf:label name="arCustRefNum_A" ezfName="arCustRefNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
											                </ezf:anchor>
											            <% } else { %>
											                <ezf:inputText name="arCustRefNum_A" ezfName="arCustRefNum_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arCustRefNum_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/>
											            <% } %>
											        </td>
											        <td class="">
											            <% if (ZYPCommonFunc.hasValue(abMsg.grpInvNum_A) && ZYPCommonFunc.hasValue(abMsg.eipRptRqstPk_A)) { %>
											                <ezf:anchor name="grpInvNum_AL" ezfName="grpInvNum_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_EIPPrintBill','{EZF_ROW_NUMBER}" >
											                    <ezf:label name="grpInvNum_A" ezfName="grpInvNum_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" tabindex=\"-1\""/>
											                </ezf:anchor>
											            <% } else { %>
											                <ezf:inputText name="grpInvNum_A" ezfName="grpInvNum_A" value="VISA USA,INC" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"grpInvNum_A#{EZF_ROW_NUMBER}\" size=\"30\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/>
											            <% } %>
											        </td>
											        <td class=""><ezf:inputText name="xxScrItem130Txt_A" ezfName="xxScrItem130Txt_A" value="Net30" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem130Txt_A#{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="dealOrigGrsAmt_A" ezfName="dealOrigGrsAmt_A" value="1100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealOrigGrsAmt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent; text-align:right;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="dealRmngBalGrsAmt_A" ezfName="dealRmngBalGrsAmt_A" value="1100.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dealRmngBalGrsAmt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent; text-align:right;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="arTrxDt_A" ezfName="arTrxDt_A" value="85.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"arTrxDt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent; text-align:right;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="invDueDt_A" ezfName="invDueDt_A" value="85.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invDueDt_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent; text-align:right;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="daysPastDueAot_A" ezfName="daysPastDueAot_A" value="800.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"daysPastDueAot_A#{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none; background-color:transparent; text-align:right;\" tabindex=\"-1\""/></td>
											        <td class=""><ezf:inputText name="custIssPoNum_A" ezfName="custIssPoNum_A" value="02/05/2015" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"custIssPoNum_A#{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none; background-color:transparent;\" tabindex=\"-1\""/></td>
											    </tr>
											</ezf:row>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</center>

					<script type="text/javascript" defer>
					  S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0,  true, false);
					</script>


<%-- **** Task specific area ends here **** --%>
