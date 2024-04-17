<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160407141058 --%>
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
			<input type="hidden" name="pageID" value="NMAL7070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Agreement Plan Search">
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
<%-- Save Option					--%>
<%------------------------------------%>
				<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
					<col width="152">
					<col width="345">
					<col width="110">
					<col width="300">
					<col width="">
					<tr>
						<td class="stab">Saved Search Options</td>
						<td>
							<ezf:select name="srchOptPk" ezfName="srchOptPk" ezfBlank="1" ezfCodeName="srchOptPk_L1" ezfDispName="srchOptNm_L1" otherEvent1=" onchange=\"sendServer('OnChange_SavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
						</td>
						<td class="stab">Search Option Name</td>
						<td class="stab"><ezf:inputText name="srchOptNm" ezfName="srchOptNm" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\""/></td>
						<td>
							<ezf:inputButton name="Save_Search" value="Save Search" htmlClass="pBtn8" />
							<ezf:inputButton name="Delete_Search" value="Delete Search" htmlClass="pBtn8" />
						</td>
					</tr>
				</table>
				<hr style="height: 0px;" cellpadding="0">
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<table border="0" cellpadding="0" cellspacing="0" height="140" width="1100" rules="none"  style="padding: 10px; margin-bottom: 5px; solid #333333;">
					<tr>
						<td valign="top" width="1100">
							<table cellpadding="0" border="0">
								<col align="left" width="135">
								<col align="left" width="300">
								<col align="left" width="155">
								<col align="left" width="250">
								<col align="left" width="110">
								<col align="left" width="140">
								<tr>
									<td class="stab">Plan Name(*)</td>
									<td><ezf:inputText name="splyAgmtPlnNm" ezfName="splyAgmtPlnNm" value="Price List Name" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
									<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_AccountSearch','accountNum')">Account Number(*)</a></td>
									<td>
										<ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" otherAttr=" size=\"28\" maxlength=\"20\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Effective Date From</td>
									<td>
										<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Plan Short Name(*)</td>
									<td><ezf:inputText name="splyAgmtPlnShortNm" ezfName="splyAgmtPlnShortNm" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
									<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_AccountSearch','accountNm')">Account Name(*)</a></td>
									<td>
										<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" otherAttr=" size=\"35\" maxlength=\"360\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Effective Date To</td>
									<td>
										<ezf:inputText name="effThruDt" ezfName="effThruDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
										<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
									</td>
								</tr>
								<tr>
									<td class="stab">Plan Description/Notes(*)</td>
									<td><ezf:inputText name="splyAgmtPlnDescTxt" ezfName="splyAgmtPlnDescTxt" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
									<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_CSMP','CSMP')">CSMP#(*)</a></td>
									<td>
										<ezf:inputText name="csmpNum" ezfName="csmpNum" otherAttr=" size=\"15\" maxlength=\"15\" ezftoupper=\"\""/>
									</td>
									<td class="stab">Status</td>
									<td valign="middle">
										<ezf:select name="splyAgmtPlnStsCd" ezfName="splyAgmtPlnStsCd" ezfCodeName="splyAgmtPlnStsCd_P" ezfDispName="splyAgmtPlnStsDescTxt_P" otherAttr=" style=\"width: 150px\""/>
									</td>
								</tr>
								<tr>
									<td class="stab">Plan Type</td>
									<td>
										<ezf:select name="splyAgmtPlnTpCd" ezfName="splyAgmtPlnTpCd" ezfBlank="1" ezfCodeName="splyAgmtPlnTpCd_P" ezfDispName="splyAgmtPlnTpDescTxt_P" otherAttr=" style=\"width: 250px\""/>
									<td class="stab"><a href="#" onclick="submitFromPluralFields('OpenWin_Branch','Branch')">Branch#(*)</a></td>
									<td>
										<ezf:inputText name="coaBrCd" ezfName="coaBrCd" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="stab">Document Type</td>
									<td>
										<ezf:select name="splyAgmtDocTpCd" ezfName="splyAgmtDocTpCd" ezfBlank="1" ezfCodeName="splyAgmtDocTpCd_P" ezfDispName="splyAgmtDocTpDescTxt_P" otherAttr=" style=\"width: 250px\""/>
									</td>
									<td class="stab">Line Of Business</td>
									<td>
										<ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_P" ezfDispName="lineBizTpDescTxt_P" otherAttr=" style=\"width: 100px\""/>
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn8" otherAttr=" id=\"search\""/></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
				<table border="0" cellpadding="01" cellspacing="0" width="1085" align="center"  rules="none"  style="padding: 10px; margin-bottom: 10px;">
					<tr align="right">
						<col width="150"  align="center">
						<col width="400"  align="center">
						<col width="550"  align="right">
						<td>
							<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
						</td>
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
							<div id="leftTbl" style="float:left; display:block; height:254px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
							</div>
						</div>  <!-- left table -->
						<div style="float:left"> <!-- right table -->
							<div id="rightTblHead" style="width:1069px; display:block; overflow:hidden; margin:0px;padding:0px;">
								<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="AHEAD" width="1069px" style="margin-right:20px">
									<col align="center" width="25">		<!-- Select					-->
									<col align="center" width="60">		<!-- Plan ID				-->
									<col align="center" width="160">	<!-- Plan Name				-->
									<col align="center" width="160">	<!-- Plan Short Name		-->
									<col align="center" width="160">	<!-- Plan Description		-->
									<col align="center" width="122">	<!-- Plan Type				-->
									<col align="center" width="72">		<!-- Status					-->
									<col align="center" width="135">	<!-- Document Type			-->
									<col align="center" width="70">		<!-- Term Cap				-->
									<col align="center" width="100">	<!-- Eff From				-->
									<col align="center" width="100">	<!-- Eff To					-->
									<col align="center" width="110">	<!-- Creation Date 			-->
									<col align="center" width="150">	<!-- Created By				-->
									<col align="center" width="110">	<!-- Last Update Date		-->
									<col align="center" width="150">	<!-- Last Update By			-->
									<tr height="25">
										<td id="AH0"  class="pClothBs">&nbsp;<br>&nbsp;</td>
										<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAgmtPlnPk_A' )">Plan ID<img id="sortIMG.splyAgmtPlnPk_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAgmtPlnNm_A' )">Plan Name<img id="sortIMG.splyAgmtPlnNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAgmtPlnShortNm_A' )">Plan Short Name<img id="sortIMG.splyAgmtPlnShortNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAgmtPlnDescTxt_A' )">Plan Description<img id="sortIMG.splyAgmtPlnDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAgmtPlnTpNm_A' )">Plan Type<img id="sortIMG.splyAgmtPlnTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem8Txt_A' )">Status<img id="sortIMG.xxScrItem8Txt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'splyAgmtDocTpNm_A' )">Document Type<img id="sortIMG.splyAgmtDocTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'annTermCapNum_A' )">Term Cap<img id="sortIMG.annTermCapNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">Eff From<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">Eff To<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDt10Dt_CD' )">Creation Date<img id="sortIMG.xxDt10Dt_CD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_CB' )">Created By<img id="sortIMG.xxFullNm_CB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDt10Dt_UD' )">Last Update Date<img id="sortIMG.xxDt10Dt_UD" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_UB' )">Last Update By<img id="sortIMG.xxFullNm_UB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							<div id="rightTBL" style="width:1085px; height:271px; display:block; overflow:scroll; margin:0px; padding:0px;"  onScroll="synchroScrollLeft(this.id, new Array('rightTblHead'));">
								<table border="1" cellpadding="0" cellspacing="0" id="A" style="table-layout: fixed" width="1085px">
									<col align="center" width="25">		<!-- Select					-->
									<col align="center" width="60">		<!-- Plan ID				-->
									<col align="center" width="160">	<!-- Plan Name				-->
									<col align="center" width="160">	<!-- Plan Short Name		-->
									<col align="center" width="160">	<!-- Plan Description		-->
									<col align="center" width="122">	<!-- Plan Type				-->
									<col align="center" width="72">		<!-- Status					-->
									<col align="center" width="135">	<!-- Document Type			-->
									<col align="center" width="70">		<!-- Term Cap				-->
									<col align="center" width="100">	<!-- Eff From				-->
									<col align="center" width="100">	<!-- Eff To					-->
									<col align="center" width="110">	<!-- Creation Date 			-->
									<col align="center" width="150">	<!-- Created By				-->
									<col align="center" width="110">	<!-- Last Update Date		-->
									<col align="center" width="150">	<!-- Last Update By			-->
									<% int valueOfxxRadioBtn = 0; %>
									<ezf:row ezfHyo="A">
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"Radio{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="splyAgmtPlnNm_A" ezfName="splyAgmtPlnNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<td><ezf:inputText name="splyAgmtPlnShortNm_A" ezfName="splyAgmtPlnShortNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<td><ezf:inputText name="splyAgmtPlnDescTxt_A" ezfName="splyAgmtPlnDescTxt_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
											<td><ezf:inputText name="splyAgmtPlnTpNm_A" ezfName="splyAgmtPlnTpNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
											<td><ezf:label name="xxScrItem8Txt_A" ezfName="xxScrItem8Txt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="splyAgmtDocTpNm_A" ezfName="splyAgmtDocTpNm_A" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\""/></td>
											<td><ezf:label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxDt10Dt_CD" ezfName="xxDt10Dt_CD" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxFullNm_CB" ezfName="xxFullNm_CB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"71\""/></td>
											<td><ezf:label name="xxDt10Dt_UD" ezfName="xxDt10Dt_UD" ezfHyo="A" ezfArrayNo="0" /></td>
											<td><ezf:inputText name="xxFullNm_UB" ezfName="xxFullNm_UB" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"71\""/></td>
										</tr>
										<% valueOfxxRadioBtn++; %>
									</ezf:row>
									<ezf:skip>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
										<tr id="id_leftA_row{EZF_ROW_NUMBER}">
											<td><input type ="radio" name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="<%= Integer.toString(valueOfxxRadioBtn) %>" /></td>
											<td><label name="splyAgmtPlnPk_A" ezfName="splyAgmtPlnPk_A" ezfHyo="A" ezfArrayNo="0" >XXXXXX</label></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnNm_A" ezfname="splyAgmtPlnNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnShortNm_A" ezfname="splyAgmtPlnShortNm_A" ezfHyo="A"></td>
											<td><input type="text" size="20" maxlength="50" value="" name="splyAgmtPlnDescTxt_A" ezfname="splyAgmtPlnDescTxt_A" ezfHyo="A"></td>
											<td><input type="text" size="8"  maxlength="30" value="" name="splyAgmtPlnTpNm_A" ezfname="splyAgmtPlnTpNm_A" ezfHyo="A"></td>
											<td><label name="prcCatgCd_A1" ezfName="prcCatgCd_A1" ezfHyo="A" ezfArrayNo="0" >XXXXXXXX</label></td>
											<td><input type="text" size="16" maxlength="30" value="" name="splyAgmtDocTpNm_A" ezfname="splyAgmtDocTpNm_A" ezfHyo="A"></td>
											<td><label name="annTermCapNum_A" ezfName="annTermCapNum_A" ezfHyo="A" ezfArrayNo="0" >XX</label></td>
											<td><label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><label name="ezInTime" ezfName="ezInTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_CB" ezfname="xxFullNm_CB" ezfHyo="A"></td>
											<td><label name="ezUpTime" ezfName="ezUpTime" ezfHyo="A" ezfArrayNo="0" >00/00/0000</label></td>
											<td><input type="text" size="20" maxlength="71" value="" name="xxFullNm_UB" ezfname="xxFullNm_UB" ezfHyo="A"></td>
										</tr>
									</ezf:skip>
								</table>
							</div>
						</div>
					</div>
				</td>
				</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0" height="20" width="1100">
					<col align="right" width="900">
					<col align="right" width="100">
					<col align="right" width="100">
					<col align="right" width="30">
						<tr>
							<td></td>
							<td><ezf:inputButton name="newRegistration" value="New Registration" htmlClass="pBtn8" otherAttr=" id=\"newRegistration\""/></td>
							<td><ezf:inputButton name="viewDetails" value="View Details" htmlClass="pBtn8" otherAttr=" id=\"viewDetails\""/></td>
							<td></td>
						</tr>
				</table>
			</td>
		</tr>
	</table>
</left>
			
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A",3);
</script>
		
			

<%-- **** Task specific area ends here **** --%>
