<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161108164645 --%>
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
			<input type="hidden" name="pageID" value="NMAL7290Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Price Adjustment Precedence">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
										<li title="Price Adjustment Precedence" class="pTab_ON"><a href="#">Prc Adj Prec</a></li>
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
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td valign="top">
								<table border="0" cellpadding="0">
									<col align="left" width="240">
									<col align="left" width="220">
									<col align="left" width="100">
									<col align="left">
									<tr>
										<td align="left" class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_PrcAdjPrcdGrp" ><b>Price Adjustment Precedence Group #</b></ezf:anchor></td>
										<td><ezf:inputText name="prcRulePrcdPk" ezfName="prcRulePrcdPk" value="1" otherAttr=" size=\"10\" maxlength=\"100\""/></td>
										<td><ezf:inputButton name="Search_PrcRulePrcd" value="Search" htmlClass="pBtn6" /></td>
									</tr>
									<tr>
										<td align="left" class="stab"><b>Price Adjustment Precedence Group Name</b></td>
										<td>
											<ezf:inputText name="prcRulePrcdGrpNm" ezfName="prcRulePrcdGrpNm" value="PARTS DISCOUNT" otherAttr=" size=\"30\" maxlength=\"100\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">
								<table border="0" cellpadding="0">
									<col align="left" width="110">
									<col align="left" width="210">
									<col align="left" width="110">
									<col align="left" width="210">
									<tr>
										<td class="stab">Precedence Action</td>
										<td>
											<ezf:select name="prcPrcdActTpCd" ezfName="prcPrcdActTpCd" ezfCodeName="prcPrcdActTpCd_L" ezfDispName="prcPrcdActTpDescTxt_L" otherEvent1=" onchange=\"sendServer('OnChange_PrcdAction')\"" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Active</td>
										<td><ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" /></td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Effective Date From</td>
										<td>
											<ezf:inputText name="effFromDt" ezfName="effFromDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Effective Date To</td>
										<td>
											<ezf:inputText name="effThruDt" ezfName="effThruDt" otherAttr=" size=\"10\" maxlength=\"10\""/>
											<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Created By</td>
										<td><ezf:inputText name="xxFullNm_H1" ezfName="xxFullNm_H1" otherAttr=" size=\"28\" maxlength=\"99\""/></td>
										<td class="stab">Creation Date</td>
										<td><ezf:inputText name="cratDt" ezfName="cratDt" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									</tr>
									<tr>
										<td class="stab">Last Updated By</td>
										<td><ezf:inputText name="xxFullNm_H2" ezfName="xxFullNm_H2" otherAttr=" size=\"28\" maxlength=\"99\""/></td>
										<td class="stab">Last Update Date</td>
										<td><ezf:inputText name="lastUpdDt" ezfName="lastUpdDt" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">
								<div  border="0">
									<fieldset style="display: inline">
										<legend style="font-size:12px;">Price Ajustment Rule / Table Search</legend>
										<table border="0" cellpadding="0">
											<col align="left" width="140">
											<col align="left" width="440">
											<col align="left" width="100">
											<col align="left" width="220">
											<col align="left" width="100">
											<tr>
												<td class="stab">Price Adjustment Category</td>
												<td>
													<ezf:select name="prcRuleCatgCd" ezfName="prcRuleCatgCd" ezfCodeName="prcRuleCatgCd_L" ezfDispName="prcRuleCatgDescTxt_L" />
												</td>
												<td class="stab">Adjustment Type</td>
												<td>
													<ezf:select name="prcRuleCondTpCd" ezfName="prcRuleCondTpCd" ezfCodeName="prcRuleCondTpCd_L" ezfDispName="prcRuleCondTpDescTxt_L" />
												</td>
												<td></td>
											</tr>
											<tr>
												<td class="stab">Price Rule or Table name(*)</td>
												<td><ezf:inputText name="prcRuleNm" ezfName="prcRuleNm" otherAttr=" size=\"60\" maxlength=\"100\" ezftoupper=\"\""/></td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderCategory" >Order Category</ezf:anchor></td>
												<td><ezf:inputText name="dsOrdCatgCd" ezfName="dsOrdCatgCd" otherAttr=" size=\"26\" maxlength=\"100\" ezftoupper=\"\""/></td>
												<td></td>
											</tr>
											<tr>
												<td class="stab">Line of Business</td>
												<td>
													<ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_L" ezfDispName="lineBizTpDescTxt_L" />
												</td>
												<td class="stab"><ezf:anchor name="" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OrderReason" >Order Reason</ezf:anchor></td>
												<td><ezf:inputText name="dsOrdTpCd" ezfName="dsOrdTpCd" otherAttr=" size=\"26\" maxlength=\"100\" ezftoupper=\"\""/></td>
												<td><ezf:inputButton name="Search_PrcRule" value="Search" htmlClass="pBtn6" /></td>
											</tr>
										</table>
									</fieldset>
								</div>
							</td>
						</tr>
					</table>
					<hr style="height: 0px;" cellpadding="0">

					<table border="0" width="100%">
