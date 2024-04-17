<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240322111048 --%>
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
			<input type="hidden" name="pageID" value="NSAL0300Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Contract Maintenance">
<%@ page import="business.servlet.NSAL0300.NSAL0300BMsg" %>
<%@ page import="business.servlet.NSAL0300.common.NSAL0300CommonLogic" %>
<%@ page import="business.servlet.NSAL0300.constant.NSAL0300Constant" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPConstant" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP" %>
<!-- <%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC" %> -->
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.fujitsu.uji.util.Parameters" %>
<%@ page import="com.fujitsu.uji.http.HttpDispatchContext" %>
<% NSAL0300BMsg bMsg = (NSAL0300BMsg) databean.getEZDBMsg(); %>
<% boolean isSummaryMode = false; %>
<% if (NSAL0300Constant.SUMMARY_MODE.equals(bMsg.xxModeCd_SD.getValue())) { %>
<%     isSummaryMode = true; %>
<% } %>
<center style="position:absolute; top:0; left:0;">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Contract Maintenance" class="pTab_ON"><a href="#">Cont Maint</a></li>
									</div>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" value="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id="NextPageBtn" src="./img/tab/NextPageBtn.gif" value="Next" border="0"></a>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<div id="top" style="border-style:solid; border-width:0; height:550; overflow-y:scroll; width:1133; overflowx:none; padding-left:10;" onScroll="setScrollPosition()">
						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="1">
							<tr style="padding-bottom:.5em;">
								<td>
									<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
										<col width="75"><!-- Contract # Label -->
										<col width="37"><!-- Category Abbreviation -->
										<col width="57"><!-- Contract No -->
										<col width="25"><!-- Search -->
										<col width="50"><!-- Status Label -->
										<col width="287"><!-- Status -->
										<col width="700"><!-- button -->
										<tr height="20">
											<td class="stab">Contract #</td>
											<td><ezf:inputText name="dsContrCatgAbbrNm" ezfName="dsContrCatgAbbrNm" value="AGG" otherAttr=" size=\"4\" ezftoupper=\"\""/></td>
											<td><ezf:inputText name="dsContrNum" ezfName="dsContrNum" value="0001068" otherEvent1="Search" otherAttr=" size=\"7\" ezffocusout=\"Search\""/></td>
											<td><ezf:inputButton name="OpenWin_ContractNum" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
											<td class="stab">Status</td>
											<td><ezf:inputText name="dsContrStsDescTxt" ezfName="dsContrStsDescTxt" value="TEST DESCRIPTION" otherAttr=" size=\"32\""/></td>
											<td>
												<ezf:inputButton name="OpenWin_Terminate" value="Terminate" htmlClass="pBtn6" />
												<ezf:inputButton name="OpenWin_Renew" value="Renew" htmlClass="pBtn5" />
												<ezf:inputButton name="OpenWin_StatusChange" value="Status Change" htmlClass="pBtn8" />
												<ezf:inputButton name="OpenForUpdate" value="Open For Update" htmlClass="pBtn8" />
												<ezf:inputButton name="Revert" value="Revert" htmlClass="pBtn5" />
												<ezf:inputButton name="OpenWin_ContractStatusHistory" value="Status Tracker" htmlClass="pBtn8" />
												<ezf:inputButton name="OpenWin_AddNotes" value="Notes" htmlClass="pBtn5" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="padding-bottom:.5em;">
								<td>
									<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
										<col width="75"><!-- Contract Type Label -->
										<col width="119"><!-- Contract Type -->
										<col width="50"><!-- Category Label -->
										<col width="110"><!-- Category -->
										<col width="45"><!-- Duration Label -->
										<col width="93"><!-- Duration -->
										<col width="40"><!-- Branch Label -->
										<col width="148"><!-- Branch -->
										<col width="22"><!-- Search -->
										<col width="63"><!-- Branch Rep Label -->
										<col width="120"><!-- Branch Rep -->
										<col width="22"><!-- Search -->
										<col width="55"><!-- Sales Rep Label -->
										<col width="120"><!-- Sales Rep -->
										<col width="25"><!-- Search -->
										<tr height="20">
											<td class="stab">Contract Type</td>
											<td>
												<ezf:select name="dsContrCatgCd" ezfName="dsContrCatgCd" ezfBlank="1" ezfCodeName="dsContrCatgCd_C" ezfDispName="dsContrCatgDescTxt_C" otherEvent1=" onchange=\"sendServer('ChangeDsContrCatgCd')\"" otherAttr=" style=\"width:90;\""/>
											</td>
											<td class="stab">Category</td>
											<td>
												<ezf:select name="dsContrClsCd" ezfName="dsContrClsCd" ezfBlank="1" ezfCodeName="dsContrClsCd_C" ezfDispName="dsContrClsDescTxt_C" otherAttr=" style=\"width:90;\""/>
											</td>
											<td class="stab">Duration</td>
											<td>
												<ezf:inputText name="contrDurnAot" ezfName="contrDurnAot" value="2" otherEvent1="ChangeDurationPeriod" otherAttr=" size=\"10\" maxlength=\"5\" ezffocusout=\"ChangeDurationPeriod\""/>
												<ezf:inputHidden name="contrVrsnEffFromDt_BK" ezfName="contrVrsnEffFromDt_BK" />
												<ezf:inputHidden name="contrDurnAot_BK" ezfName="contrDurnAot_BK" />
											</td>
											<td class="stab">Branch</td>
											<td><ezf:inputText name="xxDplyByCdNmCnctTxt" ezfName="xxDplyByCdNmCnctTxt" value="ESS-401 - MINNEAPOLIS" otherEvent1="ChangeSvcContrBrCd" otherAttr=" size=\"20\" ezffocusout=\"ChangeSvcContrBrCd\""/></td>
											<td><ezf:inputButton name="OpenWin_Branch" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
											<td class="stab">Branch Rep</td>
											<td><ezf:inputText name="xxPsnNm" ezfName="xxPsnNm" value="REP USER" otherEvent1="ChangeContrAdminPsnCd" otherAttr=" size=\"16\" ezfnoupperfocusout=\"ChangeContrAdminPsnCd\""/></td>
											<td><ezf:inputButton name="OpenWin_Rep" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
											<td class="stab">Sales Rep</td>
											<td><ezf:inputText name="tocNm" ezfName="tocNm" value="SALES REP USER" otherEvent1="ChangeTocCd" otherAttr=" size=\"16\" ezfnoupperfocusout=\"ChangeTocCd\""/></td>
											<td><ezf:inputButton name="OpenWin_SalesRep" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="padding-bottom:.5em;">
								<td>
									<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
										<col width="75"><!-- Start Date Label -->
										<col width="119"><!-- Start Date -->
										<col width="50"><!-- End Date Label -->
										<col width="110"><!-- End Date -->
										<col width="45"><!-- Period Label -->
										<col width="92"><!-- Period -->
										<col width="68"><!-- Source Ref# Label -->
										<col width="182"><!-- Source Ref# -->
										<col width="24"><!-- EDI Label -->
										<col width="67"><!-- EDI -->
										<col width="70"><!-- Report Group Label -->
										<col width="138"><!-- Report Group -->
										<col width="23"><!-- Search -->
										<col width="35"><!-- New -->
										<tr height="20">
											<td class="stab">Start Date</td>
											<td>
												<ezf:inputText name="contrVrsnEffFromDt" ezfName="contrVrsnEffFromDt" value="01/01/2016" otherEvent1="ChangeDurationDate" otherAttr=" size=\"10\" maxlength=\"10\" ezffocusout=\"ChangeDurationDate\""/>
												<img src="./img/calendar.gif" class="c" onclick="calendar('contrVrsnEffFromDt', 4);">
											</td>
											<td class="stab">End Date</td>
											<td>
												<ezf:inputText name="contrVrsnEffThruDt" ezfName="contrVrsnEffThruDt" value="12/31/2017" otherEvent1="ChangeDurationDate" otherAttr=" size=\"10\" maxlength=\"10\" ezffocusout=\"ChangeDurationDate\""/>
												<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrVrsnEffThruDt', 4);">
											</td>
											<td class="stab">Period</td>
											<td>
												<ezf:select name="bllgCycleUomCd" ezfName="bllgCycleUomCd" ezfBlank="1" ezfCodeName="bllgCycleUomCd_CB" ezfDispName="bllgCycleUomDescTxt_CB" otherEvent1=" onchange=\"sendServer('ChangeDurationPeriod')\"" otherAttr=" style=\"width:74;\""/>
												<ezf:inputHidden name="contrVrsnEffThruDt_BK" ezfName="contrVrsnEffThruDt_BK" />
												<ezf:inputHidden name="bllgCycleUomCd_BK" ezfName="bllgCycleUomCd_BK" />
											</td>
											<td class="stab">Source Ref#</td>
											<td><ezf:inputText name="svcContrRefCmntTxt" ezfName="svcContrRefCmntTxt" value="SOURCE REFERENNCE NUMBER" otherAttr=" size=\"18\" maxlength=\"90\" ezftoupper=\"\""/></td>
											<td class="stab">EDI</td>
											<td>
												<ezf:select name="dsContrEdiCd" ezfName="dsContrEdiCd" ezfBlank="1" ezfCodeName="dsContrEdiCd_E" ezfDispName="dsContrEdiDescTxt_E" otherAttr=" style=\"width:60;\""/>
											</td>
											<td class="stab">Report Group</td>
											<td><ezf:inputText name="dsContrRptGrpDescTxt" ezfName="dsContrRptGrpDescTxt" value="1234567890" otherEvent1="ChangeReportGrp" otherAttr=" size=\"18\" ezffocusout=\"ChangeReportGrp\""/></td>
											<td><ezf:inputButton name="OpenWin_ReportGrp" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
											<td><ezf:inputButton name="OpenWin_NewReportGrp" value="New" htmlClass="pBtn1" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="padding-bottom:.5em;">
								<td>
									<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
										<col width="75"><!-- Description Label -->
										<col width="279"><!-- Description -->
										<col width="45"><!-- Contract Link # Label -->
										<col width="93"><!-- Contract Link # -->
										<col width="78"><!-- Inv. Comments Label -->
										<col width="530"><!-- Inv. Comments -->
										<tr height="20">
											<td class="stab">Description</td>
											<td><ezf:inputText name="dsContrNm" ezfName="dsContrNm" value="TEST CONTRACT DESCRIPTION" otherAttr=" size=\"56\" maxlength=\"60\" style=\"width:270;\""/></td>
											<td class="stab">Link #</td>
											<td><ezf:inputText name="contrLinkNum" ezfName="contrLinkNum" value="000000" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
											<td class="stab">Inv. Comments</td>
											<td><ezf:inputText name="contrInvCmntTxt" ezfName="contrInvCmntTxt" value="TEST INVOICE COMMENT" otherAttr=" size=\"74\" maxlength=\"250\""/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr height="2">
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
										<col width="500" valign="top">
										<col width="603" valign="top">
										<tr>
<%
	String xxFilePathTxt_EC = bMsg.xxFilePathTxt_EC.getValue();
	String endCustomerStyle = NSAL0300Constant.DIV_STYLE_NO_DISPLAY;
	if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_EC)) {
		endCustomerStyle = NSAL0300Constant.DIV_STYLE_DISPLAY;
	}
%>
											<!-- End Customer -->
											<td>
												<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="1">
																<col width="24">
																<col width="90">
																<col width="384">
																<tr height="22" valign="center">
																	<td>
																		<ezf:anchor name="xxLinkProt_EC" ezfName="xxLinkProt_EC" ezfEmulateBtn="1" ezfGuard="ExpandEndCustomer" >
																			<img src="<%=xxFilePathTxt_EC%>" border="0" value="Expand End Customer">
																		</ezf:anchor>
																	</td>
																	<td class="stab"><b>End Customer</b></td>
																	<td class="stab">
																		<div style="position:relative;">
																			<img src="./img/calendarHead.png" width="376" height="1" value="line">
																			<span style="position:absolute; top:0px; left:38px;height:14px;text-align:center;background-color:#E0E0E0;">
<%
	if (ZYPCommonFunc.hasValue(bMsg.dsAcctNm_EC.getValue())) {
%>
																				<ezf:label name="dsAcctNm_EC" ezfName="dsAcctNm_EC" />
<%
	}
