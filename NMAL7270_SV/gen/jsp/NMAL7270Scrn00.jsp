<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180913102243 --%>
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
			<input type="hidden" name="pageID" value="NMAL7270Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Rule Setup">

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
										<li title="Pricing Rule" class="pTab_ON"><a href="#">Prcing Rule</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td valign="top">
								<table cellpadding="0" border="0">
									<col align="left" width="110">
									<col align="left" width="350">
									<col align="left" width="110">
									<col align="left" width="180">
									<col align="left" width="140">
									<col align="left" width="130">
									<tr>
										<td class="stab">Rule ID</td>
										<td>
											<ezf:inputText name="prcRuleHdrPk_H1" ezfName="prcRuleHdrPk_H1" value="PRCLIST-ID" otherAttr=" size=\"15\" maxlength=\"28\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Rule Name</td>
										<td><ezf:inputText name="prcRuleNm_H1" ezfName="prcRuleNm_H1" value="LFS LIFTGATE FEE" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td class="stab">Active</td>
										<td><ezf:inputCheckBox name="actvFlg_H1" ezfName="actvFlg_H1" value="Y" /></td>
										<td class="stab">Default Rule Precedence#</td>
										<td>
											<ezf:inputText name="defRulePrcdNum_H1" ezfName="defRulePrcdNum_H1" value="1" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
											<ezf:anchor name="xxLinkAncr_DP" ezfName="xxLinkAncr_DP" ezfEmulateBtn="1" ezfGuard="OpenWin_DefPrcd" otherAttr=" id=\"xxLinkAncr_DP\" ezfanchortext=\"\"">View Others</ezf:anchor>
										</td>
									</tr>
									<tr>
										<td class="stab">Description</td>
										<td><ezf:inputText name="prcRuleDescTxt_H1" ezfName="prcRuleDescTxt_H1" value="PRICE-RULE-DESCRIPTION" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/></td>
										<td class="stab">Effective Date From</td>
										<td>
											<ezf:inputText name="effFromDt_H1" ezfName="effFromDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_H1', 4);" >
										</td>
										<td>Precedence Group</td>
										<td><ezf:inputText name="prcRulePrcdGrpNm_H1" ezfName="prcRulePrcdGrpNm_H1" value="PARTS DISCOUNT" otherAttr=" size=\"20\" maxlength=\"100\" ezftoupper=\"\""/></td>
									</tr>
									<tr>
										<td class="stab">Line of Business</td>
										<td>
											<ezf:select name="lineBizTpCd_H1" ezfName="lineBizTpCd_H1" ezfCodeName="lineBizTpCd_L1" ezfDispName="lineBizTpDescTxt_L1" otherAttr=" style=\"width: 160px\""/>
										</td>
										<td class="stab">Effective Date To</td>
										<td>
											<ezf:inputText name="effThruDt_H1" ezfName="effThruDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_H1', 4);" >
										</td>
										<td>&nbsp;</td>
										<td><ezf:inputButton name="OpenWin_PrcdGrp" value="View Prcd Group" htmlClass="pBtn10" otherAttr=" id=\"OpenWin_PrcdGrp\""/></td>
									</tr>
									<tr>
										<td class="stab">Rule Category</td>
										<td>
											<ezf:select name="prcRuleCatgCd_H1" ezfName="prcRuleCatgCd_H1" ezfCodeName="prcRuleCatgCd_L1" ezfDispName="prcRuleCatgDescTxt_L1" otherEvent1=" onchange=\"sendServer('OnChange_Category')\"" otherAttr=" style=\"width: 160px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">Apply Automatically</td>
										<td class="stab">
											<ezf:inputCheckBox name="applyAutoFlg_H1" ezfName="applyAutoFlg_H1" value="Y" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											Override&nbsp;&nbsp;&nbsp;&nbsp;
											<ezf:inputCheckBox name="ovrdFlg_H1" ezfName="ovrdFlg_H1" value="Y" />
										</td>
										<td class="stab">Created By</td>
										<td><ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" otherAttr=" size=\"28\" maxlength=\"99\""/></td>
										<td class="stab">Creation Date</td>
										<td><ezf:inputText name="cratDt_H1" ezfName="cratDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									</tr>
									<tr>
										<td class="stab">Transaction Type</td>
										<td>
											<ezf:select name="prcGrpTrxTpCd_H1" ezfName="prcGrpTrxTpCd_H1" ezfBlank="1" ezfCodeName="prcGrpTrxTpCd_L1" ezfDispName="prcGrpTrxTpDescTxt_L1" otherAttr=" style=\"width: 160px\" tabindex=\"15\""/>
										</td>
										<td class="stab">Last Updated By</td>
										<td><ezf:inputText name="xxFullNm_H2" ezfName="xxFullNm_H2" otherAttr=" size=\"28\" maxlength=\"99\""/></td>
										<td class="stab">Last Update Date</td>
										<td><ezf:inputText name="lastUpdDt_H1" ezfName="lastUpdDt_H1" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									</tr>
								</table>
							</td>
						</tr>
