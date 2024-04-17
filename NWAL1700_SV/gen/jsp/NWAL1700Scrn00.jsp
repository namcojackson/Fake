<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20230425154256 --%>
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
			<input type="hidden" name="pageID" value="NWAL1700Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Process Flow Controls Maintenance">

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
													<li title="Role Maintenance" class="pTab_ON"><a href="#">Rle Mnt</a></li>
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
					
<%--------------------------------%>
<%-- Header						--%>
<%--------------------------------%>
							<div id="mainTBL"  style="overflow-x:hidden; overflow-y:scroll; height:570; width:1130; float:left;word-break: break-all;"  onscroll="setScrollPosition();" >
								
								
								<table border="0" cellpadding="0" cellspacing="0" height="80">
									<tr>
										<td valign="top">
			                            </td>
										<td valign="top" width="">
											<table width="" cellpadding="0" border="0" >
												<col align="left" width="5">
												<col align="left" width="80">
												<col align="left" width="250">
												<col align="left" width="120">
												<col align="left" width="250">
												<col align="left" width="100">
												<col align="left" width="5">
												<col align="left" width="100">
												<col align="left" width="100">
												<col align="left" width="10">
												<tr>
													<td class="stab">&nbsp;</td>
													<td class="stab">Category</td>
													<td>
														<ezf:select name="dsOrdCatgCd" ezfName="dsOrdCatgCd" ezfBlank="1" ezfCodeName="dsOrdCatgCd_P" ezfDispName="dsOrdCatgDescTxt_P" otherAttr=" style=\"width:235px\""/>
													</td>
													<td class="stab">Sub Reason </td>
													<td>
													    <ezf:select name="dsOrdRsnGrpCd" ezfName="dsOrdRsnGrpCd" ezfBlank="1" ezfCodeName="dsOrdRsnGrpCd_P" ezfDispName="dsOrdRsnGrpDescTxt_P" otherAttr=" style=\"width:235px\""/>
													</td>
													<td class="stab">
													    &nbsp;
													</td>
													<td class="stab">
														&nbsp;
													</td>
													<td class="stab">Start Date</td>
													<td>
														<ezf:inputText name="effFromDt" ezfName="effFromDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt', 4);" >
													</td>
													<td class="stab">&nbsp;</td>
												</tr>
												<tr>
													<td class="stab">&nbsp;</td>
													<td class="stab">Reason</td>
													<td ><ezf:inputText name="dsOrdTpNm" ezfName="dsOrdTpNm" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td class="stab">Description </td>
													<td colspan="3">
													    <ezf:inputText name="dsOrdTpNoteTxt" ezfName="dsOrdTpNoteTxt" value="123456789012345678901234567890123456789012345678901234567890" otherAttr=" size=\"50\" maxlength=\"50\" ezftoupper=\"\""/>
													</td>
													
													
													<td class="stab">
														End Date
													</td>
													<td>
														<ezf:inputText name="effThruDt" ezfName="effThruDt" value="12/31/9999" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													    <img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt', 4);" >
													</td>
													<td class="stab">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td class="stab">&nbsp;</td>
													<td class="stab">Workflow</td>
													<td>
														<ezf:select name="ordProcTpCd" ezfName="ordProcTpCd" ezfBlank="1" ezfCodeName="ordProcTpCd_P" ezfDispName="ordProcTpDescTxt_P" otherAttr=" style=\"width:235px\""/>
													</td>
													<td class="stab">Line of Business</td>
													<td >
													     <ezf:select name="lineBizTpCd" ezfName="lineBizTpCd" ezfBlank="1" ezfCodeName="lineBizTpCd_P" ezfDispName="lineBizTpDescTxt_P" otherAttr=" style=\"width:235px\""/>
													</td>
													<td class="stab">
													   &nbsp;
													</td>
													<td class="stab">
														&nbsp;
													</td>
													<td class="stab">
														Active
													</td>
													<td>
														<ezf:inputCheckBox name="actvFlg" ezfName="actvFlg" value="Y" />
													</td>
													<td class="stab">
														&nbsp;
													</td>
												</tr>
												
											</table>
										</td>
									</tr>
								</table>
								<br>
								<table style="width:1200;height:30;fontcolor:#FFFFFF">
									<tr>
										<td>
										</td>
									</tr>
									<tr style="background:#999999;width:1200">
										<td class="stab">
											<span style="color: #FFFFFF">Workflow Controls</span>
										</td>
									</tr>
									<tr>
										<td>
										</td>
									</tr>
								</table>
								