%>
																			</span>
																		</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<div name="EndCustomer" style="<%=endCustomerStyle%>">
																<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0" width="500">
																	<tr>
																		<td>
																			<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="2">
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- Customer # Label -->
																							<col width="56"><!-- Customer # -->
																							<col width="16"><!-- Search -->
																							<col width="345"><!-- Customer Name -->
																							<tr height="20">
																								<td class="stab">Customer #</td>
																								<td><ezf:inputText name="dsAcctNum" ezfName="dsAcctNum" value="0000043" otherEvent1="ChangeDsAcctNum" otherAttr=" size=\"7\" ezffocusout=\"ChangeDsAcctNum\""/></td>
																								<td><ezf:inputButton name="OpenWin_Customer" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
																								<td><ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="BPR COPY SHOP INC." otherAttr=" size=\"45\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- Bill To Location Label -->
																							<col width="56"><!-- Search -->
																							<col width="16"><!-- Bill To Location -->
																							<col width="290"><!-- Bill To Location Name -->
																							<col width="32"><!-- Special Instruction -->
																							<tr height="20">
																								<td class="stab">Bill To Code</td>
																								<td><ezf:inputText name="altPayerCustCd" ezfName="altPayerCustCd" value="0000028" otherEvent1="ChangeBillToLoc" otherAttr=" size=\"7\" ezffocusout=\"ChangeBillToLoc\""/></td>
																								<td><ezf:inputButton name="OpenWin_BillToLoc" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
																								<td><ezf:inputText name="billToLocNm" ezfName="billToLocNm" value="BPR COPY SHOP INC." otherAttr=" size=\"40\""/></td>
																								<td><ezf:inputButton name="OpenWin_SpecialInstruction" value="SI" htmlClass="pBtn0" otherAttr=" title=\"Special Instruction\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- Contact Label -->
																							<col width="10"><!-- First Name Link -->
																							<col width="190"><!-- First Name -->
																							<col width="10"><!-- Last Name Label -->
																							<col width="190"><!-- Last Name -->
																							<tr height="20">
																								<td class="stab">Contact</td>
																								<td class="stab"><ezf:anchor name="ctacPsnFirstNm_PL" ezfName="ctacPsnFirstNm_PL" ezfEmulateBtn="1" ezfGuard="OpenWin_BillToContact" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																								<td><ezf:inputText name="ctacPsnFirstNm_CP" ezfName="ctacPsnFirstNm_CP" otherAttr=" size=\"25\" maxlength=\"30\""/></td>
																								<td class="stab">L</td>
																								<td><ezf:inputText name="ctacPsnLastNm_CP" ezfName="ctacPsnLastNm_CP" otherAttr=" size=\"25\" maxlength=\"30\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- Lease Label -->
																							<col width="56"><!-- Lease -->
																							<col width="16"><!-- Search -->
																							<col width="200"><!-- Lease Name -->
																							<col width="55"><!-- Base -->
																							<col width="70"><!-- Overage -->
																							<col>
																							<tr height="20">
																								<td class="stab">Lease</td>
																								<td><ezf:inputText name="leaseCmpyCd" ezfName="leaseCmpyCd" value="1002805" otherEvent1="ChangeLeaseCmpyCd" otherAttr=" size=\"7\" ezffocusout=\"ChangeLeaseCmpyCd\""/></td>
																								<td><ezf:inputButton name="OpenWin_LeaseCompany" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
																								<td><ezf:inputText name="dsAcctNm_L" ezfName="dsAcctNm_L" value="CANON FINANCIAL SERVICES" otherAttr=" size=\"27\""/></td>
																								<td class="stab"><ezf:inputCheckBox name="baseChrgToLeaseCmpyFlg" ezfName="baseChrgToLeaseCmpyFlg" value="Y" otherEvent1=" onchange=\"sendServer('ChangBaseChrgToLeaseCmpyFlg')\"" />Base</td>
																								<td class="stab"><ezf:inputCheckBox name="usgChrgToLeaseCmpyFlg" ezfName="usgChrgToLeaseCmpyFlg" value="Y" otherEvent1=" onchange=\"sendServer('ChangeUsgChrgToLeaseCmpyFlg')\"" />Overage</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80">
																							<col width="400">
																							<tr height="20">
																								<td class="stab">Lease #</td>
																								<td><ezf:inputText name="cfsLeaseNumCmntTxt" ezfName="cfsLeaseNumCmntTxt" value="LEASE NUMBER" otherAttr=" size=\"55\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																	<!-- Payment Details -->
																	<tr>
																		<td>
																			<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="2">
																				<col width="95">
																				<col width="395">
																				<tr height="22" valign="center">
																					<td class="stab"><b>Payment Details</b></td>
																					<td><img src="./img/calendarHead.png" width="400" height="1" value="line"></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="2">
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- PO Number Label -->
																							<col width="106"><!-- PO Number -->
																							<col width="40"><!-- PO button -->
																							<col width="44"><!-- PO Date Label -->
																							<col width="105"><!-- PO Date (From) -->
																							<col width="10"><!-- Hyphen Label -->
																							<col width="105"><!-- PO Date (Thru) -->
																							<tr height="20">
																								<td class="stab">PO Number</td>
																								<td><ezf:inputText name="custPoNum" ezfName="custPoNum" value="CUTOMER PO#12345" otherAttr=" size=\"14\" ezftoupper=\"\""/></td>
																								<td><ezf:inputButton name="OpenWin_CreditCardPo" value="PO" otherAttr=" style=\"height:17px;width:28px;\""/></td>
																								<td class="stab">PO Date</td>
																								<td>
																									<ezf:inputText name="poFromDt" ezfName="poFromDt" value="10/01/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
																									<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('poFromDt', 4);">
																								</td>
																								<td class="stab">-</td>
																								<td>
																									<ezf:inputText name="poDt" ezfName="poDt" value="10/31/2016" otherAttr=" size=\"10\" maxlength=\"10\""/>
																									<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('poDt', 4);">
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																				<% if (!"CP".equals(bMsg.pmtTermCashDiscCd.getValue())) { %>
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- Credit Card # Label -->
																							<col width="148"><!-- Credit Card # -->
																							<col width="70"><!-- Credit Card Last 4 Digits# -->
																							<col width="33"><!-- Search -->
																							<col width="95"><!-- CC Expiration Date Label -->
																							<col width="30"><!-- CC Expiration Month -->
																							<col width="50"><!-- CC Expiration Year -->
																							<tr height="20">
																								<td class="stab">Credit Card #</td>
																								<td><ezf:inputText name="crCardCustRefNum" ezfName="crCardCustRefNum" value="70711756" otherAttr=" size=\"20\" ezftoupper=\"\""/></td>
																								<td><ezf:inputText name="xxScrItem10Txt" ezfName="xxScrItem10Txt" value="****1234" otherAttr=" size=\"9\""/></td>
																								<td><ezf:inputButton name="OpenWin_CreditCard" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
																								<td class="stab">CC Expiration Date</td>
																								<td><ezf:inputText name="xxMthDt_CC" ezfName="xxMthDt_CC" value="12" otherAttr=" size=\"2\""/></td>
																								<td><ezf:inputText name="xxYrDt_CC" ezfName="xxYrDt_CC" value="2020" otherAttr=" size=\"4\""/></td>
																							</tr>
																						</table>
																				    </td>
																				<% } else { %>
																				    <td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
                                                                                            <col width="75"><!-- Bank Routing # Link -->
                                                                                            <col width="148"><!--  Bank Routing # -->
                                                                                            <col width="85"><!-- Bank Account# Link -->
                                                                                            <col width="148"><!--  Bank Account # -->
                                                                                            <col width="35"><!-- New -->
                                                                                            <tr height="20">
                                                                                                <td class="stab"><ezf:anchor name="xxLinkProt_BR" ezfName="xxLinkProt_BR" ezfEmulateBtn="1" ezfGuard="OpenWin_BankAccountSearch" >Bank Routing #</ezf:anchor></td>
                                                                                                <td><ezf:inputText name="bankRteNum" ezfName="bankRteNum" value="70711756" otherAttr=" size=\"20\""/></td>
                                                                                                <td class="stab"><ezf:anchor name="xxLinkProt_AC" ezfName="xxLinkProt_AC" ezfEmulateBtn="1" ezfGuard="OpenWin_BankAccountSearch" >Bank Account #</ezf:anchor></td>
                                                                                                <td><ezf:inputText name="dsBankAcctNum" ezfName="dsBankAcctNum" value="****1234" otherAttr=" size=\"20\""/></td>
                                                                                                <td><ezf:inputButton name="OpenWin_BankAccountDetail" value="New" htmlClass="pBtn1" /></td>
                                                                                            </tr>
                                                                                        </table>
																					</td>
																				<% } %>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80"><!-- Payment Term Label -->
																							<col width="21"><!-- Payment Term -->
																							<col width="16"><!-- Search -->
																							<col width="300"><!-- Payment Term Name -->
																							<tr height="20">
																								<td class="stab">Payment Term</td>
																								<td><ezf:inputText name="pmtTermCashDiscCd" ezfName="pmtTermCashDiscCd" value="CC" otherEvent1="ChangePmtTermCashDiscCd" otherAttr=" size=\"2\" ezffocusout=\"ChangePmtTermCashDiscCd\""/></td>
																								<td><ezf:inputButton name="OpenWin_PaymentTerm" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
																								<td><ezf:inputText name="pmtTermCashDiscDescTxt" ezfName="pmtTermCashDiscDescTxt" value="CREDIT CARD" otherAttr=" size=\"30\" maxlength=\"50\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
<!--
																				<tr height="46">
																					<td>&nbsp;</td>
																				</tr>
-->
																			</table>
																		</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>
												</table>
											</td>

											<!-- Entitlements -->
											<td>
												<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td>
															<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="1">
																<col width="80">
																<col width="520">
																<tr height="22" valign="center">
																	<td class="stab"><b>Entitlements</b></td>
																	<td class="stab">
																		<div style="position:relative;">
																			<img src="./img/calendarHead.png" width="515" height="1" value="line">
																			<span style="position:absolute; top:0px; left:19px;height:14px;text-align:center;background-color:#E0E0E0;">
<%
	if (ZYPCommonFunc.hasValue(bMsg.mdseDescShortTxt_EC.getValue())) {
%>
																				<ezf:label name="mdseDescShortTxt_EC" ezfName="mdseDescShortTxt_EC" />
<%
	}
%>
																			</span>
																		</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<div name="Entitlements" style="<%=endCustomerStyle%>">
																<table style="table-layout:fixed;border-left: solid 1px #000000;" border="0" cellpadding="0" cellspacing="0" width="603">
																	<tr>
																		<td width="8">
																		</td>
																		<td colspan="2" width="595">
																			<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="2">
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="406">
																							<col width="16">
																							<tr height="20">
																								<td class="stab">Service Program</td>
																								<td><ezf:inputText name="mdseDescShortTxt_SP" ezfName="mdseDescShortTxt_SP" value="RENTAL SUPPLY INCLUSIVE SERVIC" otherEvent1="ChangeServiceProgram" otherAttr=" size=\"57\" maxlength=\"250\" ezffocusout=\"ChangeServiceProgram\""/></td>
																								<td><ezf:inputButton name="OpenWin_ServiceProgram" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="180">
																							<col width="110">
																							<col width="200">
																							<tr height="20">
																								<td class="stab">Base Frequency</td>
																								<td>
																									<ezf:select name="baseBllgCycleCd" ezfName="baseBllgCycleCd" ezfCodeName="baseBllgCycleCd_CB" ezfDispName="bllgCycleDescTxt_CB" otherAttr=" style=\"width:130\""/>
																								</td>
																								<td class="stab">Overage Frequency</td>
																								<td>
																									<ezf:select name="mtrBllgCycleCd" ezfName="mtrBllgCycleCd" ezfBlank="1" ezfCodeName="mtrBllgCycleCd_CU" ezfDispName="bllgCycleDescTxt_CU" otherAttr=" style=\"width:130\""/>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="180">
																							<col width="110">
																							<col width="200">
																							<tr height="20">
																								<td class="stab">Auto Estimation</td>
																								<td>
																									<ezf:select name="mtrEstTpCd" ezfName="mtrEstTpCd" ezfCodeName="mtrEstTpCd_E" ezfDispName="mtrEstTpDescTxt_E" otherAttr=" style=\"width:130\""/>
																								</td>
																								<td class="stab">Invoicing Option</td>
																								<td>
																									<ezf:select name="dsInvTgtrTpCd" ezfName="dsInvTgtrTpCd" ezfCodeName="dsInvTgtrTpCd_E" ezfDispName="dsInvTgtrTpDescTxt_E" otherEvent1=" onchange=\"sendServer('ChangeBillTogether')\"" otherAttr=" style=\"width:200\""/>
																									<ezf:inputCheckBox name="xxSelFlg_UT" ezfName="xxSelFlg_UT" value="Y" otherEvent1=" onchange=\"sendServer('ChangeBillTogether')\"" otherAttr=" style=\"display:none\""/>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="180">
																							<col width="300">
																							<tr height="20">
																								<td class="stab">&nbsp;</td>
																								<td class="stab">&nbsp;</td>
																								<td class="stab">
																									<ezf:inputCheckBox name="prcAllocByMachQtyFlg" ezfName="prcAllocByMachQtyFlg" value="Y" />
																									Allocate Across All Machine
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																	<!-- Renewal & Upliftment -->
																	<tr>
																		<td width="8">
																		</td>
																		<td colspan="2" width="595">
																			<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="2">
																				<col width="65">
																				<col width="180">
																				<col width="50">
																				<col width="65">
																				<col width="186">
																				<col width="35">
																				<tr height="22" valign="center">
																					<td class="stab"><b>Renewal</b></td>
																					<td><img src="./img/calendarHead.png" width="170" height="1" value="line"></td>
																					<td><ezf:inputButton name="OpenWin_UpliftDetail" value="RNW" htmlClass="pBtn0" otherAttr=" title=\"Renewal\""/></td>
																					<td class="stab"><b>Upliftment</b></td>
																					<td><img src="./img/calendarHead.png" width="176" height="1" value="line"></td>
																					<td><ezf:inputButton name="OpenWin_Escalation" value="ESC" htmlClass="pBtn0" otherAttr=" title=\"Escalation\""/></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<!-- Renewal -->
																		<td width="8">
																		</td>
																		<td width="297">
																			<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="2">
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80">
																							<col width="217">
																							<tr height="20">
																								<td class="stab">Renewal Type</td>
																								<td>
																									<ezf:select name="contrAutoRnwTpCd" ezfName="contrAutoRnwTpCd" ezfCodeName="contrAutoRnwTpCd_R" ezfDispName="contrAutoRnwTpDescTxt_R" otherEvent1=" onchange=\"sendServer('ChangeContrAutoRnwTpCd')\"" otherAttr=" style=\"width:200;\""/>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80">
																							<col width="217">
																							<tr height="20">
																								<td class="stab">Method</td>
																								<td>
																									<ezf:select name="rnwPrcMethCd" ezfName="rnwPrcMethCd" ezfBlank="1" ezfCodeName="rnwPrcMethCd_M" ezfDispName="rnwPrcMethDescTxt_M" otherEvent1=" onchange=\"sendServer('ChangeRnwPrcMethCd')\"" otherAttr=" style=\"width:200;\""/>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="3">
																							<col width="42">
																							<col width="78">
																							<col width="3">
																							<col width="42">
																							<tr height="25">
																								<td class="stab" align="right">% Base</td>
																								<td>&nbsp;</td>
																								<td><ezf:inputText name="basePrcUpRatio" ezfName="basePrcUpRatio" value="10" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																								<td class="stab" align="right">% Overage</td>
																								<td>&nbsp;</td>
																								<td><ezf:inputText name="mtrPrcUpRatio" ezfName="mtrPrcUpRatio" value="20" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="3">
																							<col width="42">
																							<col width="78">
																							<col width="3">
																							<col width="42">
																							<tr height="20">
																								<td class="stab" align="right"># of Days Before</td>
																								<td>&nbsp;</td>
																								<td><ezf:inputText name="befEndRnwDaysAot" ezfName="befEndRnwDaysAot" value="10" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
																								<td class="stab" align="right">Times Renewed</td>
																								<td>&nbsp;</td>
																								<td><ezf:inputText name="contrRnwTotCnt" ezfName="contrRnwTotCnt" value="123" otherAttr=" size=\"3\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																		<!-- Annual Escalation -->
																		<td width="298">
																			<table style="table-layout:fixed;border-left: solid 1px #A9A9A9;" border="0" cellpadding="0" cellspacing="2">
																				<tr style="padding-bottom:.5em;">
																					<td width="5">
																					</td>
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80">
																							<col width="217">
																							<tr height="20">
																								<td class="stab">Upliftment Type</td>
																								<td>
																									<ezf:select name="contrUplftTpCd" ezfName="contrUplftTpCd" ezfCodeName="contrUplftTpCd_U" ezfDispName="contrUplftTpDescTxt_U" otherEvent1=" onchange=\"sendServer('ChangeContrUplftTpCd')\"" otherAttr=" style=\"width:200;\""/>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td width="5">
																					</td>
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="80">
																							<col width="217">
																							<tr height="20">
																								<td class="stab">Method</td>
																								<td>
																									<ezf:select name="uplftPrcMethCd" ezfName="uplftPrcMethCd" ezfBlank="1" ezfCodeName="uplftPrcMethCd_M" ezfDispName="uplftPrcMethDescTxt_M" otherEvent1=" onchange=\"sendServer('ChangeUplftPrcMethCd')\"" otherAttr=" style=\"width:200;\""/>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td width="5">
																					</td>
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<col width="90">
																							<col width="3">
																							<col width="42">
																							<col width="78">
																							<col width="3">
																							<col width="42">
																							<tr height="20">
																								<td class="stab" align="right">% Base</td>
																								<td>&nbsp;</td>
																								<td><ezf:inputText name="uplftBasePrcUpRatio" ezfName="uplftBasePrcUpRatio" value="5" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																								<td class="stab" align="right">% Overage</td>
																								<td>&nbsp;</td>
																								<td><ezf:inputText name="uplftMtrPrcUpRatio" ezfName="uplftMtrPrcUpRatio" value="10" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr style="padding-bottom:.5em;">
																					<td width="5">
																					</td>
																					<td>
																						<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																							<tr height="20">
																								<td>&nbsp;</td>
																							</tr>
																						</table>
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
											</td>
										</tr>
									</table>
								</td>
							</tr>
<%
	if (NSAL0300Constant.IMG_CLOSE_ARROW.equals(xxFilePathTxt_EC)) {
%>
							<tr>
								<td>&nbsp;</td>
							</tr>
<%
	}

	String dsContrCatgCd = bMsg.dsContrCatgCd.getValue();
	if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
		String xxFilePathTxt_FA = bMsg.xxFilePathTxt_FA.getValue();
		String fleetAggregateLineStyle = NSAL0300Constant.DIV_STYLE_NO_DISPLAY;
		if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_FA)) {
			fleetAggregateLineStyle = NSAL0300Constant.DIV_STYLE_DISPLAY;
		}
		String displayFA = "Fleet";
		if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
			displayFA = "Aggregate";
		}