<%--------------------------------%>
<%-- Transaction Conditions		--%>
<%--------------------------------%>
					<table border="0" cellpadding="1" cellspacing="0" width="1100" height="120"  rules="none"  style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
						<tr>
							<td align="top">
								<table border="0" width="1100">
									<col align="left" width="1100">
									<tr>
										<td>
											<table border="0" width="1000">
												<col align="left"  width="140">
												<col align="left"  width="430">
												<col align="right"  width="530">
												<tr>
													<td class="stab"><b>Transaction Conditions</b></td>
													<td>
														<ezf:inputButton name="Add_TrxCond" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"Add_TrxCond\""/>
														<ezf:inputButton name="Del_TrxCond" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"Del_TrxCond\""/>
														<ezf:inputButton name="Download_TrxCond" value="Download" htmlClass="pBtn6" otherAttr=" id=\"Download_TrxCond\""/>
													</td>
													<td>
														<ezf:inputButton name="OpenWin_CondGrpRules" value="Condition Group Rules" htmlClass="pBtn12" otherAttr=" id=\"OpenWin_CondGrpRules\""/>
													</td>
												</tr>
											</table>
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:1070; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed; style="margin-right:10px">
													<col align="center" width="25">
													<col align="center" width="110">
													<col align="center" width="145">
													<col align="center" width="130">
													<col align="center" width="190">
													<col align="center" width="40" >
													<col align="center" width="130">
													<col align="center" width="70">
													<col align="center" width="120">
													<col align="center" width="110">
													<tr height="18">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">Category</td>
														<td class="pClothBs">Attribute</td>
														<td class="pClothBs" colspan="3">Value From</td>
														<td class="pClothBs">Value To</td>
														<td class="pClothBs">Condition#</td>
														<td class="pClothBs">Effective Date From</td>
														<td class="pClothBs">Effective Date To</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:122; overflow-x:hidden; width:1087; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
													<col width="25" align="center" >
													<col width="110">
													<col width="145">
													<col width="130">
													<col width="190">
													<col width="40" >
													<col width="130">
													<col width="70"  align="center">
													<col width="120" align="center">
													<col width="110" align="center">
													<ezf:row ezfHyo="A">
														<tr id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td>
																<ezf:select name="prcRuleTrxCatgCd_A1" ezfName="prcRuleTrxCatgCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleTrxCatgCd_L1" ezfDispName="prcRuleTrxCatgDescTxt_L1" otherAttr=" style=\"width: 100px\""/>
															</td>
															<td>
																<ezf:select name="prcRuleAttrbCd_A1" ezfName="prcRuleAttrbCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="prcRuleAttrbCd_LA" ezfDispName="prcRuleAttrbDescTxt_LA" otherEvent1=" onchange=\"sendServer('OnChange_Attrb_TrxCond', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 130px\""/>
															</td>
															<td>
																<ezf:inputText name="prcRuleCondFromTxt_A1" ezfName="prcRuleCondFromTxt_A1" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_Setting_Name" otherAttr=" size=\"15\" maxlength=\"50\" id=\"prcRuleCondFromTxt#{EZF_ROW_NUMBER}\" ezffocusout=\"OnBlur_Setting_Name\""/>
																<ezf:inputText name="xxFromDt_A1" ezfName="xxFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"condFromDate#{EZF_ROW_NUMBER}\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxFromDt_A1', 4, '{EZF_ROW_NUMBER}');" id="condFromCalendar#{EZF_ROW_NUMBER}">
															</td>
															<td>
																<ezf:inputText name="xxRecNmTxt_A1" ezfName="xxRecNmTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"360\" ezftoupper=\"\""/>
															</td>
															<td>
																<ezf:inputButton name="OpenWin_PrcRuleCondVal_Cmn" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcRuleCondVal_Cmn#{EZF_ROW_NUMBER}\""/>
																<ezf:inputButton name="OpenWin_PrcRuleCondVal_Gen" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcRuleCondVal_Gen#{EZF_ROW_NUMBER}\""/>
																<ezf:inputButton name="OpenWin_PrcRuleCondVal_Item" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcRuleCondVal_Item#{EZF_ROW_NUMBER}\""/>
																<ezf:inputButton name="OpenWin_PrcRuleCondVal_Acct" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcRuleCondVal_Acct#{EZF_ROW_NUMBER}\""/>
																<ezf:inputButton name="OpenWin_PrcRuleCondVal_Mdl" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcRuleCondVal_Mdl#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
																<ezf:inputText name="prcRuleCondThruTxt_A1" ezfName="prcRuleCondThruTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\" id=\"prcRuleCondThruTxt#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																<ezf:inputText name="xxThruDt_A1" ezfName="xxThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" id=\"condThruDate#{EZF_ROW_NUMBER}\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('xxThruDt_A1', 4, '{EZF_ROW_NUMBER}');" id="condThruCalendar#{EZF_ROW_NUMBER}">
															</td>
															<td><ezf:inputText name="prcRuleCondNum_A1" ezfName="prcRuleCondNum_A1" value="1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
															<td>
																<ezf:inputText name="effFromDt_A1" ezfName="effFromDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A1', 4, '{EZF_ROW_NUMBER}');" >
															</td>
															<td>
																<ezf:inputText name="effThruDt_A1" ezfName="effThruDt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A1', 4, '{EZF_ROW_NUMBER}');" >
															</td>
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
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
<%--------------------------------%>
<%-- Price Adjustment Deatils	--%>
<%--------------------------------%>
					<table border="0" cellpadding="1" cellspacing="0" width="1100" height="120"  rules="none"  style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
						<tr>
