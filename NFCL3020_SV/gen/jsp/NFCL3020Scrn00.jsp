<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190308131309 --%>
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
			<input type="hidden" name="pageID" value="NFCL3020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Batch Entry">

<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
<%--
					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="margin-left:20px;">
						<col width="115" align="left">
						<col width="305" align="left">
						<col width="105" align="left">
						<col width="300" align="left">
						<col width="83"  align="left">
						<col width="83"  align="left">
						<col width="">
						<tr height="25">
							<td class="stab">Saved Search Options</td>
							<td>
								<ezf:select name="srchOptPk_H" ezfName="srchOptPk_H" ezfBlank="1" ezfCodeName="srchOptPk_LC" ezfDispName="srchOptNm_LD" otherEvent1=" onchange=\"sendServer('OnChangeSavedSearchOption')\"" otherAttr=" style=\"width:290px;\" tabindex=\"1\""/>
							</td>
							<td class="stab">Search Option Name</td>
							<td><ezf:inputText name="srchOptNm_H" ezfName="srchOptNm_H" otherAttr=" size=\"40\" maxlength=\"50\" ezftoupper=\"\" tabindex=\"1\""/></td>
							<td><ezf:inputButton name="SaveSearch" ezfName="SaveSearch" value="SaveSearch" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
							<td><ezf:inputButton name="DeleteSearch" ezfName="DeleteSearch" value="DeleteSearch" htmlClass="pBtn7" otherAttr=" tabindex=\"1\""/></td>
							<td>&nbsp;</td>
						</tr>
					</table>