%>
							<!-- Fleet/Aggregate Line -->
							<tr>
								<td>
									<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="1">
										<col width="24">
										<col width="65">
										<col width="1024">
										<tr height="25" valign="center">
											<td>
<%
		if (ZYPCommonFunc.hasValue(xxFilePathTxt_FA)) {
%>
												<ezf:anchor name="xxLinkProt_FA" ezfName="xxLinkProt_FA" ezfEmulateBtn="1" ezfGuard="ExpandFleetAggregate" >
													<img src="<%=xxFilePathTxt_FA%>" border="0" value="Expand Fleet/Aggregate">
												</ezf:anchor>
<%
		} else {
%>
												&nbsp;
<%
		}
%>
											</td>
											<td class="stab"><b><%=displayFA%></b></td>
											<td><img src="./img/calendarHead.png" width="1004" height="1" value="line"></td>
										</tr>
									</table>
								</td>
							</tr>

<%
		if (bMsg.B.getValidCount() > 0) {
%>
							<!-- Fleet/Aggregate Expanded -->
							<tr>
<%
			if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_FA)) {
%>
								<td bgcolor="#FFFFBB">
<%
			} else {
%>
								<td>
<%
			}
%>
									<div name="FleetAggregateLine" style="<%=fleetAggregateLineStyle%>">
<%
		} else {
%>
							<tr>
								<td>&nbsp;</td>
							</tr>
<%
		}
		// Loop B Start
		int b = 0;
		boolean dispFlg = true;
		BigDecimal preDsContrBllgMtrPk = NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
		BigDecimal curDsContrBllgMtrPk = NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
		int tierCnt = 0;
%>
<ezf:row ezfHyo="B">
<%
		if (DS_CONTR_DTL_TP.FLEET.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
			if (!dispFlg) {
%>
				<div style="display:none;"><ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/></div>
				<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
				<div style="display:none;"><ezf:inputText name="serNum_B" ezfName="serNum_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"serNum_B#{EZF_ROW_NUMBER}\""/></div>
				<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrEffFromDt_B" ezfName="contrEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrEffThruDt_B" ezfName="contrEffThruDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToLocNm_BB" ezfName="billToLocNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<div style="display:none;"><ezf:inputText name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"basePrcDealAmt_B#{EZF_ROW_NUMBER}\""/></div>
				<ezf:inputHidden name="xxListNum_B" ezfName="xxListNum_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />

				<ezf:inputHidden name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
			} else {
				dispFlg = false;
%>
										<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
														<col width="26">
														<col width="65"><!-- Effective From Date Label -->
														<col width="120"><!-- Effective From Date -->
														<col width="60"><!-- Effective Thru Date Label -->
														<col width="120"><!-- Effective Thru Date -->
														<col width="80"><!-- Renewal Date Label -->
														<col width="110"><!-- Renewal Date -->
														<col width="90"><!-- Times Renewed Label -->
														<col width="50"><!-- Times Renewed -->
														<col width="90"><!-- Date Terminated Label -->
														<col width="251"><!-- Date Terminated -->
														<col width="70"><!-- AC -->
														<tr><td>&nbsp;</td></tr>
														<tr height="25">
															<td>&nbsp;</td>
															<td class="stab">Start Date</td>
															<td>
																<div style="display:none;">
																	<ezf:inputText name="serNum_B" ezfName="serNum_B" value="IR2525-1" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"12\" id=\"serNum_B#{EZF_ROW_NUMBER}\""/>
																	<ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/>
																</div>
																<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
																<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
																<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
																<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
																<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
																<ezf:inputHidden name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"xxTpCd_B#{EZF_ROW_NUMBER}\""/>
																<ezf:inputText name="contrEffFromDt_B" ezfName="contrEffFromDt_B" value="01/01/2016" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeDetailEffectiveFromDateB" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffFromDt_B#{EZF_ROW_NUMBER}\" ezffocusout=\"ChangeDetailEffectiveFromDateB\""/>
																<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrEffFromDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td class="stab">End Date</td>
															<td>
																<ezf:inputText name="contrEffThruDt_B" ezfName="contrEffThruDt_B" value="12/31/2017" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeDetailEffectiveThruDateB" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffThruDt_B#{EZF_ROW_NUMBER}\" ezffocusout=\"ChangeDetailEffectiveThruDateB\""/>
																<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrEffThruDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td class="stab">Renewal Date</td>
															<td><ezf:inputText name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" value="06/25/2016" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"10\" id=\"rnwEffFromDt_B#{EZF_ROW_NUMBER}\""/></td>
															<td class="stab">Times Renewed</td>
															<td><ezf:inputText name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" value="1" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"3\" id=\"contrRnwTotCnt_B#{EZF_ROW_NUMBER}\""/></td>
															<td class="stab">Date Terminated</td>
															<td><ezf:inputText name="contrCloDt_B" ezfName="contrCloDt_B" value="12/31/2016" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"10\" id=\"contrCloDt_B#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputButton name="OpenWin_AdditionalCharge" value="AC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Additional Charges\""/></td>
														</tr>
													</table>
												</td>
											</tr>
<%
				if (DS_CONTR_DTL_TP.FLEET.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
%>
											<!-- Fleet Ship To -->
											<tr>
												<td>
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
														<col width="26">
														<col width="93"><!-- Ship To Label -->
														<col width="65"><!-- Ship To Code -->
														<col width="17"><!-- ... -->
														<col width="380"><!-- Ship To Loc Name -->
														<col width="90"><!-- Number Of Units Label -->
														<col width="50"><!-- Number Of Units -->
														<col width="90"><!-- Term Amount Label -->
														<col width="100"><!-- Term Amount -->
														<tr height="25">
															<td>&nbsp;</td>
															<td class="stab">Ship To Code</td>
															<td><ezf:inputText name="shipToCustCd_B" ezfName="shipToCustCd_B" value="0000028" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeShipToCustCd" otherAttr=" size=\"8\" ezffocusout=\"ChangeShipToCustCd\""/></td>
															<td><ezf:inputButton name="OpenWin_ShipTo" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
															<td><ezf:inputText name="shipToLocNm_B" ezfName="shipToLocNm_B" value="BPR COPY SHOP INC." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"40\""/></td>
															<td class="stab">Number Of Units</td>
															<td><ezf:inputText name="xxListNum_B" ezfName="xxListNum_B" value="10" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"3\""/></td>
															<td class="stab">Term Amount</td>
															<td><ezf:inputText name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" value="11,520" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"12\" id=\"basePrcTermDealAmtRate_B#{EZF_ROW_NUMBER}\""/></td>
														</tr>
													</table>
												</td>
											</tr>
<%
				} else if (DS_CONTR_DTL_TP.AGGREGATE.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
%>
											<!-- Aggregate Line -->
											<tr>
												<td>
													<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
														<col width="581">
														<col width="90"><!-- Number Of Units Label -->
														<col width="50"><!-- Number Of Units -->
														<col width="90"><!-- Term Amount Label -->
														<col width="100"><!-- Term Amount -->
														<tr height="25">
															<td>&nbsp;</td>
															<td class="stab">Number Of Units</td>
															<td><ezf:inputText name="xxListNum_B" ezfName="xxListNum_B" value="10" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"3\""/></td>
															<td class="stab">Term Amount</td>
															<td><ezf:inputText name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" value="11,520" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"12\" id=\"basePrcTermDealAmtRate_B#{EZF_ROW_NUMBER}\""/></td>
														</tr>
													</table>
												</td>
											</tr>
<%
				}
%>
										</table>
										<br/>

										<!-- Base -->
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<col width="25">
											<col width="50"><!-- Base Label -->
											<col width="70"><!-- Bar -->
											<col width="120"><!-- Next Open Schedule Label -->
											<col width="75"><!-- Next Open Schedule -->
											<col width="60"><!-- Bar -->
											<col width="80"><!-- Billing Timing Label -->
											<col width="100"><!-- Billing Timing -->
											<col width="90"><!-- Period End Date Label -->
											<col width="100"><!-- Period End Date -->
											<col width="80"><!-- Invoice Date Label -->
											<col width="100"><!-- Invoice Date -->
											<col width="77"><!-- Bar -->
											<col width="35"><!-- PE Button -->
											<col width="35"><!-- SC Button -->
											<tr height="25">
												<td>&nbsp;</td>
												<td class="stab"><b>Base</b></td>
												<td><img src="./img/calendarHead.png" width="60" height="1" value="line"></td>
												<td class="stab">Next Open Schedule:</td>
												<td class="stab"><ezf:label name="nextBllgDt_BB" ezfName="nextBllgDt_BB" ezfHyo="B" ezfArrayNo="${b}" /></td>
												<td><img src="./img/calendarHead.png" width="55" height="1" value="line"></td>
												<td class="stab">Billing Timing</td>
												<td>
													<ezf:select name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="bllgTmgTpCd_BT" ezfDispName="bllgTmgTpDescTxt_BT" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('ChangeBaseBllgTmgCd', {EZF_ROW_NUMBER});\"" otherAttr=" style=\"width:90\" id=\"baseBllgTmgCd_B#{EZF_ROW_NUMBER}\""/>
												</td>
												<td class="stab">Period End Date</td>
												<td>
													<ezf:select name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="contrCloDay_BC" ezfDispName="xxEdtDescTxt_BC" ezfCodeDispHyo="B" otherEvent1=" onchange=\"syncBaseUsgDay('baseDplyPerEndDay_B', '{EZF_ROW_NUMBER}', 'mtrDplyPerEndDay_B')\"" otherAttr=" style=\"width:90\" id=\"baseDplyPerEndDay_B#{EZF_ROW_NUMBER}\""/>
												</td>
												<td class="stab">Invoice Date</td>
												<td>
													<ezf:select name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="contrBllgDay_BB" ezfDispName="xxEdtDescTxt_BB" ezfCodeDispHyo="B" otherEvent1=" onchange=\"syncBaseUsgDay('contrBllgDay_B', '{EZF_ROW_NUMBER}', 'mtrBllgDay_B')\"" otherAttr=" style=\"width:90\" id=\"contrBllgDay_B#{EZF_ROW_NUMBER}\""/>
												</td>
												<td><img src="./img/calendarHead.png" width="70" height="1" value="line"></td>
												<td><ezf:inputButton name="OpenWin_PricingEffectivity_Base" value="PE" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Pricing Effectivity\""/></td>
												<td><ezf:inputButton name="OpenWin_Schedule_Base" value="SC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Schedules\""/></td>
											</tr>
										</table>
										<br/>
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<col width="45">
											<col width="450">
											<col width="8">
											<col width="650">
											<tr>
												<td>&nbsp;</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="90">
														<col width="280">
														<tr height="25">
															<td class="stab">Base Charge</td>
															<td>
																<ezf:inputText name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" value="480" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValue('basePrcDealAmt_B', '{EZF_ROW_NUMBER}', 'basePrcDealAmt_A')\"" otherAttr=" size=\"12\" id=\"basePrcDealAmt_B#{EZF_ROW_NUMBER}\""/>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Frequency</td>
															<td>
																<ezf:select name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="baseBllgCycleCd_BB" ezfDispName="bllgCycleDescTxt_BB" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('ChangeBaseBllgCycleCdB', {EZF_ROW_NUMBER});\"" otherAttr=" style=\"width:132\" id=\"baseBllgCycleCd_B#{EZF_ROW_NUMBER}\""/>
															</td>
														</tr>
														<tr height="25">
															<td>&nbsp;</td>
														</tr>
														<tr height="25">
															<td>&nbsp;</td>
														</tr>
													</table>
												</td>
												<td style="table-layout:fixed;border-left: solid 1px #A9A9A9;">&nbsp;</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="97">
														<col width="85">
														<col width="25">
														<col width="168">
														<col width="5">
														<col width="200">
														<tr height="25">
															<td class="stab">Service Program</td>
															<td><ezf:inputText name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" value="MASUPINCL" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"sendServer('ChangeServiceProgram', {EZF_ROW_NUMBER});\"" otherAttr=" size=\"10\" maxlength=\"16\" id=\"svcPgmMdseCd_B#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputButton name="OpenWin_ServiceProgram" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
															<td colspan="3"><ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" value="RENTAL SUPPLY INCLUSIVE SERVIC" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"sendServer('ChangeServiceProgram', {EZF_ROW_NUMBER});\"" otherAttr=" size=\"44\" style=\"width:320px;\" maxlength=\"250\" id=\"mdseDescShortTxt_B#{EZF_ROW_NUMBER}\""/></td>
														</tr>
														<tr height="25">
															<td class="stab">Bill To Code</td>
															<td colspan="5">
																<ezf:inputText name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" value="0000028" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeBaseBillToCustCd" otherAttr=" size=\"7\" ezffocusout=\"ChangeBaseBillToCustCd\""/>
																<ezf:inputButton name="OpenWin_BillTo_Base" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																<ezf:inputText name="billToLocNm_BB" ezfName="billToLocNm_BB" value="BPR COPY SHOP INC." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"40\""/>
																<ezf:inputButton name="OpenWin_SpecialInstruction_Base" value="SI" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Special Instruction\""/>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Contact</td>
															<td colspan="5">
																<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																	<col width="10"><!-- First Name Link -->
																	<col width="207"><!-- First Name -->
																	<col width="10"><!-- Last Name Label -->
																	<col width="207"><!-- Last Name -->
																	<tr height="25">
																		<td class="stab"><ezf:anchor name="ctacPsnFirstNm_BL" ezfName="ctacPsnFirstNm_BL" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="OpenWin_Contact_Base" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																		<td><ezf:inputText name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																		<td class="stab">L</td>
																		<td><ezf:inputText name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Bill To Address</td>
															<td colspan="5"><ezf:inputText name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" value="2100 STEPPINGSTONE SQ SE VA TRAIN CTR SOUTHEASTERN, CHESAPEAKE, VA 23320-2517" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"59\" style=\"width:430px;\""/></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
										<br/>

										<!-- Overage -->
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<col width="26">
											<col width="50">
											<col width="70">
											<col width="120">
											<col width="75">
											<col width="240"><!-- Bar -->
											<col width="90"><!-- Period End Date Label -->
											<col width="100"><!-- Period End Date -->
											<col width="80"><!-- Invoice Date Label -->
											<col width="100"><!-- Invoice Date -->
											<col width="42"><!-- Bar -->
											<col width="35"><!-- BC Button -->
											<col width="35"><!-- PE Button -->
											<col width="35"><!-- SC Button -->
											<tr height="22">
												<td>&nbsp;</td>
												<td class="stab"><b>Overage</b></td>
												<td><img src="./img/calendarHead.png" width="60" height="1" value="line"></td>
												<td class="stab">Next Open Schedule:</td>
												<td class="stab"><ezf:label name="nextBllgDt_BU" ezfName="nextBllgDt_BU" ezfHyo="B" ezfArrayNo="${b}" /></td>
												<td><img src="./img/calendarHead.png" width="230" height="1" value="line"></td>
															<td class="stab">Period End Date</td>
															<td>
																<ezf:select name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="mtrCloDay_MC" ezfDispName="xxEdtDescTxt_MC" ezfCodeDispHyo="B" otherAttr=" style=\"width:90\" id=\"mtrDplyPerEndDay_B#{EZF_ROW_NUMBER}\""/>
															</td>
															<td class="stab">Invoice Date</td>
															<td>
																<ezf:select name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="mtrBllgDay_MB" ezfDispName="xxEdtDescTxt_MB" ezfCodeDispHyo="B" otherAttr=" style=\"width:90\" id=\"mtrBllgDay_B#{EZF_ROW_NUMBER}\""/>
															</td>
												<td><img src="./img/calendarHead.png" width="32" height="1" value="line"></td>
												<td><ezf:inputButton name="OpenWin_BillingCounters" value="BC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Billing Counters\""/></td>
												<td><ezf:inputButton name="OpenWin_PricingEffectivity_Meter" value="PE" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Pricing Effectivity\""/></td>
												<td><ezf:inputButton name="OpenWin_Schedule_Usage" value="SC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Schedules\""/></td>
														</tr>
													</table>
										<br/>
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<!-- Overage List -->
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="35">
														<col width="1030">
														<col width="">
														<tr>
															<td>&nbsp;</td>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24"  align="center"><!-- Radio -->
																	<col width="24"  align="center"><!-- Expand -->
																	<col width="255" align="center"><!-- Billing Counter -->
																	<col width="126"  align="center"><!-- Frequency -->
																	<col width="70"  align="center"><!-- Type -->
																	<col width="94"  align="center"><!-- Allowance -->
																	<col width="69"  align="center"><!-- Price -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="95"  align="center"><!-- Free Copy -->
																	<col width="80"  align="center"><!-- Min. Vol -->
																	<col width="80"  align="center"><!-- Min. Amt -->
																	<col width="60"  align="center"><!-- Roll Over % -->
																	<tr height="16" bgcolor="#E0E0E0">
																		<td colspan="3" rowspan="2" class="stab">Billing Counter</td>
																		<td rowspan="2" class="stab">Frequency</td>
																		<td rowspan="2" class="stab">Type</td>
																		<td colspan="4" class="stab">Pricing</td>
																		<td rowspan="2" class="stab">Free Copy</td>
																		<td rowspan="2" class="stab">Min. Vol</td>
																		<td rowspan="2" class="stab">Min. Amt</td>
																		<td rowspan="2" class="stab">Roll<br>Over %</td>
																	</tr>
																	<tr height="16" bgcolor="#E0E0E0">
																		<td class="stab">Allowance</td>
																		<td class="stab">Price</td>
																		<td class="stab">&nbsp;</td>
																		<td class="stab">&nbsp;</td>
																	</tr>
<%
			}
			String xxFilePathTxt_BC = bMsg.B.no(b).xxFilePathTxt_BC.getValue();

			// hidden Line for Excess
			curDsContrBllgMtrPk = bMsg.B.no(b).dsContrBllgMtrPk_B.getValue();
			if (NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, preDsContrBllgMtrPk)) {
%>
				<ezf:inputHidden name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
			} else {
				tierCnt = NSAL0300CommonLogic.getTierCnt(bMsg, bMsg.B.no(b).dsContrBllgMtrPk_B.getValue());
%>
																	<tr>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:inputRadio name="xxRowNum_BX" ezfName="xxRowNum_BX" value="{EZF_ROW_NUMBER}" />
																		</td>
																		<td rowspan="<%=tierCnt%>">
<%
				if (ZYPCommonFunc.hasValue(xxFilePathTxt_BC)) {
%>
																			<ezf:anchor name="xxLinkProt_BC" ezfName="xxLinkProt_BC" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="ExpandBillingCounter" otherAttr=" id=\"xxLinkProt_BC#{EZF_ROW_NUMBER}\"">
																				<img src="<%=xxFilePathTxt_BC%>" border="0" value="Expand Billing Counter">
																			</ezf:anchor>
<%
				} else {
%>
																			&nbsp;
<%
				}
%>
																		</td>
																		<td rowspan="<%=tierCnt%>"><ezf:inputText name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" value="Fax (Billing)" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"38\""/></td>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:select name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="bllgMtrBllgCycleCd_BU" ezfDispName="bllgCycleDescTxt_BU" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('ChangeBllgMtrBllgCycleCd', {EZF_ROW_NUMBER});\"" otherAttr=" style=\"width:122\""/>
																		</td>
																		<td rowspan="<%=tierCnt%>"><ezf:select name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="xsChrgTpCd_BU" ezfDispName="xsChrgTpDescTxt_BU" ezfCodeDispHyo="B" otherAttr=" style=\"width:65;\""/></td>
																		</td>
<%
			}

			String xxFilePathTxt_BD = bMsg.B.no(b).xxFilePathTxt_BD.getValue();
			String xxFilePathTxt_BA = bMsg.B.no(b).xxFilePathTxt_BA.getValue();