<%--------------------------------%>
<%-- Workflow Controls			--%>
<%--------------------------------%>
								<table>
									<col width="5">
									<col width="200">
									<col width="50">
									<col width="50">
									<col width="200">
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    &nbsp;
										</td>
										<td class="stab">
											Yes
										</td>
										<td class="stab">
											No
										</td>
										<td class="stab">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Validation Approval
										</td>
										<td>
											<ezf:inputRadio name="vldApvlNodeUsgFlg" ezfName="vldApvlNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="vldApvlNodeUsgFlg" ezfName="vldApvlNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="vldApvlNodePrflCd" ezfName="vldApvlNodePrflCd" ezfBlank="1" ezfCodeName="vldApvlNodePrflCd_PV" ezfDispName="ordProcNodePrflNm_PV" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    DI Check Required
										</td>
										<td>
											<ezf:inputRadio name="diChkNodeUsgFlg" ezfName="diChkNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="diChkNodeUsgFlg" ezfName="diChkNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="diChkNodePrflCd" ezfName="diChkNodePrflCd" ezfBlank="1" ezfCodeName="diChkNodePrflCd_PD" ezfDispName="ordProcNodePrflNm_PD" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Profitability Approval
										</td>
										<td>
											<ezf:inputRadio name="prftApvlNodeUsgFlg" ezfName="prftApvlNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="prftApvlNodeUsgFlg" ezfName="prftApvlNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="prftApvlNodePrflCd" ezfName="prftApvlNodePrflCd" ezfBlank="1" ezfCodeName="prftApvlNodePrflCd_PP" ezfDispName="ordProcNodePrflNm_PP" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Credit Approval
										</td>
										<td>
											<ezf:inputRadio name="crApvlNodeUsgFlg" ezfName="crApvlNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="crApvlNodeUsgFlg" ezfName="crApvlNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="crApvlNodePrflCd" ezfName="crApvlNodePrflCd" ezfBlank="1" ezfCodeName="crApvlNodePrflCd_PC" ezfDispName="ordProcNodePrflNm_PC" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Fixed Asset
										</td>
										<td>
											<ezf:inputRadio name="assetNodeUsgFlg" ezfName="assetNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="assetNodeUsgFlg" ezfName="assetNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="assetNodePrflCd" ezfName="assetNodePrflCd" ezfBlank="1" ezfCodeName="assetNodePrflCd_PF" ezfDispName="ordProcNodePrflNm_PF" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Out of Warehouse
										</td>
										<td>
											<ezf:inputRadio name="outOfWhNodeUsgFlg" ezfName="outOfWhNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="outOfWhNodeUsgFlg" ezfName="outOfWhNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="outOfWhNodePrflCd" ezfName="outOfWhNodePrflCd" ezfBlank="1" ezfCodeName="outOfWhNodePrflCd_PO" ezfDispName="ordProcNodePrflNm_PO" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Supply Abuse
										</td>
										<td>
											<ezf:inputRadio name="splyAbuseNodeUsgFlg" ezfName="splyAbuseNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="splyAbuseNodeUsgFlg" ezfName="splyAbuseNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="splyAbuseNodePrflCd" ezfName="splyAbuseNodePrflCd" ezfBlank="1" ezfCodeName="splyAbuseNodePrflCd_PS" ezfDispName="ordProcNodePrflNm_PS" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
										    Manual Price Approval
										</td>
										<td>
											<ezf:inputRadio name="manPrcNodeUsgFlg" ezfName="manPrcNodeUsgFlg" value="Y" />
										</td>
										<td>
											<ezf:inputRadio name="manPrcNodeUsgFlg" ezfName="manPrcNodeUsgFlg" value="N" />
										</td>
										<td>
											<ezf:select name="manPrcNodePrflCd" ezfName="manPrcNodePrflCd" ezfBlank="1" ezfCodeName="manPrcNodePrflCd_PM" ezfDispName="ordProcNodePrflNm_PM" otherAttr=" style=\"width:235px\""/>
										</td>
									</tr>
								</table>
								<br>
								<table style="width:1200;height:30;fontcolor:#FFFFFF">
									<tr>
										<td>
										</td>
									</tr>
									<tr style="background:#999999;width:1200">
										<td class="stab">
											<span style="color: #FFFFFF">Processing Rules</span>
										</td>
									</tr>
									<tr>
										<td>
										</td>
									</tr>
								</table>
