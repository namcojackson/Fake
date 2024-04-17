<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170609103621 --%>
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
			<input type="hidden" name="pageID" value="NPAL0190Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="PO Event History">

<center>
	<table width="95%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="text-align: left;">
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0" style=" margin-left: 5px; margin-top: 10px;">
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
							<tr>
							<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="35">
								<col width="210">
								<tr height="30">
									<td class="stab">PO#</td>
									<td><ezf:inputText name="poOrdNum_H" ezfName="poOrdNum_H" value="12345678" otherAttr=" size=\"28\" maxlength=\"28\""/></td>
									<td class="stab">PO Line#</td>
									<td><ezf:inputText name="dispPoDtlLineNum_H" ezfName="dispPoDtlLineNum_H" value="12345678" otherAttr=" size=\"28\" maxlength=\"28\""/></td>
								</tr>
							</table>
							</td>
							</tr>
							</table>
							<hr>
<%-- ######################################## DETAIL ######################################## --%>
							
							<%-- Pagenation --%>
							<table border="0" cellpadding="0" cellspacing="0">
								<col width="200px">
								<col width="905px">
								<tr>
									<td>
										<table>
											<tr height="20px">
												<td>
													<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
												</td>
											</tr>
										</table>
									</td>
									<td style="text-align: right;">
										<ezf:skip>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="54"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="16"  align="center">
											<col width="40"  align="right">
											<col width="10">
											<col>
											<col width="1">
											<col>
											<tr>
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">10</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
											</tr>
										</table>
										</ezf:skip>
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="top">
							<div id="parentBoxA">
								<div style="float:left; display:block"><!-- left table -->
									<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;"></div>
									<div id="leftTbl" style="float:left; display:block; height:480; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
								</div><!-- left table -->
								<div style="float:left"><!-- right table -->
									<div id="rightTblHead" style="width:1050px; display:block; overflow:hidden; margin:0px;padding:0px;">
										<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD" style="margin-right:20px">
										<%-- ******************************* --%>
										<%-- *** Left Table Area(Header) *** --%>
										<%-- ******************************* --%>
											<col width="189" align="center"><!-- Event -->
											<col width="160" align="center"><!-- Date -->
											<col width="80" align="center"><!-- User ID -->
											<col width="136" align="center"><!-- Header Status -->
											<col width="136" align="center"><!-- Approval Status -->
											<col width="42" align="center"><!-- Line -->
											<col width="136" align="center"><!-- Line Status -->
											<col width="100" align="center"><!-- Item#(Pin) -->
											<col width="125" align="center"><!-- Supplier Item#(Pin) -->
											<col width="240" align="center"><!-- Item Description(Pin) -->
											<col width="105" align="center"><!-- Line Price -->
											<col width="125" align="center"><!-- Order Qty -->
											<col width="125" align="center"><!-- UOM(Pin) -->
											<col width="105" align="center"><!-- Ext. Total(Pin) -->
											<col width="82" align="center"><!-- Date Needed -->
											<col width="60" align="center"><!-- WH -->
											<col width="60" align="center"><!-- SWH -->
											<col width="60"  align="center"><!-- Dest WH(Pin) -->
											<col width="60"  align="center"><!-- Dest SWH(Pin) -->
											<col width="125"  align="center"><!-- Ship To Customer(Pin) -->
											<col width="150"  align="center"><!-- Ship To Customer Name(Pin) -->
											<col width="125"  align="center"><!-- Match Opt -->
											<col width="125" align="center"><!-- Received Qty(Pin) -->
											<col width="125" align="center"><!-- Cancelled Qty(Pin) -->
											<col width="125" align="center"><!-- Invoiced Qty(Pin) -->
											<col width="125" align="center"><!-- From Stock Status(Pin) -->
											<col width="125" align="center"><!-- To Stock Status(Pin) -->
											<col width="160" align="center"><!-- Serial#(Pin) -->
											<col width="340" align="center"><!-- Charge Account -->
											<col width="340" align="center"><!-- Accrual Account -->
											<col width="340" align="center"><!-- Variance Account -->
											<col width="365" align="center"><!-- Line Comment -->
											<tr height="28">
												<td class="pClothBs colFix" id="AH0"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'eventId' )">Event<img id="sortIMG.eventId" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs colFix" id="AH1"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem19Txt' )">Date<img id="sortIMG.xxScrItem19Txt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs colFix" id="AH2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poSubmtPsnCd' )">User ID<img id="sortIMG.poSubmtPsnCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs colFix" id="AH3"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poHdrStsDescTxt' )">Header Status<img id="sortIMG.poHdrStsDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs colFix" id="AH4"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poApvlStsDescTxt' )">Approval Status<img id="sortIMG.poApvlStsDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH5"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'dispPoDtlLineNum' )">Line<img id="sortIMG.dispPoDtlLineNum" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH6"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poLineStsDescTxt' )"> Line Status<img id="sortIMG.poLineStsDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH7"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd' )"> Item<img id="sortIMG.mdseCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH8"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'aslMdseCd' )"> Supplier Item<img id="sortIMG.aslMdseCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH9"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt' )"> Item Description<img id="sortIMG.mdseDescShortTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH10"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'entDealNetUnitPrcAmt' )"> Line Price<img id="sortIMG.entDealNetUnitPrcAmt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH11"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poDispQty' )">Order Qty<img id="sortIMG.poDispQty" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH12"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'pkgUomClsDescTxt' )">UOM<img id="sortIMG.pkgUomClsDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH13"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'entPoDtlDealNetAmt' )">Ext. Total<img id="sortIMG.entPoDtlDealNetAmt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH14"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstRcvDt' )">Date Needed<img id="sortIMG.rqstRcvDt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH15"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'srcRtlWhCd' )">WH<img id="sortIMG.srcRtlWhCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH16"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'srcRtlSwhCd' )">SWH<img id="sortIMG.srcRtlSwhCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH17"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'destRtlWhCd' )">Dest WH<img id="sortIMG.destRtlWhCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH18"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'destRtlSwhCd' )">Dest SWH<img id="sortIMG.destRtlSwhCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH19"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToCustCd' )">Ship To Customer<img id="sortIMG.shipToCustCd" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH20"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'shipToLocNm' )">Ship To Customer Name<img id="sortIMG.shipToLocNm'" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH21"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poMatchTpDescTxt' )">Match Opt<img id="sortIMG.poMatchTpDescTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH22"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poRcvQty' )">Received Qty<img id="sortIMG.poRcvQty" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH23"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poCancQty' )">Cancelled Qty<img id="sortIMG.poCancQty" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH24"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poInvQty' )">Invoiced Qty<img id="sortIMG.poInvQty" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH25"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stkStsDescTxt_FR' )">From Stock Status<img id="sortIMG.stkStsDescTxt_FR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH26"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'stkStsDescTxt_TO' )">To Stock Status<img id="sortIMG.stkStsDescTxt_TO" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH27"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'serNumListTxt' )">Serial<img id="sortIMG.serNumListTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH28"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem130Txt_CH' )">Charge ACC<img id="sortIMG.xxScrItem130Txt_CH" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH29"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem130Txt_AC' )">Accrual ACC<img id="sortIMG.xxScrItem130Txt_AC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH30"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem130Txt_VA' )">Variance ACC<img id="sortIMG.xxScrItem130Txt_VA" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"id="AH31"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poOrdDtlCmntTxt' )"> Line Comment<img id="sortIMG.poOrdDtlCmntTxt" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											</tr>
										</table>
									</div>
									<%-- ******************************* --%>
									<%-- *** Left Table Area(Detail) *** --%>
									<%-- ******************************* --%>
									<div id="rightTbl" style="width:1067px; height:497px; display:block; overflow:scroll; margin:0px; padding:0px;" >
										<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="A" >
											<col width="189" align="center"><!-- Event -->
											<col width="160" align="center"><!-- Date -->
											<col width="80" align="center"><!-- User ID -->
											<col width="136" align="center"><!-- Header Status -->
											<col width="136" align="center"><!-- Approval Status -->
											<col width="42" align="center"><!-- Line -->
											<col width="136" align="center"><!-- Line Status -->
											<col width="100" align="center"><!-- Item#(Pin) -->
											<col width="125" align="center"><!-- Supplier Item#(Pin) -->
											<col width="240" align="center"><!-- Item Description(Pin) -->
											<col width="105" align="center"><!-- Line Price -->
											<col width="125" align="center"><!-- Order Qty -->
											<col width="125" align="center"><!-- UOM(Pin) -->
											<col width="105" align="center"><!-- Ext. Total(Pin) -->
											<col width="82" align="center"><!-- Date Needed -->
											<col width="60" align="center"><!-- WH -->
											<col width="60" align="center"><!-- SWH -->
											<col width="60"  align="center"><!-- Dest WH(Pin) -->
											<col width="60"  align="center"><!-- Dest SWH(Pin) -->
											<col width="125"  align="center"><!-- Ship To Customer(Pin) -->
											<col width="150"  align="center"><!-- Ship To Customer Name(Pin) -->
											<col width="125"  align="center"><!-- Match Opt -->
											<col width="125" align="center"><!-- Received Qty(Pin) -->
											<col width="125" align="center"><!-- Cancelled Qty(Pin) -->
											<col width="125" align="center"><!-- Invoiced Qty(Pin) -->
											<col width="125" align="center"><!-- From Stock Status(Pin) -->
											<col width="125" align="center"><!-- To Stock Status(Pin) -->
											<col width="160" align="center"><!-- Serial#(Pin) -->
											<col width="340" align="center"><!-- Charge Account -->
											<col width="340" align="center"><!-- Accrual Account -->
											<col width="340" align="center"><!-- Variance Account -->
											<col width="365" align="center"><!-- Line Comment -->
											<ezf:row ezfHyo="A">
											<tr height="28" id="id_row{EZF_ROW_NUMBER}">
												<td><ezf:inputText name="eventId" ezfName="eventId" value="PO Submitted" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:label name="xxScrItem19Txt" ezfName="xxScrItem19Txt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="poSubmtPsnCd" ezfName="poSubmtPsnCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="poHdrStsDescTxt" ezfName="poHdrStsDescTxt" value="Validated" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="poApvlStsDescTxt" ezfName="poApvlStsDescTxt" value="Validated" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="dispPoDtlLineNum" ezfName="dispPoDtlLineNum" value="001" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"32\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:inputText name="poLineStsDescTxt" ezfName="poLineStsDescTxt" value="Validated" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<td><ezf:label name="mdseCd" ezfName="mdseCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="aslMdseCd" ezfName="aslMdseCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="mdseDescShortTxt" ezfName="mdseDescShortTxt" value="AAAAAAAAAAA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												<!--<td><label ezfout name="mdseDescShortTxt" ezfname="mdseDescShortTxt" ezfhyo="A">100.00</label></td>-->
												<td style="text-align: right;"><ezf:label name="entDealNetUnitPrcAmt" ezfName="entDealNetUnitPrcAmt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="text-align: right;"><ezf:label name="poDispQty" ezfName="poDispQty" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="pkgUomClsDescTxt" ezfName="pkgUomClsDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="text-align: right;"><ezf:label name="entPoDtlDealNetAmt" ezfName="entPoDtlDealNetAmt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="rqstRcvDt" ezfName="rqstRcvDt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="srcRtlWhCd" ezfName="srcRtlWhCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="srcRtlSwhCd" ezfName="srcRtlSwhCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="destRtlWhCd" ezfName="destRtlWhCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="destRtlSwhCd" ezfName="destRtlSwhCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="shipToCustCd" ezfName="shipToCustCd" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="shipToLocNm" ezfName="shipToLocNm" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="poMatchTpDescTxt" ezfName="poMatchTpDescTxt" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="text-align: right;"><ezf:label name="poRcvQty" ezfName="poRcvQty" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="text-align: right;"><ezf:label name="poCancQty" ezfName="poCancQty" ezfHyo="A" ezfArrayNo="0" /></td>
												<td style="text-align: right;"><ezf:label name="poInvQty" ezfName="poInvQty" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="stkStsDescTxt_FR" ezfName="stkStsDescTxt_FR" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="stkStsDescTxt_TO" ezfName="stkStsDescTxt_TO" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="serNumListTxt" ezfName="serNumListTxt" value="Serial" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
												<td><ezf:label name="xxScrItem130Txt_CH" ezfName="xxScrItem130Txt_CH" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxScrItem130Txt_AC" ezfName="xxScrItem130Txt_AC" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxScrItem130Txt_VA" ezfName="xxScrItem130Txt_VA" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="poOrdDtlCmntTxt" ezfName="poOrdDtlCmntTxt" value="010:MANUAL PRICE" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
											</tr>
											</ezf:row>
											<ezf:skip>
											</ezf:skip>
										</table>
									</div>
								</div>
							</td>
						</div>
						<script type="text/javascript" defer>
							S21TableUI.initialize("parentBoxA", "AHEAD", "A", 5, false);
						</script>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