%>
																		<!-- Excess -->
																		<td align="center"><ezf:inputText name="xsMtrCopyQty_B" ezfName="xsMtrCopyQty_B" value="12,000" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"11\""/></td>
																		<td align="center"><ezf:inputText name="xsMtrAmtRate_B" ezfName="xsMtrAmtRate_B" value="0.012345" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"8\""/></td>
																		<td align="center">
<%
			if (ZYPCommonFunc.hasValue(xxFilePathTxt_BD)) {
%>
																			<ezf:anchor name="xxLinkProt_BD" ezfName="xxLinkProt_BD" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="Delete_Price" otherAttr=" id=\"xxLinkProt_BD#{EZF_ROW_NUMBER}\"">
																				<img src="<%=xxFilePathTxt_BD%>" border="0" value="Delete Price">
																			</ezf:anchor>
<%
			} else {
%>
																			&nbsp;
<%
			}
%>
																		</td>
																		<td align="center">
<%
			if (ZYPCommonFunc.hasValue(xxFilePathTxt_BA)) {
%>
																			<ezf:anchor name="xxLinkProt_BA" ezfName="xxLinkProt_BA" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="Add_Price" otherAttr=" id=\"xxLinkProt_BA#{EZF_ROW_NUMBER}\"">
																				<img src="<%=xxFilePathTxt_BA%>" border="0" value="Add Price">
																			</ezf:anchor>
<%
			} else {
%>
																			&nbsp;
<%
			}
%>
																		</td>
<%
			// Last Excess - Close
			BigDecimal nxtDsContrBllgMtrPk = NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
			if (b + 1 != bMsg.B.getValidCount()) {
			    nxtDsContrBllgMtrPk = bMsg.B.no(b + 1).dsContrBllgMtrPk_B.getValue();
			}
			if (!NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, preDsContrBllgMtrPk) || curDsContrBllgMtrPk == null) {
%>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:inputText name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeFreeCopyCnt" otherAttr=" size=\"9\" ezffocusout=\"ChangeFreeCopyCnt\" id=\"bllgFreeCopyCnt_B#{EZF_ROW_NUMBER}\""/>
																			<ezf:inputButton name="OpenWin_FreeCopyRollOverHistory" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																		</td>
																		<td rowspan="<%=tierCnt%>"><ezf:inputText name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('bllgMinCnt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"9\" id=\"bllgMinCnt_B#{EZF_ROW_NUMBER}\""/></td>
																		<td rowspan="<%=tierCnt%>"><ezf:inputText name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('bllgMinAmtRate_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"9\" id=\"bllgMinAmtRate_B#{EZF_ROW_NUMBER}\""/></td>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:inputText name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('bllgRollOverRatio_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"3\" id=\"bllgRollOverRatio_B#{EZF_ROW_NUMBER}\""/>
																			<ezf:inputButton name="OpenWin_FreeCopyRollOverHistory" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																		</td>
																	</tr>
<%
			} else {
				// End of Excess
%>
																	</tr>
<%
			}

			// Next Excess
			if (NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, nxtDsContrBllgMtrPk) && curDsContrBllgMtrPk != null) {
%>
																	<tr>
<%
			}

			// Billing Counter Expanded
			if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_BC) && !NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, nxtDsContrBllgMtrPk)) {
%>

																</table>
															</td>
															<td>&nbsp;</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr><td>&nbsp;</td></tr>

											<!-- Billing Counter Expanded -->
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="100">
														<col width="95"><!-- Billing Counter Name Label -->
														<col width="348">
														<col width="97"><!-- Bill To Address Label -->
														<col width="85">
														<col width="25">
														<col width="168">
														<col width="5">
														<col width="200">
														<tr height="25">
															<td>
																&nbsp;
																<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Billing Counter Name</td>
															<td><ezf:inputText name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" value="Fax (Billing)" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"30\" style=\"width:315px;\""/></td>
															<td class="stab">Bill To Code</td>
															<td colspan="5">
																<ezf:inputText name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" value="0000028" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeBllgMtrBillToCustCd" otherAttr=" size=\"7\" ezffocusout=\"ChangeBllgMtrBillToCustCd\""/>
																<ezf:inputButton name="OpenWin_BillTo_Meter" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																<ezf:inputText name="billToLocNm_BM" ezfName="billToLocNm_BM" value="BPR COPY SHOP INC." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"40\""/>
																<ezf:inputButton name="OpenWin_SpecialInstruction_Meter" value="SI" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Special Instruction\""/>
															</td>
														</tr>
													</table>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="100">
														<col width="95"><!-- Billing Counter Name Label -->
														<col width="100">
														<col width="20">
														<col width="95">
														<col width="100">
														<col width="33">
														<col width="97"><!-- Bill To Address Label -->
														<col width="85">
														<col width="25">
														<col width="168">
														<col width="5">
														<col width="200">
														<tr height="25">
															<td>
																&nbsp;
																<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Cum Allowance</td>
															<td><ezf:inputText name="cumCopyCnt_B" ezfName="cumCopyCnt_B" value="10000" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyCnt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"30\" style=\"width:100px;\" id=\"cumCopyCnt_B#{EZF_ROW_NUMBER}\""/></td>
															<td>&nbsp;</td>
															<td class="stab">Cum Copy Freq</td>
															<td>
																<ezf:select name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" ezfBlank="1" ezfCodeName="cumCopyFreqMthAot_BU" ezfDispName="cumCopyFreqMthAot_BM" ezfCodeDispHyo="B" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyFreqMthAot_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75;\" id=\"cumCopyFreqMthAot_B#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Contact</td>
															<td colspan="5">
																<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																	<col width="10"><!-- First Name Link -->
																	<col width="207"><!-- First Name -->
																	<col width="10"><!-- Last Name Label -->
																	<col width="207"><!-- Last Name -->
																	<tr height="25">
																		<td class="stab"><ezf:anchor name="ctacPsnFirstNm_ML" ezfName="ctacPsnFirstNm_ML" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="OpenWin_Contact_Meter" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																		<td><ezf:inputText name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('ctacPsnFirstNm_BM', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"28\" maxlength=\"30\" id=\"ctacPsnFirstNm_BM#{EZF_ROW_NUMBER}\""/></td>
																		<td class="stab">L</td>
																		<td><ezf:inputText name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('ctacPsnLastNm_BM', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"28\" maxlength=\"30\" id=\"ctacPsnLastNm_BM#{EZF_ROW_NUMBER}\""/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr height="25">
															<td>
																&nbsp;
																<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Cum Copy Start</td>
															<td>
																<ezf:inputText name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" value="12/31/2017" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyStartDt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"cumCopyStartDt_B#{EZF_ROW_NUMBER}\""/>
																<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('cumCopyStartDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td>&nbsp;</td>
															<td class="stab">Cum Copy End</td>
															<td>
																<ezf:inputText name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" value="12/31/9999" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyEndDt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"cumCopyEndDt_B#{EZF_ROW_NUMBER}\""/>
																<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('cumCopyEndDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td>&nbsp;</td>
															<td class="stab">Bill To Address</td>
															<td colspan="5"><ezf:inputText name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" value="2100 STEPPINGSTONE SQ SE VA TRAIN CTR SOUTHEASTERN, CHESAPEAKE, VA 23320-2517" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"59\" style=\"width:430px;\""/></td>
														</tr>
													</table>
												</td>
											</tr>
<%
				// Except Last Line in Billing Counter List
				if (b != bMsg.B.getValidCount() - 1 && NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue())) {
%>
											<tr><td>&nbsp;</td></tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="35">
														<col width="1030">
														<col width="">
														<tr>
															<td>&nbsp;</td>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24"  align="center"><!-- Radio -->
																	<col width="24"  align="center"><!-- Expand -->
																	<col width="255" align="center"><!-- Billing Counter -->
																	<col width="126"  align="center"><!-- Frequency -->
																	<col width="70"  align="center"><!-- Type -->
																	<col width="94"  align="center"><!-- Allowance -->
																	<col width="69"  align="center"><!-- Price -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="95"  align="center"><!-- Free Copy -->
																	<col width="80"  align="center"><!-- Min. Vol -->
																	<col width="80"  align="center"><!-- Min. Amt -->
																	<col width="60"  align="center"><!-- Roll Over % -->
<%
				}
			// Billing Counter Collapsed or except last excess
			} else {
%>
				<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToLocNm_BM" ezfName="billToLocNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"ctacPsnFirstNm_BM#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"ctacPsnLastNm_BM#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="cumCopyCnt_B" ezfName="cumCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyCnt_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyFreqMthAot_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyStartDt_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyEndDt_B#{EZF_ROW_NUMBER}\""/>

<%
				// Last Line in B
				if (b == bMsg.B.getValidCount() - 1 || !NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue())) {
%>
																</table>
															</td>
															<td>&nbsp;</td>
														</tr>
													</table>
												</td>
											</tr>
<%
				}
			}

			// Last Line in B
			if (b == bMsg.B.getValidCount() - 1 || !NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue())) {
%>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
<%
			}

			preDsContrBllgMtrPk = curDsContrBllgMtrPk;
		}
		b++;
		// Loop B End
%>
</ezf:row>
<%
	// Fleet/Aggregate Line End
	}
%>

							<!-- Machines -->
							<tr height="27">
								<td valign="top">
									<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
										<col width="75">
										<col width="875">
										<col width="">
										<tr>
											<td class="stab"><b>Machines</b></td>
											<td><img src="./img/calendarHead.png" width="855" height="1" value="line"></td>
											<td><ezf:inputButton name="DownloadMachine" value="Download Machine Details" htmlClass="pBtn14" /></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed; ">
										<col width="38">
										<col width="91">
										<col width="25">
										<col width="32">
										<col width="95">
										<col width="48">
										<col width="18">
										<col width="40">
										<col width="47">
										<col width="98">
										<col width="42">
										<col width="148">
										<col width="50">
										<col width="330">
										<tr height="22">
											<td class="stab">Serial#</td>
											<td><ezf:inputText name="serNum" ezfName="serNum" value="IR2525-5" otherAttr=" size=\"12\""/></td>
											<td><ezf:inputButton name="OpenWin_Serial" value="..." otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
											<td><ezf:inputButton name="Add_Detail" value="Add" htmlClass="pBtn0" /></td>
											<td><ezf:inputButton name="OpenWin_Add_Detail" value="Add Machines" htmlClass="pBtn7" /></td>
											<td class="stab">Template</td>
											<td>
												<ezf:anchor name="xxLinkProt" ezfName="xxLinkProt" ezfEmulateBtn="1" ezfGuard="Download" >
													<img src="./img/Download-16.png" border="0" value="Download" title="Download">
												</ezf:anchor>
											</td>
											<td>
												<ezf:anchor name="xxLinkProt" ezfName="xxLinkProt" ezfEmulateBtn="1" ezfGuard="OpenWin_MachineUpload" >
													<img src="./img/Upload-16.png" border="0" value="Upload" title="Upload">
												</ezf:anchor>
											</td>
											<td class="stab">Filter By</td>
											<td>
												<ezf:select name="xxCondCd_1V" ezfName="xxCondCd_1V" ezfBlank="1" ezfCodeName="xxCondCd_1C" ezfDispName="xxCondCd_1D" otherAttr=" style=\"width:96;\""/>
											</td>
											<td>
												<ezf:select name="xxCondCd_2V" ezfName="xxCondCd_2V" ezfBlank="1" ezfCodeName="xxCondCd_2C" ezfDispName="xxCondCd_2D" otherAttr=" style=\"width:40;\""/>
											</td>
											<td><ezf:inputText name="condSqlTxt" ezfName="condSqlTxt" value="IR2525-0" otherAttr=" size=\"20\""/></td>
											<td><ezf:inputButton name="Filter" value="Go" htmlClass="pBtn0" /></td>
											<!-- Pagination & Navigation--START-->
											<td>
												<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">1</td>
														<td class="stab">to</td>
														<td class="pOut">20</td>
														<td class="stab">of</td>
														<td class="pOut">200</td>
														<td>&nbsp;</td>
														<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
														<td></td>
														<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
													</tr>
													</table>
												</ezf:skip>
												<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_A" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A" />
												</jsp:include>
											</td>
											<!-- Pagination & Navigation--END-->
										</tr>
									</table>
								</td>
							</tr>

							<!-- Machine Table -->
							<tr>
								<td>