--%>
					<table border="0" cellpadding="0" cellspacing="0">
						<col width="1100" align="right">
						<tr>
							<td><ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn6" /></td>
						</tr>
					</table>

					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="1" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" height="80" width="1100" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
									<tr>
										<td valign="top">
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="100">
												<col width="240">
												<col width="100">
												<col width="240">
												<col width="420">
												<tr>
													<td class="stab">Receipt Source</td>
													<td>
														<ezf:select name="arRcptSrcCd_H" ezfName="arRcptSrcCd_H" ezfBlank="1" ezfCodeName="arRcptSrcCd_LC" ezfDispName="arRcptSrcNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"1000\""/>
													</td>
													<td class="stab">Batch Count</td>
													<td><ezf:inputText name="arBatRcptCnt_H" ezfName="arBatRcptCnt_H" value="35" otherAttr=" size=\"6\" maxlength=\"6\" tabindex=\"1000\" ezftoupper=\"\" style=\"text-align:right\""/>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Batch Name</td>
													<td><ezf:inputText name="arBatRcptNm_H" ezfName="arBatRcptNm_H" value="BOA_CHI_20150303_001" otherAttr=" size=\"30\" maxlength=\"55\" tabindex=\"-1\" ezftoupper=\"\""/></td>
													<td class="stab">Batch Amount</td>
													<td><ezf:inputText name="arBatRcptAmt_H" ezfName="arBatRcptAmt_H" value="71256.21" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"1000\" ezftoupper=\"\" style=\"text-align:right\""/></td>
												</tr>
												<tr>
													<td class="stab">Batch Date</td>
													<td>
														<ezf:inputText name="arBatRcptDt_H" ezfName="arBatRcptDt_H" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"1000\""/>
														<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('arBatRcptDt_H', 4);" >
													</td>
													<td class="stab">Batch Status</td>
													<td>
														<ezf:select name="arBatRcptStsCd_H" ezfName="arBatRcptStsCd_H" ezfCodeName="arBatRcptStsCd_LC" ezfDispName="arBatRcptStsNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"-1\""/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
	<%-- ######################################## DETAIL ######################################## --%>
					<%-- ###TAB - HEAD --%>
					<div class="pTab_HEAD">
						<ul>
							<li  id="AddInfo"  title="AddInfo"  class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_A" ezfEmulateBtn="1" ezfGuard="TAB_AddInfo" >Add Info</ezf:anchor></li>
							<li  id="Receipts" title="Receipts" class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_B" ezfEmulateBtn="1" ezfGuard="TAB_Receipts" >Receipts</ezf:anchor></li>
						</ul>
					</div>
					<c:choose>
						<%-- Add Info TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_AddInfo'}">
							<script type="text/javascript">
								document.getElementById( "AddInfo" ).className = "pTab_ON";
								document.getElementById( "Receipts").className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="1100">
									<tr>
										<td>
											<table width="1100"  height="400" border="0" cellpadding="0" cellspacing="0">
												<col width="490">
												<col width="005">
												<col width="600">
												<tr>
													<td valign="top">
														<table border="0" cellpadding="0" cellspacing="0" height="250" width="490" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
															<tr>
																<td valign="top">
																	<table border="0" cellpadding="1" cellspacing="0">
																		<col width="080" align="left">
																		<col width="050" align="center">
																		<col width="150" align="center">
																		<col width="210" align="center">
																		<tr>
																			<td class="stab"><b>Total</b></td>
																			<td class="stab"></td>
																			<td class="stab"></td>
																			<td></td>
																		</tr>
																		<tr>
																			<td class="stab"></td>
																			<td class="stab"><b>Count</b></td>	
																			<td class="stab"><b>Amount</b></td>	
																		</tr>
																		<tr>
																			<td class="stab">Actual</td>
																			<td><ezf:inputText name="xxTotCnt_H1" ezfName="xxTotCnt_H1" value="35" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H1" ezfName="xxTotAmt_H1" value="71256.21" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td></td>
																		</tr>
																		<tr>
																			<td class="stab">Reversed</td>
																			<td><ezf:inputText name="xxTotCnt_H2" ezfName="xxTotCnt_H2" value="0" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H2" ezfName="xxTotAmt_H2" value="0.00" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																		<tr>
																			<td class="stab">Difference</td>
																			<td><ezf:inputText name="xxTotCnt_H3" ezfName="xxTotCnt_H3" value="0" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H3" ezfName="xxTotAmt_H3" value="0.00" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab"></td>
																			<td class="stab"><b>Count</b></td>	
																			<td class="stab"><b>Amount</b></td>	
																		</tr>
																		<tr>
																			<td class="stab">Applied</td>
																			<td><ezf:inputText name="xxTotCnt_H4" ezfName="xxTotCnt_H4" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H4" ezfName="xxTotAmt_H4" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																		<tr>
																			<td class="stab">UnApplied</td>
																			<td><ezf:inputText name="xxTotCnt_H5" ezfName="xxTotCnt_H5" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H5" ezfName="xxTotAmt_H5" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																		<tr>
																			<td class="stab">On Account</td>
																			<td><ezf:inputText name="xxTotCnt_H6" ezfName="xxTotCnt_H6" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H6" ezfName="xxTotAmt_H6" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																		<tr>
																			<td class="stab">Unidentified</td>
																			<td><ezf:inputText name="xxTotCnt_H7" ezfName="xxTotCnt_H7" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H7" ezfName="xxTotAmt_H7" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																		<tr>
																			<td class="stab">Refunded</td>
																			<td><ezf:inputText name="xxTotCnt_H8" ezfName="xxTotCnt_H8" otherAttr=" size=\"5\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																			<td><ezf:inputText name="xxTotAmt_H8" ezfName="xxTotAmt_H8" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>	
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
													<td></td>
													<td valign="top">
														<table border="0" cellpadding="0" cellspacing="0" height="250" width="600" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
															<tr valign="top">
																<td>
																	<table border="0" cellpadding="0" cellspacing="0">
																		<col width="100" align="left">
																		<col width="500" align="left">
																		<tr>
																			<td class="stab">Comment</td>
																			<td colspan="7"><ezf:inputText name="arBatRcptCmntTxt_H" ezfName="arBatRcptCmntTxt_H" value="XXXX1XXXX2XXXX3XXXX4XXXX5XXXX6" otherAttr=" size=\"70\" maxlength=\"1000\" tabindex=\"1000\" ezftoupper=\"\""/></td>	
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab"><b>Lockbox</b></td>
																			<td></td>	

																		</tr>
																		<tr>
																			<td class="stab">File Name</td>
																			<td><ezf:inputText name="arLockBoxFileNm_H" ezfName="arLockBoxFileNm_H" otherAttr=" size=\"30\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>	

																		</tr>
																		<tr>
																			<td class="stab">Lockbox</td>
																			<td>
																				<ezf:select name="arLockBoxCd_H" ezfName="arLockBoxCd_H" ezfBlank="1" ezfCodeName="arLockBoxCd_LC" ezfDispName="arLockBoxNm_LD" otherAttr=" style=\"width:220px;\" tabindex=\"-1\""/>
																			</td>	
																		</tr>
																		<tr>
																			<td class="stab">Batch#</td>
																			<td><ezf:inputText name="arLockBoxBatNum_H" ezfName="arLockBoxBatNum_H" otherAttr=" size=\"3\" maxlength=\"3\" tabindex=\"-1\" ezftoupper=\"\""/></td>	
																		</tr>
																		<tr>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>
																		</tr>
																		<tr>
																			<td class="stab" colspan="2"><b>Remittance Bank</b></td>
																		</tr>
																		<tr>
																			<td class="stab"><ezf:anchor name="dsBankAcctNm_L1" ezfName="dsBankAcctNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_1" otherAttr=" tabindex=\"1000\"">Bank Name</ezf:anchor></td>
																			<td><ezf:inputText name="dsBankAcctNm_H" ezfName="dsBankAcctNm_H" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab"><ezf:anchor name="dsBankBrNm_L1" ezfName="dsBankBrNm_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_2" otherAttr=" tabindex=\"1000\"">Branch Name</ezf:anchor></td>
																			<td><ezf:inputText name="dsBankBrNm_H" ezfName="dsBankBrNm_H" otherAttr=" size=\"31\" maxlength=\"80\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab"><ezf:anchor name="dsBankAcctNum_L1" ezfName="dsBankAcctNum_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkBankAccount_3" otherAttr=" tabindex=\"1000\"">Account Number</ezf:anchor></td>
																			<td><ezf:inputText name="dsBankAcctNum_H" ezfName="dsBankAcctNum_H" otherAttr=" size=\"31\" maxlength=\"14\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
						<%-- Receipts TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Receipts'}">
							<script type="text/javascript">
								document.getElementById( "AddInfo" ).className = "pTab_OFF";
								document.getElementById( "Receipts").className = "pTab_ON";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="1100">
									<tr>
										<td>
											<table width="1100" border="0" cellpadding="0" cellspacing="0">
												<col width="750">
												<tr>
													<td>
														<table  border="0">
															<col width="050">
															<col width="120">
															<col width="080">
															<col width="070">
															<col width="080">
															<col width="120">
															<col width="050">
															<col width="100">
															<col width="040">
															<col width="040">
															<tr>
															<td class="stab">Receipt#</td>
															<td><ezf:inputText name="rcptChkNum_BH" ezfName="rcptChkNum_BH" value="XXXXXXXXXXXXXXXX" otherAttr=" size=\"15\" maxlength=\"15\" tabindex=\"3001\" ezftoupper=\"\""/>
															<td class="stab">Receipt Type</td>
															<td>
																<ezf:select name="arRcptTrxTpCd_BH" ezfName="arRcptTrxTpCd_BH" ezfBlank="1" ezfCodeName="arRcptTrxTpCd_LC" ezfDispName="arRcptTrxTpNm_LD" otherAttr=" style=\"width:60px;\" tabindex=\"3001\""/>
															</td>
															<td class="stab">Receipt Date</td>
															<td>
																<ezf:inputText name="rcptDt_BH" ezfName="rcptDt_BH" value="10/10/09" otherAttr=" size=\"10\" maxlength=\"10\" tabindex=\"3001\""/>
																<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('rcptDt_BH', 4);" >
															</td>
															<td class="stab">Amount</td>
															<td><ezf:inputText name="funcRcptAmt_BH" ezfName="funcRcptAmt_BH" value="812.25" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"3001\" ezftoupper=\"\" style=\"text-align:right\""/></td>
															<td><ezf:inputButton name="Click_Add" value="Add" htmlClass="pBtn2" otherAttr=" tabindex=\"3001\""/></td></td>
															<td><ezf:inputButton name="Click_Del" value="Del" htmlClass="pBtn2" otherAttr=" tabindex=\"3001\""/></td></td>
															</tr>
															
														</table>
													</td>
													<td></td>
												</tr>
											</table>
											<table>
												<tr>
													<td class="stab"><ezf:anchor name="payerCustCd_L" ezfName="payerCustCd_L" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1519\"">Customer Number:</ezf:anchor></td>
													<td><ezf:inputText name="payerCustCd_BH" ezfName="payerCustCd_BH" value="1015536" otherAttr=" size=\"12\" maxlength=\"8\" tabindex=\"-1\" ezftoupper=\"\""/></td>
													<td><ezf:inputButton name="CustomerName" value=">>" /></td>
													<td><ezf:inputText name="dsAcctNm_BH" ezfName="dsAcctNm_BH" value="XXXXXXXXX1XXX" otherAttr=" size=\"16\" maxlength=\"50\""/></td>
													<td><ezf:inputButton name="OpenWin_SearchAccountByTrxPopup" value="Search By Trx" htmlClass="pBtn8" /></td>
													<td class="stab"><ezf:anchor name="payerCustCd_L" ezfName="payerCustCd_L" ezfEmulateBtn="1" ezfGuard="Click_LinkCustomer" otherAttr=" tabindex=\"1519\"">Location:</ezf:anchor></td>
													<td><ezf:inputText name="locNum_BH" ezfName="locNum_BH" value="42886" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
												</tr>
											</table>
											<table width="1100" border="0" cellpadding="0" cellspacing="0">
												<col width="5">
												<col width="525">
												<col width="550">
												<col width="20">
												<tr>
													<td></td>
													<td><ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/></td>
													<td align="right">
														<ezf:skip>
														<table border="0" cellpadding="1" cellspacing="0">
															<col width="054"  align="center">
															<col width="040"  align="right">
															<col width="016"  align="center">
															<col width="040"  align="right">
															<col width="016"  align="center">
															<col width="040"  align="right">
															<col width="010">
															<col width="050">
															<col width="050">
															<tr>
																<td class="stab">Showing</td>
																<td class="pOut">1</td>
																<td class="stab">to</td>
																<td class="pOut">20</td>
																<td class="stab">of</td>
																<td class="pOut">20</td>
																<td></td>
																<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
																<td><input type="button" class="pBtn3" value="Next" name="PageNext" disabled></td>
															</tr>
														</table>
														</ezf:skip>

														<%-- Pagenation --%>
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="B" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
														</jsp:include>
													</td>
													<td></td>
												</tr>
											</table>
											<div id="parentBoxB">
												<table border="0">
													<tr>
														<td>
															<div style="float:left; display:block"><!-- left table -->
															<div id='leftTblHead' style="display:block;"></div>
															<div id='leftTbl' style="float:left; display:block; height:308px; overflow:hidden; margin:0px; padding:0px; padding-bottom:20px"></div>
															</div><!-- left table -->
															<div style="float:left"><!-- right table -->
																<div id='rightTblHead' style="display:block; overflow:hidden; margin:0px;padding:0px; width:1070;">
																	<table style="table-layout:fixed; " width="1350" height="25" border="1" cellpadding="1" cellspacing="0" style="margin-right:20px" id="BHEAD" >
																		<col width="020" align="center">
																		<col width="100" align="center">
																		<col width="120" align="center">
																		<col width="120" align="center">
																		<col width="085" align="center">
																		<col width="120" align="center">
																		<col width="090" align="center">
																		<col width="160" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="090" align="center">
																		<col width="100" align="center">
																		<col width="120" align="center">
																		<tr>
																			<td id="DH0" class="pClothBs colFix">&nbsp;</td>
																			<td id="DH1" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rcptNum_B' )">Receipt Doc#<img id="sortIMG.rcptNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH2" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rcptChkNum_B' )">Receipt#<img id="sortIMG.rcptChkNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH3" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'arRcptTrxTpNm_B' )">Type<img id="sortIMG.arRcptTrxTpNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH4" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'rcptDt_B' )">Date<img id="sortIMG.rcptDt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH5" class="pClothBs colFix"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'funcRcptAmt_B' )">Amount<img id="sortIMG.funcRcptAmt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH6" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'payerCustCd_B' )">Customer#<img id="sortIMG.payerCustCd_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH7" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'dsAcctNm_B' )">Customer Name<img id="sortIMG.dsAcctNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH8" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'locNum_B' )">Location<img id="sortIMG.locNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH9" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'arRcptStsNm_B' )">Status<img id="sortIMG.arRcptStsNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH10" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'arTrxNum_B' )">Apply To<img id="sortIMG.arTrxNum_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH11" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'arTrxTpNm_B' )">Inv Type<img id="sortIMG.arTrxTpNm_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																			<td id="DH12" class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'funcApplyAmt_B' )">Amount Applied<img id="sortIMG.funcApplyAmt_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
																		</tr>
																	</table>
																</div>
																<div id='rightTbl' style="height:325; width:1087; display:block; overflow:scroll; margin:0px; padding:0px;" >
																	<table border="1" cellpadding="1" cellspacing="0" width="1350" id="B"  style="table-layout:fixed;">
																		<col width="020" align="center">
																		<col width="100" align="center">
																		<col width="120" align="center">
																		<col width="120" align="center">
																		<col width="085" align="center">
																		<col width="120" align="center">
																		<col width="090" align="center">
																		<col width="160" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="090" align="center">
																		<col width="100" align="center">
																		<col width="120" align="center">
																		<ezf:row ezfHyo="B">
																		<tr id="id_row{EZF_ROW_NUMBER}" height="20">
																			<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B#{EZF_ROW_NUMBER}\" tabindex=\"4001\""/></td>
																			<td><div id="rcptNum_B#{EZF_ROW_NUMBER}"><ezf:anchor name="Click_LinkRcptNum" ezfName="Click_LinkRcptNum" ezfHyo="B" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Click_LinkRcptNum" otherAttr=" id=\"rcptNum_B#{EZF_ROW_NUMBER}\" ezfanchortext=\"\" tabindex=\"4001\""><ezf:label name="rcptNum_B" ezfName="rcptNum_B" ezfHyo="B" ezfArrayNo="0" /></ezf:anchor></div></td>
																			<td><ezf:inputText name="rcptChkNum_B" ezfName="rcptChkNum_B" value="XXXXXXXXXXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"15\" id=\"rcptChkNum_B#{EZF_ROW_NUMBER}\" tabindex=\"4001\" ezftoupper=\"\""/>
																			<td><ezf:inputText name="arRcptTrxTpNm_B" ezfName="arRcptTrxTpNm_B" value="Cash" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" id=\"arRcptTrxTpNm_B#{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="rcptDt_B" ezfName="rcptDt_B" value="10/08/2014" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" id=\"rcptDt_B#{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="funcRcptAmt_B" ezfName="funcRcptAmt_B" value="812.25" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\" id=\"funcRcptAmt_B#{EZF_ROW_NUMBER}\" tabindex=\"4001\""/></td>
																			<td><ezf:inputText name="payerCustCd_B" ezfName="payerCustCd_B" value="1015536" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="dsAcctNm_B" ezfName="dsAcctNm_B" value="NJ DEPT OF LAW & PUBLIC" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="locNum_B" ezfName="locNum_B" value="Location" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="arRcptStsNm_B" ezfName="arRcptStsNm_B" value="Cleared" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"26\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="arTrxNum_B" ezfName="arTrxNum_B" value="4013907050" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="arTrxTpNm_B" ezfName="arTrxTpNm_B" value="Invoice" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\""/></td>
																			<td><ezf:inputText name="funcApplyAmt_B" ezfName="funcApplyAmt_B" value="812.25" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" tabindex=\"-1\" ezftoupper=\"\" style=\"text-align:right\""/></td>
																			<ezf:inputHidden name="arRcptTrxTpCd_B" ezfName="arRcptTrxTpCd_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" ezftoupper=\"\""/>
																			<ezf:inputHidden name="ezUpTimeZone_B" ezfName="ezUpTimeZone_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" ezftoupper=\"\""/>
																			<ezf:inputHidden name="ezUpTime_B" ezfName="ezUpTime_B" ezfHyo="B" ezfArrayNo="0" otherAttr=" tabindex=\"-1\" ezftoupper=\"\""/>
																		</tr>
																		</ezf:row>
																		<ezf:skip>
																		</ezf:skip>
																	</table>
																</div>
															</div><!-- right table -->
														</td>
													</tr>
												</table>
											</div><!-- parent box -->
											<script type="text/javascript" defer>
											S21TableUI.initialize("parentBoxB", "BHEAD", "B", 6, true);
											</script>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
					</c:choose>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
