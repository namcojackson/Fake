<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20181218101627 --%>
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

			<script type="text/javascript" src="./js/business/NMAL7260_colchg.js" charset="UTF-8"></script>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NMAL7260Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Adjustment Table Entry">
			
<%@ page import="business.servlet.NMAL7260.NMAL7260BMsg" %>
<% NMAL7260BMsg bMsg = (NMAL7260BMsg) databean.getEZDBMsg(); %>
			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp" />
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Price Adjustment Table" class="pTab_ON"><a href="#">Prc Adj Tbl</a></li>
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
								<%-- ## HEADER ## --%>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td valign="top">
											<table cellpadding="0" border="0">
												<col align="left" width="130">
												<col align="left" width="190">
												<col align="left" width="130">
												<col align="left" width="100">
												<col align="left" width="130">
												<col align="left" width="150">
												<col align="left" width="90">
												<col align="left" width="70">
												<col align="left">
												<tr>
													<td class="stab">Adjustment Table ID</td>
													<td>
														<ezf:inputText name="prcRuleHdrPk_H1" ezfName="prcRuleHdrPk_H1" value="PRCLIST-ID" otherAttr=" size=\"15\" maxlength=\"28\" tabindex=\"1\" ezftoupper=\"\""/>
														<ezf:inputButton name="Search" value="Search" htmlClass="pBtn4" otherAttr=" tabindex=\"2\""/>
													</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td class="stab">Default Rule Precedence#</td>
													<td>
														<ezf:inputText name="defRulePrcdNum_H1" ezfName="defRulePrcdNum_H1" value="1" otherAttr=" size=\"3\" maxlength=\"3\" tabindex=\"3\" ezftoupper=\"\""/>
														<ezf:anchor name="xxLinkAncr_DP" ezfName="xxLinkAncr_DP" ezfEmulateBtn="1" ezfGuard="OpenWin_DefPrcd" otherAttr=" id=\"xxLinkAncr_DP\" ezfanchortext=\"\"">View Others</ezf:anchor>
													</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Table Name</td>
													<td colspan="3"><ezf:inputText name="prcRuleNm_H1" ezfName="prcRuleNm_H1" value="LFS/PPS Parts Customer model Series Discoun" otherAttr=" size=\"50\" maxlength=\"50\" tabindex=\"4\" ezftoupper=\"\""/></td>
													<td class="stab">Precedence Group</td>
													<td colspan="2">
														<ezf:inputText name="prcRulePrcdGrpNm_H1" ezfName="prcRulePrcdGrpNm_H1" value="PARTS DISCOUNT" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/>
														<ezf:inputButton name="OpenWin_PrcdGrp" value="View Prcd Group" htmlClass="pBtn10" otherAttr=" id=\"OpenWin_PrcdGrp\""/>
													</td>
												</tr>
												<tr>
													<td class="stab">Line of Business</td>
													<td>
														<ezf:select name="lineBizTpCd_H1" ezfName="lineBizTpCd_H1" ezfCodeName="lineBizTpCd_L1" ezfDispName="lineBizTpDescTxt_L1" otherAttr=" style=\"width: 60px\" tabindex=\"5\""/>
													</td>
													<td class="stab" style="width: 120px">Apply Automatically</td>
													<td><ezf:inputCheckBox name="applyAutoFlg_H1" ezfName="applyAutoFlg_H1" value="Y" otherAttr=" tabindex=\"10\""/></td>
													<td class="stab">Table Description</td>
													<td colspan="3"><ezf:inputText name="prcRuleHdrDescTxt_H1" ezfName="prcRuleHdrDescTxt_H1" value="TEST DESCRIPTION" otherAttr=" size=\"52\" maxlength=\"100\" ezftoupper=\"\" tabindex=\"15\""/></td>
												</tr>
												<tr>
													<td class="stab">Category</td>
													<td>
														<ezf:select name="prcRuleCatgCd_H1" ezfName="prcRuleCatgCd_H1" ezfCodeName="prcRuleCatgCd_L1" ezfDispName="prcRuleCatgDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_Category')\"" otherAttr=" style=\"width: 160px\" tabindex=\"6\""/>
													</td>
													<td class="stab">Override</td>
													<td><ezf:inputCheckBox name="ovrdFlg_H1" ezfName="ovrdFlg_H1" value="Y" otherAttr=" tabindex=\"11\""/></td>
													<td class="stab">Comments</td>
													<td colspan="3" rowspan="2"><ezf:textArea name="xxFldValTxt_H1" ezfName="xxFldValTxt_H1" otherAttr=" size=\"50\" maxlength=\"250\" rows=\"3\" cols=\"50\" ezftoupper=\"\" tabindex=\"16\""/></td>
												</tr>
												<tr>
													<td class="stab">Price Adjustment Level</td>
													<td>
														<ezf:select name="prcRuleAdjLvlCd_H1" ezfName="prcRuleAdjLvlCd_H1" ezfCodeName="prcRuleAdjLvlCd_L1" ezfDispName="prcRuleAdjLvlDescTxt_L1" otherAttr=" tabindex=\"7\""/>
													</td>
													<td class="stab">Active</td>
													<td><ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" otherAttr=" tabindex=\"12\""/></td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">Modify</td>
													<td>
														<ezf:select name="prcRuleModTpCd_H1" ezfName="prcRuleModTpCd_H1" ezfCodeName="prcRuleModTpCd_L1" ezfDispName="prcRuleModTpDescTxt_L1" otherAttr=" tabindex=\"8\""/>
													</td>
													<td class="stab">Effective Date From</td>
													<td>
														<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"13\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
													</td>
													<td class="stab">Created By</td>
													<td><ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" otherAttr=" size=\"28\" maxlength=\"99\""/></td>
													<td class="stab">Creation Date</td>
													<td><ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
												<tr>
													<td class="stab">Adjustment</td>
													<td>
														<ezf:select name="prcRuleAdjTpCd_H1" ezfName="prcRuleAdjTpCd_H1" ezfCodeName="prcRuleAdjTpCd_L1" ezfDispName="prcRuleAdjTpDescTxt_L1" otherAttr=" tabindex=\"9\""/>
													</td>
													<td class="stab">Effective Date To</td>
													<td>
														<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" value="mm/dd/yyyy" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"14\""/>
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
													</td>
													<td class="stab">Last Updated By</td>
													<td><ezf:inputText name="xxFullNm_H2" ezfName="xxFullNm_H2" otherAttr=" size=\"28\" maxlength=\"99\""/></td>
													<td class="stab">Last Update Date</td>
													<td><ezf:inputText name="lastUpdDt_H1" ezfName="lastUpdDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												</tr>
												<tr>
													<td class="stab">Transaction Type</td>
													<td>
														<ezf:select name="prcGrpTrxTpCd_H1" ezfName="prcGrpTrxTpCd_H1" ezfBlank="1" ezfCodeName="prcGrpTrxTpCd_L1" ezfDispName="prcGrpTrxTpDescTxt_L1" otherAttr=" tabindex=\"15\""/>
													</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