<%
	int startB = 0;
	int endB = 0;
	if (bMsg.A.getValidCount() > 0) {
		for (int i = 0; i < bMsg.B.getValidCount(); i++) {
			if (NSAL0300CommonLogic.isEqualNum(bMsg.B.no(i).dsContrDtlPk_B.getValue(), bMsg.A.no(0).dsContrDtlPk_A.getValue())) {
				startB = i;
				break;
			}
		}
		for (int i = bMsg.B.getValidCount() - 1; i >= 0; i--) {
			if (NSAL0300CommonLogic.isEqualNum(bMsg.B.no(i).dsContrDtlPk_B.getValue(), bMsg.A.no(bMsg.A.getValidCount() - 1).dsContrDtlPk_A.getValue())) {
				endB = i;
				break;
			}
		}
	}

	int b = 0;
%>
<ezf:row ezfHyo="B">
<%
	// Loop B Start for hidden item before A
	if (b < startB && !DS_CONTR_DTL_TP.FLEET.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
%>
		<div style="display:none;"><ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
		<div style="display:none;"><ezf:inputText name="serNum_B" ezfName="serNum_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"serNum_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrEffFromDt_B" ezfName="contrEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrEffThruDt_B" ezfName="contrEffThruDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToLocNm_BB" ezfName="billToLocNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<div style="display:none;"><ezf:inputText name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"basePrcDealAmt_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="xxListNum_B" ezfName="xxListNum_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" ezfHyo="B" ezfArrayNo="${b}" />

		<ezf:inputHidden name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsMtrCopyQty_B" ezfName="xsMtrCopyQty_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsMtrAmtRate_B" ezfName="xsMtrAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToLocNm_BM" ezfName="billToLocNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" />

		<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyCnt_B" ezfName="cumCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
	}
	b++;
	// Loop B End
%>
</ezf:row>
<%
	String xxFilePathTxt_M = bMsg.xxFilePathTxt_M.getValue();
	if (!ZYPCommonFunc.hasValue(dsContrCatgCd) || !DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
		// Non Fleet or Aggregate or Warranty
%>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD"">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="center"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="80" align="center"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="80" align="center"><!-- Service Program -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="90" align="center"><!-- Frequency -->
										<col width="96" align="center"><!-- Base Charge -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Meter Package -->
										<tr height="32">
											<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_M" ezfName="xxChkBox_M" value="Y" onClick="sendServer('CheckAll')" /></td>
											<td class="pClothBs">Seq.#</td>
											<td class="pClothBs">
<%
	if (ZYPCommonFunc.hasValue(xxFilePathTxt_M)) {
%>
												<ezf:anchor name="xxLinkProt_M" ezfName="xxLinkProt_M" ezfEmulateBtn="1" ezfGuard="ExpandMachineAll" >
													<img src="<%=xxFilePathTxt_M%>" border="0" value="Expand Machine All" width="15" height="15">
												</ezf:anchor>
<%
	} else {
%>
												&nbsp;
<%
	}
%>
											</td>
											<td class="pClothBs">Machine Master</td>
											<td class="pClothBs">Item Code</td>
											<td class="pClothBs">Serial#</td>
											<td class="pClothBs">Model</td>
											<td class="pClothBs">Status</td>
											<td class="pClothBs">Service Program</td>
											<td class="pClothBs">Start Date</td>
											<td class="pClothBs">End Date</td>
											<td class="pClothBs">Frequency</td>
											<td class="pClothBs">Base Charge</td>
											<td class="pClothBs">Read Method</td>
											<td class="pClothBs">Meter Package</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="right"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="80" align="left"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="80" align="center"><!-- Service Program -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="90" align="center"><!-- Frequency -->
										<col width="96" align="center"><!-- Base Charge -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Meter Package -->
<%
	} else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
		// Fleet
%>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD"">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="center"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="98" align="center"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="108" align="center"><!-- Install Location -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Renewal Date -->
										<col width="59" align="center"><!-- Times Renewed -->
										<col width="80" align="center"><!-- Date Terminated -->
										<tr height="32">
											<td class="pClothBs"><ezf:inputCheckBox name="xxChkBox_M" ezfName="xxChkBox_M" value="Y" onClick="sendServer('CheckAll')" /></td>
											<td class="pClothBs">Seq.#</td>
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">Machine Master</td>
											<td class="pClothBs">Item Code</td>
											<td class="pClothBs">Serial#</td>
											<td class="pClothBs">Model</td>
											<td class="pClothBs">Status</td>
											<td class="pClothBs">Install Location</td>
											<td class="pClothBs">Start Date</td>
											<td class="pClothBs">End Date</td>
											<td class="pClothBs">Read Method</td>
											<td class="pClothBs">Renewal Date</td>
											<td class="pClothBs">Times Renewed</td>
											<td class="pClothBs">Date Terminated</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="right"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="98" align="left"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="108" align="center"><!-- Install Location -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Renewal Date -->
										<col width="59" align="center"><!-- Times Renewed -->
										<col width="80" align="center"><!-- Date Terminated -->
<%
	}
	// Loop A Start
	int a = 0;
%>
<ezf:row ezfHyo="A">
<%
	String dsContrDtlTpCd = bMsg.A.no(a).dsContrDtlTpCd_A.getValue();
	String xxFilePathTxt_A = bMsg.A.no(a).xxFilePathTxt_A.getValue();
	if (!ZYPCommonFunc.hasValue(dsContrCatgCd) || !DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
		// Non Fleet or Aggregate or Warranty
%>
										<tr height="23">
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="sqId_A" ezfName="sqId_A" value="1" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"4\" id=\"sqId_A#{EZF_ROW_NUMBER}\""/></td>
											<td>
<%
	if (ZYPCommonFunc.hasValue(xxFilePathTxt_A)) {
%>
												<ezf:anchor name="xxLinkProt_A" ezfName="xxLinkProt_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfEmulateBtn="1" ezfGuard="ExpandMachine" otherAttr=" id=\"xxLinkProt_A#{EZF_ROW_NUMBER}\"">
													<img src="<%=xxFilePathTxt_A%>" border="0" value="Expand Machine">
												</ezf:anchor>
<%
	} else {
%>
												&nbsp;
<%
	}
%>
											</td>
											<td>
												<ezf:anchor name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfEmulateBtn="1" ezfGuard="OpenWin_MachMstr" otherAttr=" id=\"svcMachMstrPk_AL#{EZF_ROW_NUMBER}\"">
													<ezf:label name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
												</ezf:anchor>
												<ezf:inputHidden name="dsContrDtlPk_A" ezfName="dsContrDtlPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"dsContrDtlPk_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="2834B002AA" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"mdseCd_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="IR2525-1" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"11\" id=\"serNum_A#{EZF_ROW_NUMBER}\""/></td>
											<td>
												<ezf:anchor name="mdlNm_A" ezfName="mdlNm_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdl" otherAttr=" id=\"mdlNm_AL#{EZF_ROW_NUMBER}\"">
													<ezf:label name="mdlNm_A" ezfName="mdlNm_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
												</ezf:anchor>
											</td>
											<td><ezf:inputText name="dsContrDtlStsDescTxt_A" ezfName="dsContrDtlStsDescTxt_A" value="Active" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"dsContrDtlStsDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td>
												<ezf:inputText name="mdseDescShortTxt_AS" ezfName="mdseDescShortTxt_AS" value="STANDARD SUPPORT - SERVICE" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"mdseDescShortTxt_AS#{EZF_ROW_NUMBER}\""/>
												<ezf:inputHidden name="svcPgmMdseCd_A" ezfName="svcPgmMdseCd_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"svcPgmMdseCd_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<td><ezf:inputText name="contrEffFromDt_A" ezfName="contrEffFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="<%=a%>" otherEvent1="ChangeDetailEffectiveFromDateA" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffFromDt_A#{EZF_ROW_NUMBER}\" ezffocusout=\"ChangeDetailEffectiveFromDateA\""/><img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrEffFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><ezf:inputText name="contrEffThruDt_A" ezfName="contrEffThruDt_A" value="12/31/2017" ezfHyo="A" ezfArrayNo="<%=a%>" otherEvent1="ChangeDetailEffectiveThruDateA" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffThruDt_A#{EZF_ROW_NUMBER}\" ezffocusout=\"ChangeDetailEffectiveThruDateA\""/><img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrEffThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td>
												<ezf:select name="baseBllgCycleCd_A" ezfName="baseBllgCycleCd_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfBlank="1" ezfCodeName="baseBllgCycleCd_AB" ezfDispName="bllgCycleDescTxt_AB" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('ChangeBaseBllgCycleCdA', {EZF_ROW_NUMBER});\"" otherAttr=" style=\"width:86;\" id=\"baseBllgCycleCd_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<td><ezf:inputText name="basePrcDealAmt_A" ezfName="basePrcDealAmt_A" value="120" ezfHyo="A" ezfArrayNo="<%=a%>" otherEvent1=" onchange=\"syncValue('basePrcDealAmt_A', '{EZF_ROW_NUMBER}', 'basePrcDealAmt_B')\"" otherAttr=" size=\"12\" id=\"basePrcDealAmt_A#{EZF_ROW_NUMBER}\""/></td>
											<td>
												<ezf:select name="mtrReadMethCd_A" ezfName="mtrReadMethCd_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfBlank="1" ezfCodeName="mtrReadMethCd_AM" ezfDispName="mtrReadMethDescTxt_AM" ezfCodeDispHyo="A" otherAttr=" style=\"width:75;\" id=\"mtrReadMethCd_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<td>
												<ezf:inputText name="prcMtrPkgNm_A" ezfName="prcMtrPkgNm_A" value="ESS: BW/CLR" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"prcMtrPkgNm_A#{EZF_ROW_NUMBER}\""/>
												<ezf:inputHidden name="prcMtrPkgPk_A" ezfName="prcMtrPkgPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"prcMtrPkgPk_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<ezf:inputHidden name="rnwEffFromDt_A" ezfName="rnwEffFromDt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"rnwEffFromDt_A#{EZF_ROW_NUMBER}\""/>
											<ezf:inputHidden name="contrRnwTotCnt_A" ezfName="contrRnwTotCnt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"contrRnwTotCnt_A#{EZF_ROW_NUMBER}\""/>
											<ezf:inputHidden name="contrCloDt_A" ezfName="contrCloDt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"contrCloDt_A#{EZF_ROW_NUMBER}\""/>
										</tr>
<%
	} else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
		// Fleet
%>
										<tr height="23">
											<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="sqId_A" ezfName="sqId_A" value="1" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"4\" id=\"sqId_A#{EZF_ROW_NUMBER}\""/></td>
											<td>&nbsp;</td>
											<td>
												<ezf:anchor name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfEmulateBtn="1" ezfGuard="OpenWin_MachMstr" otherAttr=" id=\"svcMachMstrPk_AL#{EZF_ROW_NUMBER}\"">
													<ezf:label name="svcMachMstrPk_A" ezfName="svcMachMstrPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
												</ezf:anchor>
												<ezf:inputHidden name="dsContrDtlPk_A" ezfName="dsContrDtlPk_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"dsContrDtlPk_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<td><ezf:inputText name="mdseCd_A" ezfName="mdseCd_A" value="2834B002AA" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"mdseCd_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="serNum_A" ezfName="serNum_A" value="IR2525-1" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"11\" id=\"serNum_A#{EZF_ROW_NUMBER}\""/></td>
											<td>
												<ezf:anchor name="mdlNm_A" ezfName="mdlNm_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfEmulateBtn="1" ezfGuard="OpenWin_Mdl" otherAttr=" id=\"mdlNm_AL#{EZF_ROW_NUMBER}\"">
													<ezf:label name="mdlNm_A" ezfName="mdlNm_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" style=\"white-space:nowrap; overflow:hidden;\""/>
												</ezf:anchor>
											</td>
											<td><ezf:inputText name="dsContrDtlStsDescTxt_A" ezfName="dsContrDtlStsDescTxt_A" value="Active" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"dsContrDtlStsDescTxt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="billToCustLocAddr_A" ezfName="billToCustLocAddr_A" value="2100 STEPPINGSTONE SQ SE VA TRAIN CTR SOUTHEASTERN, CHESAPEAKE, VA 23320-2517" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"14\" id=\"billToCustLocAddr_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="contrEffFromDt_A" ezfName="contrEffFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="<%=a%>" otherEvent1="ChangeDetailEffectiveFromDateA" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffFromDt_A#{EZF_ROW_NUMBER}\" ezffocusout=\"ChangeDetailEffectiveFromDateA\""/><img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrEffFromDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td><ezf:inputText name="contrEffThruDt_A" ezfName="contrEffThruDt_A" value="12/31/2017" ezfHyo="A" ezfArrayNo="<%=a%>" otherEvent1="ChangeDetailEffectiveThruDateA" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrEffThruDt_A#{EZF_ROW_NUMBER}\" ezffocusout=\"ChangeDetailEffectiveThruDateA\""/><img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('contrEffThruDt_A', 4, '{EZF_ROW_NUMBER}');"></td>
											<td>
												<ezf:select name="mtrReadMethCd_A" ezfName="mtrReadMethCd_A" ezfHyo="A" ezfArrayNo="<%=a%>" ezfBlank="1" ezfCodeName="mtrReadMethCd_AM" ezfDispName="mtrReadMethDescTxt_AM" ezfCodeDispHyo="A" otherAttr=" style=\"width:75;\" id=\"mtrReadMethCd_A#{EZF_ROW_NUMBER}\""/>
											</td>
											<td><ezf:inputText name="rnwEffFromDt_A" ezfName="rnwEffFromDt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"rnwEffFromDt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="contrRnwTotCnt_A" ezfName="contrRnwTotCnt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"3\" id=\"contrRnwTotCnt_A#{EZF_ROW_NUMBER}\""/></td>
											<td><ezf:inputText name="contrCloDt_A" ezfName="contrCloDt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" size=\"10\" id=\"contrCloDt_A#{EZF_ROW_NUMBER}\""/></td>
											<ezf:inputHidden name="baseBllgCycleCd_A" ezfName="baseBllgCycleCd_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"baseBllgCycleCd_A#{EZF_ROW_NUMBER}\""/>
											<ezf:inputHidden name="basePrcDealAmt_A" ezfName="basePrcDealAmt_A" ezfHyo="A" ezfArrayNo="<%=a%>" otherAttr=" id=\"basePrcDealAmt_A#{EZF_ROW_NUMBER}\""/>
										</tr>
<%
	}
	// Machine Expanded
	if (NSAL0300Constant.IMG_OPEN_MACHINE_RED.equals(xxFilePathTxt_A) || NSAL0300Constant.IMG_OPEN_MACHINE_GREEN.equals(xxFilePathTxt_A)) {
%>
									</table>
								</td>
							</tr>
<%
	}
