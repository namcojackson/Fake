<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170223105810 --%>
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
			<input type="hidden" name="pageID" value="NWAL2410Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Therefore Branch Maintenance Screen">
<left>
	<table width="1133" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Supply Agreement Plan Search" class="pTab_ON"><a href="#">AgmtPln Srch</a></li>
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
				<div class="pTab_BODY_In">
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<table border="0" cellpadding="0" cellspacing="0" height="45" width="1100" rules="none"  style="margin-bottom: 5px; solid #333333;">
					<tr>
						<td valign="top" width="1100">
							<table cellpadding="1" border="0">
								<col align="left" width="110">
								<col align="left" width="110">
								<col align="left" width="90">
								<col align="left" width="110">
								<col align="left" width="110">
								<col align="left" width="110">
								<col align="left" width="110">
								<col align="left" width="110">
								<col align="left" width="110">
								<col align="left" width="110">
								<tr>
									<td class="stab">LOB</td>
									<td>
										<ezf:select name="docMgtOrgCd" ezfName="docMgtOrgCd" ezfBlank="1" ezfCodeName="docMgtOrgCd_P" ezfDispName="docMgtOrgDescTxt_P" otherAttr=" style=\"width: 110px\""/>
									</td>
									<td class="stab">Branch Code(*)</td>
									<td><ezf:inputText name="docMgtScanBrCd" ezfName="docMgtScanBrCd" value="Branch Code" otherAttr=" size=\"14\" maxlength=\"3\" ezftoupper=\"\""/></td>
									<td class="stab">Branch Name(*)</td>
									<td><ezf:inputText name="docMgtScanBrNm" ezfName="docMgtScanBrNm" value="Branch Name" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td class="stab">Region(*)</td>
									<td><ezf:inputText name="docMgtScanRgNm" ezfName="docMgtScanRgNm" value="Region" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
									<td class="stab">Zone(*)</td>
									<td><ezf:inputText name="docMgtScanZnNm" ezfName="docMgtScanZnNm" value="Zone" otherAttr=" size=\"14\" maxlength=\"50\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab">Order Processor(*)</td>
									<td><ezf:inputText name="defOrdProcPsnCd" ezfName="defOrdProcPsnCd" value="Order Processor" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td class="stab">Branch Admin(*)</td>
									<td><ezf:inputText name="defBrAdminPsnCd" ezfName="defBrAdminPsnCd" value="Branch Admin" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td class="stab">CFS Processor(*)</td>
									<td><ezf:inputText name="leaseCmpyProcPsnCd" ezfName="leaseCmpyProcPsnCd" value="CFS Processor" otherAttr=" size=\"14\" maxlength=\"8\" ezftoupper=\"\""/></td>
									<td class="stab">Active Only</td>
									<td class="stab"><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" /></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<hr>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table border="0" cellpadding="01" cellspacing="0" width="1085" align="center"  rules="none"  style="padding: 10px; margin-bottom: 0px;">
					<tr align="right">
						<col width="170"  align="left">
						<col width="110"  align="left">
						<col width="110"  align="left">
						<col width="160"  align="center">
						<col width="550"  align="right">
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
						<td><ezf:inputButton name="InsertRow" value="Add" htmlClass="pBtn8" /></td>
						<td><ezf:inputButton name="DeleteRow" value="Delete" htmlClass="pBtn8" /></td>
						<td>&nbsp;</td>
						<td class="stab">
							<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
								<jsp:param name="beanId"           value='<%= request.getParameter( "beanId" ) %>' />
								<jsp:param name="TableNm"          value="A" />
								<jsp:param name="ShowingFrom"      value="xxPageShowFromNum" />
								<jsp:param name="ShowingTo"        value="xxPageShowToNum" />
								<jsp:param name="ShowingOf"        value="xxPageShowOfNum" />
								<jsp:param name="ShowingCurrent"   value="xxPageShowCurNum" />
								<jsp:param name="ShowingTotal"     value="xxPageShowTotNum" />
								<jsp:param name="ViewMode"         value="FULL" />
							</jsp:include>
							<ezf:skip>
								<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
									<col>
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
										<td>Results 990 - 1000 of 1000</td>
										<td class="stab">Showing</td>
										<td><input type="text" value="1" size="4" maxlength="4" style="text-align:right" class="pPro" readOnly name="xxPageShowFromNum" ezfname="xxPageShowFromNum"></td>
										<td class="stab">/</td>
										<td><input type="text" size="4" maxlength="4" value="1" class="pPro" style="text-align:right" id="txtShowingTot" readOnly name="xxPageShowToNum" ezfname="xxPageShowToNum"></td>
										<td class="stab">page</td>
										<td>&nbsp;</td>
										<td><input type="button" class="pBtn3" value="Jump" name="PageJump" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" disabled></td>
										<td></td>
										<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" disabled></td>
									</tr>
								</table>
							</ezf:skip>
						</td>
					</tr>
				</table>
				<table>
				<tr>
				<td width="3"></td>
				<td>
					<div id="parentBoxA">
						<div style="float:left; display:block"> <!-- left table -->
							<div id="leftTblHead" style="display:block;">
							</div>
							<div id="leftTbl" style="float:left; display:block; height:413px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
							</div>
						</div>  <!-- left table -->
						<div style="float:left"> <!-- right table -->
							<div id="rightTblHead" style="width:1069px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1069px" height="40" style="margin-right:20px">
									<col align="center" width="35">		<!-- Subsc					-->
									<col align="center" width="120">	<!-- LOB					-->
									<col align="center" width="130">	<!-- Branch Code				-->
									<col align="center" width="95">		<!-- Branch				-->
									<col align="center" width="95">		<!-- Region		-->
									<col align="center" width="95">		<!-- Zone		-->
									<col align="center" width="120">	<!-- Default Order Processor				-->
									<col align="center" width="120">	<!-- Default Branch Admin					-->
									<col align="center" width="120">	<!-- CFS Processor					-->
									<col align="center" width="150">	<!-- Notify Email For PO Pend			-->
									<col align="center" width="150">	<!-- Notify Email For PO Issue				-->
									<col align="center" width="150">	<!-- Notify Email For Invoice Package Pend				-->
									<col align="center" width="60">		<!-- Active					-->
									<col align="center" width="80">	<!-- Status				-->
									<col align="center" width="200">	<!-- Timestamp				-->
									<tr height="25">
										<td id="AH0"  class="pClothBs colFix"></td>
										<td id="AH1"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtOrgCd_A' )">LOB<img id="sortIMG.docMgtOrgCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH2"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtScanBrCd_A' )">Branch Code<img id="sortIMG.docMgtScanBrCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH3"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtScanBrNm_A' )">Branch<img id="sortIMG.docMgtScanBrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH4"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtScanRgNm_A' )">Region<img id="sortIMG.docMgtScanRgNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH5"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtScanZnNm_A' )">Zone<img id="sortIMG.docMgtScanZnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'defOrdProcPsnCd_A' )">Default Order Processor<img id="sortIMG.defOrdProcPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'defBrAdminPsnCd_A' )">Default Branch Admin<img id="sortIMG.defBrAdminPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'leaseCmpyProcPsnCd_A' )">CFS Processor<img id="sortIMG.leaseCmpyProcPsnCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poPendEmlAddr_A' )">Notify Email For PO Pend<img id="sortIMG.poPendEmlAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'poIssEmlAddr_A' )">Notify Email For PO Issue<img id="sortIMG.poIssEmlAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'invPkgPendEmlAddr_A' )">Notify Email For Invoice Package Pend<img id="sortIMG.invPkgPendEmlAddr_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'actvFlg_A' )">Active<img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'docMgtIntfcStsDescTxt_A' )">Status<img id="sortIMG.docMgtIntfcStsDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem23Txt_A' )">Timestamp<img id="sortIMG.xxScrItem23Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							<div id="rightTBL" style="width:1085px; height:433px; display:block; overflow:scroll; margin:0px; padding:0px;"  onScroll="synchroScrollLeft(this.id, new Array('rightTblHead'));">
								<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed" width="1085px">
									<col align="center" width="35">		<!-- Subsc					-->
									<col align="center" width="120">	<!-- LOB					-->
									<col align="center" width="130">	<!-- Branch Code				-->
									<col align="center" width="95">	<!-- Branch				-->
									<col align="center" width="95">	<!-- Region		-->
									<col align="center" width="95">	<!-- Zone		-->
									<col align="center" width="120">	<!-- Default Order Processor				-->
									<col align="center" width="120">	<!-- Default Branch Admin					-->
									<col align="center" width="120">	<!-- CFS Processor					-->
									<col align="center" width="150">	<!-- Notify Email For PO Pend			-->
									<col align="center" width="150">	<!-- Notify Email For PO Issue				-->
									<col align="center" width="150">	<!-- Notify Email For Invoice Package Pend				-->
									<col align="center" width="60">		<!-- Active					-->
									<col align="center" width="80">	<!-- Status				-->
									<col align="center" width="200">	<!-- Timestamp				-->
									<ezf:row ezfHyo="A">
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="this.blur();this.focus();" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
											<td>
												<ezf:select name="docMgtOrgCd_A" ezfName="docMgtOrgCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="docMgtOrgCd_P" ezfDispName="docMgtOrgDescTxt_P" otherAttr=" style=\"width: 110px\""/>
											</td>
											<td><ezf:inputText name="docMgtScanBrCd_A" ezfName="docMgtScanBrCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"3\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_Branch" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="docMgtScanBrNm_A" ezfName="docMgtScanBrNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="docMgtScanRgNm_A" ezfName="docMgtScanRgNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="docMgtScanZnNm_A" ezfName="docMgtScanZnNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="defOrdProcPsnCd_A" ezfName="defOrdProcPsnCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_ProcPsn" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="defBrAdminPsnCd_A" ezfName="defBrAdminPsnCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_BrPsn" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="leaseCmpyProcPsnCd_A" ezfName="leaseCmpyProcPsnCd_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_CFS" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" /></td>
											<td><ezf:inputText name="poPendEmlAddr_A" ezfName="poPendEmlAddr_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"320\""/></td>
											<td><ezf:inputText name="poIssEmlAddr_A" ezfName="poIssEmlAddr_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"320\""/></td>
											<td><ezf:inputText name="invPkgPendEmlAddr_A" ezfName="invPkgPendEmlAddr_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"320\""/></td>
											<td><ezf:inputCheckBox name="actvFlg_A" ezfName="actvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" onClick="this.blur();this.focus();" otherAttr=" id=\"xxChkBox_A{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:label name="docMgtIntfcStsDescTxt_A" ezfName="docMgtIntfcStsDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxScrItem23Txt_A" ezfName="xxScrItem23Txt_A" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
									</ezf:row>
									<ezf:skip>
										<tr id="test">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
										<tr id="">
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
											<td>
												<select style="width: 110px"name="splyAgmtPlnStsCd" ezfname="splyAgmtPlnStsCd" ezflist="splyAgmtPlnStsCd_P,splyAgmtPlnStsDescTxt_P,99, ,noblank" >
													<option>ESS</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
													<option>XXX</option>
												</select>
											</td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="10" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"><input class="pBtn0" type="button" value="..." onClick="sendServer(this)" name="..."></td>
											<td><input type="text" size="12" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="18" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="checkbox" name="xxChkBox_SD" ezfname="xxChkBox_SD" value="Y" onclick="this.blur();this.focus();" ></td>
										</tr>
									</ezf:skip>
								</table>
							</div>
						</div>
					</div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</left>
			
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",4);
</script>

<%-- **** Task specific area ends here **** --%>
