<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171222114512 --%>
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
			<input type="hidden" name="pageID" value="NFBL1120Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="AP Invoice Maintenance Search">

<%@ page import="business.servlet.NFBL1120.NFBL1120BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>

<% NFBL1120BMsg  bMsg = (NFBL1120BMsg)databean.getEZDBMsg(); %>
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<!-- ###TAB - HEAD -->
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Mach Mst Inq" class="pTab_ON"><a href="#">Invoice Entry</a></li>
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
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<!-- ###TAB - BODY -->
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" width="1100" style="margin-left:20px;">
						<col width="115" align="left">
						<col width="305" align="left">
						<col width="105" align="left">
						<col width="300" align="left">
						<col width="83"  align="left">
						<col width="83"  align="left">
						<col width="">
						<tr height="25">
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_H" ezfDispName="srchOptNm_H" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:290px;\" tabindex=\"1\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td><ezf:inputButton name="SaveSearch" ezfName="SaveSearch" value="SaveSearch" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
							<td><ezf:inputButton name="DeleteSearch" ezfName="DeleteSearch" value="DeleteSearch" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<hr size="1" noshade>
					<table border="0" cellpadding="0" cellspacing="0" width="1100" align="center">
						<col width="125">
						<col width="425">
						<col width="125">
						<col width="425">
						<tr height="27">
							<td class="stab">Batch #</td>
							<td><ezf:inputText name="apBatNum" ezfName="apBatNum" value="12345678901234567890" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/></td>
							<td class="stab"><ezf:anchor name="prntVndCd_L" ezfName="prntVndCd_L" ezfEmulateBtn="1" ezfGuard="OpenWin_PrntVnd" >Supplier</ezf:anchor></td>
							<td><ezf:inputText name="prntVndCd" ezfName="prntVndCd" value="1234567890123456" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
							    <ezf:inputButton name="SearchSupplierName" value=">>" htmlClass="pBtn1" />
							    <ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"240\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="27">
							<td class="stab">Batch Date</td>
							<td><ezf:inputText name="apBatDt" ezfName="apBatDt" value="12345678901234567890123456789012345" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('apBatDt', 4);" ></td>
							<td class="stab"><ezf:anchor name="locNm_L" ezfName="locNm_L" ezfEmulateBtn="1" ezfGuard="OpenWin_Vnd" >Supplier Site Name</ezf:anchor></td>
							<td><ezf:inputText name="locNm" ezfName="locNm" value="1234567890123456" otherAttr=" size=\"15\" maxlength=\"60\""/></td>
						</tr>
						<tr height="27">
							<td class="stab">Invoice Status</td>
							<td><ezf:select name="apMaintInvStsCd_S" ezfName="apMaintInvStsCd_S" ezfBlank="1" ezfCodeName="apMaintInvStsCd_C" ezfDispName="apMaintInvStsDescTxt_D" otherAttr=" style=\"width:203px;\""/>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr height="27">
							<td class="stab">Current Approver</td>
							<td><ezf:select name="varCharConstNm_S" ezfName="varCharConstNm_S" ezfBlank="1" ezfCodeName="varCharConstNm_C" ezfDispName="varCharConstVal_D" otherAttr=" style=\"width:203px;\""/>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr height="27">
							<td class="stab">Invoice #</td>
							<td><ezf:inputText name="apInvNum" ezfName="apInvNum" value="12345678901234567890123456789012345" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr height="27">
							<td class="stab">Invoice Date</td>
							<td><ezf:inputText name="invDt" ezfName="invDt" value="12345678901234567890123456789012345" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt', 4);" ></td>
							<td>&nbsp;</td>
							<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<hr size="1" noshade>

					<table border="0" cellpadding="0" cellspacing="0"width="1100" align="center">
						<tr>
							<col width="180">
							<col width="320">
							<col width="600">
						    <td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
							<td align = "left" class="stab"><ezf:inputButton name="CreateNewBatchButton" value="Create New Batch" htmlClass="pBtn9" /></td>
							<td align = "right">
                            	<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
								<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"     value="A" />
								<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
								<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
								<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
		    					</jsp:include>
							</td>
						</tr>
					</table>

					<div id="parentBoxA">
					<table border="0" align="center">
						<tr>
							<td>
								<div style="float:left; display:block"><!-- left table -->
								    <div id='leftTblHead' style="display:block;"></div>
								    <div id='leftTbl' style="float:left; display:block; height:290; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
								</div><!-- left table -->
								<div style="float:left"><!-- right table -->
								<div id='rightTblHead' style="display:block; overflow:hidden; margin:0px;padding:0px; width:1106;">
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="1106" height="34" id="AHEAD" style="margin-right:20px">
										<col align="center" width="110" >
										<col align="center" width="85" >
										<col align="center" width="110" >
										<col align="center" width="160" >
										<col align="center" width="110" >
										<col align="center" width="110" >
										<col align="center" width="110" >
										<col align="center" width="110" >
										<col align="center" width="85" >
										<col align="center" width="110" >
										<tr>
											<td id="AH0" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apBatNum_A1' )">Batch Number<img id="sortIMG.apBatNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH1" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apBatDt_A1' )">Batch Date<img id="sortIMG.apBatDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prntVndCd_A1' )">Supplier Number<img id="sortIMG.prntVndCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH3" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prntVndNm_A1' )">Supplier Name<img id="sortIMG.prntVndNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH4" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'vndSiteNm_A1' )">Supplier Site Name<img id="sortIMG.vndSiteNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apMaintInvStsDescTxt_A1' )">Invoice Status<img id="sortIMG.apMaintInvStsDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'varCharConstVal_A1' )">Current Approver<img id="sortIMG.varCharConstVal_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apInvNum_A1' )">Invoice Number<img id="sortIMG.apInvNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invDt_A1' )">Invoice Date<img id="sortIMG.invDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td id="AH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'apInvAmt_A1' )">Invoice Amount<img id="sortIMG.apInvAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
								</div>
								<div id='rightTbl' style="display:block; overflow:hidden; margin:0px; padding:0px; width:1106;height:290;" >
									<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" width="1106" id="A">
										<col align="left" width="110">
										<col align="center" width="85">
										<col align="left" width="110">
										<col align="left" width="160">
										<col align="left" width="110">
										<col align="left" width="110">
										<col align="left" width="110">
										<col align="left" width="110">
										<col align="center" width="85">
										<col align="right" width="110">
										<ezf:row ezfHyo="A">
											<tr height="24">
												<td><ezf:inputText name="apBatNum_A1" ezfName="apBatNum_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="apBatDt_A1" ezfName="apBatDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="prntVndCd_A1" ezfName="prntVndCd_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="prntVndNm_A1" ezfName="prntVndNm_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"240\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="vndSiteNm_A1" ezfName="vndSiteNm_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"20\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="apMaintInvStsDescTxt_A1" ezfName="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="varCharConstVal_A1" ezfName="varCharConstVal_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" style=\"border:none; background-color:transparent;\""/></td>
												<td>
													<ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OnClick_InvoiceNumberLink" >
														<ezf:label name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"15\" ezftoupper=\"\""/>
													</ezf:anchor>
												</td>
												<td><ezf:inputText name="invDt_A1" ezfName="invDt_A1" value="MM/DD/YYYY" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" style=\"border:none; background-color:transparent;\""/></td>
												<td><ezf:inputText name="apInvAmt_A1" ezfName="apInvAmt_A1" value="WWW,WWW,WWW,WWW,WWW.WWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"24\" style=\"border:none; background-color:transparent;\""/></td>
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
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
											<tr height="24">
												<td><input type="text" name="apBatNum_A1"             value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="apBatNum_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apBatDt_A1"              value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="apBatDt_A1"              class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndCd_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="prntVndCd_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="prntVndNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="240" ezfHyo="A" ezfname="prntVndNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="vndSiteNm_A1"            value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="20"  ezfHyo="A" ezfname="vndSiteNm_A1"            class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apMaintInvStsDescTxt_A1" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="50"  ezfHyo="A" ezfname="apMaintInvStsDescTxt_A1" class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="varCharConstVal_A1"      value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" size="15" maxlength="30"  ezfHyo="A" ezfname="varCharConstVal_A1"      class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td>
													<a href="#" name="" ezfname="" ezfHyo="A" ezfArrayNo="0" onclick="sendServer('OnClick_InvoiceNumberLink')">
														<label ezfout name="apInvNum_A1" ezfName="apInvNum_A1" value="WWWWWWWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" size="13" maxlength="15" ezftoupper>WWWWWWWWWWWWWWW</label>
													</a>
												</td>
												<td><input type="text" name="invDt_A1"                value="MM/DD/YYYY"                 size="10" maxlength="10"  ezfHyo="A" ezfname="invDt_A1"                class="pPro" style="border:none; background-color:transparent;" readOnly></td>
												<td><input type="text" name="apInvAmt_A1"             value="WWW,WWW,WWW,WWW,WWW.WWWW"   size="15" maxlength="24"  ezfHyo="A" ezfname="apInvAmt_A1"             class="pPro" style="border:none; background-color:transparent;" readOnly></td>
											</tr>
										</ezf:skip>
									</table>
								</div>
								</div><!-- right table -->
							</td>
						</tr>
					</table>
					</div><!-- parentBoxA -->
		            <script type="text/javascript" defer>
                        S21TableUI.initialize("parentBoxA", "AHEAD", "A", 0, false);
                    </script>
				</div>
			</td>
		</tr>
	</table>
</center>

							

<%-- **** Task specific area ends here **** --%>
