<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161214090339 --%>
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
			<input type="hidden" name="pageID" value="NMAL0230Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Bill Of Material Search">
			
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
					<table width="97%" border="0" cellpadding="0" cellspacing="0">
						<col width="180">
						<col width="250">
						<col width="200">
						<col width="250">
						<col width="100">
						<tr>
							<td>BOM Type</td>
							<td>
								<ezf:select name="mdseItemTpCd_BO" ezfName="mdseItemTpCd_BO" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="mdseItemTpDescTxt_L1" otherAttr=" style=\"width:200px;\""/>
							</td>
							<td>Component Type</td>
							<td>
								<ezf:select name="mdseCmpsnTpCd_CO" ezfName="mdseCmpsnTpCd_CO" ezfBlank="1" ezfCodeName="mdseCmpsnTpCd_L2" ezfDispName="mdseCmpsnTpDescTxt_L2" otherAttr=" style=\"width:200px;\""/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BomItem" otherAttr=" tabindex=\"-1\"">BOM Item#(*)</ezf:anchor></td>
							<td><ezf:inputText name="mdseCd_BO" ezfName="mdseCd_BO" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
							<td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CmpsnItem" otherAttr=" tabindex=\"-1\"">Component Item#(*)</ezf:anchor></td>
							<td><ezf:inputText name="childMdseCd_CO" ezfName="childMdseCd_CO" value="1234567890123456" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td>BOM Item Description(*)</td>
							<td><ezf:inputText name="mdseDescShortTxt_BO" ezfName="mdseDescShortTxt_BO" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/></td>
							<td>Component Item Description(*)</td>
							<td><ezf:inputText name="mdseDescShortTxt_CO" ezfName="mdseDescShortTxt_CO" otherAttr=" size=\"30\" maxlength=\"250\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td>BOM Revision</td>
							<td><ezf:inputText name="cmpsnRevnNum_BO" ezfName="cmpsnRevnNum_BO" value="001" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
							<td>Component Revision</td>
							<td><ezf:inputText name="cmpsnRevnNum_CO" ezfName="cmpsnRevnNum_CO" value="002" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
							<td></td>
						</tr>
						<tr>
							<td>BOM Merch Type</td>
							<td>
								<ezf:select name="coaMdseTpCd_BO" ezfName="coaMdseTpCd_BO" ezfBlank="1" ezfCodeName="coaMdseTpCd_L1" ezfDispName="coaProjDescTxt_L1" otherAttr=" style=\"width:200px;\""/>
							</td>
							<td>Component Merch Type</td>
							<td>
								<ezf:select name="coaMdseTpCd_CO" ezfName="coaMdseTpCd_CO" ezfBlank="1" ezfCodeName="coaMdseTpCd_L2" ezfDispName="coaProjDescTxt_L2" otherAttr=" style=\"width:200px;\""/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_BomProdCd" otherAttr=" tabindex=\"-1\"">BOM Product Code</ezf:anchor></td>
							<td>
								<ezf:inputText name="coaProdCd_BO" ezfName="coaProdCd_BO" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
								<ezf:inputText name="coaProdNm_BO" ezfName="coaProdNm_BO" value="XX" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
							</td>
							<td><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_CmpsnProdCd" otherAttr=" tabindex=\"-1\"">Component Product Code</td>
							<td>
								<ezf:inputText name="coaProdCd_CO" ezfName="coaProdCd_CO" value="XX" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
								<ezf:inputText name="coaProdNm_CO" ezfName="coaProdNm_CO" value="XX" otherAttr=" size=\"25\" maxlength=\"50\" tabindex=\"-1\" ezftoupper=\"\""/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Effective Start</td>
							<td>
								<ezf:inputText name="effFromDt_BO" ezfName="effFromDt_BO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_BO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_BO', 4);">
							</td>
							<td>Effective Start</td>
							<td>
								<ezf:inputText name="effFromDt_CO" ezfName="effFromDt_CO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effFromDt_CO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_CO', 4);">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Effective End</td>
							<td>
								<ezf:inputText name="effThruDt_BO" ezfName="effThruDt_BO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_BO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_BO', 4);">
							</td>
							<td>Effective End</td>
							<td>
								<ezf:inputText name="effThruDt_CO" ezfName="effThruDt_CO" otherAttr=" size=\"10\" maxlength=\"10\" id=\"effThruDt_CO\""/>
								<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_CO', 4);">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Active Only</td>
							<td><ezf:inputCheckBox name="actvFlg_BO" ezfName="actvFlg_BO" value="Y" /></td>
							<td>Active Only</td>
							<td><ezf:inputCheckBox name="actvFlg_CO" ezfName="actvFlg_CO" value="Y" /></td>
							<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>
					<hr>
					<table border="0" cellpadding="0" cellspacing="0">
						<col width="520">
						<col width="550">
						<tr>
							<td>
								<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
							</td>
							<td align="right">
								<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
									<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
									<jsp:param name="TableNm"     value="A" />
									<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
									<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
									<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
									<jsp:param name="ShowingCurrent" value="xxPageShowCurNum" />
									<jsp:param name="ShowingTotal"   value="xxPageShowTotNum" />
									<jsp:param name="ViewMode"       value="FULL" />
								</jsp:include>
							</td>
						</tr>
					</table>
					<div id="parentBoxA">
						<table>
							<tr>
								<td width="10"></td>
								<td>
									<div style="float:left; display:block"> <!-- left table -->
										<div id="leftTblHead" style="display:block;"></div>
										<div id="leftTbl" style="float:left; display:block; height:275px; overflow:hidden; margin:0px; padding:0px;padding-bottom: 20px"></div>
									</div>  <!-- left table -->
									<div style="float:left"> <!-- right table -->
										<div id="rightTblHead" style="width:1083px; display:block; overflow:hidden; margin:0px;padding:0px;">
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1299px" style="margin-right:20px">
												<col width="128" align="center">
												<col width="240" align="center">
												<col width="90" align="center">
												<col width="96" align="center">
												<col width="200" align="center">
												<col width="96" align="center">
												<col width="80" align="center">
												<col width="80" align="center">
												<col width="64" align="center">
												<tr height="24">
													<td id="AH0" class="pClothBs colFix" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseCd_A' )">
															BOM Item#<img id="sortIMG.mdseCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</ezf:anchor>
													</td>
													<td id="AH1" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseDescShortTxt_A' )">
															BOM Description<img id="sortIMG.mdseDescShortTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH2" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemTpNm_A' )">
															BOM Type<img id="sortIMG.mdseItemTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH3" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'cmpsnRevnNum_A' )">
															BOM Revision<img id="sortIMG.cmpsnRevnNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH4" class="pClothBs" align="center">
														<!-- Mod Start 2016/12/12 T.Aoi S21_NA#16591 -->
														<!-- 
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaMdseTpNm_A' )">
															BOM Merch Type<img id="sortIMG.coaMdseTpNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
														-->
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProjDescTxt_A' )">
															BOM Merch Type<img id="sortIMG.coaProjDescTxt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
														<!-- Mod End   2016/12/12 T.Aoi S21_NA#16591 -->
													</td>
													<td id="AH5" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'coaProdCd_A' )">
															BOM Prod Type<img id="sortIMG.coaProdCd_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH6" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effFromDt_A' )">
															Effective Start<img id="sortIMG.effFromDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH7" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'effThruDt_A' )">
															Effective End<img id="sortIMG.effThruDt_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
													<td id="AH8" class="pClothBs" align="center">
														<a href="#" class="pSortCol" onclick="columnSort( 'A', 'actvFlg_A' )">
															Active<img id="sortIMG.actvFlg_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}">
														</a>
													</td>
												</tr>
											</table>
										</div><!-- rightTblHead-->
										<div id="rightTbl" style="width:1096px; height:292px; display:block; overflow:scroll; margin:0px; padding:0px;" >
											<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1300px" >
												<col width="128">
												<col width="240">
												<col width="90">
												<col width="96">
												<col width="200">
												<col width="96">
												<col width="80">
												<col width="80">
												<col width="64">
												<ezf:row ezfHyo="A">
													<tr id="id_row{EZF_ROW_NUMBER}">
														<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="GoToBomMain" ><ezf:label name="mdseCd_A" ezfName="mdseCd_A" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
														<td><ezf:label name="mdseDescShortTxt_A" ezfName="mdseDescShortTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="mdseItemTpNm_A" ezfName="mdseItemTpNm_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="cmpsnRevnNum_A" ezfName="cmpsnRevnNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<!-- Mod Start 2016/12/12 T.Aoi S21_NA#16591 -->
														<!--
														<td><label ezfout name="coaMdseTpNm_A" ezfname="coaMdseTpNm_A" ezfHyo="A">1XXXXXXXXX2XXXXXXXXX3XXXXXXXXX4XXXXXXXXX</label></td>
														-->
														<td><ezf:label name="coaProjDescTxt_A" ezfName="coaProjDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<!-- Mod End   2016/12/12 T.Aoi S21_NA#16591 -->
														<td><ezf:label name="coaProdCd_A" ezfName="coaProdCd_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="effFromDt_A" ezfName="effFromDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="effThruDt_A" ezfName="effThruDt_A" ezfHyo="A" ezfArrayNo="0" /></td>
														<td><ezf:label name="actvFlg_A" ezfName="actvFlg_A" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
												</ezf:row>
											</table>
										</div><!-- rightTbl-->
									</div><!-- right table-->
								</td>
							</tr>
						</table>
					</div><!-- parentBoxA -->
					<div align="right" style="width:97%">
						<ezf:inputButton name="Create" value="Create" htmlClass="pBtn6" />
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript" defer>
    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 2, false);
</script>

<%-- **** Task specific area ends here **** --%>
