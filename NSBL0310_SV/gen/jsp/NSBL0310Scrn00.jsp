<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20190402130145 --%>
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
			<input type="hidden" name="pageID" value="NSBL0310Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Request By Manager">

<%@ page import="business.servlet.NSBL0310.NSBL0310BMsg" %>
<%@ page import="business.servlet.NSBL0310.NSBL0310_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NSBL0310BMsg bMsg = (NSBL0310BMsg)databean.getEZDBMsg(); %>

<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<!-- ###TAB - HEAD -->
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<!-- ###TAB - BODY -->
			<div class="pTab_BODY">
				<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MGR_TP" %>
				<%@ page import="com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_DOWN_TP" %>
				<table border="0">
					<col width="10">
					<col width="75" align="left">
					<col width="60" align="left">
					<col width="20" align="left">
					<col width="100" align="left">
					<col width="400" align="left">
					<col width="30" align="center">
					<col width="120" align="left">
					<col width="20" align="center">
					<col width="100" align="left">
					<tr>
						<td></td>
						<td class="stab"><ezf:anchor name="orgCd_HL" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_OrgLkupPop" >Service Group</ezf:anchor></td>
						<td><ezf:inputText name="orgCd_HT" ezfName="orgCd_HT" value="100000" otherAttr=" size=\"12\""/></td>
						<td><ezf:inputButton name="Search_SvcGrp" value=">>" htmlClass="pBtn0" /></td>
						<td><ezf:inputText name="orgDescTxt_H" ezfName="orgDescTxt_H" value="NORTH CENTRAL" otherAttr=" size=\"23\""/></td>
						<td></td>
						<td><ezf:inputRadio name="svcMgrTpCd_H" ezfName="svcMgrTpCd_H" value="<%= SVC_MGR_TP.MACHINE_MANAGER %>" /></td>
						<td class="stab">Machine Manager</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><ezf:inputRadio name="svcMgrTpCd_H" ezfName="svcMgrTpCd_H" value="<%= SVC_MGR_TP.RESOURCE_MANAGER %>" /></td>
						<td class="stab">Resource Manager</td>
						<td></td>
						<td align="right"><ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" /></td>
					</tr>
				</table>
				<table border="0">
				</table>

				<hr>

				<ezf:skip>
				<table border="0" cellpadding="1" cellspacing="0">
					<col width="740">
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
						<td></td>
						<td class="stab">Showing</td>
						<td class="pOut">1</td>
						<td class="stab">to</td>
						<td class="pOut">18</td>
						<td class="stab">of</td>
						<td class="pOut">18</td>
						<td>&nbsp;</td>
						<td><input type="button" class="pBtn3" value="Prev" name="PagePrev"></td>
						<td></td>
						<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
					</tr>
				</table>
				</ezf:skip>

				<table width="100%">
					<tr>
						<td>
							<table width="98%">
								<tr align="right">
									<td>
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

