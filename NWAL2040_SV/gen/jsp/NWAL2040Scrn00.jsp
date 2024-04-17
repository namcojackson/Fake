<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20170920114139 --%>
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
			<input type="hidden" name="pageID" value="NWAL2040Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Default WH Setup">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<%-- #################################################### HEADER ################################################### --%>
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
				<div class="pTab_BODY">
					
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
						</tr>
					</table>
<%-- #################################################### DETAIL ################################################### --%>
<%
    String SrcCatgFlg = ((business.servlet.NWAL2040.NWAL2040Bean)databean).getXxTabProt_SC();
    String MapTmpQlfyFlg = ((business.servlet.NWAL2040.NWAL2040Bean)databean).getXxTabProt_MT();
    String MapTmpQlfyRmaFlg = ((business.servlet.NWAL2040.NWAL2040Bean)databean).getXxTabProt_MR();
    String xxDplyTab_H1 = ((business.servlet.NWAL2040.NWAL2040Bean)databean).getXxDplyTab_H1();
%>

						<table width="100%">
							<col valign="top">
							<tr>
								<td>

									<div class="pTab_HEAD">
										<ul>
<%if(SrcCatgFlg.equals("Y")){%>
											<li class="pTab_OFF" id="SrcCatg" title="Src Catg"><ezf:anchor name="" ezfName="xxTabProt_08" ezfEmulateBtn="1" ezfGuard="TAB_SrcCatg" >Src Catg</ezf:anchor></li>
<%}%>
<%if(MapTmpQlfyFlg.equals("Y")){%>
											<li class="pTab_OFF" id="MapTmpQlfy" title="Map Tmp"><ezf:anchor name="" ezfName="xxTabProt_08" ezfEmulateBtn="1" ezfGuard="TAB_MapTmpQlfy" >Map Tmp</ezf:anchor></li>
<%}%>
<%if(MapTmpQlfyRmaFlg.equals("Y")){%>
											<li class="pTab_OFF" id="MapTmpQlfyRMA" title="Map Tmp RMA"><ezf:anchor name="" ezfName="xxTabProt_08" ezfEmulateBtn="1" ezfGuard="TAB_MapTmpQlfyRMA" >Map Tmp RMA</ezf:anchor></li>
<%}%>
										</ul>
									</div>

