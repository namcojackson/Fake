<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170621200606 --%>
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
			<input type="hidden" name="pageID" value="NMAL0140Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Item Cost Search">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<!-- include S21BusinessProcessTAB --> 
													<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
												</div>
											</td>
										</tr>
									</table>
								</ul>
							</div>
							<div class="pTab_BODY_In">
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="150">
									<col width="245">
									<col width="115">
									<col width="285">
									<col width="">
									<tr>
										<td class="stab">Saved Search Options</td>
										<td>
											<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:220px\" id=\"srchOptPk\""/>
										</td>
										<td class="stab">Search Option Name</td>
										<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"33\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>
											<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
											<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">
								<table border="0" cellpadding="0" cellspacing="0" height="120">
									<tr>
										<td valign="top">
                                        </td>
										<td valign="top" width="420">
											<table>
												<col align="left" width="150">
												<col align="left" width="245">
												<tr>
													<td class="stab">Cost Type</td>
													<td><ezf:select name="mdseCstUpdTpCd_H1" ezfName="mdseCstUpdTpCd_H1" ezfBlank="1" ezfCodeName="mdseCstUpdTpCd_L1" ezfDispName="mdseCstUpdTpNm_L1" otherAttr=" style=\"width:150\""/></td>
												</tr>
												<tr>
													<td class="stab">Cost Update Group(*)</td>
													<td><ezf:inputText name="mdseCstUpdGrpTxt_H1" ezfName="mdseCstUpdGrpTxt_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Reference(*)</td>
													<td><ezf:inputText name="mdseCstUpdRefTxt_H1" ezfName="mdseCstUpdRefTxt_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Reference Id(*)</td>
													<td><ezf:inputText name="mdseCstUpdRefId_H1" ezfName="mdseCstUpdRefId_H1" value="XX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
												</tr>
											</table>
										</td>
										<td valign="top" width="420">
											<table >
												<col align="left" width="110">
												<col align="left" width="250">
												<tr>
													<td><ezf:anchor name="" ezfName="xxLinkProt_01" ezfEmulateBtn="1" ezfGuard="Merchandise_Link" otherAttr=" tabindex=\"-1\"">Item Number(*)</ezf:anchor></td>
													<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
												</tr>
												<tr>
													<td class="stab">Item Type</td>
													<td><ezf:select name="mdseItemTpCd_H1" ezfName="mdseItemTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="mdseItemTpNm_L1" />
													</td>
												</tr>
												<tr>
													<td class="stab">Merchandise Type</td>
													<td><ezf:select name="coaMdseTpCd_H1" ezfName="coaMdseTpCd_H1" ezfBlank="1" ezfCodeName="coaProjCd_L1" ezfDispName="xxScrItem54Txt_L1" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_02" ezfEmulateBtn="1" ezfGuard="COA_Product_Link" otherAttr=" tabindex=\"-1\"">Prod Code</ezf:anchor></td>
													<td>
                                                        <ezf:inputText name="coaProdCd_H1" ezfName="coaProdCd_H1" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="coaProdNm_H1" ezfName="coaProdNm_H1" value="XX" otherAttr=" size=\"28\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td colspan="2">&nbsp;</td>
												</tr>
												<tr>
													<td colspan="2">&nbsp;</td>
												</tr>
											</table>
										</td>


										<td valign="top" width="350">
											<table border="0" width="100%">
												<col align="left" width="120">
												<col align="left" width="200">
												<tr>
													<td class="stab">Effective Date From</td>
													<td><ezf:inputText name="mdseCstUpdEffFromDt_H1" ezfName="mdseCstUpdEffFromDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
                                                    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('mdseCstUpdEffFromDt_H1', 4);" ></td>
												</tr>
												<tr>
													<td class="stab">Current Cost Only</td>
													<td><ezf:inputCheckBox name="xxChkBox_CC" ezfName="xxChkBox_CC" value="Y" /></td>
												</tr>
												<tr>
													<td class="stab">WH Type</td>
													<td><ezf:select name="rtlWhCatgCd_H1" ezfName="rtlWhCatgCd_H1" ezfBlank="1" ezfCodeName="rtlWhCatgCd_L1" ezfDispName="rtlWhCatgDescTxt_L1" />
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_03" ezfEmulateBtn="1" ezfGuard="WH_Link" otherAttr=" tabindex=\"-1\"">Warehouse</ezf:anchor></td>
													<td>
                                                        <ezf:inputText name="rtlWhCd_H1" ezfName="rtlWhCd_H1" value="XX" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="rtlWhNm_H1" ezfName="rtlWhNm_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_04" ezfEmulateBtn="1" ezfGuard="Sub_WH_Link" otherAttr=" tabindex=\"-1\"">Sub Warehouse</ezf:anchor></td>
													<td>
                                                        <ezf:inputText name="rtlSwhCd_H1" ezfName="rtlSwhCd_H1" value="XX" otherAttr=" size=\"6\" maxlength=\"20\" ezftoupper=\"\""/>&nbsp;<ezf:inputText name="rtlSwhNm_H1" ezfName="rtlSwhNm_H1" value="XX" otherAttr=" size=\"15\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
													</td>
												</tr>
												<tr>
													<td colspan="2">&nbsp;</td>
												</tr>
												<tr align="right">
													<td colspan="2" valign="bottom" align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
												</tr>
											</table>
										</td>
										<td valign="top" align="right" width="15">
											<table border="0">
												<tr>
													<td colspan="2">&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<hr>