<!-- ######################################## DETAIL ######################################## -->
				<center>
					<table border="0">
						<tr>
							<td align="left" width="">
								<div id="headerTBL" style="width:1110px ; height:60px;" >
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed;word-break:break-all">
										<colgroup>
											<col align="center" width="245px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
											<col align="center" width="65px">
										</colgroup>
										<tbody>
											<tr height="20px">
												<td class="pClothBs" colspan="1" rowspan="2">&nbsp;</td>
												<td class="pClothBs" colspan="1">Prior</td>
												<td class="pClothBs" colspan="4">Today</td>
												<td class="pClothBs" colspan="8">Current</td>
											</tr>
											<tr height="40px">
												<td class="pClothBs">Open</td>
												<td class="pClothBs">In</td>
												<td class="pClothBs">Closed</td>
												<td class="pClothBs">Cancel</td>
												<td class="pClothBs">Avr Resp <br/>Time</td>
												<td class="pClothBs">TBO</td>
												<td class="pClothBs">Scheduled</td>
												<td class="pClothBs">Assign</td>
												<td class="pClothBs">Open</td>
												<td class="pClothBs">On Hold</td>
												<td class="pClothBs">Escalation</td>
												<td class="pClothBs">VIP</td>
												<td class="pClothBs">Error/<br/>Need Attn</td>
											</tr>
										</tbody>
									</table>
								</div>

								<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; width:1110px ; height:374px;" >
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all">
										<colgroup>
										<col align="" width="245px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										<col align="" width="65px">
										</colgroup>
										<tbody>
										<% int i = 0; %>
										<ezf:row ezfHyo="A">
											<% NSBL0310_ABMsg abMsg = bMsg.A.no(i++); %>
											<tr height="20px">
												<td><ezf:inputText name="mgrNm_A" ezfName="mgrNm_A" value="ZZZZZZZZZ1ZZZZZZZZZ2ZZZZZZZZZ3ZZZZZZZZZ4ZZZZZ" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"61\" size=\"36\""/></td>
												<td align="right"><ezf:label name="prevOpenTaskCnt_A" ezfName="prevOpenTaskCnt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td align="right">
													<% if (!"0".equals(abMsg.inprocTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="inprocTaskCnt_AL" ezfName="inprocTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'inprocTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'inprocTaskCnt');\"" otherAttr=" id=\"inprocTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="inprocTaskCnt_A" ezfName="inprocTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.cloTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="cloTaskCnt_AL" ezfName="cloTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'cloTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'cloTaskCnt');\"" otherAttr=" id=\"cloTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="cloTaskCnt_A" ezfName="cloTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.cancTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="cancTaskCnt_AL" ezfName="cancTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'cancTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'cancTaskCnt');\"" otherAttr=" id=\"cancTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="cancTaskCnt_A" ezfName="cancTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"00:00".equals(abMsg.xxRcvTm_A.getValue().toString())) { %>
														<ezf:anchor name="xxRcvTm_AL" ezfName="xxRcvTm_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'xxRcvTm');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'xxRcvTm');\"" otherAttr=" id=\"xxRcvTm_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="xxRcvTm_A" ezfName="xxRcvTm_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>00:00</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.toBeOptmTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="toBeOptmTaskCnt_AL" ezfName="toBeOptmTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'toBeOptmTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'toBeOptmTaskCnt');\"" otherAttr=" id=\"toBeOptmTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="toBeOptmTaskCnt_A" ezfName="toBeOptmTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.schdTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="schdTaskCnt_AL" ezfName="schdTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'schdTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'schdTaskCnt');\"" otherAttr=" id=\"schdTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="schdTaskCnt_A" ezfName="schdTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.asgTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="asgTaskCnt_AL" ezfName="asgTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'asgTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'asgTaskCnt');\"" otherAttr=" id=\"asgTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="asgTaskCnt_A" ezfName="asgTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.openTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="openTaskCnt_AL" ezfName="openTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'openTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'openTaskCnt');\"" otherAttr=" id=\"openTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="openTaskCnt_A" ezfName="openTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.hldTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="hldTaskCnt_AL" ezfName="hldTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'hldTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'hldTaskCnt');\"" otherAttr=" id=\"hldTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="hldTaskCnt_A" ezfName="hldTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.esclTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="esclTaskCnt_AL" ezfName="esclTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'esclTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'esclTaskCnt');\"" otherAttr=" id=\"esclTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="esclTaskCnt_A" ezfName="esclTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.vipTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="vipTaskCnt_AL" ezfName="vipTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'vipTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'vipTaskCnt');\"" otherAttr=" id=\"vipTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="vipTaskCnt_A" ezfName="vipTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
												<td align="right">
													<% if (!"0".equals(abMsg.attnTaskCnt_A.getValue().toString())) { %>
														<ezf:anchor name="attnTaskCnt_AL" ezfName="attnTaskCnt_AL" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'attnTaskCnt');\" onkeypress=\"sendServerForNSBL0320({EZF_ROW_NUMBER}, 'attnTaskCnt');\"" otherAttr=" id=\"attnTaskCnt_AL#{EZF_ROW_NUMBER}\"">
														<ezf:label name="attnTaskCnt_A" ezfName="attnTaskCnt_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													<% } else { %>
														<label>0</label>
													<% } %>
												</td>
											</tr>
										</ezf:row>
										</tbody>
									</table>
								</div>

								<div id="bottomTBL_TTL" style="width:1110px ; height:24px;" >
									<table border="1" cellpadding="1" cellspacing="0" id="" style="table-layout:fixed;word-break:break-all">
										<colgroup>
										<col align="center" width="245px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										<col align="center" width="65px">
										</colgroup>
										<tbody>
											<tr height="20px">
												<td>Total</td>
												<td align="right">
													<ezf:label name="prevOpenTaskCnt_T" ezfName="prevOpenTaskCnt_T" />
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.inprocTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="inprocTaskCnt_TL" ezfName="inprocTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'inprocTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'inprocTaskCnt');\"" otherAttr=" id=\"inprocTaskCnt_TL\"">
																<ezf:label name="inprocTaskCnt_T" ezfName="inprocTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="inprocTaskCnt_T" ezfName="inprocTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.cloTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="cloTaskCnt_TL" ezfName="cloTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'cloTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'cloTaskCnt');\"" otherAttr=" id=\"cloTaskCnt_TL\"">
																<ezf:label name="cloTaskCnt_T" ezfName="cloTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="cloTaskCnt_T" ezfName="cloTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.cancTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="cancTaskCnt_TL" ezfName="cancTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'cancTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'cancTaskCnt');\"" otherAttr=" id=\"cancTaskCnt_TL\"">
																<ezf:label name="cancTaskCnt_T" ezfName="cancTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="cancTaskCnt_T" ezfName="cancTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"00:00".equals(bMsg.xxRcvTm_T.getValue().toString())) { %>
															<ezf:anchor name="xxRcvTm_TL" ezfName="xxRcvTm_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'xxRcvTm');\" onkeypress=\"sendServerForNSBL0320(-1, 'xxRcvTm');\"" otherAttr=" id=\"xxRcvTm_TL\"">
																<ezf:label name="xxRcvTm_T" ezfName="xxRcvTm_T" />
															</ezf:anchor>
														<% } else { %>
															<label>00:00</label>
														<% } %>
													<% } else { %>
														<ezf:label name="xxRcvTm_T" ezfName="xxRcvTm_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.toBeOptmTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="toBeOptmTaskCnt_TL" ezfName="toBeOptmTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'toBeOptmTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'toBeOptmTaskCnt');\"" otherAttr=" id=\"toBeOptmTaskCnt_TL\"">
																<ezf:label name="toBeOptmTaskCnt_T" ezfName="toBeOptmTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="toBeOptmTaskCnt_T" ezfName="toBeOptmTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.schdTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="schdTaskCnt_TL" ezfName="schdTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'schdTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'schdTaskCnt');\"" otherAttr=" id=\"schdTaskCnt_TL\"">
																<ezf:label name="schdTaskCnt_T" ezfName="schdTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="schdTaskCnt_T" ezfName="schdTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.asgTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="asgTaskCnt_TL" ezfName="asgTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'asgTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'asgTaskCnt');\"" otherAttr=" id=\"asgTaskCnt_TL\"">
																<ezf:label name="asgTaskCnt_T" ezfName="asgTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="asgTaskCnt_T" ezfName="asgTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.openTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="openTaskCnt_TL" ezfName="openTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'openTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'openTaskCnt');\"" otherAttr=" id=\"openTaskCnt_TL\"">
																<ezf:label name="openTaskCnt_T" ezfName="openTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="openTaskCnt_T" ezfName="openTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.hldTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="hldTaskCnt_TL" ezfName="hldTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'hldTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'hldTaskCnt');\"" otherAttr=" id=\"hldTaskCnt_TL\"">
																<ezf:label name="hldTaskCnt_T" ezfName="hldTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="hldTaskCnt_T" ezfName="hldTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.esclTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="esclTaskCnt_T" ezfName="esclTaskCnt_T" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'esclTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'esclTaskCnt');\"" otherAttr=" id=\"esclTaskCnt_T\"">
																<ezf:label name="esclTaskCnt_T" ezfName="esclTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="esclTaskCnt_T" ezfName="esclTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.vipTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="vipTaskCnt_TL" ezfName="vipTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'vipTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'vipTaskCnt');\"" otherAttr=" id=\"vipTaskCnt_TL\"">
																<ezf:label name="vipTaskCnt_T" ezfName="vipTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="vipTaskCnt_T" ezfName="vipTaskCnt_T" />
													<% } %>
												</td>
												<td align="right">
													<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).svcMgrPsnCd_A)) { %>
														<% if (!"0".equals(bMsg.attnTaskCnt_T.getValue().toString())) { %>
															<ezf:anchor name="attnTaskCnt_TL" ezfName="attnTaskCnt_TL" ezfEmulateBtn="1" ezfGuard="OpenWin_SelectSRSummaryCriteria" otherEvent1=" onmousedown=\"sendServerForNSBL0320(-1, 'attnTaskCnt');\" onkeypress=\"sendServerForNSBL0320(-1, 'attnTaskCnt');\"" otherAttr=" id=\"attnTaskCnt_TL\"">
																<ezf:label name="attnTaskCnt_T" ezfName="attnTaskCnt_T" />
															</ezf:anchor>
														<% } else { %>
															<label>0</label>
														<% } %>
													<% } else { %>
														<ezf:label name="attnTaskCnt_T" ezfName="attnTaskCnt_T" />
													<% } %>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								
							</td>
						</tr>
					</table>
				</center>
