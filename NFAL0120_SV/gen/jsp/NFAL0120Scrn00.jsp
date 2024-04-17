<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190820162643 --%>
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
	<input type="hidden" name="pageID" value="NFAL0120Scrn00">
	<!-- Set Page Name -->
	<input type="hidden" name="pageName" value="Journal Entry Inquiry">
			
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- ###TAB - HEAD --%>
				<div class="pTab_HEAD">
					<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				</div>
				<%-- ###TAB - BODY --%>
<div class="pTab_BODY" style="padding-top:5px;" align="center">
		
			<table width="98%" align="center" border="0" cellspacing="0">  <!-- table 1 -->
				<tr>
					<td>
						<table  width="100%" align="left" border="0" cellspacing="0">
							<col width="130">
							<col width="340">
							<col width="110">
							<col width="250">
							<col width="100">
							<col width="">
							<tr>
								<td class="stab">Saved Search Option</td>
								<td>
									<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width: 320px\" id=\"srchOptPk\""/>
								</td>
								<td class="stab">Search Option Name</td>
								<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
								<td>
									<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
								</td>
								<td>
									<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						<table width="1080" align="left" border="0" cellspacing="0">
							<col width="106">
							<col width="320">
							<col width="100">
							<col width="210">
							<col width="100">
							<col width="230">
							<col width="">
							<hr>
							<tr>
								<td class="stab">GL Date</td>
								<td>
									<ezf:inputText name="glDt_FR" ezfName="glDt_FR" value="03/13/2010" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_FR', 4);">
								    - 
									<ezf:inputText name="glDt_TO" ezfName="glDt_TO" value="03/13/2010" otherAttr=" size=\"10\" maxlength=\"10\""/>
									<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_TO', 4);">
								</td>
								<td align="left" class="stab">Journal Source</td>
			     				<td>
			     					<ezf:select name="sysSrcCd_3" ezfName="sysSrcCd_3" ezfBlank="1" ezfCodeName="sysSrcCd_1" ezfDispName="sysSrcNm_2" otherAttr=" style=\"width:170px;\""/>
								</td>
								<td align="left" class="stab"><ezf:anchor name="jrnlCatgNm_LK" ezfName="jrnlCatgNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_JrnlCatg" otherAttr=" id=\"jrnlCatgNm_LK\" ezfanchortext=\"\"">Journal Category</ezf:anchor></td>
								<td align="left">
									<ezf:inputText name="jrnlCatgCd" ezfName="jrnlCatgCd" value="A01" otherAttr=" size=\"3\" maxlength=\"3\""/>
									<ezf:inputButton name="CatgSearchBtn" value=">>" htmlClass="pBtn5" otherAttr=" style=\"width:30px;height:20px\""/>
									<ezf:inputText name="jrnlCatgNm" ezfName="jrnlCatgNm" value="OM SVC ALLOC - SPLY" otherAttr=" size=\"20\" maxlength=\"25\""/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
					<table width="1080" align="left" border="0" cellspacing="0">
						<col width="106">
						<col width="100">
						<col width="112">
						<col width="106">
						<col width="106">
						<col width="106">
						<col width="96">
						<col width="136">
						<col width="120">
						<col width="">
						<tr>
							<td class="stab">Company Code</td>
							<td><ezf:inputText name="coaCmpyCd" ezfName="coaCmpyCd" value="ADB" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="coaBrCd_LK" ezfName="coaBrCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaBr" otherAttr=" id=\"coaBrCd_LK\" ezfanchortext=\"\"">Branch  (*)</ezf:anchor></td>
							<td><ezf:inputText name="coaBrCd" ezfName="coaBrCd" value="500" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="coaCcCd_LK" ezfName="coaCcCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaCc" otherAttr=" id=\"coaCcCd_LK\" ezfanchortext=\"\"">Cost Center  (*)</ezf:anchor></td>
							<td><ezf:inputText name="coaCcCd" ezfName="coaCcCd" value="000000" otherAttr=" size=\"6\" maxlength=\"6\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="coaAcctCd_LK" ezfName="coaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaAcct" otherAttr=" id=\"coaAcctCd_LK\" ezfanchortext=\"\"">Natural Account  (*)</ezf:anchor></td>
							<td><ezf:inputText name="coaAcctCd" ezfName="coaAcctCd" value="000000" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="coaProdCd_LK" ezfName="coaProdCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaProd" otherAttr=" id=\"coaProdCd_LK\" ezfanchortext=\"\"">Product Code  (*)</ezf:anchor></td>
							<td><ezf:inputText name="coaProdCd" ezfName="coaProdCd" value="AO" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						<table width="1080" align="left" border="0" cellspacing="0">
							<col width="106">
							<col width="100">
							<col width="112">
							<col width="106">
							<col width="106">
							<col width="106">
							<col width="96">
							<col width="136">
							<col width="120">
							<col width="">
							<tr>
								<td class="stab"><ezf:anchor name="coaChCd_LK" ezfName="coaChCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaCh" otherAttr=" id=\"coaChCd_LK\" ezfanchortext=\"\"">Sales Channel (*)</ezf:anchor></td>
								<td><ezf:inputText name="coaChCd" ezfName="coaChCd" value="000" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
								<td class="stab"><ezf:anchor name="coaAfflCd_LK" ezfName="coaAfflCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaAffl" otherAttr=" id=\"coaAfflCd_LK\" ezfanchortext=\"\"">Intercompany Code (*)</ezf:anchor></td>
								<td><ezf:inputText name="coaAfflCd" ezfName="coaAfflCd" value="ADB" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
								<td class="stab"><ezf:anchor name="coaProjCd_LK" ezfName="coaProjCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaProj" otherAttr=" id=\"coaProjCd_LK\" ezfanchortext=\"\"">Merchandise Type  (*)</ezf:anchor></td>
								<td><ezf:inputText name="coaProjCd" ezfName="coaProjCd" value="10" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/></td>
								<td class="stab"><ezf:anchor name="coaExtnCd_LK" ezfName="coaExtnCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_CoaExtn" otherAttr=" id=\"coaExtnCd_LK\" ezfanchortext=\"\"">Business Unit  (*)</ezf:anchor></td>
								<td><ezf:inputText name="coaExtnCd" ezfName="coaExtnCd" value="111" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						<table width="1080" align="left" border="0" cellspacing="0">
							<col width="106">
							<col width="320">
							<col width="100">
							<col width="">
							<tr>
								<td class="stab">Transaction Type</td>
								<td>
			     					<ezf:select name="ajeTrxTpCd_3" ezfName="ajeTrxTpCd_3" ezfBlank="1" ezfCodeName="ajeTrxTpCd_1" ezfDispName="ajeTrxTpNm_2" otherAttr=" style=\"width:120px;\""/>
									<ezf:inputText name="xxQueryFltrTxt_AT" ezfName="xxQueryFltrTxt_AT" value="XXXXX" otherAttr=" size=\"25\" maxlength=\"30\""/>
								</td>
								<td class="stab">Amount</td>
								<td>
									<ezf:inputText name="jrnlDealDrAmt_FM" ezfName="jrnlDealDrAmt_FM" value="1111" otherAttr=" style=\"text-align: right; \" size=\"18\" maxlength=\"18\" ezftoupper=\"\""/>
							        -
									<ezf:inputText name="jrnlDealDrAmt_TO" ezfName="jrnlDealDrAmt_TO" value="2222" otherAttr=" style=\"text-align: right; \" size=\"18\" maxlength=\"18\" ezftoupper=\"\""/>
								</td>
							</tr>
						</table>
					</td>
				<tr>
					<td align="left">
						<table width="1080" align="left" border="0" cellspacing="0">
							<col width="106">
							<col width="110">
							<col width="120">
							<col width="170">
							<col width="">
							<tr>
							    <td class="stab">GL Period</td>
								<td>
									<ezf:inputText name="glPerNm" ezfName="glPerNm" value="MAR-16" otherAttr=" size=\"8\" maxlength=\"6\" ezftoupper=\"\""/>
								</td>
								<td class="stab">Reference Search (*)</td>
								<td align="left" class="stab" colspan="4">
									<ezf:inputText name="xxQueryFltrTxt" ezfName="xxQueryFltrTxt" value="XXXXX" otherAttr=" style=\"width:400px;\""/>
								</td>
								<td class="stab">
									<ezf:inputButton name="InquiryBtn" value="Inquiry" htmlClass="pBtn5" otherAttr=" style=\"width:80px;\""/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table> <!-- table 1 -->
			
			<%@ page import="business.servlet.NFAL0120.NFAL0120BMsg" %>
			<%@ page import="business.servlet.NFAL0120.NFAL0120_ABMsg" %>
			<%  NFAL0120BMsg bMsg = (NFAL0120BMsg)databean.getEZDBMsg(); %>
			
			
			<table width="98%" align="center" border="0" cellspacing="0"> 			
			<tr>
			<td>
			<table width="100%" align="left" border="0" cellspacing="0"> <!-- table 2 -->
			<hr>
				<col width="200">
				<col width="150">
				<col width="60">
				<col width="90">
				<col width="20">
				<col width="60">
				<col width="90">
				<col width="">
				<tr>
					<td class="stab">
						<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
					</td>
					<!--
					<td class="stab">
						<input class="pBtn5" type="button" value="Create Report" style="width:120px;height:22px;font-size:8pt;" name="CreateReportBtn" onclick="sendServer(this)">
					</td>
					<td class="stab">
						<input class="pBtn5" type="button" value="Create Report - Suppress Reclass" style="width:180px;height:22px;font-size:8pt;" name="CreateReportSuppressReclassBtn" onclick="sendServer(this)">
					</td>
					<td class="stab">
						<input class="pBtn5" type="button" value="CSV-AR Reclass" style="width:120px;height:22px;font-size:8pt;" name="CsvArReclassBtn" onclick="sendServer(this)">
					</td>
					-->
					<td>&nbsp;</td>
					<td align="left" class="stab"><b>DR Total:</b></td>
					<td align="right" class="stab">
						<ezf:label name="xxTotPrcAmt_DR" ezfName="xxTotPrcAmt_DR" />
					</td>
					<td>&nbsp;</td>
					<td align="left" class="stab"><b>CR Total:</b></td>
					<td align="right" class="stab">
						<ezf:label name="xxTotPrcAmt_CR" ezfName="xxTotPrcAmt_CR" />
					</td>
					<td align="right">
	<%-- Pagenation --%>
					<%--
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
								<td class="pOut">15</td>
								<td class="stab">of</td>
								<td class="pOut">500</td>
								<td>&nbsp;</td>
								<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
								<td></td>
								<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
							</tr>
						</table>
					--%>

						<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
							<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
							<jsp:param name="TableNm"     value="A" />
							<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
							<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
							<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
							<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
							<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
							<jsp:param name="ViewMode"   value="LEFT_NONE" />
						</jsp:include>
					</td>
				</tr>
			</table> <!-- table 2 -->			
			</td>
			</tr>
			<tr>
			<td>			
			
			<div id="parentBoxA">
			<table valign="center" > <!-- table 3 -->
				<tr>
					<td valign="top">
					<!-- ********* TITLE ********* -->
					<div style="float:left; display:block"> <!-- left table -->
        			  <div id="leftTblHead" style="display:block"></div>
        			  <div id="leftTbl"style="float:left; display:block; height:328px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"> </div>
    				</div>  <!-- left table -->
    				
    				<div style="float:left"> <!-- right table -->
    					<div id="rightTblHead" style="width:1083px; display:block; overflow:hidden; margin:0px;padding:0px;">
							<table border="1" cellpadding="1" width="3760px" cellspacing="0" id="AHEAD" style="margin-right:20px; table-layout:fixed">
							<col width="330">
							<col width="80">
							<col width="60">
							<col width="200">
							<col width="200">
							<col width="80">
							<col width="80">
							<col width="80">
							<!-- <col width="50"> -->
							<col width="70">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="120">
							<col width="120">
							<col width="160">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="250">
							<col width="250">
							<col width="250">
							<col width="250">
							<col width="250">
							<tbody>
								<tr height="20px">
									<td align="center" class="pClothBs colFix" id="AH0">
									    <a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxComnScrCondValTxt_A' )">
										COA Account String<img id="sortIMG.xxComnScrCondValTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH1">
									    <a href="#" class="pSortCol" onclick="columnSort( 'A', 'glDt_A' )">
										GL Date<img id="sortIMG.glDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH2">
									    <a href="#" class="pSortCol" onclick="columnSort( 'A', 'glSendCpltFlg_A' )">
										GL Send<img id="sortIMG.glSendCpltFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH3">
									    <a href="#" class="pSortCol" onclick="columnSort( 'A', 'jrnlSrcNm_A' )">
										Journal Source<img id="sortIMG.jrnlSrcNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH4">
									    <a href="#" class="pSortCol" onclick="columnSort( 'A', 'jrnlCatgNm_A' )">
										Journal Category<img id="sortIMG.jrnlCatgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH5">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'jrnlFuncDrAmt_A' )">
										Debit Amt<img id="sortIMG.jrnlFuncDrAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH6">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'jrnlFuncCrAmt_A' )">
										Credit Amt<img id="sortIMG.jrnlFuncCrAmt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH7">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ajeId_A' )">
										AJE ID<img id="sortIMG.ajeId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<!--
									<td align="center" class="pClothBs" id="AH8">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'jrnlQty_A' )">
										Qty<img id="sortIMG.jrnlQty_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									-->
									<td align="center" class="pClothBs" id="AH8">
									<!-- START 2016/08/25 S.Fujita [QC#4105,ADD] -->
									<!--
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'tocCd_A' )">
										TOC Code<img id="sortIMG.tocCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
									-->
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'psnNum_A' )">
										Sales Rep<img id="sortIMG.psnNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
									<!-- END   2016/08/25 S.Fujita [QC#4105,ADD] -->
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH9">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'dsAcctNum_A' )">
										Cust Acct<img id="sortIMG.dsAcctNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH10">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'vndCd_A' )">
										Vendor<img id="sortIMG.vndCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH11">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ajeInvNum_A' )">
										Invoice<img id="sortIMG.ajeInvNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH12">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ajeItemCd_A' )">
										Item ID<img id="sortIMG.ajeItemCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH13">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'ajeItemDescTxt_A' )">
										Item Name<img id="sortIMG.ajeItemDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH14">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'serNum_A' )">
										Ser Num<img id="sortIMG.serNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH15">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'cpoOrdNum_A' )">
										Order Number<img id="sortIMG.cpoOrdNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH16">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'poOrdNum_A' )">
										PO Number<img id="sortIMG.poOrdNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH17">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'soNum_A' )">
										SO Number<img id="sortIMG.soNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH18">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'rcptNum_A' )">
										Recpt Num<img id="sortIMG.rcptNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH19">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'prntDsAssetMstrPk_A' )">
										Asset Num<img id="sortIMG.prntDsAssetMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH20">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'invtyTrxPk_A' )">
										Inventory Trx<img id="sortIMG.invtyTrxPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH21">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcMachMstrPk_A' )">
										Install Base ID<img id="sortIMG.svcMachMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH22">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'svcConfigMstrPk_A' )">
										Configuration#<img id="sortIMG.svcConfigMstrPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH23">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxQueryFltrTxt_A1' )">
										Ref# 1<img id="sortIMG.xxQueryFltrTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH24">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxQueryFltrTxt_A2' )">
										Ref# 2<img id="sortIMG.xxQueryFltrTxt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH25">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxQueryFltrTxt_A3' )">
										Ref# 3<img id="sortIMG.xxQueryFltrTxt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH26">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxQueryFltrTxt_A4' )">
										Ref# 4<img id="sortIMG.xxQueryFltrTxt_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
									<td align="center" class="pClothBs" id="AH27">
										<a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxQueryFltrTxt_A5' )">
										Ref# 5<img id="sortIMG.xxQueryFltrTxt_A5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
										</a>
									</td>
								</tr>
							</tbody>
							</table>
				    	</div>  <!-- right table head-->
				    	
			    		<!-- ********* DATA ********* -->
						 <div id="rightTbl" style="width:1096px; height:328px; display:block; overflow:scroll; margin:0px; padding:0px;" >
						 <table border="1" cellpadding="1" cellspacing="0" id="A" width="3760px" style="table-layout:fixed">
					 		<col width="330">
							<col width="80">
							<col width="60">
							<col width="200">
							<col width="200">
							<col width="80">
							<col width="80">
							<col width="80">
							<!-- <col width="50"> -->
							<col width="70">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="120">
							<col width="120">
							<col width="160">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="80">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="120">
							<col width="250">
							<col width="250">
							<col width="250">
							<col width="250">
							<col width="250">
							<tbody>
								<% int i = 0; %>
						 		<ezf:row ezfHyo="A">
						 		    <% NFAL0120_ABMsg lineMsg = bMsg.A.no(i++); %>
									<% String ajeId_A = lineMsg.ajeId_A.getValue(); %>
									<% String ajeInvNum_A = lineMsg.ajeInvNum_A.getValue(); %>
									<% String rcptNum_A = lineMsg.rcptNum_A.getValue(); %>
									<!-- START 2017/03/16 T.Murai [QC#14205,MOD] -->
									<!-- <tr height="22px"> -->
									<tr height="22px" id="A_rightTBLRow#{EZF_ROW_NUMBER}">
									<!-- END 2017/03/16 T.Murai [QC#14205,MOD] -->
										<td align="left"><ezf:label name="xxComnScrCondValTxt_A" ezfName="xxComnScrCondValTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left"><ezf:label name="glDt_A" ezfName="glDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left"><ezf:label name="glSendCpltFlg_A" ezfName="glSendCpltFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left"><ezf:inputText name="jrnlSrcNm_A" ezfName="jrnlSrcNm_A" value="S21 Accounting AR" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\""/></td>
										<td align="left"><ezf:inputText name="jrnlCatgNm_A" ezfName="jrnlCatgNm_A" value="A/R RECEIPT REVALUATION" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\""/></td>
										<td align="right"><ezf:inputText name="jrnlFuncDrAmt_A" ezfName="jrnlFuncDrAmt_A" value="12345.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
										<td align="right"><ezf:inputText name="jrnlFuncCrAmt_A" ezfName="jrnlFuncCrAmt_A" value="0.00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\""/></td>
										<td align="left"><ezf:label name="ajeId_A" ezfName="ajeId_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<!-- <td align="right"><label ezfout name="jrnlQty_A" ezfName="jrnlQty_A" ezfHyo="A">899</label></td> -->
										<!-- START 2016/08/25 S.Fujita [QC#4105,ADD] -->
										<!--
										<td align="left"><label ezfout name="tocCd_A" ezfName="tocCd_A" ezfHyo="A">XXXXX</label></td>
										<td align="left"><input type="text"  value="12345" name="psnNum_A" ezfName="psnNum_A" ezfHyo="A" size="9" maxlength="50"></td>
										-->
										<!-- END   2016/08/25 S.Fujita [QC#4105,ADD] -->
										<td align="left"><ezf:anchor name="psnNum_A" ezfName="psnNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_RsrcMaint" otherAttr=" id=\"psnNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="psnNum_A" ezfName="psnNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td align="left"><ezf:label name="dsAcctNum_A" ezfName="dsAcctNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left"><ezf:label name="vndCd_A" ezfName="vndCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left">
										<% if (!ajeInvNum_A.trim().equals("") && ajeInvNum_A.substring(0,2).equals("DD")) {%>
											<ezf:label name="ajeInvNum_A" ezfName="ajeInvNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<%} else if (!ajeInvNum_A.trim().equals("") && ajeId_A.indexOf("NFC-130") == -1) {%>
											<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ManualInvoice" otherAttr=" id=\"ajeInvNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="ajeInvNum_A" ezfName="ajeInvNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
										<%} else if (!ajeInvNum_A.trim().equals("")) {%>
											<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_InvoiceActivity" otherAttr=" id=\"ajeInvNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="ajeInvNum_A" ezfName="ajeInvNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
										<%} else {%>
											<label>&nbsp;</label>
										<%}%>
										</td>
										<td align="left"><ezf:anchor name="ajeItemCd_A" ezfName="ajeItemCd_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_ItemCostSrc" otherAttr=" id=\"ajeItemCd_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="ajeItemCd_A" ezfName="ajeItemCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td align="left"><ezf:inputText name="ajeItemDescTxt_A" ezfName="ajeItemDescTxt_A" value="AAAAAAAAAAAAAAAAA" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/></td>
										<td align="left"><ezf:inputText name="serNum_A" ezfName="serNum_A" value="XXXXXXXXX1XXXXXXXXX2" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
										<!--
										<td align="left"><label ezfout name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A">XXXXX</label></td>
										-->
										<td align="left"><ezf:anchor name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_OrdEntry" otherAttr=" id=\"cpoOrdNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="cpoOrdNum_A" ezfName="cpoOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td align="left"><ezf:anchor name="poOrdNum_A" ezfName="poOrdNum_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_PoEntry" otherAttr=" id=\"poOrdNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="poOrdNum_A" ezfName="poOrdNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td align="left"><ezf:label name="soNum_A" ezfName="soNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left">
										<%if (!rcptNum_A.trim().equals("") && ajeId_A.indexOf("110") == -1){%>
											<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_CashApp" otherAttr=" id=\"rcptNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="rcptNum_A" ezfName="rcptNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
										<%}else if (!rcptNum_A.trim().equals("")) {%>
											<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_RcptEntry" otherAttr=" id=\"rcptNum_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="rcptNum_A" ezfName="rcptNum_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
										<%} else {%>
											<label>&nbsp;</label>
										<%}%>
										</td>
										<td align="left"><ezf:anchor name="prntDsAssetMstrPk_A" ezfName="prntDsAssetMstrPk_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_AssetMaint" otherAttr=" id=\"prntDsAssetMstrPk_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="prntDsAssetMstrPk_A" ezfName="prntDsAssetMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td class=""><ezf:anchor name="invtyTrxPk_A" ezfName="invtyTrxPk_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_InventoryInquiry" otherAttr=" id=\"invtyTrxPk_A#{EZF_ROW_NUMBER}\" ezfanchortext=\"\""><ezf:label name="invtyTrxPk_A" ezfName="invtyTrxPk_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
										<td align="left"><ezf:label name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left"><ezf:label name="svcConfigMstrPk_A" ezfName="svcConfigMstrPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
										<td align="left"><ezf:inputText name="xxQueryFltrTxt_A1" ezfName="xxQueryFltrTxt_A1" value="XXXX-XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\""/></td>
										<td align="left"><ezf:inputText name="xxQueryFltrTxt_A2" ezfName="xxQueryFltrTxt_A2" value="XXXX-XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\""/></td>
										<td align="left"><ezf:inputText name="xxQueryFltrTxt_A3" ezfName="xxQueryFltrTxt_A3" value="XXXX-XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\""/></td>
										<td align="left"><ezf:inputText name="xxQueryFltrTxt_A4" ezfName="xxQueryFltrTxt_A4" value="XXXX-XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\""/></td>
										<td align="left"><ezf:inputText name="xxQueryFltrTxt_A5" ezfName="xxQueryFltrTxt_A5" value="XXXX-XXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"35\""/></td>
										<td class="pAuditInfo">
                                            <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
                                            <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
                                            <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
                                            <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
                                            <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
                                        </td>
									</tr>
								</ezf:row>
								<ezf:skip>

								</ezf:skip>
							</tbody>
						</table>
						</div>  <!-- rightTbl -->
					</div> <!-- right table -->
					</td>
				</tr>
			</table> <!-- table 3 -->
			</div> <!-- parent box -->
			</td>
			</tr>
			</table>
		    
			<!--2016/06/10  move to upper
			<table width="94%" height="18px" align="center" border="0" cellspacing="0"> 
				<col width="60%">
				<col width="7%">
				<col width="12%">
				<col width="2%">
				<col width="7%">
				<col width="12%">
				<tr height="16px">
					<td></td>
					<td align="left" class="stab">DR Total:</td>
					<td align="right" class="stab">
						<label ezfout name="xxTotPrcAmt_DR" ezfname="xxTotPrcAmt_DR">123456789.00</label>
					</td>
					<td>&nbsp;</td>
					<td align="left" class="stab">CR Total:</td>
					<td align="right" class="stab">
						<label ezfout name="xxTotPrcAmt_CR" ezfname="xxTotPrcAmt_CR">123456789.00</label>
					</td>
				</tr>
			</table>
			-->
			
	</div> <!-- pTab_BODY -->
</td>
</tr>
</table>
</center>

<script type="text/javascript" defer>

 	S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, true);

</script>
    

<%-- **** Task specific area ends here **** --%>
