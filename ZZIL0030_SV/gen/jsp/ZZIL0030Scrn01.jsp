<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171218105552 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0030Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Target I/F Setting Master Edit">

<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
<!-- ######################################## HEADER ######################################## -->
				<!-- include S21BusinessProcessTAB -->
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
				<div class="pTab_BODY">
					<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="48">
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td width="32">
							</td>
							<td>
								<table align="center" height="72" width="930">
									<col valian="top">
									<tr>
										<td>
											<table align="center" border="0">
												<col width="224">
												<col width="408">
												<col width="280">
												<tr height="32">
													<td class="stab"><label>Global Company Code</label></td>
													<td class="stub"><ezf:inputText name="glblCmpyCd" ezfName="glblCmpyCd" value="ADB" otherAttr=" size=\"4\" maxlength=\"4\""/></td>
													<td class="stab"></td>
												</tr>
												<tr height="32">
													<td class="stub"><label>Interface ID</label></td>
													<td class="stub"><ezf:inputText name="intfcId_2" ezfName="intfcId_2" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
													<td class="stab"></td>
												</tr>
												<tr height="32">
													<td class="stub"><label>Business API ID</label></td>
													<td class="stub"><ezf:inputText name="bizApiId_2" ezfName="bizApiId_2" otherAttr=" size=\"12\" maxlength=\"12\" ezftoupper=\"\""/></td>
													<td class="stab"></td>
												</tr>
												<tr height="32">
													<td class="stub"><label>Interface ID Description</label></td>
													<td class="stub"><ezf:inputText name="intfcIdDescTxt_2" ezfName="intfcIdDescTxt_2" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
													<td class="stab"></td>
												</tr>
												<tr height="32">
													<td class="stub"><label>Target Batch job Name</label></td>
													<td class="stub"><ezf:inputText name="trgtBatJobNm_2" ezfName="trgtBatJobNm_2" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
													<td class="stab"></td>
												</tr>
												<tr height="32">
													<td class="stub"><label>Target Batch job Description</label></td>
													<td class="stub"><ezf:inputText name="trgtBatJobDescTxt_2" ezfName="trgtBatJobDescTxt_2" otherAttr=" size=\"50\" maxlength=\"50\""/></td>
													<td class="stab"></td>
												</tr>
											</table>
											<table border="0">
												<col width="224">
												<col width="36">
												<col width="5">
												<col width="44">
												<tr height="32">
													<td class="stub"><label>Active Flag</label></td>
													<td class="stab"><ezf:inputRadio name="actvFlg_AF" ezfName="actvFlg_AF" value="Y" /><label>ON</label></td>
													<td class="stub"></td>
													<td class="stab"><ezf:inputRadio name="actvFlg_AF" ezfName="actvFlg_AF" value="N" /><label>OFF</label></td>
												</tr>
												<tr height="32">
													<td class="stub"><label>Force Queuing Enable Flag</label></td>
													<td class="stab"><ezf:inputRadio name="frceQueueEnblFlg_FF" ezfName="frceQueueEnblFlg_FF" value="Y" /><label>ON</label></td>
													<td class="stub"></td>
													<td class="stab"><ezf:inputRadio name="frceQueueEnblFlg_FF" ezfName="frceQueueEnblFlg_FF" value="N" /><label>OFF</label></td>
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
