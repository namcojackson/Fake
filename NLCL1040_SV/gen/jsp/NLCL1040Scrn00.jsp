<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181127142821 --%>
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
			<input type="hidden" name="pageID" value="NLCL1040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Inventory ABC Analysis Setup">

<center>
	<table height="95%" cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
				<%-- ********************** --%>
				<%-- *** Upper Tab Area *** --%>
				<%-- ********************** --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<ezf:skip>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="1070px" height="28px" valign="bottom">
								<table border="0" cellpadding="0" cellspacing="0" class="pTab_UPPER_ON">
									<tr title="ABC Setup">
										<td width="107px" align="center" class="same">ABC Setup</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</ezf:skip>

				<div class="pTab_BODY">
					<!-- ########## Header - [START] ########## -->
					<table border="0"  style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px">
						<col width="440"  align="left">
						<col width="350"  align="left">
						<col width=""  align="left">
						<tr valign="top">
							<td>
								<table border="0" cellpadding="0" cellspacing="0" >
									<col width="110">
									<col width="200">
									<!-- 1 -->
									<tr height="20">
										<td class="stab" >ABC Name</td>
										<td class="stab"><ezf:inputText name="abcAsgNm" ezfName="abcAsgNm" value="WOODRIDGE EQUIPMENTS" otherAttr=" size=\"45\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
									<tr height="20">
										<td class="stab">Description</td>
										<td class="stab"><ezf:inputText name="abcAsgDescTxt" ezfName="abcAsgDescTxt" value="ABC Classification for Items in Woodridge Warehouse" otherAttr=" size=\"45\" maxlength=\"30\""/></td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" >
									<col width="">
									<tr height="20">
										<td>
											<ezf:inputButton name="SearchHeader" value="Search" htmlClass="pBtn6" />
										</td>
									</tr>
									<tr height="20">
										<td>
											<ezf:inputButton name="Import_New" value="Import New" htmlClass="pBtn6" />
											<ezf:inputButton name="Save" value="Save" htmlClass="pBtn6" />
											<ezf:inputButton name="Analyze" value="Analyze" htmlClass="pBtn6" />
											<ezf:inputButton name="Delete" value="Delete" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="border-left:silver 1px solid">
									<col width="110">
									<col width="">
									<!-- 1 -->
									<tr height="20">
										<td class="stab" style="padding-left: 3px;">Analysis Status</td>
										<td class="stab"><ezf:inputText name="abcAnlsRqstStsDescTxt" ezfName="abcAnlsRqstStsDescTxt" value="In Process" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/>
											<ezf:inputButton name="Refresh" value="Refresh" htmlClass="pBtn3" /></td>
									</tr>
									<tr height="20">
										<td class="stab" style="padding-left: 3px;">Last Analysis</td>
										<td class="stab"><ezf:inputText name="xxScrItem30Txt" ezfName="xxScrItem30Txt" value="04/06/2016 14:30" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr>
					<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px;margin-bottom:-10px;">
						<col width="400">
						<col width="270">
						<col width="400">
						<tr valign="top">
							<td>
								<table border="0" cellpadding="0" cellspacing="0" >
									<col width="110">
									<col width="">
									<tr height="20">
										<td class="stab">Warehouse Type</td>
										<td class="stab">
											<ezf:select name="rtlWhCatgCd_H1" ezfName="rtlWhCatgCd_H1" ezfBlank="1" ezfCodeName="rtlWhCatgCd_L1" ezfDispName="rtlWhCatgDescTxt_L1" otherAttr=" style=\"width:200px\" id=\"rtlWhCatgCd_H1\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab"><ezf:anchor name="xxLinkAncr_W" ezfName="xxLinkAncr_W" ezfEmulateBtn="1" ezfGuard="OpenWin_Warehouse" otherAttr=" id=\"xxLinkAncr_W\"">Warehouse</ezf:anchor></td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" >
												<tr>
													<td class="stab">
														<ezf:inputText name="rtlWhCdSrchTxt_RW" ezfName="rtlWhCdSrchTxt_RW" value="3Y1" otherAttr=" size=\"8\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlWhCdSrchTxt_RW\""/>
														<ezf:inputButton name="Search_Warehouse" value=">>" htmlClass="pBtn0" />
														<ezf:inputText name="rtlWhNmSrchTxt_RW" ezfName="rtlWhNmSrchTxt_RW" value="WOODRIDGE WH- CSA" otherAttr=" size=\"25\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlWhNmSrchTxt_RW\""/></td>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr height="20">
										<td class="stab" ><ezf:anchor name="xxLinkAncr_SW" ezfName="xxLinkAncr_SW" ezfEmulateBtn="1" ezfGuard="OpenWin_SubWarehouse" otherAttr=" id=\"xxLinkAncr_SW\"">Sub Warehouse</ezf:anchor></td>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" >
												<tr>
													<td class="stab">
														<ezf:inputText name="rtlSwhCdSrchTxt_SW" ezfName="rtlSwhCdSrchTxt_SW" value="NEW, U90" otherAttr=" size=\"39\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlSwhCdSrchTxt_SW\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Item Type</td>
										<td class="stab">
											<ezf:select name="mdseItemTpCd_H1" ezfName="mdseItemTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="mdseItemTpDescTxt_L1" otherAttr=" style=\"width:200px\" id=\"mdseItemTpCd_H1\""/>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table>
									<tr height="15">
										<td class="stab">
											Stock Status
										</td>
									</tr>
									<tr>
										<td valign="top">
											<div id="Stock Status" style="overflow-y:scroll; height:90;" > 
											<table border="1" cellpadding="0" cellspacing="0" id="S">
											<ezf:row ezfHyo="S">
											<tr>
												<td class="stab">
													<ezf:inputCheckBox name="xxChkBox_SS" ezfName="xxChkBox_SS" value="Y" ezfHyo="S" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SS{EZF_ROW_NUMBER}\""/>
													<ezf:inputHidden name="stkStsCd_SS" ezfName="stkStsCd_SS" value="1" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" ezftoupper=\"\""/>
												</td>
												<td class="stab"><ezf:inputText name="xxScrItem61Txt_SS" ezfName="xxScrItem61Txt_SS" value="1:Good" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/>
												</td>
											</tr>
											</ezf:row>
											</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" style="border-left:silver 1px solid">
									<col width="135">
									<col width="">
									<tr height="25">
										<td style="padding-left: 3px;" class="stab">Analysis Criteria</td>
										<td class="stab">
											<ezf:select name="abcAnlsCritCd_H1" ezfName="abcAnlsCritCd_H1" ezfCodeName="abcAnlsCritCd_L1" ezfDispName="abcAnlsCritDescTxt_L1" otherAttr=" style=\"width:300px\" id=\"abcAnlsCritCd_H1\""/>
										</td>
									</tr>
									<tr height="25">
										<td style="padding-left: 3px;" class="stab">From Date</td>
										<td class="stab">
											<ezf:inputText name="effFromDt" ezfName="effFromDt" value="07/09/2015" otherAttr=" maxlength=\"10\" size=\"12\" id=\"effFromDt\""/>
											<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);"/>
										</td>
									</tr>
									<tr height="25">
										<td style="padding-left: 3px;" class="stab">To Date</td>
										<td class="stab">
											<ezf:inputText name="effThruDt" ezfName="effThruDt" value="07/09/2015" otherAttr=" maxlength=\"10\" size=\"12\" id=\"effThruDt\""/>
											<IMG src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);"/>
										</td>
									</tr>
									<tr height="25">
										<td style="padding-left: 3px;" class="stab">ABC Class Name</td>
										<td class="stab">
											<ezf:select name="abcAnlsClsNum_H1" ezfName="abcAnlsClsNum_H1" ezfCodeName="abcAnlsClsNum_L1" ezfDispName="abcAnlsClsNm_L1" otherAttr=" style=\"width:300px\" id=\"abcAnlsClsNum_H1\""/>
										</td>
									</tr>
									<tr height="25">
										<td>&nbsp;</td>
										<td class="stab">
											<ezf:inputButton name="OpenWin_EditClass" value="ABC Class" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<!-- ########## HEADER - [E N D] ########## -->
					<hr>
					<!-- ########## DETAIL - [START] ########## -->
					<table style="margin-left: 11px; width: 1110; margin-bottom:0px; margin-top:0px;" border="0">
						<col align="left"  width="450">
						<col align="right" width="600">
						<tr height="20">
							<!-- ##### Item Search [START] ##### -->
							<td>
								<table cellSpacing="0" cellPadding="0" border="0">
									<col width="10">
									<col width="210">
									<col width="90">
									<col width="100">
									<col width="10">
									<col width="30">
										<tr>
											<td></td>
											<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
											<td class="stab"><ezf:anchor name="xxLinkAncr_I" ezfName="xxLinkAncr_I" ezfEmulateBtn="1" ezfGuard="OpenWin_Item" otherAttr=" id=\"xxLinkAncr_I\"">Item Number</ezf:anchor></td>
											<td class="stab"><ezf:inputText name="mdseCd" ezfName="mdseCd" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
											<td></td>
											<td class="stab"><ezf:inputButton name="ItemSearch" value="Search" htmlClass="pBtn5" /></td>
										</tr>
								</table>
							</td>
							<!-- ##### Item Search [E N D] ##### -->
							<!-- ###### (COMMON) PAGINATION [START] ##### -->
							<td align="right">
								<ezf:skip>
								<table border="0" cellpadding="0" width="">
									<col width="250">
									<col width="350">
									<tr height="20">
										<td align="right">
											<table border="0" cellpadding="0" align="left" cellspacing="0"  width="200">
												<col width="250" align="right">
													<tr>
														<td class="stab">Results 1000 - 1000 of 1000</td>
													</tr>
											</table>
										</td>
										<td align="right">
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="60" align="center">
												<col width="10" align="center">
												<col width="50" align="center">
												<col width="10">
												<col width="10" align="center">
												<col width="10">
												<col width="40" align="center">
												<col width="10">
												<col width="30" align="center">
												<col width="10">
												<col width="30" align="center">
												<col width="10">
												<col width="30" align="center">
												<col width="10">
												<col width="30" align="center">
												<tr>
													<td class="stab">Showing</td>
													<td></td>
													<td class="stab"><input type="text"  name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
													<td></td>
													<td class="stab">/</td>
													<td></td>
													<td class="stab" ><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
													<td></td>
													<td class="stab">page</td>
													<td></td>
													<td class="stab" ><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td class="stab" ><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
													<td></td>
													<td class="stab"><input type="button"  class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
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
												<jsp:param name="beanId"         value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"        value="A" />
												<jsp:param name="ShowingFrom"    value="xxPageShowFromNum_A" />
												<jsp:param name="ShowingTo"      value="xxPageShowToNum_A" />
												<jsp:param name="ShowingOf"      value="xxPageShowOfNum_A" />
												<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_A" />
												<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_A" />
												<jsp:param name="ViewMode"       value="FULL" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
							<!-- ###### (COMMON) PAGINATION [E N D] ##### -->
						</tr>
					</table>
					<!-- ###### ABC ANALYSIS RESULT DETAIL - [START] ##### -->
					<div id="parentBoxA">
						<table border="1">
							<tr>
								<td valign="Top" width="1132">
									<div style="float:left; display:block"> <!-- left table -->
										<div id='leftTblHead' style="display:block">
										</div>
										<div id='leftTbl' style="float:left; display:block; height:235; overflow:hidden; margin:0px; padding:0px; padding-bottom: 0px">
										</div>
									</div>  <!-- left table -->
									<%-- LEFT-TABLE(TOP) --%>
									<div style="float:left"> <!-- right table -->
										<div id='rightTblHead' style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed" id="AHEAD" width="3815px" style="margin-right:20px">
												<col width="38"  align="center">	<!-- checkbox -->
												<col width="200" align="center">	<!-- Item Number -->
												<col width="200" align="center">	<!-- Item Description -->
												<col width="200" align="center">	<!-- Warehouse Type -->
												<col width="80"  align="center">	<!-- Warehouse Code -->
												<col width="200" align="center">	<!-- Warehouse Name -->
												<col width="100" align="center">	<!-- Sub Warehouse -->
												<col width="80"  align="center">	<!-- Stock Status-->
												<col width="100" align="center">	<!-- Current On Hand Quantity -->
												<col width="100" align="center">	<!-- Current On Hand Value -->
												<col width="100" align="center">	<!-- Historical Usage Qty -->
												<col width="100" align="center">	<!-- Historical Usage Value -->
												<col width="100" align="center">	<!-- Historical Usage Transactions -->
												<col width="100" align="center">	<!-- ABC Class Tag -->
												<tr height="40">
													<td id="AH0"  class="pClothBs">       <a href="#" class="pSortCol" id="xxChkBox_A"></td>
													<td id="AH1"  class="pClothBs">       <a href="#" class="pSortCol" id="mdseCd_A"               onclick="columnSort( 'A', 'mdseCd_A' )" >Item Number<img id="sortIMG.mdseCd_A"                                            border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"  class="pClothBs">       <a href="#" class="pSortCol" id="mdseDescShortTxt_A"     onclick="columnSort( 'A', 'mdseDescShortTxt_A' )" >Item Description<img id="sortIMG.mdseDescShortTxt_A"                   border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs">       <a href="#" class="pSortCol" id="rtlWhCatgCd_A"          onclick="columnSort( 'A', 'rtlWhCatgCd_A' )" >Warehouse Type<img id="sortIMG.rtlWhCatgCd_A"                               border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs">       <a href="#" class="pSortCol" id="rtlWhCd_A"              onclick="columnSort( 'A', 'rtlWhCd_A' )" >Warehouse<img id="sortIMG.rtlWhCd_A"                                            border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5"  class="pClothBs">       <a href="#" class="pSortCol" id="rtlWhNm_A"              onclick="columnSort( 'A', 'rtlWhNm_A' )" >Warehouse Name<img id="sortIMG.rtlWhNm_A"                                       border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6"  class="pClothBs">       <a href="#" class="pSortCol" id="rtlSwhCd_A"             onclick="columnSort( 'A', 'rtlSwhCd_A' )" >Sub Warehouse<img id="sortIMG.rtlSwhCd_A"                                      border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7"  class="pClothBs">       <a href="#" class="pSortCol" id="stkStsCd_A"             onclick="columnSort( 'A', 'stkStsCd_A' )" >Stock Status<img id="sortIMG.stkStsCd_A"                                       border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH8"  class="pClothBs">       <a href="#" class="pSortCol" id="curInvtyQty_A"          onclick="columnSort( 'A', 'curInvtyQty_A' )" >Current On Hand Quantity<img id="sortIMG.curInvtyQty_A"                     border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH9"  class="pClothBs">       <a href="#" class="pSortCol" id="curInvtyCostAmt_A"      onclick="columnSort( 'A', 'curInvtyCostAmt_A' )" >Current On Hand Value<img id="sortIMG.curInvtyCostAmt_A"                border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH10" class="pClothBs">       <a href="#" class="pSortCol" id="histInvtyTrxQty_A"      onclick="columnSort( 'A', 'histInvtyTrxQty_A' )" >Historical Usage Qty<img id="sortIMG.histInvtyTrxQty_A"                 border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH11" class="pClothBs">       <a href="#" class="pSortCol" id="histInvtyTrxCostAmt_A"  onclick="columnSort( 'A', 'histInvtyTrxCostAmt_A' )" >Historical Usage Value<img id="sortIMG.histInvtyTrxCostAmt_A"       border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH12" class="pClothBs">       <a href="#" class="pSortCol" id="histInvtyTrxRecCnt_A"   onclick="columnSort( 'A', 'histInvtyTrxRecCnt_A' )" >Historical Usage Transactions<img id="sortIMG.histInvtyTrxRecCnt_A"  border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH13" class="pClothBs">       <a href="#" class="pSortCol" id="abcAnlsClsTagCd_A"      onclick="columnSort( 'A', 'abcAnlsClsTagCd_A' )" >ABC Class Tag<img id="sortIMG.abcAnlsClsTagCd_A"                        border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div><!-- rightTblHead -->
										
										<div id="rightTbl" style="width:1117px; height:252; display:block; overflow:scroll; margin:0px; padding:0px;">
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="3815px" >
												<col width="38"  align="center">	<!-- checkbox -->
												<col width="200" align="center">	<!-- Item Number -->
												<col width="200" align="center">	<!-- Item Description -->
												<col width="200" align="center">	<!-- Warehouse Type -->
												<col width="80"  align="center">	<!-- Warehouse Code -->
												<col width="200" align="center">	<!-- Warehouse Name -->
												<col width="100" align="center">	<!-- Sub Warehouse -->
												<col width="80"  align="center">	<!-- Stock Status-->
												<col width="100" align="center">	<!-- Current On Hand Quantity -->
												<col width="100" align="center">	<!-- Current On Hand Value -->
												<col width="100" align="center">	<!-- Historical Usage Qty -->
												<col width="100" align="center">	<!-- Historical Usage Value -->
												<col width="100" align="center">	<!-- Historical Usage Transactions -->
												<col width="100" align="center">	<!-- ABC Class Tag -->
												<ezf:row ezfHyo="A">
												<tr height="24" id="id_leftA_row{EZF_ROW_NUMBER}">
													<td class="stab"><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab">
														<ezf:inputButton name="OpenWin_ItemDetail" value="Item" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"OpenWin_ItemDetail\""/>
														<ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"16\" id=\"mdseCd_A{EZF_ROW_NUMBER}\" size=\"15\" ezftoupper=\"\""/>
														<ezf:inputButton name="Search_Item" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn2" otherAttr=" id=\"Search_Item\""/>
													</td>
													<td class="stab"><ezf:inputText name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseDescShortTxt_A{EZF_ROW_NUMBER}\" size=\"26\""/></td>
													<td class="stab">
														<ezf:select name="rtlWhCatgCd_A" ezfName="rtlWhCatgCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="rtlWhCatgCd_L2" ezfDispName="rtlWhCatgDescTxt_L2" otherAttr=" id=\"rtlWhCatgCd_A\" style=\"width:180\""/>
													</td>
													<td class="stab">
														<ezf:select name="rtlWhCd_A" ezfName="rtlWhCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="rtlWhCd_L2" ezfDispName="rtlWhDescTxt_L2" otherAttr=" id=\"rtlWhCd_A\" style=\"width:70\""/>
													</td>
													
													<td class="stab"><ezf:inputText name="rtlWhNm_A" ezfName="rtlWhNm_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" id=\"rtlWhNm_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab">
														<ezf:select name="rtlSwhCd_A" ezfName="rtlSwhCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="rtlSwhCd_L2" ezfDispName="rtlSwhDescTxt_L2" otherAttr=" id=\"rtlSwhCd_A\" style=\"width:95\""/>
													</td>
													<td class="stab">
														<ezf:select name="stkStsCd_A" ezfName="stkStsCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="stkStsCd_L2" ezfDispName="stkStsDescTxt_L2" otherAttr=" id=\"stkStsCd_A\" style=\"width:70\""/>
													</td>
													<td class="stab"><ezf:inputText name="curInvtyQty_A" ezfName="curInvtyQty_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"curInvtyQty_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab"><ezf:inputText name="curInvtyCostAmt_A" ezfName="curInvtyCostAmt_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"curInvtyCostAmt_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab"><ezf:inputText name="histInvtyTrxQty_A" ezfName="histInvtyTrxQty_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"histInvtyTrxQty_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab"><ezf:inputText name="histInvtyTrxCostAmt_A" ezfName="histInvtyTrxCostAmt_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"histInvtyTrxCostAmt_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab"><ezf:inputText name="histInvtyTrxRecCnt_A" ezfName="histInvtyTrxRecCnt_A" value="A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" id=\"histInvtyTrxRecCnt_A{EZF_ROW_NUMBER}\""/></td>
													<td class="stab">
														<ezf:select name="abcAnlsClsTagCd_A" ezfName="abcAnlsClsTagCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="abcAnlsClsTagCd_L2" ezfDispName="abcAnlsClsTagCd_L3" otherAttr=" id=\"abcAnlsClsTagCd_A\" style=\"width:80\""/>
													</td>
												</tr>
												</ezf:row>
											</table>
										</div><!-- rightTbl -->
									</div> <!-- right table -->
								</td>
							</tr>
						</table>
					</div><!-- parentBoxA -->
					<!-- ###### ABC ANALYSIS RESULT DETAIL - [E N D] ##### -->
					<!-- ###### ABC ANALYSIS RESULT EDIT   - [START] ##### -->
					<table style="margin-left: 11px; width: 100%;" >
						<tr height="20">
							<td align="left">
								<ezf:inputButton name="EditTag" value="Edit Tag" htmlClass="pBtn6" />
								<ezf:inputButton name="AddTag" value="Add Line" htmlClass="pBtn6" />
								<ezf:inputButton name="DeleteTag" value="Delete Line" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
					<!-- ###### ABC ANALYSIS RESULT EDIT - [E N D] ##### -->
					<!-- ########## DETAIL - [E N D] ########## -->
				</div><!-- pTab_BODY -->
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, false, true);
</script>


<%-- **** Task specific area ends here **** --%>