<%-- Sourcing Category Start --%>
<%if(SrcCatgFlg.equals("Y") && xxDplyTab_H1.equals("SrcCatg")){%>
										<script type="text/javascript">
<%if(SrcCatgFlg.equals("Y")){%>
											document.getElementById( "SrcCatg" ).className = "pTab_ON";
<%}%>
<%if(MapTmpQlfyFlg.equals("Y")){%>
											document.getElementById( "MapTmpQlfy" ).className = "pTab_OFF";
<%}%>
<%if(MapTmpQlfyRmaFlg.equals("Y")){%>
											document.getElementById( "MapTmpQlfyRMA" ).className = "pTab_OFF";
<%}%>
										</script>


									<div class="pTab_BODY_In">
									    <div>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<table>
															<tr>
																<td class="stab"><ezf:inputButton name="InsertRow_SRC_CATG" value="Insert Row" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="DeleteRow_SRC_CATG" value="Delete Row" htmlClass="pBtn6" /></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td colspan="2">
													<div id="topTBL" style="overflow-x:none; height:20; width:905; overflow-y:none;">
														<table border="1" style="table-layout:fixed;"  cellpadding="1" cellspacing="0">
															<col width="25"  align="center">
															<col width="200" align="center">
															<col width="200" align="center">
															<col width="140" align="center">
															<col width="140" align="center">
															<col width="100" align="center">
															<col width="100" align="center">

															<tr>
																<td class="pClothBs">&nbsp;&nbsp;</td>
																<td class="pClothBs">Order Category</td>
																<td class="pClothBs">Order Reason Code</td>
																<td class="pClothBs">Mapping Template Name</td>
																<td class="pClothBs">Inventory Owner</td>
																<td class="pClothBs">Effective Date</td>
																<td class="pClothBs">End Date</td>
															</tr>
														</table>
													</div>
													<div id="bottomTBL" style="word-break:break-all; width:920; height:470; overflow-y:auto;">
														<table border="1" style="table-layout:fixed;" cellpadding="1" cellspacing="0" id="B1">
															<col width="25" align="center">
															<col width="200" align="center">
															<col width="200" align="center">
															<col width="140" align="center">
															<col width="140" align="center">
															<col width="100" align="center">
															<col width="100" align="center">

															<ezf:row ezfHyo="B">
															<tr>
																<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																<!--DS_ORD_CATG_CD-->
																<td><ezf:select name="dsOrdCatgCd_B1" ezfName="dsOrdCatgCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsOrdCatgCd_L1" ezfDispName="dsOrdCatgNm_L1" otherEvent1=" onchange=\"sendServer('Change_DsOrdCatgPulldown', '{EZF_ROW_NUMBER}');\"" otherAttr=" style=\"width: 200px\""/></td>
																<!--DS_ORD_TP_CD-->
																<td><ezf:select name="dsOrdTpCd_B1" ezfName="dsOrdTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsOrdTpCd_BL" ezfDispName="dsOrdTpNm_BL" ezfCodeDispHyo="B" otherAttr=" style=\"width: 200px\""/></td>
																<!--FIRST_BIZ_CTX_ATTRB_TXT-->
																<!--DEF_WH_MAP_TMPL_CD-->
																<td><ezf:select name="firstBizCtxAttrbTxt_B1" ezfName="firstBizCtxAttrbTxt_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="defWhMapTmplCd_L1" ezfDispName="defWhMapTmplNm_L1" otherAttr=" style=\"width: 140px\""/></td>
																<td><ezf:select name="scdBizCtxAttrbTxt_B1" ezfName="scdBizCtxAttrbTxt_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="invtyOwnrCd_L1" ezfDispName="invtyOwnrNm_L1" otherAttr=" style=\"width: 140px\""/></td>
																<!--EFF_FROM_DT-->
																<td><ezf:inputText name="effFromDt_B1" ezfName="effFromDt_B1" value="mm/dd/yyyy" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B1', 4, '{EZF_ROW_NUMBER}');"></td>
																<!--EFF_THRU_DT-->
																<td><ezf:inputText name="effThruDt_B1" ezfName="effThruDt_B1" value="mm/dd/yyyy" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/><img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B1', 4, '{EZF_ROW_NUMBER}');"></td>
															</tr>
															</ezf:row>
															<ezf:skip>
															</ezf:skip>
														</table>
													</div>
													</td>
												</tr>
											</table>


										</div>
									</div>

<%}%>
<%-- Sourcing Category End --%>

