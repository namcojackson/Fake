<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230407082120 --%>
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
			<input type="hidden" name="pageID" value="NLCL0280Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Inventory Transaction Inqiury">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<!-- #################################################### HEADER ################################################### -->
				<!-- ###TAB - HEAD -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Inventory Transaction Inqiury" class="pTab_ON"><a href="#">Invty Trx</a></li>
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
				<!-- ###TAB - BODY -->
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
                                    <ezf:select name="srchOptPk_PS" ezfName="srchOptPk_PS" ezfBlank="1" ezfCodeName="srchOptPk_PD" ezfDispName="srchOptNm_PD" otherEvent1=" onchange=\"sendServer('Select_Search')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
                                </td>
                                <td class="stab">Search Option Name</td>
                                <td class="stab"><ezf:inputText name="srchOptNm_H1" ezfName="srchOptNm_H1" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
                                <td>
                                    <ezf:inputButton name="Save_Search" value="Save Search" htmlClass="pBtn8" />
                                    <ezf:inputButton name="Delete_Search" value="Delete Search" htmlClass="pBtn8" />
                                </td>
                            </tr>
                        </table>
					<!-- Search Header -->
					<hr style="height: 0px;" cellpadding="0">
					<table border="0" cellpadding="0" cellspacing="0" height="">
						<tr>
							<td valign="top">
								<table cellpadding="0" height="178">
									<col align="left" width="10">
									<col align="left" width="140">
									<col align="left" width="110">
									<col align="left" width="10">
									<col align="left" width="130">
									<tr height="20">
										<td rowspan="9">&nbsp;</td>
										<td class="stab">Transaction Date</td>
										<td>
											<ezf:inputText name="trxDt_H1" ezfName="trxDt_H1" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'trxDt_H1', 4 );">
										</td>
										<td class="stab">-</td>
										<td>
											<ezf:inputText name="trxDt_H2" ezfName="trxDt_H2" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'trxDt_H2', 4 );">
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Transaction ID</td>
										<td colspan="3">
											<ezf:inputText name="invtyTrxPk_H1" ezfName="invtyTrxPk_H1" value="xx" otherAttr=" size=\"31\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Transaction Type</td>
										<td colspan="3">
											<ezf:select name="invtyTrxTpCd_PS" ezfName="invtyTrxTpCd_PS" ezfBlank="1" ezfCodeName="invtyTrxTpCd_PD" ezfDispName="invtyTrxTpDescTxt_PD" otherEvent1=" onchange=\"sendServer('Select_TrxTp')\"" otherAttr=" style=\"width:223px\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Transaction Code</td>
										<td colspan="3">
											<ezf:select name="trxCd_PS" ezfName="trxCd_PS" ezfBlank="1" ezfCodeName="trxCd_PD" ezfDispName="xxScrItem61Txt_TX" otherEvent1=" onchange=\"sendServer('SelectTrx')\"" otherAttr=" style=\"width:223px\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Transaction Reason Code</td>
										<td colspan="3">
											<ezf:select name="trxRsnCd_PS" ezfName="trxRsnCd_PS" ezfBlank="1" ezfCodeName="trxRsnCd_PD" ezfDispName="xxScrItem61Txt_TR" otherAttr=" style=\"width:223px\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Source Document Number</td>
										<td colspan="3">
											<!-- INVTY_TRX_SLP_NUM -->
											<ezf:inputText name="invtyTrxSlpNum_H1" ezfName="invtyTrxSlpNum_H1" value="xx" otherAttr=" size=\"31\" maxlength=\"15\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">RWS Number</td>
										<td colspan="3">
											<ezf:inputText name="rwsNum_H1" ezfName="rwsNum_H1" value="xx" otherAttr=" size=\"31\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Shipping Order Number</td>
										<td colspan="3">
											<ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="xx" otherAttr=" size=\"31\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Serial</td>
										<td colspan="3">
											<ezf:inputText name="serNum_H1" ezfName="serNum_H1" value="xx" otherAttr=" size=\"31\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
									</tr>
								</table>
							</td>

							<td valign="top">
								<table cellpadding="0" >
									<col align="left" width="120">
									<col align="left" width="60">
									<col align="left" width="34">
									<col align="left" width="102">
									<tr height="20">
										<td class="stab">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseInfo" >Item Number</ezf:anchor>
										</td>
										<td colspan="3">
											<ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="xx" otherAttr=" size=\"24\" maxlength=\"16\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">
											<ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_MdseInfo" >Item Description(*)</ezf:anchor>
										</td>
										<td colspan="3">
											<ezf:inputText name="mdseDescShortTxt_H1" ezfName="mdseDescShortTxt_H1" value="xx" otherAttr=" size=\"24\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Item Type</td>
										<td colspan="3">
											<ezf:select name="mdseItemTpCd_PS" ezfName="mdseItemTpCd_PS" ezfBlank="1" ezfCodeName="mdseItemTpCd_PD" ezfDispName="xxScrItem61Txt_MT" otherAttr=" style=\"width:174px\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Merchandise Type</td>
										<td colspan="3">
											<ezf:select name="coaProjCd_PS" ezfName="coaProjCd_PS" ezfBlank="1" ezfCodeName="coaProjCd_PD" ezfDispName="xxScrItem61Txt_PC" otherAttr=" style=\"width:174px\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ProdInfo" >Product</ezf:anchor></td>
										<td><ezf:inputText name="coaProdCd_H1" ezfName="coaProdCd_H1" value="XX" otherAttr=" size=\"7\" maxlength=\"8\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ProdInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="coaProdDescTxt_H1" ezfName="coaProdDescTxt_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr height="20">
										<td class="stab">Cross Reference Type</td>
										<td colspan="3">
											<ezf:select name="mdseItemRelnTpCd_PS" ezfName="mdseItemRelnTpCd_PS" ezfBlank="1" ezfCodeName="mdseItemRelnTpCd_PD" ezfDispName="mdseItemRelnTpDescTxt_PD" otherEvent1=" onchange=\"sendServer('Select_MdseItemRelnTp')\"" otherAttr=" style=\"width:174px\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Cross Reference Item</td>
										<td colspan="3">
											<ezf:inputText name="relnMdseCd_H1" ezfName="relnMdseCd_H1" value="xx" otherAttr=" size=\"24\" maxlength=\"16\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Supplier Item</td>
										<td colspan="3">
											<ezf:inputText name="splyItemNum_H1" ezfName="splyItemNum_H1" value="xx" otherAttr=" size=\"24\" maxlength=\"50\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Manufacturer Item</td>
										<td colspan="3">
											<ezf:inputText name="mnfItemCd_H1" ezfName="mnfItemCd_H1" value="xx" otherAttr=" size=\"24\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
									</tr>
								</table>
							</td>

							<td valign="top" width="">
								<table cellpadding="0" width="">
									<col align="left" width="110">
									<col align="left" width="80">
									<col align="left" width="34">
									<col align="left" width="125">
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfo" >Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="12345678" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="123456789012345" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfo" >Sub Warehouse</ezf:anchor></td>
										<td><ezf:inputText name="rtlSwhCd_H1" ezfName="rtlSwhCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_RtlSWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="rtlSwhNm_H1" ezfName="rtlSwhNm_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_VndCode" >Supplier Site</ezf:anchor></td>
										<td><ezf:inputText name="vndCd_H1" ezfName="vndCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_VndInfo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="locNm_H1" ezfName="locNm_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"60\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipLocCodeFrom" >Ship From Location</ezf:anchor></td>
										<td><ezf:inputText name="shipFromLocCustCd_H1" ezfName="shipFromLocCustCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipLocInfoFrom" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="dsAcctNm_H1" ezfName="dsAcctNm_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"360\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipLocCodeTo" >Ship To Location</ezf:anchor></td>
										<td><ezf:inputText name="shipToLocCustCd_H1" ezfName="shipToLocCustCd_H1" value="XX" otherAttr=" size=\"10\" maxlength=\"20\" ezftoupper=\"\""/></td>
										<td><ezf:inputButton name="Search_ShipLocInfoTo" value=">>" htmlClass="pBtn0" /></td>
										<td><ezf:inputText name="dsAcctNm_H2" ezfName="dsAcctNm_H2" value="XX" otherAttr=" size=\"16\" maxlength=\"360\" tabindex=\"-1\" ezftoupper=\"\""/><td>
									</tr>
									<tr height="20">
										<td>Location Status</td>
										<td rowspan="3" colspan="3" valign="top">
											<div id="Location Status" style="width:237; overflow-x:hidden; overflow-y:scroll; height:60;">
											<table border="1" cellpadding="1" cellspacing="0" id="L">
												<ezf:row ezfHyo="L">
												<tr>
													<td>
														<ezf:inputCheckBox name="xxChkBox_LS" ezfName="xxChkBox_LS" value="Y" ezfHyo="L" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_LS{EZF_ROW_NUMBER}\""/>
														<ezf:inputHidden name="locStsCd_LS" ezfName="locStsCd_LS" value="1" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" ezftoupper=\"\""/>
													</td>
													<td><ezf:inputText name="xxScrItem61Txt_LS" ezfName="xxScrItem61Txt_LS" value="01:In-Transit (PO)" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="03:DC Stock" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="07:WIP (Kit)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="08:WIP (Un-Kit)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="11:Loan" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="15:Waiting for Delivery" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="16:In-Transit (WH Transfer)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="20:WIP (Reman)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
									<tr height="20">
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td class="stab" colspan="4" align="right" style="padding-right: 5px;">
											<ezf:inputButton name="Search" value="Search" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr>
