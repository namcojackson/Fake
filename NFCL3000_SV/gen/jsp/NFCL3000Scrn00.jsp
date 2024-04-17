<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190606165253 --%>
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
			<input type="hidden" name="pageID" value="NFCL3000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Manual Invoice Entry">

			<ezf:inputHidden name="xxRecHistCratTs" ezfName="xxRecHistCratTs" otherAttr=" id=\"xxRecHistCratTs\""/>
			<ezf:inputHidden name="xxRecHistCratByNm" ezfName="xxRecHistCratByNm" otherAttr=" id=\"xxRecHistCratByNm\""/>
			<ezf:inputHidden name="xxRecHistUpdTs" ezfName="xxRecHistUpdTs" otherAttr=" id=\"xxRecHistUpdTs\""/>
			<ezf:inputHidden name="xxRecHistUpdByNm" ezfName="xxRecHistUpdByNm" otherAttr=" id=\"xxRecHistUpdByNm\""/>
			<ezf:inputHidden name="xxRecHistTblNm" ezfName="xxRecHistTblNm" otherAttr=" id=\"xxRecHistTblNm\""/>

<center>
	<table width="1128" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

<%-- ######################################## HEADER ######################################## --%>
				<%-- ###TAB - HEAD --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">

					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0" width="352" rules="none" style="float:left;">
									<tr>
										<td valign="top">
											<table border="0" cellpadding="1" cellspacing="0">
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0" height="250" width="350" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
															<tr>
																<td valign="top">
																	<table border="0" cellpadding="0" cellspacing="1">
																	<col width="120">
																	<col width="220">
																		<tr>
																			<td class="stab" >Invoice Number</td>
																			<td>
																				<ezf:inputText name="grpInvNum_H1" ezfName="grpInvNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"13\""/>
																				<ezf:inputText name="invNum_H1" ezfName="invNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"13\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab" >Class</td>
																			<td>
																				<ezf:select name="invTpCd_H1" ezfName="invTpCd_H1" ezfBlank="1" ezfCodeName="invTpCd_LC" ezfDispName="invTpNm_LD" otherEvent1=" onchange=\"sendServer('Select_InvoiceType')\"" otherAttr=" style=\"width:130px;\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab" >Invoice Type</td>
																			<td>
																				<ezf:select name="dsInvTpCd_H1" ezfName="dsInvTpCd_H1" ezfBlank="1" ezfCodeName="dsInvTpCd_LC" ezfDispName="dsInvTpNm_LD" otherEvent1=" onchange=\"sendServer('Select_DsInvoiceType')\"" otherAttr=" style=\"width:170px;\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab" >Invoice Date</td>
																			<td>
																				<ezf:inputText name="invDt_H1" ezfName="invDt_H1" value="04/25/2015" otherEvent1=" onchange=\"sendServer('Select_PmtTerm')\"" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('invDt_H1', 4);" >
																			</td>
																		</tr>
																		<tr>
																			<td class="stab" >Payment Term</td>
																			<td>
																				<ezf:select name="pmtTermCashDiscCd_H1" ezfName="pmtTermCashDiscCd_H1" ezfBlank="1" ezfCodeName="pmtTermCashDiscCd_LC" ezfDispName="pmtTermCashDiscNm_LD" otherEvent1=" onchange=\"sendServer('Select_PmtTerm')\"" otherAttr=" style=\"width:170px;\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab" >Due Date</td>
																			<td>
																				<ezf:inputText name="netDueDt_H1" ezfName="netDueDt_H1" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="img/calendar.gif" class="pCalendar" onclick="calendar('netDueDt_H1', 4);" >
																			</td>
																		</tr>
																		<!-- START 2016/10/03 S.Fujita [QC#14120,DEL] -->
																		<!-- 
																		<tr>
																			<td class="stab" >Invoicing Rule</td>
																			<td>
																				<select style="width:170px;" name="dfrdInvRuleCd_H1" ezfname="dfrdInvRuleCd_H1" ezflist="dfrdInvRuleCd_LC,dfrdInvRuleNm_LD,99, ,blank">
																					<option>000000000011111111110000000000</option>
																					<option>000000000011111</option>
																				</select>
																			</td>
																		</tr>
																		-->
																		<!-- END   2016/10/03 S.Fujita [QC#14120,DEL] -->
																		<tr>
																			<td class="stab" >Source Number</td>
																			<td><ezf:inputText name="srcSysDocNum_H1" ezfName="srcSysDocNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab" >PO Number</td>
																			<td><ezf:inputText name="custIssPoNum_H1" ezfName="custIssPoNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"30\" maxlength=\"35\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab" ><ezf:anchor name="slsRepTocCd_L1" ezfName="slsRepTocCd_L1" ezfEmulateBtn="1" ezfGuard="Click_LinkSalesRep" >Salesrep</ezf:anchor></td>
																			<td>
																				<!-- START 2016/07/14 S.Fujita [QC#11157,MOD] -->
																				<!-- 
																				<input type="text" size="8" maxlength="8" value="XXXXXXXXX1XXX" name="slsRepTocCd_H1" ezfname="slsRepTocCd_H1">
																				-->
																				<ezf:inputText name="psnNum_H1" ezfName="psnNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"8\" maxlength=\"50\" ezftoupper=\"\""/>
																				<!-- END   2016/07/14 S.Fujita [QC#11157,MOD] -->
																				<ezf:inputButton name="SalesRepName" value=">>" ezfHyo="A" ezfArrayNo="0" />
																				<ezf:inputText name="slsRepTocNm_H1" ezfName="slsRepTocNm_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"16\" maxlength=\"50\""/>
																			</td>
																		</tr>
																		<tr>
																			<td class="stab" >CI Number</td>
																			<td><ezf:inputText name="custCareTktNum_H1" ezfName="custCareTktNum_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
																		</tr>
																		<tr>
																			<td class="stab" >Source</td>
																			<td><ezf:inputText name="sysSrcCd_H1" ezfName="sysSrcCd_H1" value="XXX" otherAttr=" size=\"3\" maxlength=\"3\""/>
																				<ezf:inputText name="sysSrcDescTxt_H1" ezfName="sysSrcDescTxt_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"25\" maxlength=\"50\""/>
																			</td>
																			<td></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
														<table border="0" cellpadding="0" cellspacing="0" width="350" rules="none"  style="float:left;padding: 3px; margin-bottom: 3px; border: 1px solid #333333;">
															<tr height="36">
																<td>
																	<table border="0" cellpadding="0" cellspacing="0" align="center">
																		<col width="040">
																		<col width="020">
																		<col width="070">
																		<col width="020">
																		<col width="070">
																		<col width="020">
																		<col width="060">
																		<col width="020">
																		<tr>
																			<td class="stab" >Complete</td>
																			<td><ezf:inputCheckBox name="xxChkBox_H3" ezfName="xxChkBox_H3" value="Y" /></td>
																			<td class="stab" >Credit Applied</td>
																			<td><ezf:inputCheckBox name="xxChkBox_H4" ezfName="xxChkBox_H4" value="Y" /></td>
																			<td class="stab" >Cash Applied</td>
																			<td><ezf:inputCheckBox name="xxChkBox_H5" ezfName="xxChkBox_H5" value="Y" /></td>
																			<td class="stab" >Print Eligible</td>
																			<td><ezf:inputCheckBox name="xxChkBox_H6" ezfName="xxChkBox_H6" value="Y" /></td>
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
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" width="375" rules="none" style="float:left;">
												<tr>
													<td valign="top">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td class="stab" height="24" valign="bottom"><b>
																	Ship To (Multiple :
																	<ezf:inputCheckBox name="xxChkBox_H2" ezfName="xxChkBox_H2" value="Y" />
																	)
																</b></td>
															</tr>
															<tr>
																<td>
																	<table border="0" cellpadding="0" cellspacing="0" height="200" width="370" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
																		<tr>
																			<td valign="top">
																				<table border="0" cellpadding="0" cellspacing="1">
																					<col width="100">
																					<col width="220">
																					<tr>
																						<td class="stab" ><ezf:anchor name="shipToCustAcctCd_L2" ezfName="shipToCustAcctCd_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkShipToAccount" >Account Number</ezf:anchor></td>
																						<td>
																							<ezf:inputText name="shipToCustAcctCd_H2" ezfName="shipToCustAcctCd_H2" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
																							<!-- START 2016/07/28 S.Fujita [QC#10148,MOD] -->
																							<!-- 
																							<input type="text" size="13" maxlength="20" value="XXXXXXXXX1XXX" name="shipToCustCd_H2" ezfname="shipToCustCd_H2" >
																							 -->
																							 <ezf:inputButton name="ShipToCustName" value=">>" />
																							<!-- END   2016/07/28 S.Fujita [QC#10148,MOD] -->
																						</td>
																					</tr>
																					<tr>
																						<td class="stab" >Name</td>
																						<td><ezf:inputText name="shipToCustAcctNm_H2" ezfName="shipToCustAcctNm_H2" value="XXXXXXXXX1XXX" otherAttr=" size=\"32\" maxlength=\"360\""/></td>
																					</tr>
																					<tr>
																						<td class="stab" ><ezf:anchor name="locNum_L2" ezfName="locNum_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkShipToLocation" >Location</ezf:anchor></td>
																						<!-- START 2016/07/28 S.Fujita [QC#10148,MOD] -->
																						<!-- 
																						<td><input type="text" size="32" maxlength="30" value="XXXXXXXXX1XXX" name="locNum_H2" ezfname="locNum_H2" ></td>
																						-->
																						<td>
																							<ezf:inputText name="locNum_H2" ezfName="locNum_H2" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
																							<ezf:inputButton name="ShipToLocName" value=">>" />
																						</td>
																						<!-- END   2016/07/28 S.Fujita [QC#10148,MOD] -->
																					</tr>
																					<tr>
																						<td class="stab" valign="top">Address</td>
																						<td>
																							<ezf:textArea name="xxDsMsgEntryTxt_H2" ezfName="xxDsMsgEntryTxt_H2" otherAttr=" rows=\"8\" cols=\"33\" maxlength=\"1000\" ezftoupper=\"\""/>
																						</td>
																					</tr>
																					<tr>
																						<td class="stab" ><ezf:anchor name="ctacPsnPk_L2" ezfName="ctacPsnPk_L2" ezfEmulateBtn="1" ezfGuard="Click_LinkShipToContact" >Contact</ezf:anchor></td>
																						<td><ezf:inputText name="xxPsnNm_H2" ezfName="xxPsnNm_H2" value="XXXXXXXXX1XXX" otherAttr=" size=\"32\" maxlength=\"62\""/></td>
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
											<table border="0" cellpadding="0" cellspacing="0" width="375" rules="none" style="float:left;">
												<tr>
													<td valign="top">
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td width="100" class="stab" height="24" valign="bottom"><b>Bill To</b></td>
																<td width="200"></td>
																<td width=""><ezf:inputButton name="OpenWin_AttachFile" value="Attachment" htmlClass="pBtn6" /></td>
															</tr>
															<tr>
																<td colspan="3">
																	<table border="0" cellpadding="0" cellspacing="0" height="200" width="370" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
																		<tr>
																			<td valign="top">
																				<table border="0" cellpadding="0" cellspacing="1">
																					<col width="100">
																					<col width="220">
																					<tr>
																						<td class="stab" ><ezf:anchor name="billToCustAcctCd_L3" ezfName="billToCustAcctCd_L3" ezfEmulateBtn="1" ezfGuard="Click_LinkBillToAccount" >Account Number</ezf:anchor></td>
																						<td>
																							<ezf:inputText name="billToCustAcctCd_H3" ezfName="billToCustAcctCd_H3" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"20\" ezftoupper=\"\""/>
																							<!-- START 2016/07/28 S.Fujita [QC#10148,MOD] -->
																							<!-- 
																							<input type="text" size="13" maxlength="20" value="XXXXXXXXX1XXX" name="billToCustCd_H3" ezfname="billToCustCd_H3" >
																							 -->
																							 <ezf:inputButton name="BillToCustName" value=">>" />
																							<!-- END   2016/07/28 S.Fujita [QC#10148,MOD] -->
																						</td>
																					</tr>
																					<tr>
																						<td class="stab" >Name</td>
																						<td><ezf:inputText name="billToCustAcctNm_H3" ezfName="billToCustAcctNm_H3" value="XXXXXXXXX1XXX" otherAttr=" size=\"32\" maxlength=\"360\""/></td>
																					</tr>
																					<tr>
																						<td class="stab" ><ezf:anchor name="locNum_L3" ezfName="locNum_L3" ezfEmulateBtn="1" ezfGuard="Click_LinkBillToLocation" >Location</ezf:anchor></td>
																						<!-- START 2016/07/28 S.Fujita [QC#10148,MOD] -->
																						<!-- 
																						<td><input type="text" size="32" maxlength="30" value="XXXXXXXXX1XXX" name="locNum_H3" ezfname="locNum_H3" ></td>
																						-->
																						<td>
																							<ezf:inputText name="locNum_H3" ezfName="locNum_H3" value="XXXXXXXXX1XXX" otherAttr=" size=\"13\" maxlength=\"30\" ezftoupper=\"\""/>
																							<ezf:inputButton name="BillToLocName" value=">>" />
																						</td>
																						<!-- END   2016/07/28 S.Fujita [QC#10148,MOD] -->
																					</tr>
																					<tr>
																						<td class="stab" valign="top">Address</td>
																						<td>
																							<ezf:textArea name="xxDsMsgEntryTxt_H3" ezfName="xxDsMsgEntryTxt_H3" otherAttr=" rows=\"8\" cols=\"33\" maxlength=\"1000\" ezftoupper=\"\""/>
																						</td>
																					</tr>
																					<tr>
																						<td class="stab" ><ezf:anchor name="ctacPsnPk_L3" ezfName="ctacPsnPk_L3" ezfEmulateBtn="1" ezfGuard="Click_LinkBillToContact" >Contact</ezf:anchor></td>
																						<td><ezf:inputText name="xxPsnNm_H3" ezfName="xxPsnNm_H3" value="XXXXXXXXX1XXX" otherAttr=" size=\"32\" maxlength=\"62\""/></td>
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
										</td>
									</tr>
									<tr>
										<td>
											<!--<table border="0" cellpadding="0" cellspacing="0">
												<col width="108" align="left">
												<col width="080" align="left">
												<col width="030" align="left">
												<col width="250" align="left">
												<col width="080" align="left">
												<col width="100" align="left">
												<col width="100" align="right">
												<tr>
													<td class="stab" ><a href="#" name="shipFromInvtyLocCd_L4" ezfname="shipFromInvtyLocCd_L4" onclick="sendServer('Click_LinkWarehouse')">W/H</a></td>
													<td><input type="text" size="10"   maxlength="20"   name="shipFromInvtyLocCd_H4"  ezfname="shipFromInvtyLocCd_H4" value="AAAAAA" ezftoupper></td>
													<td><input type="button" value=">>" name="WarehouseName" onclick="sendServer(this)"></td>
													<td><input type="text" size="33"   maxlength="30"   name="rtlWhNm_H4"  ezfname="rtlWhNm_H4" value="AAAAAA"></td>
													<td class="stab" >Ship Date</td>
													<td>
														<input type="text" size="10" maxlength="10" value="04/25/2015" name="shipDt_H4" ezfname="shipDt_H4">
														<img src="img/calendar.gif" class="pCalendar" onclick="calendar('shipDt_H4', 4);" >
													</td>
													<td><input type="button" class="pBtn6" value="Calc" name="Calc" onclick="sendServer(this)" ></td>
												</tr>
											</table>-->	
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="108" align="left">
												<col width="130" align="left">
												<col width="100" align="left">
												<col width="130" align="left">
												<col width="080" align="left">
												<col width="130" align="left">
												<col width="70" align="right">
												<tr>
													<td class="stab" >Sales Amount</td>
													<td><ezf:inputText name="xxTotAmt_T1" ezfName="xxTotAmt_T1" value="100.00" otherAttr=" size=\"16\" maxlength=\"30\""/></td>	
													<td class="stab" >Freight Amount</td>
													<td><ezf:inputText name="xxTotAmt_T2" ezfName="xxTotAmt_T2" value="100.00" otherAttr=" size=\"16\" maxlength=\"30\""/></td>	
													<td class="stab" >Tax Amount</td>
													<td><ezf:inputText name="xxTotAmt_T3" ezfName="xxTotAmt_T3" value="100.00" otherAttr=" size=\"16\" maxlength=\"30\""/></td>	
													<td><ezf:inputButton name="Calc" value="Calc" htmlClass="pBtn6" /></td>
												</tr>
												<tr>
													<td class="stab" >Invoice Total</td>
													<td><ezf:inputText name="xxTotAmt_T4" ezfName="xxTotAmt_T4" value="100.00" otherAttr=" size=\"16\" maxlength=\"30\""/></td>	
													<td class="stab" >Balance</td>
													<td><ezf:inputText name="xxTotAmt_T5" ezfName="xxTotAmt_T5" value="100.00" otherAttr=" size=\"16\" maxlength=\"30\""/></td>	
													<td class="stab" ></td>
													<td align="right">
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
							<li  id="Line"        title="Line"         class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_A" ezfEmulateBtn="1" ezfGuard="TAB_Line" >Line</ezf:anchor></li>
							<li  id="SalesCredit" title="SalesCredit"  class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_B" ezfEmulateBtn="1" ezfGuard="TAB_SalesCredit" >Sales Credit</ezf:anchor></li>
							<li  id="Accounting"  title="Accounting"   class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_C" ezfEmulateBtn="1" ezfGuard="TAB_Accounting" >Accounting</ezf:anchor></li>
							<li  id="Shipping"    title="Shipping"     class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_D" ezfEmulateBtn="1" ezfGuard="TAB_BOL" >Shipping</ezf:anchor></li>
							<li  id="MoreInfo"    title="MoreInfo"     class="pTab_ON"><ezf:anchor name="" ezfName="xxTabProt_E" ezfEmulateBtn="1" ezfGuard="TAB_MoreInfo" >More Info</ezf:anchor></li>
						</ul>
					</div>
					<c:choose>
						<%-- Line TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Line'}">
							<script type="text/javascript">
								document.getElementById( "Line"       ).className = "pTab_ON";
								document.getElementById( "SalesCredit").className = "pTab_OFF";
								document.getElementById( "Accounting" ).className = "pTab_OFF";
								document.getElementById( "Shipping"   ).className = "pTab_OFF";
								document.getElementById( "MoreInfo"   ).className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="100%">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" width="1090px" style="margin-left:4px;">
												<col width="200px" align="left">
												<col width="400px" align="center">
												<col width="200px" align="right">
												<tr>
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<td>
<%-- Pending
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
															<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"         value="A" />
															<jsp:param name="ShowingFrom"     value="xxPageShowFromNum_A" />
															<jsp:param name="ShowingTo"       value="xxPageShowToNum_A" />
															<jsp:param name="ShowingOf"       value="xxPageShowOfNum_A" />
															<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum_A" />
															<jsp:param name="ShowingTotal"    value="xxPageShowTotNum_A" />
															<jsp:param name="ViewMode"        value="FULL" />
														</jsp:include>
--%>
													</td>
													<td>
														<ezf:inputButton name="AddLine_A" value="Add Line" htmlClass="pBtn6" />
														<ezf:inputText name="xxNum_A" ezfName="xxNum_A" value="12345" otherAttr=" size=\"5\" maxlength=\"5\""/>
														<ezf:inputButton name="DeleteLine_A" value="Delete Line" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>

											<div id="parentBoxA">
												<table>
													<tr>
														<td>
															<div style="float:left; display:block"> <!-- left table -->
																<div id="leftTblHead" style="display:block;"></div>
																<div id="leftTbl" style="float:left; display:block; height:148px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
															</div>  <!-- left table -->

															<div style="float:left"> <!-- right table -->
																<div id="rightTblHead" style="width:1090px; display:block; overflow:hidden; margin:0px;padding:0px;">
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="AHEAD" width="1970px" style="margin-right:20px">
																		<col width="030">
																		<col width="060">
																		<col width="060">
																		<col width="090">
																		<col width="180">
																		<col width="150">
																		<col width="060">
																		<col width="050">
																		<col width="100">
																		<col width="100">
																		<col width="100">
																		<col width="100">
																		<col width="070">
																		<col width="070">
																		<col width="120">
																		<col width="080">
																		<col width="100">
																	<!--	<col width="100">-->
																		<col width="100">
																		<col width="080">
																		<col width="130">
																		<col width="150">
																		<col width="150">
																		<col width="150">
																		<col width="120">
																		<col width="120">
																		<col width="080">
																		<col width="100">
																		<col width="240">
																		<col width="080">
																		<col width="100">
																		<col width="100">
																		<col width="100">
																		<col width="150">
																		<col width="150">
																		<col width="150">
																		<col width="150">
																		<col width="150">
																		<col width="150">
																		<col width="400">
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400">
																		<col width="200">
																</c:when>
															</c:choose>
																		<tr height="24">
																			<td id="AH0"  class="pClothBs" align="center" colFix><ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" onClick="sendServerForPreferredView('SelectCheckBox_A')" /></td>
																			<td id="AH1"  class="pClothBs" align="center" colFix>ShipLn#</td>
																			<td id="AH2"  class="pClothBs" align="center" colFix>Line#</td>
																			<td id="AH3"  class="pClothBs" align="center" colFix>Category</td>
																			<td id="AH4"  class="pClothBs" align="center" colFix>Item Code</td>
																			<td id="AH5"  class="pClothBs" align="center">Description</td>
																			<td id="AH6"  class="pClothBs" align="center">UOM</td>
																			<td id="AH7"  class="pClothBs" align="center">Qty</td>
																			<td id="AH8"  class="pClothBs" align="center">Unit Price</td>
																			<td id="AH9"  class="pClothBs" align="center">Sales Amount</td>
																			<td id="AH10"  class="pClothBs" align="center">Tax Amount</td>
																			<td id="AH11" class="pClothBs" align="center">Total Amount</td>
																			<td id="AH12" class="pClothBs" align="center">Order#</td>
																			<td id="AH13" class="pClothBs" align="center">Order Line#</td>
																			<td id="AH14" class="pClothBs" align="center">Order Date</td>
																			<td id="AH15" class="pClothBs" align="center">Ship Date</td>
																			<td id="AH16" class="pClothBs" align="center">W/H</td>
																		<!--	<td id="AH16" class="pClothBs" align="center">Std Cost</td>-->
																			<td id="AH17" class="pClothBs" align="center">Tax Percent</td>
																			<td id="AH18" class="pClothBs" align="center">Contract#</td>
																			<td id="AH19" class="pClothBs" align="center">Contract Line Revision</td>
																			<td id="AH20" class="pClothBs" align="center">Serial#</td>
																			<td id="AH21" class="pClothBs" align="center">Model#</td>
																			<td id="AH22" class="pClothBs" align="center">Billing Type</td>
																			<td id="AH23" class="pClothBs" align="center">Bill From Date</td>
																			<td id="AH24" class="pClothBs" align="center">Bill To Date</td>
																			<td id="AH25" class="pClothBs" align="center">No of Copies</td>
																			<td id="AH26" class="pClothBs" align="center">Quantity Ordered</td>
																			<td id="AH27" class="pClothBs" align="center">Reason Code</td>
																			<td id="AH28" class="pClothBs" align="center">Bill Instance#</td>
																			<td id="AH29" class="pClothBs" align="center">CSMP Number</td>
																			<td id="AH30" class="pClothBs" align="center">CSA Number</td>
																			<td id="AH31" class="pClothBs" align="center">CSMP Amount</td>
																			<td id="AH32" class="pClothBs" align="center">Control1</td>
																			<td id="AH33" class="pClothBs" align="center">Control2</td>
																			<td id="AH34" class="pClothBs" align="center">Control3</td>
																			<td id="AH35" class="pClothBs" align="center">Control4</td>
																			<td id="AH36" class="pClothBs" align="center">Control5</td>
																			<td id="AH37" class="pClothBs" align="center">Control6</td>
																			<td id="AH38" class="pClothBs" align="center">Comments</td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																			<td id="AH39" class="pClothBs" align="center">Error Message</td>
																			<td id="AH40" class="pClothBs" align="center">Error Val</td>
																</c:when>
															</c:choose>
																		</tr>
																	</table>
																</div><!-- rightTblHead-->

																<div id="rightTbl" style="width:1106px; height:165px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="A" width="1970px" >
																		<col width="030" align="center">
																		<col width="060" align="center">
																		<col width="060" align="center">
																		<col width="090" align="center">
																		<col width="180" align="center">
																		<col width="150" align="center">
																		<col width="060" align="center">
																		<col width="050" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="070" align="center">
																		<col width="070" align="center">
																		<col width="120" align="center">
																		<col width="080" align="center">
																		<col width="100" align="center">
																	<!--	<col width="100" align="center">-->
																		<col width="100" align="center">
																		<col width="080" align="center">
																		<col width="130" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="120" align="center">
																		<col width="120" align="center">
																		<col width="080" align="center">
																		<col width="100" align="center">
																		<col width="240" align="center">
																		<col width="080" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="100" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="150" align="center">
																		<col width="400" align="center">
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400" align="center">
																		<col width="200" align="center">
																</c:when>
															</c:choose>
<!-- START 2018/09/28 T.Ogura [QC#28526,ADD] -->
<%@ page import="business.servlet.NFCL3000.NFCL3000BMsg" %>
<%@ page import="business.servlet.NFCL3000.NFCL3000_ABMsg" %>
																		<% NFCL3000BMsg bMsg = (NFCL3000BMsg)databean.getEZDBMsg(); %>
																		<% int i = 0; %>
<!-- END   2018/09/28 T.Ogura [QC#28526,ADD] -->
																		<ezf:row ezfHyo="A">
<!-- START 2018/09/28 T.Ogura [QC#28526,ADD] -->
<!-- END   2018/09/28 T.Ogura [QC#28526,ADD] -->
																			<tr id="A_rightTBLRow#{EZF_ROW_NUMBER}" height="24">
																				<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A1#{EZF_ROW_NUMBER}\""/></td>
																				<td>
																					<ezf:select name="invBolLineNum_A1" ezfName="invBolLineNum_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="invBolLineNum_AC" ezfDispName="invBolLineNum_AD" otherEvent1=" onchange=\"sendServerForPreferredView('Select_InvBolLineNum_A', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:55px;\""/>
																				</td>
																				<td><ezf:inputText name="invLineNum_A1" ezfName="invLineNum_A1" value="12345" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																				<td>
																					<ezf:select name="invLineCatgCd_A1" ezfName="invLineCatgCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="invLineCatgCd_AC" ezfDispName="invLineCatgNm_AD" otherAttr=" style=\"width:80px;\""/>
																				</td>
																				<td>
																					<ezf:inputButton name="ItemSearch_A" value="I" ezfHyo="A" ezfArrayNo="0" />
																					<ezf:inputText name="mdseCd_A1" ezfName="mdseCd_A1" value="XXXXXXXXXXXXXXXX" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\" id=\"mdseCd_A1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																					<ezf:inputButton name="ItemName_A" value=">>" ezfHyo="A" ezfArrayNo="0" />
																				</td>
																				<td><ezf:inputText name="mdseNm_A1" ezfName="mdseNm_A1" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td>
<!-- START 2018/09/28 T.Ogura [QC#28526,MOD] -->
<!--																				<select style="width:60px;" name="pkgUomCd_A1" ezfname="pkgUomCd_A1" ezflist="pkgUomCd_AC,pkgUomNm_AD,99,A," ezfHyo="A"> -->
<!--																					<option>EA</option> -->
<!--																					<option>UB</option> -->
<!--																				</select> -->
																					<div id="<%= i %>">
																					<ezf:select name="pkgUomCd_A1" ezfName="pkgUomCd_A1" ezfHyo="A" ezfArrayNo="0" ezfCodeName="pkgUomCd_AC" ezfDispName="pkgUomDescTxt_AD" ezfCodeDispHyo="A" otherEvent1=" onchange=\"sendServer('Select_PackageUom',this.parentNode.id)\"" otherAttr=" style=\"width:60px;\" id=\"pkgUomCd_A1#{EZF_ROW_NUMBER}\""/>
																					</div>
<!-- END   2018/09/28 T.Ogura [QC#28526,MOD] -->
																				</td>
<!-- START 2018/09/28 T.Ogura [QC#28526,MOD] -->
<!--																			<td><input type="text" size="5"  maxlength="6"    name="shipQty_A1"            ezfname="shipQty_A1"            value="213" ezfHyo="A"></td> -->
																				<td><ezf:inputText name="adjQtyDplyTxt_A1" ezfName="adjQtyDplyTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"6\" style=\"{text-align: right}\""/>
<!-- END   2018/09/28 T.Ogura [QC#28526,MOD] -->
																				<td><ezf:inputText name="dealNetUnitPrcAmt_A1" ezfName="dealNetUnitPrcAmt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="invLineDealNetAmt_A1" ezfName="invLineDealNetAmt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="invLineDealTaxAmt_A1" ezfName="invLineDealTaxAmt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="xxTotAmt_A1" ezfName="xxTotAmt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="cpoOrdNum_A1" ezfName="cpoOrdNum_A1" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
																				<td><ezf:inputText name="xxDplyOrdLineNum_A1" ezfName="xxDplyOrdLineNum_A1" value="1.1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"7\""/></td>
																				<td>
																					<ezf:inputText name="ordDt_A1" ezfName="ordDt_A1" value="2015/12/31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('ordDt_A1', 4, '{EZF_ROW_NUMBER}');" >
																				</td>
																				<td><ezf:inputText name="shipDt_A1" ezfName="shipDt_A1" value="2015/12/31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><ezf:inputText name="shipFromInvtyLocCd_A1" ezfName="shipFromInvtyLocCd_A1" value="123" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
																		<!--		<td><input type="text" size="13" maxlength="30"   name="unitCostAmt_A1"        ezfname="unitCostAmt_A1"        value="213" ezfHyo="A"></td>-->
																				<td><ezf:inputText name="taxPct_A1" ezfName="taxPct_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><ezf:inputText name="dsContrNum_A1" ezfName="dsContrNum_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" ezftoupper=\"\""/></td>
																				<td><ezf:inputText name="dsContrSqNum_A1" ezfName="dsContrSqNum_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"17\" maxlength=\"8\""/></td>
																				<td align="left">
																				    <ezf:inputText name="serNum_A1" ezfName="serNum_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"30\""/>
																				    <% if (!"Y".equals(bMsg.A.no(i).xxYesNoCd_A2.getValue())) { %>
                                                                                    <ezf:inputButton name="SerNumSearch_A" value="S" ezfHyo="A" ezfArrayNo="0" />
                                                                                    <% } %>
																				</td>
																				<td><ezf:inputText name="mdlNm_A1" ezfName="mdlNm_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\" ezftoupper=\"\""/></td>
																				<td>
																					<ezf:select name="svcInvChrgTpCd_A1" ezfName="svcInvChrgTpCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="svcInvChrgTpCd_AC" ezfDispName="svcInvChrgTpNm_AD" otherEvent1=" onchange=\"sendServer('Select_SvcInvChrgTp')\"" otherAttr=" style=\"width:150px;\""/>
																				</td>
																				<td>
																					<ezf:inputText name="bllgPerFromDt_A1" ezfName="bllgPerFromDt_A1" value="2015/12/31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgPerFromDt_A1', 4, '{EZF_ROW_NUMBER}');" >
																				</td>
																				<td>
																					<ezf:inputText name="bllgPerThruDt_A1" ezfName="bllgPerThruDt_A1" value="2015/12/31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																					<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('bllgPerThruDt_A1', 4, '{EZF_ROW_NUMBER}');" >
																				</td>
																				<td><ezf:inputText name="bllgCopyQty_A1" ezfName="bllgCopyQty_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																				<td><ezf:inputText name="ordQty_A1" ezfName="ordQty_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"10\""/></td>
																				<td>
																					<ezf:select name="crDrRsnCd_A1" ezfName="crDrRsnCd_A1" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="crDrRsnCd_AC" ezfDispName="crDrRsnNm_AD" otherAttr=" style=\"width:240px;\""/>
																				</td>
																				<td><ezf:inputText name="dsContrDtlPk_A1" ezfName="dsContrDtlPk_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="csmpContrNum_A1" ezfName="csmpContrNum_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"15\""/></td>
																				<td><ezf:inputText name="dlrRefNum_A1" ezfName="dlrRefNum_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\""/></td>
																				<td><ezf:inputText name="funcCsmpCrAmt_A1" ezfName="funcCsmpCrAmt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																				<td><ezf:inputText name="firstBllgAttrbValTxt_A1" ezfName="firstBllgAttrbValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="scdBllgAttrbValTxt_A1" ezfName="scdBllgAttrbValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="thirdBllgAttrbValTxt_A1" ezfName="thirdBllgAttrbValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="frthBllgAttrbValTxt_A1" ezfName="frthBllgAttrbValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="fifthBllgAttrbValTxt_A1" ezfName="fifthBllgAttrbValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="sixthBllgAttrbValTxt_A1" ezfName="sixthBllgAttrbValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"21\" maxlength=\"50\""/></td>
																				<td><ezf:inputText name="manInvCratCmntTxt_A1" ezfName="manInvCratCmntTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"56\" maxlength=\"1000\""/></td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																				<td><ezf:inputText name="invErrMsgTxt_A1" ezfName="invErrMsgTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"56\" maxlength=\"1000\""/></td>
																				<td><ezf:inputText name="invldValTxt_A1" ezfName="invldValTxt_A1" value="213" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
																</c:when>
															</c:choose>
																				<td class="pAuditInfo">
										                                            <ezf:inputHidden name="xxRecHistCratTs_A" ezfName="xxRecHistCratTs_A" ezfHyo="A" ezfArrayNo="0" />
										                                            <ezf:inputHidden name="xxRecHistCratByNm_A" ezfName="xxRecHistCratByNm_A" ezfHyo="A" ezfArrayNo="0" />
										                                            <ezf:inputHidden name="xxRecHistUpdTs_A" ezfName="xxRecHistUpdTs_A" ezfHyo="A" ezfArrayNo="0" />
										                                            <ezf:inputHidden name="xxRecHistUpdByNm_A" ezfName="xxRecHistUpdByNm_A" ezfHyo="A" ezfArrayNo="0" />
										                                            <ezf:inputHidden name="xxRecHistTblNm_A" ezfName="xxRecHistTblNm_A" ezfHyo="A" ezfArrayNo="0" />
										                                        </td>
										                                    </tr>
										                                    <% i++; %>
																		</ezf:row>
																	</table>
																</div><!-- rightTbl-->
															</div><!-- right table-->
														</td>
													</tr>
												</table>
											</div><!-- parentBoxA -->

										</td>
									</tr>
								</table>
							</div>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxA", "AHEAD", "A", 5, false, false);
							</script>
						</c:when>

						<%-- SalesCredit TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_SalesCredit'}">
							<script type="text/javascript">
								document.getElementById( "Line"       ).className = "pTab_OFF";
								document.getElementById( "SalesCredit").className = "pTab_ON";
								document.getElementById( "Accounting" ).className = "pTab_OFF";
								document.getElementById( "Shipping"   ).className = "pTab_OFF";
								document.getElementById( "MoreInfo"   ).className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="100%">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" width="1090px" style="margin-left:4px;">
												<col width="200px" align="left">
												<col width="400px" align="center">
												<col width="200px" align="right">
												<tr>
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<td>
<%-- Pending
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
															<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"         value="B" />
															<jsp:param name="ShowingFrom"     value="xxPageShowFromNum_B" />
															<jsp:param name="ShowingTo"       value="xxPageShowToNum_B" />
															<jsp:param name="ShowingOf"       value="xxPageShowOfNum_B" />
															<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum_B" />
															<jsp:param name="ShowingTotal"    value="xxPageShowTotNum_B" />
															<jsp:param name="ViewMode"        value="FULL" />
														</jsp:include>
--%>
													</td>
													<td>
														<ezf:inputButton name="AddLine_B" value="Add Line" htmlClass="pBtn6" />
														<ezf:inputText name="xxNum_B" ezfName="xxNum_B" value="12345" otherAttr=" size=\"5\" maxlength=\"5\""/>
														<ezf:inputButton name="DeleteLine_B" value="Delete Line" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>

											<div id="parentBoxB">
												<table>
													<tr>
														<td>
															<div style="float:left; display:block"> <!-- left table -->
																<div id="leftTblHead" style="display:block;"></div>
																<div id="leftTbl" style="float:left; display:block; height:148px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
															</div>  <!-- left table -->

															<div style="float:left"> <!-- right table -->
																<div id="rightTblHead" style="width:1090px; display:block; overflow:hidden; margin:0px;padding:0px;">
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="BHEAD" width="2330px" style="margin-right:20px">
																		<col width="030"> 
																		<col width="060"> <!-- S/C# -->
																		<col width="060"> <!-- ShipLn# -->
																		<col width="060"> <!-- Line# -->
																		<col width="120"> <!-- Salesrep Number -->
																		<col width="150"> <!-- Salesrep Name -->
																		<col width="110"> <!-- Percentage Split% -->
																		<col width="100"> <!-- Amount -->
																		<col width="200"> <!-- Salesrep Branch -->
																		<col width="350"> <!-- Comments -->
																		<col width="120"> <!-- Revenue Split Type -->
																		<col width="120"> <!-- Item Code -->
																		<col width="120"> <!-- Accounting Rule -->
																		<col width="110"> <!-- Deferred Revenue Balance Amount -->
																		<col width="100"> <!-- First Revenue Recognition Date -->
																		<col width="100"> <!-- Next Revenue Recognition Date -->
																		<col width="100"> <!-- Schedule Revenue Amount -->
																		<col width="105"> <!-- Revenue Recognition Count -->
																		<col width="090"> <!-- Accounting Rule Duration -->
																		<col width="120"> <!-- Rule Start Date -->
																		<col width="050"> <!-- Error Message -->
																		<col width="050"> <!-- Error Val -->
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400"> <!-- Trx Reason -->
																		<col width="200"> <!-- Trx Reason -->
																</c:when>
															</c:choose>
																		<tr height="40">
																			<td id="BH0"  class="pClothBs" align="center"><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" onClick="sendServer('SelectCheckBox_B')" /></td>
																			<td id="BH1"  class="pClothBs" align="center">S/C#</td>
																			<td id="BH2"  class="pClothBs" align="center">ShipLn#</td>
																			<td id="BH3"  class="pClothBs" align="center">Line#</td>
																			<td id="BH4"  class="pClothBs" align="center">Salesrep Number</td>
																			<td id="BH5"  class="pClothBs" align="center">Salesrep Name</td>
																			<td id="BH6"  class="pClothBs" align="center">Percentage Split%</td>
																			<td id="BH7"  class="pClothBs" align="left" valign="top"><ezf:inputCheckBox name="xxChkBox_BA" ezfName="xxChkBox_BA" value="Y" onClick="sendServer('SelectCheckBox_BA')" />&nbsp;Amount</td>
																			<td id="BH8"  class="pClothBs" align="center">Salesrep Branch</td>
																			<td id="BH9"  class="pClothBs" align="center">Comments</td>
																			<td id="BH10" class="pClothBs" align="center">Revenue Split Type</td>
																			<td id="BH11" class="pClothBs" align="center">Item Code</td>
																			<td id="BH12" class="pClothBs" align="center">Accounting Rule</td>
																			<td id="BH13" class="pClothBs" align="center">Deferred Revenue Balance Amount</td>
																			<td id="BH14" class="pClothBs" align="center">First Revenue Recognition Date</td>
																			<td id="BH15" class="pClothBs" align="center">Next Revenue Recognition Date</td>
																			<td id="BH16" class="pClothBs" align="center">Schedule Revenue Amount</td>
																			<td id="BH17" class="pClothBs" align="center">Revenue Recognition Count</td>
																			<td id="BH18" class="pClothBs" align="center">Accounting Rule Duration</td>
																			<td id="BH19" class="pClothBs" align="center">Rule Start Date</td>
																			<td id="BH20" class="pClothBs" align="center">Trx</td>
																			<td id="BH21" class="pClothBs" align="center">Trx Reason</td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																			<td id="BH22" class="pClothBs" align="center">Error Message</td>
																			<td id="BH23" class="pClothBs" align="center">Error Val</td>
																</c:when>
															</c:choose>
																		</tr>
																	</table>
																</div><!-- rightTblHead-->

																<div id="rightTbl" style="width:1106px; height:149px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="B" width="2330px" >
																		<col width="030" align="center">
																		<col width="060" align="center"><!-- S/C# -->
																		<col width="060" align="center"><!-- ShipLn# -->
																		<col width="060" align="center"><!-- Line# -->
																		<col width="120" align="center"><!-- Salesrep Number -->
																		<col width="150" align="center"><!-- Salesrep Name -->
																		<col width="110" align="center"><!-- Percentage Split% -->
																		<col width="100" align="center"><!-- Amount -->
																		<col width="200" align="center"><!-- Salesrep Branch -->
																		<col width="350" align="center"><!-- Comments -->
																		<col width="120" align="center"><!-- Revenue Split Type -->
																		<col width="120" align="center"><!-- Item Code -->
																		<col width="120" align="center"><!-- Accounting Rule -->
																		<col width="110" align="center"><!-- Deferred Revenue Balance Amount -->
																		<col width="100" align="center"><!-- First Revenue Recognition Date -->
																		<col width="100" align="center"><!-- Next Revenue Recognition Date -->
																		<col width="100" align="center"><!-- Schedule Revenue Amount -->
																		<col width="105" align="center"><!-- Revenue Recognition Count -->
																		<col width="090" align="center"><!-- Accounting Rule Duration -->
																		<col width="120" align="center"><!-- Rule Start Date -->
																		<col width="050" align="center"><!-- Trx -->
																		<col width="050" align="center"><!-- Trx Reason -->
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400" align="center"><!-- Error Message -->
																		<col width="200" align="center"><!-- Error Val -->
																</c:when>
															</c:choose>
																		<ezf:row ezfHyo="B">
																		<tr id="B_rightTBLRow#{EZF_ROW_NUMBER}" height="24">
																			<td><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_B1#{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:inputText name="xxLineNum_B1" ezfName="xxLineNum_B1" value="12345" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																			<td>
																				<ezf:select name="invBolLineNum_B1" ezfName="invBolLineNum_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="invBolLineNum_BC" ezfDispName="invBolLineNum_BD" otherEvent1=" onchange=\"sendServerForPreferredView('Select_InvBolLineNum_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:55px;\""/>
																			</td>
																			<td>
																				<ezf:inputText name="invLineNum_B1" ezfName="invLineNum_B1" value="00001" ezfHyo="B" ezfArrayNo="0" otherEvent1="OnBlur_invLineNum_B" otherAttr=" size=\"5\" maxlength=\"5\" ezffocusout=\"OnBlur_invLineNum_B\""/>
																			</td>
																			<td>
																				<ezf:inputButton name="SalesRepSearch_B" value="S" ezfHyo="B" ezfArrayNo="0" />
																				<ezf:inputText name="psnNum_B1" ezfName="psnNum_B1" value="12345678" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/>
																				<ezf:inputButton name="SalesRepName_B" value=">>" ezfHyo="B" ezfArrayNo="0" />
																			</td>
																			<td><ezf:inputText name="tocNm_B1" ezfName="tocNm_B1" value="123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"50\""/></td>
																			<td><ezf:inputText name="slsRepCrPct_B2" ezfName="slsRepCrPct_B2" value="33.33" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
																			<td><ezf:inputText name="dealSlsCrAmt_B1" ezfName="dealSlsCrAmt_B1" value="213" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"30\""/></td>
																			<td><ezf:inputText name="coaBrNm_B1" ezfName="coaBrNm_B1" value="213" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"240\""/></td>
																			<td><ezf:inputText name="manInvCratCmntTxt_B1" ezfName="manInvCratCmntTxt_B1" value="213" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"48\" maxlength=\"1000\""/></td>
																			<td>
																				<ezf:select name="invLineSplTpCd_B1" ezfName="invLineSplTpCd_B1" ezfHyo="B" ezfArrayNo="0" ezfBlank="1" ezfCodeName="invLineSplTpCd_BC" ezfDispName="invLineSplTpNm_BD" otherAttr=" style=\"width:100px;\""/>
																			</td>
																			<td>
																				<ezf:inputText name="mdseCd_B1" ezfName="mdseCd_B1" value="XXXXXXXXXXXXXXXX" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"16\""/>
																			</td>
																			<td>
																				<ezf:select name="dfrdAcctgRuleCd_B1" ezfName="dfrdAcctgRuleCd_B1" ezfHyo="B" ezfArrayNo="0" ezfCodeName="dfrdAcctgRuleCd_BC" ezfDispName="dfrdAcctgRuleNm_BD" otherEvent1=" onchange=\"sendServerForPreferredView('Select_DfrdAcctgRule_B', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:200px;\""/>
																			</td>
																			<td><ezf:inputText name="dealDfrdBalAmt_B1" ezfName="dealDfrdBalAmt_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
																			<td><ezf:inputText name="firstRevRecogDt_B1" ezfName="firstRevRecogDt_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																			<td><ezf:inputText name="nextRevRecogDt_B1" ezfName="nextRevRecogDt_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																			<td><ezf:inputText name="dealSchdRevAmt_B1" ezfName="dealSchdRevAmt_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"13\""/></td>
																			<td><ezf:inputText name="revRecogCnt_B1" ezfName="revRecogCnt_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
																			<td><ezf:inputText name="dfrdAcctgRuleDurnAot_B1" ezfName="dfrdAcctgRuleDurnAot_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\""/></td>
																			<td>
																				<ezf:inputText name="durnStartDt_B1" ezfName="durnStartDt_B1" value="2015/12/31" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																				<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('durnStartDt_B1', 4, '{EZF_ROW_NUMBER}');" >
																			</td>
																			<td><ezf:inputText name="trxCd_B1" ezfName="trxCd_B1" value="123" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
																			<td><ezf:inputText name="trxRsnCd_B1" ezfName="trxRsnCd_B1" value="12" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																			<td><ezf:inputText name="invErrMsgTxt_B1" ezfName="invErrMsgTxt_B1" value="213" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"56\" maxlength=\"1000\""/></td>
																			<td><ezf:inputText name="invldValTxt_B1" ezfName="invldValTxt_B1" value="213" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
																</c:when>
														</c:choose>
																			<td class="pAuditInfo">
									                                            <ezf:inputHidden name="xxRecHistCratTs_B" ezfName="xxRecHistCratTs_B" ezfHyo="B" ezfArrayNo="0" />
									                                            <ezf:inputHidden name="xxRecHistCratByNm_B" ezfName="xxRecHistCratByNm_B" ezfHyo="B" ezfArrayNo="0" />
									                                            <ezf:inputHidden name="xxRecHistUpdTs_B" ezfName="xxRecHistUpdTs_B" ezfHyo="B" ezfArrayNo="0" />
									                                            <ezf:inputHidden name="xxRecHistUpdByNm_B" ezfName="xxRecHistUpdByNm_B" ezfHyo="B" ezfArrayNo="0" />
									                                            <ezf:inputHidden name="xxRecHistTblNm_B" ezfName="xxRecHistTblNm_B" ezfHyo="B" ezfArrayNo="0" />
									                                        </td>
																		</tr>
																		</ezf:row>
																	</table>
																</div><!-- rightTbl-->
															</div><!-- right table-->
														</td>
													</tr>
												</table>
											</div><!-- parentBoxB -->

										</td>
									</tr>
								</table>
							</div>

							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxB", "BHEAD", "B", 4, false, false);
							</script>
						</c:when>

						<%-- Line Accounting --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_Accounting'}">
							<script type="text/javascript">
								document.getElementById( "Line"       ).className = "pTab_OFF";
								document.getElementById( "SalesCredit").className = "pTab_OFF";
								document.getElementById( "Accounting" ).className = "pTab_ON";
								document.getElementById( "Shipping"   ).className = "pTab_OFF";
								document.getElementById( "MoreInfo"   ).className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>
							<div class="pTab_BODY_In">
								<table width="100%">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" width="1090px" style="margin-left:4px;">
												<col width="140" align="left">
												<col width="030" align="left">
												<col width="020" align="left">
												<col width="020" align="left">
												<col width="030" align="left">
												<col width="400" align="center">
												<col width="170" align="right">
												<tr>
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<td class="stab">
														Summary
													</td>
													<td>
													<%--<ezf:inputRadio name="xxRadioBtn_C" ezfName="xxRadioBtn_C" value="0" otherAttr="onclick=\"sendServer('OnChangeRadio_C');\""/> --%>
														<ezf:inputRadio name="xxRadioBtn_C" ezfName="xxRadioBtn_C" value="0" otherAttr="onclick=\"sendServer('OnChangeRadio_C');\""/>
													</td>
													<td class="stab">
														Edit
													</td>
													<td>
													<%--<ezf:inputRadio name="xxRadioBtn_C" ezfName="xxRadioBtn_C" value="1" otherAttr="onclick=\"sendServer('OnChangeRadio_C');\""/> --%>
														<ezf:inputRadio name="xxRadioBtn_C" ezfName="xxRadioBtn_C" value="1" otherAttr="onclick=\"sendServer('OnChangeRadio_C');\""/>
													</td>
													<td>
<%--Pending
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
															<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"         value="C" />
															<jsp:param name="ShowingFrom"     value="xxPageShowFromNum_C" />
															<jsp:param name="ShowingTo"       value="xxPageShowToNum_C" />
															<jsp:param name="ShowingOf"       value="xxPageShowOfNum_C" />
															<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum_C" />
															<jsp:param name="ShowingTotal"    value="xxPageShowTotNum_C" />
															<jsp:param name="ViewMode"        value="FULL" />
														</jsp:include>
--%>
													</td>
													
													<td>
														<ezf:inputButton name="AddLine_C" value="Add Line" htmlClass="pBtn6" otherAttr=" id=\"AddLine_CId\""/>
														<ezf:inputText name="xxNum_C" ezfName="xxNum_C" value="12345" otherAttr=" size=\"5\" maxlength=\"5\""/>
														<ezf:inputButton name="DeleteLine_C" value="Delete Line" htmlClass="pBtn6" otherAttr=" id=\"DeleteLine_CId\""/>
													</td>
												</tr>
											</table>

											<div id="parentBoxC">
												<table>
													<tr>
														<td>
															<div style="float:left; display:block"> <!-- left table -->
																<div id="leftTblHead" style="display:block;"></div>
																<div id="leftTbl" style="float:left; display:block; height:138px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
															</div>  <!-- left table -->

															<div style="float:left"> <!-- right table -->
																<div id="rightTblHead" style="width:1090px; display:block; overflow:hidden; margin:0px;padding:0px;">
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="CHEAD" width="2330px" style="margin-right:20px">
																		<col width="027">
																		<col width="050">
																		<col width="060">
																		<col width="050">
																		<col width="050">
																		<col width="150">
																		<col width="340">
																		<col width="107">
																		<col width="107">
																		<col width="090">
																		<col width="115">
																		<col width="115">
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400">
																		<col width="200">
																</c:when>
															</c:choose>
																		<tr height="34">
																			<td id="CH0"  class="pClothBs" align="center"><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" onClick="sendServer('SelectCheckBox_C')" /></td>
																			<td id="CH1"  class="pClothBs" align="center">S/C#</td>
																			<td id="CH2"  class="pClothBs" align="center">ShipLn#</td>
																			<td id="CH3"  class="pClothBs" align="center">Line#</td>
																			<td id="CH4"  class="pClothBs" align="center">D/C</td>
																			<td id="CH5"  class="pClothBs" align="center">Accounting Class</td>
																			<td id="CH6"  class="pClothBs" align="center">Account Information</td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxRadioBtn_C == '0'}">
																			<td id="CH7"  class="pClothBs" align="center">Debit Amount
																				<br><ezf:inputText name="xxTotAmt_C1" ezfName="xxTotAmt_C1" value="100.0" otherAttr=" size=\"13\" maxlength=\"20\" style=\"font-size:9px;height:12px;margin-top:-17px;\""/>
																			</td>
																			<td id="CH8"  class="pClothBs" align="center">Credit Amount
																				<br><ezf:inputText name="xxTotAmt_C2" ezfName="xxTotAmt_C2" value="100.0" otherAttr=" size=\"13\" maxlength=\"20\" style=\"font-size:9px;height:12px;margin-top:-17px;\""/>
																			</td>
																</c:when>
																<c:when test="${pageScope._ezddatabean.xxRadioBtn_C == '1'}">
																			<td id="CH7"  class="pClothBs" align="center">Debit Amount</td>
																			<td id="CH8"  class="pClothBs" align="center">Credit Amount</td>
																</c:when>
															</c:choose>
																			<td id="CH9"  class="pClothBs" align="center">Percentage(%)</td>
																			<td id="CH10" class="pClothBs" align="center">GL Date</td>
																			<td id="CH11" class="pClothBs" align="center">GL Posting Date</td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																			<td id="CH12" class="pClothBs" align="center">Error Message</td>
																			<td id="CH13" class="pClothBs" align="center">Error Val</td>
																</c:when>
															</c:choose>
																		</tr>
																	</table>
																</div><!-- rightTblHead-->

																<div id="rightTbl" style="width:1106px; height:155px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="C" width="2330px" >
																		<col width="027" align="center">
																		<col width="050" align="center">
																		<col width="060" align="center">
																		<col width="050" align="center">
																		<col width="050" align="center">
																		<col width="150" align="center">
																		<col width="340" align="center">
																		<col width="107" align="center">
																		<col width="107" align="center">
																		<col width="090" align="center">
																		<col width="115" align="center">
																		<col width="115" align="center">
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400" align="center">
																		<col width="200" align="center">
																</c:when>
															</c:choose>
																<ezf:row ezfHyo="C">
																	<tr id="C_rightTBLRow#{EZF_ROW_NUMBER}" height="24">
																		<td><ezf:inputCheckBox name="xxChkBox_C1" ezfName="xxChkBox_C1" value="Y" ezfHyo="C" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_C1#{EZF_ROW_NUMBER}\""/></td>
																		<td>
																			<ezf:inputText name="xxLineNum_C1" ezfName="xxLineNum_C1" value="00001" ezfHyo="C" ezfArrayNo="0" otherEvent1="OnBlur_SalesCreditLineNum_C" otherAttr=" size=\"5\" maxlength=\"5\" ezffocusout=\"OnBlur_SalesCreditLineNum_C\""/>
																		</td>
																		<td><ezf:inputText name="invBolLineNum_C1" ezfName="invBolLineNum_C1" value="123" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
																		<td><ezf:inputText name="invLineNum_C1" ezfName="invLineNum_C1" value="12345" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"5\" maxlength=\"5\""/></td>
																		<td>
																			<ezf:select name="drCrTpCd_C1" ezfName="drCrTpCd_C1" ezfHyo="C" ezfArrayNo="0" ezfCodeName="drCrTpCd_CC" ezfDispName="drCrTpCd_CD" otherEvent1=" onchange=\"sendServerForPreferredView('Select_DebitCreditType', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:40px;\""/>
																		</td>
																		<td>
																			<ezf:select name="ajeInvAcctClsCd_C1" ezfName="ajeInvAcctClsCd_C1" ezfHyo="C" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ajeInvAcctClsCd_CC" ezfDispName="ajeInvAcctClsNm_CD" otherEvent1=" onchange=\"sendServerForPreferredView('Select_AccountingClass', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:140px;\""/>
																		</td>
																		<td>
																			<ezf:inputText name="xxScrItem61Txt_C1" ezfName="xxScrItem61Txt_C1" value="100.0" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"43\" maxlength=\"50\" ezftoupper=\"\""/>
																			<ezf:inputButton name="GlCombnSearch_C" value="A" ezfHyo="C" ezfArrayNo="0" />
																		</td>
																		<td><ezf:inputText name="jrnlDealAmt_C1" ezfName="jrnlDealAmt_C1" value="100.0" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\""/></td>
																		<td><ezf:inputText name="jrnlDealAmt_C2" ezfName="jrnlDealAmt_C2" value="100.0" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"13\" maxlength=\"20\""/></td>
																		<td><ezf:inputText name="ajeInvAcctDistPct_C1" ezfName="ajeInvAcctDistPct_C1" value="100.0" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"6\" maxlength=\"6\""/></td>
																		<td>
																			<ezf:inputText name="glDt_C1" ezfName="glDt_C1" value="2015/12/31" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('glDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
																		<td>
																			<ezf:inputText name="jrnlCratDt_C1" ezfName="jrnlCratDt_C1" value="2015/12/31" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('jrnlCratDt_C1', 4, '{EZF_ROW_NUMBER}');" >
																		</td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<td><ezf:inputText name="invErrMsgTxt_C1" ezfName="invErrMsgTxt_C1" value="213" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"56\" maxlength=\"1000\""/></td>
																		<td><ezf:inputText name="invldValTxt_C1" ezfName="invldValTxt_C1" value="213" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
																</c:when>
															</c:choose>
																		<td class="pAuditInfo">
								                                            <ezf:inputHidden name="xxRecHistCratTs_C" ezfName="xxRecHistCratTs_C" ezfHyo="C" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistCratByNm_C" ezfName="xxRecHistCratByNm_C" ezfHyo="C" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistUpdTs_C" ezfName="xxRecHistUpdTs_C" ezfHyo="C" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistUpdByNm_C" ezfName="xxRecHistUpdByNm_C" ezfHyo="C" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistTblNm_C" ezfName="xxRecHistTblNm_C" ezfHyo="C" ezfArrayNo="0" />
								                                        </td>
																	</tr>
																	</ezf:row>
																	</table>
																</div><!-- rightTbl-->
															</div><!-- right table-->
														</td>
													</tr>
												</table>
											</div><!-- parentBoxC -->

										</td>
									</tr>
								</table>
							</div>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxC", "CHEAD", "C", 4, false, false);
							</script>
						</c:when>
						
						<%-- BOL TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_BOL'}">
							<script type="text/javascript">
								document.getElementById( "Line"       ).className = "pTab_OFF";
								document.getElementById( "SalesCredit").className = "pTab_OFF";
								document.getElementById( "Accounting" ).className = "pTab_OFF";
								document.getElementById( "Shipping"   ).className = "pTab_ON";
								document.getElementById( "MoreInfo"   ).className = "pTab_OFF";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="100%">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0" width="1090px" style="margin-left:4px;">
												<col width="200px" align="left">
												<col width="400px" align="center">
												<col width="200px" align="right">
												<tr>
													<td>
														<ezf:inputHidden name="xxComnColOrdTxt" ezfName="xxComnColOrdTxt" otherAttr=" size=\"0\" id=\"xxComnColOrdTxt\""/>
													</td>
													<td>
<%--Pending
														<jsp:include page="../tablePagenation/S21TablePagenationWithJump.jsp">
															<jsp:param name="beanId"          value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"         value="D" />
															<jsp:param name="ShowingFrom"     value="xxPageShowFromNum_D" />
															<jsp:param name="ShowingTo"       value="xxPageShowToNum_D" />
															<jsp:param name="ShowingOf"       value="xxPageShowOfNum_D" />
															<jsp:param name="ShowingCurrent"  value="xxPageShowCurNum_D" />
															<jsp:param name="ShowingTotal"    value="xxPageShowTotNum_D" />
															<jsp:param name="ViewMode"        value="FULL" />
														</jsp:include>
--%>
													</td>
													<td>
														<ezf:inputButton name="AddLine_D" value="Add Line" htmlClass="pBtn6" />
														<ezf:inputText name="xxNum_D" ezfName="xxNum_D" value="12345" otherAttr=" size=\"5\" maxlength=\"5\""/>
														<ezf:inputButton name="DeleteLine_D" value="Delete Line" htmlClass="pBtn6" />
													</td>
												</tr>
											</table>

											<div id="parentBoxD">
												<table>
													<tr>
														<td>
															<div style="float:left; display:block"> <!-- left table -->
																<div id="leftTblHead" style="display:block;"></div>
																<div id="leftTbl" style="float:left; display:block; height:148px; overflow:hidden; margin:0px; padding:0px; padding-bottom: 20px"></div>
															</div>  <!-- left table -->

															<div style="float:left"> <!-- right table -->
																<div id="rightTblHead" style="width:1090px; display:block; overflow:hidden; margin:0px;padding:0px;">
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0"  id="DHEAD" width="2330px" style="margin-right:20px">
																		<col width="030">
																		<col width="060">
																		<col width="100">
																		<col width="120">
																		<col width="150">
																		<col width="120">
																		<col width="300">
																		<col width="300">
																		<col width="150">
																		<col width="040">
																		<col width="100">
																		<col width="200">
																		<col width="120">
																		<col width="080">
																		<col width="130">
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400">
																		<col width="200">
																</c:when>
															</c:choose>
																		<tr height="24">
																			<td id="DH0"  class="pClothBs" align="center"><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" onClick="sendServer('SelectCheckBox_D')" /></td>
																			<td id="DH1"  class="pClothBs" align="center">ShipLn#</td>
																			<td id="DH2"  class="pClothBs" align="center">W/H</td>
																			<td id="DH3"  class="pClothBs" align="center">Ship To Account#</td>
																			<td id="DH4"  class="pClothBs" align="center">Ship To Cust Name</td>
																			<td id="DH5"  class="pClothBs" align="center">Ship To Location</td>
																			<td id="DH6"  class="pClothBs" align="center">Addresss1</td>
																			<td id="DH7" class="pClothBs" align="center">Addresss2</td>
																			<td id="DH8" class="pClothBs" align="center">City</td>
																			<td id="DH9" class="pClothBs" align="center">State</td>
																			<td id="DH10" class="pClothBs" align="center">Postal Code</td>
																			<td id="DH11" class="pClothBs" align="center">Ship To Contact</td>
																			<td id="DH12" class="pClothBs" align="center">Ship Date</td>
																			<td id="DH13" class="pClothBs" align="center">S/O Number</td>
																			<td id="DH14" class="pClothBs" align="center">BOL Number</td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																			<td id="DH15" class="pClothBs" align="center">Error Message</td>
																			<td id="DH16" class="pClothBs" align="center">Error Val</td>
																</c:when>
															</c:choose>
																		</tr>
																	</table>
																</div><!-- rightTblHead-->

																<div id="rightTbl" style="width:1106px; height:165px; display:block; overflow:scroll; margin:0px; padding:0px;" >
																	<table style="table-layout:fixed;" border="1" cellpadding="0" cellspacing="0" id="D" width="2330px" >
																		<col width="030" align="center">
																		<col width="060" align="center">
																		<col width="100" align="center">
																		<col width="120" align="center">
																		<col width="150" align="center">
																		<col width="120" align="center">
																		<col width="300" align="center">
																		<col width="300" align="center">
																		<col width="150" align="center">
																		<col width="040" align="center">
																		<col width="100" align="center">
																		<col width="200" align="center">
																		<col width="120" align="center">
																		<col width="080" align="center">
																		<col width="130" align="center">
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<col width="400" align="center">
																		<col width="200" align="center">
																</c:when>
															</c:choose>
																<ezf:row ezfHyo="D">
																	<tr id="D_rightTBLRow#{EZF_ROW_NUMBER}" height="24">
																		<td><ezf:inputCheckBox name="xxChkBox_D1" ezfName="xxChkBox_D1" value="Y" ezfHyo="D" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_D1#{EZF_ROW_NUMBER}\""/></td>
																		<td><ezf:inputText name="invBolLineNum_D1" ezfName="invBolLineNum_D1" value="123" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\""/></td>
																		<td>
																			<ezf:inputButton name="WarehouseSearch_D" value="W" ezfHyo="D" ezfArrayNo="0" />
																			<ezf:inputText name="shipFromInvtyLocCd_D1" ezfName="shipFromInvtyLocCd_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"shipFromInvtyLocCd_D1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																		</td>
																		<td>
																			<ezf:inputButton name="ShipToSearch_D" value="S" ezfHyo="D" ezfArrayNo="0" />
																			<ezf:inputText name="shipToCustAcctCd_D1" ezfName="shipToCustAcctCd_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"shipToCustAcctCd_D1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																			<ezf:inputButton name="ShipToName_D" value=">>" ezfHyo="D" ezfArrayNo="0" />
																		</td>
																		<td><ezf:inputText name="shipToCustAcctNm_D1" ezfName="shipToCustAcctNm_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"360\""/></td>
																		<td>
																			<ezf:inputButton name="ShipToLocSearch_D" value="L" ezfHyo="D" ezfArrayNo="0" />
																			<ezf:inputText name="locNum_D1" ezfName="locNum_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" id=\"locNum_D1#{EZF_ROW_NUMBER}\" ezftoupper=\"\""/>
																			<ezf:inputButton name="ShipToLocName_D" value=">>" ezfHyo="D" ezfArrayNo="0" />
																		</td>
																		<td><ezf:inputText name="shipToFirstLineAddr_D1" ezfName="shipToFirstLineAddr_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"60\""/></td>
																		<td><ezf:inputText name="shipToScdLineAddr_D1" ezfName="shipToScdLineAddr_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"40\" maxlength=\"60\""/></td>
																		<td><ezf:inputText name="shipToCtyAddr_D1" ezfName="shipToCtyAddr_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"25\""/></td>
																		<td><ezf:inputText name="shipToStCd_D1" ezfName="shipToStCd_D1" value="NY" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"2\" maxlength=\"2\""/></td>
																		<td><ezf:inputText name="shipToPostCd_D1" ezfName="shipToPostCd_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"15\""/></td>
																		<td>
																			<ezf:inputButton name="ShipToContactSearch_D" value="C" ezfHyo="D" ezfArrayNo="0" />
																			<ezf:inputText name="xxPsnNm_D1" ezfName="xxPsnNm_D1" value="AAAAAA" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"62\" id=\"xxPsnNm_D1#{EZF_ROW_NUMBER}\""/>
																		</td>
																		<td>
																			<ezf:inputText name="shipDt_D1" ezfName="shipDt_D1" value="2015/12/31" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\""/>
																			<img src="./img/calendar.gif" class="pCalendar" onclick="calendar('shipDt_D1', 4,'{EZF_ROW_NUMBER}');" >
																		</td>
																		<td><ezf:inputText name="soNum_D1" ezfName="soNum_D1" value="12345" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" ezftoupper=\"\""/></td>
																		<td><ezf:inputText name="bolNum_D1" ezfName="bolNum_D1" value="12345" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"16\" maxlength=\"30\" ezftoupper=\"\""/></td>
															<c:choose>
																<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
																		<td><ezf:inputText name="invErrMsgTxt_D1" ezfName="invErrMsgTxt_D1" value="213" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"56\" maxlength=\"1000\""/></td>
																		<td><ezf:inputText name="invldValTxt_D1" ezfName="invldValTxt_D1" value="213" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"27\" maxlength=\"50\""/></td>
																</c:when>
															</c:choose>
																		<td class="pAuditInfo">
								                                            <ezf:inputHidden name="xxRecHistCratTs_D" ezfName="xxRecHistCratTs_D" ezfHyo="D" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistCratByNm_D" ezfName="xxRecHistCratByNm_D" ezfHyo="D" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistUpdTs_D" ezfName="xxRecHistUpdTs_D" ezfHyo="D" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistUpdByNm_D" ezfName="xxRecHistUpdByNm_D" ezfHyo="D" ezfArrayNo="0" />
								                                            <ezf:inputHidden name="xxRecHistTblNm_D" ezfName="xxRecHistTblNm_D" ezfHyo="D" ezfArrayNo="0" />
								                                        </td>
																	</tr>
																		</ezf:row>
																	</table>
																</div><!-- rightTbl-->
															</div><!-- right table-->
														</td>
													</tr>
												</table>
											</div><!-- parentBoxD -->

										</td>
									</tr>
								</table>
							</div>
							<script type="text/javascript" defer>
							    S21TableUI.initialize("parentBoxD", "DHEAD", "D", 2, false, false);
							</script>
						</c:when>

						<%-- More TAB --%>
						<c:when test="${pageScope._ezddatabean.xxDplyTab == 'TAB_MoreInfo'}">
							<script type="text/javascript">
								document.getElementById( "Line"       ).className = "pTab_OFF";
								document.getElementById( "SalesCredit").className = "pTab_OFF";
								document.getElementById( "Accounting" ).className = "pTab_OFF";
								document.getElementById( "Shipping"   ).className = "pTab_OFF";
								document.getElementById( "MoreInfo"   ).className = "pTab_ON";
							</script>
							<%-- ###TAB - BODY --%>	
							<div class="pTab_BODY_In">
								<table width="100%">
									<tr>
										<td>
											<table border="0" cellpadding="0" cellspacing="0">
												<col width="350" align="left">
												<col width="300" align="left">
												<col width="450" align="left">
												<tr height="211">
													<td valign="top">
														<table border="0" cellpadding="0" cellspacing="0" width="700" rules="none" style="float:left;">
															<col width="050">
															<col width="100">
															<col width="200">
															<col width="100">
															<col width="200">
															<tr>
																<td></td>
																<td class="stab">Print Status</td>
																<td>
																	<ezf:select name="invPrintStsCd_E1" ezfName="invPrintStsCd_E1" ezfCodeName="invPrintStsCd_EC" ezfDispName="xxScrItem30Txt_ED" otherAttr=" style=\"width:110px;\""/>
																	<ezf:select name="invPrintCratStsCd_E1" ezfName="invPrintCratStsCd_E1" ezfCodeName="invPrintCratStsCd_EC" ezfDispName="xxScrItem10Txt_ED" otherAttr=" style=\"width:90px;\""/>
																</td>
																<td class="stab">Contract Modifier</td>
																<td><ezf:inputText name="contrRnwTotCnt_E1" ezfName="contrRnwTotCnt_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Printing Method</td>
																<td>
																	<ezf:select name="invProcTpCd_E1" ezfName="invProcTpCd_E1" ezfBlank="1" ezfCodeName="invProcTpCd_EC" ezfDispName="invProcTpNm_ED" otherAttr=" style=\"width:110px;\""/>
																</td>
																<td class="stab">Contract Type</td>
																<td>
																	<ezf:select name="dsContrCatgCd_E1" ezfName="dsContrCatgCd_E1" ezfBlank="1" ezfCodeName="dsContrCatgCd_EC" ezfDispName="dsContrCatgDescTxt_ED" otherAttr=" style=\"width:110px;\""/>
																</td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Date Print</td>
																<td><ezf:inputText name="invPrintProcDt_E1" ezfName="invPrintProcDt_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																<td class="stab">Order Category</td>
																<td>
																	<ezf:select name="dsOrdCatgCd_E1" ezfName="dsOrdCatgCd_E1" ezfBlank="1" ezfCodeName="dsOrdCatgCd_EC" ezfDispName="dsOrdCatgNm_ED" otherAttr=" style=\"width:110px;\""/>
																</td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Status</td>
																<td>
																	<ezf:select name="arCashApplyStsCd_E1" ezfName="arCashApplyStsCd_E1" ezfBlank="1" ezfCodeName="arCashApplyStsCd_EC" ezfDispName="arCashApplyStsNm_ED" otherAttr=" style=\"width:110px;\""/>
																</td>
																<td class="stab">Original Invoice</td>
																<td><ezf:inputText name="origInvNum_E1" ezfName="origInvNum_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Dispute Amount</td>
																<td><ezf:inputText name="dealCltDsptAmt_E1" ezfName="dealCltDsptAmt_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																<td class="stab">Credit Memo</td>
																<td><ezf:inputText name="crArInvNum_E1" ezfName="crArInvNum_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Dispute Date</td>
																<td><ezf:inputText name="cltDsptDt_E1" ezfName="cltDsptDt_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																<td class="stab">Prepayment Amount</td>
																<td><ezf:inputText name="prePmtAmt_E1" ezfName="prePmtAmt_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab" >GL Date</td>
																<td>
																	<ezf:inputText name="acctDt_H1" ezfName="acctDt_H1" value="04/25/2015" otherAttr=" size=\"10\" maxlength=\"10\""/>
																	<img src="img/calendar.gif" class="pCalendar" onclick="calendar('acctDt_H1', 4);" >
																</td>
															</tr>
															<tr>
																<td></td>
																<td class="stab" >Creation Date</td>
																<td><ezf:inputText name="xxDtTm_IN" ezfName="xxDtTm_IN" value="09/05/2016 23:01:09" otherAttr=" size=\"20\" maxlength=\"23\""/></td>
																<td class="stab" >Created By</td>
																<td><ezf:inputText name="ezUserID_H1" ezfName="ezUserID_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"15\" maxlength=\"16\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab" >Update Date</td>
																<td><ezf:inputText name="xxDtTm_UP" ezfName="xxDtTm_UP" value="09/05/2016 23:01:09" otherAttr=" size=\"20\" maxlength=\"23\""/></td>
																<td class="stab" >Updated By</td>
																<td><ezf:inputText name="ezUpUserID_H1" ezfName="ezUpUserID_H1" value="XXXXXXXXX1XXX" otherAttr=" size=\"15\" maxlength=\"16\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Comments</td>
																<td colspan="3"><ezf:inputText name="xxInvMemoTxt_E1" ezfName="xxInvMemoTxt_E1" value="12345" otherAttr=" size=\"61\" maxlength=\"260\""/></td>
															</tr>
															<tr>
																<td></td>
																<td class="stab">Cr & Rebill</td>
																<td colspan="3"><ezf:inputText name="prmCmntTxt_E1" ezfName="prmCmntTxt_E1" value="Cr & Rebill Comments" otherAttr=" size=\"61\" maxlength=\"1000\""/></td>
															</tr>
														</table>
													</td>
													<td valign="top">
														<table border="0" cellpadding="0" cellspacing="0" width="400" rules="none" style="float:left;">
															<col width="100">
															<col width="300">
															<tr>
																<!-- START 2016/09/15 S.Yoshidaa [QC#13956,MOD] -->
																<!--
																<td class="stab">Payment Method</td>
																<td>
																	<select style="width:150px;" name="dsPmtMethCd_E1" ezfname="dsPmtMethCd_E1" ezflist="dsPmtMethCd_EC,dsPmtMethNm_ED,99, ,blank" onChange="sendServer('Select_PaymentMethod')">
																		<option>INVOICE</option>
																		<option>CREDIT CARD</option>
																	</select>
																	<input type="button" class="pBtn6" value="Credit Card"     name="CreditCard"  onclick="sendServer(this)" >
																</td>
																-->
																<td/><td/>
																<!-- END   2016/09/15 S.Yoshidaa [QC#13956,MOD] -->
															</tr>
															<tr>
																<td>&nbsp;</td>
															</td>
															<tr>
																<td colspan="2">
																	<table border="0" cellpadding="0" cellspacing="0" height="150" width="250" rules="none"  style="float:left;padding: 5px; margin-bottom: 5px; border: 1px solid #333333;">
																		<tr>
																			<td valign="top">
																				<table border="0" cellpadding="0" cellspacing="0">
																					<col width="100">
																					<col width="150">
																					<tr>
																						<td class="stab">Card Type</td>
																						<td><ezf:inputText name="crCardTpCd_E1" ezfName="crCardTpCd_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																					</tr>
																					<tr>
																						<td class="stab">Name</td>
																						<td><ezf:inputText name="crCardHldNm_E1" ezfName="crCardHldNm_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																					</tr>	
																					<tr>
																						<td class="stab">Card Number</td>
																						<td><ezf:inputText name="xxScrItem19Txt_E1" ezfName="xxScrItem19Txt_E1" value="****12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																					</tr>
																					<tr>
																						<td class="stab">Exp. Date</td>
																						<td><ezf:inputText name="crCardExprYrMth_E1" ezfName="crCardExprYrMth_E1" value="12345" otherAttr=" size=\"15\" maxlength=\"15\""/></td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
													<c:choose>
														<c:when test="${pageScope._ezddatabean.xxDplyTab_H1 == 'VisibilityError'}">
															<tr>
																<td class="stab" >Error Val</td>
																<td colspan="3">
																	<ezf:inputText name="invldValTxt_H4" ezfName="invldValTxt_H4" value="213" otherAttr=" size=\"20\" maxlength=\"50\""/>
																</td>
															</tr>
															<tr>
																<td class="stab" >Error Message</td>
																<td colspan="3">
																	<ezf:inputText name="invErrMsgTxt_H4" ezfName="invErrMsgTxt_H4" value="213" otherAttr=" size=\"40\" maxlength=\"1000\""/>
																</td>
															</tr>
														</c:when>
													</c:choose>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</c:when>
					</c:choose>
					<table width="1102" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right">
								<ezf:inputButton name="PrintedInvoice" value="Printed Invoice" htmlClass="pBtn8" />
								<ezf:inputButton name="Installments" value="Installments" htmlClass="pBtn8" />
								<ezf:inputButton name="MaterDetailes" value="Meter Details" htmlClass="pBtn8" />
								<ezf:inputButton name="CreditRebill" value="Credit Rebil" htmlClass="pBtn8" />
								<ezf:inputButton name="AdjustInvoice" value="Adjust Invoices" htmlClass="pBtn8" />
								<ezf:inputButton name="Activity" value="Activity" htmlClass="pBtn8" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- 13222 --%>
<ezf:inputHidden name="xxListNum_LX" ezfName="xxListNum_LX" value="XXX" otherAttr=" size=\"5\" maxlength=\"5\""/>
<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="XXX" otherAttr=" size=\"5\" maxlength=\"5\""/>

<script type="text/javascript">

	function getScrollFunctionC() {
		var rightTBL    = document.getElementById('rightTBLC');
		var xxListNumLX = document.getElementById('xxListNum_LX');
		var xxListNumLY = document.getElementById('xxListNum_LY');
		xxListNumLX.value = rightTBL.scrollLeft;
		xxListNumLY.value = rightTBL.scrollTop;
	}
	
	function setScrollFunctionC() {
		var xxListNumLX = document.getElementById('xxListNum_LX').value;
		var xxListNumLY = document.getElementById('xxListNum_LY').value;

		var rightTBL = document.getElementById('rightTBLC');
		rightTBL.scrollLeft = xxListNumLX;
		rightTBL.scrollTop  = xxListNumLY;

		var leftTBL = document.getElementById('LeftTBLC');
		leftTBL.scrollTop  = xxListNumLY;

		var rightTopTBL = document.getElementById('rightTopTBLC');
		rightTopTBL.scrollLeft = xxListNumLX;
	}
	
	// Accounting tab
	if (document.getElementById('Accounting').className.indexOf('ON') >= 0) {
		setScrollFunctionC();
	}
	
</script>

<%-- **** Task specific area ends here **** --%>