<%--------------------------------%>
<%-- Search Result				--%>
<%--------------------------------%>
						<tr>
							<td align="top">
								<table border="0">
									<col align="left" width="570">
									<col width="35">
									<col width="490">
									<tr>
										<td class="stab" valign="bottom">
											<table border="0">
												<tr>
													<td class="stab" valign="bottom"><b>Search Result</b></td>
												</tr>
											</table>
										<td>&nbsp;</td>
										<td valign="bottom">
											<table border="0">
												<col width="420" align="left">
												<col>
												<tr>
													<td class="stab" valign="bottom"><b>Price Adjustment Precedence Group Detail</b></td>
													<td><ezf:inputButton name="Del_PrcRulePrcd" value="Del" htmlClass="pBtn4" /></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td valign="top">
											<div id="LeftTable_A_Top" style="overflow-x:none; overflow-y:none; width:547; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed;" height="34">
													<col align="center" width="25">
													<col align="center" width="200">
													<col align="center" width="120">
													<col align="center" width="120">
													<col align="center" width="80">
													<tr height="18">
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">Rule or Table Name</td>
														<td class="pClothBs">Price Adjustment type</td>
														<td class="pClothBs">Category</td>
														<td class="pClothBs">Default Rule</br>Precedence</td>
													</tr>
												</table>
											</div>
											<div id="LeftTable_A" style="overflow-y:scroll; height:175; overflow-x:hidden; width:565; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed">
													<col width="25" align="center">
													<col width="200" align="center">
													<col width="120" align="center">
													<col width="120" align="center">
													<col width="80" align="center">
													<ezf:row ezfHyo="A">
														<tr id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="prcRuleNm_A" ezfName="prcRuleNm_A" value="LIS PRICE BREAK" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"100\""/></td>
															<td><ezf:inputText name="prcRuleCondTpDescTxt_A" ezfName="prcRuleCondTpDescTxt_A" value="Price rule" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"100\""/></td>
															<td><ezf:inputText name="prcRuleCatgDescTxt_A" ezfName="prcRuleCatgDescTxt_A" value="PARTS DISCOUNT" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"100\""/></td>
															<td><ezf:inputText name="defRulePrcdNum_A" ezfName="defRulePrcdNum_A" value="10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" ezfhyo="C" value="Y" checked></td>
															<td><input type="text" name="prcListNm_H1" value="LFS DEALER PARTS/SUPPLIES DISCOUNT" size="26" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="Price Adjustment Table" size="15" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="PARTS DISCOUNT" size="15" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="defRulePrcdNum_A" value="10" size="3" maxlength="3" class="pPro" readOnly ezfname="defRulePrcdNum_A" ezfhyo="A"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
<%--------------------------------%>
<%-- >> << 						--%>
<%--------------------------------%>
										<td align="center">
											<ezf:inputButton name="Add_PrcRulePrcd" value=">>" htmlClass="pBtn1" />
											<br>
											<ezf:inputButton name="Rmv_PrcRulePrcd" value="<<" htmlClass="pBtn1" />
										</td>
