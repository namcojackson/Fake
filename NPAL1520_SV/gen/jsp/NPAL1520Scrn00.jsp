<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230510161910 --%>
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
		<input type="hidden" name="pageID" value="NPAL1520Scrn00">
		<!-- Set Page Name -->
		<input type="hidden" name="pageName" value="Min-Max Planning Inquiry">

		<center>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<%-- ######################################## HEADER AREA ######################################## --%>
						<%-- =============== TAB HEADER of Plan Inq  =============== --%>
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
					<ezf:skip>
						<div class="pTab_HEAD">
							<ul>
								<li class="pTab_ON">
										<a>Plan Inq</a>
								</li>
							</ul>
						</div>
					</ezf:skip>
						<%-- =============== TAB BODY of Plan Inq  =============== --%>
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
                                    <ezf:select name="srchOptPk_SL" ezfName="srchOptPk_SL" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('OnChange_SearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
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
							<%-- ============================== Search Area 2 ============================== --%>

						    <table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px" border="0">
                                <col width="460"  align="left">
                                <col width="484"  align="left">
                                <col align="right">
							    <tr valign="top">
									<%-- ================ Search Area 2 (LEFT) ================ --%>
									<td>
                                        <table border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<col width="120" align="left">
												<col width="80"  align="left">
												<col width="30"  align="center">
												<col width="210" align="left">
											</tr>
											<%-- ---------- Plan Name (*) ---------- --%>
											<tr height="20">
												<td class="stab">Plan Name (*)</td>
												<td colspan="3">
													<ezf:inputText name="mrpPlnNm" ezfName="mrpPlnNm" value="MONROE PLAN" otherAttr=" maxlength=\"70\" style=\"width:295px;\" ezftoupper=\"\""/>
												</td>
											</tr>
											<%-- ---------- Item Number ---------- --%>
											<tr height="20">
												<td class="stab">
													<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdse" >Item Number (*)</ezf:anchor>
												</td>
												<td class="stab">
													<ezf:inputText name="mdseCd" ezfName="mdseCd" value="0000A000AA" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputButton name="SetItemDescription" ezfName="ItemNm" value=">>" />
												</td>
												<td class="stab">
													<ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"250\""/>
												</td>
											</tr>
											<%-- ---------- WH Type ---------- --%>
											<tr height="20">
												<td class="stab">WH Type</td>
												<td colspan="3">
													<ezf:select name="rtlWhCatgCd_SL" ezfName="rtlWhCatgCd_SL" ezfBlank="1" ezfCodeName="rtlWhCatgCd_PD" ezfDispName="rtlWhCatgDescTxt_PD" otherAttr=" style=\"width:295px;\""/>
												</td>
											</tr>
											<%-- ---------- Warehouse ---------- --%>
											<tr height="20">
												<td class="stab">
													<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" >Warehouse</ezf:anchor>
												</td>
												<td class="stab">
													<ezf:inputText name="rtlWhCd" ezfName="rtlWhCd" value="WWWWWW" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputButton name="SetWarehouseName" ezfName="WhNm" value=">>" />
												</td>
												<td class="stab">
													<ezf:inputText name="rtlWhNm_W1" ezfName="rtlWhNm_W1" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"30\""/>
												</td>
											</tr>
											<%-- ---------- Sub Warehouse ---------- --%>
											<tr height="20">
												<td class="stab">
													<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" >Sub Warehouse</ezf:anchor>
												</td>
												<td class="stab">
													<ezf:inputText name="rtlSwhCd" ezfName="rtlSwhCd" value="WWW" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputButton name="SetSubWarehouseName" ezfName="SwhNm" value=">>" />
												</td>
												<td class="stab">
													<ezf:inputText name="rtlSwhNm_S1" ezfName="rtlSwhNm_S1" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"30\""/>
												</td>
											</tr>
										</table>
									</td>
									<%-- ================ Search Area 2 (RIGHT) ================ --%>
									<td>
                                        <table border="0" cellpadding="0" cellspacing="0" >
											<tr>
												<col width="120px" align="left">
												<col width="80px"  align="left">
												<col width="30px"  align="center">
												<col width="210px" align="left">
											</tr>
											<%-- ---------- Manager ---------- --%>
											<tr height="20">
												<td class="stab">
													<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_Manager" >Manager</ezf:anchor>
												</td>
												<td class="stab">
													<ezf:inputText name="whMgrPsnCd" ezfName="whMgrPsnCd" value="WWWWWW" otherAttr=" size=\"16\" maxlength=\"8\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputButton name="SetManagerName" ezfName="WhMgrNm" value=">>" />
												</td>
												<td class="stab">
													<ezf:inputText name="fullPsnNm" ezfName="fullPsnNm" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"62\""/>
												</td>
											</tr>
											<%-- ---------- Source Type ---------- --%>
											<tr height="20">
												<td class="stab">Source Type</td>
												<td colspan="3">
													<ezf:select name="procrTpCd_SL" ezfName="procrTpCd_SL" ezfBlank="1" ezfCodeName="procrTpCd_PD" ezfDispName="procrTpDescTxt_PD" otherAttr=" style=\"width:295px;\""/>
												</td>
											</tr>
											<%-- ---------- Source Warehouse ---------- --%>
											<tr height="20">
												<td class="stab">
													<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SourceWarehouse" >Source Warehouse</ezf:anchor>
												</td>
												<td class="stab">
													<ezf:inputText name="srcRtlWhCd" ezfName="srcRtlWhCd" value="WWWWWW" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputButton name="SetSourceWarehouseName" ezfName="SrcWhNm" value=">>" />
												</td>
												<td class="stab">
													<ezf:inputText name="rtlWhNm_W2" ezfName="rtlWhNm_W2" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"30\""/>
												</td>
											</tr>
											<%-- ---------- Source Sub Warehouse ---------- --%>
											<tr height="20">
												<td class="stab">
													<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SourceSubWarehouse" >Source Sub Warehouse</ezf:anchor>
												</td>
												<td class="stab">
													<ezf:inputText name="srcRtlSwhCd" ezfName="srcRtlSwhCd" value="WWW" otherAttr=" size=\"16\" maxlength=\"20\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputButton name="SetSourceSubWarehouseName" ezfName="SrcSwhNm" value=">>" />
												</td>
												<td class="stab">
													<ezf:inputText name="rtlSwhNm_S2" ezfName="rtlSwhNm_S2" value="XXXXXXXX" otherAttr=" size=\"20\" maxlength=\"30\""/>
												</td>
											</tr>
											<%-- ---------- Enabled Item Only ---------- --%>
											<tr height="20">
												<td class="stab">
													Enabled Item Only
													<ezf:inputCheckBox name="mrpEnblFlg" ezfName="mrpEnblFlg" value="Y" />
												</td>
												<%-- ---------- Include Entered Sales Order ---------- --%>
												<td class="stab" colspan="3">
													Include Entered Sales Order
													<ezf:inputCheckBox name="calcOrdProcCd" ezfName="calcOrdProcCd" value="1" />
												</td>
											</tr>
										</table>
									</td>
									<%-- ---------- Search button ---------- --%>
									<td>
                                        <table border="0" cellpadding="0" cellspacing="0" >
											<%-- ---------- Empty Row. ---------- --%>
											<tr height="20">
												<td></td>
											</tr>
											<%-- ---------- Empty Row. ---------- --%>
											<tr height="20">
												<td></td>
											</tr>
											<%-- ---------- Empty Row. ---------- --%>
											<tr height="20">
												<td></td>
											</tr>
											<%-- ---------- Empty Row. ---------- --%>
											<tr height="20">
												<td></td>
											</tr>
											<tr height="20">
												<td>
													<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
													<ezf:inputButton name="OpenWin_PlanningEntry_New" value="New" htmlClass="pBtn6" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

							<hr>

                            <!-- ######################################## FROM (COMMON)PAGENATION #################################### -->
                            <table style="margin-left: 11px; width: 100%;" >
                                <colgroup>
                                    <col align="left" width="200">
                                    <col align="right" width="900">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td>
                                            <ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
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
                            <!-- ######################################## TO (COMMON)PAGENATION ###################################### -->

                            <!-- ################################################## Detail   - [START] ################################################## -->
                        <div id="parentBoxA">
                            <%-- =============== TABLE AREA =============== --%>
                            <table border="0" cellpadding="0" cellspacing="0" style="margin-left:4px; margin-right:4px;">
                                <%-- =============== TABLE HEADER =============== --%>
                                <tr>
                                    <td valign="top">
                                        <div style="float:left; display:block"> <!-- left table -->
                                            <div id='leftTblHead' style="display:block;">
                                            </div>
                                            <div id='leftTbl' style="float:left; display:block; height:310px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
                                            </div>
                                        </div>  <!-- left table -->
                                        <div style="float:left"> <!-- right table -->
                                            <div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
                                            <%-- ******************************** --%>
                                            <%-- *** Right Table Area(Header) *** --%>
                                            <%-- ******************************** --%>
                                                <table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" height="28" id="AHEAD" width="1956px">
                                                    <col width="40px"  align="center">
                                                    <col width="120px" align="center"><%-- Plan Name --%>
                                                    <col width="108px" align="center"><%-- Item Number --%>
                                                    <col width="160px" align="center"><%-- Item Description  --%>
                                                    <col width="120px" align="center"><%-- WH Category --%>
                                                    <col width="80px"  align="center"><%-- WH Code --%>
                                                    <col width="120px" align="center"><%-- WH Name --%>
                                                    <col width="80px"  align="center"><%-- SWH Code --%>
                                                    <col width="120px" align="center"><%-- SWH Name --%>
                                                    <col width="90px"  align="center"><%-- Manager Code --%>
                                                    <col width="120px" align="center"><%-- Manager Name --%>
                                                    <col width="120px" align="center"><%-- Include Entered Sales Order --%>
                                                    <col width="100px" align="center"><%-- Min Qty --%>
                                                    <col width="100px" align="center"><%-- Max Qty --%>
                                                    <col width="60px"  align="center"><%-- Enabled --%>
                                                    <col width="90px"  align="center"><%-- Source Type --%>
                                                    <col width="80px"  align="center"><%-- Source WH  --%>
                                                    <col width="120px" align="center"><%-- Source WH Name --%>
                                                    <col width="80px"  align="center"><%-- Source SWH --%>
                                                    <col width="120px" align="center"><%-- Source SWH Name --%>
                                                    <tr height="40">
                                                        <td id="AH0" class="pClothBs colFix">&nbsp;</td>
                                                        <td id="AH1" class="pClothBs colFix">Plan Name</td>
                                                        <td id="AH2" class="pClothBs colFix">Item Number</td>
                                                        <td id="AH3" class="pClothBs">Item Description</td>
                                                        <td id="AH4" class="pClothBs">WH Category</td>
                                                        <td id="AH5" class="pClothBs">WH Code</td>
                                                        <td id="AH6" class="pClothBs">WH Name</td>
                                                        <td id="AH7" class="pClothBs">SWH Code</td>
                                                        <td id="AH8" class="pClothBs">SWH Name</td>
                                                        <td id="AH9" class="pClothBs">Manager Code</td>
                                                        <td id="AH10" class="pClothBs">Manager Name</td>
                                                        <td id="AH11" class="pClothBs">Include Entered Sales Order</td>
                                                        <td id="AH12" class="pClothBs">Min Qty</td>
                                                        <td id="AH13" class="pClothBs">Max Qty</td>
                                                        <td id="AH14" class="pClothBs">Enabled</td>
                                                        <td id="AH15" class="pClothBs">Source Type</td>
                                                        <td id="AH16" class="pClothBs">Source WH</td>
                                                        <td id="AH17" class="pClothBs">Source WH Name</td>
                                                        <td id="AH18" class="pClothBs">Source SWH</td>
                                                        <td id="AH19" class="pClothBs">Source SWH Name</td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <%-- ******************************** --%>
                                            <%-- *** Right Table Area(Detail) *** --%>
                                            <%-- ******************************** --%>
                                            <div  id="rightTbl" style="width:1117px; height:300px; display:block; overflow:scroll; margin:0px; padding:0px;" >
                                                <table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1956px">
                                                    <col width="40px"  align="center">
                                                    <col width="120px" align="left"><%-- Plan Name --%>
                                                    <col width="108px" align="left"><%-- Item Number --%>
                                                    <col width="160px" align="center"><%-- Item Description --%>
                                                    <col width="120px" align="center"><%-- WH Category --%>
                                                    <col width="80px"  align="left"><%-- WH Code --%>
                                                    <col width="120px" align="center"><%-- WH Name --%>
                                                    <col width="80px"  align="left"><%-- SWH Code --%>
                                                    <col width="120px" align="center"><%-- SWH Name --%>
                                                    <col width="90px"  align="left"><%-- Manager Code --%>
                                                    <col width="120px" align="center"><%-- Manager Name --%>
                                                    <col width="120px" align="center"><%-- Include Entered Sales Order --%>
                                                    <col width="100px" align="right"><%-- Min Qty --%>
                                                    <col width="100px" align="right"><%-- Max Qty --%>
                                                    <col width="60px"  align="center"><%-- Enabled --%>
                                                    <col width="90px"  align="center"><%-- Source Type --%>
                                                    <col width="80px"  align="left"><%-- Source WH  --%>
                                                    <col width="120px" align="center"><%-- Source WH Name --%>
                                                    <col width="80px"  align="left"><%-- Source SWH --%>
                                                    <col width="120px" align="center"><%-- Source SWH Name --%>
                                                    <ezf:row ezfHyo="A">
                                                        <tr height="28">
                                                            <ezf:inputHidden name="mrpInfoPk_A1" ezfName="mrpInfoPk_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mrpInfoPk_A1\""/>
                                                            <td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn{EZF_ROW_NUMBER}\""/></td>
                                                            <%-- Plan Name --%>
                                                            <td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_PlanningEntry_Plan_Nm" ><ezf:label name="mrpPlnNm_A1" ezfName="mrpPlnNm_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                            <%-- Item Number --%>
                                                            <td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_PlanningEntry_Mdse_Cd" ><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
                                                            <%-- Item Description --%>
                                                            <td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"250\" ezftoupper=\"\""/></td>
                                                            <%-- WH Category --%>
                                                            <td><ezf:inputText name="rtlWhCatgDescTxt_A1" ezfName="rtlWhCatgDescTxt_A1" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
                                                            <%-- WH Code --%>
                                                            <td><ezf:label name="rtlWhCd_A1" ezfName="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- WH Name --%>
                                                            <td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                            <%-- SWH Code --%>
                                                            <td><ezf:label name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- SWH Name --%>
                                                            <td><ezf:inputText name="rtlSwhNm_A1" ezfName="rtlSwhNm_A1" value="XXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                            <%-- Manager Code --%>
                                                            <td><ezf:label name="whMgrPsnCd_A1" ezfName="whMgrPsnCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Manager Name --%>
                                                            <td><ezf:inputText name="fullPsnNm_A1" ezfName="fullPsnNm_A1" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"62\""/></td>
                                                            <%-- Include Entered Sales Order --%>
                                                            <td><ezf:inputCheckBox name="calcOrdProcCd_A1" ezfName="calcOrdProcCd_A1" value="1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Min Qty --%>
                                                            <td><ezf:label name="ropQty_A1" ezfName="ropQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Max Qty --%>
                                                            <td><ezf:label name="maxInvtyQty_A1" ezfName="maxInvtyQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Enabled --%>
                                                            <td><ezf:label name="mrpEnblFlg_A1" ezfName="mrpEnblFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Source Type --%>
                                                            <td><ezf:inputText name="procrTpDescTxt_A1" ezfName="procrTpDescTxt_A1" value="XXXXXXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" maxlength=\"50\""/></td>
                                                            <%-- Source WH  --%>
                                                            <td><ezf:label name="srcRtlWhCd_A1" ezfName="srcRtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Source WH Name --%>
                                                            <td><ezf:inputText name="rtlWhNm_A2" ezfName="rtlWhNm_A2" value="XXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                            <%-- Source SWH --%>
                                                            <td><ezf:label name="srcRtlSwhCd_A1" ezfName="srcRtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
                                                            <%-- Source SWH Name --%>
                                                            <td><ezf:inputText name="rtlSwhNm_A2" ezfName="rtlSwhNm_A2" value="XXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
                                                        </tr>
                                                    </ezf:row>
                                                </table>
                                            </div>
                                        </div><!-- right table -->
                                    </td>
                                </tr>
                            </table>
                        </div><!-- parentBoxA -->
                        <script type="text/javascript" defer>
                            S21TableUI.initialize("parentBoxA", "AHEAD", "A",-1,true,false);
                       </script>
                            <%-- =============== Button Group =============== --%>
                            <table>
                                <col width="1017">
                                <col width="72">
                                <tr>
                                    <td></td>
                                    <td>
                                        <ezf:inputButton name="OpenWin_PlanningEntry_Detail" value="View Detail" htmlClass="pBtn8" />
                                    </td>
                                </tr>
                            </table>
						</div>
					</td>
				</tr>
			</table>
		</center>
			

<%-- **** Task specific area ends here **** --%>