<%-- Mapping Template Qualify Start --%>
<%if(MapTmpQlfyFlg.equals("Y") && xxDplyTab_H1.equals("MapTmpQlfy")){%>
										<script type="text/javascript">
<%if(SrcCatgFlg.equals("Y")){%>
											document.getElementById( "SrcCatg" ).className = "pTab_OFF";
<%}%>
<%if(MapTmpQlfyFlg.equals("Y")){%>
											document.getElementById( "MapTmpQlfy" ).className = "pTab_ON";
<%}%>
<%if(MapTmpQlfyRmaFlg.equals("Y")){%>
											document.getElementById( "MapTmpQlfyRMA" ).className = "pTab_OFF";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
<!--									    <div style="OVERFLOW-Y: scroll; HEIGHT: 513px" >-->
									    <div>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<table>
															<col width="200">
															<col width="220">
															<col width="80">
															<col width="600">
															<tr>
																<td class="stab"><ezf:inputButton name="InsertRow_MAP_TMPL_QLFY" value="Insert Row" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="DeleteRow_MAP_TMPL_QLFY" value="Delete Row" htmlClass="pBtn6" /></td>
																<!--DEF_WH_MAP_TMPL_CD-->
																<td class="stab">&nbsp;<ezf:select name="defWhMapTmplCd_H1" ezfName="defWhMapTmplCd_H1" ezfBlank="1" ezfCodeName="defWhMapTmplCd_L1" ezfDispName="defWhMapTmplNm_L1" otherAttr=" style=\"width: 130px\""/>&nbsp;<ezf:inputButton name="filter_Result" value="Filter Result" htmlClass="pBtn6" /></td>
																<td><ezf:inputButton name="MoveToUpload" value="Mass Upload" htmlClass="pBtn6" /></td>
																<td align="right">
																	<table cellpadding="0" cellspacing="0">
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
													</td>
												</tr>
												<tr>
													<td valign="top">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:570; height:60; float:left;">
												<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="570" height="60">
													<col align="center" width="30">
													<col align="center" width="140">
													<col align="center" width="120">
													<col align="center" width="60">
													<col align="center" width="80">
													<col align="center" width="80">
													<col align="center" width="60">
													<tr rowspan="2">
														<td class="pClothBs"></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'defWhMapTmplCd_A1' )">Mapping Template Name<img id="sortIMG.defWhMapTmplCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'mdseItemClsTpCd_A1' )">Item Sourcing Classification: Qualifier<img id="sortIMG.mdseItemClsTpCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'thirdPtyItemFlg_A1' )">Third Party Flag<img id="sortIMG.thirdPtyItemFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'fromPostCd_A1' )">Postal Code From<img id="sortIMG.fromPostCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'toPostCd_A1' )">Postal Code To<img id="sortIMG.toPostCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'onHndChkFlg_A1' )">On Hand Checking<img id="sortIMG.onHndChkFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:515; height:60; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="1160" height="60">
													<col align="center" width="100">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="100">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="100">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="20">
													<tr>
														<td colspan="3" class="pClothBs">Primary On Hand Check</td>
														<td colspan="3" class="pClothBs">Secondary On Hand Check</td>
														<td colspan="3" class="pClothBs">OUTBOUND:Default Warehouse:If Not On Hand</td>
														<td class="pClothBs">&nbsp;&nbsp;</td>
													</tr>
													<tr>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'otbdPrimOnHndChkWhCd_A1' )">Warehouse<img id="sortIMG.otbdPrimOnHndChkWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhNm_01' )">Sub Warehouse<img id="sortIMG.rtlSwhNm_01" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'otbdPrimOnHndLinSrcCd_A1' )">Line Source Type<img id="sortIMG.otbdPrimOnHndLinSrcCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'otbdScdOnHndChkWhCd_A1' )">Warehouse<img id="sortIMG.otbdScdOnHndChkWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhNm_02' )">Sub Warehouse<img id="sortIMG.rtlSwhNm_02" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'otbdScdOnHndLinSrcCd_A1' )">Line Source Type<img id="sortIMG.otbdScdOnHndLinSrcCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'otbdDefWhCd_A1' )">Warehouse<img id="sortIMG.otbdDefWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'rtlSwhNm_03' )">Sub Warehouse<img id="sortIMG.rtlSwhNm_03" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'otbdDefLineSrcCd_A1' )">Line Source Type<img id="sortIMG.otbdDefLineSrcCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														<td class="pClothBs">&nbsp;&nbsp;</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:416; width:570; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="570" id="A1">
													<col align="left" width="30">
													<col align="center" width="140">
													<col align="left" width="120">
													<col align="center" width="60">
													<col align="left" width="80">
													<col align="left" width="80">
													<col align="center" width="60">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<!--DEF_WH_MAP_TMPL_CD-->
														<td><ezf:select name="defWhMapTmplCd_A1" ezfName="defWhMapTmplCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="defWhMapTmplCd_L1" ezfDispName="defWhMapTmplNm_L1" otherAttr=" style=\"width: 130px\""/></td>
														<!--MDSE_ITEM_CLS_TP_CD-->
														<td><ezf:select name="mdseItemClsTpCd_A1" ezfName="mdseItemClsTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_L1" ezfDispName="mdseItemClsTpNm_L1" otherAttr=" style=\"width: 100px\""/></td>
														<td><ezf:inputCheckBox name="thirdPtyItemFlg_A1" ezfName="thirdPtyItemFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
														<!--FROM_POST_CD-->
														<td><ezf:inputText name="fromPostCd_A1" ezfName="fromPostCd_A1" value="WWWWWWW003" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
														<!--TO_POST_CD-->
														<td><ezf:inputText name="toPostCd_A1" ezfName="toPostCd_A1" value="WWWWWWW004" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
														<!--ON_HND_CHK_FLG-->
														<td><ezf:inputCheckBox name="onHndChkFlg_A1" ezfName="onHndChkFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:433; width:535; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="1140" id="A2">
													<col align="center" width="100">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="100">
													<col align="center" width="140">
													<col align="center" width="140">
													<col align="center" width="100">
													<col align="center" width="140">
													<col align="center" width="140">
													<ezf:row ezfHyo="A">
													<tr height="24">
														<!--OTBD_PRIM_ON_HND_CHK_WH_CD-->
														<td><ezf:inputText name="otbdPrimOnHndChkWhCd_A1" ezfName="otbdPrimOnHndChkWhCd_A1" value="W2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"5\" ezftoupper=\"\""/>
														<ezf:inputButton name="LineItem_OTBD_PRIM_ON_HND_CHK_WH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<!--OTBD_PRIM_ON_HND_CHK_SWH_CD-->
														<td><ezf:inputText name="rtlSwhNm_01" ezfName="rtlSwhNm_01" value="WWWWWWW013" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/>
														<ezf:inputButton name="LineItem_OTBD_PRIM_ON_HND_CHK_SWH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<!--OTBD_PRIM_ON_HND_LIN_SRC_CD-->
														<td><ezf:select name="otbdPrimOnHndLinSrcCd_A1" ezfName="otbdPrimOnHndLinSrcCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ordLineSrcCd_L1" ezfDispName="ordLineSrcNm_L1" otherAttr=" style=\"width: 130px\""/></td>
														<!--OTBD_SCD_ON_HND_CHK_WH_CD-->
														<td><ezf:inputText name="otbdScdOnHndChkWhCd_A1" ezfName="otbdScdOnHndChkWhCd_A1" value="W2" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"5\" ezftoupper=\"\""/>
														<ezf:inputButton name="LineItem_OTBD_SCD_ON_HND_CHK_WH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<!--OTBD_SCD_ON_HND_CHK_SWH_CD-->
														<td><ezf:inputText name="rtlSwhNm_02" ezfName="rtlSwhNm_02" value="WWWWWWW016" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/>
														<ezf:inputButton name="LineItem_OTBD_SCD_ON_HND_CHK_SWH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<!--OTBD_SCD_ON_HND_LINE_SRC_CD-->

														<td><ezf:select name="otbdScdOnHndLineSrcCd_A1" ezfName="otbdScdOnHndLineSrcCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ordLineSrcCd_L1" ezfDispName="ordLineSrcNm_L1" otherAttr=" style=\"width: 130px\""/></td>
														<!--OTBD_DEF_WH_CD-->
														<td><ezf:inputText name="otbdDefWhCd_A1" ezfName="otbdDefWhCd_A1" value="W5" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"5\" ezftoupper=\"\""/>
														<ezf:inputButton name="LineItem_OTBD_DEF_WH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<!--OTBD_DEF_SWH_CD-->
														<td><ezf:inputText name="rtlSwhNm_03" ezfName="rtlSwhNm_03" value="WWWWWWW019" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/>
														<ezf:inputButton name="LineItem_OTBD_DEF_SWH" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /></td>
														<!--OTBD_DEF_LINE_SRC_CD-->
														<td><ezf:select name="otbdDefLineSrcCd_A1" ezfName="otbdDefLineSrcCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ordLineSrcCd_D1" ezfDispName="ordLineSrcNm_D1" otherAttr=" style=\"width: 130px\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
													</td>
												</tr>
											</table>


										</div>
									</div>

