<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190319084449 --%>
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
			<input type="hidden" name="pageID" value="NLGL0030Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Forced Item Master Download">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<%--
				<div class="pTab_HEAD">
					<ul>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="96%">
									<div>
										<li title="Item Download" class="pTab_ON"><a href="#">Item Download</a></li>
									</div>
								</td>
							</tr>
						</table>
					</ul>
				</div>
				--%>

				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
                    <table height="560" width="100%">
                        <col valign="top">
                        <tr>
                            <td>
<%-- ######################################## DETAIL ######################################## --%>
                                <hr>
								<%-- ###TAB - HEAD --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="tbHistory" class="pTab_ON" title="History"><ezf:anchor name="xxHistory" ezfName="xxHistory" ezfEmulateBtn="1" ezfGuard="TAB_History" >History</ezf:anchor></li>
										<li id="tbDownload" class="pTab_OFF" title="History"><ezf:anchor name="xxDownload" ezfName="xxDownload" ezfEmulateBtn="1" ezfGuard="TAB_Download" >Download</ezf:anchor></li>
									</ul>
								</div>
								<c:choose>

<%-- History TAB --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='xxHistory'}">
										<script type="text/javascript">
											document.getElementById("tbHistory").className="pTab_ON";
											document.getElementById("tbDownload").className="pTab_OFF";
										</script>

										<%-- ###TAB - BODY --%>
										<div class="pTab_BODY_In">
											<table height="428" width="100%">
												<tr valign="top">
													<td>
														<table height="30">
															<col width="32">
															<col width="90">
															<col width="5">

															<col width="64">
															<col width="104">
															<col width="5">

															<col width="160">
															<tr>
																<td class="stab">WH</td>
																<td align="left">
																	<ezf:select name="whCd_P0" ezfName="whCd_P0" ezfCodeName="whCd_H0" ezfDispName="xxEdtCdNm_H0" />
																</td>
																<td></td>

																<td class="stab">Item Number</td>
																<td><ezf:inputText name="wmsMdseCd_H0" ezfName="wmsMdseCd_H0" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																<td></td>
																<td><ezf:inputButton name="OnClick_HistSrch" value="Search" htmlClass="pBtn6" /></td>
															</tr>
														</table>
														<table border="0" cellpadding="1" cellspacing="0" width="1078">
															<tr>
																<td align="right">
																<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
																	<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
																	<jsp:param name="TableNm"     value="A" />
																	<jsp:param name="ShowingFrom" value="xxPageShowFromNum_H0" />
																	<jsp:param name="ShowingTo"   value="xxPageShowToNum_H0" />
																	<jsp:param name="ShowingOf"   value="xxPageShowOfNum_H0" />
																</jsp:include>
																</td>
															</tr>
														</table>
														<table border="0" cellpadding="1" cellspacing="0" >
															<tr>
																<td valign="top">
																	<div id="topHistTBL" style="overflow-x:none; width:995;">
																		<table border="1" cellpadding="1" cellspacing="0"width="1078" >
																			<col align="center" width="48">
																			<col align="center" width="140">
																			<col align="center" width="130">
																			<col align="center" width="130">
                                                                            <col align="center" width="64">
																			<col align="center" width="64">
																			<col align="center" width="64">
																			<col align="center" width="64">
																			<col align="center" width="59">
																			<col align="center" width="96">
																			<col align="center" width="160">

																			<tr>
																				<td class="pClothBs">WH</td>
																				<td class="pClothBs">Item Number</td>
																				<td class="pClothBs">Description1</td>
																				<td class="pClothBs">Description2</td>
																				<td class="pClothBs">Prod Code</td>
																				<td class="pClothBs">NMFC</td>
																				<td class="pClothBs">Case Qty</td>
																				<td class="pClothBs">Pallet Qty</td>
																				<td class="pClothBs">Serial</td>
																				<td class="pClothBs">Status</td>
																				<td class="pClothBs">Download Date</td>
																			</tr>
																		</table>
																	</div>

																	<div id="bottomHistTBL" style="overflow-y:auto; height:414;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="1078" > 
																			<col align="center" width="48">
																			<col width="140">
																			<col width="130">
																			<col width="130">
																			<col align="center" width="64">
																			<col align="right" width="64">
																			<col align="right" width="64">
																			<col align="right" width="64">
																			<col align="center" width="59">
																			<col align="center" width="96">
																			<col align="center" width="160">
																			<ezf:row ezfHyo="A">
																			<tr height="28">
																				<td><ezf:label name="whCd_D0" ezfName="whCd_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="wmsMdseCd_D0" ezfName="wmsMdseCd_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="mdseDescShortTxt_D0" ezfName="mdseDescShortTxt_D0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"250\""/></td>
																				<td><ezf:inputText name="mdseFmlNm_D0" ezfName="mdseFmlNm_D0" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"80\""/></td>
																				<td><ezf:label name="fifthProdCtrlCd_D0" ezfName="fifthProdCtrlCd_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="nmfcClsNum_D0" ezfName="nmfcClsNum_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="inEachQty_Q0" ezfName="inEachQty_Q0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="inEachQty_Q1" ezfName="inEachQty_Q1" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="shpgSerTakeFlg_D0" ezfName="shpgSerTakeFlg_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxPrmoStsCd_D0" ezfName="xxPrmoStsCd_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_D0" ezfName="xxDtTm_D0" ezfHyo="A" ezfArrayNo="0" /></td>
																			</tr>
																			</ezf:row>
																			<ezf:skip>
																			<tr height="28">
																				<td><label ezfout ezfhyo="A" name="whCd_D0" ezfname="whCd_D0">32</label></td>
																				<td><label ezfout ezfhyo="A" name="wmsMdseCd_D0" ezfname="wmsMdseCd_D0">123456789012345</label></td>
																				<td><input type="text" class="pPro" size="15" maxlength="250" value="" name="mdseDescShortTxt_D0" ezfname="mdseDescShortTxt_D0" ezfhyo="A"></td>
																				<td><input type="text" class="pPro" size="15" maxlength="80" value="" name="mdseFmlNm_D0" ezfname="mdseFmlNm_D0" ezfhyo="A"></td>
																				<td><label ezfout ezfhyo="A" name="fifthProdCtrlCd_D0" ezfname="fifthProdCtrlCd_D0">S</label></td>
																				<td><label ezfout ezfhyo="A" name="nmfcClsNum_D0" ezfname="nmfcClsNum_D0">100</label></td>
																				<td><label ezfout ezfhyo="A" name="inEachQty_Q0" ezfname="inEachQty_Q0">1</label></td>
																				<td><label ezfout ezfhyo="A" name="inEachQty_Q1" ezfname="inEachQty_Q1">10</label></td>
																				<td><label ezfout ezfhyo="A" name="shpgSerTakeFlg_D0" ezfname="shpgSerTakeFlg_D0">Y</label></td>
																				<td><label ezfout ezfhyo="A" name="xxPrmoStsCd_D0" ezfname="xxPrmoStsCd_D0">InCompleted</label></td>
																				<td><label ezfout ezfhyo="A" name="xxDtTm_D0" ezfname="xxDtTm_D0">08/15/2013 10:10 10</label></td>
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
									</c:when>

