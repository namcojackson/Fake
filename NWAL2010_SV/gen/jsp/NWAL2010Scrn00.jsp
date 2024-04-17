<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240416154315 --%>
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

<!--  For TCEPPS Start-->
<script type="text/javascript" src="./js/common/tcepps.js" charset="UTF-8"></script>
<%@page import="business.servlet.NWAL2010.NWAL2010BMsg" %>
<%@page import="parts.common.EZDSystemEnv" %>
<%
	int index = 0;
	NWAL2010BMsg scrnMsg 	= (NWAL2010BMsg)databean.getEZDBMsg();
	String bgColor                      = EZDSystemEnv.getString("S21.mode.color");
	bgColor                             = (null == bgColor || 0 == bgColor.length()) ? "#FAFAFA" : bgColor;

%>


<!--  For TCEPPS End-->
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NWAL2010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Credit Card Screen">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%-- ######################################## HEADER ######################################## --%>
				<div class="pTab_BODY">
					<table border="0" width="99%" align="center">
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<col width="" align="" valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="5">
									<col width="60"> <!-- Trx Type label  -->
									<col width="70"> <!-- Trx Type txt box -->
									<col width="10">
									<col width="800"> <!-- Trx Type txt box -->
									<tr>
										<td rowspan="2">&nbsp</td>
										<td colspan = "3">&nbsp</td>
										<td rowspan = "2">
											<table border="0" cellpadding="0" cellspacing="0" style="margin-left:1px">
												<col align="left" valign="top">
												<tr>
													<td>
														<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="90" align="center"> <!-- TRX_C1  -->
															<col width="90" align="center"> <!-- TRX_C2  -->
															<col width="90" align="center"> <!-- TRX_C3  -->
															<col width="90" align="center"> <!-- TRX_C4  -->
															<col width="90" align="center"> <!-- TRX_C5  -->
															<col width="90" align="center"> <!-- TRX_N1  -->
															<col width="90" align="center"> <!-- TRX_N2  -->
															<col width="90" align="center"> <!-- TRX_N3  -->
															<col width="90" align="center"> <!-- TRX_N4  -->
															<col width="90" align="center"> <!-- TRX_N5  -->
															<tr height="24">
																<td class="pClothBs">TRX_C1</td>
																<td class="pClothBs">TRX_C2</td>
																<td class="pClothBs">TRX_C3</td>
																<td class="pClothBs">TRX_C4</td>
																<td class="pClothBs">TRX_C5</td>
																<td class="pClothBs">TRX_N1</td>
																<td class="pClothBs">TRX_N2</td>
																<td class="pClothBs">TRX_N3</td>
																<td class="pClothBs">TRX_N4</td>
																<td class="pClothBs">TRX_N5</td>
															</tr>
														</table>
														<table id="B_Top" border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
															<col width="90"> <!-- TRX_C1  -->
															<col width="90"> <!-- TRX_C2  -->
															<col width="90"> <!-- TRX_C3  -->
															<col width="90"> <!-- TRX_C4  -->
															<col width="90"> <!-- TRX_C5  -->
															<col width="90"> <!-- TRX_N1  -->
															<col width="90"> <!-- TRX_N2  -->
															<col width="90"> <!-- TRX_N3  -->
															<col width="90"> <!-- TRX_N4  -->
															<col width="90"> <!-- TRX_N5  -->
															<tbody>
																<tr height="24">
																	<td align="center"><ezf:inputText name="firstTrxInfoTxt" ezfName="firstTrxInfoTxt" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="scdTrxInfoTxt" ezfName="scdTrxInfoTxt" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="thirdTrxInfoTxt" ezfName="thirdTrxInfoTxt" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="frthTrxInfoTxt" ezfName="frthTrxInfoTxt" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="fifthTrxInfoTxt" ezfName="fifthTrxInfoTxt" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="firstTrxInfoNum" ezfName="firstTrxInfoNum" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="scdTrxInfoNum" ezfName="scdTrxInfoNum" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="thirdTrxInfoNum" ezfName="thirdTrxInfoNum" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="frthTrxInfoNum" ezfName="frthTrxInfoNum" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																	<td align="center"><ezf:inputText name="fifthTrxInfoNum" ezfName="fifthTrxInfoNum" otherAttr=" size=\"11\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/></td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td class="stab">Trx Type</td>
										<td><ezf:inputText name="crCardTrxTpCd" ezfName="crCardTrxTpCd" value="TRX_N5" otherAttr=" size=\"15\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<%-- ######################################## SEARCH CRITERIA ######################################## --%>
					<table>
						<tr>
							<td>
								<table>
									<col width="5"> 
									<col width="80"> <!-- Cust Ref Num Label  -->
									<col width="100"> <!-- Cust Ref Num Text Box  -->
									<col width="10">
									<col width="50"> <!-- Valid Thru Label  -->
									<col width="90"> <!-- Valid Thru Text Box  -->
									<col width="20">
									<col width="50"> <!-- CC TYPE Label -->
									<col width="90"> <!-- CC TYPE Text Box -->
									<col width="20">
									<col width="60"> <!-- Last 4 digits Label  -->
									<col width="90"> <!-- Last 4 digits Text Box  -->
									<col width="30">
									<col width="100"> <!-- Search Button  -->
									<tr>
										<td rowspan="2">&nbsp</td>
										<%if ("Y".equals(scrnMsg.acctCdFlg_LK.getValue())) {%>
											<td class="stab">Account Code</td>
										<% } else { %>
											<td class="stab"><ezf:anchor name="acctCd_LK" ezfName="acctCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctSearch" otherAttr=" id=\"acctCd_LK\" tabindex=\"1\" ezfanchortext=\"\"">Account Code</ezf:anchor></td>
										<% } %>
										<td><ezf:inputText name="sellToCustCd" ezfName="sellToCustCd" value="TRX_N5" otherAttr=" size=\"15\" ezftoupper=\"\""/>
										<td>&nbsp</td>
										<td class="stab">Invalid</td>
										<td><ezf:inputCheckBox name="crCardValidFlg" ezfName="crCardValidFlg" value="N" /></td>
										<td colspan = "8">&nbsp</td>
									</tr>
									<tr>
										<td class="stab">Cust Ref Num</td>
										<td><ezf:inputText name="crCardCustRefNum" ezfName="crCardCustRefNum" value="CE1234" otherAttr=" size=\"15\" ezftoupper=\"\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
										<td class="stab">Valid Thru</td>
										<td><ezf:inputText name="crCardExprYrMth" ezfName="crCardExprYrMth" value="12345678" otherAttr=" size=\"15\" ezftoupper=\"\" maxlength=\"7\""/></td>
										<td>&nbsp</td>
										<td class="stab">CC TYPE</td>
										<td>
											<ezf:select name="crCardTpCd" ezfName="crCardTpCd" ezfBlank="1" ezfCodeName="crCardTpCd_CD" ezfDispName="crCardTpDescTxt_DI" otherAttr=" style=\"width : 130px;\""/>
										</td>
										<td>&nbsp</td>
										<td class="stab">Last 4 Digit</td>
										<td><ezf:inputText name="crCardLastDigitNum" ezfName="crCardLastDigitNum" value="1234" otherAttr=" size=\"5\" maxlength=\"4\" ezftoupper=\"\" ezftoupper=\"\""/></td>
										<td>&nbsp</td>
										<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<hr>

					<%-- ######################################## DETAIL ######################################## --%>
					<table>
						<tr>
							<td>
								<table border="0" cellpadding="1" cellspacing="0">
									<col width="778">
									<tr>
										<td></td>
										<td>
											<table>
												<tr align="right">
													<td>
														<!-- MOCK -->
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
																	<td class="pOut">192</td>
																	<td>&nbsp;</td>
																	<td>
																		<input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled>
																	</td>
																	<td></td>
																	<td>
																		<input type="button" class="pBtn3" value="Next" name="PageNext">
																	</td>
																</tr>
															</table>
														</ezf:skip>

														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>

								<table>
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<tr>
													<td valign="top">
														<!-- Right TBL Header -->
														<div id="rightTopTBL" style="overflow-y:hidden; height:20; overflow-x:hidden;" >
															<table border="1" cellpadding="1" cellspacing="0" width="1100" style="table-layout: fixed;">
																<col width="30" align="center"> <!-- Check Box -->
																<col width="110" align="center"> <!-- Cust Ref Num -->
																<col width="180" align="center"> <!-- Card Holder Name -->
																<col width="80" align="center"> <!-- CC Type -->
																<col width="100" align="center"> <!-- Last 4 Digit -->
																<col width="60" align="center"> <!-- Invalid -->
																<col width="75" align="center"> <!-- Valid Thru -->
																<col width="125" align="center"> <!-- Address 1 -->
																<col width="125" align="center"> <!-- Address 2 -->
																<col width="70" align="center"> <!-- City -->
																<col width="40" align="center"> <!-- State -->
																<col width="80" align="center"> <!-- Post Code -->
																<col width="35" align="center"> <!-- Country -->
																<tr class="pEvenNumberBGcolor" height="18">
																	<td class="pClothBs">&nbsp</td>
																	<td class="pClothBs">Cust Ref Num</td>
																	<td class="pClothBs">Card Holder Name</td>
																	<td class="pClothBs">CC Type</td>
																	<td class="pClothBs">Last 4 Digit</td>
																	<td class="pClothBs">Invalid</td>
																	<td class="pClothBs">Valid Thru</td>
																	<td class="pClothBs">Address 1</td>
																	<td class="pClothBs">Address 2</td>
																	<td class="pClothBs">City</td>
																	<td class="pClothBs">State</td>
																	<td class="pClothBs">Post Code</td>
																	<td class="pClothBs">Ctry</td>
																</tr>
															</table>
														</div>
														<!-- Right TBL Data -->
														<div id="rightTBL" style="overflow-y:scroll; height:304; overflow-x:hidden;" >
															<table border="1" cellpadding="1" cellspacing="0" width="1100" id="A" style="table-layout: fixed;">
																<col width="30" align="center"> <!-- Check Box -->
																<col width="110" align="left"> <!-- Cust Ref Num -->
																<col width="180" align="center"> <!-- Card Holder Name -->
																<col width="80" align="left"> <!-- CC Type -->
																<col width="100" align="left"> <!-- Last 4 Digit -->
																<col width="60" align="left"> <!-- Invalid -->
																<col width="75" align="left"> <!-- Valid Thru -->
																<col width="125" align="center"> <!-- Address 1 -->
																<col width="125" align="center"> <!-- Address 2 -->
																<col width="70" align="left"> <!-- City -->
																<col width="40" align="left"> <!-- State -->
																<col width="80" align="left"> <!-- Post Code -->
																<col width="35" align="left"> <!-- Country -->
																<ezf:row ezfHyo="A">
																	<tr height="25">
																		<td>
																			<ezf:inputRadio name="xxRadioBtn_A" ezfName="xxRadioBtn_A" value="{EZF_ROW_NUMBER}" ezfGetLineNoHyo="A" ezfGetLineNoOffset="0" otherAttr=" id=\"xxRadioBtn\""/>
																		</td>
																		<td>
																			<ezf:label name="crCardCustRefNum_A" ezfName="crCardCustRefNum_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:inputText name="crCardHldNm_A" ezfName="crCardHldNm_A" ezfHyo="A" otherAttr=" size=\"24\" maxlength=\"40\" style=\"border:none; background-color:transparent\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:label name="crCardTpDescTxt_A" ezfName="crCardTpDescTxt_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			****<ezf:label name="crCardLastDigitNum_A" ezfName="crCardLastDigitNum_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:label name="crCardValidFlg_A" ezfName="crCardValidFlg_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:label name="crCardExprYrMth_A" ezfName="crCardExprYrMth_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:inputText name="firstLineAddr_A" ezfName="firstLineAddr_A" ezfHyo="A" otherAttr=" size=\"16\" maxlength=\"60\" style=\"border:none; background-color:transparent\""/>
																		</td>
																		<td>
																			<ezf:inputText name="scdLineAddr_A" ezfName="scdLineAddr_A" ezfHyo="A" otherAttr=" size=\"16\" maxlength=\"60\" style=\"border:none; background-color:transparent\""/>
																		</td>
																		<td>
																			<ezf:inputText name="ctyAddr_A" ezfName="ctyAddr_A" ezfHyo="A" otherAttr=" size=\"8\" maxlength=\"25\" style=\"border:none; background-color:transparent\""/>
																		</td>
																		<td>
																			<ezf:label name="stCd_A" ezfName="stCd_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:label name="postCd_A" ezfName="postCd_A" ezfHyo="A" ezfArrayNo="0" />
																		</td>
																		<td>
																			<ezf:label name="ctryCd_A" ezfName="ctryCd_A" ezfHyo="A" ezfArrayNo="0" />
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

								<%-- ######################################## CREDIT CREATION ######################################## --%>
								<table style="table-layout:fixed;">
									<col width="99%" align="center">
									<tr>
										<td>
											<table border="0" width="99%" align="center">
												<col width="10">
												<col width="230">
												<col width="30">
												<col width="800">
												<tr>
													<td>&nbsp</td>
													<td><ezf:inputButton name="OpenWin_CreditCardEntry" value="Credit Card Entry" htmlClass="pBtn12" /></td>
													<td>&nbsp</td>
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
</center>


<%-- **** Task specific area ends here **** --%>
