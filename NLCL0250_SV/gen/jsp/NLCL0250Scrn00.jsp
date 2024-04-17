<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230524152142 --%>
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
			<input type="hidden" name="pageID" value="NLCL0250Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Onhand Inventory Inquiry">

			<center>
				<table width="1133" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
<%-- #################################################### HEADER ################################################### --%>
							<%-- ###TAB - HEAD --%>
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Onhand Inventory Inquiry" class="pTab_ON"><a href="./NLCL0250Scrn00.html">Onhand Inventory Inquiry</a></li>
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
							<%-- ###TAB - BODY --%>
							<div class="pTab_BODY">
								<!-- ################################################## Search Criteria - [START] ################################################## -->
								<table border="0" cellspacing="0" cellpadding="0" style="width:100%; text-align:left;height:30px;margin-left:3px;margin-bottom:-5px;">
									<col width="152">
									<col width="345">
									<col width="110">
									<col width="300">
									<col width="">
									<tr>
										<td class="stab">Saved Search Options</td>
										<td>
											<ezf:select name="srchOptPk_S" ezfName="srchOptPk_S" ezfBlank="1" ezfCodeName="srchOptPk_L" ezfDispName="srchOptNm_L" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:320px\" id=\"srchOptPk\""/>
										</td>
										<td class="stab">Search Option Name</td>
										<td class="stab"><ezf:inputText name="srchOptNm_S" ezfName="srchOptNm_S" otherAttr=" size=\"40\" maxlength=\"50\""/></td>
										<td>
											<ezf:inputButton name="SaveSearch" value="Save Search" htmlClass="pBtn8" />
											<ezf:inputButton name="DeleteSearch" value="Delete Search" htmlClass="pBtn8" />
										</td>
									</tr>
								</table>
								<hr style="height: 0px;" cellpadding="0">
								<!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
								<!-- <table border="0" cellpadding="0" cellspacing="0" id="H"> -->
								<table border="0" cellpadding="0" cellspacing="0" id="H" style="width:100%; table-layout:fixed;">
								<!-- END 2023/05/15 S.Dong [QC#61398, MOD] -->
									<col align="left" width="5">
									<col align="left" width="92">
									<col align="left" width="101">
									<col align="left" width="95">
									<col align="left" width="130">
									<col align="left" width="98">
									<col align="left" width="82">
									<col align="left" width="18">
									<col align="left" width="18">
									<col align="left" width="82">
									<col align="left" width="76">
									<col align="left" width="83">
									<!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
									<!-- <col align="left" width="80">
                                    <col align="left" width="70">
                                    <col align="left" width="80"> -->
									<col align="left" width="70">
									<col align="left" width="55">
									<col align="left" width="110">
									<!-- END 2023/05/15 S.Dong [QC#61398, MOD] -->
									<tr height="24">
										<td rowspan="5">&nbsp;</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_BU" ezfEmulateBtn="1" ezfGuard="Product_Line_Group_Link" otherAttr=" id=\"xxLinkAncr_BU\"">Business Unit(*)</ezf:anchor></td>
										<td><ezf:inputText name="zerothProdCtrlNm_H1" ezfName="zerothProdCtrlNm_H1" value="ZEROTH" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\" id=\"zerothProdCtrlNm_H1\""/></td>
										<td class="stab">Merchandise Type</td>
										<td>
											<ezf:select name="coaProjCd_H1" ezfName="coaProjCd_H1" ezfBlank="1" ezfCodeName="coaProjCd_L1" ezfDispName="xxScrItem61Txt_LP" otherAttr=" style=\"width:118px\" id=\"coaProjCd_H1\""/>
										</td>
										<td class="stab">Warehouse Type</td>
										<td colspan="4">
											<ezf:select name="rtlWhCatgCd_H1" ezfName="rtlWhCatgCd_H1" ezfBlank="1" ezfCodeName="rtlWhCatgCd_L1" ezfDispName="xxScrItem61Txt_LR" otherAttr=" style=\"width:118px\" id=\"rtlWhCatgCd_H1\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Location Status</td>
										<td colspan="3" rowspan="2" valign="top">
											<div id="Location Status" style="width:235; overflow-x:hidden; overflow-y:scroll; height:44;">
											<table border="1" cellpadding="1" cellspacing="0" id="L">
												<ezf:row ezfHyo="L">
												<tr>
													<td>
														<ezf:inputCheckBox name="xxChkBox_LS" ezfName="xxChkBox_LS" value="Y" ezfHyo="L" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_LS{EZF_ROW_NUMBER}\""/>
														<ezf:inputHidden name="locStsCd_LS" ezfName="locStsCd_LS" value="1" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" ezftoupper=\"\""/>
													</td>
													<td><ezf:inputText name="xxScrItem61Txt_LS" ezfName="xxScrItem61Txt_LS" value="01:In-Transit (PO)" ezfHyo="L" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="03:DC Stock" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="07:WIP (Kit)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="08:WIP (Un-Kit)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="11:Loan" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="15:Waiting for Delivery" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="16:In-Transit (WH Transfer)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="20:WIP (Reman)" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
									<tr height="24">
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_PG" ezfEmulateBtn="1" ezfGuard="Product_Line_Link" otherAttr=" id=\"xxLinkAncr_PG\"">Product Group(*)</ezf:anchor></td>
										<td><ezf:inputText name="firstProdCtrlNm_H1" ezfName="firstProdCtrlNm_H1" value="FIRST" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\" id=\"firstProdCtrlNm_H1\""/></td>
										<td class="stab">Item Type</td>
										<td>
											<ezf:select name="mdseItemTpCd_H1" ezfName="mdseItemTpCd_H1" ezfBlank="1" ezfCodeName="mdseItemTpCd_L1" ezfDispName="xxScrItem61Txt_LI" otherAttr=" style=\"width:118px\" id=\"mdseItemTpCd_H1\""/>
										</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WR" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoRtlWh" otherAttr=" id=\"xxLinkAncr_WR\"">Warehouse(*)</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCdSrchTxt_RW" ezfName="rtlWhCdSrchTxt_RW" value="1Z1" otherAttr=" size=\"10\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlWhCdSrchTxt_RW\""/></td>
										<td colspan="2" align="left"><ezf:inputButton name="Search_RtlWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td colspan="2"><ezf:inputText name="rtlWhNmSrchTxt_RW" ezfName="rtlWhNmSrchTxt_RW" value="NORCROSS WH - CSA" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlWhNmSrchTxt_RW\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="24">
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_PF" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_2_Link" otherAttr=" id=\"xxLinkAncr_PF\"">Product Family(*)</ezf:anchor></td>
										<td><ezf:inputText name="scdProdCtrlNm_H1" ezfName="scdProdCtrlNm_H1" value="SCD" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\" id=\"scdProdCtrlNm_H1\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_IT" ezfEmulateBtn="1" ezfGuard="Item_Link" otherAttr=" id=\"xxLinkAncr_IT\"">Item Number(*)</ezf:anchor></td>
										<td><ezf:inputText name="xxMdseSrchTxt_H1" ezfName="xxMdseSrchTxt_H1" value="ITEM NUMBER" otherAttr=" size=\"16\" maxlength=\"1000\" ezftoupper=\"\" id=\"xxMdseSrchTxt_H1\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_WS" ezfEmulateBtn="1" ezfGuard="OpenWin_LocInfoRtlSWh" otherAttr=" id=\"xxLinkAncr_WS\"">Sub Warehouse(*)</ezf:anchor></td>
										<td><ezf:inputText name="rtlWhCdSrchTxt_SW" ezfName="rtlWhCdSrchTxt_SW" value="U90" otherAttr=" size=\"10\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlWhCdSrchTxt_SW\""/></td>
										<td colspan="2" align="left"><ezf:inputButton name="Search_RtlSWHInfo" value=">>" htmlClass="pBtn0" /></td>
										<td colspan="2"><ezf:inputText name="rtlWhNmSrchTxt_SW" ezfName="rtlWhNmSrchTxt_SW" value="USED 90" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\" id=\"rtlWhNmSrchTxt_SW\""/></td>
										<td class="stab">Stock Status</td>
										<td colspan="3" rowspan="2" valign="top">
											<div id="Stock Status" style="width:235; overflow-x:hidden; overflow-y:scroll; height:44;">
											<table border="1" cellpadding="1" cellspacing="0" id="S">
												<ezf:row ezfHyo="S">
												<tr>
													<td>
														<ezf:inputCheckBox name="xxChkBox_SS" ezfName="xxChkBox_SS" value="Y" ezfHyo="S" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_SS{EZF_ROW_NUMBER}\""/>
														<ezf:inputHidden name="stkStsCd_SS" ezfName="stkStsCd_SS" value="1" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"1\" maxlength=\"1\" ezftoupper=\"\""/>
													</td>
													<td><ezf:inputText name="xxScrItem61Txt_SS" ezfName="xxScrItem61Txt_SS" value="1:Good" ezfHyo="S" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"60\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="2:Rank-B" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="3:Waiting for Inspection" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="4:Defective" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												<tr>
													<td><input type="checkbox"></td>
													<td><input type="text" value="5:To Be Reman" size="30" style="border:none;background-color:transparent;padding:0px;"/></td>
												</tr>
												</ezf:skip>
											</table>
											</div>
										</td>
									</tr>
									<tr height="24">
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_PL" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_3_Link" otherAttr=" id=\"xxLinkAncr_PL\"">Product Line(*)</ezf:anchor></td>
										<td><ezf:inputText name="thirdProdCtrlNm_H1" ezfName="thirdProdCtrlNm_H1" value="THIRD" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\" id=\"thirdProdCtrlNm_H1\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_SN" ezfEmulateBtn="1" ezfGuard="OpenWin_Serial" otherAttr=" id=\"xxLinkAncr_SN\"">Serial Number(*)</ezf:anchor></td>
										<td><ezf:inputText name="xxSerNumSrchTxt_H1" ezfName="xxSerNumSrchTxt_H1" value="XX" otherAttr=" size=\"16\" maxlength=\"1000\" ezftoupper=\"\" id=\"xxSerNumSrchTxt_H1\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_ST" ezfEmulateBtn="1" ezfGuard="OpenWin_ShipToLoc" otherAttr=" id=\"xxLinkAncr_ST\"">To Location(*)</ezf:anchor></td>
										<td><ezf:inputText name="xxFldValTxt_HC" ezfName="xxFldValTxt_HC" value="U90" otherAttr=" size=\"10\" maxlength=\"1000\" ezftoupper=\"\" id=\"xxFldValTxt_HC\""/></td>
										<td colspan="2" align="left"><ezf:inputButton name="Search_ShipToLocInfo" value=">>" htmlClass="pBtn0" /></td>
										<td colspan="2"><ezf:inputText name="xxFldValTxt_HN" ezfName="xxFldValTxt_HN" value="SHIP TO LOCATION" otherAttr=" size=\"20\" maxlength=\"1000\" ezftoupper=\"\" id=\"xxFldValTxt_HN\""/></td>
										<td>&nbsp;</td>
									</tr>
									<tr height="24">
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_PS" ezfEmulateBtn="1" ezfGuard="Product_Line_Level_4_Link" otherAttr=" id=\"xxLinkAncr_PS\"">Product Series(*)</ezf:anchor></td>
										<td><ezf:inputText name="frthProdCtrlNm_H1" ezfName="frthProdCtrlNm_H1" value="FRTH" otherAttr=" size=\"12\" maxlength=\"50\" ezftoupper=\"\" id=\"frthProdCtrlNm_H1\""/></td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkAncr_CN" ezfEmulateBtn="1" ezfGuard="OpenWin_Config" otherAttr=" id=\"xxLinkAncr_CN\"">Config ID(*)</ezf:anchor></td>
										<td><ezf:inputText name="srchOptTxt_CF" ezfName="srchOptTxt_CF" value="XX" otherAttr=" size=\"16\" maxlength=\"1000\" ezftoupper=\"\" id=\"srchOptTxt_CF\""/></td>
										<td>Age</td>
										<td>
											<ezf:inputText name="xxFromDt_H1" ezfName="xxFromDt_H1" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxFromDt_H1\""/>
										</td>
										<td>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxFromDt_H1', 4 );">
										</td>
										<td align="center">-</td>
										<td>
											<ezf:inputText name="xxThruDt_H1" ezfName="xxThruDt_H1" value="07/09/2015" otherAttr=" size=\"10\" maxlength=\"10\" id=\"xxThruDt_H1\""/>
										</td>
										<td>
											<img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'xxThruDt_H1', 4 );">
										</td>
										<!-- START 2023/03/07 S.Dong [QC#61205, MOD] -->
										<td class="stab">WH Operation</td>
                                        <td colspan="2">
                                        <!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
                                            <!-- <select name="whOwnrCd_H1" ezfname="whOwnrCd_H1" ezflist="whOwnrCd_L1,whOwnrNm_L1,99, ,blank" style="width:152px;"> -->
                                            <ezf:select name="whOwnrCd_H1" ezfName="whOwnrCd_H1" ezfBlank="1" ezfCodeName="whOwnrCd_L1" ezfDispName="whOwnrNm_L1" otherAttr=" style=\"width:125px;\""/>
                                        </td>
                                        <!-- END 2023/03/07 S.Dong [QC#61205, MOD] -->
									</tr>
									<!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
									<!-- <tr height="24> -->
									<tr height="27">
									<!-- END 2023/05/15 S.Dong [QC#61398, MOD] -->
										<td>&nbsp;</td>
										<td>Parts Return Controlled</td>
										<td>
											<ezf:inputCheckBox name="xxChkBox_PR" ezfName="xxChkBox_PR" value="Y" />
										</td>
										<td class="stab"><ezf:anchor name="" ezfName="xxLinkProt_RC" ezfEmulateBtn="1" ezfGuard="Rtrn_Ctrl_Tp_Link" otherAttr=" tabindex=\"-1\"">Return Control Type</ezf:anchor></td>
										<td>
											<ezf:inputText name="rtrnCtrlTpVndRelnPk_H1" ezfName="rtrnCtrlTpVndRelnPk_H1" otherAttr=" size=\"16\" maxlength=\"2\""/>
										</td>
										<td colspan="2">
											<ezf:inputText name="xxScrItem500Txt_H1" ezfName="xxScrItem500Txt_H1" value="REFURBISH/HYTEC DEALER SERVICES,INC./EPAY" otherAttr=" size=\"24\" maxlength=\"500\" ezftoupper=\"\" id=\"xxScrItem500Txt_H1\""/>
											<ezf:inputHidden name="rtrnCtrlTpCd_H1" ezfName="rtrnCtrlTpCd_H1" />
											<ezf:inputHidden name="rtrnCtrlTpNm_H1" ezfName="rtrnCtrlTpNm_H1" />
											<ezf:inputHidden name="prntVndCd_H1" ezfName="prntVndCd_H1" />
											<ezf:inputHidden name="prntVndNm_H1" ezfName="prntVndNm_H1" />
											<ezf:inputHidden name="vndCd_H1" ezfName="vndCd_H1" />
											<ezf:inputHidden name="vndNm_H1" ezfName="vndNm_H1" />
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<!-- START 2023/03/07 S.Dong [QC#61205, MOD] -->
										<!-- <td>&nbsp;</td> -->
                                        <!-- <td>&nbsp;</td> -->
                                        <td class="stab">Display Option</td>
                                        <td class="stab"><ezf:inputRadio name="xxDplyCtrlFlg_H1" ezfName="xxDplyCtrlFlg_H1" value="1" otherAttr=" id=\"xxDplyCtrlFlg_H1\""/>Summary</td>
                                        <td class="stab"><ezf:inputRadio name="xxDplyCtrlFlg_H1" ezfName="xxDplyCtrlFlg_H1" value="2" otherAttr=" id=\"xxDplyCtrlFlg_H1\""/>Detail</td>
                                        <td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
                                        <td>&nbsp;</td>
                                        <!-- END 2023/03/07 S.Dong [QC#61205, MOD] -->
									</tr>
									<!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
									<tr height="24">
										<td colspan="14"></td>
										<td align="right"><ezf:inputButton name="OpenWin_InvMaterial" value="3rd Party Inv Material" htmlClass="pBtn10" /></td>
									</tr>
									<!-- END 2023/05/15 S.Dong [QC#61398, MOD] -->
								</table>
<%-- #################################################### DETAIL ################################################### --%>
<%@ page import="business.servlet.NLCL0250.NLCL0250BMsg" %>
<%@ page import="business.servlet.NLCL0250.NLCL0250_ABMsg" %>
<%  NLCL0250BMsg bMsg = (NLCL0250BMsg)databean.getEZDBMsg(); %>

								<hr style="height: 0px;" cellpadding="0">
								<table cellpadding="0" cellspacing="0">
									<col width="003">
									<col width="200">
									<col width="345">
									<col width="555">
									<tr>
										<td></td>
										<td>
											<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
										</td>
										<td></td>
										<td align="right">
											<ezf:skip>
											<table border="0" cellpadding="0" width="100%">
												<tr>
													<td align="left">
														<table border="0" cellpadding="0" align="left" cellspacing="0">
															<col>
																<tr>
																<td>Results 1000 - 1000 of 1000</td>
																</tr>
														</table>
													</td>
													<td align="right">
														<table border="0" cellpadding="0" cellspacing="0">
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
																<td class="stab">Showing</td>
																<td><input type="text" name="xxPageShowCurNum" value="9999" size="4" maxlength="4" style="text-align:right"></td>
																<td class="stab">/</td>
																<td><input type="text" name="xxPageShowTotNum" ezfName="xxPageShowTotNum" size="4" maxlength="4" value="9999" class="pPro" style="text-align:right" readOnly></td>
																<td class="stab">page</td>
																<td>&nbsp;</td>
																<td><input type="button" class="pBtn3" value="Jump" id="btnJump" name="PageJump" onclick="tablePagenation(this.name, 'A')"></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" id="btnPrev" name="PagePrev" onclick="tablePagenation(this.name, 'A')"></td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Next" id="btnNext" name="PageNext" onclick="tablePagenation(this.name, 'A')"></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
											</ezf:skip>
											<table width="100%">
												<tr align="right">
													<td>
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
															<jsp:param name="beanId"         value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"        value="A" />
															<jsp:param name="ShowingFrom"    value="xxPageShowFromNum_A" />
															<jsp:param name="ShowingTo"      value="xxPageShowToNum_A" />
															<jsp:param name="ShowingOf"      value="xxPageShowOfNum_A" />
															<jsp:param name="ShowingCurrent" value="xxPageShowCurNum_A" />
															<jsp:param name="ShowingTotal"   value="xxPageShowTotNum_A" />
															<jsp:param name="ViewMode"       value="FULL" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<div id="parentBoxA">
								<table border="0">
									<tr>
										<td valign="Top" width="1132">
											<div style="float:left; display:block"> <!-- left table -->
												<div id='leftTblHead' style="display:block;">
												</div>
												<!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
												<!-- <div id='leftTbl' style="float:left; display:block; height:300; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
												</div> -->
												<div id='leftTbl' style="float:left; display:block; height:273; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px">
                                                </div>
												<!-- END 2023/05/15 S.Dong [QC#61398, MOD] -->
											</div>  <!-- left table -->
											<%-- LEFT-TABLE(TOP) --%>
											<div style="float:left"> <!-- right table -->
												<div id='rightTblHead' style="width:1095px; display:block; overflow:hidden; margin:0px;padding:0px;">
													<table border="1" cellpadding="1" cellspacing="0" style="table-layout: fixed" id="AHEAD" width="3788px" style="margin-right:20px">
														<col align="center" width="123">		<!-- Merchandise Code -->
														<col align="center" width="198">		<!-- Merchandise Name -->
														<col align="center" width="138">		<!-- Serial Number -->
														<col align="center" width="114">		<!-- WH Type Name -->
														<col align="center" width="145">		<!-- Retail Warehouse Name -->
														<col align="center" width="63">			<!-- Sub Warehouse Code -->
														<col align="center" width="70">			<!-- Min Qty -->
														<col align="center" width="70">			<!-- Max Qty -->
														<col align="center" width="82.5">		<!-- Inventory Quantity -->
														<col align="center" width="82.5">		<!-- Inventory Available Quantity -->
														<col align="center" width="153">		<!-- Config ID -->
														<col align="center" width="120">		<!-- Stock Status Name -->
														<col align="center" width="172">		<!-- Location Status Name -->
														<col align="center" width="107">		<!-- Inventory Age -->
														<col align="center" width="172">		<!-- From Location -->
														<col align="center" width="172">		<!-- To Location -->
														<col align="center" width="122">		<!-- Sorce Document Type -->
														<col align="center" width="122">		<!-- Sorce Document Number -->
														<col align="center" width="122">		<!-- Sorce Document Line Number -->
														<col align="center" width="71">			<!-- Retail Warehouse Code -->
														<col align="center" width="122">		<!-- Merchandise Type Name -->
														<col align="center" width="122">		<!-- Item Type Name -->
														<col align="center" width="122">		<!-- Zeroth Product Control -->
														<col align="center" width="122">		<!-- First Product Control -->
														<col align="center" width="122">		<!-- Second Product Control -->
														<col align="center" width="122">		<!-- Third Product Control -->
														<col align="center" width="122">		<!-- Forth Product Control -->
														<col align="center" width="50">			<!-- IB -->
														<col align="center" width="50">			<!-- Serial Trx -->
														<tr height="24">
															<td id="AH0"  class="pClothBs"><a href="#" class="pSortCol" id="mdseCd_A1" onclick="columnSort( 'A', 'mdseCd_A1' )">Item Number<img id="sortIMG.mdseCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH1"  class="pClothBs"><a href="#" class="pSortCol" id="mdseDescShortTxt_A1" onclick="columnSort( 'A', 'mdseDescShortTxt_A1' )">Item Description<img id="sortIMG.mdseDescShortTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH2"  class="pClothBs"><a href="#" class="pSortCol" id="serNum_A1" onclick="columnSort( 'A', 'serNum_A1' )">Serial Number<img id="sortIMG.serNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH3"  class="pClothBs"><a href="#" class="pSortCol" id="rtlWhCatgDescTxt_A1" onclick="columnSort( 'A', 'rtlWhCatgDescTxt_A1' )">Warehouse Type<img id="sortIMG.rtlWhCatgDescTxt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH4"  class="pClothBs"><a href="#" class="pSortCol" id="rtlWhNm_A1" onclick="columnSort( 'A', 'rtlWhNm_A1' )">Warehouse<img id="sortIMG.rtlWhNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH5"  class="pClothBs"><a href="#" class="pSortCol" id="rtlSwhCd_A1" onclick="columnSort( 'A', 'rtlSwhCd_A1' )">Sub WH<img id="sortIMG.rtlSwhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH6"  class="pClothBs"><a href="#" class="pSortCol" id="ropQty_A1" onclick="columnSort( 'A', 'ropQty_A1' )">Min Qty<img id="sortIMG.ropQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH7"  class="pClothBs"><a href="#" class="pSortCol" id="maxInvtyQty_A1" onclick="columnSort( 'A', 'maxInvtyQty_A1' )">Max Qty<img id="sortIMG.maxInvtyQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH8"  class="pClothBs"><a href="#" class="pSortCol" id="invtyQty_A1" onclick="columnSort( 'A', 'invtyQty_A1' )">On Hand Qty<img id="sortIMG.invtyQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH9"  class="pClothBs"><a href="#" class="pSortCol" id="invtyAvalQty_A1" onclick="columnSort( 'A', 'invtyAvalQty_A1' )">Available Qty<img id="sortIMG.invtyAvalQty_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH10"  class="pClothBs"><a href="#" class="pSortCol" id="svcConfigMstrPk_A1" onclick="columnSort( 'A', 'svcConfigMstrPk_A1' )">Config ID<img id="sortIMG.svcConfigMstrPk_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH11"  class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_AS" onclick="columnSort( 'A', 'xxScrItem61Txt_AS' )">Stock Status<img id="sortIMG.xxScrItem61Txt_AS" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH12" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_AL" onclick="columnSort( 'A', 'xxScrItem61Txt_AL' )">Location Status<img id="sortIMG.xxScrItem61Txt_AL" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH13" class="pClothBs"><a href="#" class="pSortCol" id="agingCnt_A1" onclick="columnSort( 'A', 'agingCnt_A1' )">Inventory Age<img id="sortIMG.agingCnt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH14" class="pClothBs"><a href="#" class="pSortCol" id="dsAcctNm_A1" onclick="columnSort( 'A', 'dsAcctNm_A1' )">From Location<img id="sortIMG.dsAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH15" class="pClothBs"><a href="#" class="pSortCol" id="shipToAcctNm_A1" onclick="columnSort( 'A', 'shipToAcctNm_A1' )">To Location<img id="sortIMG.shipToAcctNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH16" class="pClothBs"><a href="#" class="pSortCol" id="sceOrdTpNm_A1" onclick="columnSort( 'A', 'sceOrdTpNm_A1' )">Souce Doc Type<img id="sortIMG.sceOrdTpNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH17" class="pClothBs"><a href="#" class="pSortCol" id="trxHdrNum_A1" onclick="columnSort( 'A', 'trxHdrNum_A1' )">Souce Doc#<img id="sortIMG.trxHdrNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH18" class="pClothBs"><a href="#" class="pSortCol" id="dplyLineNum_A1" onclick="columnSort( 'A', 'dplyLineNum_A1' )">Line#<img id="sortIMG.dplyLineNum_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH19" class="pClothBs"><a href="#" class="pSortCol" id="rtlWhCd_A1" onclick="columnSort( 'A', 'rtlWhCd_A1' )">WH Code<img id="sortIMG.rtlWhCd_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH20" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A5" onclick="columnSort( 'A', 'xxScrItem61Txt_A5' )">Merchandise Type<img id="sortIMG.xxScrItem61Txt_A5" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH21" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A6" onclick="columnSort( 'A', 'xxScrItem61Txt_A6' )">Item Type<img id="sortIMG.xxScrItem61Txt_A6" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH22" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A0" onclick="columnSort( 'A', 'xxScrItem61Txt_A0' )">Business Unit<img id="sortIMG.xxScrItem61Txt_A0" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH23" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A1" onclick="columnSort( 'A', 'xxScrItem61Txt_A1' )">Product Group<img id="sortIMG.xxScrItem61Txt_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH24" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A2" onclick="columnSort( 'A', 'xxScrItem61Txt_A2' )">Product Family<img id="sortIMG.xxScrItem61Txt_A2" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH25" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A3" onclick="columnSort( 'A', 'xxScrItem61Txt_A3' )">Product Line<img id="sortIMG.xxScrItem61Txt_A3" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH26" class="pClothBs"><a href="#" class="pSortCol" id="xxScrItem61Txt_A4" onclick="columnSort( 'A', 'xxScrItem61Txt_A4' )">Product Series<img id="sortIMG.xxScrItem61Txt_A4" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH27" class="pClothBs"><a href="#" class="pSortCol" id="mainMachFlg_A1" onclick="columnSort( 'A', 'mainMachFlg_A1' )">IB<img id="sortIMG.mainMachFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
															<td id="AH28" class="pClothBs"><a href="#" class="pSortCol" id="serNumFlg_A1" onclick="columnSort( 'A', 'serNumFlg_A1' )">Trx<img id="sortIMG.serNumFlg_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
														</tr>
													</table>
												</div><!-- rightTblHead -->
												<!-- START 2023/05/15 S.Dong [QC#61398, MOD] -->
												<!-- <div id="rightTbl" style="width:1112px; height:310; display:block; overflow:scroll; margin:0px; padding:0px;" onscroll="setScrollPosition();synchroScrollTop(this.id, new Array('leftTbl'));synchroScrollLeft(this.id, new Array('rightTblHead'));"> -->
												<div id="rightTbl" style="width:1112px; height:283; display:block; overflow:scroll; margin:0px; padding:0px;" onscroll="setScrollPosition();synchroScrollTop(this.id, new Array('leftTbl'));synchroScrollLeft(this.id, new Array('rightTblHead'));">
												<!-- END 2023/05/15 S.Dong [QC#61398, MOD] -->
													<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout: fixed"  width="3788px" >
														<col align="left"   width="123">		<!-- Merchandise Code -->
														<col align="center" width="198">		<!-- Merchandise Name -->
														<col align="center" width="138">		<!-- Serial Number -->
														<col align="center" width="114">		<!-- WH Type Name -->
														<col align="center" width="145">		<!-- Retail Warehouse Name -->
														<col align="center" width="63">			<!-- Sub Warehouse Code -->
														<col align="center" width="70">			<!-- Min Qty -->
														<col align="center" width="70">			<!-- Max Qty -->
														<col align="center" width="82.5">		<!-- Inventory Quantity -->
														<col align="center" width="82.5">		<!-- Inventory Available Quantity -->
														<col align="center" width="153">		<!-- Config ID -->
														<col align="center" width="120">		<!-- Stock Status Name -->
														<col align="center" width="172">		<!-- Location Status Name -->
														<col align="center" width="107">		<!-- Inventory Age -->
														<col align="center" width="172">		<!-- From Location -->
														<col align="center" width="172">		<!-- To Location -->
														<col align="center" width="122">		<!-- Sorce Document Type -->
														<col align="center" width="122">		<!-- Sorce Document Number -->
														<col align="center" width="122">		<!-- Sorce Document Line Number -->
														<col align="center" width="71">			<!-- Retail Warehouse Code -->
														<col align="center" width="122">		<!-- Merchandise Type Name -->
														<col align="center" width="122">		<!-- Item Type Name -->
														<col align="center" width="122">		<!-- Zeroth Product Control -->
														<col align="center" width="122">		<!-- First Product Control -->
														<col align="center" width="122">		<!-- Second Product Control -->
														<col align="center" width="122">		<!-- Third Product Control -->
														<col align="center" width="122">		<!-- Forth Product Control -->
														<col align="center" width="50">			<!-- IB -->
														<col align="center" width="50">			<!-- Serial Trx -->
														<% int indexA = 0; %>
														<ezf:row ezfHyo="A">
														<tr height="24" id="id_leftA_row{EZF_ROW_NUMBER}">
															<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_ItemDetail" otherAttr=" ezfanchortext=\"\" id=\"mdseCd_A1{EZF_ROW_NUMBER}\""><ezf:label name="mdseCd_A1" ezfName="mdseCd_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
															<td><ezf:inputText name="mdseDescShortTxt_A1" ezfName="mdseDescShortTxt_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mdseDescShortTxt_A1{EZF_ROW_NUMBER}\" size=\"28\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"serNum_A1{EZF_ROW_NUMBER}\" size=\"18\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="rtlWhCatgDescTxt_A1" ezfName="rtlWhCatgDescTxt_A1" value="Tech Car Stock" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlWhCatgDescTxt_A1{EZF_ROW_NUMBER}\" size=\"14\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="rtlWhNm_A1" ezfName="rtlWhNm_A1" value="MONROE WH - CSA" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlWhNm_A1{EZF_ROW_NUMBER}\" size=\"19\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="rtlSwhCd_A1" ezfName="rtlSwhCd_A1" value="U70" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlSwhCd_A1{EZF_ROW_NUMBER}\" size=\"10\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="ropQty_A1" ezfName="ropQty_A1" value="9,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"ropQty_A1{EZF_ROW_NUMBER}\" size=\"9\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="maxInvtyQty_A1" ezfName="maxInvtyQty_A1" value="9,999,999" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"maxInvtyQty_A1{EZF_ROW_NUMBER}\" size=\"9\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="invtyQty_A1" ezfName="invtyQty_A1" value="123,456,789" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invtyQty_A1{EZF_ROW_NUMBER}\" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="invtyAvalQty_A1" ezfName="invtyAvalQty_A1" value="123,456,789" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"invtyAvalQty_A1{EZF_ROW_NUMBER}\" size=\"11\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="svcConfigMstrPk_A1" ezfName="svcConfigMstrPk_A1" value="316259816" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcConfigMstrPk_A1{EZF_ROW_NUMBER}\" size=\"20\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_AS" ezfName="xxScrItem61Txt_AS" value="01:Good" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_AS{EZF_ROW_NUMBER}\" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_AL" ezfName="xxScrItem61Txt_AL" value="03:DC Stock" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_AL{EZF_ROW_NUMBER}\" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="agingCnt_A1" ezfName="agingCnt_A1" value="33" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"agingCnt_A1{EZF_ROW_NUMBER}\" size=\"13\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="dsAcctNm_A1" ezfName="dsAcctNm_A1" value="JAGAN AND VENKAT COPIERS LLC." ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dsAcctNm_A1{EZF_ROW_NUMBER}\" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="shipToAcctNm_A1" ezfName="shipToAcctNm_A1" value="OMNICARE INC- TEST CK" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"shipToAcctNm_A1{EZF_ROW_NUMBER}\" size=\"22\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="sceOrdTpNm_A1" ezfName="sceOrdTpNm_A1" value="Direct Sales" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"sceOrdTpNm_A1{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="trxHdrNum_A1" ezfName="trxHdrNum_A1" value="CP001001" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"trxHdrNum_A1{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="dplyLineNum_A1" ezfName="dplyLineNum_A1" value="001.002.003" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"dplyLineNum_A1{EZF_ROW_NUMBER}\" size=\"15\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="rtlWhCd_A1" ezfName="rtlWhCd_A1" value="2Z1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rtlWhCd_A1{EZF_ROW_NUMBER}\" size=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A5" ezfName="xxScrItem61Txt_A5" value="20:ACCESSORY" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A5{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A6" ezfName="xxScrItem61Txt_A6" value="01:Accessory" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A6{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A0" ezfName="xxScrItem61Txt_A0" value="ESS1:ESS1" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A0{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A1" ezfName="xxScrItem61Txt_A1" value="ESS2:ESS2" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A1{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A2" ezfName="xxScrItem61Txt_A2" value="ESS3:ESS3" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A2{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A3" ezfName="xxScrItem61Txt_A3" value="ESS4:ESS4" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A3{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<td><ezf:inputText name="xxScrItem61Txt_A4" ezfName="xxScrItem61Txt_A4" value="ESS5:ESS5" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxScrItem61Txt_A4{EZF_ROW_NUMBER}\" size=\"16\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
															<% if ("Y".equals(bMsg.A.no(indexA).mainMachFlg_A1.getValue())) { %>
															<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_MachMstrMaint" otherAttr=" ezfanchortext=\"\" id=\"mainMachFlg_A1{EZF_ROW_NUMBER}\""><ezf:label name="mainMachFlg_A1" ezfName="mainMachFlg_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
															<% } else { %>
															<td><ezf:label name="mainMachFlg_A1" ezfName="mainMachFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<% } %>
															<% if ("Y".equals(bMsg.A.no(indexA).serNumFlg_A1.getValue())) { %>
															<td><ezf:anchor name="" ezfName="" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Open_SerTrxInq" otherAttr=" ezfanchortext=\"\" id=\"serNumFlg_A1{EZF_ROW_NUMBER}\""><ezf:label name="serNumFlg_A1" ezfName="serNumFlg_A1" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor></td>
															<% } else { %>
															<td><ezf:label name="serNumFlg_A1" ezfName="serNumFlg_A1" ezfHyo="A" ezfArrayNo="0" /></td>
															<% } %>
														</tr>
														<% indexA++; %>
														</ezf:row>
														<ezf:skip>
														</ezf:skip>
													</table>
												</div><!-- rightTbl -->
											</div> <!-- right table -->
										</td>
									</tr>
								</table>
								</div><!-- parentBoxA -->
							</div>
						</td>
					</tr>
				</table>
			</center>
<script type="text/javascript" defer>
	S21TableUI.initialize("parentBoxA", "AHEAD", "A", 1, true);
</script>
<ezf:inputHidden name="xxListNum_LX" ezfName="xxListNum_LX" value="" otherAttr=" id=\"xxListNum_LX\" "/>
<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="" otherAttr=" id=\"xxListNum_LY\" "/>
<%-- ###SCRIPT --%>
<script type="text/javascript">

	setKeyDownHandler();

	// setScroll(); 
	setTimeout(setScroll,10);

	function synchroScroll_fromRightTblAction() {
		var rightTblHead = this.document.getElementById( 'rightTblHead' );
		var rightTbl     = this.document.getElementById( 'rightTbl' );
		var leftTbl      = this.document.getElementById( 'leftTbl' );

		// synchronize scroll - Y
		leftTbl.scrollTop = rightTbl.scrollTop;
		// synchronize scroll - X
		rightTblHead.scrollLeft = rightTbl.scrollLeft;
	}

	function synchroScroll_fromLeftTblAction() {
		var leftTbl  = this.document.getElementById( 'leftTbl'  );
		var rightTbl = this.document.getElementById( 'rightTbl' );

		// synchronize scroll - Y
		rightTbl.scrollTop = leftTbl.scrollTop;
	}

	<%@ page import="com.fujitsu.uji.util.Parameters" %>
	<%@ page import="com.fujitsu.uji.http.HttpDispatchContext" %>
	function setScroll() {
		<%
		Parameters params = (Parameters) pageContext.getAttribute(HttpDispatchContext.PARAMETERS_KEY, PageContext.REQUEST_SCOPE);
		String ezdEvent = params.getSingleValue("ezd.event");
		%>
		var xxListNum_LX = this.document.getElementById( 'xxListNum_LX' );
		var xxListNum_LY = this.document.getElementById( 'xxListNum_LY' );
		var leftTbl = this.document.getElementById( 'leftTbl' );
		var rightTblHead = this.document.getElementById( 'rightTblHead' );
		var rightTbl = this.document.getElementById( 'rightTbl' );

		<% if (ezdEvent!=null && ezdEvent.startsWith("TAB_")) { %>
			xxListNum_LX.value = 0;
			xxListNum_LY.value = 0;
		<% } %>
	    var LX = xxListNum_LX.value;
	    var LY = xxListNum_LY.value
		if (xxListNum_LY.value > 0) {
			leftTbl.scrollTop = LY;
			rightTbl.scrollTop = LY;
		}

		if (xxListNum_LX.value > 0) {
			rightTblHead.scrollLeft = LX;
			rightTbl.scrollLeft = LX;
		 }
	}

	function setScrollPosition() {
		var rightTbl    = this.document.getElementById( 'rightTbl' );
		this.document.getElementById( 'xxListNum_LX' ).value = rightTbl.scrollLeft;
		this.document.getElementById( 'xxListNum_LY' ).value = rightTbl.scrollTop;
	}
</script>

<%-- **** Task specific area ends here **** --%>
