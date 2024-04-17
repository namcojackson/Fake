<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230214143951 --%>
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
			<input type="hidden" name="pageID" value="NPAL1510Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Search">
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
                                        <tr title="PR Entry Screen">
                                            <td width="107px" align="center" class="same">PO Search</td>
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
	                            <td class="stab"><ezf:inputText name="srchOptNm_TX" ezfName="srchOptNm_TX" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
	                            <td>
	                                <ezf:inputButton name="SaveSearchOption" value="Save Search" htmlClass="pBtn8" />
	                                <ezf:inputButton name="DeleteSearchOption" value="Delete Search" htmlClass="pBtn8" />
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
										<col width="90">
										<col width="135">
										<col width="150">
										<col width="10">
										<col width="120">
										<col width="220">
										<col width="10">
										<col width="60">
										<col width="10">
										<col width="125">
										<col width="10">
										<col width="10">
										<col width="10">
										<col width="">
										<!-- 1 -->
										<tr height="20">
											<td class="stab">PO Number(*)</td>
											<td colspan="2">
												<ezf:inputText name="poOrdNum" ezfName="poOrdNum" value="50123456" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"5\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="whMgrPsnCd_H2" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" otherAttr=" tabindex=\"15\"">Supplier(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="11331" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"16\" ezftoupper=\"\""/>
												<ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="Canon USA" otherAttr=" size=\"18\" maxlength=\"45\" tabindex=\"17\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">ACK Status</td>
											<td colspan="2">
												<ezf:select name="vndPoAckStsCd_SL" ezfName="vndPoAckStsCd_SL" ezfBlank="1" ezfCodeName="vndPoAckStsCd_PD" ezfDispName="vndPoAckStsDescTxt_PD" otherAttr=" style=\"width:220\" tabindex=\"39\""/>
											</td>
											<td></td>
											<td class="stab">Display Level</td>
										</tr>
										<!-- 2 -->
										<tr height="20">
											<td class="stab">PO Source</td>
											<td colspan="2">
												<ezf:select name="poOrdSrcCd_SL" ezfName="poOrdSrcCd_SL" ezfBlank="1" ezfCodeName="poOrdSrcCd_PD" ezfDispName="poOrdSrcDescTxt_PD" otherAttr=" style=\"width:100%\" tabindex=\"6\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Supplier" otherAttr=" tabindex=\"18\"">Supplier Site(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="vndCd" ezfName="vndCd" value="1133133" otherAttr=" size=\"10\" maxlength=\"20\" tabindex=\"19\" ezftoupper=\"\""/>
												<ezf:inputText name="vndNm" ezfName="vndNm" value="CAN02" otherAttr=" size=\"18\" maxlength=\"45\" tabindex=\"20\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">ACK#(*)</td>
											<td colspan="2">
												<ezf:inputText name="poAckNum" ezfName="poAckNum" value="50123456" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"40\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td>
												<!-- START 2023/02/07 TZ.Win [QC#60966, MOD] -->
												<ezf:inputRadio name="xxDplyByCtrlItemCdNm" ezfName="xxDplyByCtrlItemCdNm" value="PO" otherAttr=" ezftoupper=\"\" tabindex=\"53\""/>PO
												<ezf:inputRadio name="xxDplyByCtrlItemCdNm" ezfName="xxDplyByCtrlItemCdNm" value="Detail" otherAttr=" ezftoupper=\"\" tabindex=\"54\""/>Detail
												<ezf:inputRadio name="xxDplyByCtrlItemCdNm" ezfName="xxDplyByCtrlItemCdNm" value="ACK" otherAttr=" ezftoupper=\"\" tabindex=\"55\""/>ACK
												<!-- END 2023/02/07 TZ.Win [QC#60966, MOD] -->
											</td>
										</tr>
										<!-- 3 -->
										<tr height="20">
											<td class="stab">PO Type</td>
											<td colspan="2">
												<ezf:select name="dsPoTpCd_SL" ezfName="dsPoTpCd_SL" ezfBlank="1" ezfCodeName="dsPoTpCd_PD" ezfDispName="dsPoTpDescTxt_PD" otherAttr=" style=\"width:100%\" tabindex=\"7\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" tabindex=\"21\"">Destination WH(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="destRtlWhCd" ezfName="destRtlWhCd" value="A01" otherAttr=" size=\"10\" maxlength=\"8\" tabindex=\"22\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlWhNm" ezfName="rtlWhNm" value="Canon USA" otherAttr=" size=\"18\" maxlength=\"45\" tabindex=\"23\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Vendor CPO#(*)</td>
											<td colspan="2">
												<ezf:inputText name="vndIssOrdNum" ezfName="vndIssOrdNum" value="CPO12345" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"41\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:inputCheckBox name="xxChkBox_H1" ezfName="xxChkBox_H1" value="Y" />Include Write-Off PO
											</td>
										</tr>
										<!-- 4 -->
										<tr height="20">
											<td class="stab">Header Status</td>
											<td colspan="2">
												<ezf:select name="poHdrStsCd_SL" ezfName="poHdrStsCd_SL" ezfBlank="1" ezfCodeName="poHdrStsCd_PD" ezfDispName="poHdrStsDescTxt_PD" otherAttr=" style=\"width:100%\" tabindex=\"8\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" tabindex=\"24\"">Destination SWH(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="destRtlSwhCd" ezfName="destRtlSwhCd" value="NEW" otherAttr=" size=\"10\" maxlength=\"8\" tabindex=\"25\" ezftoupper=\"\""/>
												<ezf:inputText name="rtlSwhNm" ezfName="rtlSwhNm" value="NEW" otherAttr=" size=\"18\" maxlength=\"45\" tabindex=\"26\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Tracking#(*)</td>
											<td colspan="2">
												<ezf:inputText name="proNum" ezfName="proNum" value="60123456789" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"42\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 5 -->
										<tr height="20">
											<td class="stab">Approval Status</td>
											<td colspan="2">
												<ezf:select name="poApvlStsCd_SL" ezfName="poApvlStsCd_SL" ezfBlank="1" ezfCodeName="poApvlStsCd_PD" ezfDispName="poApvlStsDescTxt_PD" otherAttr=" style=\"width:100%\" tabindex=\"9\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToCust" otherAttr=" tabindex=\"27\"">Ship To Customer(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="shipToCustCd" ezfName="shipToCustCd" value="A01NEW" otherAttr=" size=\"10\" maxlength=\"45\" tabindex=\"28\" ezftoupper=\"\""/>
												<ezf:inputText name="shipToCustLocNm" ezfName="shipToCustLocNm" value="Canon USA" otherAttr=" size=\"18\" maxlength=\"45\" tabindex=\"29\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Shipment#(*)</td>
											<td colspan="2">
												<ezf:inputText name="vndSoNum" ezfName="vndSoNum" value="SO12345" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"43\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 6 -->
										<tr height="20">
											<td class="stab">PO Line Status</td>
											<td colspan="2">
												<ezf:select name="poLineStsCd_SL" ezfName="poLineStsCd_SL" ezfBlank="1" ezfCodeName="poLineStsCd_PD" ezfDispName="poLineStsDescTxt_PD" otherAttr=" style=\"width:100%\" tabindex=\"10\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="mdseCd_H2" ezfName="mdseCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" otherAttr=" tabindex=\"30\"">Item#(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="mdseCd" ezfName="mdseCd" value="1234B001AA" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"31\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Ship From WH(*)</td>
											<td colspan="2">
												<ezf:inputText name="vndInvtyLocCd" ezfName="vndInvtyLocCd" value="51" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"44\" ezftoupper=\"\""/>
											</td>
										</tr>
										<!-- 7 -->
										<tr height="20">
											<td class="stab">Source Doc#(*)</td>
											<td colspan="2">
												<ezf:inputText name="trxRefNum" ezfName="trxRefNum" value="PR12345" otherAttr=" maxlength=\"30\" style=\"width:100%\" tabindex=\"11\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Supplier Item#(*)</td>
											<td colspan="3">
												<ezf:inputText name="aslMdseCd" ezfName="aslMdseCd" value="1234B001AA" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"32\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Date Created From/To</td>
											<td colspan="3">
												<ezf:inputText name="poSubmtDt_FR" ezfName="poSubmtDt_FR" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"45\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('poSubmtDt_FR', 4);"/>
												<ezf:inputText name="poSubmtDt_TO" ezfName="poSubmtDt_TO" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"46\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('poSubmtDt_TO', 4);"/>
											</td>
										</tr>
										<!-- 8 -->
										<tr height="20">
											<td class="stab">PO Req#(*)</td>
											<td colspan="2">
												<ezf:inputText name="prchReqNum" ezfName="prchReqNum" value="PR12345" otherAttr=" maxlength=\"30\" style=\"width:100%\" tabindex=\"12\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">
												<ezf:anchor name="mdseCd_L1" ezfName="mdseCd_L1" ezfEmulateBtn="1" ezfGuard="OpenWin_SubItem" otherAttr=" tabindex=\"33\"">Substituted Item#(*)</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="mdseCd_SB" ezfName="mdseCd_SB" value="1234B001AA" otherAttr=" size=\"20\" maxlength=\"30\" style=\"width:100%\" tabindex=\"34\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Date Transmitted From/To</td>
											<td colspan="2">
												<ezf:inputText name="xxDt10Dt_FR" ezfName="xxDt10Dt_FR" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"47\" ezftoupper=\"\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxDt10Dt_FR', 4);"/>
												<ezf:inputText name="xxDt10Dt_TO" ezfName="xxDt10Dt_TO" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"48\" ezftoupper=\"\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxDt10Dt_TO', 4);"/>
											</td>
										</tr>
										<!-- 8 -->
										<tr height="20">
											<td class="stab">Req Service Level</td>
											<td colspan="2">
												<ezf:select name="shpgSvcLvlCd_SL" ezfName="shpgSvcLvlCd_SL" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_PD" ezfDispName="shpgSvcLvlDescTxt_PD" otherAttr=" style=\"width:100%\" tabindex=\"13\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_28" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseTpCd" otherAttr=" tabindex=\"35\"">Merchandise Type(*)</ezf:anchor></td>
											<td colspan="3">
												<ezf:inputText name="coaMdseTpCd" ezfName="coaMdseTpCd" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" tabindex=\"36\""/>
												<ezf:inputText name="coaProjDescTxt" ezfName="coaProjDescTxt" value="XX" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
											</td>
											<td></td>
											<td class="stab">Date ACK From/To</td>
											<td colspan="2">
												<ezf:inputText name="xxOrdDt_FR" ezfName="xxOrdDt_FR" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"49\" ezftoupper=\"\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxOrdDt_FR', 4);"/>
												<ezf:inputText name="xxOrdDt_TO" ezfName="xxOrdDt_TO" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"50\" ezftoupper=\"\""/>
												<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('xxOrdDt_TO', 4);"/>
											</td>
										</tr>
										<!-- 9 -->
										<tr height="20">
											<td class="stab">
												<ezf:anchor name="whMgrPsnCd_H2" ezfName="whMgrPsnCd_H2" ezfEmulateBtn="1" ezfGuard="OpenWin_Buyer" otherAttr=" tabindex=\"14\"">Buyer(*)</ezf:anchor>
											</td>
											<td colspan="2">
												<ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="Cristina" otherAttr=" size=\"20\" maxlength=\"45\" tabindex=\"14\""/>
												<ezf:inputHidden name="poSubmtPsnCd" ezfName="poSubmtPsnCd" otherAttr=" size=\"30\" maxlength=\"45\""/>
											</td>
											<td></td>
											<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_07" ezfEmulateBtn="1" ezfGuard="OpenWin_PrdCd" otherAttr=" tabindex=\"37\"">Product Code(*)</ezf:anchor></td>
											<td colspan="3">
												<ezf:inputText name="coaProdCd" ezfName="coaProdCd" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\" tabindex=\"38\""/>
												<ezf:inputText name="coaProdNm" ezfName="coaProdNm" value="XX" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
											</td>
											<!-- START 2023/02/07 TZ.Win [QC#60966, MOD] -->
											<td></td>
											<!-- END 2023/02/07 TZ.Win [QC#60966, MOD] -->
											<!-- START 2023/02/07 TZ.Win [QC#60966, ADD] -->
											<td class="stab">Vendor Ship Date</td>
                                            <td colspan="2">
                                                <ezf:inputText name="rqstShipDt_FR" ezfName="rqstShipDt_FR" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"51\""/>
                                                <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt_FR', 4);"/>
                                                <ezf:inputText name="rqstShipDt_TO" ezfName="rqstShipDt_TO" value="05/15/2016" otherAttr=" maxlength=\"10\" size=\"10\" tabindex=\"52\""/>
                                                <IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('rqstShipDt_TO', 4);"/>
                                            </td>
                                            <!-- END 2023/02/07 TZ.Win [QC#60966, ADD] -->
											<td colspan="2" align="right">
												<!-- START 2023/02/07 TZ.Win [QC#60966, MOD] -->
												<ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" otherAttr=" tabindex=\"56\""/>
												<!-- END 2023/02/07 TZ.Win [QC#60966, MOD] -->
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
                        <!-- ################################################## Header - [E N D] ################################################## -->
                    <hr>

	                <%-- ######################################## DETAIL ######################################## --%>
					<table border="0" cellpadding="1" cellspacing="0" style="margin-left:5px;">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="400px"  align="left">
									<col width="400px"  align="left"><%-- The horizontal space between Fileters Parts and Paging Parts --%>
									<col align="right">
									<tr>
										<%-- =============== Filters Parts =============== --%>
										<td>
											<table>
												<tr height="20px">
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
												</tr>
											</table>
										</td>
										<%-- The horizontal space between Fileters Parts and Paging Parts --%>
										<td>
										</td>
										<%-- =============== Paging Parts =============== --%>
										<td>
											<div align="right">
												
			                                        <table cellspacing="0" cellpadding="0" border="0" style="float: right; margin-right: 25px;">
			                                            <tbody>
			                                                <tr>
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
			                                    
											</div>
										</td>
									</tr>
								</table>

							</td>
						</tr>

						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<!-- Detail Table Area Start -->
										<%-- +++++ [START] Programming JSP for Nesting Records Table +++++ --%>
										<%@ page import="business.servlet.NPAL1510.NPAL1510BMsg" %>
										<%@ page import="business.servlet.NPAL1510.NPAL1510_ABMsg" %>
										<% 
										NPAL1510BMsg bMsg = (NPAL1510BMsg)databean.getEZDBMsg(); 
										%>
										<td align="left" valign="top">
											<div id="parentBoxA">
												<div style="float:left; display:block"><!-- left table -->
													<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
													<div id="leftTbl" style="float:left; display:block; height:225px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
												</div><!-- left table -->
												<div style="float:left"><!-- right table -->
													<div id="rightTblHead" style="width:1101px; display:block; overflow:hidden; margin:0px;padding:0px;">
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" style="margin-right:20px">
														<col width="100" align="center">
														<col width="100" align="center">
														<col width="88"  align="center">
														<col width="125" align="center">
														<col width="125" align="center"><!-- Supplier Code -->
														<col width="125" align="center"><!-- Supplier Name -->
														<col width="125" align="center"><!-- Supplier Site Code -->
														<col width="125" align="center"><!-- Supplier Site Name -->
														<col width="46" align="center"><!-- Line# -->
														<col width="125" align="center"><!-- Line Type-->
														<col width="125" align="center"><!-- Item# -->
														<col width="125" align="center"><!-- Sub Item# -->
														<col width="125" align="center"><!-- Supplier Item# -->
														<col width="150" align="center"><!-- Item Description -->
														<col width="46"  align="center"><!-- Merchandise Type -->
														<col width="46"  align="center"><!-- Product Code -->
														<col width="88" align="center"><!-- Line Price -->
														<col width="88" align="center"><!-- Order Qty -->
														<col width="82"  align="center"><!-- Date Created -->
														<col width="125" align="center"><!-- Ship To Customer -->
														<col width="150" align="center"><!-- Ship To Customer Name -->
														<col width="88" align="center"><!-- Dest WH -->
														<col width="88" align="center"><!-- Dest SWH -->
														<col width="125" align="center"><!-- Match Opt -->
														<col width="125" align="center"><!-- Line Status -->
														<col width="88" align="center"><!-- Ext. Total -->
														<col width="88"  align="center"><!-- Received Qty -->
														<col width="88"  align="center"><!-- Invoiced Qty -->
														<col width="88"  align="center"><!-- Canceld Qty -->
														<col width="125" align="center"><!-- Req Service Level -->
														<col width="88" align="center"><!-- Req Doc# -->
														<col width="88" align="center"><!-- Req Doc#/Line# -->
														<col width="88" align="center"><!-- SrcOrdDoc# -->
														<col width="100" align="center"><!-- SrcOrdDoc#/Line# -->
														<col width="88" align="center"><!-- Original PO# -->
														<col width="120" align="center"><!-- Original PO#/Line# -->
														<col width="46"  align="center"><!-- ACK# -->
														<col width="88"  align="center"><!-- DateTrans. -->
														<col width="46" align="center"><!-- ACK Cd -->
														<col width="125" align="center"><!-- ACK Description -->
														<col width="88"  align="center"><!-- ACK Date -->
														<col width="88"  align="center"><!-- ACK Qty -->
														<col width="88"  align="center"><!-- FOB -->
														<col width="82"  align="center"><!-- ETD -->
														<col width="82"  align="center"><!-- ETA -->
														<col width="88"  align="center"><!-- Shipped by -->
														<col width="150"  align="center"><!-- Tracking# -->
														<col width="88" align="center"><!-- Ship# -->
														<col width="88" align="center"><!-- Ship#/Line# -->
														<col width="88"  align="center"><!-- Ship From WH -->
														<col width="88"  align="center"><!-- Ship To -->
														<col width="88"  align="center"><!-- Sold To -->
														<col width="88" align="center"><!-- Unit Price -->
														<col width="88" align="center"><!-- COMP Price -->
														<col width="88"  align="center"><!-- Vnd CPO# -->
														<col width="88"  align="center"><!-- Vnd PO# -->
														<col width="125" align="center"><!-- Vnd Line Detail Status -->
														<!-- START 2023/02/07 TZ.Win [QC#60966, ADD] -->
                                        				<col width="100" align="center"><!-- Vendor Ship Date -->
                                        				<!-- END 2023/02/07 TZ.Win [QC#60966, ADD] -->
														<col width="108"  align="center"><!-- Received WO Qty -->
														<col width="108"  align="center"><!-- Invoiced WO Qty -->
														<tr>
															<td class="pClothBs colFix" height="30" id="AH0">PO#</td>
															<td class="pClothBs colFix" height="30" id="AH1">PO Type</td>
															<td class="pClothBs colFix" height="30" id="AH2">Header Status</td>
															<td class="pClothBs colFix" height="30" id="AH3">Approval Status</td>
															<td class="pClothBs" height="30" id="AH4">Supplier Code</td>
															<td class="pClothBs" height="30" id="AH5">Supplier Name</td>
															<td class="pClothBs" height="30" id="AH6">Supplier Site Code</td>
															<td class="pClothBs" height="30" id="AH7">Supplier Site Name</td>
															<td class="pClothBs" height="30" id="AH8">Line#</td>
															<td class="pClothBs" height="30" id="AH9">Line Type</td>
															<td class="pClothBs" height="30" id="AH10">Item#</td>
															<td class="pClothBs" height="30" id="AH11">Sub Item#</td>
															<td class="pClothBs" height="30" id="AH12">Supplier Item#</td>
															<td class="pClothBs" height="30" id="AH13">Item Description</td>
															<td class="pClothBs" height="30" id="AH14">MT</td>
															<td class="pClothBs" height="30" id="AH15">PC</td>
															<td class="pClothBs" height="30" id="AH16">Line Price</td>
															<td class="pClothBs" height="30" id="AH17">Order Qty</td>
															<td class="pClothBs" height="30" id="AH18">Date Created</td>
															<td class="pClothBs" height="30" id="AH19">Ship To Customer</td>
															<td class="pClothBs" height="30" id="AH20">Ship To Customer Name</td>
															<td class="pClothBs" height="30" id="AH21">Dest WH</td>
															<td class="pClothBs" height="30" id="AH22">Dest SWH</td>
															<td class="pClothBs" height="30" id="AH23">Match Opt</td>
															<td class="pClothBs" height="30" id="AH24">Line Status</td>
															<td class="pClothBs" height="30" id="AH25">Ext. Total</td>
															<td class="pClothBs" height="30" id="AH26">Received Qty</td>
															<td class="pClothBs" height="30" id="AH27">Invoiced Qty</td>
															<td class="pClothBs" height="30" id="AH28">Canceld Qty</td>
															<td class="pClothBs" height="30" id="AH29">Req Service Level</td>
															<td class="pClothBs" height="30" id="AH30">Req Doc#</td>
															<td class="pClothBs" height="30" id="AH31">Req Doc#/Line#</td>
															<td class="pClothBs" height="30" id="AH32">SrcOrdDoc#</td>
															<td class="pClothBs" height="30" id="AH33">SrcOrdDoc#/Line#</td>
															<td class="pClothBs" height="30" id="AH34">Original PO#</td>
															<td class="pClothBs" height="30" id="AH35">Original PO#/Line#</td>
															<td class="pClothBs" height="30" id="AH36">ACK#</td>
															<td class="pClothBs" height="30" id="AH37">DateTrans.</td>
															<td class="pClothBs" height="30" id="AH38">ACK Cd</td>
															<td class="pClothBs" height="30" id="AH39">ACK Description</td>
															<td class="pClothBs" height="30" id="AH40">ACK Date</td>
															<td class="pClothBs" height="30" id="AH41">ACK Qty</td>
															<td class="pClothBs" height="30" id="AH42">FOB</td>
															<td class="pClothBs" height="30" id="AH43">ETD</td>
															<td class="pClothBs" height="30" id="AH44">ETA</td>
															<td class="pClothBs" height="30" id="AH45">Shipped by</td>
															<td class="pClothBs" height="30" id="AH46">Tracking#</td>
															<td class="pClothBs" height="30" id="AH47">Ship#</td>
															<td class="pClothBs" height="30" id="AH48">Ship#/Line#</td>
															<td class="pClothBs" height="30" id="AH49">ShipFrom WH</td>
															<td class="pClothBs" height="30" id="AH50">Ship To</td>
															<td class="pClothBs" height="30" id="AH51">Sold To</td>
															<td class="pClothBs" height="30" id="AH52">Unit Price</td>
															<td class="pClothBs" height="30" id="AH53">COMP Price</td>
															<td class="pClothBs" height="30" id="AH54">Vnd CPO#</td>
															<td class="pClothBs" height="30" id="AH55">Vnd PO#</td>
															<td class="pClothBs" height="30" id="AH56">Vnd Line Detail Status</td>
															<!-- START 2023/02/07 TZ.Win [QC#60966, ADD] -->
                                            				<td class="pClothBs" height="30" id="AH57">Vendor Ship Date</td>
                                            				<!-- END 2023/02/07 TZ.Win [QC#60966, ADD] -->
                                            				<!-- START 2023/02/07 TZ.Win [QC#60966, MOD] -->
															<td class="pClothBs" height="30" id="AH58">Received WO Qty</td>
															<td class="pClothBs" height="30" id="AH59">Invoiced WO Qty</td>
															<!-- END 2023/02/07 TZ.Win [QC#60966, MOD] -->
														</tr>
													</table>
												</div>
												<%-- ******************************* --%>
												<%-- *** Left Table Area(Detail) *** --%>
												<%-- ******************************* --%>
												<div id="rightTbl" style="width:1118px; height:242px; display:block; overflow:scroll; margin:0px; padding:0px;" >
													<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A">
														<col width="100" align="left">
														<col width="100" align="left">
														<col width="88"  align="left">
														<col width="125" align="left">
														<col width="125" align="left"><!-- Supplier Code -->
														<col width="125" align="left"><!-- Supplier Name -->
														<col width="125" align="left"><!-- Supplier Site Code -->
														<col width="125" align="left"><!-- Supplier Site Name -->
														<col width="46" align="left"><!-- Line# -->
														<col width="125" align="left"><!-- Line Type-->
														<col width="125" align="left"><!-- Item# -->
														<col width="125" align="left"><!-- Sub Item# -->
														<col width="125" align="left"><!-- Supplier Item# -->
														<col width="150" align="center"><!-- Item Description -->
														<col width="46" align="left"><!-- Merchandise Type -->
														<col width="46" align="left"><!-- Product Code -->
														<col width="88"  align="right"><!-- Line Price -->
														<col width="88"  align="right"><!-- Order Qty -->
														<col width="82"  align="left"><!-- Date Created -->
														<col width="125" align="left"><!-- Ship To Customer -->
														<col width="150" align="left"><!-- Ship To Customer Name -->
														<col width="88"  align="left"><!-- Dest WH -->
														<col width="88"  align="left"><!-- Dest SWH -->
														<col width="125" align="left"><!-- Match Opt -->
														<col width="125" align="left"><!-- Line Status -->
														<col width="88"  align="right"><!-- Ext. Total -->
														<col width="88"  align="right"><!-- Received Qty -->
														<col width="88"  align="right"><!-- Invoiced Qty -->
														<col width="88"  align="right"><!-- Canceld Qty -->
														<col width="125" align="left"><!-- Req Service Level -->
														<col width="88" align="left"><!-- Req Doc# -->
														<col width="88"  align="left"><!-- Req Doc#/Line# -->
														<col width="88" align="left"><!-- SrcOrdDoc# -->
														<col width="100"  align="left"><!-- SrcOrdDoc#/Line# -->
														<col width="88" align="left"><!-- Original PO# -->
														<col width="120"  align="left"><!-- Original PO#/Line# -->
														<col width="46"  align="left"><!-- ACK# -->
														<col width="88"  align="left"><!-- DateTrans. -->
														<col width="46" align="left"><!-- ACK Cd -->
														<col width="125" align="left"><!-- ACK Description -->
														<col width="88"  align="left"><!-- ACK Date -->
														<col width="88"  align="right"><!-- ACK Qty -->
														<col width="88"  align="right"><!-- FOB -->
														<col width="82"  align="left"><!-- ETD -->
														<col width="82"  align="left"><!-- ETA -->
														<col width="88"  align="left"><!-- Shipped by -->
														<col width="150"  align="left"><!-- Tracking# -->
														<col width="88" align="left"><!-- Ship# -->
														<col width="88" align="left"><!-- Ship#/Line# -->
														<col width="88"  align="left"><!-- Ship From WH -->
														<col width="88"  align="left"><!-- Ship To -->
														<col width="88"  align="left"><!-- Sold To -->
														<col width="88"  align="right"><!-- Unit Price -->
														<col width="88"  align="right"><!-- COMP Price -->
														<col width="88"  align="left"><!-- Vnd CPO# -->
														<col width="88"  align="left"><!-- Vnd PO# -->
														<col width="125" align="left"><!-- Vnd Line Detail Status -->
														<!-- START 2023/02/07 TZ.Win [QC#60966, ADD] -->
                                                        <col width="100" align="left"><!-- Vendor Ship Date -->
                                                        <!-- END 2023/02/07 TZ.Win [QC#60966, ADD] -->
														<col width="108"  align="right"><!-- Received WO Qty -->
														<col width="108"  align="right"><!-- Invoiced WO Qty -->

														<ezf:row ezfHyo="A">
															<tr height="28">
																<td>
																	<ezf:anchor name="poOrdNum_A1" ezfName="poOrdNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenPoEntry" otherAttr=" id=\"poOrdNum_A1\" ezfanchortext=\"\"">
																		<ezf:label name="poOrdNum_A1" ezfName="poOrdNum_A1" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
																</td>
																<td>
																	<ezf:label name="dsPoTpDescTxt_A1" ezfName="dsPoTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poHdrStsDescTxt_A1" ezfName="poHdrStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poApvlStsDescTxt_A1" ezfName="poApvlStsDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="prntVndCd_A1" ezfName="prntVndCd_A1" value="CANON USA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="prntVndNm_A1" ezfName="prntVndNm_A1" value="CANON USA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"240\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="vndCd_A1" ezfName="vndCd_A1" value="CANON USA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"20\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="vndNm_A1" ezfName="vndNm_A1" value="CANON USA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="dispPoDtlLineNum_A1" ezfName="dispPoDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poLineTpDescTxt_A1" ezfName="poLineTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="mdseCd_A0" ezfName="mdseCd_A0" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="xxMdseCd_A0" ezfName="xxMdseCd_A0" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="aslMdseCd_A1" ezfName="aslMdseCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="IMAGERUNNER ADVANCED C5250" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="coaMdseTpCd_A1" ezfName="coaMdseTpCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="coaProdCd_A1" ezfName="coaProdCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="entDealNetUnitPrcAmt_A1" ezfName="entDealNetUnitPrcAmt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poDispQty_A1" ezfName="poDispQty_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poSubmtDt_A1" ezfName="poSubmtDt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="shipToCustCd_A1" ezfName="shipToCustCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="shipToCustLocNm_A1" ezfName="shipToCustLocNm_A1" value="Customer1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="destRtlWhCd_A1" ezfName="destRtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="destRtlSwhCd_A1" ezfName="destRtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poMatchTpDescTxt_A1" ezfName="poMatchTpDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="poLineStsDescTxt_A1" ezfName="poLineStsDescTxt_A1" value="Open" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="entPoDtlDealNetAmt_A1" ezfName="entPoDtlDealNetAmt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poRcvQty_A1" ezfName="poRcvQty_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poInvQty_A1" ezfName="poInvQty_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poCancQty_A1" ezfName="poCancQty_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="BESTWAY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="prchReqNum_A1" ezfName="prchReqNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="prchReqLineNum_A1" ezfName="prchReqLineNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="trxRefNum_A1" ezfName="trxRefNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="trxRefLineNum_A1" ezfName="trxRefLineNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="origPoOrdNum_A1" ezfName="origPoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="origDispPoDtlLineNum_A1" ezfName="origDispPoDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poAckNum_A1" ezfName="poAckNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poSendDt_A1" ezfName="poSendDt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="vndPoAckStsCd_A1" ezfName="vndPoAckStsCd_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="vndPoAckStsDescTxt_A1" ezfName="vndPoAckStsDescTxt_A1" value="DO Created" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="xxDt10Dt_A1" ezfName="xxDt10Dt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="ordQty_A1" ezfName="ordQty_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="xxTotAmt_A1" ezfName="xxTotAmt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="etdDt_A1" ezfName="etdDt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="etaDt_A1" ezfName="etaDt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="vndOtbdCarrNm_A1" ezfName="vndOtbdCarrNm_A1" value="&nbsp;" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:anchor name="carrTrkUrl_A1" ezfName="carrTrkUrl_A1" ezfHyo="A" ezfArrayNo="0" onClick="window.open( this.href, '_blank', 'status=yes, toolbar=yes, location=yes, menubar=yes, scrollbars=yes, resizable=yes');return false;" otherAttr=" id=\"carrTrk{EZF_ROW_NUMBER}\"">
																		<ezf:label name="proNum_A1" ezfName="proNum_A1" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
																</td>
																<td>
																	<ezf:label name="vndSoNum_A1" ezfName="vndSoNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="vndSoSlpNum_A1" ezfName="vndSoSlpNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="vndInvtyLocCd_A1" ezfName="vndInvtyLocCd_A1" value="&nbsp;" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="vndShipToCustLocNm_A1" ezfName="vndShipToCustLocNm_A1" value="&nbsp;" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:inputText name="vndSellToCustLocNm_A1" ezfName="vndSellToCustLocNm_A1" value="&nbsp;" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"60\" ezftoupper=\"\""/>
																</td>
																<td>
																	<ezf:label name="thisMthFobCostAmt_A1" ezfName="thisMthFobCostAmt_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
		 														<td>
		 															<ezf:label name="vndChildBomPrcAmt_A1" ezfName="vndChildBomPrcAmt_A1" ezfHyo="A" ezfArrayNo="0" />
		 														</td>
		 														<td>
			 														<ezf:anchor name="vndCpoOrdNum_A1" ezfName="vndCpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CUSAEventHist" otherAttr=" id=\"vndCpoOrdNum_A1\" ezfanchortext=\"\"">
																		<ezf:label name="vndCpoOrdNum_A1" ezfName="vndCpoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" />
																	</ezf:anchor>
		 														</td>
																<td>
																	<ezf:label name="vndIssPoOrdNum_A1" ezfName="vndIssPoOrdNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputText name="vndPoAckLineStsTxt_A1" ezfName="vndPoAckLineStsTxt_A1" value="DO Created" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
																</td>
																<!-- START 2023/02/07 TZ.Win [QC#60966, ADD] -->
                                                    			<td>
                                                    				<ezf:label name="rqstShipDt_A1" ezfName="rqstShipDt_A1" ezfHyo="A" ezfArrayNo="0" />
                                                    			</td>
                                                    			<!-- END 2023/02/07 TZ.Win [QC#60966, ADD] -->
																<td>
																	<ezf:label name="poRcvQty_WO" ezfName="poRcvQty_WO" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:label name="poInvQty_WO" ezfName="poInvQty_WO" ezfHyo="A" ezfArrayNo="0" />
																</td>
																<td>
																	<ezf:inputHidden name="vndOtbdCarrCd_A1" ezfName="vndOtbdCarrCd_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="vndCpoDtlLineNum_A1" ezfName="vndCpoDtlLineNum_A1" ezfHyo="A" ezfArrayNo="0" />
																	<ezf:inputHidden name="vndCpoDtlLineSubNum_A1" ezfName="vndCpoDtlLineSubNum_A1" ezfHyo="A" ezfArrayNo="0" />
																</td>
															</tr>
														</ezf:row>
													</table>
												</div>
											</div>
											<script type="text/javascript" defer>
												S21TableUI.initialize("parentBoxA", "AHEAD", "A", 4, true, true);
											</script>
										</td>
											<!-- Detail Table Area End -->
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</center>
<!-- Application Parts End -->


<%-- **** Task specific area ends here **** --%>
