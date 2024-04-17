<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230529114422 --%>
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
			<input type="hidden" name="pageID" value="NWAL1600Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Salesrep Credit">

		<!-- ##### BODY(HEADER) ##### -->
			<div class="pTab_BODY">
				<table>
					<tr>
						<td>
							<table cellpadding="2" border="1" cellspacing="0" style="margin-bottom:4px;">
								<col width="">
								<col width="">
								<col width="">
								<col width="">
								<tr height="">
									<td class="pClothBs">Transaction Number</td>
									<td class="pOut"><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="12345678" otherAttr=" size=\"10\" maxlength=\"8\" ezftoupper=\"\" style=\"border:0;background-color:transparent;\""/></td>
									<td class="pClothBs">Configuration Number</td>
									<td class="pOut"><ezf:inputText name="xxComnScrColValTxt" ezfName="xxComnScrColValTxt" value="1,2,3" otherAttr=" size=\"80\" ezftoupper=\"\" style=\"border:0;background-color:transparent;\""/></td>
								</tr>
							</table>
							<fieldset>
								<legend style="font-size:12px;">Sales Credit</legend>
								<table cellpadding="1">
									<tr>
										<td>
											<ezf:inputButton name="AddRowSlsCr" ezfName="AddRowQuote" value="Add" htmlClass="pBtn4" />
										</td>
										<td>
											<ezf:inputButton name="DeleteRowSlsCr" ezfName="DeleteRowQuote" value="Delete" htmlClass="pBtn4" />
										</td>
									</tr>
								</table>

								<table border="0" cellpadding="0" cellspacing="0" style="padding:1px;">
									<col align="left" valign="top">
									<tr>
										<td>
											<div id="QuoteTop" style="overflow-x:hidden; width:994;" onScroll="synchroScrollLeft( this.id, new Array( 'Quote' ));">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="24"  align="center">
													<col width="130" align="center">
													<col width="198" align="center">
													<col width="69"  align="center">
													<col width="68"  align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<tr height="24">
														<td class="pClothBs">&nbsp</td>
														<td class="pClothBs">Role Type</td>
														<td class="pClothBs">Name(*)</td>
														<td class="pClothBs">Num(*)</td>
														<td class="pClothBs">Percent</td>
														<td class="pClothBs">Bus Unit</td>
														<td class="pClothBs">Branch</td>
														<td class="pClothBs">Cost Center</td>
													</tr>
												</table>
											</div>
											<div id="Quote" style="overflow-x:hidden; width:994; height:180px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
												<table id="A_Top" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="24"  align="center">
													<col width="130" align="center">
													<col width="198" align="center">
													<col width="69"  align="center">
													<col width="68"  align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<tbody>
													<ezf:row ezfHyo="A">
														<tr>
															<td><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/></td>
															<td><ezf:select name="lineBizRoleTpCd_A" ezfName="lineBizRoleTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfCodeName="lineBizRoleTpCd_AC" ezfDispName="lineBizRoleTpDescTxt_AD" ezfCodeDispHyo="A" otherAttr=" style=\"width:124px;\""/></td>
															<td>
																<ezf:inputText name="tocNm_A" ezfName="tocNm_A" value="BLOOM Josh" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromSalesCreditName" otherAttr=" size=\"22\" ezfnoupperfocusout=\"OnBlur_DeriveFromSalesCreditName\""/><ezf:inputButton name="OpenWin_SlsRep" value="..." ezfHyo="A_Top" ezfArrayNo="0" htmlClass="pBtn0" />
															</td>
															<td><ezf:inputText name="psnNum_A" ezfName="psnNum_A" value="C11590" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromSalesCreditCode" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromSalesCreditCode\""/></td>
															<td><ezf:inputText name="slsRepCrPct_A" ezfName="slsRepCrPct_A" value="60.00" ezfHyo="A" ezfArrayNo="0" otherEvent1="OnBlur_CalcTotalPercent" otherAttr=" size=\"6\" ezffocusout=\"OnBlur_CalcTotalPercent\" style=\"text-align:right;\""/>&nbsp;%</td>
															<td><ezf:inputText name="xxCoaExtnSrchTxt_A" ezfName="xxCoaExtnSrchTxt_A" value="200 CORE SALES" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:0;background-color:transparent;\""/></td>
															<td><ezf:inputText name="xxCoaBrSrchTxt_A" ezfName="xxCoaBrSrchTxt_A" value="CHICAGO" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:0;background-color:transparent;\""/></td>
															<td><ezf:inputText name="xxCoaProdSrchTxt_A" ezfName="xxCoaProdSrchTxt_A" value="21212-ESS SALES***20123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:0;background-color:transparent;\""/></td>
														</tr>
													</ezf:row>
													<ezf:skip>	
													</ezf:skip>
													</tbody>
												</table>
												<table id="A_Bottom" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;margin-left:351px;">
													<col width="70"  align="center">
													<col width="68"  align="right">
													<tr>
														<td class="pClothV">Total</td>
														<td class="stabB"><ezf:label name="xxDealSlsPct" ezfName="xxDealSlsPct" />&nbsp;%</td>
													</tr>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</fieldset>
							<br>
							<fieldset>
								<legend style="font-size:12px;">Non Quota Revenue: Salesrep</legend>
								<table cellpadding="1">
									<tr>
										<td>
											<ezf:inputButton name="AddRowNonQuote" ezfName="AddRowNonQuote" value="Add" htmlClass="pBtn4" />
										</td>
										<td>
											<ezf:inputButton name="DeleteRowNonQuote" ezfName="DeleteRowNonQuote" value="Delete" htmlClass="pBtn4" />
										</td>
									</tr>
								</table>

								<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
									<col align="left" valign="top">
									<tr>
										<td>
											<div id="NonQuoteTop" style="overflow-x:hidden; width:994;"
												onScroll="synchroScrollLeft( this.id, new Array( 'NonQuote' ));">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="24"  align="center">
													<col width="130" align="center">
													<col width="198" align="center">
													<col width="69"  align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<tr height="24">
														<td class="pClothBs">&nbsp</td>
														<td class="pClothBs">Role Type</td>
														<td class="pClothBs">Name(*)</td>
														<td class="pClothBs">Num(*)</td>
														<td class="pClothBs">Bus Unit</td>
														<td class="pClothBs">Branch</td>
														<td class="pClothBs">Cost Center</td>
													</tr>
												</table>
											</div>
											<div id="NonQuote" style="overflow-x:hidden; width:925; height:180px; overflow-y:scroll;" onScroll="synchroScrollLeft( this.id, new Array( 'RightTop' ) );">
												<table id="B_Top" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
													<col width="24"  align="center">
													<col width="130" align="center">
													<col width="198" align="center">
													<col width="69"  align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<col width="161" align="center">
													<tbody>
													<ezf:row ezfHyo="B">
														<tr>
															<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/></td>
															<td><ezf:select name="lineBizRoleTpCd_B" ezfName="lineBizRoleTpCd_B" ezfHyo="B" ezfArrayNo="0" ezfCodeName="lineBizRoleTpCd_BC" ezfDispName="lineBizRoleTpDescTxt_BD" ezfCodeDispHyo="B" otherAttr=" style=\"width:124px;\""/></td>
															<td>
																<ezf:inputText name="tocNm_B" ezfName="tocNm_B" value="FM REP 123" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromSalesRepName" otherAttr=" size=\"22\" ezfnoupperfocusout=\"OnBlur_DeriveFromSalesRepName\""/><ezf:inputButton name="OpenWin_NoQuoteSlsRep" value="..." ezfHyo="B_Top" ezfArrayNo="0" htmlClass="pBtn0" />
															</td>
															<td><ezf:inputText name="psnNum_B" ezfName="psnNum_B" value="ZZZ0001" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_DeriveFromSalesRepCode" otherAttr=" size=\"8\" maxlength=\"50\" ezffocusout=\"OnBlur_DeriveFromSalesRepCode\""/></td>
															<td><ezf:inputText name="xxCoaExtnSrchTxt_B" ezfName="xxCoaExtnSrchTxt_B" value="320" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:0;background-color:transparent;\""/></td>
															<td><ezf:inputText name="xxCoaBrSrchTxt_B" ezfName="xxCoaBrSrchTxt_B" value="CHICAGO" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:0;background-color:transparent;\""/></td>
															<td><ezf:inputText name="xxCoaProdSrchTxt_B" ezfName="xxCoaProdSrchTxt_B" value="31111-FM MO RGAN STANLEY" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" style=\"border:0;background-color:transparent;\""/></td>
														</tr>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
			</div>


<%-- **** Task specific area ends here **** --%>
