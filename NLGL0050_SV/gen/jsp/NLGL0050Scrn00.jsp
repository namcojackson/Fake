<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180309103622 --%>
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
			<input type="hidden" name="pageID" value="NLGL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Ship Via Mapping from WMS to HOST">
<center>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				
				<%-- ###TAB - BODY --%>
				<div class="pTab_BODY">
					<table height="1">
						<tr><td></td></tr>
					</table>
					<table height="30">

						<col width="65">
						<col width="62">
						<col width="5">

						<col width="85">
						<col width="49">
						<col width="5">

						<col width="90">
						<col width="100">
						<col width="5">

						<col width="76">
						<col width="54">
						<col width="5">

						<col width="200">

						<tr>
							<td class="stab">Host System</td>
							<td align="left">
								<ezf:select name="wmsOrgHostId_P1" ezfName="wmsOrgHostId_P1" ezfCodeName="wmsOrgHostId_D1" ezfDispName="xxEdtCdNm_D1" >
								</ezf:select>
							</td>
							<td></td>
							<td class="stab">Transport Mode</td>
							<td align="left">
								<ezf:select name="wmsTrnspTpCd_P1" ezfName="wmsTrnspTpCd_P1" ezfCodeName="wmsTrnspTpCd_D2" ezfDispName="xxEdtCdNm_D2" >
								</ezf:select>
							</td>
							<td></td>
							<td class="stab">WMS Carrier Code</td>
							<td><ezf:inputText name="wmsCarrCd_T1" ezfName="wmsCarrCd_T1" value="WWWW" otherAttr=" size=\"24\" maxlength=\"40\" ezftoupper=\"\""/></td>
							<td></td>
							<td class="stab">Carrier SCAC</td>
							<td><ezf:inputText name="carrScacCd_T1" ezfName="carrScacCd_T1" value="1234" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
							<td></td>
							<td><ezf:inputButton name="OnClick_Search" value="Search" htmlClass="pBtn6" /></td>
						</tr>
					</table>

					<hr>

					<%-- ######################################## DETAIL ######################################## --%>
					<%-- ########## Table:A --%>
					<table width="1126">
						<col valign="top">
						<tr>
							<!-- first under tab starts here -->
							<%-- Carrier Code List Tab --%>
							<td>
								<%-- ###TAB - HEAD --%>
								<div class="pTab_HEAD">
									<ul>
										<li id="tbCodeList" title="CarrierCodeList" class="pTab_ON">
											<ezf:anchor name="" ezfName="xxTabProt_A1" ezfEmulateBtn="1" ezfGuard="OnClick_CodeListTab" >Code List</ezf:anchor>
										</li>
										<li id="tbCodeEdit" title="CarrierCodeEdit" class="pTab_OFF">
											<ezf:anchor name="" ezfName="xxTabProt_T2" ezfEmulateBtn="1" ezfGuard="OnClick_CodeEditTab" >Code Edit</ezf:anchor>
										</li>
									</ul>
									<table cellpadding="0" cellspacing="0">
										<col width="850">
										<col width="54"  align="center">
										<tr>
											<td></td>
											<td></td>
										</tr>
									</table>
								</div>
							<c:choose>
							<c:when test="${pageScope._ezddatabean.xxDplyTab=='xxTabCarrierCode_List'}">
								<script type="text/javascript">
									document.getElementById( "tbCodeList" ).className ="pTab_ON";
									document.getElementById( "tbCodeEdit" ).className ="pTab_OFF";
								</script>

								<%-- ###TAB - BODY --%>
								<div class="pTab_BODY_In">
									<table height="357" width="100%">
										<tr valign="top">
											<td>
												<table border="0" cellpadding="1" cellspacing="0" width="1126">
													<tr>
														<!--<col width="54"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col>
														<col width="1">
														<col>
														<col width="17">-->
														<td align="left"><ezf:inputButton name="OnClick_New" value="Insert Row" htmlClass="pBtn6" /></td>
														<td align="right">
														<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
															<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
															<jsp:param name="TableNm"     value="A" />
															<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
															<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
															<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
														</jsp:include>
														</td>
													</tr>
												</table>

												<table border="1" cellpadding="0" cellspacing="0">
													<tr>
													<%-- ########## Table:A --%>
														<td align="left" valign="top">
															<table border="1" cellpadding="1" cellspacing="0" width="425">
																<col width="30">
																<col width="80" align="center">
																<col width="300" align="center">
																
																<tr height="37">
																	<td class="pClothBs">&nbsp;</td>
																	<td class="pClothBs">Transport<br>Mode</td>
																	<td class="pClothBs">WMS Carr Code</td>
																</tr>
															</table>
																	
															<%-- id:leftTBL --%>
															<div id="leftTBL" style="overflow-y:hidden; height:377; overflow-x:hidden; width:;" onScroll="synchroScrollTop(this.id, new Array('rightTBL'));">
																<table border="1" cellpadding="1" cellspacing="0" width="425" id="A_left">
																	<col width="30" align="center">
																	<col width="80" align="center">
																	<col width="300" align="center">

																	<ezf:row ezfHyo="A">
																		<tr height="28">
																			<td><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"chkBox#{EZF_ROW_NUMBER}\""/></td>
																			<td><ezf:label name="wmsTrnspTpCd_A1" ezfName="wmsTrnspTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="wmsCarrCd_A1" ezfName="wmsCarrCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<!--<td><label ezfout name="wmsTrnspTpCd_A1" ezfname="wmsTrnspTpCd_A1" ezfhyo="A">TL</label></td>
																			<td><label ezfout name="wmsCarrCd_A1" ezfname="wmsCarrCd_A1" ezfhyo="A">1FUL</label></td>-->
																		</tr>
																	</ezf:row>
																</table>
															</div>
														</td>

														<%-- ########## Table:B --%>
														<td valign="top">
															<%-- id:topTBL --%>
															<div id="topTBL" style="overflow-y:none; height:; overflow-x:hidden; width:663;" onScroll="synchroScrollLeft(this.id, new Array('rightTBL'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1686">
																	<col width="180" align="center">
																	<col width="180" align="center">
																	<col width="180" align="center">
																	<col width="80" align="center">
																	<col width="180" align="center">
																	<col width="80" align="center">
																	<col width="170" align="center">
																	<col width="80" align="center">
																	<col width="170" align="center">
																	<col width="160" align="center">
																	<col width="160" align="center">
																	<tr height="37">
																		<td class="pClothBs">Description</td>
																		<td class="pClothBs">Carrier Name</td>
																		<td class="pClothBs">Carrier Svc</td>
																		<td class="pClothBs">S21 SCAC</td>
																		<td class="pClothBs">S21 Shipping Service Level</td>
																		<td class="pClothBs">Fuel Uc Type</td>
																		<td class="pClothBs">Fuel Upcharge</td>
																		<td class="pClothBs">Addl Uc Type</td>
																		<td class="pClothBs">Addl Upcharge</td>
																		<td class="pClothBs">Updated By</td>
																		<td class="pClothBs">Updated On</td>
																	</tr>
																</table>
															</div>

															<%-- id:rightTBL --%>
															<div id="rightTBL" style="overflow-y:scroll; height:394; overflow-x:scroll; width:680;" onScroll="synchroScrollTop(this.id, new Array('leftTBL')); onScroll=synchroScrollLeft(this.id, new Array('topTBL'));">
																<table border="1" cellpadding="1" cellspacing="0" width="1686" id="A_right">
																	<col width="180" align="left">
																	<col width="180" align="left">
																	<col width="180" align="left">
																	<col width="80" align="center">
																	<col width="180" align="center">
																	<col width="80" align="center">
																	<col width="170" align="right">
																	<col width="80" align="center">
																	<col width="170" align="right">
																	<col width="160" align="center">
																	<col width="160" align="center">
																	<ezf:row ezfHyo="A">
																		<tr height="28">
																			<td><ezf:inputText name="wmsDescTxt_A1" ezfName="wmsDescTxt_A1" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"100\""/></td>
																			<td><ezf:inputText name="wmsCarrNm_A1" ezfName="wmsCarrNm_A1" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"100\""/></td>
																			<td><ezf:inputText name="carrSvcTxt_A1" ezfName="carrSvcTxt_A1" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"100\""/></td>
																			<td><ezf:label name="carrScacCd_A1" ezfName="carrScacCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:inputText name="shpgSvcLvlDescTxt_A1" ezfName="shpgSvcLvlDescTxt_A1" value="12345678901234567890123456789012345678901234567890" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"25\" maxlength=\"100\""/></td>
																			<td><ezf:label name="fuelUpchgTpCd_A1" ezfName="fuelUpchgTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="fuelUpchgAmt_A1" ezfName="fuelUpchgAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="addlUpchgTpCd_A1" ezfName="addlUpchgTpCd_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="addlUpchgAmt_A1" ezfName="addlUpchgAmt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="ezUpUserID_A1" ezfName="ezUpUserID_A1" ezfHyo="A" ezfArrayNo="0" /></td>
																			<td><ezf:label name="xxDtTm_A1" ezfName="xxDtTm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
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

							<%-- ######################################## DETAIL ######################################## --%>
							<%-- ########## Table:A --%>
							<c:when test="${pageScope._ezddatabean.xxDplyTab=='xxTabCarrierCode_Edit'}">
								<script type="text/javascript">
									document.getElementById( "tbCodeList" ).className ="pTab_OFF";
									document.getElementById( "tbCodeEdit" ).className ="pTab_ON";
								</script>

								<%-- SHIP VIA Edit --%>
								<%-- TAB_Detail --%>
								<div class="pTab_BODY_In">
									<table border="0" width="1120" height="450">
										<tr valign="top">
											<td>
												<table>
													<col width="145">
													<col width="145">
													<col width="145">
													<col width="395">
													<tr  height="41px">
														<td class="stab">Transport Mode</td>
														<td colspan="3">
															<ezf:select name="wmsTrnspTpCd_P2" ezfName="wmsTrnspTpCd_P2" ezfCodeName="wmsTrnspTpCd_T2" ezfDispName="xxEdtCdNm_T2" >
															</ezf:select>
														</td>
													</tr>
													<tr  height="41px">
														<td class="stab">WMS Carrier Code</td>
														<td colspan="3"><ezf:inputText name="wmsCarrCd_T2" ezfName="wmsCarrCd_T2" value="1234567890123456789012345678901234567890" otherAttr=" size=\"40\" maxlength=\"40\""/></td>
													</tr>
													<tr height="41px">
														<td class="stab">Description</td>
														<td colspan="3"><ezf:inputText name="wmsDescTxt_T2" ezfName="wmsDescTxt_T2" value="12345678901234567890123456789012345678901234567890" otherAttr=" size=\"50\" maxlength=\"100\""/></td>
													</tr>
													<tr height="41px">
														<td class="stab">Carrier Name</td>
														<td colspan="3"><ezf:inputText name="wmsCarrNm_T2" ezfName="wmsCarrNm_T2" value="12345678901234567890123456789012345678901234567890" otherAttr=" size=\"50\" maxlength=\"100\""/></td>
													</tr>
													<tr height="41px">
														<td class="stab">Carrier Service</td>
														<td colspan="3"><ezf:inputText name="carrSvcTxt_T2" ezfName="carrSvcTxt_T2" value="12345678901234567890123456789012345678901234567890" otherAttr=" size=\"50\" maxlength=\"100\""/></td>
													</tr>
													<tr height="41px">
														<td class="stab">Carrier SCAC Code</td>
														<td><ezf:inputText name="carrScacCd_T2" ezfName="carrScacCd_T2" value="1234" otherAttr=" size=\"4\" maxlength=\"4\" ezftoupper=\"\""/></td>
														<td class="stab">Shipping Service Level</td>
														<td>
															<ezf:select name="shpgSvcLvlCd_P2" ezfName="shpgSvcLvlCd_P2" ezfBlank="1" ezfCodeName="shpgSvcLvlCd_T2" ezfDispName="xxEdtCdNm_T5" />
														</td>
													</tr>
													<tr height="41px">
														<td class="stab">Fuel Upcharge Type </td>
														<td colspan="3">
															<ezf:select name="fuelUpchgTpCd_P2" ezfName="fuelUpchgTpCd_P2" ezfBlank="1" ezfCodeName="fuelUpchgTpCd_T2" ezfDispName="xxEdtCdNm_T3" />
														</td>
													</tr>
													<tr height="41px">
														<td class="stab">Fuel Upcharge</td>
														<td colspan="3"><ezf:inputText name="fuelUpchgAmt_T2" ezfName="fuelUpchgAmt_T2" value="123,456,789,012,345.1234" otherAttr=" size=\"24\" maxlength=\"20\""/></td>
													</tr>
													<tr height="41px">
														<td class="stab">Additional Upcharge Type</td>
														<td colspan="3">
															<ezf:select name="addlUpchgTpCd_P2" ezfName="addlUpchgTpCd_P2" ezfBlank="1" ezfCodeName="addlUpchgTpCd_T2" ezfDispName="xxEdtCdNm_T4" />
														</td>
													</tr>
													<tr height="41px">
														<td class="stab">Additional Upcharge</td>
														<td colspan="3"><ezf:inputText name="addlUpchgAmt_T2" ezfName="addlUpchgAmt_T2" value="123,456,789,012,345.1234" otherAttr=" size=\"24\" maxlength=\"20\""/></td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
							</c:when>
							</c:choose>
							</td>
							<!-- Third under tab ends here -->
						</tr>
					</table>
				</div> 
<%-- ######################################## FOOTER ######################################## --%>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
