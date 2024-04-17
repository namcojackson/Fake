<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230303095002 --%>
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
			<input type="hidden" name="pageID" value="NPAL1270Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Requisition List">

<center>
    <table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
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
                                        <tr title="PO Requisition List">
                                            <td width="107px" align="center" class="same">PO Req List</td>
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
                                    <ezf:select name="srchOptPk_SL" ezfName="srchOptPk_SL" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\" tabindex=\"1\""/>
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
											<td class="stab">Requisition Number</td>
											<td colspan="2"><ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="12345678" otherAttr=" size=\"28\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"5\""/></td>
											<td></td>
											<td class="stab">Document Source Type</td>
											<td>
												<ezf:select name="prchReqSrcTpCd_SL" ezfName="prchReqSrcTpCd_SL" ezfBlank="1" ezfCodeName="prchReqSrcTpCd_PD" ezfDispName="prchReqSrcTpDescTxt_PD" otherAttr=" style=\"width:223\" tabindex=\"15\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" otherAttr=" tabindex=\"28\"">Supplier(*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="prntVndCd" ezfName="prntVndCd" otherAttr=" size=\"10\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"29\""/>
												<ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="CANON USA NP / CLC DIVISION" otherAttr=" size=\"29\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"30\""/>
											</td>
										</tr>
										<!-- 2 -->
										<tr height="20">
											<td class="stab">Requisition Type</td>
											<td colspan="2">
												<ezf:select name="prchReqTpCd_SL" ezfName="prchReqTpCd_SL" ezfBlank="1" ezfCodeName="prchReqTpCd_PD" ezfDispName="prchReqTpDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"6\""/>
											</td>
											<td></td>
											<td class="stab">Source Doc#</td>
											<td>
												<ezf:inputText name="trxRefNum" ezfName="trxRefNum" value="12345678" otherAttr=" size=\"31\" maxlength=\"30\" ezftoupper=\"\" tabindex=\"16\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" otherAttr=" tabindex=\"31\"">Supplier Site (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="vndCd" ezfName="vndCd" value="VS99999" otherAttr=" size=\"10\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"32\""/>
												<ezf:inputText name="vndNm" ezfName="vndNm" value="Canon Non EDI" otherAttr=" size=\"29\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"33\""/>
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
											<td>
												<ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="Min Max Plan 1" otherAttr=" size=\"31\" maxlength=\"70\" ezftoupper=\"\" tabindex=\"17\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" tabindex=\"34\"">Destination WH (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="destRtlWhCd" ezfName="destRtlWhCd" value="DS" otherAttr=" size=\"10\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"35\""/>
												<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="Drop Ship" otherAttr=" size=\"29\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"36\""/>
											</td>
										</tr>
										<!-- 4 -->
										<tr height="20">
											<td class="stab">Approval Status</td>
											<td colspan="2">
												<ezf:select name="prchReqApvlStsCd_SL" ezfName="prchReqApvlStsCd_SL" ezfBlank="1" ezfCodeName="prchReqApvlStsCd_PD" ezfDispName="prchReqApvlStsDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"8\""/>
											</td>
											<td></td>
											<td class="stab">Planning Group</td>
											<td>
												<ezf:select name="prchGrpCd_SL" ezfName="prchGrpCd_SL" ezfBlank="1" ezfCodeName="prchGrpCd_PD" ezfDispName="prchGrpDescTxt_PD" otherAttr=" style=\"width:223\" tabindex=\"18\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" tabindex=\"37\"">Destination SWH (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="destRtlSwhCd" ezfName="destRtlSwhCd" otherAttr=" size=\"10\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"38\""/>
												<ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" otherAttr=" size=\"29\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"39\""/>
											</td>
										</tr>
										<!-- 5 -->
										<tr height="20">
											<td class="stab">Line Status</td>
											<td colspan="2" class="stab">
												<ezf:select name="prchReqLineStsCd_SL" ezfName="prchReqLineStsCd_SL" ezfBlank="1" ezfCodeName="prchReqLineStsCd_PD" ezfDispName="prchReqLineStsDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"9\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" otherAttr=" tabindex=\"19\"">Item Number (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"31\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"20\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" otherAttr=" tabindex=\"40\"">Ship To Customer (*)</ezf:anchor></td>
											<td>
												<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="5127470" otherAttr=" size=\"10\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"41\""/>
												<ezf:inputText name="shipToLocNm" ezfName="shipToLocNm" value="EXP01-BURLINGTON" otherAttr=" size=\"29\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"42\""/>
											</td>
										</tr>
										<!-- 6 -->
										<tr height="20">
											<td class="stab">Req Service Level</td>
											<td colspan="2" class="stab">
												<ezf:select name="shpgSvcLvlCd_SL" ezfName="shpgSvcLvlCd_SL" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:202\" tabindex=\"10\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_MerchandiseType" otherAttr=" tabindex=\"21\"">Merchandise Type(*)</ezf:anchor></td>
											<td class="stab">
												<ezf:inputText name="coaMdseTpCd" ezfName="coaMdseTpCd" value="XX" otherAttr=" size=\"3\" maxlength=\"2\" ezftoupper=\"\" tabindex=\"22\""/>&nbsp;
												<ezf:inputText name="coaProjDescTxt" ezfName="coaProjDescTxt" value="XX" otherAttr=" size=\"26\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"23\""/>
											</td>
											<td></td>
											<td class="stab">Date Created</td>
											<td class="stab">
												<ezf:inputText name="prchReqCratDt_FR" ezfName="prchReqCratDt_FR" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"43\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('prchReqCratDt_FR', 4);" tabindex="44"/>
												-
												<ezf:inputText name="prchReqCratDt_TO" ezfName="prchReqCratDt_TO" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"45\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('prchReqCratDt_TO', 4);" tabindex="46"/>
											</td>
										</tr>
										<!-- 7 -->
										<tr height="20">
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" otherAttr=" tabindex=\"11\"">Requested Carrier (*)</ezf:anchor></td>
											<td colspan="2" class="stab">
												<ezf:inputHidden name="carrCd" ezfName="carrCd" otherAttr=" ezftoupper=\"\""/>
												<ezf:inputText name="carrNm" ezfName="carrNm" value="UPS" otherAttr=" size=\"28\" maxlength=\"45\" ezftoupper=\"\" tabindex=\"12\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ProductCode" otherAttr=" tabindex=\"24\"">Product Code(*)</ezf:anchor></td>
											<td class="stab">
												<ezf:inputText name="coaProdCd" ezfName="coaProdCd" value="XX" otherAttr=" size=\"3\" maxlength=\"2\" ezftoupper=\"\" tabindex=\"25\""/>&nbsp;
												<ezf:inputText name="coaProdNm" ezfName="coaProdNm" value="XX" otherAttr=" size=\"26\" maxlength=\"50\" tabindex=\"27\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Date Needed</td>
											<td class="stab">
												<ezf:inputText name="rqstRcvDt_FR" ezfName="rqstRcvDt_FR" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"47\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_FR', 4);" tabindex="48"/>
												-
												<ezf:inputText name="rqstRcvDt_TO" ezfName="rqstRcvDt_TO" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"49\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstRcvDt_TO', 4);" tabindex="50"/>
											</td>
										</tr>
										<!-- 8 -->
										<tr height="20">
											<td class="stab"><ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Requester" otherAttr=" tabindex=\"13\"">Buyer (*)</ezf:anchor></td>
											<td colspan="2">
												<ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" otherAttr=" size=\"28\" maxlength=\"45\" tabindex=\"14\""/>
												<ezf:inputHidden name="prchReqCratByPsnCd" ezfName="prchReqCratByPsnCd" value="Caruros" otherAttr=" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">PO Number</td>
											<td>
												<ezf:inputText name="poOrdNum" ezfName="poOrdNum" value="VP000001" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\" tabindex=\"27\""/>
											</td>
											<td></td>
											<!-- START 2023/02/01 S.Dong [QC#60966, ADD] -->
											<td class="stab">Vendor Ship Date</td>
                                            <td class="stab">
                                                <ezf:inputText name="rqstShipDt_FR" ezfName="rqstShipDt_FR" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"51\""/>
                                                <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt_FR', 4);" tabindex="52"/>
                                                -
                                                <ezf:inputText name="rqstShipDt_TO" ezfName="rqstShipDt_TO" value="06/12/2015" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"53\""/>
                                                <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt_TO', 4);" tabindex="54"/>
                                            </td>
                                            <!-- END 2023/02/01 S.Dong [QC#60966, ADD] -->
                                            <!-- START 2023/02/01 S.Dong [QC#60966, DEL] -->
											<!-- <td class="stab">Display Level</td>
											<td class="stab">
												<input type="Radio" value="Header" name="xxScrDply" checked="checked" ezfname="xxScrDply" ezftoupper tabindex="51" >Header
												<input type="Radio" value="Detail" name="xxScrDply" ezfname="xxScrDply" ezftoupper tabindex="52" >Detail
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" class="pBtn6" value="Search" name="Search" onclick="sendServer(this)" tabindex="53">
												<input type="button" class="pBtn6" value="New" name="OpenWin_POReqEntryNew" onclick="sendServer('OpenWin_POReqEntryNew')" tabindex="54">
											</td> -->
											<!-- END 2023/02/01 S.Dong [QC#60966, DEL] -->
										</tr>
										<!-- START 2023/02/01 S.Dong [QC#60966, ADD] -->
										<!-- 9 -->
                                        <tr height="20">
                                        <td colspan="7"></td>
                                            <td class="stab">Display Level</td>
                                            <td class="stab">
                                                <ezf:inputRadio name="xxScrDply" ezfName="xxScrDply" value="Header" otherAttr=" ezftoupper=\"\" tabindex=\"55\""/>Header
                                                <ezf:inputRadio name="xxScrDply" ezfName="xxScrDply" value="Detail" otherAttr=" ezftoupper=\"\" tabindex=\"56\""/>Detail
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" tabindex=\"57\""/>
                                                <ezf:inputButton name="OpenWin_POReqEntryNew" value="New" htmlClass="pBtn6" otherAttr=" tabindex=\"58\""/>
                                            </td>
                                        </tr>
                                        <!-- END 2023/02/01 S.Dong [QC#60966, ADD] -->
									</table>
								</td>
							</tr>
						</table>
                        <!-- ################################################## Header - [E N D] ################################################## -->

                        <hr>

                        <!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                        <table style="margin-left: 3px; width: 100%;" >
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
                                                <tr>
                                                    <!--<td class="stab"><label>Preferred View Setting</label></td> -->
                                                    <td>
                                                    	<!--
                                                        <select name="xxComnColOrdTxt" ezfname="xxComnColOrdTxt">
                                                            <option>Default</option>
                                                            <option>----+----1----+----2</option>
                                                        </select>
                                                        -->
                                                        <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                    <td>
                                        <ezf:skip>
                                            <table cellSpacing="0" cellPadding="0" border="0" style="float: right; margin-right: 30px;">
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
                                                        <td><input onclick="tablePagenation(this.name, 'A')" disabled id="btnPrev" class="pBtn3" type="button" value="Prev" name="PagePrev"></td>
                                                        <td></td>
                                                        <td><input onclick="tablePagenation(this.name, 'A')" id="btnNext" class="pBtn3" type="button" value="Next" name="PageNext"></td>
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
                        <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <!-- <td align="left" valign="top"> -->
                                <td align="left" valign="top">
                                    <%-- ******************************* --%>
                                    <%-- *** Left Table Area(Header) *** --%>
                                    <%-- ******************************* --%>
                                    <div id="parentBoxA">
                                    	<div style="float:left; display:block"> <!-- left table -->
          									<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
          									<!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
          									<!-- <div id="leftTbl" style="float:left; display:block; height:273px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div> -->
          									<div id="leftTbl" style="float:left; display:block; height:253px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
          									<!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
    									</div>  <!-- left table -->
    									<div style="float:left"> <!-- right table -->
    										<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                    			    <!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                    				<!-- <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="AHEAD" width="1299px" style="margin-right:20px" > --> 
                                    				<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0"  id="AHEAD" width="1419px" style="margin-right:20px" >
                                    				<!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                        				<col width="90" align="center"><!-- Requisition# -->
                                        				<col width="120" align="center"><!-- Requisition Type -->
                                        				<col width="120" align="center"><!-- Header Status -->
                                        				<col width="120" align="center"><!-- Approval Status -->
                                        				<col width="88"  align="center"><!-- Date Created -->
                                        				<col width="88" align="center"><!-- Need By Date -->
                                        				<col width="88" align="center"><!-- Need By Time -->
                                        				<!-- START 2023/02/01 S.Dong [QC#60966, ADD] -->
                                        				<col width="120" align="center"><!-- Vendor Ship Date -->
                                        				<!-- END 2023/02/01 S.Dong [QC#60966, ADD] -->
                                        				<col width="150" align="center"><!-- Document Source Type -->
                                        				<col width="150" align="center"><!-- Source Doc# -->
                                        				<col width="88"  align="center"><!-- Buyer -->
                                        				<col width="150" align="center"><!-- Buyer Name -->
                                        				<col width="88"  align="center"><!-- Planning Group -->
                                        				<col width="150" align="center"><!-- Req Service Level -->
                                        				<col width="88"  align="center"><!-- Carrier -->
                                        				<col width="150" align="center"><!-- Carrier Name -->
                                        				<col width="88"  align="center"><!-- Supplier -->
                                        				<col width="150" align="center"><!-- Supplier Name -->
                                        				<col width="150" align="center"><!-- Supplier Site -->
                                        				<col width="150" align="center"><!-- Supplier Site Name -->
                                        				<col width="88"  align="center"><!-- Destination WH -->
                                        				<col width="150" align="center"><!-- Destination WH Name -->
                                        				<col width="100" align="center"><!-- Destination SWH -->
                                        				<col width="150" align="center"><!-- Destination SWH Name -->
                                        				<col width="150" align="center"><!-- Plan Name -->
                                        				<col width="110" align="center"><!-- Ship To Customer -->
                                        				<col width="150" align="center"><!-- Ship To Customer Name -->
                                        				<col width="100" align="center"><!-- PO Number -->
                                        				<col width="65" align="center"><!-- Line# -->
                                        				<col width="80" align="center"><!-- Line Status -->
                                        				<col width="120" align="center"><!-- Item Code -->
                                        				<col width="150" align="center"><!-- Item Description -->
                                                        <col width="50"  align="center"><!-- MT -->
                                                        <col width="60"  align="center"><!-- PC -->
                                        				<col width="100" align="center"><!-- Order Qty -->
                                        				<col width="88" align="center"><!-- Scheduled PO Release Date -->
                                        				<col width="88" align="center"><!-- PO Release Date -->
                                        				<tr>
                                            				<td id="AH0" class="pClothBs">Requisition#</td>
                                            				<td id="AH1" class="pClothBs">Requisition Type</td>
                                            				<td id="AH2" class="pClothBs">Header Status</td>
                                            				<td id="AH3" class="pClothBs">Approval Status</td>
                                            				<td id="AH4" class="pClothBs">Date Created</td>
                                            				<td id="AH5" class="pClothBs">Need By Date</td>
                                            				<td id="AH6" class="pClothBs">Need By Time</td>
                                            				<!-- START 2023/02/03 S.Dong [QC#60966, MOD] -->
                                            				<td id="AH7" class="pClothBs">Vendor Ship Date</td>
                                            				<td id="AH8" class="pClothBs">Document Source Type</td>
                                            				<td id="AH9" class="pClothBs">Source Doc#</td>
                                            				<td id="AH10" class="pClothBs">Buyer</td>
                                            				<td id="AH11" class="pClothBs">Buyer Name</td>
                                            				<td id="AH12" class="pClothBs">Planning Group</td>
                                            				<td id="AH13" class="pClothBs">Req Service Level</td>
                                            				<td id="AH14" class="pClothBs">Carrier</td>
                                            				<td id="AH15" class="pClothBs">Carrier Name</td>
                                            				<td id="AH16" class="pClothBs">Supplier</td>
                                            				<td id="AH17" class="pClothBs">Supplier Name</td>
                                            				<td id="AH18" class="pClothBs">Supplier Site</td>
                                            				<td id="AH19" class="pClothBs">Supplier Site Name</td>
                                            				<td id="AH20" class="pClothBs">Destination WH</td>
                                            				<td id="AH21" class="pClothBs">Destination WH Name</td>
                                            				<td id="AH22" class="pClothBs">Destination SWH</td>
                                            				<td id="AH23" class="pClothBs">Destination SWH Name</td>
                                            				<td id="AH24" class="pClothBs">Plan Name</td>
                                            				<td id="AH25" class="pClothBs">Ship To Customer</td>
                                            				<td id="AH26" class="pClothBs">Ship To Customer Name</td>
                                            				<td id="AH27" class="pClothBs">PO Number</td>
                                            				<td id="AH28" class="pClothBs">PR Line#</td>
                                            				<td id="AH29" class="pClothBs">Line Status</td>
                                            				<td id="AH30" class="pClothBs">Item Code</td>
                                            				<td id="AH31" class="pClothBs">Item Description</td>
                                                            <td id="AH32" class="pClothBs">MT</td>
                                                            <td id="AH33" class="pClothBs">PC</td>
                                            				<td id="AH34" class="pClothBs">Order Qty</td>
                                            				<td id="AH35" class="pClothBs" class="stab">Planned PO<br>Release Date</td>
                                            				<td id="AH36" class="pClothBs" class="stab">PO<br>Released Date</td>
                                            				<!-- END 2023/02/03 S.Dong [QC#60966, MOD] -->
                                         				</tr>
                                    				</table>
                                    		</div>
                                    			<%-- ******************************* --%>
                                    			<%-- *** Left Table Area(Detail) *** --%>
                                    			<%-- ******************************* --%>
                                    			<!--
                                    			<div id="bottomTBL" style="overflow-y:scroll; height:310; overflow-x:scroll; width:1107; margin-left: 5px;"
                                        			onScroll="synchroScrollLeft( this.id, new Array( 'topTBL' );">
                                        		-->
                                        		<!-- START 2023/03/03 S.Dong [QC#60966, MOD] -->
                                    			<!-- <div  id="rightTbl" style="width:1117px; height:290px; display:block; overflow:scroll; margin:0px; padding:0px;" > -->
                                    			<div  id="rightTbl" style="width:1117px; height:270px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                        			<!-- <table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1299"> -->
                                        			<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" width="1419">
                                                <!-- END 2023/03/03 S.Dong [QC#60966, MOD] -->
                                            			<col width="90" align="left"><!-- Requisition# -->
                                            			<col width="120" align="center"><!-- Requisition Type -->
                                            			<col width="120" align="center"><!-- Header Status -->
                                            			<col width="120" align="center"><!-- Approval Status -->
                                            			<col width="88"  align="left"><!-- Date Created -->
                                            			<col width="88"  align="left"><!-- Need By Date -->
                                            			<col width="88"  align="left"><!-- Need By Time -->
                                            			<!-- START 2023/02/01 S.Dong [QC#60966, ADD] -->
                                                        <col width="120" align="left"><!-- Vendor Ship Date -->
                                                        <!-- END 2023/02/01 S.Dong [QC#60966, ADD] -->
                                            			<col width="150" align="center"><!-- Document Source Type -->
                                            			<col width="150" align="left"><!-- Source Doc# -->
                                            			<col width="88"  align="left"><!-- Buyer -->
                                            			<col width="150" align="center"><!-- Buyer Name -->
                                            			<col width="88"  align="left"><!-- Planning Group -->
                                            			<col width="150" align="center"><!-- Req Service Level -->
                                            			<col width="88"  align="left"><!-- Carrier -->
                                            			<col width="150" align="center"><!-- Carrier Name -->
                                            			<col width="88"  align="left"><!-- Supplier -->
                                            			<col width="150" align="center"><!-- Supplier Name -->
                                            			<col width="150" align="center"><!-- Supplier Site -->
                                            			<col width="150" align="center"><!-- Supplier Site Name-->
                                            			<col width="88"  align="left"><!-- Destination WH -->
                                            			<col width="150" align="center"><!-- Destination WH Name -->
                                            			<col width="100" align="left"><!-- Destination SWH -->
                                            			<col width="150" align="center"><!-- Destination SWH Name -->
                                            			<col width="150" align="left"><!-- Plan Name -->
                                            			<col width="110" align="left"><!-- Ship To Customer -->
                                            			<col width="150" align="center"><!-- Ship To Customer Name -->
                                            			<col width="100" align="left"><!-- PO Numner -->
                                            			<col width="65" align="center"><!-- Line# -->
                                            			<col width="80" align="center"><!-- Line Status -->
                                            			<col width="120" align="center"><!-- Item Code -->
                                            			<col width="150" align="center"><!-- Item Description -->
                                                        <col width="50" align="left"><!-- MT -->
                                                        <col width="60" align="left"><!-- PC -->
                                            			<col width="100" align="right"><!-- Order Qty -->
                                            			<col width="88" align="center"><!-- Planned PO Release Date -->
                                            			<col width="88" align="center"><!-- Planned PO Release Date -->
                                            			<ezf:row ezfHyo="A">
                                                			<tr height="25">
                                                    			<td class="stab">
                                                    				<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_POReqEntryUpdate" >
                                                    					<ezf:label name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" onClick="sendServer('OpenWin_POReqEntryUpdate')" />
                                                    				</ezf:anchor>
                                                    			</td>
                                                    			<td><ezf:inputText name="prchReqTpDescTxt_A1" ezfName="prchReqTpDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="prchReqStsDescTxt_A1" ezfName="prchReqStsDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="prchReqApvlStsDescTxt_A1" ezfName="prchReqApvlStsDescTxt_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                    			
                                                    			<td><ezf:label name="prchReqCratDt_A1" ezfName="prchReqCratDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:label name="rqstRcvDt_A1" ezfName="rqstRcvDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<!-- START 2023/02/02 S.Dong [QC#60966, ADD] -->
                                                    			<td><ezf:label name="rqstShipDt_A1" ezfName="rqstShipDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<!-- END 2023/02/02 S.Dong [QC#60966, ADD] -->
                                                    			<td><ezf:inputText name="prchReqSrcTpDescTxt_A1" ezfName="prchReqSrcTpDescTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="trxRefNum_A1" ezfName="trxRefNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:label name="prchReqCratByPsnCd_A1" ezfName="prchReqCratByPsnCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="fullPsnNm_A1" ezfName="fullPsnNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="prchGrpCd_A1" ezfName="prchGrpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="carrCd_A1" ezfName="carrCd_A1" value="----+----1----+----2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="carrNm_A1" ezfName="carrNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="prntVndCd_A1" ezfName="prntVndCd_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="prntVndNm_A1" ezfName="prntVndNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="vndCd_A1" ezfName="vndCd_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="locNm_A1" ezfName="locNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="destRtlWhCd_A1" ezfName="destRtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="destRtlSwhCd_A1" ezfName="destRtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="mrpPlnNm_A1" ezfName="mrpPlnNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:label name="shipToCustCd_A1" ezfName="shipToCustCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="----+----1----+" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                    			<td><ezf:label name="poOrdNum_A1" ezfName="poOrdNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:label name="prchReqLineNum_A1" ezfName="prchReqLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="prchReqLineStsNm_A1" ezfName="prchReqLineStsNm_A1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="5595B001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\""/></td>
                                                    			<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="ADDITIONAL MEMORY TYPE D (512M" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
                                                                <td><ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                                <td><ezf:label name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:inputText name="prchReqDispQty_A1" ezfName="prchReqDispQty_A1" value="999,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\" style=\"text-align:right;\""/></td>
                                                    			<td><ezf:label name="poSchdRelDt_A1" ezfName="poSchdRelDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                    			<td><ezf:label name="xxDtTm_A2" ezfName="xxDtTm_A2" ezfHyo="A" ezfArrayNo="0" /></td>
                                                			</tr>
                                            			</ezf:row>
                                        			</table>
                                    			</div>

                                    	</div><!--right table -->
                                    </div><!--parentBoxA -->
                                    <script type="text/javascript" defer>
    									S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, true);
									</script>
                                </td>
                                
                            </tr>
                        </table>
                        <!-- ################################################## Search Result   - [E N D] ################################################## -->
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</center>



<%-- **** Task specific area ends here **** --%>
