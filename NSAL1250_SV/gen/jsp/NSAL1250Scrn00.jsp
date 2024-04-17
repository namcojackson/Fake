<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160311113035 --%>
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
			<input type="hidden" name="pageID" value="NSAL1250Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Preview Billing Workflow Details">
			
			<center>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td>
							<%-- #################################################### HEADER ################################################### --%>
							<ezf:skip>
							<div class="pTab_HEAD">
								<ul>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="96%">
												<div>
													<li title="Preview Billing Workflow Details" class="pTab_ON"><a href="#">Preview Detail</a></li>
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
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							<%-- ###TAB - BODY --%>
							<div class="pTab_BODY">
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
									<tr>
										<td valign="top">
											<table border="0" style="table-layout:fixed;" width="95%" align="left">
												<col width="10">
												<col width="40">
												<col width="130">
												<col width="30">
												<col width="30">
												<tr>
													<td colspan="5"><ezf:label name="xxWfCmntTxt_1" ezfName="xxWfCmntTxt_1" otherAttr=" align=\"left\""/></td>
												</tr>
												<tr>
													<td colspan="5"></td>
												</tr>
												<tr>
													<td class="stab">To</td>
													<td><ezf:inputText name="xxWfActOpNm" ezfName="xxWfActOpNm" value="D00007" otherAttr=" size=\"19\" maxlength=\"10\" style=\"text-align:left\""/></td>
													<td><ezf:inputText name="xxCtacPsnNmTxt" ezfName="xxCtacPsnNmTxt" value="Morris, Kristal Kathleen" otherAttr=" size=\"30\" maxlength=\"67\" style=\"text-align:left\""/></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td class="stab">Sent</td>
													<td><ezf:inputText name="xxTsDsp19Txt" ezfName="xxTsDsp19Txt" value="12/31/2016 23:25:40" otherAttr=" size=\"19\" maxlength=\"19\" style=\"text-align:left\""/></td>
													<td></td>
													<td><ezf:inputButton name="Approve" value="Approve" htmlClass="pBtn6" otherAttr=" id=\"Approve\""/></td>
													<td><ezf:inputButton name="Reassign" value="Reassign" htmlClass="pBtn6" otherAttr=" id=\"Reassign\""/></td>
												</tr>
												<tr>
													<td colspan="5"><ezf:textArea name="xxWfCmntTxt_2" ezfName="xxWfCmntTxt_2" otherAttr=" rows=\"5\" cols=\"140\""/></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
								<table width="99%" align="center">
									<tr>
										<td align="left">Please see details below</td>
										<td align="right">
											<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col align="left">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col width="0">
														<col width="1">
														<col width="0">
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">3</td>
															<td class="stab">of</td>
															<td class="pOut">1000</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
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
									</tr>
								</table>
								<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
								<%-- ######################################## DETAIL1 Start ############################################## --%>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
									<col align="left" valign="top">
									<tr>
										<td>
											<div id="Top" style="overflow-x:hidden; width:1056;">
												<table border="1" cellpadding="1" cellspacing="0" width="" height="24">
													<col width="195"  align="center">		<!-- Counter Type -->
													<col width="135"  align="center">		<!-- Billing Period Start Date -->
													<col width="135"  align="center">		<!-- Billing Period End Date -->
													<col width="185"  align="center">		<!-- Average Billing(12 months) -->
													<col width="185"  align="center">		<!-- Current Overage Billing -->
													<col width="185"  align="center">		<!-- Overage Billing(%) -->
													<tr>
														<td class="pClothBs">Counter Type</td>
														<td class="pClothBs">Billing Period Start Date</td>
														<td class="pClothBs">Billing Period End Date</td>
														<td class="pClothBs">Average Billing<br>(12 months)</td>
														<td class="pClothBs">Current Overage Billing</td>
														<td class="pClothBs">Overage Billing(%)</td>
													</tr>
												</table>
											</div>
											<div id="Detail" style="width:1066; overflow-y:scroll; height:142;">
												<table id="A" border="1" cellpadding="1" cellspacing="0">
													<col width="195"  align="center">		<!-- Counter Type -->
													<col width="135"  align="center">		<!-- Billing Period Start Date -->
													<col width="135"  align="center">		<!-- Billing Period End Date -->
													<col width="185"  align="center">		<!-- Average Billing(12 months) -->
													<col width="185"  align="center">		<!-- Current Overage Billing -->
													<col width="185"  align="center">		<!-- Overage Billing(%) -->
													<ezf:row ezfHyo="A">
														<tr>
															<td>
																<ezf:inputText name="mtrLbDescTxt_A" ezfName="mtrLbDescTxt_A" value="BW Billing Counter" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mtrLbDescTxt_A#{EZF_ROW_NUMBER}\" size=\"25\" maxlength=\"50\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="svcContrBllgFromDt_A" ezfName="svcContrBllgFromDt_A" value="01/01/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcContrBllgFromDt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="svcContrBllgThruDt_A" ezfName="svcContrBllgThruDt_A" value="01/31/2016" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"svcContrBllgThruDt_A#{EZF_ROW_NUMBER}\" size=\"10\" maxlength=\"10\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="mtrChrgDealAmt_AV" ezfName="mtrChrgDealAmt_AV" value="123.89" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mtrChrgDealAmt_AV#{EZF_ROW_NUMBER}\" size=\"24\" maxlength=\"24\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="mtrChrgDealAmt_AA" ezfName="mtrChrgDealAmt_AA" value="265.98" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mtrChrgDealAmt_AA#{EZF_ROW_NUMBER}\" size=\"24\" maxlength=\"24\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="mtrChrgDealAmt_AP" ezfName="mtrChrgDealAmt_AP" value="214.69" ezfHyo="A" ezfArrayNo="0" otherAttr=" id=\"mtrChrgDealAmt_AP#{EZF_ROW_NUMBER}\" size=\"24\" maxlength=\"24\" tabindex=\"-1\""/>
															</td>
														</tr>
														<ezf:skip>
														<tr>
															<td>
																<input id="mtrLbDescTxt_A#{EZF_ROW_NUMBER}" type="text" size="25" maxlength="50" class="pPro" value="BW Billing Counter" name="mtrLbDescTxt_A" ezfname="mtrLbDescTxt_A" ezfhyo="A" tabindex="-1">
															</td>
															<td>
																<input id="svcContrBllgFromDt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="01/01/2016" name="svcContrBllgFromDt_A" ezfname="svcContrBllgFromDt_A" ezfhyo="A" tabindex="-1">
															</td>
															<td>
																<input id="svcContrBllgThruDt_A#{EZF_ROW_NUMBER}" type="text" size="10" maxlength="10" class="pPro" value="01/31/2016" name="svcContrBllgThruDt_A" ezfname="svcContrBllgThruDt_A" ezfhyo="A" tabindex="-1">
															</td>
															<td>
																<input id="mtrChrgDealAmt_AV#{EZF_ROW_NUMBER}" type="text" size="24" maxlength="24" class="pPro" value="123.89" name="mtrChrgDealAmt_AV" ezfname="mtrChrgDealAmt_AV" ezfhyo="A" tabindex="-1">
															</td>
															<td>
																<input id="mtrChrgDealAmt_AA#{EZF_ROW_NUMBER}" type="text" size="24" maxlength="24" class="pPro" value="265.98" name="mtrChrgDealAmt_AA" ezfname="mtrChrgDealAmt_AA" ezfhyo="A" tabindex="-1">
															</td>
															<td>
																<input id="mtrChrgDealAmt_AP#{EZF_ROW_NUMBER}" type="text" size="24" maxlength="24" class="pPro" value="214.69" name="mtrChrgDealAmt_AP" ezfname="mtrChrgDealAmt_AP" ezfhyo="A" tabindex="-1">
															</td>
														</tr>
														</ezf:skip>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<%-- ######################################## DETAIL1 End   ######################################## --%>
								<br>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
								<tr><td><ezf:inputButton name="ChangeRead" value="Change Read" htmlClass="pBtn6" otherAttr=" id=\"ChangeRead\""/></td></tr>
								</table>
								<%-- ######################################## FROM (COMMON)PAGENATION #################################### --%>
								<table width="99%">
									<tr align="right">
										<td>
											<ezf:skip>
													<table border="0" cellpadding="1" cellspacing="0">
														<col >
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="16"  align="center">
														<col width="40"  align="right">
														<col width="10">
														<col width="0">
														<col width="1">
														<col width="0">
														<tr>
															<td class="stab">Showing</td>
															<td class="pOut">1</td>
															<td class="stab">to</td>
															<td class="pOut">3</td>
															<td class="stab">of</td>
															<td class="pOut">1000</td>
															<td>&nbsp;</td>
															<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
															<td></td>
															<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
														</tr>
													</table>
											</ezf:skip>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="B" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
											</jsp:include>
										</td>
									</tr>
								</table>
								<%-- ######################################## TO (COMMON)PAGENATION ###################################### --%>
								<%-- ######################################## DETAIL2 Start ######################################## --%>
								<table border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;" width="98%" align="center">
									<col align="left" valign="top">
									<tr>
										<td>
											<div id="Top" style="overflow-x:hidden; width:1070;">
												<table border="1" cellpadding="1" cellspacing="0" width="" height="24">
													<col width="160"  align="center">		<!-- Action Date -->
													<col width="150" align="center">		<!-- Action -->
													<col width="185"  align="center">		<!-- From -->
													<col width="185" align="center">		<!-- To -->
													<col width="360"  align="center">		<!-- Detail -->
													<tr>
														<td class="pClothBs">Action Date</td>
														<td class="pClothBs">Action</td>
														<td class="pClothBs">From</td>
														<td class="pClothBs">To</td>
														<td class="pClothBs">Detail</td>
													</tr>
												</table>
											</div>
											<div id="Detail" style="width:1080; overflow-y:scroll; height:142;">
												<table id="B" border="1" cellpadding="1" cellspacing="0">
													<col width="160"  align="center">		<!-- Action Date -->
													<col width="150" align="center">		<!-- Action -->
													<col width="185"  align="center">		<!-- From -->
													<col width="185" align="center">		<!-- To -->
													<col width="360"  align="center">		<!-- Detail -->
													<ezf:row ezfHyo="B">
														<tr>
															<td>
																<ezf:inputText name="xxTsDsp19Txt_B" ezfName="xxTsDsp19Txt_B" value="12/31/2016 23:25:40" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"procEndTs_B#{EZF_ROW_NUMBER}\" size=\"19\" maxlength=\"19\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxWfActOpNm_B" ezfName="xxWfActOpNm_B" value="Submit" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxWfActOpNm_B#{EZF_ROW_NUMBER}\" size=\"20\" maxlength=\"1024\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxCtacPsnNmTxt_BF" ezfName="xxCtacPsnNmTxt_BF" value="Tanaka, Tomokazu" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxCtacPsnNmTxt_BF#{EZF_ROW_NUMBER}\" size=\"25\" maxlength=\"62\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxCtacPsnNmTxt_BT" ezfName="xxCtacPsnNmTxt_BT" value="Morris, Kristal Kathleen" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxCtacPsnNmTxt_BT#{EZF_ROW_NUMBER}\" size=\"25\" maxlength=\"62\" tabindex=\"-1\""/>
															</td>
															<td>
																<ezf:inputText name="xxWfCmntTxt_B" ezfName="xxWfCmntTxt_B" value="xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3" ezfHyo="B" ezfArrayNo="0" otherAttr=" id=\"xxWfCmntTxt_B#{EZF_ROW_NUMBER}\" size=\"50\" maxlength=\"4000\" tabindex=\"-1\""/>
															</td>
														</tr>
														<ezf:skip>
														
														</ezf:skip>
													</ezf:row>
												</table>
											</div>
										</td>
									</tr>
								</table>
								<%-- ######################################## DETAIL2 End   ######################################## --%>
							</div>
						</td>
					</tr>
				</table>
			</center>
			
			

<%-- **** Task specific area ends here **** --%>