<%--------------------------------%>
<%-- Price Adjustment Precedence--%>
<%--------------------------------%>
										<td valign="top">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:64; height:34; float:left;">
												<table border="1" cellpadding="1" cellspacing="0" width="64" height="34">
													<col align="center" width="25">
													<col align="center" width="37">
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">Seq</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:408; height:34; float:left;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1250" height="34">
													<col align="center" width="60">
													<col align="center" width="200">
													<col align="center" width="120">
													<col align="center" width="120">
													<col align="center" width="120">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="100">
													<col align="center" width="80">
													<col align="center" width="100">
													<col align="center">
													<tr height="18">
														<td class="pClothBs">ID</td>
														<td class="pClothBs">Rule or table Name</td>
														<td class="pClothBs">Price Adjustment Type</td>
														<td class="pClothBs">Line of Business</td>
														<td class="pClothBs">Category</td>
														<td class="pClothBs">Apply Automatically</td>
														<td class="pClothBs">Override</td>
														<td class="pClothBs">Status</td>
														<td class="pClothBs">Default Rule<br>Precedence</td>
														<td class="pClothBs">Effective Date From</td>
														<td class="pClothBs">Effective Date<br> To</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:175; width:64; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="64" id="B1">
													<col width="25" align="center">
													<col width="37" align="center">
													<ezf:row ezfHyo="B">
														<tr height="24" id="B_leftTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
															<td><ezf:inputText name="prcRulePrcdSqNum_B" ezfName="prcRulePrcdSqNum_B" value="2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"4\" maxlength=\"100\" style=\"text-align:right\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="24">
															<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" ezfhyo="B" value="Y"></td>
															<td><input type="text" name="prcListNm_H1" value="1" size="4" maxlength="100" style="text-align:right"></td>
														</tr>
														<tr height="24">
															<td><input type="checkbox" name="xxChkBox_C1" ezfname="xxChkBox_C1" ezfhyo="B" value="Y"></td>
															<td><input type="text" name="prcListNm_H1" value="1" size="4" maxlength="100" style="text-align:right"></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:175; width:425; float:left;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1250" id="B2">
													<col width="60" align="center">
													<col width="200" align="center">
													<col width="120" align="center">
													<col width="120" align="center">
													<col width="120" align="center">
													<col width="100" align="center">
													<col width="100" align="center">
													<col width="100" align="center">
													<col width="80" align="center">
													<col width="100" align="center">
													<col align="center">
													<ezf:row ezfHyo="B">
														<tr height="24" id="B_rightTBLRow#{EZF_ROW_NUMBER}">
															<td><ezf:inputText name="prcRuleHdrPk_B" ezfName="prcRuleHdrPk_B" value="65" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"28\" style=\"text-align:right\""/></td>
															<td><ezf:inputText name="prcRuleNm_B" ezfName="prcRuleNm_B" value="LFS PRICE BREAK" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"24\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="prcRuleCondTpDescTxt_B" ezfName="prcRuleCondTpDescTxt_B" value="PRICE RULE" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="lineBizTpDescTxt_B" ezfName="lineBizTpDescTxt_B" value="LFS" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"50\""/></td>
															<td><ezf:inputText name="prcRuleCatgDescTxt_B" ezfName="prcRuleCatgDescTxt_B" value="PARTS DISCOUNT" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"100\""/></td>
															<td><ezf:inputText name="applyAutoFlg_B" ezfName="applyAutoFlg_B" value="YES" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"1\""/></td>
															<td><ezf:inputText name="ovrdFlg_B" ezfName="ovrdFlg_B" value="YES" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"1\""/></td>
															<td><ezf:inputText name="xxScrItem8Txt_B" ezfName="xxScrItem8Txt_B" value="ACTIVE" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\""/></td>
															<td><ezf:inputText name="defRulePrcdNum_B" ezfName="defRulePrcdNum_B" value="2" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
															<td><ezf:inputText name="effFromDt_B" ezfName="effFromDt_B" value="01/01/2014" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
															<td><ezf:inputText name="effThruDt_B" ezfName="effThruDt_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
			                                                <td class="pAuditInfo">
			                                                    <ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" />
			                                                    <ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" />
			                                                </td>
														</tr>
													</ezf:row>
													<ezf:skip>
														<tr height="24">
															<td><input type="text" name="prcListNm_H1" value="40" size="6" maxlength="100" style="text-align:right" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="LFS DEALER PARTS/SUPPLIES DISCOUNT" size="24" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="PRICE ADJUSTMENT TABLE" size="12" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="LFS" size="5" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="PARTS DISCOUNT" size="15" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="YES" size="3" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="YES" size="3" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="ACTIVE" size="8" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="2" size="3" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="03/04/2015" size="10" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="10" maxlength="100" class="pPro" readOnly></td>
														</tr>
														<tr height="24">
															<td><input type="text" name="prcListNm_H1" value="55" size="6" maxlength="100" style="text-align:right" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="LFS PARTS" size="24" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="12" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="5" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="15" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="3" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="3" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="8" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="3" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="10" maxlength="100" class="pPro" readOnly></td>
															<td><input type="text" name="prcListNm_H1" value="" size="10" maxlength="100" class="pPro" readOnly></td>
														</tr>
													</ezf:skip>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
