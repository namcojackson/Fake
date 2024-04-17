<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20200221093642 --%>
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
			<input type="hidden" name="pageID" value="NMAL7180Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Group Search">
<center>
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
										<li title="Price Group Search" class="pTab_ON"><a href="#">Prc Grp Srch</a></li>
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
<%------------------------------------%>
<%-- Header							--%>
<%------------------------------------%>
				<div class="pTab_BODY">
					<table border="0" cellpadding="0" cellspacing="0" height="120">
						<tr>
							<td valign="top">
								<table cellpadding="0" border="0">
									<col align="left" width="140">
									<col align="left" width="360">
									<col align="left" width="5">
									<col align="left" width="110">
									<col align="left" width="120">
									<col align="left" width="210">
									<col align="left" width="120">
									<tr>
										<td class="stab">Pricing Group Name(*)</td>
										<td><ezf:inputText name="prcGrpNm" ezfName="prcGrpNm" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Active</td>
										<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Group Description(*)</td>
										<td><ezf:inputText name="prcGrpDescTxt" ezfName="prcGrpDescTxt" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Effective Date From</td>
										<td>
											<ezf:inputText name="effFromDt" ezfName="effFromDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Pricing Group Type</td>
										<td>
											<ezf:select name="prcGrpTpCd" ezfName="prcGrpTpCd" ezfBlank="1" ezfCodeName="prcGrpTpCd_P" ezfDispName="prcGrpTpDescTxt_P" otherAttr=" style=\"width:355;\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Effective Date To</td>
										<td>
											<ezf:inputText name="effThruDt" ezfName="effThruDt" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Target Context</td>
										<td>
											<ezf:select name="prcGrpTrgtTpCd" ezfName="prcGrpTrgtTpCd" ezfBlank="1" ezfCodeName="prcGrpTrgtTpCd_P" ezfDispName="prcGrpTrgtTpDescTxt_P" otherEvent1=" onchange=\"sendServer('OnChange_TrgtContext')\"" otherAttr=" style=\"width:355;\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Attribute Name</td>
										<td>
											<ezf:select name="prcGrpTrgtAttrbCd" ezfName="prcGrpTrgtAttrbCd" ezfBlank="1" ezfCodeName="prcGrpTrgtAttrbCd_P" ezfDispName="prcGrpTrgtAttrbDescTxt_P" otherAttr=" style=\"width:355;\""/></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Attribute Target From(*)</td>
										<td><ezf:inputText name="prcGrpFromTxt" ezfName="prcGrpFromTxt" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
										<td class="stab">Attribute Target To(*)</td>
										<td colspan="2"><ezf:inputText name="prcGrpThruTxt" ezfName="prcGrpThruTxt" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Transaction Type</td>
										<td>
											<ezf:select name="prcGrpTrxTpCd" ezfName="prcGrpTrxTpCd" ezfBlank="1" ezfCodeName="prcGrpTrxTpCd_P" ezfDispName="prcGrpTrxTpDescTxt_P" otherAttr=" style=\"width:355;\""/></td>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td align="right" valign="middle" height="24">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">
<%------------------------------------%>
<%-- Detail							--%>
<%------------------------------------%>
					<table border="0" cellpadding="0" cellspacing="0" width="1105" align="center"  rules="none"  style="padding: 4px; margin-bottom: 1px;">
						<tr align="left">
							<col width="120"  align="center">
							<col width="50">
							<col align="left">
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td>&nbsp;</td>
							<td>
								<ezf:inputButton name="MoveWin_PrcGrpSetUp" value="View Group" htmlClass="pBtn6" />
								<ezf:inputButton name="MoveWin_PrcGrpSetUpNew" value="New Group" htmlClass="pBtn6" />
							</td>
						</tr>
					</table>
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="5"></td>
							<td>
								<div id="parentBoxA">
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;overflow:hidden;margin:0px;padding:0px;">
										</div>
										<div id="leftTbl" style="float:left; display:block; height:345px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
										</div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1085px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="1" cellspacing="0" id="AHEAD" width="1555px" style="margin-right:20px">
												<col align="center" width="025">	<!-- Select						-->
												<col align="center" width="210">	<!-- Pricing Group Name			-->
												<col align="center" width="210">	<!-- Group Description			-->
												<col align="center" width="210">	<!-- Pricing Group Type			-->
												<col align="center" width="060">	<!-- Active						-->
												<col align="center" width="100">	<!-- Date From					-->
												<col align="center" width="100">	<!-- Date To					-->
												<col align="center" width="110">	<!-- Transaction Type			-->
												<col align="center" width="040">	<!-- Precedence#				-->
												<tr>
													<td id="AH0"  class="pClothBs colFix">&nbsp;</td>
													<td id="AH1"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpNm_A1' )">Pricing Group Name<img id="sortIMG.prcGrpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH2"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpDescTxt_A1' )">Group Description<img id="sortIMG.prcGrpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH3"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpTpDescTxt_A1' )">Pricing Group Type<img id="sortIMG.prcGrpTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH4"  class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'actvFlg_A1' )">Active<img id="sortIMG.actvFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH5" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A1' )">Date From<img id="sortIMG.effFromDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A1' )">Date To<img id="sortIMG.effThruDt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													<td id="AH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'prcGrpTrxTpDescTxt_A1' )">Transaction Type<img id="sortIMG.prcGrpTrxTpDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												</tr>
											</table>
										</div>
										<div id="rightTbl" style="width:1102px; height:345px; display:block; overflow-y:scroll; margin:0px; padding:0px;" >
											<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed" width="1555px">
												<col align="center" width="25">		<!-- Select						-->
												<col width="210">					<!-- Pricing Group Name			-->
												<col width="210">					<!-- Group Description			-->
												<col width="210">					<!-- Pricing Group Type			-->
												<col align="center" width="60">		<!-- Active						-->
												<col align="center" width="100">	<!-- Date From					-->
												<col align="center" width="100">	<!-- Date To					-->
												<col align="left"   width="110">	<!-- Transaction Type			-->
												<col align="right"  width="040">	<!-- Precedence#				-->
												<ezf:row ezfHyo="A">
													<tr height="28" id="id_leftA_row{EZF_ROW_NUMBER}">
														<td><ezf:inputRadio name="xxRadioBtn" ezfName="xxRadioBtn" value="Y" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn{EZF_ROW_NUMBER}\""/></td>
														<td><ezf:inputText name="prcGrpNm_A1" ezfName="prcGrpNm_A1" value="123456789A123456789B123456789C123456789D123456789E" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
														<td><ezf:inputText name="prcGrpDescTxt_A1" ezfName="prcGrpDescTxt_A1" value="123456789A123456789B123456789C123456789D123456789E" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
														<td><ezf:inputText name="prcGrpTpDescTxt_A1" ezfName="prcGrpTpDescTxt_A1" value="123456789A123456789B123456789C123456789D123456789E" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"50\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
														<td><ezf:label name="actvFlg_A1" ezfName="actvFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:inputText name="prcGrpTrxTpDescTxt_A1" ezfName="prcGrpTrxTpDescTxt_A1" value="Equipment123456" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" style=\"border:none;background-color:transparent;padding:0px;\""/>
													</tr>
												</ezf:row>
												<ezf:skip>
												</ezf:skip>
											</table>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", -1, false);
</script>

<%-- **** Task specific area ends here **** --%>