%>
<%
	// Loop B Start
	b = 0;
	boolean dispFlg = true;
	BigDecimal preDsContrBllgMtrPk = NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
	BigDecimal curDsContrBllgMtrPk = NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
	int tierCnt = 0;
%>
<ezf:row ezfHyo="B">
<%
	// Collapsed/filtered B -> hidden
	if (NSAL0300CommonLogic.isEqualNum(bMsg.A.no(a).dsContrDtlPk_A.getValue(), bMsg.B.no(b).dsContrDtlPk_B.getValue()) && b >= startB && b <= endB && !NSAL0300CommonLogic.isOpenB(bMsg, b)) {
%>
		<div style="display:none;"><ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
		<div style="display:none;"><ezf:inputText name="serNum_B" ezfName="serNum_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"serNum_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrEffFromDt_B" ezfName="contrEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrEffThruDt_B" ezfName="contrEffThruDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToLocNm_BB" ezfName="billToLocNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<div style="display:none;"><ezf:inputText name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"basePrcDealAmt_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="xxListNum_B" ezfName="xxListNum_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" ezfHyo="B" ezfArrayNo="${b}" />

		<ezf:inputHidden name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsMtrCopyQty_B" ezfName="xsMtrCopyQty_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsMtrAmtRate_B" ezfName="xsMtrAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToLocNm_BM" ezfName="billToLocNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" />

		<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyCnt_B" ezfName="cumCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
	}

	// Machine Expanded
	if (NSAL0300Constant.IMG_OPEN_MACHINE_RED.equals(xxFilePathTxt_A) || NSAL0300Constant.IMG_OPEN_MACHINE_GREEN.equals(xxFilePathTxt_A)) {
		if (NSAL0300CommonLogic.isEqualNum(bMsg.A.no(a).dsContrDtlPk_A.getValue(), bMsg.B.no(b).dsContrDtlPk_B.getValue())) {
			if (!dispFlg) {
				// hidden item for Base
%>
				<div style="display:none;"><ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/></div>
				<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
				<div style="display:none;"><ezf:inputText name="serNum_B" ezfName="serNum_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"serNum_B#{EZF_ROW_NUMBER}\""/></div>
				<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
				<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrEffFromDt_B" ezfName="contrEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrEffThruDt_B" ezfName="contrEffThruDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToLocNm_BB" ezfName="billToLocNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<div style="display:none;"><ezf:inputText name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"basePrcDealAmt_B#{EZF_ROW_NUMBER}\""/></div>
				<ezf:inputHidden name="xxListNum_B" ezfName="xxListNum_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" ezfHyo="B" ezfArrayNo="${b}" />

				<ezf:inputHidden name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
			} else {
				dispFlg = false;
%>
							<!-- Machine Expanded -->
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
										<col width="66">
										<col width="90"><!-- Renewal Date Label -->
										<col width="120"><!-- Renewal Date -->
										<col width="90"><!-- Times Renewed Label -->
										<col width="60"><!-- Times Renewed -->
										<col width="90"><!-- Date Terminated Label -->
										<col width="100"><!-- Date Terminated -->
										<col width="80"><!-- Term Amount Label -->
										<col width="100"><!-- Term Amount -->
										<col width="60"><!-- Print Option Label -->
										<col width="160"><!-- Print Option -->
										<col width="20">
										<col width="70">
										<tr><td>&nbsp;</td></tr>
										<tr height="25">
											<td>&nbsp;</td>
											<td class="stab">Renewal Date</td>
											<td>
												<div style="display:none;">
													<ezf:inputText name="serNum_B" ezfName="serNum_B" value="IR2525-1" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"12\" id=\"serNum_B#{EZF_ROW_NUMBER}\""/>
													<ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/>
												</div>
												<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
												<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
												<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
												<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputHidden name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputHidden name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputHidden name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputHidden name="contrEffFromDt_B" ezfName="contrEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputHidden name="contrEffThruDt_B" ezfName="contrEffThruDt_B" ezfHyo="B" ezfArrayNo="${b}" />
												<ezf:inputText name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" value="01/01/2016" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"10\" maxlength=\"10\" id=\"rnwEffFromDt_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td class="stab">Times Renewed</td>
											<td>
												<ezf:inputText name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" value="10" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"3\" maxlength=\"3\" id=\"contrRnwTotCnt_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td class="stab">Date Terminated</td>
											<td>
												<ezf:inputText name="contrCloDt_B" ezfName="contrCloDt_B" value="12/31/2017" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"10\" maxlength=\"10\" id=\"contrCloDt_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td class="stab">Term Amount</td>
											<td><ezf:inputText name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" value="11,520" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"12\" id=\"basePrcTermDealAmtRate_B#{EZF_ROW_NUMBER}\""/></td>
											<td class="stab">Print Option</td>
											<td>
												<ezf:select name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="svcInvMergeTpCd_BT" ezfDispName="svcInvMergeTpDescTxt_BT" ezfCodeDispHyo="B" otherAttr=" style=\"width:140\" id=\"xxTpCd_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td>&nbsp;</td>
											<td><ezf:inputButton name="OpenWin_AdditionalCharge" value="AC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Additional Charges\""/></td>
										</tr>
									</table>
								</td>
							</tr>

<%
				String xxFilePathTxt_BB = bMsg.B.no(b).xxFilePathTxt_BB.getValue();
				String baseStyle = NSAL0300Constant.DIV_STYLE_NO_DISPLAY;
				if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_BB)) {
					baseStyle = NSAL0300Constant.DIV_STYLE_DISPLAY;
				}
%>
							<!-- Base -->
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
										<col width="66">
										<col width="25">
										<col width="50"><!-- Base Label -->
										<col width="70"><!-- Bar -->
										<col width="120"><!-- Next Open Schedule Label -->
										<col width="75"><!-- Next Open Schedule -->
										<col width="25"><!-- Bar -->
										<col width="80"><!-- Billing Timing Label -->
										<col width="100"><!-- Billing Timing -->
										<col width="90"><!-- Period End Date Label -->
										<col width="100"><!-- Period End Date -->
										<col width="80"><!-- Invoice Date Label -->
										<col width="100"><!-- Invoice Date -->
										<col width="56"><!-- Bar -->
										<col width="35"><!-- PE Button -->
										<col width="35"><!-- SC Button -->
										<tr height="25">
											<td>&nbsp;</td>
											<td>
												<ezf:anchor name="xxLinkProt_BB" ezfName="xxLinkProt_BB" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="ExpandBase" otherAttr=" id=\"xxLinkProt_BB#{EZF_ROW_NUMBER}\"">
													<img src="<%=xxFilePathTxt_BB%>" border="0" value="Expand Base">
												</ezf:anchor>
											</td>
											<td class="stab"><b>Base</b></td>
											<td><img src="./img/calendarHead.png" width="60" height="1" value="line"></td>
											<td class="stab">Next Open Schedule:</td>
											<td class="stab"><ezf:label name="nextBllgDt_BB" ezfName="nextBllgDt_BB" ezfHyo="B" ezfArrayNo="${b}" /></td>
											<td><img src="./img/calendarHead.png" width="20" height="1" value="line"></td>
											<td class="stab">Billing Timing</td>
											<td>
												<ezf:select name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="bllgTmgTpCd_BT" ezfDispName="bllgTmgTpDescTxt_BT" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('ChangeBaseBllgTmgCd', {EZF_ROW_NUMBER});\"" otherAttr=" style=\"width:90\" id=\"baseBllgTmgCd_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td class="stab">Period End Date</td>
											<td>
												<ezf:select name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="contrCloDay_BC" ezfDispName="xxEdtDescTxt_BC" ezfCodeDispHyo="B" otherEvent1=" onchange=\"syncBaseUsgDay('baseDplyPerEndDay_B', '{EZF_ROW_NUMBER}', 'mtrDplyPerEndDay_B')\"" otherAttr=" style=\"width:90\" id=\"baseDplyPerEndDay_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td class="stab">Invoice Date</td>
											<td>
												<ezf:select name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="contrBllgDay_BB" ezfDispName="xxEdtDescTxt_BB" ezfCodeDispHyo="B" otherEvent1=" onchange=\"syncBaseUsgDay('contrBllgDay_B', '{EZF_ROW_NUMBER}', 'mtrBllgDay_B')\"" otherAttr=" style=\"width:90\" id=\"contrBllgDay_B#{EZF_ROW_NUMBER}\""/>
											</td>
											<td><img src="./img/calendarHead.png" width="46" height="1" value="line"></td>
											<td><ezf:inputButton name="OpenWin_PricingEffectivity_Base" value="PE" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Pricing Effectivity\""/></td>
											<td><ezf:inputButton name="OpenWin_Schedule_Base" value="SC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Schedules\""/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<div name="MachineBase" style="<%=baseStyle%>">
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<col width="85">
											<col width="450">
											<col width="8">
											<col width="610">
											<tr>
												<td>&nbsp;</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="90">
														<col width="330">
														<tr height="25">
															<td class="stab">Item</td>
															<td>
																<ezf:inputText name="mdseCd_B" ezfName="mdseCd_B" value="2834B002AA" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"10\" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
																<ezf:inputText name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" value="IMAGERUNNER 2525" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"32\" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Install</td>
															<td>
																<ezf:inputText name="shipToCustCd_B" ezfName="shipToCustCd_B" value="0000028" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"10\" id=\"shipToCustCd_B#{EZF_ROW_NUMBER}\""/>
																<ezf:inputText name="shipToLocNm_B" ezfName="shipToLocNm_B" value="BPR COPY SHOP INC." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"32\" id=\"shipToLocNm_B#{EZF_ROW_NUMBER}\""/>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Address</td>
															<td>
																<ezf:inputText name="shipToCustLocAddr_B" ezfName="shipToCustLocAddr_B" value="2100 STEPPINGSTONE SQ SE VA TRAIN CTR SOUTHEASTERN, CHESAPEAKE, VA 23320-2517" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"43\" style=\"width:312px;\" id=\"shipToCustLocAddr_B#{EZF_ROW_NUMBER}\""/>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Svc Branch</td>
															<td>
																<ezf:inputHidden name="svcBrCd_B" ezfName="svcBrCd_B" ezfHyo="B" ezfArrayNo="${b}" />
																<ezf:inputText name="svcBrCdDescTxt_B" ezfName="svcBrCdDescTxt_B" value="251-ESS BOSTON" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"43\" style=\"width:312px;\" id=\"svcBrCdDescTxt_B#{EZF_ROW_NUMBER}\""/>
															</td>
														</tr>
													</table>
												</td>
												<td style="table-layout:fixed;border-left: solid 1px #A9A9A9;">
													&nbsp;
													<ezf:inputHidden name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" ezfHyo="B" ezfArrayNo="${b}" />
													<ezf:inputHidden name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
												</td>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="97">
														<col width="85">
														<col width="25">
														<col width="168">
														<col width="5">
														<col width="200">
														<tr height="25">
															<td class="stab">Service Program</td>
															<td><ezf:inputText name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" value="MASUPINCL" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"sendServer('ChangeServiceProgram', {EZF_ROW_NUMBER});\"" otherAttr=" size=\"10\" maxlength=\"16\" id=\"svcPgmMdseCd_B#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:inputButton name="OpenWin_ServiceProgram" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/></td>
															<td colspan="3"><ezf:inputText name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" value="RENTAL SUPPLY INCLUSIVE SERVIC" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"sendServer('ChangeServiceProgram', {EZF_ROW_NUMBER});\"" otherAttr=" size=\"44\" style=\"width:320px;\" maxlength=\"250\" id=\"mdseDescShortTxt_B#{EZF_ROW_NUMBER}\""/></td>
														</tr>
														<tr height="25">
															<td class="stab">Bill To Code</td>
															<td colspan="5">
																<ezf:inputText name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" value="0000028" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeBaseBillToCustCd" otherAttr=" size=\"7\" ezffocusout=\"ChangeBaseBillToCustCd\""/>
																<ezf:inputButton name="OpenWin_BillTo_Base" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																<ezf:inputText name="billToLocNm_BB" ezfName="billToLocNm_BB" value="BPR COPY SHOP INC." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"40\""/>
																<ezf:inputButton name="OpenWin_SpecialInstruction_Base" value="SI" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Special Instruction\""/>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Contact</td>
															<td colspan="5">
																<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																	<col width="10"><!-- First Name Link -->
																	<col width="207"><!-- First Name -->
																	<col width="10"><!-- Last Name Label -->
																	<col width="207"><!-- Last Name -->
																	<tr height="25">
																		<td class="stab"><ezf:anchor name="ctacPsnFirstNm_BL" ezfName="ctacPsnFirstNm_BL" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="OpenWin_Contact_Base" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																		<td><ezf:inputText name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																		<td class="stab">L</td>
																		<td><ezf:inputText name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"28\" maxlength=\"30\""/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr height="25">
															<td class="stab">Bill To Address</td>
															<td colspan="5"><ezf:inputText name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" value="2100 STEPPINGSTONE SQ SE VA TRAIN CTR SOUTHEASTERN, CHESAPEAKE, VA 23320-2517" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"59\" style=\"width:430px;\""/></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
<%
				if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
					String xxFilePathTxt_BM = bMsg.B.no(b).xxFilePathTxt_BM.getValue();
					String overageStyle = NSAL0300Constant.DIV_STYLE_NO_DISPLAY;
					if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_BM)) {
						overageStyle = NSAL0300Constant.DIV_STYLE_DISPLAY;
					}
