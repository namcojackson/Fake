<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160809140113 --%>
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
			<input type="hidden" name="pageID" value="NLEL0070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Asset Book Control">
			
			<%-- ********************** --%>
			<%-- *** Upper Tab Area *** --%>
			<%-- ********************** --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<li class="pTab_ON"><a href="./NLEL0090Scrn00.html">Asset Book Ctl</a></li>
								</ul>
							</div>

							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							</ezf:skip>

							<div class="pTab_BODY">

								<%-- ###TAB - HEAD --%>
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px; border: 0;">
									<col width="120" align="left">
									<col width="120"  align="left">
									<col width="10"  align="left">
									<col width="120" align="left">
									<col width="100"  align="left">
									<col width="10"  align="left">
									<col width="110" align="left">
									<!-- 1 -->
									<tr height="20">
										<td class="stab">Book Type</td>
										<td>
											<ezf:select name="assetTpCd_H1" ezfName="assetTpCd_H1" ezfBlank="1" ezfCodeName="assetTpCd_P1" ezfDispName="assetTpDescTxt_P1" otherEvent1=" onchange=\"sendServer('OnChange_AssetTp')\"" otherAttr=" style=\"width:120px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Effective From Date</td>
										<td>
											<ezf:select name="effFromDt_H1" ezfName="effFromDt_H1" ezfBlank="1" ezfCodeName="effFromDt_P1" ezfDispName="effFromDt_P2" otherAttr=" style=\"width:100px\""/>
										</td>
										<td>&nbsp;</td>
										<td align="right"><ezf:inputButton name="Search" ezfName="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>

								<hr>

								<%-- ######################################## DETAIL ######################################## --%>
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px; border: 0;">
									<col width="120" align="left">
									<col width="120"  align="left">
									<col width="10"  align="left">
									<col width="140" align="left">
									<col width="100"  align="left">
									<col width="10"  align="left">
									<col width="110" align="left">
									<tr height="20">
										<td class="stab">Book Type</td>
										<td class="stab" style="vertical-align: middle;">
											<ezf:select name="assetTpCd_M1" ezfName="assetTpCd_M1" ezfBlank="1" ezfCodeName="assetTpCd_Q1" ezfDispName="assetTpDescTxt_Q1" otherAttr=" style=\"width:120px\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab">Effective From Date</td>
										<td class="stab" style="vertical-align: middle;">
											<ezf:inputText name="effFromDt_M1" ezfName="effFromDt_M1" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
                                            <img src="./img/calendar.gif" class="pCalendar" onclick="calendar( 'effFromDt_M1', 4 );">
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td class="stab">Description</td>
										<td colspan="6">
											<ezf:inputText name="assetBookCtrlDescTxt_M1" ezfName="assetBookCtrlDescTxt_M1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5WWWWWWWWW6WWWWWWWWW7" otherAttr=" size=\"50\" maxlength=\"50\""/>
										</td>
									</tr>
									<tr height="20">
										<td class="stab">Default Life (Month)</td>
										<td class="stab">
											<ezf:inputText name="defDepcMthNum_M1" ezfName="defDepcMthNum_M1" value="1234567890" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="assetCoaAcctCd_LK" ezfName="assetCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AssetCoaAcct" otherAttr=" id=\"assetCoaAcctCd_LK\" tabindex=\"1\"">Asset</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="assetCoaAcctCd_M1" ezfName="assetCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td class="stab">Current Period</td>
										<td class="stab">
											<ezf:inputText name="xxYrMth_M1" ezfName="xxYrMth_M1" value="1234567890123456" otherAttr=" size=\"8\" maxlength=\"8\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="depcCoaAcctCd_LK" ezfName="depcCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_DepcCoaAcct" otherAttr=" id=\"depcCoaAcctCd_LK\" tabindex=\"1\"">Depreciation Expense</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="depcCoaAcctCd_M1" ezfName="depcCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td class="stab">Last Depc Period</td>
										<td class="stab">
											<ezf:inputText name="acctYrMth_M1" ezfName="acctYrMth_M1" value="1234567890123456" otherAttr=" size=\"8\" maxlength=\"8\""/>
										</td>
										<td>&nbsp;</td>
										<td class="stab"><ezf:anchor name="accumDepcCoaAcctCd_LK" ezfName="accumDepcCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AccumDepcCoaAcct" otherAttr=" id=\"accumDepcCoaAcctCd_LK\" tabindex=\"1\"">Accumulated Depreciation</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="accumDepcCoaAcctCd_M1" ezfName="accumDepcCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td colspan="3">&nbsp;</td>
										<td class="stab"><ezf:anchor name="depcAdjCoaAcctCd_LK" ezfName="depcAdjCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_DepcAdjCoaAcct" otherAttr=" id=\"depcAdjCoaAcctCd_LK\" tabindex=\"1\"">Depreciation Adjustment</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="depcAdjCoaAcctCd_M1" ezfName="depcAdjCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td colspan="3">&nbsp;</td>
										<td class="stab"><ezf:anchor name="slsPrcdCoaAcctCd_LK" ezfName="slsPrcdCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_SlsPrcdCoaAcct" otherAttr=" id=\"slsPrcdCoaAcctCd_LK\" tabindex=\"1\"">Sales Proceeds</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="slsPrcdCoaAcctCd_M1" ezfName="slsPrcdCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td colspan="3">&nbsp;</td>
										<td class="stab"><ezf:anchor name="rmvCostCoaAcctCd_LK" ezfName="rmvCostCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_RmvCostCoaAcct" otherAttr=" id=\"rmvCostCoaAcctCd_LK\" tabindex=\"1\"">Removal Cost</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="rmvCostCoaAcctCd_M1" ezfName="rmvCostCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td colspan="3">&nbsp;</td>
										<td class="stab"><ezf:anchor name="gainLossCoaAcctCd_LK" ezfName="gainLossCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_GainLossCoaAcct" otherAttr=" id=\"gainLossCoaAcctCd_LK\" tabindex=\"1\"">Gain/Loss</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="gainLossCoaAcctCd_M1" ezfName="gainLossCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td colspan="3">&nbsp;</td>
										<td class="stab"><ezf:anchor name="clingCoaAcctCd_LK" ezfName="clingCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_ClingCoaAcct" otherAttr=" id=\"clingCoaAcctCd_LK\" tabindex=\"1\"">Clearing Account</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="clingCoaAcctCd_M1" ezfName="clingCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>									<!-- 9 -->
									<tr height="20">
										<td colspan="3">&nbsp;</td>
										<td class="stab"><ezf:anchor name="adjCoaAcctCd_LK" ezfName="adjCoaAcctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AdjCoaAcct" otherAttr=" id=\"adjCoaAcctCd_LK\" tabindex=\"1\"">Adjustment Account</ezf:anchor></td>
										<td class="stab">
											<ezf:inputText name="adjCoaAcctCd_M1" ezfName="adjCoaAcctCd_M1" value="1234567890" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>

							</div>
						</td>
					</tr>
				</table>
			</center>


<%-- **** Task specific area ends here **** --%>