<%}%>
<%-- Mapping Template Qualify End --%>



<%-- Mapping Template Qualify(RMA) Start --%>
<%if(MapTmpQlfyRmaFlg.equals("Y") && xxDplyTab_H1.equals("MapTmpQlfyRMA")){%>
										<script type="text/javascript">
<%if(SrcCatgFlg.equals("Y")){%>
											document.getElementById( "SrcCatg" ).className = "pTab_OFF";
<%}%>
<%if(MapTmpQlfyFlg.equals("Y")){%>
											document.getElementById( "MapTmpQlfy" ).className = "pTab_OFF";
<%}%>
<%if(MapTmpQlfyRmaFlg.equals("Y")){%>
											document.getElementById( "MapTmpQlfyRMA" ).className = "pTab_ON";
<%}%>
										</script>
									
									<div class="pTab_BODY_In">
									    <div>
											<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td valign="top">
														<table>
															<col width="200">
															<col width="220">
															<col width="80">
															<col width="600">
															<tr>
																<td class="stab"><ezf:inputButton name="InsertRow_MAP_TMPL_QLFY_RMA" value="Insert Row" htmlClass="pBtn6" />&nbsp;<ezf:inputButton name="DeleteRow_MAP_TMPL_QLFY_RMA" value="Delete Row" htmlClass="pBtn6" /></td>
																<!--DEF_WH_MAP_TMPL_CD-->
																<td class="stab">&nbsp;<ezf:select name="defWhMapTmplCd_H1" ezfName="defWhMapTmplCd_H1" ezfBlank="1" ezfCodeName="defWhMapTmplCd_L1" ezfDispName="defWhMapTmplNm_L1" otherAttr=" style=\"width: 130px\""/>&nbsp;<ezf:inputButton name="filter_Result" value="Filter Result" htmlClass="pBtn6" /></td>
																<td><ezf:inputButton name="MoveToUpload" value="Mass Upload" htmlClass="pBtn6" /></td>
																<td align="right">
																	<table cellpadding="0" cellspacing="0">
																		<tr>
																			<td>
																				<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
																				<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																				<jsp:param name="TableNm"     value="E" />
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
													</td>
												</tr>
												<tr>
													<td valign="top">
													<%-- ### MEISAI - TOP --%>
													<div id="topTBL" style="overflow-x:none; overflow-y:none; width:1009; height:60; float:left;">
														<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="990" height="60">
															<col align="center" width=" 30">
															<col align="center" width="140">
															<col align="center" width="120">
															<col align="center" width=" 60">
															<col align="center" width=" 80">
															<col align="center" width=" 80">
															<col align="center" width=" 60">
															<col align="center" width="140">
															<col align="center" width="140">
															<col align="center" width="140">
															<tr>
																<td rowspan="2" class="pClothBs"></td>
																<td rowspan="2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'defWhMapTmplCd_E1' )">Mapping Template Name<img id="sortIMG.defWhMapTmplCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td rowspan="2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'mdseItemClsTpCd_E1' )">Item Sourcing Classification: Qualifier<img id="sortIMG.mdseItemClsTpCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td rowspan="2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'thirdPtyItemFlg_E1' )">Third Party Flag<img id="sortIMG.thirdPtyItemFlg_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td rowspan="2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'fromPostCd_E1' )">Postal Code From<img id="sortIMG.fromPostCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td rowspan="2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'toPostCd_E1' )">Postal Code To<img id="sortIMG.toPostCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td rowspan="2" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'onHndChkFlg_E1' )">On Hand Checking<img id="sortIMG.onHndChkFlg_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td colspan="3" class="pClothBs">RMA:Default Warehouse</td>
															</tr>
															<tr>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'thirdPtyDspTpCd_E1' )">RMA Disposition<img id="sortIMG.thirdPtyDspTpCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'dropRtrnVndCd_E1' )">Supplier<img id="sortIMG.dropRtrnVndCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'E', 'rmaDefWhCd_E1' )">Warehouse<img id="sortIMG.rmaDefWhCd_E1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															</tr>
														</table>
													</div>
													<%-- ### MEISAI - BOTTOM --%>
													<div id="bottomTBL" style="overflow-x:hidden; overflow-y:auto; height:416; width:1009; float:left;">
														<table style="table-layout:fixed;"  border="1" cellpadding="1" cellspacing="0" width="990" id="E1">
															<col align="left"   width=" 30">
															<col align="center" width="140">
															<col align="left"   width="120">
															<col align="center" width=" 60">
															<col align="left"   width=" 80">
															<col align="left"   width=" 80">
															<col align="center" width=" 60">
															<col align="center" width="140">
															<col align="center" width="140">
															<col align="center" width="140">
															<ezf:row ezfHyo="E">
															<tr height="24">
																<td><ezf:inputCheckBox name="xxChkBox_E1" ezfName="xxChkBox_E1" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																<!--DEF_WH_MAP_TMPL_CD-->
																<td><ezf:select name="defWhMapTmplCd_E1" ezfName="defWhMapTmplCd_E1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="defWhMapTmplCd_L1" ezfDispName="defWhMapTmplNm_L1" otherAttr=" style=\"width: 130px\""/></td>
																<!--MDSE_ITEM_CLS_TP_CD-->
																<td><ezf:select name="mdseItemClsTpCd_E1" ezfName="mdseItemClsTpCd_E1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="mdseItemClsTpCd_L1" ezfDispName="mdseItemClsTpNm_L1" otherAttr=" style=\"width: 100px\""/></td>
																<td><ezf:inputCheckBox name="thirdPtyItemFlg_E1" ezfName="thirdPtyItemFlg_E1" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																<!--FROM_POST_CD-->
																<td><ezf:inputText name="fromPostCd_E1" ezfName="fromPostCd_E1" value="WWWWWWW003" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
																<!--TO_POST_CD-->
																<td><ezf:inputText name="toPostCd_E1" ezfName="toPostCd_E1" value="WWWWWWW004" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"10\" ezftoupper=\"\""/></td>
																<!--ON_HND_CHK_FLG-->
																<td><ezf:inputCheckBox name="onHndChkFlg_E1" ezfName="onHndChkFlg_E1" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
																<!--THIRD_PTY_DSP_TP_CD-->
																<td><ezf:select name="thirdPtyDspTpCd_E1" ezfName="thirdPtyDspTpCd_E1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="thirdPtyDspTpCd_R1" ezfDispName="thirdPtyDspTpNm_R1" otherAttr=" style=\"width: 130px\""/></td>
																<!--DROP_RTRN_VND_CD-->
																<td><ezf:select name="dropRtrnVndCd_E1" ezfName="dropRtrnVndCd_E1" ezfHyo="E" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dropRtrnVndCd_R1" ezfDispName="dropRtrnVndNm_R1" otherAttr=" style=\"width: 130px\""/></td>
																<!--RMA_DEF_WH_CD-->
																<td><ezf:inputText name="rmaDefWhCd_E1" ezfName="rmaDefWhCd_E1" value="W2" ezfHyo="E" ezfArrayNo="0" otherAttr=" maxlength=\"20\" size=\"10\" ezftoupper=\"\""/>
																<ezf:inputButton name="LineItem_RMA_DEF_WH" value="..." ezfHyo="E" ezfArrayNo="0" htmlClass="pBtn1" /></td>
															</tr>
															</ezf:row>
															<ezf:skip>
															</ezf:skip>
														</table>
													</div>
													</td>
												</tr>
											</table>
										</div>
									</div>