%>

							<!-- Overage -->
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
										<col width="66">
										<col width="25">
										<col width="50">
										<col width="70">
										<col width="120">
										<col width="75">
										<col width="205"><!-- Bar -->
										<col width="90"><!-- Period End Date Label -->
										<col width="100"><!-- Period End Date -->
										<col width="80"><!-- Invoice Date Label -->
										<col width="100"><!-- Invoice Date -->
										<col width="21"><!-- Bar -->
										<col width="35">
										<col width="35">
										<col width="35">
										<tr height="25">
											<td>&nbsp;</td>
											<td>
												<ezf:anchor name="xxLinkProt_BM" ezfName="xxLinkProt_BM" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="ExpandOverage" otherAttr=" id=\"xxLinkProt_BM#{EZF_ROW_NUMBER}\"">
													<img src="<%=xxFilePathTxt_BM%>" border="0" value="Expand Overage">
												</ezf:anchor>
											</td>
											<td class="stab"><b>Overage</b></td>
											<td><img src="./img/calendarHead.png" width=60 height="1" value="line"></td>
											<td class="stab">Next Open Schedule:</td>
											<td class="stab"><ezf:label name="nextBllgDt_BU" ezfName="nextBllgDt_BU" ezfHyo="B" ezfArrayNo="${b}" /></td>
											<td><img src="./img/calendarHead.png" width="195" height="1" value="line"></td>
															<td class="stab">Period End Date</td>
															<td>
																<ezf:select name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="mtrCloDay_MC" ezfDispName="xxEdtDescTxt_MC" ezfCodeDispHyo="B" otherAttr=" style=\"width:90\" id=\"mtrDplyPerEndDay_B#{EZF_ROW_NUMBER}\""/>
															</td>
															<td class="stab">Invoice Date</td>
															<td>
																<ezf:select name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="mtrBllgDay_MB" ezfDispName="xxEdtDescTxt_MB" ezfCodeDispHyo="B" otherAttr=" style=\"width:90\" id=\"mtrBllgDay_B#{EZF_ROW_NUMBER}\""/>
															</td>
											<td><img src="./img/calendarHead.png" width="11" height="1" value="line"></td>
											<td><ezf:inputButton name="OpenWin_BillingCounters" value="BC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Billing Counters\""/></td>
											<td><ezf:inputButton name="OpenWin_PricingEffectivity_Meter" value="PE" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Pricing Effectivity\""/></td>
											<td><ezf:inputButton name="OpenWin_Schedule_Usage" value="SC" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Schedules\""/></td>
														</tr>
													</table>
												</td>
											</tr>
							<tr>
								<td>
									<div name="MachineOverage" style="<%=overageStyle%>">
										<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
											<!-- Overage List -->
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="75">
														<col width="1030">
														<col width="">
														<tr>
															<td>&nbsp;</td>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24"  align="center"><!-- Radio -->
																	<col width="24"  align="center"><!-- Expand -->
																	<col width="255" align="center"><!-- Billing Counter -->
																	<col width="126"  align="center"><!-- Frequency -->
																	<col width="70"  align="center"><!-- Type -->
																	<col width="94"  align="center"><!-- Allowance -->
																	<col width="69"  align="center"><!-- Price -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="95"  align="center"><!-- Free Copy -->
																	<col width="80"  align="center"><!-- Min. Vol -->
																	<col width="80"  align="center"><!-- Min. Amt -->
																	<col width="60"  align="center"><!-- Roll Over % -->
																	<tr height="16" bgcolor="#E0E0E0">
																		<td colspan="3" rowspan="2" class="stab">Billing Counter</td>
																		<td rowspan="2" class="stab">Frequency</td>
																		<td rowspan="2" class="stab">Type</td>
																		<td colspan="4" class="stab">Pricing</td>
																		<td rowspan="2" class="stab">Free Copy</td>
																		<td rowspan="2" class="stab">Min. Vol</td>
																		<td rowspan="2" class="stab">Min. Amt</td>
																		<td rowspan="2" class="stab">Roll<br>Over %</td>
																	</tr>
																	<tr height="16" bgcolor="#E0E0E0">
																		<td class="stab">Allowance</td>
																		<td class="stab">Price</td>
																		<td class="stab">&nbsp;</td>
																		<td class="stab">&nbsp;</td>
																	</tr>
																</table>
															</td>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td>&nbsp;</td>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24"  align="center"><!-- Radio -->
																	<col width="24"  align="center"><!-- Expand -->
																	<col width="255" align="center"><!-- Billing Counter -->
																	<col width="126"  align="center"><!-- Frequency -->
																	<col width="70"  align="center"><!-- Type -->
																	<col width="94"  align="center"><!-- Allowance -->
																	<col width="69"  align="center"><!-- Price -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="95"  align="center"><!-- Free Copy -->
																	<col width="80"  align="center"><!-- Min. Vol -->
																	<col width="80"  align="center"><!-- Min. Amt -->
																	<col width="60"  align="center"><!-- Roll Over % -->
<%
				}
			}

			if (DS_CONTR_DTL_TP.USAGE_ONLY.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue()) || DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
				String xxFilePathTxt_BC = bMsg.B.no(b).xxFilePathTxt_BC.getValue();

				// hidden Line for Excess
				curDsContrBllgMtrPk = bMsg.B.no(b).dsContrBllgMtrPk_B.getValue();
				if (NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, preDsContrBllgMtrPk)) {
%>
					<ezf:inputHidden name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
				} else {
					tierCnt = NSAL0300CommonLogic.getTierCnt(bMsg, bMsg.B.no(b).dsContrBllgMtrPk_B.getValue());
%>
																	<tr>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:inputRadio name="xxRowNum_BX" ezfName="xxRowNum_BX" value="{EZF_ROW_NUMBER}" />
																		</td>
																		<td rowspan="<%=tierCnt%>">
<%
					if (ZYPCommonFunc.hasValue(xxFilePathTxt_BC)) {
%>
																			<ezf:anchor name="xxLinkProt_BC" ezfName="xxLinkProt_BC" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="ExpandBillingCounter" otherAttr=" id=\"xxLinkProt_BC#{EZF_ROW_NUMBER}\"">
																				<img src="<%=xxFilePathTxt_BC%>" border="0" value="Expand Billing Counter">
																			</ezf:anchor>
<%
					} else {
%>
																			&nbsp;
<%
					}
%>
																		</td>
																		<td rowspan="<%=tierCnt%>"><ezf:inputText name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" value="Fax (Billing)" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"38\""/></td>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:select name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="bllgMtrBllgCycleCd_BU" ezfDispName="bllgCycleDescTxt_BU" ezfCodeDispHyo="B" otherEvent1=" onchange=\"sendServer('ChangeBllgMtrBllgCycleCd', {EZF_ROW_NUMBER});\"" otherAttr=" style=\"width:122\""/>
																		</td>
																		<td rowspan="<%=tierCnt%>"><ezf:select name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" ezfCodeName="xsChrgTpCd_BU" ezfDispName="xsChrgTpDescTxt_BU" ezfCodeDispHyo="B" otherAttr=" style=\"width:65;\""/></td>
																		</td>
<%
				}

				String xxFilePathTxt_BD = bMsg.B.no(b).xxFilePathTxt_BD.getValue();
				String xxFilePathTxt_BA = bMsg.B.no(b).xxFilePathTxt_BA.getValue();
%>
																		<!-- Excess -->
																		<td align="center"><ezf:inputText name="xsMtrCopyQty_B" ezfName="xsMtrCopyQty_B" value="12,000" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"11\""/></td>
																		<td align="center"><ezf:inputText name="xsMtrAmtRate_B" ezfName="xsMtrAmtRate_B" value="0.012345" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"8\""/></td>
																		<td align="center">
<%
				if (ZYPCommonFunc.hasValue(xxFilePathTxt_BD)) {
%>
																			<ezf:anchor name="xxLinkProt_BD" ezfName="xxLinkProt_BD" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="Delete_Price" otherAttr=" id=\"xxLinkProt_BD#{EZF_ROW_NUMBER}\"">
																				<img src="<%=xxFilePathTxt_BD%>" border="0" value="Delete Price">
																			</ezf:anchor>
<%
				} else {
%>
																			&nbsp;
<%
				}
%>
																		</td>
																		<td align="center">
<%
				if (ZYPCommonFunc.hasValue(xxFilePathTxt_BA)) {
%>
																			<ezf:anchor name="xxLinkProt_BA" ezfName="xxLinkProt_BA" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="Add_Price" otherAttr=" id=\"xxLinkProt_BA#{EZF_ROW_NUMBER}\"">
																				<img src="<%=xxFilePathTxt_BA%>" border="0" value="Add Price">
																			</ezf:anchor>
<%
				} else {
%>
																			&nbsp;
<%
				}
%>
																		</td>
<%
				// Last Excess - Close
				BigDecimal nxtDsContrBllgMtrPk = NSAL0300Constant.INVLD_DS_CONTR_BLLG_MTR_PK;
				if (b + 1 != bMsg.B.getValidCount()) {
				    nxtDsContrBllgMtrPk = bMsg.B.no(b + 1).dsContrBllgMtrPk_B.getValue();
				}
				if (!NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, preDsContrBllgMtrPk) || curDsContrBllgMtrPk == null) {
%>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:inputText name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeFreeCopyCnt" otherAttr=" size=\"9\" ezffocusout=\"ChangeFreeCopyCnt\" id=\"bllgFreeCopyCnt_B#{EZF_ROW_NUMBER}\""/>
																			<ezf:inputButton name="OpenWin_FreeCopyRollOverHistory" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																		</td>
																		<td rowspan="<%=tierCnt%>"><ezf:inputText name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('bllgMinCnt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"9\" id=\"bllgMinCnt_B#{EZF_ROW_NUMBER}\""/></td>
																		<td rowspan="<%=tierCnt%>"><ezf:inputText name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('bllgMinAmtRate_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"9\" id=\"bllgMinAmtRate_B#{EZF_ROW_NUMBER}\""/></td>
																		<td rowspan="<%=tierCnt%>">
																			<ezf:inputText name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('bllgRollOverRatio_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"3\" id=\"bllgRollOverRatio_B#{EZF_ROW_NUMBER}\""/>
																			<ezf:inputButton name="OpenWin_FreeCopyRollOverHistory" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																		</td>
																		
																	</tr>
<%
				} else {
					// End of Excess
%>
																	</tr>
<%
				}

				// Next Excess
				if (NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, nxtDsContrBllgMtrPk) && curDsContrBllgMtrPk != null) {
%>
																	<tr>
<%
				}

				// Billing Counter Expanded
				if (NSAL0300Constant.IMG_OPEN_ARROW.equals(xxFilePathTxt_BC) && !NSAL0300CommonLogic.isEqualNum(curDsContrBllgMtrPk, nxtDsContrBllgMtrPk)) {
%>
																</table>
															</td>
															<td>&nbsp;</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr><td>&nbsp;</td></tr>

											<!-- Billing Counter Expanded -->
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="100">
														<col width="95"><!-- Billing Counter Name Label -->
														<col width="348">
														<col width="97"><!-- Bill To Address Label -->
														<col width="85">
														<col width="25">
														<col width="168">
														<col width="5">
														<col width="200">
														<tr height="25">
															<td>
																&nbsp;
																<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Counter Name</td>
															<td>
																<ezf:inputText name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" value="Fax-In Counter (Hard),Fax-Out Counter (Hard)" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"30\" style=\"width:315px;\""/>
																<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Bill To Code</td>
															<!-- TODO -->
															<td colspan="5">
																<ezf:inputText name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" value="0000028" ezfHyo="B" ezfArrayNo="${b}" otherEvent1="ChangeBllgMtrBillToCustCd" otherAttr=" size=\"7\" ezffocusout=\"ChangeBllgMtrBillToCustCd\""/>
																<ezf:inputButton name="OpenWin_BillTo_Meter" value="..." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" style=\"height:17px;width:15px;font-family:'Arial',sans-serif;font-size:8pt;\""/>
																<ezf:inputText name="billToLocNm_BM" ezfName="billToLocNm_BM" value="BPR COPY SHOP INC." ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"40\""/>
																<ezf:inputButton name="OpenWin_SpecialInstruction_Meter" value="SI" ezfHyo="B" ezfArrayNo="${b}" htmlClass="pBtn0" otherAttr=" title=\"Special Instruction\""/>
															</td>
														</tr>
													</table>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="100">
														<col width="95"><!-- Billing Counter Name Label -->
														<col width="100">
														<col width="20">
														<col width="95">
														<col width="100">
														<col width="33">
														<col width="97"><!-- Bill To Address Label -->
														<col width="85">
														<col width="25">
														<col width="168">
														<col width="5">
														<col width="200">
														<tr height="25">
															<td>
																&nbsp;
																<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Cum Allowance</td>
															<td><ezf:inputText name="cumCopyCnt_B" ezfName="cumCopyCnt_B" value="10000" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyCnt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"30\" style=\"width:100px;\" id=\"cumCopyCnt_B#{EZF_ROW_NUMBER}\""/></td>
															<td>&nbsp;</td>
															<td class="stab">Cum Copy Freq</td>
															<td>
																<ezf:select name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" ezfBlank="1" ezfCodeName="cumCopyFreqMthAot_BU" ezfDispName="cumCopyFreqMthAot_BM" ezfCodeDispHyo="B" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyFreqMthAot_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:75;\" id=\"cumCopyFreqMthAot_B#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>&nbsp;</td>
															<td class="stab">Contact</td>
															<td colspan="5">
																<table style="table-layout:fixed;" border="0" cellpadding="0" cellspacing="0">
																	<col width="10"><!-- First Name Link -->
																	<col width="207"><!-- First Name -->
																	<col width="10"><!-- Last Name Label -->
																	<col width="207"><!-- Last Name -->
																	<tr height="25">
																		<td class="stab"><ezf:anchor name="ctacPsnFirstNm_ML" ezfName="ctacPsnFirstNm_ML" ezfHyo="B" ezfArrayNo="${b}" ezfEmulateBtn="1" ezfGuard="OpenWin_Contact_Meter" otherAttr=" ezfanchortext=\"\"">F</ezf:anchor></td>
																		<td><ezf:inputText name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('ctacPsnFirstNm_BM', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"28\" maxlength=\"30\" id=\"ctacPsnFirstNm_BM#{EZF_ROW_NUMBER}\""/></td>
																		<td class="stab">L</td>
																		<td><ezf:inputText name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('ctacPsnLastNm_BM', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"28\" maxlength=\"30\" id=\"ctacPsnLastNm_BM#{EZF_ROW_NUMBER}\""/></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr height="25">
															<td>
																&nbsp;
																<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
															</td>
															<td class="stab">Cum Copy Start</td>
															<td>
																<ezf:inputText name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" value="12/31/2017" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyStartDt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"cumCopyStartDt_B#{EZF_ROW_NUMBER}\""/>
																<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('cumCopyStartDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td>&nbsp;</td>
															<td class="stab">Cum Copy End</td>
															<td>
																<ezf:inputText name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" value="12/31/9999" ezfHyo="B" ezfArrayNo="${b}" otherEvent1=" onchange=\"syncValueBillingCounter('cumCopyEndDt_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" size=\"10\" maxlength=\"10\" id=\"cumCopyEndDt_B#{EZF_ROW_NUMBER}\""/>
																<img src="./img/calendar.gif" style="height:16px;width:16px;align:left; vertical-align:top;" onclick="calendar('cumCopyEndDt_B', 4, '{EZF_ROW_NUMBER}');">
															</td>
															<td>&nbsp;</td>
															<td class="stab">Bill To Address</td>
															<td colspan="5"><ezf:inputText name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" value="2100 STEPPINGSTONE SQ SE VA TRAIN CTR SOUTHEASTERN, CHESAPEAKE, VA 23320-2517" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" size=\"59\" style=\"width:430px;\""/></td>
														</tr>
													</table>
												</td>
											</tr>
<%
					// Except Last Line in Billing Counter List
					if (b != bMsg.B.getValidCount() - 1 && NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue())) {
%>
											<tr><td>&nbsp;</td></tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
														<col width="75">
														<col width="1030">
														<col width="">
														<tr>
															<td>&nbsp;</td>
															<td>
																<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
																	<col width="24"  align="center"><!-- Radio -->
																	<col width="24"  align="center"><!-- Expand -->
																	<col width="255" align="center"><!-- Billing Counter -->
																	<col width="126"  align="center"><!-- Frequency -->
																	<col width="70"  align="center"><!-- Type -->
																	<col width="94"  align="center"><!-- Allowance -->
																	<col width="69"  align="center"><!-- Price -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="24"  align="center"><!-- Icon -->
																	<col width="95"  align="center"><!-- Free Copy -->
																	<col width="80"  align="center"><!-- Min. Vol -->
																	<col width="80"  align="center"><!-- Min. Amt -->
																	<col width="60"  align="center"><!-- Roll Over % -->
<%
					}
				// Billing Counter Collapsed or except last excess
				} else {
%>
					<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="billToLocNm_BM" ezfName="billToLocNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"ctacPsnFirstNm_BM#{EZF_ROW_NUMBER}\""/>
					<ezf:inputHidden name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"ctacPsnLastNm_BM#{EZF_ROW_NUMBER}\""/>
					<ezf:inputHidden name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" ezfHyo="B" ezfArrayNo="${b}" />
					<ezf:inputHidden name="cumCopyCnt_B" ezfName="cumCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyCnt_B#{EZF_ROW_NUMBER}\""/>
					<ezf:inputHidden name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyFreqMthAot_B#{EZF_ROW_NUMBER}\""/>
					<ezf:inputHidden name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyStartDt_B#{EZF_ROW_NUMBER}\""/>
					<ezf:inputHidden name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"cumCopyEndDt_B#{EZF_ROW_NUMBER}\""/>

<%
					// Last Line in B
					if (b == bMsg.B.getValidCount() - 1 || !NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue())) {
%>
																</table>
															</td>
															<td>&nbsp;</td>
														</tr>
													</table>
												</td>
											</tr>
<%
					}
				}

				// Last Line in B
				if (b == bMsg.B.getValidCount() - 1 || !NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue())) {
%>
										</table>
									</div>
								</td>
							</tr>
<%
				}
			} else {
				// hidden Overage
%>
				<ezf:inputHidden name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xsMtrCopyQty_B" ezfName="xsMtrCopyQty_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xsMtrAmtRate_B" ezfName="xsMtrAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToLocNm_BM" ezfName="billToLocNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" />

				<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="cumCopyCnt_B" ezfName="cumCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" ezfHyo="B" ezfArrayNo="${b}" />
				<ezf:inputHidden name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
			}

			// Except Last Line in A
			if (a != bMsg.A.getValidCount() - 1 && (b == bMsg.B.getValidCount() - 1 || !NSAL0300CommonLogic.isEqualNum(bMsg.B.no(b).dsContrDtlPk_B.getValue(), bMsg.B.no(b+1).dsContrDtlPk_B.getValue()))) {
%>
							<tr><td>&nbsp;</td></tr>

<%
				if (!ZYPCommonFunc.hasValue(dsContrCatgCd) || !DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
					// Non Fleet or Aggregate or Warranty
%>
							<tr>
								<td>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="AHEAD"">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="center"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="80" align="center"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="80" align="center"><!-- Service Program -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="90" align="center"><!-- Frequency -->
										<col width="96" align="center"><!-- Base Charge -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Meter Package -->
										<tr height="32">
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">Seq.#</td>
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs">Machine Master</td>
											<td class="pClothBs">Item Code</td>
											<td class="pClothBs">Serial#</td>
											<td class="pClothBs">Model</td>
											<td class="pClothBs">Status</td>
											<td class="pClothBs">Service Program</td>
											<td class="pClothBs">Start Date</td>
											<td class="pClothBs">End Date</td>
											<td class="pClothBs">Frequency</td>
											<td class="pClothBs">Base Charge</td>
											<td class="pClothBs">Read Method</td>
											<td class="pClothBs">Meter Package</td>
										</tr>
									</table>
								</td>
							<tr>
								<td>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="right"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="80" align="left"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="80" align="center"><!-- Service Program -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="90" align="center"><!-- Frequency -->
										<col width="96" align="center"><!-- Base Charge -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Meter Package -->
<%
				} else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
					// Fleet
%>
							<tr>
								<td>
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="A">
										<col width="24" align="center"><!-- checkbox -->
										<col width="38" align="center"><!-- Seq.# -->
										<col width="24" align="center"><!-- Expand -->
										<col width="66" align="right"><!-- Machine Master -->
										<col width="80" align="center"><!-- Item Code -->
										<col width="86" align="center"><!-- Serial# -->
										<col width="98" align="left"><!-- Model -->
										<col width="80" align="center"><!-- Status -->
										<col width="108" align="center"><!-- Install Location -->
										<col width="98" align="center"><!-- Start Date -->
										<col width="98" align="center"><!-- End Date -->
										<col width="80" align="center"><!-- Read Method -->
										<col width="80" align="center"><!-- Renewal Date -->
										<col width="59" align="center"><!-- Times Renewed -->
										<col width="80" align="center"><!-- Date Terminated -->
<%
			}
%>
<%
			}

			preDsContrBllgMtrPk = curDsContrBllgMtrPk;
		}
	}
	b++;
	// Loop B End