<div id="parentBoxA">
								<table border="0" cellpadding="1" cellspacing="0" height="24">
									<col width="60">
									<col width="1055">
									<tr>
										<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
										<td  align="right">
											<table>
												<tr>
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
														<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
														<jsp:param name="TableNm"     value="A" />
														<jsp:param name="ShowingFrom" value="xxPageShowFromNum_10" />
														<jsp:param name="ShowingTo"   value="xxPageShowToNum_10" />
														<jsp:param name="ShowingOf"   value="xxPageShowOfNum_10" />
														<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_10" />
														<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_10" />
														<jsp:param name="ViewMode"       value="FULL" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table style="table-layout:fixed;" border="0" cellpadding="1" cellspacing="0" >
									<tr>
										<td valign="Top" width="1132">
<div style="float:left; display:block"> <!-- left table -->
          <div id="leftTblHead" style="display:block">
          </div>
          <div id="leftTbl"style="float:left; display:block; height:255px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
          </div>
    </div> <!-- left table -->
    <div style="float:left"> <!-- right table -->
    <div id="rightTblHead" style="width:1100px; display:block; overflow:hidden; margin:0px;padding:0px;">
    <table border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1100px" style="margin-right:20px; table-layout:fixed">

											<%-- ### MEISAI - Right TBL - TOP --%>
<!--
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:1115; height:24; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1765" height="24" id="AHEAD">
-->
													<col align="center" width="20">
													<col align="center" width="140">
													<col align="center" width="165">
													<col align="center" width="100">
													<col align="center" width="160">
													<col align="center" width="90">
													<col align="center" width="90">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="150">
													<col align="center" width="200">
													<tr>
														<td class="pClothBs" id="AH1"></td>
														<td class="pClothBs" id="AH2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A1' )" tabindex="-1">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH3"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )" tabindex="-1">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH4"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdTpNm_A1' )" tabindex="-1">Cost Type<img id="sortIMG.mdseCstUpdTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH5"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxScrItem54Txt_A1' )" tabindex="-1">Merchandise Type<img id="sortIMG.xxScrItem54Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH6"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstTotStdCostAmt_A1' )" tabindex="-1">Cost<img id="sortIMG.rqstTotStdCostAmt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH7"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rqstTotStdCostAmt_SB' )" tabindex="-1">Swh Cost<img id="sortIMG.rqstTotStdCostAmt_SB" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>

														<td class="pClothBs" id="AH8"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdEffFromDt_A1' )" tabindex="-1">Effective Date<img id="sortIMG.mdseCstUpdEffFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH9"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdCratDt_A1' )" tabindex="-1">Posted Date<img id="sortIMG.mdseCstUpdCratDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH10"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdStsNm_A1' )" tabindex="-1">Status<img id="sortIMG.mdseCstUpdStsNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH11"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdGrpTxt_A1' )" tabindex="-1">Cost Update Group<img id="sortIMG.mdseCstUpdGrpTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH12"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCstUpdRefTxt_A1' )" tabindex="-1">Reference<img id="sortIMG.mdseCstUpdRefTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs" id="AH13"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxFullNm_A1' )" tabindex="-1">Submitted BY<img id="sortIMG.xxFullNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
<div  id="rightTbl" style="width:1117px; height:272px; display:block; overflow:scroll; margin:0px; padding:0px;" >
<table border="1" cellpadding="1" cellspacing="0" id="A" width="1117px" style="table-layout:fixed">
<!--
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:255; width:790; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="790" id="A1">
-->
													<col align="center" width="20">
													<col align="left" width="140">
													<col align="left" width="165">
													<col align="left" width="100">
													<col align="left" width="160">
													<col align="right" width="90">
													<col align="right" width="90">
													<col align="left" width="150">
													<col align="left" width="150">
													<col align="left" width="150">
													<col align="left" width="150">
													<col align="left" width="150">
													<col align="left" width="200">

													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_H1{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="xxDtlCd_A1" ezfName="xxDtlCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="rqstTotStdCostAmt_SB" ezfName="rqstTotStdCostAmt_SB" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>

														<td><ezf:inputText name="mdseCstUpdEffFromDt_A1" ezfName="mdseCstUpdEffFromDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdCratDt_A1" ezfName="mdseCstUpdCratDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdRefTxt_A1" ezfName="mdseCstUpdRefTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxFullNm_A1" ezfName="xxFullNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>

													</tr>
													</ezf:row>
													<ezf:skip>
													<tr height="24">
														<td><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn_H1{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="xxDtlCd_A1" ezfName="xxDtlCd_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"23\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdTpNm_A1" ezfName="mdseCstUpdTpNm_A1" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"14\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="rqstTotStdCostAmt_A1" ezfName="rqstTotStdCostAmt_A1" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="rqstTotStdCostAmt_SB" ezfName="rqstTotStdCostAmt_SB" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>

														<td><ezf:inputText name="mdseCstUpdEffFromDt_A1" ezfName="mdseCstUpdEffFromDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdCratDt_A1" ezfName="mdseCstUpdCratDt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdStsNm_A1" ezfName="mdseCstUpdStsNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdGrpTxt_A1" ezfName="mdseCstUpdGrpTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="mdseCstUpdRefTxt_A1" ezfName="mdseCstUpdRefTxt_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="xxFullNm_A1" ezfName="xxFullNm_A1" value="WWWWWWWWW1WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													</ezf:skip>
												</table>
											</div>
<!--
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:272; width:325; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="950" id="A2">
													<ezf:row ezfHyo="A">
													<tr height="24">
													</tr>
													</ezf:row>

													<ezf:skip>
													<tr height="24">
													</tr>
													</ezf:skip>
												</table>
											</div>
-->
										</td>
									</tr>
								</table>
</div>
</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="right" height="28">
                            <ezf:inputButton name="ViewRequest" value="View Cost Update Request" htmlClass="pBtn14" />&nbsp;&nbsp;
                        </td>
					</tr>
				</table>
			</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 3, false);
</script>

<%-- **** Task specific area ends here **** --%>