<%--------------------------------%>
<%-- Processing Rules			--%>
<%--------------------------------%>
								
								<fieldset class="stab">
									<legend>Accounting</legend>
									<table border="0" cellpadding="0" cellspacing="0">
										<col width="5">
										<col width="160">
										<col width="355">

										<col width="190">
										<col width="190">
										<col width="190">

										
										<tr>
											<td>
												&nbsp;
											</td>
											<td class="stab">
												<ezf:anchor name="xxLinkAncr_AR" ezfName="xxLinkAncr_AR" ezfEmulateBtn="1" ezfGuard="OpenWin_ArTran" >
													AR Transaction Type
												</ezf:anchor>
											</td>
											<td colspan="3">
												<ezf:inputText name="dsInvTpCd" ezfName="dsInvTpCd" value="1234" otherAttr=" size=\"10\" maxlength=\"4\" ezftoupper=\"\""/>
												<ezf:inputButton name="Search_ARTran" value=">>" htmlClass="pBtn4" />
												<ezf:inputText name="dsInvTpNm" ezfName="dsInvTpNm" value="123456789012345678901234567890" otherAttr=" size=\"25\" maxlength=\"60\""/>
											</td>
											
											<td class="stab">
												Auto Cancel Order
											</td>
											<td>
												<ezf:inputCheckBox name="autoCancOrdFlg" ezfName="autoCancOrdFlg" value="Y" />
											</td>
										</tr>
									</table>
								</fieldset>
								<br>
								<fieldset class="stab">
								<legend>Defaults</legend>
								<table border="0" cellpadding="0" cellspacing="0" style="height:180;">
									<col width="5">
									<col width="160">
									<col width="120">
									<col width="10">
									<col width="150">
									<col width="190">
									<col width="50">
									<tr>
										<td>
											&nbsp;
										</td>
										<td class="stab">
											Price List
										</td>
										<td colspan="3">
											<ezf:inputText name="defPrcCatgCd" ezfName="defPrcCatgCd" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search_PriceList" value=">>" htmlClass="pBtn4" />
											<ezf:inputText name="prcCatgDescTxt_PR" ezfName="prcCatgDescTxt_PR" value="123456789012345678901234567890" otherAttr=" size=\"25\" maxlength=\"75\""/>
										</td>
										
										<td class="stab">
											Bill To Account #
										</td>
										<td>
											<ezf:inputText name="defBillToCustAcctCd" ezfName="defBillToCustAcctCd" value="12345678901234567890" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:inputButton name="Search_BillToAccount" value=">>" htmlClass="pBtn4" />
										</td>
										<td>
											<ezf:inputText name="dsAcctNm" ezfName="dsAcctNm" value="123456789012345678901234567890" otherAttr=" size=\"25\" maxlength=\"360\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											Service Price List
										</td>
										<td colspan="3">
											<ezf:inputText name="defMaintPrcCatgCd" ezfName="defMaintPrcCatgCd" value="1234567890" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
											<ezf:inputButton name="Search_ServicePriceList" value=">>" htmlClass="pBtn4" />
											<ezf:inputText name="prcCatgDescTxt_SP" ezfName="prcCatgDescTxt_SP" value="123456789012345678901234567890" otherAttr=" size=\"25\" maxlength=\"75\""/>
										</td>
										<td class="stab">
											Bill To Location
										</td>
										<td>
											<ezf:inputText name="defBillToCustCd" ezfName="defBillToCustCd" value="12345678901234567890" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:inputButton name="Search_BillToLocation" value=">>" htmlClass="pBtn4" />
										</td>
										<td>
											<ezf:inputText name="locDescTxt" ezfName="locDescTxt" value="123456789012345678901234567890" otherAttr=" size=\"25\" maxlength=\"60\""/>
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
										<td class="stab">
											Forecasting Code
										</td>
										<td colspan="2">
											<ezf:select name="revFcstCd" ezfName="revFcstCd" ezfBlank="1" ezfCodeName="revFcstCd_P" ezfDispName="revFcstDescTxt_P" otherAttr=" style=\"width:170px\""/>
										</td>
										
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											Install Type
										</td>
										<td colspan="2">
											<ezf:select name="defInstlTpCd" ezfName="defInstlTpCd" ezfBlank="1" ezfCodeName="defInstlTpCd_P" ezfDispName="svcIstlTpDescTxt_P" otherAttr=" style=\"width:170px\""/>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
										<td class="stab">
											Freight Terms
										</td>
										<td colspan="2">
											<ezf:select name="frtCondCd" ezfName="frtCondCd" ezfBlank="1" ezfCodeName="frtCondCd_P" ezfDispName="frtCondDescTxt_P" otherAttr=" style=\"width:170px\""/>
										</td>
										
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											Shipping Service Level
										</td>
										<td colspan="2">
											<ezf:select name="defShpgSvcLvlCd" ezfName="defShpgSvcLvlCd" ezfBlank="1" ezfCodeName="defShpgSvcLvlCd_P" ezfDispName="shpgSvcLvlDescTxt_P" otherAttr=" style=\"width:170px\""/>
										</td>
										<td class="stab">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											Invoice Print Style
										</td>
										<td colspan="2">
											<ezf:select name="invPrintStyleCd" ezfName="invPrintStyleCd" ezfBlank="1" ezfCodeName="invPrintStyleCd_P" ezfDispName="invPrintStyleDescTxt_P" otherAttr=" style=\"width:170px\""/>
										</td>
										
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											<ezf:anchor name="xxLinkAncr_CS" ezfName="xxLinkAncr_CS" ezfEmulateBtn="1" ezfGuard="OpenWin_Carrier" >
												Carrier Service Level
											</ezf:anchor>
										</td>
										<td>
											<ezf:inputText name="defCarrSvcLvlCd" ezfName="defCarrSvcLvlCd" value="12345678901234567890" otherAttr=" size=\"15\" maxlength=\"20\" ezftoupper=\"\""/>
										</td>
										<td>
											<ezf:inputButton name="Search_Carrier" value=">>" htmlClass="pBtn4" />
										</td>
										<td>
											<ezf:inputText name="carrSvcLvlDescTxt" ezfName="carrSvcLvlDescTxt" value="123456789012345678901234567890" otherAttr=" size=\"25\" maxlength=\"50\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											Territory Group
										</td>
										<td colspan="2">
											<ezf:inputText name="trtyGrpTpTxt" ezfName="trtyGrpTpTxt" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\""/>
										</td>
										
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											Drop Ship Available
										</td>
										<td>
											<ezf:inputCheckBox name="dropShipAvalFlg" ezfName="dropShipAvalFlg" value="Y" />
										</td>
										<td class="stab">
											&nbsp;
										</td>
										<td class="stab">
											&nbsp;
										</td>
									</tr>
									<tr>
                                        <td class="stab">
                                            &nbsp;
                                        </td>
                                        <td class="stab">
                                            Salesrep Defaulting
                                        </td>
                                        <td colspan="2">
                                            <ezf:select name="baseLocTxt" ezfName="baseLocTxt" ezfCodeName="baseLocTxt_PC" ezfDispName="baseLocTxt_PD" otherAttr=" style=\"width:170px\""/>
                                        </td>
                                        <td class="stab">
                                            &nbsp;
                                        </td>
                                        <td class="stab">
                                            &nbsp;
                                        </td>
                                        <td class="stab">
                                            &nbsp;
                                        </td>
                                        <td class="stab">
                                            &nbsp;
                                        </td>
                                        <td class="stab">
                                            &nbsp;
                                        </td>
                                    </tr>
								</table>
								</fieldset>
								<br>
								<table style="width:1200;height:30;fontcolor:#FFFFFF">
									<tr>
										<td>
										</td>
									</tr>
									<tr style="background:#999999;width:1200">
										<td class="stab">
											<span style="color: #FFFFFF">Line Category Assignment</span>
										</td>
									</tr>
									<tr>
										<td>
										</td>
									</tr>
								</table>