%>
</ezf:row>
<%
	if (NSAL0300Constant.IMG_OPEN_MACHINE_RED.equals(xxFilePathTxt_A) || NSAL0300Constant.IMG_OPEN_MACHINE_GREEN.equals(xxFilePathTxt_A)) {
		if (a == bMsg.A.getValidCount() - 1) {
%>
							<tr>
								<td>
									<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
<%
		}
	}
	a++;
	// Loop A End
%>
</ezf:row>
									</table>
								</td>
							</tr>
							<tr><td>&nbsp;</td></tr>


							<!-- Machines End Button Area -->
							<tr>
								<td>
<%
	b = 0;
%>
<ezf:row ezfHyo="B">
<%
	// Loop B Start for hidden item after A
	if (b > endB && !DS_CONTR_DTL_TP.FLEET.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(bMsg.B.no(b).dsContrDtlTpCd_B.getValue())) {
%>
		<div style="display:none;"><ezf:inputText name="dsContrBllgMtrPk_B" ezfName="dsContrBllgMtrPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrBllgMtrPk_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="dsContrDtlPk_B" ezfName="dsContrDtlPk_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"dsContrDtlPk_B#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="svcMachMstrPk_B" ezfName="svcMachMstrPk_B" ezfHyo="B" ezfArrayNo="${b}" />
		<div style="display:none;"><ezf:inputText name="serNum_B" ezfName="serNum_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"serNum_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="mdseCd_B" ezfName="mdseCd_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseCd_B#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="mdseDescShortTxt_BI" ezfName="mdseDescShortTxt_BI" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"mdseDescShortTxt_BI#{EZF_ROW_NUMBER}\""/>
		<ezf:inputHidden name="mdlNm_B" ezfName="mdlNm_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrEffFromDt_B" ezfName="contrEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrEffThruDt_B" ezfName="contrEffThruDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBillToCustCd_B" ezfName="baseBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToLocNm_BB" ezfName="billToLocNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnFirstNm_BB" ezfName="ctacPsnFirstNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnLastNm_BB" ezfName="ctacPsnLastNm_BB" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBllgTmgCd_B" ezfName="baseBllgTmgCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseDplyPerEndDay_B" ezfName="baseDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrBllgDay_B" ezfName="contrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
	    <ezf:inputHidden name="xxTpCd_B" ezfName="xxTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="svcPgmMdseCd_B" ezfName="svcPgmMdseCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mdseDescShortTxt_B" ezfName="mdseDescShortTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="baseBllgCycleCd_B" ezfName="baseBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<div style="display:none;"><ezf:inputText name="basePrcDealAmt_B" ezfName="basePrcDealAmt_B" ezfHyo="B" ezfArrayNo="${b}" otherAttr=" id=\"basePrcDealAmt_B#{EZF_ROW_NUMBER}\""/></div>
		<ezf:inputHidden name="xxListNum_B" ezfName="xxListNum_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="basePrcTermDealAmtRate_B" ezfName="basePrcTermDealAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="rnwEffFromDt_B" ezfName="rnwEffFromDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrRnwTotCnt_B" ezfName="contrRnwTotCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrCloDt_B" ezfName="contrCloDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToCustLocAddr_BB" ezfName="billToCustLocAddr_BB" ezfHyo="B" ezfArrayNo="${b}" />

		<ezf:inputHidden name="mtrDplyPerEndDay_B" ezfName="mtrDplyPerEndDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mtrBllgDay_B" ezfName="mtrBllgDay_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="mtrLbDescTxt_B" ezfName="mtrLbDescTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMtrBllgCycleCd_B" ezfName="bllgMtrBllgCycleCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsChrgTpCd_B" ezfName="xsChrgTpCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsMtrCopyQty_B" ezfName="xsMtrCopyQty_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xsMtrAmtRate_B" ezfName="xsMtrAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMtrBillToCustCd_B" ezfName="bllgMtrBillToCustCd_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToLocNm_BM" ezfName="billToLocNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnFirstNm_BM" ezfName="ctacPsnFirstNm_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="ctacPsnLastNm_BM" ezfName="ctacPsnLastNm_BM" ezfHyo="B" ezfArrayNo="${b}" />

		<ezf:inputHidden name="mtrLbDescTxt_BX" ezfName="mtrLbDescTxt_BX" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="contrMtrMultRate_B" ezfName="contrMtrMultRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="billToCustLocAddr_BM" ezfName="billToCustLocAddr_BM" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="xxComnScrColValTxt_B" ezfName="xxComnScrColValTxt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgFreeCopyCnt_B" ezfName="bllgFreeCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMinCnt_B" ezfName="bllgMinCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgMinAmtRate_B" ezfName="bllgMinAmtRate_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="bllgRollOverRatio_B" ezfName="bllgRollOverRatio_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyCnt_B" ezfName="cumCopyCnt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyFreqMthAot_B" ezfName="cumCopyFreqMthAot_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyStartDt_B" ezfName="cumCopyStartDt_B" ezfHyo="B" ezfArrayNo="${b}" />
		<ezf:inputHidden name="cumCopyEndDt_B" ezfName="cumCopyEndDt_B" ezfHyo="B" ezfArrayNo="${b}" />
<%
	}
	b++;
	// Loop B End
%>
</ezf:row>
									<table style="table-layout:fixed;">
										<col width="22">
										<col width="72">
										<col width="72">
										<tr>
											<td align="center">
												<ezf:anchor name="xxLinkProt_DL" ezfName="xxLinkProt_DL" ezfEmulateBtn="1" ezfGuard="Delete_Detail" >
													<img src="./img/trash.png" border="0" value="Delete Row">
												</ezf:anchor>
											<td><ezf:inputButton name="OpenWin_Terms" value="Terms" htmlClass="pBtn6" /></td>
											<td><ezf:inputButton name="OpenWin_SubContract" value="Sub Contract" htmlClass="pBtn6" /></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<table style="table-layout:fixed; margin-top:2; margin-right:20;" border="0" cellpadding="0" cellspacing="0">
						<col width="10">
						<col width="90">
						<col width="250">
						<col width="">
						<col width="500">
						<tr>
							<td>&nbsp;</td>
							<td class="stab">Contract Action</td>
							<td>
								<ezf:select name="dsContrActCd" ezfName="dsContrActCd" ezfBlank="1" ezfCodeName="dsContrActCd_C" ezfDispName="dsContrActDescTxt_C" />
								<ezf:inputButton name="Go" value="Go" htmlClass="pBtn3" /></td>
							</td>
							<td>&nbsp;</td>
							<td align="right">
								<ezf:inputButton name="OpenWin_Attachments" value="Attachment" htmlClass="pBtn9" />
								<ezf:inputButton name="OpenWin_CompleteContract" value="Complete Contract" htmlClass="pBtn9" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
<input type="hidden" name="xxScrItem500Txt" value="">
<input type="hidden" name="xxListNum_LY" value="">
<%--SCRIPT--%>
<script type="text/javascript">
<%
Parameters params = (Parameters) pageContext.getAttribute(HttpDispatchContext.PARAMETERS_KEY, PageContext.REQUEST_SCOPE);
String ezdEvent = params.getSingleValue("ezd.event");
if (ZYPCommonFunc.hasValue(bMsg.xxListNum_LY) && (!ZYPCommonFunc.hasValue(ezdEvent) || ZYPCommonFunc.hasValue(ezdEvent) && !ezdEvent.equals("CMN_Clear") && !ezdEvent.equals("CMN_Reset"))) {
%>
//setTimeout(setScroll,10);
setScroll();
function setScroll() {
    var LY = <%=bMsg.xxListNum_LY.getValue() %>;
    if (LY != null && LY > 0) {
        document.getElementById("top").scrollTop = LY;
        this.document.getElementById( 'xxListNum_LY' ).value = LY;
    }
}
<% } %>
function setScrollPosition() {
    var targetElm    = this.document.getElementById( 'top' );
    this.document.getElementById( 'xxListNum_LY' ).value = targetElm.scrollTop;
}
function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}
function findAIdByBId(idB) {
	var dsContrDtlPk = document.getElementById("dsContrDtlPk_B#" + idB).value;
	var rowAList = document.getElementsByName("dsContrDtlPk_A");
	var rowA = null;
	for (var a = 0; a < rowAList.length; a++) {
		if (rowAList[a].value == dsContrDtlPk) {
			rowA = rowAList[a];
			break;
		}
	}
	if (rowA == null) {
		return null;
	} else {
		return rowA.id.substring(rowA.id.indexOf("#") + 1);
	}
}
function findBIdByAId(idA) {
	var dsContrDtlPk = document.getElementById("dsContrDtlPk_A#" + idA).value;
	var rowBList = document.getElementsByName("dsContrDtlPk_B");
	var rowB = null;
	for (var b = 0; b < rowBList.length; b++) {
		if (rowBList[b].value == dsContrDtlPk) {
			rowB = rowBList[b];
			break;
		}
	}
	if (rowB == null) {
		return null;
	} else {
		return rowB.id.substring(rowB.id.indexOf("#") + 1);
	}
}
function onChangeDetail(idA) {
	var serNum = document.getElementById("serNum_A#" + idA).value;
	var rowBList = document.getElementsByName("serNum_B");
	var rowB = null;
	for (var b = 0; b < rowBList.length; b++) {
		if (rowBList[b].value == serNum) {
			var idB = rowBList[b].id.substring(rowBList[b].id.indexOf('#') + 1);
			rowB = document.getElementById("svcMachMstrPk_B#" + idB)
			break;
		}
	}
	if (rowB != null) {
		var rowBRect = rowB.getBoundingClientRect();
		var div = document.getElementById("top");
		var divRect = div.getBoundingClientRect();
		div.scrollTop = rowBRect.top - divRect.top + div.scrollTop;
	}
}
function onMouseOverBllgMtrLbDescTxt(obj) {
	var dsContrBllgMtrPk = obj.id.substring(obj.id.lastIndexOf("#") + 1);
	if (dsContrBllgMtrPk.length > 0) {
		var rect = obj.getBoundingClientRect();
		var reln = document.getElementById("c#" + dsContrBllgMtrPk);
		var dummy = document.getElementById("dummy");
		dummy.innerHTML = reln.innerHTML;
		dummy.style.left = rect.left + 10;
		dummy.style.top = rect.top + 10;
		dummy.style.visibility = "visible";
		dummy.style.display = "";
	}
}
function onMouseOutBllgMtrLbDescTxt(obj) {
	var dsContrBllgMtrPk = obj.id.substring(obj.id.lastIndexOf("#") + 1);
	if (dsContrBllgMtrPk.length > 0) {
		var dummy = document.getElementById("dummy");
		dummy.style.display = "none";
	}
}
function syncValue(fromName, fromId, toName) {
	var fromObj = document.getElementById(fromName + "#" + fromId);
	var toId = null;
	if (endsWith(toName, "_A")) {
		toId = findAIdByBId(fromId);
	} else if (endsWith(toName, "_B")) {
		toId = findBIdByAId(fromId);
	}
	if (toId != null) {
		var toObj = document.getElementById(toName + "#" + toId);
		if (toObj != null) {
			toObj.value = fromObj.value;
		}
	}
}
function syncBaseUsgDay(fromName, fromId, toName) {
	var together = document.getElementById("xxSelFlg_UT");
	if (together.checked) {
		var fromObj = document.getElementById(fromName + "#" + fromId);
		var toObj = document.getElementById(toName + "#" + fromId);
		if (toObj != null) {
			toObj.value = fromObj.value;
		}
	}
}
function syncValueBillingCounter(fromName, fromId) {
	var fromObj = document.getElementById(fromName + "#" + fromId);
	var fromVal = fromObj.value;
	var dsContrBllgMtrPk = document.getElementById("dsContrBllgMtrPk_B#" + fromId).value;
	var rowBList = document.getElementsByName("dsContrBllgMtrPk_B");
	var toId = null;
	var toObj = null;
	for (var b = 0; b < rowBList.length; b++) {
		if (rowBList[b].value == dsContrBllgMtrPk) {
			toId = rowBList[b].id.substring(rowBList[b].id.indexOf('#') + 1);
			toObj = document.getElementById(fromName + "#" + toId);
			if (toObj != null) {
				toObj.value = fromVal;
			}
		}
	}
}

</script>
<%--SCRIPT--%>

<%-- **** Task specific area ends here **** --%>