<%}%>
<%-- Mapping Template Qualify(RMA) End --%>



								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</center>

	<jsp:include page="../wfcomp/S21WfProcessStatusPageInvoker.jsp">
		<jsp:param name="processCodes" value='DMAP0010'/>
		<jsp:param name="displayMode" value='static' />
		<jsp:param name="windowWidth" value="1000px" />
		<jsp:param name="windowHeight" value="600px" />
		<jsp:param name="documentId" value='<%=databean.getWfDocumentId()%>' />
	</jsp:include>

	<jsp:include page="../wfcomp/S21WfWorkListSetPageInvoker.jsp">
		<jsp:param name="processCodes" value='<%=databean.getWfWorklistProcessCodes()%>' />
		<jsp:param name="taskCodes" value='<%=databean.getWfWorklistTaskCodes()%>' />
		<jsp:param name="displayColumn" value="Task Name~wf:taskName~center~" />
		<jsp:param name="displayColumn" value="Merchandise Code~wf:documentId~center~" />
		<jsp:param name="displayColumn" value="Requested By~wf:requestedBy~center~" />
		<jsp:param name="displayColumn" value="Task Started~wf:taskStartDate~center~MM/dd/yyyy HH:mm" />
		<jsp:param name="taskDurationColors" value="s1=720,s2=360,s3=120" />
		<jsp:param name="toDoListDisplayMode" value="static" />
		<jsp:param name="toDoListHeight" value="645px" />
		<jsp:param name="canDoListDisplayMode" value="hidden" />
		<jsp:param name="canDoListHeight" value="200px" />
		<jsp:param name="windowWidth" value="1000px" />
		<jsp:param name="windowHeight" value="680px" />
		<jsp:param name="transitionNameOnSelect" value="SelectWorkItem" />
	</jsp:include>

<jsp:include page="../wfcomp/S21WfProcessStatusSearchPageInvoker.jsp">
	<jsp:param name="processCodes" value='DMAP0010'/>
	<jsp:param name="displayMode" value='static' />
	<jsp:param name="displayStatusColumn" value='true' />
	<jsp:param name="windowWidth" value="1000px" />
	<jsp:param name="windowHeight" value="600px" />
	<jsp:param name="documentId" value='<%=databean.getWfDocumentId()%>' />
	<jsp:param name="parentDocumentId" value='<%=databean.getWfParentDocumentId()%>' />
	<jsp:param name="displayColumn" value="MDSE CD~wf:documentId~left~" />
	<jsp:param name="displayColumn" value="Create Date~wf:createDate~center~MM/dd/yyyy HH:mm:ss" />
	<jsp:param name="displayColumn" value="Status~wf:status~left~" />
</jsp:include>


<%-- **** Task specific area ends here **** --%>