<%-- ######################################## TO   (COMMON)DETAIL ######################################## --%>
				<!--<input type="hidden" name="xxEvnTpTxt" ezfname="xxEvnTpTxt" value="" >-->
				<ezf:inputHidden name="xxScrEventNm" ezfName="xxScrEventNm" />
			</div>
		</td>
	</tr>
	</table>
</center>

<%-- ###SCRIPT --%>
<script type="text/javascript">
	function onFocusEvent(item){
		if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + item.value.charAt(3) + item.value.charAt(4);
		}
	}

	function onBlurEvent(item){
		if(item.value.length === 1){
			item.value = '0' + item.value.charAt(0) + ':00';
		}else if(item.value.length === 2){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':00';
		}else if(item.value.length === 3){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + '0';
		}else if(item.value.length === 4){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}else if(item.value.length === 5){
			item.value = item.value.charAt(0) + item.value.charAt(1) + ':' + item.value.charAt(2) + item.value.charAt(3);
		}
	}
</script>

<!-- -->
<script type="text/javascript">
	function sendServerForNSBL0320 (no, colNm){
		document.getElementById('xxScrEventNm').value = colNm;
		sendServer('OpenWin_SelectSRSummaryCriteria', no);
	}
</script>


<%-- **** Task specific area ends here **** --%>
