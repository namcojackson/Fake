<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160302130721 --%>
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
			<input type="hidden" name="pageID" value="NSAL1020Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Supply Order Serial# Search">

<%@ page import="business.servlet.NSAL1020.NSAL1020BMsg" %>
<%@ page import="business.servlet.NSAL1020.NSAL1020_ABMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NSAL1020BMsg bMsg = (NSAL1020BMsg)databean.getEZDBMsg(); %>

<center>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td>
			<div>
<!-- ################################################## Search Criteria - [START] ################################################## -->
					<table style="margin-top: 5px; margin-left: 5px" border="0">
						<colgroup>
							<col width="50">
							<col width="10">
							<col width="100">
							<col width="10">
							<col width="100">
						</colgroup>
						<tbody>
							<tr>
									<td class="stab"><ezf:anchor name="serNum_HA" ezfName="" ezfEmulateBtn="1" ezfGuard="OpenWin_SerNumSrch" >Serial#(*)</ezf:anchor></td>
									<td>&nbsp;</td>
									<td><ezf:inputText name="serNum_H" ezfName="serNum_H" value="MSK00187" otherAttr=" id=\"serNum_H\" size=\"12\" ezftoupper=\"\""/></td>
									<td>&nbsp;</td>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" otherAttr=" id=\"Search\""/></td>
							</tr>
						</tbody>
					</table>
<!-- ################################################## Search Criteria - [E N D] ################################################## -->

<!-- ################################################## Search Result   - [START] ################################################## -->
					<center>
<!-- ######################################## TO (COMMON)PAGENATION #################################### -->
					<table width="1000px">
						<tr>
							<td>
								<div id="topTBL" style="overflow-x:hidden; overflow-y:none; width:983px;">
									<table border="1" cellpadding="1" cellspacing="0" style="table-layout:fixed" width="983px">
										<colgroup>
											<col align="center" width="81px">	<!-- Serial# -->
											<col align="center" width="200px">	<!-- Model -->
											<col align="center" width="81px">	<!-- Orderable -->
											<col align="center" width="81px">	<!-- Contract# -->
											<col align="center" width="180px">	<!-- Customer Name -->
											<col align="center" width="180px">	<!-- Address -->
											<col align="center" width="180px">	<!-- Comments -->
										</colgroup>
										<tbody>
											<tr>
												<td class="pClothBs">Serial#</td>
												<td class="pClothBs">Model</td>
												<td class="pClothBs">Orderable</td>
												<td class="pClothBs">Contract#</td>
												<td class="pClothBs">Customer Name</td>
												<td class="pClothBs">Address</td>
												<td class="pClothBs">Comments</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div id="bottomTBL" style="overflow-x:hidden; overflow-y:scroll; width:1000px; height:450px;" onScroll="synchroScrollLeft(this.id, new Array('topTBL'));">
									<table border="1" cellpadding="1" cellspacing="0" id="A" style="table-layout:fixed;word-break:break-all" width="983px">
										<colgroup>
											<col align="center" width="81px">	<!-- Serial# -->
											<col align="center" width="200px">	<!-- Model -->
											<col align="center" width="81px">	<!-- Orderable -->
											<col align="center" width="81px">	<!-- Contract# -->
											<col align="center" width="180px">	<!-- Customer Name -->
											<col align="center" width="180px">	<!-- Address -->
											<col align="center" width="180px">	<!-- Comments -->
										</colgroup>
										<tbody>
											<% int i = 0; %>
											<ezf:row ezfHyo="A">
												<tr>
													<td class="stab">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxMsgTxt_D)) { %>
															<ezf:label name="serNum_D" ezfName="serNum_D" ezfHyo="A" ezfArrayNo="0" />
														<% } else { %>
															<ezf:anchor name="serNum_DA" ezfName="serNum_DA" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" ><ezf:label name="serNum_D" ezfName="serNum_D" ezfHyo="A" ezfArrayNo="0" /></ezf:anchor>
														<% } %>
													</td>
													<td class="stab" align="left">
														<ezf:label name="t_MdlNm_D" ezfName="t_MdlNm_D" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td class="stab">
														<ezf:label name="xxRsltFlg_D" ezfName="xxRsltFlg_D" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td class="stab">
														<ezf:label name="dsContrNum_D" ezfName="dsContrNum_D" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td class="stab" align="left">
														<ezf:label name="dsAcctNm_D" ezfName="dsAcctNm_D" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td class="stab" align="left">
														<ezf:label name="xxAllLineAddr_D" ezfName="xxAllLineAddr_D" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td class="stab" align="left">
														<ezf:label name="xxMsgTxt_D" ezfName="xxMsgTxt_D" ezfHyo="A" ezfArrayNo="0" />
													</td>
												</tr>
												<% i++; %>
											</ezf:row>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</table>
					</center>
<!-- ################################################## Search Result   - [E N D] ################################################## -->
			</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