<%--------------------------------%>
<%-- Line Category Assignment	--%>
<%--------------------------------%>
								<table>
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											<ezf:inputButton name="Add_Line" value="+" htmlClass="pBtn3" />
										</td>
										<td>
											<ezf:inputButton name="Remove_Line" value="-" htmlClass="pBtn3" />
										</td>
									</tr>
								</table>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="top" width="1130">
											<%-- ### MEISAI - LEFT TBL - TOP --%>
											<div id="LeftTBL_Top" style="overflow-x:none; overflow-y:none; width:440; height:30; float:left;word-break: break-all;">
												<table border="1" cellpadding="1" cellspacing="0" width="440" height="30" style="table-layout: fixed;">
													<col align="center" width="30">
													<col align="center" width="30">
													<col align="center" width="120">
													<col align="center" width="120">
													<tr>
														<td class="pClothBs">&nbsp;</td>
														<td class="pClothBs">#</td>
														<td class="pClothBs">Line Category Name</td>
														<td class="pClothBs">Line Workflow</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - Right TBL - TOP --%>
											<div id="RightTBL_Top" style="overflow-x:hidden; overflow-y:hidden; width:650; height:30; float:left;word-break: break-all;" onscroll="synchroScrollLeft( this.id, new Array( 'RightTBL' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1000" height="30" style="table-layout: fixed;">
												    <col align="center" width="20">
												    <col align="center" width="30">
													<col align="center" width="100">
													<col align="center" width="40">
													<col align="center" width="40">
													<col align="center" width="40">
													<col align="center" width="50">
													<col align="center" width="20">
													
													<tr>
													    <td class="pClothBs">Primary</td>
													    <td class="pClothBs">RMA Primary</td>
														<td class="pClothBs">AJE Account Batch</td>
														<td class="pClothBs">Transaction Type</td>
														<td class="pClothBs">Start Date</td>
														<td class="pClothBs">End Date</td>
														<td class="pClothBs">Forecasting Code</td>
														<td class="pClothBs">Active</td>
													</tr>
												</table>
											</div>
											<%-- ### MEISAI - LEFT TBL - BOTTOM --%>
											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:125; width:440; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'RightTBL' ))" >
												<table border="1" cellpadding="1" cellspacing="0" width="440" id="A1" style="table-layout: fixed;">
													<col align="center" width="30">
													<col align="center" width="30">
													<col align="Left" width="120">
													<col align="Left" width="120">
													<ezf:row ezfHyo="A">
														<tr height="24">
															<td>
															    <ezf:inputCheckBox name="xxChkBox_A" ezfName="xxChkBox_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"xxChkBox_A#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
															    <ezf:inputText name="lineProcDfnSortNum_A" ezfName="lineProcDfnSortNum_A" value="999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\""/>
															</td>
															<td>
															    <ezf:select name="dsOrdLineCatgCd_A" ezfName="dsOrdLineCatgCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="dsOrdLineCatgCd_PL" ezfDispName="dsOrdLineCatgDescTxt_PL" otherEvent1=" onchange=\"sendServer('OnChange_LineCategory', '{EZF_ROW_NUMBER}')\"" otherAttr=" style=\"width:170px\""/>
															</td>
															<td>
															    <ezf:select name="ordProcTpCd_A" ezfName="ordProcTpCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="ordProcTpCd_PL" ezfDispName="ordProcTpDescTxt_PL" otherAttr=" id=\"ordProcTpCd_A#{EZF_ROW_NUMBER}\" style=\"width:170px\""/>
															</td>
														</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
											<%-- ### MEISAI - RIGHT TBL - BOTTOM --%>
											<div id="RightTBL" style="overflow-x:scroll; overflow-y:scroll; height:142; width:667; float:left;word-break: break-all;" onscroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ));synchroScrollLeft( this.id, new Array( 'RightTBL_Top' ));" >
												<table border="1" cellpadding="1" cellspacing="0" width="1000" id="A2" style="table-layout: fixed;">
												    <col align="center" width="20"><!--Primary -->
												    <col align="center" width="30"><!--RMA Primary -->
													<col align="Left" width="100"><!--AJE Account Batch -->
													<col align="Left" width="40"><!--Transaction Type -->
													<col align="Left" width="40"><!--Start Date -->
													<col align="Left" width="40"><!--End Date -->
													<col align="Left" width="50"><!--Credit/Rebill Approval Limit -->
													<col align="center" width="20"><!--Active -->
													
													
													<ezf:row ezfHyo="A">
														<tr height="24" id="rightTBL_A_tr_{EZF_ROW_NUMBER}">
														    <td>
															    <ezf:inputCheckBox name="primLineCatgFlg_A" ezfName="primLineCatgFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"primLineCatgFlg_A#{EZF_ROW_NUMBER}\""/>
															</td>
														    <td>
															    <ezf:inputCheckBox name="rmaPrimLineCatgFlg_A" ezfName="rmaPrimLineCatgFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"rmaPrimLineCatgFlg_A#{EZF_ROW_NUMBER}\""/>
															</td>
															<td>
																<ezf:inputText name="ajeAcctBatCd_A" ezfName="ajeAcctBatCd_A" value="12345678" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"3\" maxlength=\"3\" ezftoupper=\"\""/><ezf:inputButton name="Search_AJE" value=">>" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" /><ezf:inputText name="ajeAcctBatDescTxt_A" ezfName="ajeAcctBatDescTxt_A" value="123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"70\""/>
																<ezf:inputButton name="OpenWin_AJE" value="..." ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn1" />
															</td>
															<td>
																<ezf:inputText name="dsOrdLineDrctnNm_A" ezfName="dsOrdLineDrctnNm_A" value="WWWWWWWWWW" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/>
															</td>
															<td>
																<ezf:inputText name="effFromDt_A" ezfName="effFromDt_A" value="12/31/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													    		<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effFromDt_A', 4, {EZF_ROW_NUMBER});" >
																
															</td>
															<td>
																<ezf:inputText name="effThruDt_A" ezfName="effThruDt_A" value="12/31/9999" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/>
													    		<img src="img/calendar.gif" class="pCalendar" onclick="calendar('effThruDt_A', 4, {EZF_ROW_NUMBER});" >
															</td>
															<td>
																<ezf:select name="revFcstCd_A" ezfName="revFcstCd_A" ezfHyo="A" ezfArrayNo="0" ezfBlank="1" ezfCodeName="revFcstCd_P" ezfDispName="revFcstDescTxt_P" otherAttr=" style=\"width:140px\""/>
															</td>
															<td>
																<ezf:inputCheckBox name="actvFlg_A" ezfName="actvFlg_A" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"actvFlg_A#{EZF_ROW_NUMBER}\""/>
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
<%--------------------------------%>
<%-- Qualified Price List Type	--%>
<%--------------------------------%>
								<table>
									<tr>
										<td>
											<table style="width:450;height:30;fontcolor:#FFFFFF">
												<tr>
													<td>
													</td>
												</tr>
												<tr style="background:#999999;">
													<td class="stab">
														<span style="color: #FFFFFF">Qualified Price List Type</span>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
										<td style="width:40">
										</td>
										<td>
											<table style="width:600;height:30;fontcolor:#FFFFFF">
												<tr>
													<td>
													</td>
												</tr>
												<tr style="background:#999999;">
													<td class="stab">
														<span style="color: #FFFFFF">Publish to External Source</span>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								
								

								<table  width="1000" border="0" cellpadding="0" cellspacing="0">
									<col align="Left" width="450">
									<col align="Left" width="50">
									<col align="Left" width="450">
									<col align="Left" width="50">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="270">
												<col align="center" width="50">

												<tr height="24">
													<td class="pClothBs">Price List Type</td>
													<td class="pClothBs" align="center">&nbsp;</td>

												</tr>
											</table>
											<div style="width:340;overflow-y:scroll; height:122">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="B">
													<col align="left" width="270">
													<col align="center" width="50">
													<ezf:row ezfHyo="B">
													<tr height="24">
														<td><ezf:inputText name="prcListTpDescTxt_B" ezfName="prcListTpDescTxt_B" value="123456789012345678901234567890" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"75\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="270">
												<col align="center" width="50">

												<tr height="24">
													<td class="pClothBs">System Name</td>
													<td class="pClothBs" align="center">&nbsp;</td>
												</tr>
											</table>
											<div style="width:340;overflow-y:scroll; height:122">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="C">
													<col align="left" width="270">
													<col align="center" width="50">

													<ezf:row ezfHyo="C">
													<tr height="24">
														<td><ezf:inputText name="omXtrnlSysDescTxt_C" ezfName="omXtrnlSysDescTxt_C" value="WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" ezfHyo="C" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputCheckBox name="xxChkBox_C" ezfName="xxChkBox_C" value="Y" ezfHyo="C" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
									<tr>
								</table>
								
								
								<table>
									<tr>
										<td>
											<table style="width:450;height:30;fontcolor:#FFFFFF">
												<tr>
													<td>
													</td>
												</tr>
												<tr style="background:#999999;">
													<td class="stab">
														<span style="color: #FFFFFF">Qualified Order Entry Screen</span>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
										<td style="width:40">
										</td>
										<td>
											<table style="width:600;height:30;fontcolor:#FFFFFF">
												<tr>
													<td>
													</td>
												</tr>
												<tr style="background:#999999;">
													<td class="stab">
														<span style="color: #FFFFFF">Qualified Location Rule</span>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								
								
								
								
								<table  width="1000" border="0" cellpadding="0" cellspacing="0">
									<col align="Left" width="450">
									<col align="Left" width="50">
									<col align="Left" width="450">
									<col align="Left" width="50">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="270">
												<col align="center" width="50">

												<tr height="24">
													<td class="pClothBs">Screen</td>
													<td class="pClothBs" align="center">&nbsp;</td>

												</tr>
											</table>
											<div style="width:340;overflow-y:scroll; height:122">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="D">
													<col align="left" width="270">
													<col align="center" width="50">

													<ezf:row ezfHyo="D">
													<tr height="24">
														<td><ezf:inputText name="bizAppId_D" ezfName="bizAppId_D" value="12345678" ezfHyo="D" ezfArrayNo="0" otherAttr=" size=\"8\" maxlength=\"8\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputCheckBox name="xxChkBox_D" ezfName="xxChkBox_D" value="Y" ezfHyo="D" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													</ezf:skip>
												</table>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="270">
												<col align="center" width="50">

												<tr height="24">
													<td class="pClothBs">Location Role</td>
													<td class="pClothBs" align="center">&nbsp;</td>

												</tr>
											</table>
											<div style="width:340;overflow-y:scroll; height:122">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="E">
													<col align="left" width="270">
													<col align="center" width="50">

													<ezf:row ezfHyo="E">
													<tr height="24">
														<td><ezf:inputText name="locRoleTpDescTxt_E" ezfName="locRoleTpDescTxt_E" value="123456789012345678901234567890" ezfHyo="E" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"35\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputCheckBox name="xxChkBox_E" ezfName="xxChkBox_E" value="Y" ezfHyo="E" ezfArrayNo="0" /></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													
													</ezf:skip>
												</table>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
									<tr>
								</table>
								
								
								<table>
									<tr>
										<td>
											<table style="width:450;height:30;fontcolor:#FFFFFF">
												<tr>
													<td>
													</td>
												</tr>
												<tr style="background:#999999;">
													<td class="stab">
														<span style="color: #FFFFFF">Order Category Value Set Reference</span>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
										<td style="width:40">
										</td>
										<td>
											<table style="width:600;height:30;fontcolor:#FFFFFF">
												<tr>
													<td>
													</td>
												</tr>
												<tr style="background:#999999;">
													<td class="stab">
														<span style="color: #FFFFFF">Order Line Value Set Reference</span>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								
								<table  width="1000" border="0" cellpadding="0" cellspacing="0">
									<col align="Left" width="450">
									<col align="Left" width="50">
									<col align="Left" width="450">
									<col align="Left" width="50">
									<tr>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="220">
												<col align="center" width="90">
												<col align="center" width="90">

												<tr height="24">
													<td class="pClothBs">Value Set Name</td>
													<td class="pClothBs" align="center">Category</td>
													<td class="pClothBs" align="center">Reason</td>

												</tr>
											</table>
											<div style="width:420;overflow-y:scroll; height:122">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="F">
													<col align="left" width="220">
													<col align="left" width="90">
													<col align="left" width="90">

													<ezf:row ezfHyo="F">
													<tr height="24">
														<td><ezf:inputText name="ordCatgCtxTpCd_F" ezfName="ordCatgCtxTpCd_F" value="123456789012345678901234567890" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsOrdCatgDescTxt_F" ezfName="dsOrdCatgDescTxt_F" value="WWWWWWWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsOrdTpDescTxt_F" ezfName="dsOrdTpDescTxt_F" value="WWWWWWWWWW" ezfHyo="F" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													
													</ezf:skip>
												</table>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;">
												<col align="center" width="220">
												<col align="center" width="120">

												<tr height="24">
													<td class="pClothBs">Value Set Name</td>
													<td class="pClothBs" align="center">Category</td>

												</tr>
											</table>
											<div style="width:360;overflow-y:scroll; height:122">
												<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;" id="G">
													<col align="left" width="220">
													<col align="left" width="120">

													<ezf:row ezfHyo="G">
													<tr height="24">
														<td><ezf:inputText name="ordLineCtxTpCd_G" ezfName="ordLineCtxTpCd_G" value="123456789012345678901234567890" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
														<td><ezf:inputText name="dsOrdLineCatgDescTxt_G" ezfName="dsOrdLineCatgDescTxt_G" value="WWWWWWWWWW" ezfHyo="G" ezfArrayNo="0" otherAttr=" size=\"30\" maxlength=\"30\" style=\"border:none;background-color:transparent;padding:0px;\""/></td>
													</tr>
													</ezf:row>
													<ezf:skip>
													
													</ezf:skip>
												</table>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
									<tr>
								</table>
							</div>
							</div>
						</td>
					</tr>
				</table>
			</center>
			<ezf:inputHidden name="xxListNum_LY" ezfName="xxListNum_LY" value="" otherAttr=" id=\"xxListNum_LY\" "/>
			</script>

<%-- **** Task specific area ends here **** --%>
