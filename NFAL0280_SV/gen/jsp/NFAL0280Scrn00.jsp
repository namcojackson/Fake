<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20240208143254 --%>
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
			<input type="hidden" name="pageID" value="NFAL0280Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Accrual Reversal Screen">
			
			<%-- ********************** --%>
			<%-- *** Upper Tab Area *** --%>
			<%-- ********************** --%>
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

			<%-- ######################################## HEADER ######################################## --%>
			<center>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<ezf:skip>
							<!-- include S21BusinessProcessTAB -->
							<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
							</ezf:skip>

							<div class="pTab_BODY">

								<%-- ######################################## DETAIL ######################################## --%>
								<table style="MARGIN-LEFT: 5px; MARGIN-TOP: 0px; border: 0;">
									<col width="160" align="left">
									<col width="300"  align="left">
									<col width="10"  align="left">
									<col width="140" align="left">
									<col width="100"  align="left">
									<col width="10"  align="left">
									<col width="110" align="left">
									<tr height="20">
										<td class="stab">Charge Type</td>
										<td class="stab" style="vertical-align: middle;">
												<ezf:select name="svcInvChrgTpCd_A1" ezfName="svcInvChrgTpCd_A1" ezfCodeName="svcInvChrgTpCd_A2" ezfDispName="svcInvChrgTpDescTxt_A1" otherEvent1=" onchange=\"sendServer('OnChange_ChargeType')\"" otherAttr=" id=\"svcInvChrgTpCd_A1\" style=\"width:160px;\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td class="stab">Target Month</td>
										<td class="stab" style="vertical-align: middle;">
											<ezf:inputText name="glPerNm_A1" ezfName="glPerNm_A1" otherEvent1=" onblur=\"sendServer('OnBlur_TargetMonth')\"" otherAttr=" size=\"8\" maxlength=\"8\" id=\"glPerNm_A1\" ezftoupper=\"\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
										<td class="stab">Target Data to be reversed</td>
										<td class="stab" style="vertical-align: middle;">
											<ezf:select name="xxDtNm_A1" ezfName="xxDtNm_A1" ezfCodeName="xxDtNm_A2" ezfDispName="xxDtTm_A1" otherAttr=" id=\"xxDtNm_A1\" style=\"width:160px;\""/>
										</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr height="20">
                                        <td class="stab">Post GL Date</td>
                                        <td class="stab"><ezf:inputRadio name="xxTrgtDtCd_A1" ezfName="xxTrgtDtCd_A1" value="1" otherAttr=" id=\"xxTrgtDtCd_A1\""/>Today</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
										<td class="stab"></td>
									 	<td class="stab"><ezf:inputRadio name="xxTrgtDtCd_A1" ezfName="xxTrgtDtCd_A1" value="2" otherAttr=" id=\"xxTrgtDtCd_A1\""/>Previous Month End (For correction on Day 1)</td>
									<!-- <tr height="20"> -->
									<!-- </tr> -->
								</table>
							</div>
						</td>
					</tr>
				</table>
			</center>

<%-- **** Task specific area ends here **** --%>