<table width="950">
	<col valign="top">
	<tr>
		<td>
			<div class="pTab_HEAD">
				<ul>
					<li class="pTab_ON"  id="AdjTblDfn" title="AdjTblDfn"><ezf:anchor name="" ezfName="xxTabProt_D1" ezfEmulateBtn="1" ezfGuard="TAB_TblDfn" >Table Definition</ezf:anchor></li>
					<li class="pTab_OFF" id="AdjTblData"  title="AdjTblData"><ezf:anchor name="" ezfName="xxTabProt_D2" ezfEmulateBtn="1" ezfGuard="TAB_TblData" >Table Data</ezf:anchor></li>
				</ul>
			</div>
<%-- ######################################## Table Definition ######################################## --%>
			<c:choose>
				<c:when test="${(pageScope._ezddatabean.xxDplyTab_H1 == 'AdjTblDfn')}">
					<script type="text/javascript">
						document.getElementById( "AdjTblDfn").className = "pTab_ON";
						document.getElementById( "AdjTblData" ).className = "pTab_OFF";
					</script>
					<div class="pTab_BODY_In"  id="AdjTblDfn_div">

								<%-- ## TABLE_DEFINITION ## --%>
								<table border="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="540" height="300"  rules="none"  style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
												<tr>
													<td valign="top">
														<table border="0" width="550">
															<col align="left" width="330">
															<col align="right" width="100">
															<tr>
																<td>
																	<ezf:inputButton name="InsertRow_TblDef" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"InsertRow_TblDef\""/>
																	<ezf:inputButton name="DeleteRow_TblDef" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"DeleteRow_TblDef\""/>
																</td>
															</tr>
														</table>
														<div id="LeftTable_C_Top" style="overflow-x:none; overflow-y:none; width:600; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" style="margin-right:20px">
																<col align="center" width="30">
																<col align="center" width="80">
																<col align="center" width="260">
																<col align="center" width="120">
																<col align="center" width="80">
																<tr height="18">
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">Column#</td>
																	<td class="pClothBs">Column Name</td>
																	<td class="pClothBs">Data type</td>
																	<td class="pClothBs">Required</td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_C" style="overflow-y:scroll; height:282; overflow-x:hidden; width:588; float:left;">
															<table border="1" cellpadding="1" cellspacing="0" id="C" style="table-layout: fixed">
																<col width="30" align="center">
																<col width="80" align="center">
																<col width="260" align="center">
																<col width="120" align="center">
																<col width="80" align="center">
																<ezf:row ezfHyo="C">
																	<tr id="id_leftC_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																		<td><ezf:inputText name="prcRuleCondNum_C1" ezfName="prcRuleCondNum_C1" value="1" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"60\" ezftoupper=\"\""/></td>
																		<td>
																			<ezf:select name="prcRuleAttrbCd_C1" ezfName="prcRuleAttrbCd_C1" ezfHyo="C" ezfArrayNo="0" ezfCodeName="prcRuleAttrbCd_LA" ezfDispName="prcRuleAttrbDescTxt_LA" otherEvent1=" onchange=\"sendServer('OnChange_Attribute')\"" otherAttr=" style=\"width: 240px\""/>
																		</td>
																		<td><ezf:inputText name="inpObjTpDescTxt_C1" ezfName="inpObjTpDescTxt_C1" value="VARCHAR" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"14\" maxlength=\"60\" ezftoupper=\"\""/></td>
																		<td><ezf:inputCheckBox name="inpReqFlg_C1" ezfName="inpReqFlg_C1" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
																	</tr>
																</ezf:row>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td valign="top">
											<ezf:inputButton name="Show" value="Show" htmlClass="pBtn10" otherAttr=" id=\"Show\""/>
										</td>
									</tr>
								</table>
					</div>
				</c:when>

