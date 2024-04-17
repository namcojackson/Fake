<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230628130514 --%>
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
			<input type="hidden" name="pageID" value="NPBL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Inventory Request List">
<center>
    <table width="100%" cellSpacing="0" cellPadding="0"  border="0">
        <tbody>
            <tr>
                <td>
                    <%-- ********************** --%>
                    <%-- *** Upper Tab Area *** --%>
                    <%-- ********************** --%>
                    <jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
                    <ezf:skip>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image: url(./img/tab/uppertabbackground.jpg);">
                            <tr>
                                <td width="1070px" height="28px" valign="bottom">
                                    <table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
                                        <tr title="Inventory Request List">
                                            <td width="107px" align="center" class="same">Invty Req List</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </ezf:skip>
                    <div class="pTab_BODY">
                        <!-- ################################################## Search Criteria - [START] ################################################## -->
                        <table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
                            <col width="152">
                            <col width="345">
                            <col width="110">
                            <col width="300">
                            <col width="">
                            <tr>
                                <td class="stab">Saved Search Options</td>
                                <td>
                                    <ezf:select name="srchOptPk_SL" ezfName="srchOptPk_SL" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('OnChangeSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\" tabindex=\"1\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\" tabindex=\"2\""/></td>
                                <td>
                                    <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" otherAttr=" tabindex=\"3\""/>
                                    <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" otherAttr=" tabindex=\"4\""/>
                                </td>
                            </tr>
                        </table>

                        <hr>

                        <!-- ################################################## Header - [START] ################################################## -->
						<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
							<col width=""  align="left">
							<tr valign="top">
								<td>
									<table border="0" cellpadding="0" cellspacing="0" >
										<col width="105">
										<col width="145">
										<col width="60">
										<col width="18">
										<col width="120">
										<col width="225">
										<col width="18">
										<col width="120">
										<col width="">
										<!-- 1 -->
										<tr height="20">
											<td class="stab">Request Number</td>
											<td colspan="2"><ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="12345678" otherAttr=" size=\"28\" maxlength=\"30\" tabindex=\"5\" ezftoupper=\"\""/></td>
											<td>&nbsp;</td>
											<td class="stab">Document Source Type</td>
											<td>
												<ezf:select name="prchReqSrcTpCd_SL" ezfName="prchReqSrcTpCd_SL" ezfBlank="1" ezfCodeName="prchReqSrcTpCd_PD" ezfDispName="prchReqSrcTpDescTxt_PD" otherAttr=" style=\"width:223\" tabindex=\"13\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_SrcWH" otherAttr=" tabindex=\"22\"">Source WH (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="srcRtlWhCd" ezfName="srcRtlWhCd" value="1Z1" otherAttr=" size=\"10\" maxlength=\"45\" tabindex=\"23\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlWhNm_SW" ezfName="rtlWhNm_SW" value="NORCROSS%" otherAttr=" size=\"28\" maxlength=\"45\" tabindex=\"24\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 2 -->
										<tr height="20">
											<td class="stab">Request Type</td>
											<td colspan="2">
												<ezf:select name="prchReqTpCd_SL" ezfName="prchReqTpCd_SL" ezfBlank="1" ezfCodeName="prchReqTpCd_PD" ezfDispName="prchReqTpDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"6\""/>
											</td>
											<td></td>
											<td class="stab">Source Doc#</td>
											<td>
												<ezf:inputText name="trxRefNum" ezfName="trxRefNum" value="12345678" otherAttr=" size=\"31\" maxlength=\"30\" tabindex=\"14\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_SrcSWH" otherAttr=" tabindex=\"25\"">Source SWH (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="srcRtlSwhCd" ezfName="srcRtlSwhCd" otherAttr=" size=\"10\" maxlength=\"45\" tabindex=\"26\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlSwhNm_SS" ezfName="rtlSwhNm_SS" otherAttr=" size=\"28\" maxlength=\"45\" tabindex=\"27\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 3 -->
										<tr height="20">
											<td class="stab">Header Status</td>
											<td colspan="2">
												<ezf:select name="prchReqStsCd_SL" ezfName="prchReqStsCd_SL" ezfBlank="1" ezfCodeName="prchReqStsCd_PD" ezfDispName="prchReqStsDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"7\""/>
											</td>
											<td></td>
											<td class="stab">Plan Name(*)</td>
											<td colspan="2">
												<ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="Min Max Plan 1" otherAttr=" size=\"31\" maxlength=\"70\" ezftoupper=\"\" tabindex=\"15\""/>
											</td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_DestWH" otherAttr=" tabindex=\"28\"">Destination WH (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="destRtlWhCd" ezfName="destRtlWhCd" value="DS" otherAttr=" size=\"10\" maxlength=\"45\" tabindex=\"29\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlWhNm_DW" ezfName="rtlWhNm_DW" value="Drop Ship" otherAttr=" size=\"28\" maxlength=\"45\" tabindex=\"30\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 4 -->
										<tr height="20">
											<td class="stab">Approval Status</td>
											<td colspan="2">
												<ezf:select name="prchReqApvlStsCd_SL" ezfName="prchReqApvlStsCd_SL" ezfBlank="1" ezfCodeName="prchReqApvlStsCd_PD" ezfDispName="prchReqApvlStsDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"8\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Requester" otherAttr=" tabindex=\"16\"">Requester (*)</ezf:anchor></td>
											<td colspan="2">
												<ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="Caruros" otherAttr=" size=\"31\" maxlength=\"45\" tabindex=\"16\""/>
											</td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_DestSWH" otherAttr=" tabindex=\"31\"">Destination SWH (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="destRtlSwhCd" ezfName="destRtlSwhCd" otherAttr=" size=\"10\" maxlength=\"45\" tabindex=\"32\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlSwhNm_DS" ezfName="rtlSwhNm_DS" otherAttr=" size=\"28\" maxlength=\"45\" tabindex=\"33\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 5 -->
										<tr height="20">
											<td class="stab">Date Created</td>
											<td colspan="3" class="stab">
												<ezf:inputText name="prchReqCratDt_RF" ezfName="prchReqCratDt_RF" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"9\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('prchReqCratDt_RF', 4);"/>
												-
												<ezf:inputText name="prchReqCratDt_RT" ezfName="prchReqCratDt_RT" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"10\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('prchReqCratDt_RT', 4);"/>
											</td>
											<td class="stab">Req Service Level</td>
											<td>
												<ezf:select name="shpgSvcLvlCd_SL" ezfName="shpgSvcLvlCd_SL" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:223\" tabindex=\"17\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" otherAttr=" tabindex=\"34\"">Ship To Supplier(*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="vndCd" ezfName="vndCd" value="235" otherAttr=" size=\"10\" maxlength=\"20\" tabindex=\"35\" ezftoupper=\"\""/>
												<ezf:inputText name="dplyVndNm" ezfName="dplyVndNm" value="CANON USA NP / CLC DIVISION" otherAttr=" size=\"28\" maxlength=\"320\" tabindex=\"36\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 6 -->
										<tr height="20">
											<td class="stab">Date Needed</td>
											<td colspan="3" class="stab">
												<ezf:inputText name="rqstRcvDt_NF" ezfName="rqstRcvDt_NF" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"11\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_NF', 4);"/>
												-
												<ezf:inputText name="rqstRcvDt_NT" ezfName="rqstRcvDt_NT" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"12\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_NT', 4);"/>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" otherAttr=" tabindex=\"18\"">Requested Carrier (*)</ezf:anchor></td>
											<td><ezf:inputText name="carrNm" ezfName="carrNm" value="UPS" otherAttr=" size=\"31\" maxlength=\"45\" tabindex=\"19\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="xxLinkAncr_OS" ezfName="xxLinkAncr_OS" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCustH" otherAttr=" tabindex=\"37\"">Ship To Customer(*)</ezf:anchor>
											</td>
											<td>
												<ezf:inputText name="shipToCustCd_EO" ezfName="shipToCustCd_EO" value="5127470" otherAttr=" size=\"10\" maxlength=\"20\" tabindex=\"38\" ezftoupper=\"\""/>
												<ezf:inputText name="shipToLocNm_EO" ezfName="shipToLocNm_EO" value="EXP01-BURLINGTON" otherAttr=" size=\"28\" maxlength=\"60\" tabindex=\"39\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 7 -->
										<tr height="20">
											<td colspan="4">&nbsp;</td>
											<td class="stab"><ezf:anchor name="mdseCd" ezfName="mdseCd" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseCd" otherAttr=" tabindex=\"20\"">Item# (*)</ezf:anchor></td>
											<td colspan="2">
												<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234A567AA" otherAttr=" size=\"31\" maxlength=\"16\" tabindex=\"21\""/>
											</td>
											<td class="stab">Display Level</td>
											<td class="stab" align="right">
												<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="0" otherAttr=" tabindex=\"40\""/>Header
												<ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="1" otherAttr=" tabindex=\"41\""/>Detail
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" tabindex=\"42\""/>
												<ezf:inputButton name="MoveTo_POReqEntryNew" value="New" htmlClass="pBtn6" otherAttr=" tabindex=\"43\""/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
                        <!-- ################################################## Header - [E N D] ################################################## -->

                        <hr>

                        <!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        <table style="margin-left: 11px; width: 100%;" >
                            <colgroup>
                                <col align="left" width="">
                                <col align="right" width="">
                            </colgroup>
                            <tbody>
                                <tr>
                                    <td>
                                        <table cellSpacing="0" cellPadding="1" border="0">
                                            <colgroup>
                                                <col width="123">
                                                <col width="">
                                            </colgroup>
                                            <tbody>
                                                <ezf:skip>
                                                <tr>
                                                    <td class="stab"><label>Preferred View Setting</label></td>
                                                    <td>
                                                        <select name="xxViewSet_SE" ezfname="xxViewSet_SE" ezflist="xxViewSet_CD,xxViewSet_DI,99, ,noblank">
                                                            <option>Default</option>
                                                            <option>----+----1----+----2</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                </ezf:skip>
                                                <tr>
                                                    <td>
                                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                    <td>
                                        <ezf:skip>
                                            <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
                                                <colgroup>
                                                    <col align="center" width="54">
                                                    <col align="right" width="40">
                                                    <col align="center" width="16">
                                                    <col align="right" width="40">
                                                    <col align="center" width="16">
                                                    <col align="right" width="40">
                                                    <col width="10">
                                                    <col>
                                                    <col width="1">
                                                    <col>
                                                </colgroup>
                                                <tbody>
                                                    <tr>
                                                        <td class="stab"><label>Showing</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowFromNum" ezfname="xxPageShowFromNum">1</label></td>
                                                        <td class="stab"><label>to</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowToNum" ezfname="xxPageShowToNum">20</label></td>
                                                        <td class="stab"><label>of</label></td>
                                                        <td class="pOut"><label ezfout name="xxPageShowOfNum" ezfname="xxPageShowOfNum">200</label></td>
                                                        <td>&nbsp;</td>
                                                        <td><input onclick="sendServer(this)" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                        <td></td>
                                                        <td><input onclick="sendServer(this)" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </ezf:skip>
                                        <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 38px;">
                                            <tbody>
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
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- ######################################## TO (COMMON)PAGENATION ###################################### --!>

                        <!-- ################################################## Detail   - [START] ################################################## -->
                        <div id="parentBoxA">
                            <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td valign="top">
                                        <div style="float:left; display:block"> <!-- left table -->
                                            <div id='leftTblHead' style="display:block;">
                                            </div>
                                            <div id='leftTbl' style="float:left; display:block; height:283px; overflow:hidden; margin-left: 0px; padding:0px;">
                                            </div>
                                        </div>  <!-- left table -->
                                        <div style="float:left"> <!-- right table -->
                                            <div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                            <%-- ******************************** --%>
                                            <%-- *** Right Table Area(Header) *** --%>
                                            <%-- ******************************** --%>
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" height="35" id="AHEAD" width="1996px" style="margin-right:20px" >
                                                    <col width="90" align="center"><!-- Request# -->
                                                    <col width="120" align="center"><!-- Request Type -->
                                                    <col width="120" align="center"><!-- Header Status -->
                                                    <col width="120" align="center"><!-- Approval Status -->
                                                    <col width="88"  align="center"><!-- Date Created -->
                                                    <col width="88" align="center"><!-- Need By Date -->
                                                    <col width="150" align="center"><!-- Document Source Type -->
                                                    <col width="150" align="center"><!-- Source Doc# -->
                                                    <col width="88"  align="center"><!-- Requester -->
                                                    <col width="150" align="center"><!-- Requester Name -->
                                                    <col width="88"  align="center"><!-- Business Unit -->
                                                    <col width="150" align="center"><!-- Req Service Level -->
                                                    <col width="88"  align="center"><!-- Carrier -->
                                                    <col width="150" align="center"><!-- Carrier Name -->
                                                    <col width="150" align="center"><!-- Vendor Return Reason -->
                                                    <col width="88"  align="center"><!-- Source WH -->
                                                    <col width="150" align="center"><!-- Source WH Name -->
                                                    <col width="100" align="center"><!-- Source SWH -->
                                                    <col width="150" align="center"><!-- Source SWH Name -->
                                                    <col width="88"  align="center"><!-- Destination WH -->
                                                    <col width="150" align="center"><!-- Destination WH Name -->
                                                    <col width="100" align="center"><!-- Destination SWH -->
                                                    <col width="150" align="center"><!-- Destination SWH Name -->
                                                    <col width="150" align="center"><!-- Plan Name -->
                                                    <col width="150" align="center"><!-- Ship To Supplier Name -->
                                                    <col width="150" align="center"><!-- Ship To Customer Name -->
                                                    <col width="60" align="center"><!-- Line# -->
                                                    <col width="80" align="center"><!-- Line Type -->
                                                    <col width="120" align="center"><!-- Item# -->
                                                    <col width="150" align="center"><!-- Item Description -->
                                                    <col width="120" align="center"><!-- Config# -->
                                                    <col width="100" align="center"><!-- Transfer Qty -->
                                                    <col width="120" align="center"><!-- Status -->
                                                    <col width="140" align="center"><!-- Unit Price -->
                                                    <col width="140" align="center"><!-- Total Cost -->
                                                    <col width="340" align="center"><!-- Charge Account -->
                                                    <col width="50" align="center"><!-- MT -->
                                                    <col width="60" align="center"><!-- PC -->
                                                    <col width="150" align="center"><!-- Serial# -->
                                                    <col width="300" align="center"><!-- Text -->
                                                    <tr>
                                                        <td id="AH0" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqNum_A1' )">Request#<img id="sortIMG.prchReqNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH1" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqTpDescTxt_A1' )">Request Type<img id="sortIMG.prchReqTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH2" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqStsDescTxt_A1' )">Header Status<img id="sortIMG.prchReqStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH3" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqApvlStsDescTxt_A1' )">Approval Status<img id="sortIMG.prchReqApvlStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH4" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqCratDt_A1' )">Date Create<img id="sortIMG.prchReqCratDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH5" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstRcvDt_A1' )">Need By Date<img id="sortIMG.rqstRcvDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH7" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqSrcTpDescTxt_A1' )">Document Source Type<img id="sortIMG.prchReqSrcTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH8" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'trxRefNum_A1' )">Source Doc#<img id="sortIMG.trxRefNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH9" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqRqstByPsnCd_A1' )">Requester<img id="sortIMG.prchReqRqstByPsnCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH10" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fullPsnNm_A1' )">Requester Name<img id="sortIMG.fullPsnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH11" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchGrpDescTxt_A1' )">Business Unit<img id="sortIMG.prchGrpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH12" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shpgSvcLvlDescTxt_A1' )">Req Service Level<img id="sortIMG.shpgSvcLvlDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH13" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrCd_A1' )">Carrier<img id="sortIMG.carrCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH14" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'carrNm_A1' )">Carrier Name<img id="sortIMG.carrNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH15" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'vndRtrnRsnDescTxt_A1' )">Vendor Return Reason<img id="sortIMG.vndRtrnRsnDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH16" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'srcRtlWhCd_A1' )">Source WH<img id="sortIMG.srcRtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH17" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Source WH Name<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH18" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'srcRtlSwhCd_A1' )">Source SWH<img id="sortIMG.srcRtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH19" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhNm_A1' )">Source SWH Name<img id="sortIMG.rtlSwhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH20" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'destRtlWhCd_A1' )">Destination WH<img id="sortIMG.destRtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH21" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A2' )">Destination WH Name<img id="sortIMG.rtlWhNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH22" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'destRtlSwhCd_A1' )">Destination SWH<img id="sortIMG.destRtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH23" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhNm_A2' )">Destination SWH Name<img id="sortIMG.rtlSwhNm_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH24" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mrpPlnNm_A1' )">Plan Name<img id="sortIMG.mrpPlnNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH25" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyVndNm_A1' )">Ship To Supplier Name<img id="sortIMG.dplyVndNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH26" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToLocNm_E1' )">Ship To Customer Name<img id="sortIMG.shipToLocNm_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH27" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem20Txt_A1' )">Line#<img id="sortIMG.xxScrItem20Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH28" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqLineTpDescTxt_A1' )">Line Type<img id="sortIMG.prchReqLineTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH29" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">Item#<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH30" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH31" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config#<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH32" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqDispQty_A1' )">Transfer Qty<img id="sortIMG.prchReqDispQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH33" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqLineStsDescTxt_A1' )">Status<img id="sortIMG.prchReqLineStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH34" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'entDealNetUnitPrcAmt_A1' )">Unit Price<img id="sortIMG.entDealNetUnitPrcAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label></a></td>
                                                        <td id="AH35" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'entPoDtlDealNetAmt_A1' )">Total Cost<img id="sortIMG.entPoDtlDealNetAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></label></a></td>
                                                        <td id="AH36" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDsMultMsgDplyTxt_A2' )">Charge Account<img id="sortIMG.xxDsMultMsgDplyTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH37" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaMdseTpCd_A1' )">MT<img id="sortIMG.coaMdseTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH38" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdCd_A2' )">PC<img id="sortIMG.coaProdCd_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH39" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqLineSubNum_A1' )">Serial#<img id="sortIMG.prchReqLineSubNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                        <td id="AH40" class="pClothBs fontMin"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prchReqLineCmntTxt_A1' )">Text<img id="sortIMG.prchReqLineCmntTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <%-- ******************************** --%>
                                            <%-- *** Right Table Area(Detail) *** --%>
                                            <%-- ******************************** --%>
                                            <div  id="rightTbl" style="width:1117px; height:300px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1996px">
                                                    <col width="90" align="left"><!-- Request# -->
                                                    <col width="120" align="center"><!-- Request Type -->
                                                    <col width="120" align="center"><!-- Header Status -->
                                                    <col width="120" align="center"><!-- Approval Status -->
                                                    <col width="88"  align="left"><!-- Date Created -->
                                                    <col width="88"  align="left"><!-- Need By Date -->
                                                    <col width="150" align="center"><!-- Document Source Type -->
                                                    <col width="150" align="left"><!-- Source Doc# -->
                                                    <col width="88"  align="left"><!-- Requester -->
                                                    <col width="150" align="center"><!-- Requester Name -->
                                                    <col width="88"  align="left"><!-- Business Unit -->
                                                    <col width="150" align="center"><!-- Req Service Level -->
                                                    <col width="88"  align="left"><!-- Carrier -->
                                                    <col width="150" align="center"><!-- Carrier Name -->
                                                    <col width="150" align="center"><!-- Vendor Return Reason -->
                                                    <col width="88"  align="left"><!-- Source WH -->
                                                    <col width="150" align="center"><!-- Source WH Name -->
                                                    <col width="100" align="left"><!-- Source SWH -->
                                                    <col width="150" align="center"><!-- Source SWH Name -->
                                                    <col width="88"  align="left"><!-- Destination WH -->
                                                    <col width="150" align="center"><!-- Destination WH Name -->
                                                    <col width="100" align="left"><!-- Destination SWH -->
                                                    <col width="150" align="center"><!-- Destination SWH Name -->
                                                    <col width="150" align="left"><!-- Plan Name -->
                                                    <col width="150" align="center"><!--Ship To Supplier Name -->
                                                    <col width="150" align="center"><!--Ship To Customer Name -->
                                                    <col width="60" align="left"><!-- Line# -->
                                                    <col width="80" align="center"><!-- Line Type -->
                                                    <col width="120" align="center"><!-- Item# -->
                                                    <col width="150" align="center"><!-- Item Description -->
                                                    <col width="120" align="center"><!-- Config# -->
                                                    <col width="100" align="right"><!-- Transfer Qty -->
                                                    <col width="120" align="center"><!-- Status -->
                                                    <col width="140" align="right"><!-- Unit Price -->
                                                    <col width="140" align="right"><!-- Total Cost -->
                                                    <col width="340" align="left"><!-- Charge Account -->
                                                    <col width="50" align="left"><!-- MT -->
                                                    <col width="60" align="left"><!-- PC -->
                                                    <col width="150" align="center"><!-- Serial# -->
                                                    <col width="300" align="center"><!-- Text -->
                                                    <ezf:row ezfHyo="A">
                                                        <tr height="25" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
                                                            <td class="stab"><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="MoveTo_POReqEntryUpd" otherAttr=" ezfanchortext=\"\" id=\"prchReqNum_Ancher{EZF_ROW_NUMBER}\""><ezf:label name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                            <td><ezf:inputText name="prchReqTpDescTxt_A1" ezfName="prchReqTpDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prchReqStsDescTxt_A1" ezfName="prchReqStsDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prchReqApvlStsDescTxt_A1" ezfName="prchReqApvlStsDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="prchReqCratDt_A1" ezfName="prchReqCratDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="rqstRcvDt_A1" ezfName="rqstRcvDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="prchReqSrcTpDescTxt_A1" ezfName="prchReqSrcTpDescTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="trxRefNum_A1" ezfName="trxRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="prchReqRqstByPsnCd_A1" ezfName="prchReqRqstByPsnCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="fullPsnNm_A1" ezfName="fullPsnNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="prchGrpDescTxt_A1" ezfName="prchGrpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="carrCd_A1" ezfName="carrCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="carrNm_A1" ezfName="carrNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="vndRtrnRsnDescTxt_A1" ezfName="vndRtrnRsnDescTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="srcRtlWhCd_A1" ezfName="srcRtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="srcRtlSwhCd_A1" ezfName="srcRtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="destRtlWhCd_A1" ezfName="destRtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="rtlWhNm_A2" ezfName="rtlWhNm_A2" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="destRtlSwhCd_A1" ezfName="destRtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="rtlSwhNm_A2" ezfName="rtlSwhNm_A2" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="mrpPlnNm_A1" ezfName="mrpPlnNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="dplyVndNm_A1" ezfName="dplyVndNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                            <td><ezf:inputText name="shipToLocNm_E1" ezfName="shipToLocNm_E1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                            <td><ezf:label name="xxScrItem20Txt_A1" ezfName="xxScrItem20Txt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="prchReqLineTpDescTxt_A1" ezfName="prchReqLineTpDescTxt_A1" value="----+----1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="----+----1----+-" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prchReqDispQty_A1" ezfName="prchReqDispQty_A1" value="9,999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prchReqLineStsDescTxt_A1" ezfName="prchReqLineStsDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="entDealNetUnitPrcAmt_A1" ezfName="entDealNetUnitPrcAmt_A1" value="9,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="entPoDtlDealNetAmt_A1" ezfName="entPoDtlDealNetAmt_A1" value="9,999,999,999.99" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"30\" style=\"text-align:right;\" ezftoupper=\"\""/></td>
                                                            <td><ezf:label name="xxDsMultMsgDplyTxt_A2" ezfName="xxDsMultMsgDplyTxt_A2" ezfHyo="A" ezfArrayNo="0" /><ezf:inputButton name="OpenWin_Account" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"account_Button{EZF_ROW_NUMBER}\""/></td>
                                                            <td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:label name="coaProdCd_A2" ezfName="coaProdCd_A2" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <td><ezf:inputText name="xxDsMultMsgDplyTxt_A1" ezfName="xxDsMultMsgDplyTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td><ezf:inputText name="prchReqLineCmntTxt_A1" ezfName="prchReqLineCmntTxt_A1" value="----+----1----+----2----+----3----+----4----+----5" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"41\" maxlength=\"30\" ezftoupper=\"\""/></td>
                                                            <td class="pAuditInfo">
                                                                <ezf:inputHidden name="xxRecHistCratTs_A1" ezfName="xxRecHistCratTs_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistCratByNm_A1" ezfName="xxRecHistCratByNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdTs_A1" ezfName="xxRecHistUpdTs_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistUpdByNm_A1" ezfName="xxRecHistUpdByNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                                                <ezf:inputHidden name="xxRecHistTblNm_A1" ezfName="xxRecHistTblNm_A1" ezfHyo="A" ezfArrayNo="0" />
                                                            </td>
                                                        </tr>
                                                    </ezf:row>
                                                    <ezf:skip>
                                                    </ezf:skip>
                                                </table>
                                            </div>
                                        </div> <!-- right table -->
                                    </td>
                                </tr>
                            </table>
                        </div> <!-- parentBoxA -->
                        <!-- ################################################## Search Result   - [E N D] ################################################## -->
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>

<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
</script>


<%-- **** Task specific area ends here **** --%>