<!-- #################################################### DETAIL ################################################### -->
					<table cellpadding="0" cellspacing="0">
						<col width="003">
						<col width="200">
						<col width="345">
						<col width="555">
						<tr>
							<td></td>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td></td>
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="0" width="100%">
									<tr>
										<td align="left">
											<table border="0" cellpadding="0" align="left" cellspacing="0">
												<col>
													<tr>
													<td>Results 1000 - 1000 of 1000</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="center">
												<col width="16"  align="center">
												<col width="40"  align="center">
												<col width="26"  align="center">
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
											<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
												<jsp:param name="beanId"            value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"           value="A" />
												<jsp:param name="ShowingFrom"       value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"         value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"         value="xxPageShowOfNum" />
												<jsp:param name="ShowingCurrent"    value="xxPageShowCurNum" />
												<jsp:param name="ShowingTotal"      value="xxPageShowTotNum" />
												<jsp:param name="ViewMode"          value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- Preferred View -->
					<div id="parentBoxA">
						<table border="0">
							<tr>
								<td valign="Top" width="1132">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block;"></div>
										<div id='leftTbl' style="float:left; display:block; height:233px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="4660px" style="table-layout: fixed">
												<col align="center" width="80">		<!-- 	WH Code	 -->
												<col align="center" width="160">	<!-- 	WH Name	 -->
												<col align="center" width="80">		<!-- 	SWH	 -->
												<col align="center" width="100">	<!-- 	Item Numebr	 -->
												<col align="center" width="160">	<!-- 	Item Description	 -->
												<col align="center" width="180">	<!-- 	Merchandise Type	 -->
												<col align="center" width="150">	<!-- 	Item Type	 -->
												<col align="center" width="120">	<!-- 	Product	 -->
												<col align="center" width="120">	<!-- 	Xref Item	 -->
												<col align="center" width="120">	<!-- 	Supplier Item	 -->
												<col align="center" width="120">	<!-- 	Manuf. Item	 -->
												<col align="center" width="120">	<!-- 	Config ID	 -->
												<col align="center" width="120">	<!-- 	Model	 -->
												<col align="center" width="120">	<!-- 	Serial	 -->
												<col align="center" width="120">	<!-- 	Trx ID	 -->
												<col align="center" width="160">	<!-- 	Trx Type	 -->
												<col align="center" width="160">	<!-- 	Trx Code	 -->
												<col align="center" width="160">	<!-- 	Trx Reason	 -->
												<col align="center" width="80">		<!-- 	Trx Qty	 -->
												<col align="center" width="160">	<!-- 	Stock Status	 -->
												<col align="center" width="160">	<!-- 	Location Status	 -->
												<col align="center" width="120">	<!-- 	Trx Source	 -->
												<col align="center" width="100">	<!-- 	Trx Source Line	 -->
												<col align="center" width="100">	<!-- 	SO Number	 -->
												<col align="center" width="100">	<!-- 	RWS Number	 -->
												<col align="center" width="160">	<!-- 	Trx Party	 -->
												<col align="center" width="160">	<!-- 	Trx Date	 -->
												<col align="center" width="160">	<!-- 	Destination Location	 -->
												<col align="center" width="80">		<!-- 	GL Trx	 -->
												<tr height="28">
													<td id="AH1"   class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhCd_A1' )">WH Code<img id="sortIMG.rtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"   class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlWhNm_A1' )">WH Name<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"   class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhCd_A1' )">SWH<img id="sortIMG.rtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"   class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"   class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"   class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_PJ' )">Merchandise Type<img id="sortIMG.xxScrItem61Txt_PJ" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_MD' )">Item Type<img id="sortIMG.xxScrItem61Txt_MD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_PD' )">Product<img id="sortIMG.xxScrItem61Txt_PD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemRelnTpDescTxt_A1' )">Xref Item<img id="sortIMG.mdseItemRelnTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyItemNum_A1' )">Supplier Item<img id="sortIMG.splyItemNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mnfItemCd_A1' )">Manuf. Item<img id="sortIMG.mnfItemCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 't_MdlNm_A1' )">Model<img id="sortIMG.t_MdlNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH14"  class="pClothBs">Serial</td>
													<td id="AH15"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyTrxPk_A1' )">Trx ID<img id="sortIMG.invtyTrxPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH16"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyTrxTpDescTxt_A1' )">Trx Type<img id="sortIMG.invtyTrxTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH17"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_AX' )">Trx Code<img id="sortIMG.xxScrItem61Txt_AX" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH18"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_AR' )">Trx Rsn<img id="sortIMG.xxScrItem61Txt_AR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH19"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyTrxQty_A1' )">Trx Qty<img id="sortIMG.invtyTrxQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH20"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_SS' )">Stck Status<img id="sortIMG.xxScrItem61Txt_SS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH21"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem61Txt_AS' )">Location Status<img id="sortIMG.xxScrItem61Txt_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH22"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyTrxSlpNum_A1' )">Trx Source<img id="sortIMG.invtyTrxSlpNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH23"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dplyLineNum_A1' )">Trx Source Line<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH24"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'soNum_A1' )">SO Number<img id="sortIMG.soNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH25"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rwsNum_A1' )">RWS Number<img id="sortIMG.rwsNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH26"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNm_A1' )">Trx Party<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH27"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxTsDsp19Txt_A1' )">Trx Date<img id="sortIMG.xxTsDsp19Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH28"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToLocCustNm_A1' )">Destination Location<img id="sortIMG.shipToLocCustNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH29"  class="pClothBs">GL Trx</td>
												</tr>
											</table>
										</div>
										<div  id="rightTbl" style="width:1117px; height:250px; display:block; overflow:scroll; margin:0px; padding:0px;">
											<table border="1" cellpadding="0" cellspacing="0" id="A" width="4660px" style="table-layout: fixed">
												<col align="center" width="80">		<!-- 	WH Code	 -->
												<col align="center" width="160">	<!-- 	WH Name	 -->
												<col align="center" width="80">		<!-- 	SWH	 -->
												<col align="center" width="100">	<!-- 	Item Numebr	 -->
												<col align="center" width="160">	<!-- 	Item Description	 -->
												<col align="center" width="180">	<!-- 	Merchandise Type	 -->
												<col align="center" width="150">	<!-- 	Item Type	 -->
												<col align="center" width="120">	<!-- 	Product	 -->
												<col align="center" width="120">	<!-- 	Xref Item	 -->
												<col align="center" width="120">	<!-- 	Supplier Item	 -->
												<col align="center" width="120">	<!-- 	Manuf. Item	 -->
												<col align="center" width="120">	<!-- 	Config ID	 -->
												<col align="center" width="120">	<!-- 	Model	 -->
												<col align="center" width="120">	<!-- 	Serial	 -->
												<col align="center" width="120">	<!-- 	Trx ID	 -->
												<col align="center" width="160">	<!-- 	Trx Type	 -->
												<col align="center" width="160">	<!-- 	Trx Code	 -->
												<col align="center" width="160">	<!-- 	Trx Reason	 -->
												<col align="center" width="80">		<!-- 	Trx Qty	 -->
												<col align="center" width="160">	<!-- 	Stock Status	 -->
												<col align="center" width="160">	<!-- 	Location Status	 -->
												<col align="center" width="120">	<!-- 	Trx Source	 -->
												<col align="center" width="100">	<!-- 	Trx Source Line	 -->
												<col align="center" width="100">	<!-- 	Shippng Order Number	 -->
												<col align="center" width="100">	<!-- 	RWS Number	 -->
												<col align="center" width="160">	<!-- 	Trx Party	 -->
												<col align="center" width="160">	<!-- 	Trx Date	 -->
												<col align="center" width="160">	<!-- 	Destination Location	 -->
												<col align="center" width="80">		<!-- 	GL Trx	 -->
												<ezf:row ezfHyo="A">
													<tr height="28" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
														<td><ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="rtlWhCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlWhCd_A1{EZF_ROW_NUMBER}\" size=\"15\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="rtlWhNm_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlWhNm_A1{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="rtlSwhCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlSwhCd_A1{EZF_ROW_NUMBER}\" size=\"15\" maxlength=\"20\""/></td>
														<td><ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseCd_A1{EZF_ROW_NUMBER}\" size=\"13\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="mdseDescShortTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseDescShortTxt_A1{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"250\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_PJ" ezfName="xxScrItem61Txt_PJ" value="xxScrItem61Txt_PJ" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_PJ{EZF_ROW_NUMBER}\" size=\"24\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_MD" ezfName="xxScrItem61Txt_MD" value="xxScrItem61Txt_MD" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_MD{EZF_ROW_NUMBER}\" size=\"20\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_PD" ezfName="xxScrItem61Txt_PD" value="xxScrItem61Txt_PD" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_PD{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"50\""/></td>
														<td>
															<ezf:anchor name="xxLinkAncr_FI" ezfName="xxLinkAncr_FI" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_FlipItem" otherAttr=" id=\"xxLinkAncr_FI{EZF_ROW_NUMBER}\"">
																<ezf:label name="xxRelnTrgtFlg_A1" ezfName="xxRelnTrgtFlg_A1" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
														<td><ezf:inputText name="splyItemNum_A1" ezfName="splyItemNum_A1" value="0123A001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"splyItemNum_A1{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="mnfItemCd_A1" ezfName="mnfItemCd_A1" value="0123A001AA" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mnfItemCd_A1{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="100000" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcConfigMstrPk_A1{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="t_MdlNm_A1" ezfName="t_MdlNm_A1" value="t_MdlNm" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"t_MdlNm_A1{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"50\""/></td>
														<td>
															<ezf:anchor name="xxLinkAncr_SE" ezfName="xxLinkAncr_SE" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_SerTrxSeach" otherAttr=" id=\"xxLinkAncr_SE{EZF_ROW_NUMBER}\"">
																<ezf:label name="serNumFlg_A1" ezfName="serNumFlg_A1" ezfHyo="A" ezfArrayNo="0" />
															</ezf:anchor>
														</td>
														<td><ezf:inputText name="invtyTrxPk_A1" ezfName="invtyTrxPk_A1" value="9089" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invtyTrxPk_A1{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"30\""/></td>
														<td><ezf:inputText name="invtyTrxTpDescTxt_A1" ezfName="invtyTrxTpDescTxt_A1" value="invtyTrxTpDescTxt" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invtyTrxTpDescTxt_A1{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_AX" ezfName="xxScrItem61Txt_AX" value="xxScrItem61Txt_AX" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_AX{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_AR" ezfName="xxScrItem61Txt_AR" value="xxScrItem61Txt_AR" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_AR{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="invtyTrxQty_A1" ezfName="invtyTrxQty_A1" value="1,000,000,000" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invtyTrxQty_A1{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"13\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_SS" ezfName="xxScrItem61Txt_SS" value="xxScrItem61Txt_SS" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_SS{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="xxScrItem61Txt_AS" ezfName="xxScrItem61Txt_AS" value="xxScrItem61Txt_AS" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_AS{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="invtyTrxSlpNum_A1" ezfName="invtyTrxSlpNum_A1" value="invtyTrxSlpNum" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invtyTrxSlpNum_A1{EZF_ROW_NUMBER}\" size=\"16\" maxlength=\"15\""/></td>
														<td><ezf:label name="dplyLineNum_A1" ezfName="dplyLineNum_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="soNum_A1" ezfName="soNum_A1" value="soNum" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"soNum_A1{EZF_ROW_NUMBER}\" size=\"13\" maxlength=\"15\""/></td>
														<td><ezf:inputText name="rwsNum_A1" ezfName="rwsNum_A1" value="rwsNum" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rwsNum_A1{EZF_ROW_NUMBER}\" size=\"13\" maxlength=\"15\""/></td>
														<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="dsAcctNm" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNm_A1{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"360\""/></td>
														<td><ezf:inputText name="xxTsDsp19Txt_A1" ezfName="xxTsDsp19Txt_A1" value="03/29/2016 02:56:55" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxTsDsp19Txt_A1{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"50\""/></td>
														<td><ezf:inputText name="shipToLocCustNm_A1" ezfName="shipToLocCustNm_A1" value="shipToLocCustNm" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"shipToLocCustNm_A1{EZF_ROW_NUMBER}\" size=\"21\" maxlength=\"360\""/></td>
														<td><label><ezf:inputButton name="Open_JrnlEntrySearch" value="GL Trx" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></label></td>
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
									</div>
								</td>
							</tr>
						</table>
					</div>
					<!-- Preferred View End -->
				</div>
			</td>
		</tr>
	</table>
</center>

<!-- Priferred View -->
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1,true);
</script>


<%-- **** Task specific area ends here **** --%>