<%-- ######################################## Table Data ######################################## --%>
				<c:when test="${(pageScope._ezddatabean.xxDplyTab_H1 == 'AdjTblData')}">
					<script type="text/javascript">
						document.getElementById( "AdjTblDfn").className = "pTab_OFF";
						document.getElementById( "AdjTblData" ).className = "pTab_ON";
					</script>
					<div class="pTab_BODY_In"  id="AdjTblData_div">

								<%-- ## TABLE_DATA ## --%>
								<table border="0">
									<tr>
										<td>
											<table border="0" cellpadding="1" cellspacing="0" width="540" height="120" rules="none" style="padding: 0px; margin-bottom: 0px; border: 0px solid #333333;">
												<tr>
													<td valign="top">
														<table border="0" width="1110">
															<col align="left" width="230">
															<col align="right" width="55">
															<col align="left" width="490">
															<col align="right" width="">
															<col align="left">
															<tr>
																<td>
																	<ezf:inputButton name="InsertRow_TblData" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"InsertRow_TblData\""/>
																	<ezf:inputButton name="DeleteRow_TblData" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"DeleteRow_TblData\""/>
																	<ezf:inputButton name="OpenWin_Filter" value="Filter" htmlClass="pBtn6" otherAttr=" id=\"OpenWin_Filter\""/>
																</td>
																<td class="stab" valign="middle">File Upload</td>
																<td>
																	<ezf:inputFile name="xxFileData" ezfName="xxFileData" otherAttr=" size=\"27\" maxlength=\"9999\""/>
																	<ezf:inputButton name="Upload_CSV" value="File Data" htmlClass="pBtn5" otherAttr=" id=\"Upload_CSV\""/>
																	<ezf:anchor name="xxLinkAncr_TD" ezfName="xxLinkAncr_TD" ezfEmulateBtn="1" ezfGuard="Download_Template" otherAttr=" id=\"xxLinkAncr_TD\" ezfanchortext=\"\"">Download Template</ezf:anchor>
																</td>
																<td align="right" colspan="">
																	<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="B" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
																	</jsp:include>
																</td>
															</tr>
															<tr>
																<td colspan="2">
																	<ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" />
																	<ezf:inputButton name="UnSelectAll" value="UnSelect All" htmlClass="pBtn6" />
																</td>
																<td align="right">
																	<table border="0" cellpadding="1" cellspacing="0">
																		<tr>
																			<td class="stab" valign="middle">Show Records</td>
																			<td valign="middle">
																				<ezf:select name="prcDispRecTpCd_H1" ezfName="prcDispRecTpCd_H1" ezfCodeName="prcDispRecTpCd_L1" ezfDispName="prcDispRecTpDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_PrcDispRecTp')\"" otherAttr=" style=\"width: 150px\""/>
																			</td>
																		</tr>
																	</table>
																</td>
																<td class="stab" align="right" colspan="2">Eff Date To
																	<ezf:inputText name="effThruDt_MU" ezfName="effThruDt_MU" otherAttr=" size=\"10\" maxlength=\"10\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_MU', 4);" >
																	<ezf:inputButton name="MassUpd_PrcAdjTbl" value="MASS Update" htmlClass="pBtn7" />
																</td>
															</tr>
														</table>
														<div id="LeftTable_B_Top" style="overflow-x:hidden; overflow-y:hidden; width:1084; float:left;">
															<table border="1" id="tbldata" cellpadding="1" cellspacing="0" style="table-layout: fixed;" style="margin-right:20px">
																<col width="30" align="center">
																<col width="330" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="330" align="center">
                                                                <col width="150" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="330" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="60" align="center">
																<col width="300" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<tr height="18">
																	<td class="pClothBs" id="BH0">&nbsp;</td>
																	<td class="pClothBs" id="BH4"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_04' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_04" ezfName="prcRuleAttrbDescTxt_4" /><img id="sortIMG.prcRuleCondFromTxt_04" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH5"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_05' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_5" ezfName="prcRuleAttrbDescTxt_5" /><img id="sortIMG.prcRuleCondFromTxt_05" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH6"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_06' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_6" ezfName="prcRuleAttrbDescTxt_6" /><img id="sortIMG.prcRuleCondFromTxt_06" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH7"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_07' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_7" ezfName="prcRuleAttrbDescTxt_7" /><img id="sortIMG.prcRuleCondFromTxt_07" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH8"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_08' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_8" ezfName="prcRuleAttrbDescTxt_8" /><img id="sortIMG.prcRuleCondFromTxt_08" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH9"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_09' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_9" ezfName="prcRuleAttrbDescTxt_9" /><img id="sortIMG.prcRuleCondFromTxt_09" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH10"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_10' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_10" ezfName="prcRuleAttrbDescTxt_10" /><img id="sortIMG.prcRuleCondFromTxt_10" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
                                                                    <td class="pClothBs" id="BH62"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'mnfItemCd_10' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_62" ezfName="prcRuleAttrbDescTxt_62" /><img id="sortIMG.mnfItemCd_10" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH11"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_11' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_11" ezfName="prcRuleAttrbDescTxt_11" /><img id="sortIMG.prcRuleCondFromTxt_11" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH12"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_12' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_12" ezfName="prcRuleAttrbDescTxt_12" /><img id="sortIMG.prcRuleCondFromTxt_12" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH13"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_13' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_13" ezfName="prcRuleAttrbDescTxt_13" /><img id="sortIMG.prcRuleCondFromTxt_13" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH14"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_14' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_14" ezfName="prcRuleAttrbDescTxt_14" /><img id="sortIMG.prcRuleCondFromTxt_14" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH15"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_15' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_15" ezfName="prcRuleAttrbDescTxt_15" /><img id="sortIMG.prcRuleCondFromTxt_15" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH16"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_16' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_28" ezfName="prcRuleAttrbDescTxt_28" /><img id="sortIMG.prcRuleCondFromTxt_16" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH17"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_17' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_29" ezfName="prcRuleAttrbDescTxt_29" /><img id="sortIMG.prcRuleCondFromTxt_17" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH18"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_18' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_30" ezfName="prcRuleAttrbDescTxt_30" /><img id="sortIMG.prcRuleCondFromTxt_18" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH19"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_19' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_31" ezfName="prcRuleAttrbDescTxt_31" /><img id="sortIMG.prcRuleCondFromTxt_19" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH20"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_20' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_32" ezfName="prcRuleAttrbDescTxt_32" /><img id="sortIMG.prcRuleCondFromTxt_20" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH21"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxSvcCallDt_FR' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_33" ezfName="prcRuleAttrbDescTxt_33" /><img id="sortIMG.xxSvcCallDt_FR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH22"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_22' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_34" ezfName="prcRuleAttrbDescTxt_34" /><img id="sortIMG.prcRuleCondFromTxt_22" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH24"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_24' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_35" ezfName="prcRuleAttrbDescTxt_35" /><img id="sortIMG.prcRuleCondFromTxt_24" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH25"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_25' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_36" ezfName="prcRuleAttrbDescTxt_36" /><img id="sortIMG.prcRuleCondFromTxt_25" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH26"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_26' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_37" ezfName="prcRuleAttrbDescTxt_37" /><img id="sortIMG.prcRuleCondFromTxt_26" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH27"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_27' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_38" ezfName="prcRuleAttrbDescTxt_38" /><img id="sortIMG.prcRuleCondFromTxt_27" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH28"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_28' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_39" ezfName="prcRuleAttrbDescTxt_39" /><img id="sortIMG.prcRuleCondFromTxt_28" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH29"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_29' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_40" ezfName="prcRuleAttrbDescTxt_40" /><img id="sortIMG.prcRuleCondFromTxt_29" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH30"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_30' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_41" ezfName="prcRuleAttrbDescTxt_41" /><img id="sortIMG.prcRuleCondFromTxt_30" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH31"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_31' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_42" ezfName="prcRuleAttrbDescTxt_42" /><img id="sortIMG.prcRuleCondFromTxt_31" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td> 
																	<td class="pClothBs" id="BH32"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_32' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_43" ezfName="prcRuleAttrbDescTxt_43" /><img id="sortIMG.prcRuleCondFromTxt_32" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH33"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcDt_FR' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_44" ezfName="prcRuleAttrbDescTxt_44" /><img id="sortIMG.prcDt_FR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH34"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_34' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_21" ezfName="prcRuleAttrbDescTxt_21" /><img id="sortIMG.prcRuleCondFromTxt_34" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH35"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_35' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_22" ezfName="prcRuleAttrbDescTxt_22" /><img id="sortIMG.prcRuleCondFromTxt_35" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH36"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_36' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_23" ezfName="prcRuleAttrbDescTxt_23" /><img id="sortIMG.prcRuleCondFromTxt_36" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH37"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxRqstDt_FR' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_45" ezfName="prcRuleAttrbDescTxt_45" /><img id="sortIMG.xxRqstDt_FR" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH38"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_38' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_46" ezfName="prcRuleAttrbDescTxt_46" /><img id="sortIMG.prcRuleCondFromTxt_38" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH39"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_39' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_47" ezfName="prcRuleAttrbDescTxt_47" /><img id="sortIMG.prcRuleCondFromTxt_39" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH40"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_40' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_48" ezfName="prcRuleAttrbDescTxt_48" /><img id="sortIMG.prcRuleCondFromTxt_40" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH41"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_41' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_49" ezfName="prcRuleAttrbDescTxt_49" /><img id="sortIMG.prcRuleCondFromTxt_41" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH42"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_42' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_50" ezfName="prcRuleAttrbDescTxt_50" /><img id="sortIMG.prcRuleCondFromTxt_42" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH44"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_44' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_51" ezfName="prcRuleAttrbDescTxt_51" /><img id="sortIMG.prcRuleCondFromTxt_44" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH45"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_45' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_52" ezfName="prcRuleAttrbDescTxt_52" /><img id="sortIMG.prcRuleCondFromTxt_45" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH46"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_46' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_53" ezfName="prcRuleAttrbDescTxt_53" /><img id="sortIMG.prcRuleCondFromTxt_46" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH48"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_48' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_54" ezfName="prcRuleAttrbDescTxt_54" /><img id="sortIMG.prcRuleCondFromTxt_48" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH49"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_49' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_55" ezfName="prcRuleAttrbDescTxt_55" /><img id="sortIMG.prcRuleCondFromTxt_49" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH53"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_53' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_24" ezfName="prcRuleAttrbDescTxt_24" /><img id="sortIMG.prcRuleCondFromTxt_53" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH54"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_54' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_25" ezfName="prcRuleAttrbDescTxt_25" /><img id="sortIMG.prcRuleCondFromTxt_54" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH55"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_55' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_26" ezfName="prcRuleAttrbDescTxt_26" /><img id="sortIMG.prcRuleCondFromTxt_55" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH56"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_56' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_27" ezfName="prcRuleAttrbDescTxt_27" /><img id="sortIMG.prcRuleCondFromTxt_56" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH57"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_57' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_56" ezfName="prcRuleAttrbDescTxt_56" /><img id="sortIMG.prcRuleCondFromTxt_57" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH58"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_58' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_57" ezfName="prcRuleAttrbDescTxt_57" /><img id="sortIMG.prcRuleCondFromTxt_58" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH59"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_59' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_59" ezfName="prcRuleAttrbDescTxt_59" /><img id="sortIMG.prcRuleCondFromTxt_59" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH60"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_60' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_60" ezfName="prcRuleAttrbDescTxt_60" /><img id="sortIMG.prcRuleCondFromTxt_60" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH61"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleCondFromTxt_61' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_61" ezfName="prcRuleAttrbDescTxt_61" /><img id="sortIMG.prcRuleCondFromTxt_61" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH90">Qty Brk</td>
																	<td class="pClothBs" id="BH50"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcFmlaPk_B1' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_16" ezfName="prcRuleAttrbDescTxt_16" /><img id="sortIMG.prcFmlaPk_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH51"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleDtlRate_B1' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_17" ezfName="prcRuleAttrbDescTxt_17" /><img id="sortIMG.prcRuleDtlRate_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH52"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'prcRuleDtlTxt_B1' )" tabindex="-1"><ezf:label name="prcRuleAttrbDescTxt_18" ezfName="prcRuleAttrbDescTxt_18" /><img id="sortIMG.prcRuleDtlTxt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH97"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'effFromDt_B1' )" tabindex="-1">Effective Date From<img id="sortIMG.effFromDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH98"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'effThruDt_B1' )" tabindex="-1">Effective Date To<img id="sortIMG.effThruDt_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																	<td class="pClothBs" id="BH99" style="display: none"></td>
																</tr>
															</table>
														</div>
														<div id="LeftTable_B" style="overflow-y:scroll; overflow-x:scroll; width:1100; height:270; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'LeftTable_B_Top' ));">
															<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
																<col width="30" align="center">
																<col width="330" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="330" align="center">
                                                                <col width="150" align="left">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="330" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="300" align="center">
																<col width="60" align="center">
																<col width="300" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<col width="120" align="center">
																<ezf:row ezfHyo="B">
																	<tr id="id_leftB_row{EZF_ROW_NUMBER}">
																		<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_04" ezfName="prcRuleCondFromTxt_04" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_CsmpNm" otherAttr=" size=\"15\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_CsmpNm\""/>
																			<ezf:inputText name="csmpNumCmntTxt_04" ezfName="csmpNumCmntTxt_04" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_CsmpNum" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CsmpNum#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_05" ezfName="prcRuleCondFromTxt_05" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_PrcGrpMatNm" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_PrcGrpMatNm\""/>
																			<ezf:inputText name="prcGrpNm_05" ezfName="prcGrpNm_05" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_PrcGrpMat" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpMat#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_06" ezfName="prcRuleCondFromTxt_06" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ProdCtrlNm" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ProdCtrlNm\""/>
																			<ezf:inputText name="prodCtrlNm_06" ezfName="prodCtrlNm_06" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ProdCtrl" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_07" ezfName="prcRuleCondFromTxt_07" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ProdCtrlNm2" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ProdCtrlNm2\""/>
																			<ezf:inputText name="prodCtrlNm_07" ezfName="prodCtrlNm_07" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ProdCtrl2" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl2#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_08" ezfName="prcRuleCondFromTxt_08" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_CoaMdseNm" otherAttr=" size=\"5\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_CoaMdseNm\""/>
																			<ezf:inputText name="coaMdseTpDescTxt_08" ezfName="coaMdseTpDescTxt_08" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_CoaMdseTp" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CoaMdseTp#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_09" ezfName="prcRuleCondFromTxt_09" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_CoaProdNm" otherAttr=" size=\"5\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_CoaProdNm\""/>
																			<ezf:inputText name="coaProdDescTxt_09" ezfName="coaProdDescTxt_09" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_CoaProd" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CoaProd#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_10" ezfName="prcRuleCondFromTxt_10" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ItemNm" otherAttr=" size=\"16\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ItemNm\""/>
																			<ezf:inputText name="mdseDescShortTxt_10" ezfName="mdseDescShortTxt_10" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ItemSearch" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ItemSearch#{EZF_ROW_NUMBER}\""/>
																		</td>
                                                                        <td>
                                                                            <ezf:label name="mnfItemCd_10" ezfName="mnfItemCd_10" ezfHyo="B" ezfArrayNo="0" />
                                                                        </td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_11" ezfName="prcRuleCondFromTxt_11" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_DSOrdCatgNm" otherAttr=" size=\"5\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_DSOrdCatgNm\""/>
																			<ezf:inputText name="dsOrdCatgDescTxt_11" ezfName="dsOrdCatgDescTxt_11" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_DSOrdCatg" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DSOrdCatg#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_12" ezfName="prcRuleCondFromTxt_12" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_DSOrdTpNm" otherAttr=" size=\"5\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_DSOrdTpNm\""/>
																			<ezf:inputText name="dsOrdTpDescTxt_12" ezfName="dsOrdTpDescTxt_12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_DSOrdTp" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DSOrdTp#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_13" ezfName="prcRuleCondFromTxt_13" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_LineBizTpNm" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_LineBizTpNm\""/>
																			<ezf:inputText name="lineBizTpDescTxt_13" ezfName="lineBizTpDescTxt_13" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_LineBizTp" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_LineBizTp#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_14" ezfName="prcRuleCondFromTxt_14" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ProGrpTrxNm" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ProGrpTrxNm\""/>
																			<ezf:inputText name="prcGrpNm_14" ezfName="prcGrpNm_14" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_PrcGrpTrx" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpTrx#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_15" ezfName="prcRuleCondFromTxt_15" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_15" ezfName="prcRuleCondThruTxt_15" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_16" ezfName="prcRuleCondFromTxt_16" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_BillTo" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_BillTo\""/>
																			<ezf:inputText name="billToAcctNm_16" ezfName="billToAcctNm_16" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_BillTo" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BillTo#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_17" ezfName="prcRuleCondFromTxt_17" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_BillToAcctChnl" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_BillToAcctChnl\""/>
																			<ezf:inputText name="coaChDescTxt_17" ezfName="coaChDescTxt_17" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_BillToAcctChnl" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BillToAcctChnl#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_18" ezfName="prcRuleCondFromTxt_18" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_BillToAcctClss" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_BillToAcctClss\""/>
																			<ezf:inputText name="dsAcctClsDescTxt_18" ezfName="dsAcctClsDescTxt_18" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_BillToAcctClss" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BillToAcctClss#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_19" ezfName="prcRuleCondFromTxt_19" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_Branch" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_Branch\""/>
																			<ezf:inputText name="coaBrDescTxt_19" ezfName="coaBrDescTxt_19" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_Branch" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Branch#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_20" ezfName="prcRuleCondFromTxt_20" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_CallType" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_CallType\""/>
																			<ezf:inputText name="svcCallTpDescTxt_20" ezfName="svcCallTpDescTxt_20" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_CallType" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CallType#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="xxSvcCallDt_FR" ezfName="xxSvcCallDt_FR" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxSvcCallDt_FR', 4, '{EZF_ROW_NUMBER}');" >
																			-
																			<ezf:inputText name="xxSvcCallDt_TH" ezfName="xxSvcCallDt_TH" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxSvcCallDt_TH', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_22" ezfName="prcRuleCondFromTxt_22" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_22" ezfName="prcRuleCondThruTxt_22" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_24" ezfName="prcRuleCondFromTxt_24" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_24" ezfName="prcRuleCondThruTxt_24" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_25" ezfName="prcRuleCondFromTxt_25" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_LineCatg" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_LineCatg\""/>
																			<ezf:inputText name="dsOrdLineCatgDescTxt_25" ezfName="dsOrdLineCatgDescTxt_25" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_LineCatg" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_LineCatg#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_26" ezfName="prcRuleCondFromTxt_26" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_26" ezfName="prcRuleCondThruTxt_26" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_27" ezfName="prcRuleCondFromTxt_27" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_MarketMdlNm" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_MarketMdlNm\""/>
																			<ezf:inputText name="mktMdlDescTxt_27" ezfName="mktMdlDescTxt_27" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_MarketMdlNm" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_MarketMdlNm#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_28" ezfName="prcRuleCondFromTxt_28" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ModelSeg" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ModelSeg\""/>
																			<ezf:inputText name="mktMdlSegDescTxt_28" ezfName="mktMdlSegDescTxt_28" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ModelSeg" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ModelSeg#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_29" ezfName="prcRuleCondFromTxt_29" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_OrderSrc" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_OrderSrc\""/>
																			<ezf:inputText name="cpoSrcTpDescTxt_29" ezfName="cpoSrcTpDescTxt_29" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_OrderSrc" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_OrderSrc#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_30" ezfName="prcRuleCondFromTxt_30" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_30" ezfName="prcRuleCondThruTxt_30" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_31" ezfName="prcRuleCondFromTxt_31" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_PaymentTp" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_PaymentTp\""/>
																			<ezf:inputText name="dsPmtMethDescTxt_31" ezfName="dsPmtMethDescTxt_31" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_PaymentTp" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PaymentTp#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_32" ezfName="prcRuleCondFromTxt_32" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_PrcList" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_PrcList\""/>
																			<ezf:inputText name="prcCatgNm_32" ezfName="prcCatgNm_32" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_PrcList" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcList#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcDt_FR" ezfName="prcDt_FR" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcDt_FR', 4, '{EZF_ROW_NUMBER}');" >
																			-
																			<ezf:inputText name="prcDt_TH" ezfName="prcDt_TH" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('prcDt_TH', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_34" ezfName="prcRuleCondFromTxt_34" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ProdCtrlNm3" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ProdCtrlNm3\""/>
																			<ezf:inputText name="prodCtrlNm_34" ezfName="prodCtrlNm_34" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ProdCtrl3" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl3#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_35" ezfName="prcRuleCondFromTxt_35" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ProdCtrlNm4" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ProdCtrlNm4\""/>
																			<ezf:inputText name="prodCtrlNm_35" ezfName="prodCtrlNm_35" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ProdCtrl4" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl4#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_36" ezfName="prcRuleCondFromTxt_36" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ProdCtrlNm5" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ProdCtrlNm5\""/>
																			<ezf:inputText name="prodCtrlNm_36" ezfName="prodCtrlNm_36" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ProdCtrl5" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ProdCtrl5#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="xxRqstDt_FR" ezfName="xxRqstDt_FR" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxRqstDt_FR', 4, '{EZF_ROW_NUMBER}');" >
																			-
																			<ezf:inputText name="xxRqstDt_TH" ezfName="xxRqstDt_TH" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxRqstDt_TH', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_38" ezfName="prcRuleCondFromTxt_38" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_RtnRsnCd" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_RtnRsnCd\""/>
																			<ezf:inputText name="rtrnRsnDescTxt_38" ezfName="rtrnRsnDescTxt_38" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_RtnRsnCd" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_RtnRsnCd#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_39" ezfName="prcRuleCondFromTxt_39" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ServiceLv" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ServiceLv\""/>
																			<ezf:inputText name="shpgSvcLvlDescTxt_39" ezfName="shpgSvcLvlDescTxt_39" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ServiceLv" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ServiceLv#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_40" ezfName="prcRuleCondFromTxt_40" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ServiceMdl" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ServiceMdl\""/>
																			<ezf:inputText name="mdlDescTxt_40" ezfName="mdlDescTxt_40" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ServiceMdl" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ServiceMdl#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_41" ezfName="prcRuleCondFromTxt_41" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ServiceZone" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ServiceZone\""/>
																			<ezf:inputText name="prcSvcZoneDescTxt_41" ezfName="prcSvcZoneDescTxt_41" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ServiceZone" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ServiceZone#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_42" ezfName="prcRuleCondFromTxt_42" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_ShipToAcct" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_ShipToAcct\""/>
																			<ezf:inputText name="dsAcctClsDescTxt_42" ezfName="dsAcctClsDescTxt_42" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_ShipToAcct" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_ShipToAcct#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_44" ezfName="prcRuleCondFromTxt_44" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_SpecialHandTp" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_SpecialHandTp\""/>
																			<ezf:inputText name="spclHdlgTpDescTxt_44" ezfName="spclHdlgTpDescTxt_44" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_SpecialHandTp" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SpecialHandTp#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_45" ezfName="prcRuleCondFromTxt_45" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_45" ezfName="prcRuleCondThruTxt_45" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_46" ezfName="prcRuleCondFromTxt_46" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_BizUnit" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_BizUnit\""/>
																			<ezf:inputText name="coaExtnDescTxt_46" ezfName="coaExtnDescTxt_46" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_BizUnit" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_BizUnit#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_48" ezfName="prcRuleCondFromTxt_48" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_FreightTerm" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_FreightTerm\""/>
																			<ezf:inputText name="frtCondDescTxt_48" ezfName="frtCondDescTxt_48" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_FreightTerm" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_FreightTerm#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_49" ezfName="prcRuleCondFromTxt_49" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_FreightZone" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_FreightZone\""/>
																			<ezf:inputText name="fill40Txt_49" ezfName="fill40Txt_49" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_FreightZone" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_FreightZone#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_53" ezfName="prcRuleCondFromTxt_53" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_PrcGrpCustNmSold" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_PrcGrpCustNmSold\""/>
																			<ezf:inputText name="prcGrpNm_53" ezfName="prcGrpNm_53" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_PrcGrpCustSold" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpCustSold#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_54" ezfName="prcRuleCondFromTxt_54" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_AcctNmSold" otherAttr=" size=\"9\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_AcctNmSold\""/>
																			<ezf:inputText name="dsAcctNm_54" ezfName="dsAcctNm_54" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_AcctNumCustSold" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_AcctNumCustSold#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_55" ezfName="prcRuleCondFromTxt_55" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_CoaChNmSold" otherAttr=" size=\"5\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_CoaChNmSold\""/>
																			<ezf:inputText name="coaChDescTxt_55" ezfName="coaChDescTxt_55" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_CoaChSold" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_CoaChSold#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_56" ezfName="prcRuleCondFromTxt_56" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_DsAcctClsNameSold" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_DsAcctClsNameSold\""/>
																			<ezf:inputText name="dsAcctClsDescTxt_56" ezfName="dsAcctClsDescTxt_56" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_DsAcctClsSold" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_DsAcctClsSold#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_57" ezfName="prcRuleCondFromTxt_57" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_PrcGrpMatQtyBrkNm" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_PrcGrpMatQtyBrkNm\""/>
																			<ezf:inputText name="prcGrpNm_57" ezfName="prcGrpNm_57" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_PrcGrpMatQtyBrk" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcGrpMatQtyBrk#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_58" ezfName="prcRuleCondFromTxt_58" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																			-
																			<ezf:inputText name="prcRuleCondThruTxt_58" ezfName="prcRuleCondThruTxt_58" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"50\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_59" ezfName="prcRuleCondFromTxt_59" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_SlsMatGrpDescTxt1" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_SlsMatGrpDescTxt1\""/>
																			<ezf:inputText name="slsMatGrpDescTxt_59" ezfName="slsMatGrpDescTxt_59" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_SlsMatGrpDescTxt1" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SlsMatGrpDescTxt1#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_60" ezfName="prcRuleCondFromTxt_60" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_SlsMatGrpDescTxt2" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_SlsMatGrpDescTxt2\""/>
																			<ezf:inputText name="slsMatGrpDescTxt_60" ezfName="slsMatGrpDescTxt_60" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_SlsMatGrpDescTxt2" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SlsMatGrpDescTxt2#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleCondFromTxt_61" ezfName="prcRuleCondFromTxt_61" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_SlsMatGrpDescTxt3" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_SlsMatGrpDescTxt3\""/>
																			<ezf:inputText name="slsMatGrpDescTxt_61" ezfName="slsMatGrpDescTxt_61" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_SlsMatGrpDescTxt3" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_SlsMatGrpDescTxt3#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:anchor name="" ezfName="" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_QtyBreak" otherAttr=" id=\"xxLinkAncr_B1#{EZF_ROW_NUMBER}\" ezfanchortext=\"\"">
																				<ezf:label name="xxLinkAncr_B1" ezfName="xxLinkAncr_B1" ezfHyo="B" ezfArrayNo="0" />
																			</ezf:anchor>
																		</td>
																		<td>
																			<ezf:inputText name="prcFmlaPk_B1" ezfName="prcFmlaPk_B1" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_Setting_PrcFmlaNm" otherAttr=" size=\"10\" maxlength=\"50\" ezffocusout=\"OnBlur_Setting_PrcFmlaNm\""/>
																			<ezf:inputText name="prcFmlaNm_B1" ezfName="prcFmlaNm_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"18\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="OpenWin_Formula" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_Formula#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleDtlRate_B1" ezfName="prcRuleDtlRate_B1" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"7\" ezftoupper=\"\" style=\"text-align:right\""/>
																		</td>
																		<td>
																			<ezf:inputText name="prcRuleDtlTxt_B1" ezfName="prcRuleDtlTxt_B1" value="0" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"13\" ezftoupper=\"\" style=\"text-align:right\""/>
																		</td>
																		<td>
																			<ezf:inputText name="effFromDt_B1" ezfName="effFromDt_B1" value="yyyy/Mm/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B1', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td>
																			<ezf:inputText name="effThruDt_B1" ezfName="effThruDt_B1" value="yyyy/MM/dd" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
																			<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_B1', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td class="pAuditInfo">
																			<ezf:inputHidden name="xxRecHistCratTs_B1" ezfName="xxRecHistCratTs_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistCratByNm_B1" ezfName="xxRecHistCratByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdTs_B1" ezfName="xxRecHistUpdTs_B1" ezfHyo="B" ezfArrayNo="0" />
																			<ezf:inputHidden name="xxRecHistUpdByNm_B1" ezfName="xxRecHistUpdByNm_B1" ezfHyo="B" ezfArrayNo="0" />
																 			<ezf:inputHidden name="xxRecHistTblNm_B1" ezfName="xxRecHistTblNm_B1" ezfHyo="B" ezfArrayNo="0" />
																  		</td>
																	</tr>
																</ezf:row>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
					</div>
				</c:when>
			</c:choose>
		</td>
	</tr>
</table>
							</div>
						</td>
					</tr>
				</table>
			</center>
<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" value="BH0:BH19:BH20:BH56:BH90:BH50:BH51:BH52:BH97:BH98:BH99" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>

<script type="text/javascript" defer>

//Column Order Change Javascript
S21ColOrderEx = {

 ordExHeadrId : "",
 ordExDataId : "",
 ordExDefId : "",
 ordExSeparater : ":",
 columnObj : {
     columnsOrg : [],
     columnsDef : []
 },

 initialize : function(headerId, dataId, orderDefId){

     this.ordExHeadrId = headerId;
     this.ordExDataId = dataId;
     this.ordExDefId = orderDefId;

//   // Table
//   var table = document.getElementById(this.ordExHeadrId);

//   if (table == null) {
//       return;
//   }

//   var trElm = table.rows[0];
//   var len = trElm.cells.length;
//   var tdElm;
//   var value;
//   var id;
//   for(var i = 0; i < len; i++) {
//       tdElm = trElm.cells[i];
//       id = tdElm.getAttribute("id");
//       this.columnObj.columnsOrg[i] = id;
//   }

     // Order Def
     var orderDef = document.getElementById(this.ordExDefId).value;

     if (!orderDef) {
         S21ColOrderEx.hiddenTable(headerId);
//       return;
     }

     this.columnObj.columnsDef =  orderDef.split(this.ordExSeparater);

     // Column Order Change Table Renew
     S21ColOrderEx.renewTableInner(this.ordExHeadrId, this.ordExDataId, this.columnObj.columnsDef);

 },

 // Table And Parent Visibility : Hidden
 hiddenTable : function(headerId) {

     tableElm = document.getElementById(headerId);
     if (!tableElm) {
         return;
     }
     divElm = tableElm.parentNode;
     if (!divElm) {
         return;
     }

     tableElm.visibility = 'hidden';
     divElm.visibility = 'hidden';
 },

 // Table Renew
 renewTableInner : function(headerId, dataId, ids) {

     // Order
     var idOrder = [];

     var headTdElms = [];
     var tableElm;

     // Head
     tableElm = document.getElementById(headerId);
     headTdElms = tableElm.getElementsByTagName('td');

     var cellLen = headTdElms.length;
     for (var i = 0; i < cellLen; i++) {
         var id = headTdElms[i].id;
         idOrder[id] = i;
     }

     // New Order index
     var newOrder = [];
     var rowLen = tableElm.rows.length;
     var len = ids.length;
     for (var i = 0; i < len; i++) {
         newOrder.push(idOrder[ids[i]]);
     }

     // Move Header
     S21ColOrderEx.newOrderTableCols(newOrder, tableElm);
     S21ColOrderEx.newOrderTableCells(newOrder, tableElm);

     // Move Data
     var tableDataElm = document.getElementById(dataId);
     S21ColOrderEx.newOrderTableCols(newOrder, tableDataElm);
     S21ColOrderEx.newOrderTableCells(newOrder, tableDataElm);
 },

 // Table Renew
 newOrderTableCols : function(newOrder, tableElm) {

     var colg = tableElm.getElementsByTagName('colgroup').item(0);
     var cols = S21ColOrderEx.getTableChildColNodes(tableElm, "colgroup");

     var newColg = document.createElement("colgroup");

     var parent = colg.parentNode;

     var elms = [];
     var len = newOrder.length;
     for (var i = 0; i < len; i++) {
         var elm = cols[newOrder[i]];
         //newColg.appendChild(elm);
         if (elm) {
             elms.push(elm);
         }
     }

     var len = elms.length;
     for (var i = 0; i < len; i++) {
         newColg.appendChild(elms[i]);
     }

     parent.removeChild(colg);
     parent.appendChild(newColg);

 },

 newOrderTableCells : function(newOrder, tableElm) {

     var rowNum = tableElm.rows.length;

     var cellLen = newOrder.length;

     var tbElm = tableElm.tBodies[0];
     var newTbElm = document.createElement("tbody");

     for(var i = 0; i < rowNum ; i++) {
         // Record
         var trElm = tableElm.rows[i];

         var cloneTrElm = trElm.cloneNode(false);

         var elms = [];
         var len = newOrder.length;
         for (var j = 0; j < len; j++) {
             var tdElm = trElm.cells[newOrder[j]];
             if (tdElm) {
                 elms.push(tdElm);
             }
         }

         var len = elms.length;
         for (var j = 0; j < len; j++) {
             cloneTrElm.appendChild(elms[j]);
         }

         var parent = trElm.parentNode;

         //parent.removeChild(trElm);
         newTbElm.appendChild(cloneTrElm);
     }

     tableElm.removeChild(tbElm);

     newTbElm.style.display = 'none';
     tableElm.appendChild(newTbElm);
     newTbElm.style.display = '';
 },

 getTableChildColNodes : function(table, tagname){
     var cols = [];

     var colgroupElm = table.getElementsByTagName(tagname);
     if(!colgroupElm){
         return null;
     }

     return colgroupElm.item(0).getElementsByTagName('col');
 }

}
</script>

<%// if ("AdjTblData".equals(bMsg.xxDplyTab_H1.getValue())) { %>
<script type="text/javascript" defer>
	S21ColOrderEx.initialize("tbldata", "B", "xxComnColOrdTxt");
</script>
<%// } %>


<%-- **** Task specific area ends here **** --%>