<%-- Download TAB --%>
									<c:when test="${pageScope._ezddatabean.xxDplyTab=='xxDownload'}">
										<script type="text/javascript">
											document.getElementById("tbHistory").className="pTab_OFF";
											document.getElementById("tbDownload").className="pTab_ON";
										</script>

										<div class="pTab_BODY_In">
											<table height="375" width="100%">
												<tr valign="top">
													<td>
														<table height="30">

															<col width="32">
															<col width="90">
															<col width="5">

															<col width="64">
															<col width="104">
															<col width="5">

															<col width="160">
															<tr>
																<td class="stab">WH</td>
																<td align="left">
																	<ezf:select name="whCd_P1" ezfName="whCd_P1" ezfBlank="1" ezfCodeName="whCd_H1" ezfDispName="xxEdtCdNm_H1" />
																</td>
																<td></td>

																<td class="stab">Item Number</td>
																<td><ezf:inputText name="mdseCd_H1" ezfName="mdseCd_H1" otherAttr=" size=\"16\" maxlength=\"16\" ezftoupper=\"\""/></td>
																<td></td>
																<td><ezf:inputButton name="OnClick_DnldSrch" value="Search" htmlClass="pBtn6" /></td>
															</tr>
														</table>
														<table border="0" cellpadding="1" cellspacing="0" >
															<tr>
																<td valign="top">
																	<div id="topDnldTBL" style="overflow-x:none; width:1024;">
																		<table border="1" cellpadding="1" cellspacing="0"width="1024" >
																			<col align="center" width="140">
																			<col align="center" width="180">
																			<col align="center" width="180">
																			<col align="center" width="64">
																			<col align="center" width="64">
																			<col align="center" width="64">
																			<col align="center" width="64">
																			<col align="center" width="59">
																			<col align="center" width="160">

																			<tr>
																				<td class="pClothBs">Item Number</td>
																				<td class="pClothBs">Description1</td>
																				<td class="pClothBs">Description2</td>
																				<td class="pClothBs">Prod Code</td>
																				<td class="pClothBs">NMFC</td>
																				<td class="pClothBs">Case Qty</td>
																				<td class="pClothBs">Pallet Qty</td>
																				<td class="pClothBs">Serial</td>
																				<td class="pClothBs">Download Date</td>
																			</tr>
																		</table>
																	</div>

																	<div id="bottomDnldTBL" style="overflow-x:none; width:1024;" >
																		<table border="1" cellpadding="1" cellspacing="0"width="1024" > 
																			<col width="140">
																			<col width="180">
																			<col width="180">
																			<col align="center" width="64">
																			<col align="right" width="64">
																			<col align="right" width="64">
																			<col align="right" width="64">
																			<col align="center" width="59">
																			<col align="center" width="160">
																			<ezf:row ezfHyo="B">
																			<tr height="28">
																				<td><ezf:label name="mdseCd_D1" ezfName="mdseCd_D1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:inputText name="mdseDescShortTxt_D1" ezfName="mdseDescShortTxt_D1" value="SCANNER EXPRESS" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"250\""/></td>
																				<td><ezf:inputText name="mdseFmlNm_D1" ezfName="mdseFmlNm_D1" value="SCANNER EXPRESS" ezfHyo="B" ezfArrayNo="0" otherAttr=" size=\"22\" maxlength=\"80\""/></td>
																				<td><ezf:label name="fifthProdCtrlCd_D1" ezfName="fifthProdCtrlCd_D1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="nmfcClsNum_D1" ezfName="nmfcClsNum_D1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="inEachQty_Q2" ezfName="inEachQty_Q2" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="inEachQty_Q3" ezfName="inEachQty_Q3" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="shpgSerTakeFlg_D1" ezfName="shpgSerTakeFlg_D1" ezfHyo="B" ezfArrayNo="0" /></td>
																				<td><ezf:label name="xxDtTm_D1" ezfName="xxDtTm_D1" ezfHyo="B" ezfArrayNo="0" /></td>
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
										</div>
									</c:when>
                                </c:choose>
                           </td>
                        </tr>
                    </table>
                </div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
