<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171002141114 --%>
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
            <input type="hidden" name="pageID" value="NWAL2320Scrn00">
            <!-- Set Page Name -->
            <input type="hidden" name="pageName" value="Sales Order Bulk Upload Result Screen">

            <center>
                <table width="1130" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <%-- ##### MENU HEADER ##### --%>
                            <%-- ##### MENU TAB HEADER ##### --%>
                            <%-- include S21BusinessProcessTAB --%>
                            <jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
                            <ezf:skip>
                                <div class="pTab_HEAD">
                                    <ul>
                                        <table width="1130" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td width="1120">
                                                    <div>
                                                        <li title="Sales Order Bulk Upload Result Screen" class="pTab_ON"><a href="#">Sls Ord Upld</a></li>
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

                    <%-- ##### MENU TAB BODY ##### --%>
                    <%@ page import="business.servlet.NWAL2320.NWAL2320BMsg" %>
                    <% NWAL2320BMsg bMsg = (NWAL2320BMsg)databean.getEZDBMsg();%>
                    <script type="text/javascript">
                        function adjustmentPagination() {
                            document.getElementById('xxPageShowCurNum_CM').value = xxPageShowCurNum_CM_Save;
                            document.getElementById('xxPageShowTotNum_CM').value = '<%= bMsg.xxPageShowTotNum_CM.getValue() %>';
                            var disabled = <%= bMsg.xxPageShowTotNum_CM.getValueInt() <= 1 %>;
                            document.getElementById('btnJump').disabled = disabled;
                            document.getElementById('xxPageShowCurNum_CM').className = disabled ? 'pPro' : '';
                            document.getElementById('xxPageShowCurNum_CM').readOnly  = disabled;
                        }
                    </script>

                    <div class="pTab_BODY">
                        <%-- ##### PAGE HEADER START ##### --%>
                        <table border="0" cellpadding="0" cellspacing="0" height="110">
                            <col width="10">
                            <col width="500">
                            <col width="70">
                            <col width="500">
                            <col width="70">
                            <tr>
                                <td></td>
                                <td>
                                    <fieldset>
                                        <legend style="font-size:14px;">OM Sales Order Template Lists</legend>
                                        <table style="table-layout:fixed;height:75;">
                                            <col width="100" align="right">
                                            <col width="380" valign="top">
                                            <tr>
                                                <td class="stab">Template Type</td>
                                                <td>
                                                    <ezf:select name="ordUpldTmplTpCd" ezfName="ordUpldTmplTpCd" ezfCodeName="ordUpldTmplTpCd_L1" ezfDispName="ordUpldTmplTpDescTxt_L1" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="stab">Choose File</td>
                                                <td><ezf:inputFile name="xxFileData_UL" ezfName="xxFileData_UL" otherAttr=" size=\"35\" maxlength=\"9999\""/></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <ezf:inputButton name="Upload" value="Upload" htmlClass="pBtn6" />
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </td>
                                <td></td>
                                <td>
                                    <fieldset>
                                        <legend style="font-size:14px;">Excel Templates for Sales Order Upload</legend>
                                        <table style="table-layout:fixed;height:75;">
                                            <col valign="top">
                                            <tr>
                                                <td>
                                                    <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Download_NewOrd" >New Sales Order Upload Template</ezf:anchor>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Download_ExsOrd" >Existing Sales Order Upload Template</ezf:anchor>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Download_NewRma" >New RMA Order Upload Template</ezf:anchor>                                                   
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="Download_ExsRma" >Existing RMA Order Upload Template</ezf:anchor>
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </td>
                                <td></td>
                            </tr>
                        </table>
                        <%-- ##### PAGE HEADER END ##### --%>

                        <%-- ##### PAGE DETAIL START ##### --%>
                        <table border="0" cellpadding="0" cellspacing="0" width="1130" align="center">
                            <col valign="top">
                            <tr>
                                <td>
                                    <div class="pTab_HEAD">
                                        <ul>
                                            <li class="pTab_ON" id="tabNewOrd" title=""><a href="#" ezfname="">New Sales</a></li>
                                            <li class="pTab_OFF" id="tabExsOrd" title=""><a href="#" ezfname="">Exist Sales</a></li>
                                            <li class="pTab_OFF" id="tabNewRma" title=""><a href="#" ezfname="">New RMA</a></li>
                                            <li class="pTab_OFF" id="tabExsRma" title=""><a href="#" ezfname="">Exist RMA</a></li>
                                        </ul>
                                    </div>
                                    <c:choose>
                                        <%-- ##### NEW ORDER START ##### --%>
                                        <c:when test="${pageScope._ezddatabean.ordUpldTmplTpCd == '0'}">
                                            <script type="text/javascript">
                                                document.getElementById("tabNewOrd").className = "pTab_ON";
                                                document.getElementById("tabExsOrd").className = "pTab_OFF";
                                                document.getElementById("tabNewRma").className = "pTab_OFF";
                                                document.getElementById("tabExsRma").className = "pTab_OFF";
                                            </script>

                                            <div class="pTab_BODY_In" id="">
                                                <table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
                                                    <%-- ##### NEW ORDER DETAIL HEADER START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
                                                                <col align="left" width="480">
                                                                <col align="right" width="200">
                                                                <col align="left">
                                                                <col align="right" width="350">
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td>
                                                                        Rows Per Page
                                                                        <ezf:select name="xxPageCd_CM" ezfName="xxPageCd_CM" ezfCodeName="xxPageCd_LC" ezfDispName="xxPageCd_LT" otherEvent1=" onchange=\"sendServer('OnChange_RowsPerPage')\"" />
                                                                    </td>
                                                                    <td>&nbsp;</td>
                                                                    <td align="right">
                                                                        <ezf:skip>
                                                                            <table border="0" cellpadding="0" width="100%">
                                                                                <tr>
                                                                                    <td align="right">
                                                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                                                            <col width="54" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="16" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="26" align="center">
                                                                                            <col width="10">
                                                                                            <col>
                                                                                            <col width="20">
                                                                                            <col>
                                                                                            <col width="1">
                                                                                            <col>
                                                                                            <tr>
                                                                                                <td class="stab">Showing</td>
                                                                                                <td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
                                                                                                <td class="stab">/</td>
                                                                                                <td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
                                                                                                <td class="stab">page</td>
                                                                                                <td>&nbsp;</td>
                                                                                                <td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </ezf:skip>
                                                                        <table width="100%">
                                                                            <tr align="right">
                                                                                <td>
                                                                                    <script type="text/javascript">
                                                                                        var xxPageShowCurNum_CM_Save = '<%= bMsg.xxPageShowCurNum_CM.getValue() %>';
                                                                                    </script>
                                                                                    <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                                                                        <jsp:param name="beanId" value='<%= request.getParameter( "beanId" ) %>' />
                                                                                        <jsp:param name="TableNm" value="A" />
                                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_CM" />
                                                                                        <jsp:param name="ShowingTo" value="xxPageShowToNum_CM" />
                                                                                        <jsp:param name="ShowingOf" value="xxPageShowOfNum_CM" />
                                                                                        <jsp:param name="ShowingCurrent" value="xxPageShowCurNum_CM" />
                                                                                        <jsp:param name="ShowingTotal" value="xxPageShowTotNum_CM" />
                                                                                        <jsp:param name="ViewMode" value="LEFT_NONE" />
                                                                                    </jsp:include>
                                                                                    <script type="text/javascript">adjustmentPagination();</script>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### NEW ORDER DETAIL HEADER END ##### --%>

                                                    <%-- ##### NEW ORDER DETAIL LIST START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
                                                                <tr>
                                                                    <td valign="top">
                                                                        <%-- TABLE(TOP) --%>
                                                                        <div id="newOrdHdrTbl" style="overflow-y:none; height:; overflow-x:hidden; width:1090;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('newOrdDtlTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" height="42" style="table-layout: fixed">
                                                                                <col align="center" width="70">		    <!-- Group Number -->
                                                                                <col align="center" width="100">		<!-- Customer PO -->
                                                                                <col align="center" width="100">		<!-- PO Agreement No -->
                                                                                <col align="center" width="100">		<!-- Negotiated Deal -->
                                                                                <col align="center" width="100">	    <!-- Order Category -->
                                                                                <col align="center" width="100">	    <!-- Reason -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">	    <!-- Sold To Location -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Sub Warehouse -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <tr>
                                                                                    <td class="pClothBs">Group Number</td>
                                                                                    <td class="pClothBs">Customer PO</td>
                                                                                    <td class="pClothBs">PO Agreement No</td>
                                                                                    <td class="pClothBs">Negotiated Deal</td>
                                                                                    <td class="pClothBs">Order Category</td>
                                                                                    <td class="pClothBs">Reason</td>
                                                                                    <td class="pClothBs">Item</td>
                                                                                    <td class="pClothBs">Qty</td>
                                                                                    <td class="pClothBs">Selling Price</td>
                                                                                    <td class="pClothBs">Line Configuration#</td>
                                                                                    <td class="pClothBs">Line Category</td>
																					<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <!-- <td class="pClothBs">Bill To Location</td> -->
                                                                                    <!-- <td class="pClothBs">Ship To Location</td> -->
                                                                                    <!-- <td class="pClothBs">Sold To Location</td> -->
                                                                                    <td class="pClothBs">Bill To Code</td>
                                                                                    <td class="pClothBs">Ship To Code</td>
                                                                                    <td class="pClothBs">Sold To Code</td>
																					<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <td class="pClothBs">Sales Rep</td>
                                                                                    <td class="pClothBs">Warehouse</td>
                                                                                    <td class="pClothBs">Sub Warehouse</td>
                                                                                    <td class="pClothBs">Shipping Instructions</td>
                                                                                    <td class="pClothBs">Status</td>
                                                                                    <td class="pClothBs">Validation Message</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <%-- TABLE(BTM) --%>
                                                                        <div id="newOrdDtlTbl" style="overflow-y:scroll; height:302; overflow-x:scroll; width:1107;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('newOrdHdrTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
                                                                                <col align="center" width="70">		    <!-- Group Number -->
                                                                                <col align="center" width="100">		<!-- Customer PO -->
                                                                                <col align="center" width="100">		<!-- PO Agreement No -->
                                                                                <col align="center" width="100">		<!-- Negotiated Deal -->
                                                                                <col align="center" width="100">	    <!-- Order Category -->
                                                                                <col align="center" width="100">	    <!-- Reason -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">	    <!-- Sold To Location -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Sub Warehouse -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <ezf:row ezfHyo="A">
                                                                                    <tr height="28" id="id_row${EZF_ROW_NUMBER}">
                                                                                        <td><ezf:inputText name="procGrpNum_CO" ezfName="procGrpNum_CO" value="ORD0001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="custIssPoNum_CO" ezfName="custIssPoNum_CO" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"35\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="aquNum_CO" ezfName="aquNum_CO" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"35\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="negoDealAmt_CO" ezfName="negoDealAmt_CO" value="50000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdCatgDescTxt_CO" ezfName="dsOrdCatgDescTxt_CO" value="PURCHASE, CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdTpDescTxt_CO" ezfName="dsOrdTpDescTxt_CO" value="PPS-SUPPLY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="mdseCd_CO" ezfName="mdseCd_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordQty_CO" ezfName="ordQty_CO" value="100" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="entDealNetUnitPrcAmt_CO" ezfName="entDealNetUnitPrcAmt_CO" value="30000" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdPosnNum_CO" ezfName="dsOrdPosnNum_CO" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdLineCatgDescTxt_CO" ezfName="dsOrdLineCatgDescTxt_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="billToCustLocCd_CO" ezfName="billToCustLocCd_CO" value="1234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="shipToCustLocCd_CO" ezfName="shipToCustLocCd_CO" value="1234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="soldToCustLocCd_CO" ezfName="soldToCustLocCd_CO" value="1234567" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="tocNm_CO" ezfName="tocNm_CO" value="1234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtlWhNm_CO" ezfName="rtlWhNm_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtlSwhNm_CO" ezfName="rtlSwhNm_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxShpgOrdCmntTxt_CO" ezfName="xxShpgOrdCmntTxt_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordUpldVldStsDescTxt_CO" ezfName="ordUpldVldStsDescTxt_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxDsMultMsgDplyTxt_CO" ezfName="xxDsMultMsgDplyTxt_CO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"2000\" ezftoupper=\"\""/></td>
                                                                                    </tr>
                                                                                </ezf:row>
                                                                            </table>
                                                                        </div>

                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### NEW ORDER DETAIL LIST END ##### --%>
                                                </table>
                                            </div>
                                        </c:when>
                                        <%-- ##### NEW ORDER END ##### --%>

                                        <%-- ##### EXISTING ORDER START ##### --%>
                                        <c:when test="${pageScope._ezddatabean.ordUpldTmplTpCd == '1'}">
                                            <script type="text/javascript">
                                                document.getElementById("tabNewOrd").className = "pTab_OFF";
                                                document.getElementById("tabExsOrd").className = "pTab_ON";
                                                document.getElementById("tabNewRma").className = "pTab_OFF";
                                                document.getElementById("tabExsRma").className = "pTab_OFF";
                                            </script>

                                            <div class="pTab_BODY_In" id="">
                                                <table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
                                                    <%-- ##### EXISTING ORDER DETAIL HEADER START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
                                                                <col align="left" width="480">
                                                                <col align="right" width="200">
                                                                <col align="left">
                                                                <col align="right" width="350">
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td>
                                                                        Rows Per Page
                                                                        <ezf:select name="xxPageCd_CM" ezfName="xxPageCd_CM" ezfCodeName="xxPageCd_LC" ezfDispName="xxPageCd_LT" otherEvent1=" onchange=\"sendServer('OnChange_RowsPerPage')\"" />
                                                                    </td>
                                                                    <td>&nbsp;</td>
                                                                    <td align="right">
                                                                        <ezf:skip>
                                                                            <table border="0" cellpadding="0" width="100%">
                                                                                <tr>
                                                                                    <td align="right">
                                                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                                                            <col width="54" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="16" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="26" align="center">
                                                                                            <col width="10">
                                                                                            <col>
                                                                                            <col width="20">
                                                                                            <col>
                                                                                            <col width="1">
                                                                                            <col>
                                                                                            <tr>
                                                                                                <td class="stab">Showing</td>
                                                                                                <td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
                                                                                                <td class="stab">/</td>
                                                                                                <td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
                                                                                                <td class="stab">page</td>
                                                                                                <td>&nbsp;</td>
                                                                                                <td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'B')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'B')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'B')"></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </ezf:skip>
                                                                        <table width="100%">
                                                                            <tr align="right">
                                                                                <td>
                                                                                    <script type="text/javascript">
                                                                                        var xxPageShowCurNum_CM_Save = '<%= bMsg.xxPageShowCurNum_CM.getValue() %>';
                                                                                    </script>
                                                                                    <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                                                                        <jsp:param name="beanId" value='<%= request.getParameter( "beanId" ) %>' />
                                                                                        <jsp:param name="TableNm" value="B" />
                                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_CM" />
                                                                                        <jsp:param name="ShowingTo" value="xxPageShowToNum_CM" />
                                                                                        <jsp:param name="ShowingOf" value="xxPageShowOfNum_CM" />
                                                                                        <jsp:param name="ShowingCurrent" value="xxPageShowCurNum_CM" />
                                                                                        <jsp:param name="ShowingTotal" value="xxPageShowTotNum_CM" />
                                                                                        <jsp:param name="ViewMode" value="LEFT_NONE" />
                                                                                    </jsp:include>
                                                                                    <script type="text/javascript">adjustmentPagination();</script>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### EXISTING ORDER DETAIL HEADER END ##### --%>

                                                    <%-- ##### EXISTING ORDER DETAIL LIST START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
                                                                <tr>
                                                                    <td valign="top">
                                                                        <%-- TABLE(TOP) --%>
                                                                        <div id="exsOrdHdrTbl" style="overflow-y:none; height:; overflow-x:hidden; width:1090;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('exsOrdDtlTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" height="42" style="table-layout: fixed">
                                                                                <col align="center" width="100">		<!-- Order Number -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Sub Warehouse -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <tr>
                                                                                    <td class="pClothBs">Order Number</td>
                                                                                    <td class="pClothBs">Item</td>
                                                                                    <td class="pClothBs">Qty</td>
                                                                                    <td class="pClothBs">Selling Price</td>
                                                                                    <td class="pClothBs">Line Configuration#</td>
                                                                                    <td class="pClothBs">Line Category</td>
																					<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <!-- <td class="pClothBs">Bill To Location</td> -->
                                                                                    <!-- <td class="pClothBs">Ship To Location</td> -->
                                                                                    <td class="pClothBs">Bill To Code</td>
                                                                                    <td class="pClothBs">Ship To Code</td>
																					<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <td class="pClothBs">Sales Rep</td>
                                                                                    <td class="pClothBs">Warehouse</td>
                                                                                    <td class="pClothBs">Sub Warehouse</td>
                                                                                    <td class="pClothBs">Shipping Instructions</td>
                                                                                    <td class="pClothBs">Status</td>
                                                                                    <td class="pClothBs">Validation Message</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <%-- TABLE(BTM) --%>
                                                                        <div id="exsOrdDtlTbl" style="overflow-y:scroll; height:302; overflow-x:scroll; width:1107;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('exsOrdHdrTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
                                                                                <col align="center" width="100">		<!-- Order Number -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Sub Warehouse -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <ezf:row ezfHyo="B">
                                                                                    <tr height="28" id="id_leftB_row{EZF_ROW_NUMBER}">
                                                                                        <td><ezf:inputText name="cpoOrdNum_EO" ezfName="cpoOrdNum_EO" value="ORD0001" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="mdseCd_EO" ezfName="mdseCd_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordQty_EO" ezfName="ordQty_EO" value="100" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="entDealNetUnitPrcAmt_EO" ezfName="entDealNetUnitPrcAmt_EO" value="30000" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdPosnNum_EO" ezfName="dsOrdPosnNum_EO" value="1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdLineCatgDescTxt_EO" ezfName="dsOrdLineCatgDescTxt_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="billToCustLocCd_EO" ezfName="billToCustLocCd_EO" value="1234567" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="shipToCustLocCd_EO" ezfName="shipToCustLocCd_EO" value="1234567" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="tocNm_EO" ezfName="tocNm_EO" value="1234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtlWhNm_EO" ezfName="rtlWhNm_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtlSwhNm_EO" ezfName="rtlSwhNm_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxShpgOrdCmntTxt_EO" ezfName="xxShpgOrdCmntTxt_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordUpldVldStsDescTxt_EO" ezfName="ordUpldVldStsDescTxt_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxDsMultMsgDplyTxt_EO" ezfName="xxDsMultMsgDplyTxt_EO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"2000\" ezftoupper=\"\""/></td>
                                                                                    </tr>
                                                                                </ezf:row>
                                                                            </table>
                                                                        </div>

                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### EXISTING ORDER DETAIL LIST END ##### --%>
                                                </table>
                                            </div>
                                        </c:when>
                                        <%-- ##### EXISTING ORDER END ##### --%>

                                        <%-- ##### NEW RMA START ##### --%>
                                        <c:when test="${pageScope._ezddatabean.ordUpldTmplTpCd == '2'}">
                                            <script type="text/javascript">
                                                document.getElementById("tabNewOrd").className = "pTab_OFF";
                                                document.getElementById("tabExsOrd").className = "pTab_OFF";
                                                document.getElementById("tabNewRma").className = "pTab_ON";
                                                document.getElementById("tabExsRma").className = "pTab_OFF";
                                            </script>

                                            <div class="pTab_BODY_In" id="">
                                                <table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
                                                    <%-- ##### NEW RMA DETAIL HEADER START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
                                                                <col align="left" width="480">
                                                                <col align="right" width="200">
                                                                <col align="left">
                                                                <col align="right" width="350">
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td>
                                                                        Rows Per Page
                                                                        <ezf:select name="xxPageCd_CM" ezfName="xxPageCd_CM" ezfCodeName="xxPageCd_LC" ezfDispName="xxPageCd_LT" otherEvent1=" onchange=\"sendServer('OnChange_RowsPerPage')\"" />
                                                                    </td>
                                                                    <td>&nbsp;</td>
                                                                    <td align="right">
                                                                        <ezf:skip>
                                                                            <table border="0" cellpadding="0" width="100%">
                                                                                <tr>
                                                                                    <td align="right">
                                                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                                                            <col width="54" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="16" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="26" align="center">
                                                                                            <col width="10">
                                                                                            <col>
                                                                                            <col width="20">
                                                                                            <col>
                                                                                            <col width="1">
                                                                                            <col>
                                                                                            <tr>
                                                                                                <td class="stab">Showing</td>
                                                                                                <td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
                                                                                                <td class="stab">/</td>
                                                                                                <td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
                                                                                                <td class="stab">page</td>
                                                                                                <td>&nbsp;</td>
                                                                                                <td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'C')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'C')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'C')"></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </ezf:skip>
                                                                        <table width="100%">
                                                                            <tr align="right">
                                                                                <td>
                                                                                    <script type="text/javascript">
                                                                                        var xxPageShowCurNum_CM_Save = '<%= bMsg.xxPageShowCurNum_CM.getValue() %>';
                                                                                    </script>
                                                                                    <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                                                                        <jsp:param name="beanId" value='<%= request.getParameter( "beanId" ) %>' />
                                                                                        <jsp:param name="TableNm" value="C" />
                                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_CM" />
                                                                                        <jsp:param name="ShowingTo" value="xxPageShowToNum_CM" />
                                                                                        <jsp:param name="ShowingOf" value="xxPageShowOfNum_CM" />
                                                                                        <jsp:param name="ShowingCurrent" value="xxPageShowCurNum_CM" />
                                                                                        <jsp:param name="ShowingTotal" value="xxPageShowTotNum_CM" />
                                                                                        <jsp:param name="ViewMode" value="LEFT_NONE" />
                                                                                    </jsp:include>
                                                                                    <script type="text/javascript">adjustmentPagination();</script>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### NEW RMA DETAIL HEADER END ##### --%>

                                                    <%-- ##### NEW RMA DETAIL LIST START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
                                                                <tr>
                                                                    <td valign="top">
                                                                        <%-- TABLE(TOP) --%>
                                                                        <div id="newRmaHdrTbl" style="overflow-y:none; height:; overflow-x:hidden; width:1090;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('newRmaDtlTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" height="42" style="table-layout: fixed">
                                                                                <col align="center" width="70">		    <!-- Group Number -->
                                                                                <col align="center" width="100">		<!-- Customer PO -->
                                                                                <col align="center" width="100">		<!-- PO Agreement No -->
                                                                                <col align="center" width="100">		<!-- Negotiated Deal -->
                                                                                <col align="center" width="100">	    <!-- Order Category -->
                                                                                <col align="center" width="100">	    <!-- Reason -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">	    <!-- Sold To Location -->
                                                                                <col align="center" width="100">		<!-- Serial Number -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Return Reason -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <tr>
                                                                                    <td class="pClothBs">Group Number</td>
                                                                                    <td class="pClothBs">Customer PO</td>
                                                                                    <td class="pClothBs">PO Agreement No</td>
                                                                                    <td class="pClothBs">Negotiated Deal</td>
                                                                                    <td class="pClothBs">Order Category</td>
                                                                                    <td class="pClothBs">Reason</td>
                                                                                    <td class="pClothBs">Item</td>
                                                                                    <td class="pClothBs">Qty</td>
                                                                                    <td class="pClothBs">Selling Price</td>
                                                                                    <td class="pClothBs">Line Configuration#</td>
                                                                                    <td class="pClothBs">Line Category</td>
																					<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <!-- <td class="pClothBs">Bill To Location</td> -->
                                                                                    <!-- <td class="pClothBs">Ship To Location</td> -->
                                                                                    <!-- <td class="pClothBs">Sold To Location</td> -->
                                                                                    <td class="pClothBs">Bill To Code</td>
                                                                                    <td class="pClothBs">Ship To Code</td>
                                                                                    <td class="pClothBs">Sold To Code</td>
																					<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <td class="pClothBs">Serial Number</td>
                                                                                    <td class="pClothBs">Sales Rep</td>
                                                                                    <td class="pClothBs">Warehouse</td>
                                                                                    <td class="pClothBs">Return Reason</td>
                                                                                    <td class="pClothBs">Shipping Instructions</td>
                                                                                    <td class="pClothBs">Status</td>
                                                                                    <td class="pClothBs">Validation Message</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <%-- TABLE(BTM) --%>
                                                                        <div id="newRmaDtlTbl" style="overflow-y:scroll; height:302; overflow-x:scroll; width:1107;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('newRmaHdrTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
                                                                                <col align="center" width="70">		    <!-- Group Number -->
                                                                                <col align="center" width="100">		<!-- Customer PO -->
                                                                                <col align="center" width="100">		<!-- PO Agreement No -->
                                                                                <col align="center" width="100">		<!-- Negotiated Deal -->
                                                                                <col align="center" width="100">	    <!-- Order Category -->
                                                                                <col align="center" width="100">	    <!-- Reason -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">	    <!-- Sold To Location -->
                                                                                <col align="center" width="100">		<!-- Serial Number -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Return Reason -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <ezf:row ezfHyo="C">
                                                                                    <tr height="28" id="id_leftC_row{EZF_ROW_NUMBER}">
                                                                                        <td><ezf:inputText name="procGrpNum_NR" ezfName="procGrpNum_NR" value="ORD0001" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"7\" maxlength=\"7\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="custIssPoNum_NR" ezfName="custIssPoNum_NR" value="1234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"35\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="aquNum_NR" ezfName="aquNum_NR" value="1234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"35\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="negoDealAmt_NR" ezfName="negoDealAmt_NR" value="50000" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdCatgDescTxt_NR" ezfName="dsOrdCatgDescTxt_NR" value="PURCHASE, CSA" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdTpDescTxt_NR" ezfName="dsOrdTpDescTxt_NR" value="PPS-SUPPLY" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="mdseCd_NR" ezfName="mdseCd_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordQty_NR" ezfName="ordQty_NR" value="100" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="entDealNetUnitPrcAmt_NR" ezfName="entDealNetUnitPrcAmt_NR" value="30000" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdPosnNum_NR" ezfName="dsOrdPosnNum_NR" value="1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdLineCatgDescTxt_NR" ezfName="dsOrdLineCatgDescTxt_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="billToCustLocCd_NR" ezfName="billToCustLocCd_NR" value="1234567" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="shipToCustLocCd_NR" ezfName="shipToCustLocCd_NR" value="1234567" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="soldToCustLocCd_NR" ezfName="soldToCustLocCd_NR" value="1234567" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="serNum_NR" ezfName="serNum_NR" value="1234567" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="tocNm_NR" ezfName="tocNm_NR" value="1234567890" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtlWhNm_NR" ezfName="rtlWhNm_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtrnRsnDescTxt_NR" ezfName="rtrnRsnDescTxt_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxShpgOrdCmntTxt_NR" ezfName="xxShpgOrdCmntTxt_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordUpldVldStsDescTxt_NR" ezfName="ordUpldVldStsDescTxt_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxDsMultMsgDplyTxt_NR" ezfName="xxDsMultMsgDplyTxt_NR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"2000\" ezftoupper=\"\""/></td>
                                                                                    </tr>
                                                                                </ezf:row>
                                                                            </table>
                                                                        </div>

                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### NEW RMA DETAIL LIST END ##### --%>
                                                </table>
                                            </div>
                                        </c:when>
                                        <%-- ##### NEW RMA END ##### --%>

                                        <%-- ##### EXISTING RMA START ##### --%>
                                        <c:when test="${pageScope._ezddatabean.ordUpldTmplTpCd == '3'}">
                                            <script type="text/javascript">
                                                document.getElementById("tabNewOrd").className = "pTab_OFF";
                                                document.getElementById("tabExsOrd").className = "pTab_OFF";
                                                document.getElementById("tabNewRma").className = "pTab_OFF";
                                                document.getElementById("tabExsRma").className = "pTab_ON";
                                            </script>

                                            <div class="pTab_BODY_In" id="">
                                                <table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
                                                    <%-- ##### EXISTING RMA DETAIL HEADER START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="table-layout: fixed;">
                                                                <col align="left" width="480">
                                                                <col align="right" width="200">
                                                                <col align="left">
                                                                <col align="right" width="350">
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td>
                                                                        Rows Per Page
                                                                        <ezf:select name="xxPageCd_CM" ezfName="xxPageCd_CM" ezfCodeName="xxPageCd_LC" ezfDispName="xxPageCd_LT" otherEvent1=" onchange=\"sendServer('OnChange_RowsPerPage')\"" />
                                                                    </td>
                                                                    <td>&nbsp;</td>
                                                                    <td align="right">
                                                                        <ezf:skip>
                                                                            <table border="0" cellpadding="0" width="100%">
                                                                                <tr>
                                                                                    <td align="right">
                                                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                                                            <col width="54" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="16" align="center">
                                                                                            <col width="40" align="center">
                                                                                            <col width="26" align="center">
                                                                                            <col width="10">
                                                                                            <col>
                                                                                            <col width="20">
                                                                                            <col>
                                                                                            <col width="1">
                                                                                            <col>
                                                                                            <tr>
                                                                                                <td class="stab">Showing</td>
                                                                                                <td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
                                                                                                <td class="stab">/</td>
                                                                                                <td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
                                                                                                <td class="stab">page</td>
                                                                                                <td>&nbsp;</td>
                                                                                                <td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'D')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'D')"></td>
                                                                                                <td></td>
                                                                                                <td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'D')"></td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </ezf:skip>
                                                                        <table width="100%">
                                                                            <tr align="right">
                                                                                <td>
                                                                                    <script type="text/javascript">
                                                                                        var xxPageShowCurNum_CM_Save = '<%= bMsg.xxPageShowCurNum_CM.getValue() %>';
                                                                                    </script>
                                                                                    <jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
                                                                                        <jsp:param name="beanId" value='<%= request.getParameter( "beanId" ) %>' />
                                                                                        <jsp:param name="TableNm" value="D" />
                                                                                        <jsp:param name="ShowingFrom" value="xxPageShowFromNum_CM" />
                                                                                        <jsp:param name="ShowingTo" value="xxPageShowToNum_CM" />
                                                                                        <jsp:param name="ShowingOf" value="xxPageShowOfNum_CM" />
                                                                                        <jsp:param name="ShowingCurrent" value="xxPageShowCurNum_CM" />
                                                                                        <jsp:param name="ShowingTotal" value="xxPageShowTotNum_CM" />
                                                                                        <jsp:param name="ViewMode" value="LEFT_NONE" />
                                                                                    </jsp:include>
                                                                                    <script type="text/javascript">adjustmentPagination();</script>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### EXISTING RMA DETAIL HEADER END ##### --%>

                                                    <%-- ##### EXISTING RMA DETAIL LIST START ##### --%>
                                                    <tr>
                                                        <td>
                                                            <table border="0" cellpadding="0" cellspacing="0" style="margin: 5px;">
                                                                <tr>
                                                                    <td valign="top">
                                                                        <%-- TABLE(TOP) --%>
                                                                        <div id="exsRmaHdrTbl" style="overflow-y:none; height:; overflow-x:hidden; width:1090;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('exsRmaDtlTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" height="42" style="table-layout: fixed">
                                                                                <col align="center" width="100">		<!-- RMA Number -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">		<!-- Serial Number -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Return Reason -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <tr>
                                                                                    <td class="pClothBs">RMA Number</td>
                                                                                    <td class="pClothBs">Item</td>
                                                                                    <td class="pClothBs">Qty</td>
                                                                                    <td class="pClothBs">Selling Price</td>
                                                                                    <td class="pClothBs">Line Configuration#</td>
                                                                                    <td class="pClothBs">Line Category</td>
																					<!-- Mod Start 2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <!-- <td class="pClothBs">Bill To Location</td> -->
                                                                                    <!-- <td class="pClothBs">Ship To Location</td> -->
                                                                                    <td class="pClothBs">Bill To Code</td>
                                                                                    <td class="pClothBs">Ship To Code</td>
																					<!-- Mod End   2017/10/02 H.Sugawara QC#19922 -->
                                                                                    <td class="pClothBs">Serial Number</td>
                                                                                    <td class="pClothBs">Sales Rep</td>
                                                                                    <td class="pClothBs">Warehouse</td>
                                                                                    <td class="pClothBs">Return Reason</td>
                                                                                    <td class="pClothBs">Shipping Instructions</td>
                                                                                    <td class="pClothBs">Status</td>
                                                                                    <td class="pClothBs">Validation Message</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <%-- TABLE(BTM) --%>
                                                                        <div id="exsRmaDtlTbl" style="overflow-y:scroll; height:302; overflow-x:scroll; width:1107;" onScroll="onScroll = synchroScrollLeft(this.id, new Array('exsRmaHdrTbl'));">
                                                                            <table border="1" cellpadding="1" cellspacing="0" id="D" style="table-layout: fixed">
                                                                                <col align="center" width="100">		<!-- RMA Number -->
                                                                                <col align="center" width="100">		<!-- Item -->
                                                                                <col align="center" width="80">		    <!-- Qty -->
                                                                                <col align="center" width="100">		<!-- Selling Price -->
                                                                                <col align="center" width="90">		    <!-- Line Configuration# -->
                                                                                <col align="center" width="100">		<!-- Line Category -->
                                                                                <col align="center" width="100">	    <!-- Bill To Location -->
                                                                                <col align="center" width="100">	    <!-- Ship To Location -->
                                                                                <col align="center" width="100">		<!-- Serial Number -->
                                                                                <col align="center" width="100">		<!-- Sales Rep -->
                                                                                <col align="center" width="100">		<!-- Warehouse -->
                                                                                <col align="center" width="100">		<!-- Return Reason -->
                                                                                <col align="center" width="100">		<!-- Shipping Instructions -->
                                                                                <col align="center" width="80">		    <!-- Status -->
                                                                                <col align="center" width="200">		<!-- Validation Message -->
                                                                                <ezf:row ezfHyo="D">
                                                                                    <tr height="28" id="id_leftD_row{EZF_ROW_NUMBER}">
                                                                                        <td><ezf:inputText name="cpoOrdNum_ER" ezfName="cpoOrdNum_ER" value="ORD0001" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="mdseCd_ER" ezfName="mdseCd_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"16\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordQty_ER" ezfName="ordQty_ER" value="100" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="entDealNetUnitPrcAmt_ER" ezfName="entDealNetUnitPrcAmt_ER" value="30000" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"19\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdPosnNum_ER" ezfName="dsOrdPosnNum_ER" value="1" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="dsOrdLineCatgDescTxt_ER" ezfName="dsOrdLineCatgDescTxt_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="billToCustLocCd_ER" ezfName="billToCustLocCd_ER" value="1234567" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="shipToCustLocCd_ER" ezfName="shipToCustLocCd_ER" value="1234567" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="serNum_ER" ezfName="serNum_ER" value="1234567" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="tocNm_ER" ezfName="tocNm_ER" value="1234567890" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtlWhNm_ER" ezfName="rtlWhNm_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="rtrnRsnDescTxt_ER" ezfName="rtrnRsnDescTxt_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxShpgOrdCmntTxt_ER" ezfName="xxShpgOrdCmntTxt_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="ordUpldVldStsDescTxt_ER" ezfName="ordUpldVldStsDescTxt_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/></td>
                                                                                        <td><ezf:inputText name="xxDsMultMsgDplyTxt_ER" ezfName="xxDsMultMsgDplyTxt_ER" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"2000\" ezftoupper=\"\""/></td>
                                                                                    </tr>
                                                                                </ezf:row>
                                                                            </table>
                                                                        </div>

                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                    <%-- ##### EXISTING RMA DETAIL LIST END ##### --%>
                                                </table>
                                            </div>
                                        </c:when>
                                        <%-- ##### EXISTING RMA END ##### --%>


                                    </c:choose>
                                </td>
                            </tr>
                            <%-- ##### DETAIL FOOTER START ##### --%>
                            <tr>
                                <table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
                                    <col width="80" align="center">
                                    <col width="80" align="center">
                                    <col width="80" align="center">
                                    <col width="80" align="center">
                                    <col width="700" align="center">
                                    <tr>
                                        <td>
                                            <ezf:inputButton name="Validate" value="Validate" htmlClass="pBtn7" />
                                        </td>
                                        <td>
                                            <ezf:inputButton name="CancelUpload" value="Cancel Upload" htmlClass="pBtn7" />
                                        </td>
                                        <td>
                                            <ezf:inputButton name="UploadLines" value="Upload Lines" htmlClass="pBtn7" />
                                        </td>
                                        <td>
                                            <ezf:inputButton name="Download" value="Download" htmlClass="pBtn7" />
                                        </td>
                                        <td></td>
                                    </tr>
                                </table>
                            </tr>
                            <%-- ##### DETAIL FOOTER END ##### --%>
                        </table>
                        <%-- ##### PAGE DETAIL END ##### --%>
                    </div>

                    <script type="text/javascript">
                        function adjustmentPagination() {
	                        document.getElementById('xxPageShowCurNum_CM').value = xxPageShowCurNum_CM_Save;
	                        document.getElementById('xxPageShowTotNum_CM').value = '<%= bMsg.xxPageShowTotNum_CM.getValue() %>';
	                        var disabled = <%= bMsg.xxPageShowTotNum_CM.getValueInt() == 0 %>;
	                        document.getElementById('btnJump').disabled = disabled;
	                        document.getElementById('xxPageShowCurNum_CM').className = disabled ? 'pPro' : '';
	                        document.getElementById('xxPageShowCurNum_CM').readOnly  = disabled;
                        }
                    </script>
                    
<%-- **** Task specific area ends here **** --%>