<!--
							<td align="top" width="1190">
-->
							<td align="top">
								<table border="0" width="600">
									<col align="left" width="140">
									<col align="left" width="430">
									<tr>
										<td class="stab"><b>Price Adjustment Details</b></td>
										<td>
											<ezf:inputButton name="Add_PrcAdjDtl" value="Insert Row" htmlClass="pBtn6" otherAttr=" id=\"Add_PrcAdjDtl\""/>
											<ezf:inputButton name="Del_PrcAdjDtl" value="Delete Row" htmlClass="pBtn6" otherAttr=" id=\"Del_PrcAdjDtl\""/>
											<ezf:inputButton name="Download_PrcAdjDtl" value="Download" htmlClass="pBtn6" otherAttr=" id=\"Download_PrcAdjDtl\""/>
										</td>
									</tr>
								</table>
								<%-- ### MEISAI TBL - TOP --%>
								<div id="LeftTable_B_Top" style="overflow-x:hidden; overflow-y:hidden; width:1080; float:left;">
									<table border="1" cellpadding="1" cellspacing="0" style="width:1220; table-layout: fixed; margin-right:10px">
										<col align="center" width="25">
										<col align="center" width="60">
										<col align="center" width="235">
										<col align="center" width="110">
										<col align="center" width="110">
										<col align="center" width="90">
										<col align="center" width="80">
										<col align="center" width="280">
										<col align="center" width="120">
										<col align="center" width="110">
										<tr height="18">
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">Adj Con</td>
											<td class="pClothBs">Charge Name</td>
											<td class="pClothBs">Modify</td>
											<td class="pClothBs">Adjustment</td>
											<td class="pClothBs">Type</td>
											<td class="pClothBs">Adj Level</td>
											<td class="pClothBs">Value</td>
											<td class="pClothBs">Effective Date From</td>
											<td class="pClothBs">Effective Date To</td>
										</tr>
									</table>
								</div>
								<%-- ### MEISAI - TBL - BOTTOM --%>
								<div id="LeftTable_B" style="overflow-y:scroll; height:170; overflow-x:scroll; width:1097; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'LeftTable_B_Top' ));">
									<table border="1" cellpadding="1" cellspacing="0" id="B" style="table-layout: fixed">
										<col width="25"  align="center" >
										<col width="60"  align="center">
										<col width="235" align="center">
										<col width="110" align="center">
										<col width="110" align="center">
										<col width="90"  align="center">
										<col width="80"  align="center">
										<col width="280" align="left">
										<col width="120" align="center">
										<col width="110" align="center">
										<ezf:row ezfHyo="B">
											<tr height="24">
												<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
												<td><ezf:inputText name="prcRuleCondGrpCd_B1" ezfName="prcRuleCondGrpCd_B1" value="B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/></td>
												<td><ezf:inputText name="prcRuleDtlChrgNm_B1" ezfName="prcRuleDtlChrgNm_B1" value="ESS Freight Charge $0-$250" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
												<td>
													<ezf:select name="prcRuleModTpCd_B1" ezfName="prcRuleModTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcRuleModTpCd_L1" ezfDispName="prcRuleModTpDescTxt_L1" otherAttr=" style=\"width: 100px\""/>
												</td>
												<td>
													<ezf:select name="prcRuleAdjTpCd_B1" ezfName="prcRuleAdjTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcRuleAdjTpCd_L1" ezfDispName="prcRuleAdjTpDescTxt_L1" otherAttr=" style=\"width: 100px\""/>
												</td>
												<td>
													<ezf:select name="prcRuleAttrbCd_B1" ezfName="prcRuleAttrbCd_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcRuleAttrbCd_LB" ezfDispName="prcRuleAttrbDescTxt_LB" otherEvent1=" onchange=\"sendServer('OnChange_Attrb_PrcAdjDtl', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width: 80px\""/>
												</td>
												<td>
													<ezf:select name="prcRuleAdjLvlCd_B1" ezfName="prcRuleAdjLvlCd_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="prcRuleAdjLvlCd_L1" ezfDispName="prcRuleAdjLvlDescTxt_L1" otherAttr=" style=\"width: 70px\""/>
												</td>
												<td>
													<ezf:inputText name="prcFmlaPk_B1" ezfName="prcFmlaPk_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"28\" id=\"prcFmlaPk#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputText name="prcFmlaNm_B1" ezfName="prcFmlaNm_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" id=\"prcFmlaNm#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputButton name="OpenWin_PrcFmla" value="..." ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"OpenWin_PrcFmla#{EZF_ROW_NUMBER}\""/><ezf:inputText name="prcRuleDtlRate_B1" ezfName="prcRuleDtlRate_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"7\" style=\"text-align:right\" id=\"prcRuleDtlRate#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/><ezf:inputText name="prcRuleDtlTxt_B1" ezfName="prcRuleDtlTxt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"50\" style=\"text-align:right\" id=\"prcRuleDtlTxt#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
												</td>
												<td>
													<ezf:inputText name="effFromDt_B1" ezfName="effFromDt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_B1', 4, '{EZF_ROW_NUMBER}');" >
												</td>
												<td>
													<ezf:inputText name="effThruDt_B1" ezfName="effThruDt_B1" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
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
										<ezf:skip>
										</ezf:skip>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
