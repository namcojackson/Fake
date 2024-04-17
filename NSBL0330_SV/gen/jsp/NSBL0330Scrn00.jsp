<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160309171759 --%>
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
			<input type="hidden" name="pageID" value="NSBL0330Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Service Request By Summary Criteria">
<center>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>

				<%-- include S21BusinessProcessTAB --%>
				<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>

				<ezf:skip>
				<div class="pTab_HEAD">
					<ul>
						<table width="100%"  border="0" cellspacing="0" cellpadding="0"> 
							<tr>
								<td width="96%">
									<li title = "Service Request By Summary Criteria" class="pTab_ON" ><a href="#">Srch Smry</a></li>
								</td>

								<td valign="bottom" align="center">
									<a href="#"><img id ="PrevPageBtn" src="./img/tab/PrevPageBtn.gif" alt="Prev" border="0"></a>
								</td>
								<td valign="bottom" align="center">
									<a href="#"><img id ="NextPageBtn" src="./img/tab/NextPageBtn.gif" alt="Next" border="0"></a>
								</td>

							</tr>
						</table>
					</ul>
				</div>
				</ezf:skip>

				<div class="pTab_BODY">
					<br>
<%-- ######################################## DETAIL ######################################## --%>
<%@ page import="business.servlet.NSBL0330.NSBL0330BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<%  NSBL0330BMsg bMsg = (NSBL0330BMsg) databean.getEZDBMsg(); %>
<% int i = 0; %>
					<ezf:inputHidden name="xxSfxKeyTxt" ezfName="xxSfxKeyTxt" otherAttr=" id=\"xxSfxKeyTxt\""/>
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
							<td class="pOut">50</td>
							<td class="stab">of</td>
							<td class="pOut">100</td>
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
									<col width="10">
									<col width="55">
									<col width="1">
									<col width="55">
									<col width="1">
									<col align="right">
									<tr>
										<td></td>
										<td><ezf:inputButton name="PrevMgr" value="Prev Mgr" htmlClass="pBtn4" /></td>
										<td></td>
										<td><ezf:inputButton name="NextMgr" value="Next Mgr" htmlClass="pBtn4" /></td>
										<td></td>
										<td>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum_H" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum_H" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum_H" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
						<col align="left" valign="top">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<col width="152">
									<tr>
										<td align="left" valign="top">
											<div id="TopLeftTBL" style="overflow-x:none; overflow-y:none; width:150; float:left;">
												<table border="0" cellpadding="1" cellspacing="0"  style="table-layout: fixed;">
													<col width="150" align="center">
													<tr height="24">
														<td></td>
													</tr>
												</table>
											</div>

											<div id="LeftTBL" style="overflow-x:hidden; overflow-y:hidden; height:486; width:150; float:left;" onScroll="synchroScrollTop( this.id, new Array( 'RightTBL' ) );">
												<!-- drill down -->
												<table border="1" cellpadding="1" cellspacing="0" id="A1" style="table-layout: fixed;">
													<col width="150" align="center">
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (i == 0) { %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_00)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_00" ezfName="xxScrItem130Txt_00" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_00\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_01)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_01" ezfName="xxScrItem130Txt_01" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_01\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_02)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_02" ezfName="xxScrItem130Txt_02" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_02\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_03)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_03" ezfName="xxScrItem130Txt_03" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_03\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_04)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_04" ezfName="xxScrItem130Txt_04" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_04\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_05)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_05" ezfName="xxScrItem130Txt_05" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_05\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_06)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_06" ezfName="xxScrItem130Txt_06" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_06\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_07)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_07" ezfName="xxScrItem130Txt_07" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_07\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_08)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_08" ezfName="xxScrItem130Txt_08" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_08\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_09)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_09" ezfName="xxScrItem130Txt_09" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_09\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_10)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_10" ezfName="xxScrItem130Txt_10" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_10\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_11)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_11" ezfName="xxScrItem130Txt_11" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_11\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_12)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_12" ezfName="xxScrItem130Txt_12" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_12\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_13)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_13" ezfName="xxScrItem130Txt_13" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_13\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_14)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_14" ezfName="xxScrItem130Txt_14" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_14\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_15)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_15" ezfName="xxScrItem130Txt_15" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_15\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_16)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_16" ezfName="xxScrItem130Txt_16" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_16\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_17)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_17" ezfName="xxScrItem130Txt_17" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_17\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_18)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_18" ezfName="xxScrItem130Txt_18" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_18\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_19)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_19" ezfName="xxScrItem130Txt_19" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_19\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_20)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_20" ezfName="xxScrItem130Txt_20" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_20\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_21)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_21" ezfName="xxScrItem130Txt_21" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_21\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_22)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_22" ezfName="xxScrItem130Txt_22" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_22\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_23)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_23" ezfName="xxScrItem130Txt_23" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_23\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_24)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_24" ezfName="xxScrItem130Txt_24" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_24\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_25)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_25" ezfName="xxScrItem130Txt_25" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_25\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_26)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_26" ezfName="xxScrItem130Txt_26" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_26\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_27)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_27" ezfName="xxScrItem130Txt_27" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_27\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_28)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_28" ezfName="xxScrItem130Txt_28" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_28\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_29)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_29" ezfName="xxScrItem130Txt_29" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_29\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_30)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_30" ezfName="xxScrItem130Txt_30" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_30\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_31)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_31" ezfName="xxScrItem130Txt_31" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_31\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_32)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_32" ezfName="xxScrItem130Txt_32" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_32\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_33)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_33" ezfName="xxScrItem130Txt_33" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_33\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_34)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_34" ezfName="xxScrItem130Txt_34" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_34\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_35)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_35" ezfName="xxScrItem130Txt_35" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_35\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_36)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_36" ezfName="xxScrItem130Txt_36" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_36\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_37)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_37" ezfName="xxScrItem130Txt_37" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_37\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_38)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_38" ezfName="xxScrItem130Txt_38" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_38\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_39)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_39" ezfName="xxScrItem130Txt_39" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_39\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_40)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_40" ezfName="xxScrItem130Txt_40" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_40\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_41)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_41" ezfName="xxScrItem130Txt_41" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_41\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_42)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_42" ezfName="xxScrItem130Txt_42" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_42\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_43)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_43" ezfName="xxScrItem130Txt_43" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_43\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_44)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_44" ezfName="xxScrItem130Txt_44" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_44\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_45)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_45" ezfName="xxScrItem130Txt_45" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_45\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_46)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_46" ezfName="xxScrItem130Txt_46" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_46\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_47)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_47" ezfName="xxScrItem130Txt_47" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_47\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_48)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_48" ezfName="xxScrItem130Txt_48" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_48\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
															<% if (ZYPCommonFunc.hasValue(bMsg.A.no(i).xxScrItem130Txt_49)) { %>
																<tr height="22">
																	<td><ezf:inputText name="xxScrItem130Txt_49" ezfName="xxScrItem130Txt_49" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem130Txt_49\" tabindex=\"-1\" style=\"background-color:transparent;border-style:none;\""/></td>
																</tr>
															<% } %>
														<% } %>
														<% i++; %>
													</ezf:row>
												</table>
											</div>
										</td>
										<td align="left" valign="top">
											<div id="topRightTBL" style="overflow-x:hidden; width:902; overflow-y:none;">
												<!-- manager -->
												<table border="1" cellpadding="1" cellspacing="0" id="A2" style="table-layout: fixed;">
													<tr height="22">
														<ezf:row ezfHyo="A">
															<td class="pClothBs" width="150" align="center"><ezf:inputText name="xxScrItem81Txt" ezfName="xxScrItem81Txt" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" id=\"xxScrItem81Txt\" tabindex=\"-1\" style=\"color:white;background-color:transparent;border-style:none;\""/></td>
														</ezf:row>
													</tr>
												</table>
											</div>

											<div id="RightTBL" style="overflow-x:scroll; width:919; overflow-y:scroll; height:503;" onScroll="synchroScrollTop( this.id, new Array( 'LeftTBL' ) ); synchroScrollLeft( this.id, new Array( 'topRightTBL' ) );">
												<!-- search -->
												<table border="1" cellpadding="1" cellspacing="0" id="A3" style="table-layout: fixed;">
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_00)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_00" ezfName="xxTotCnt_00" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('00')\" onkeypress=\"setSfxKeyTxt('00')\"" ><ezf:label name="xxTotCnt_00" ezfName="xxTotCnt_00" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_01)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_01" ezfName="xxTotCnt_01" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('01')\" onkeypress=\"setSfxKeyTxt('01')\"" ><ezf:label name="xxTotCnt_01" ezfName="xxTotCnt_01" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_02)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_02" ezfName="xxTotCnt_02" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('02')\" onkeypress=\"setSfxKeyTxt('02')\"" ><ezf:label name="xxTotCnt_02" ezfName="xxTotCnt_02" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_03)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_03" ezfName="xxTotCnt_03" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('03')\" onkeypress=\"setSfxKeyTxt('03')\"" ><ezf:label name="xxTotCnt_03" ezfName="xxTotCnt_03" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_04)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_04" ezfName="xxTotCnt_04" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('04')\" onkeypress=\"setSfxKeyTxt('04')\"" ><ezf:label name="xxTotCnt_04" ezfName="xxTotCnt_04" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_05)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_05" ezfName="xxTotCnt_05" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('05')\" onkeypress=\"setSfxKeyTxt('05')\"" ><ezf:label name="xxTotCnt_05" ezfName="xxTotCnt_05" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_06)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_06" ezfName="xxTotCnt_06" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('06')\" onkeypress=\"setSfxKeyTxt('06')\"" ><ezf:label name="xxTotCnt_06" ezfName="xxTotCnt_06" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_07)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_07" ezfName="xxTotCnt_07" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('07')\" onkeypress=\"setSfxKeyTxt('07')\"" ><ezf:label name="xxTotCnt_07" ezfName="xxTotCnt_07" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_08)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_08" ezfName="xxTotCnt_08" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('08')\" onkeypress=\"setSfxKeyTxt('08')\"" ><ezf:label name="xxTotCnt_08" ezfName="xxTotCnt_08" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_09)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_09" ezfName="xxTotCnt_09" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('09')\" onkeypress=\"setSfxKeyTxt('09')\"" ><ezf:label name="xxTotCnt_09" ezfName="xxTotCnt_09" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_10)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_10" ezfName="xxTotCnt_10" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('10')\" onkeypress=\"setSfxKeyTxt('10')\"" ><ezf:label name="xxTotCnt_10" ezfName="xxTotCnt_10" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_11)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_11" ezfName="xxTotCnt_11" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('11')\" onkeypress=\"setSfxKeyTxt('11')\"" ><ezf:label name="xxTotCnt_11" ezfName="xxTotCnt_11" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_12)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_12" ezfName="xxTotCnt_12" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('12')\" onkeypress=\"setSfxKeyTxt('12')\"" ><ezf:label name="xxTotCnt_12" ezfName="xxTotCnt_12" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_13)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_13" ezfName="xxTotCnt_13" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('13')\" onkeypress=\"setSfxKeyTxt('13')\"" ><ezf:label name="xxTotCnt_13" ezfName="xxTotCnt_13" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_14)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_14" ezfName="xxTotCnt_14" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('14')\" onkeypress=\"setSfxKeyTxt('14')\"" ><ezf:label name="xxTotCnt_14" ezfName="xxTotCnt_14" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_15)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_15" ezfName="xxTotCnt_15" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('15')\" onkeypress=\"setSfxKeyTxt('15')\"" ><ezf:label name="xxTotCnt_15" ezfName="xxTotCnt_15" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_16)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_16" ezfName="xxTotCnt_16" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('16')\" onkeypress=\"setSfxKeyTxt('16')\"" ><ezf:label name="xxTotCnt_16" ezfName="xxTotCnt_16" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_17)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_17" ezfName="xxTotCnt_17" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('17')\" onkeypress=\"setSfxKeyTxt('17')\"" ><ezf:label name="xxTotCnt_17" ezfName="xxTotCnt_17" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_18)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_18" ezfName="xxTotCnt_18" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('18')\" onkeypress=\"setSfxKeyTxt('18')\"" ><ezf:label name="xxTotCnt_18" ezfName="xxTotCnt_18" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_19)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_19" ezfName="xxTotCnt_19" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('19')\" onkeypress=\"setSfxKeyTxt('19')\"" ><ezf:label name="xxTotCnt_19" ezfName="xxTotCnt_19" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_20)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_20" ezfName="xxTotCnt_20" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('20')\" onkeypress=\"setSfxKeyTxt('20')\"" ><ezf:label name="xxTotCnt_20" ezfName="xxTotCnt_20" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_21)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_21" ezfName="xxTotCnt_21" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('21')\" onkeypress=\"setSfxKeyTxt('21')\"" ><ezf:label name="xxTotCnt_21" ezfName="xxTotCnt_21" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_22)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_22" ezfName="xxTotCnt_22" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('22')\" onkeypress=\"setSfxKeyTxt('22')\"" ><ezf:label name="xxTotCnt_22" ezfName="xxTotCnt_22" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_23)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_23" ezfName="xxTotCnt_23" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('23')\" onkeypress=\"setSfxKeyTxt('23')\"" ><ezf:label name="xxTotCnt_23" ezfName="xxTotCnt_23" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_24)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_24" ezfName="xxTotCnt_24" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('24')\" onkeypress=\"setSfxKeyTxt('24')\"" ><ezf:label name="xxTotCnt_24" ezfName="xxTotCnt_24" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_25)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_25" ezfName="xxTotCnt_25" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('25')\" onkeypress=\"setSfxKeyTxt('25')\"" ><ezf:label name="xxTotCnt_25" ezfName="xxTotCnt_25" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_26)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_26" ezfName="xxTotCnt_26" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('26')\" onkeypress=\"setSfxKeyTxt('26')\"" ><ezf:label name="xxTotCnt_26" ezfName="xxTotCnt_26" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_27)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_27" ezfName="xxTotCnt_27" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('27')\" onkeypress=\"setSfxKeyTxt('27')\"" ><ezf:label name="xxTotCnt_27" ezfName="xxTotCnt_27" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_28)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_28" ezfName="xxTotCnt_28" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('28')\" onkeypress=\"setSfxKeyTxt('28')\"" ><ezf:label name="xxTotCnt_28" ezfName="xxTotCnt_28" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_29)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_29" ezfName="xxTotCnt_29" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('29')\" onkeypress=\"setSfxKeyTxt('29')\"" ><ezf:label name="xxTotCnt_29" ezfName="xxTotCnt_29" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_30)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_30" ezfName="xxTotCnt_30" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('30')\" onkeypress=\"setSfxKeyTxt('30')\"" ><ezf:label name="xxTotCnt_30" ezfName="xxTotCnt_30" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_31)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_31" ezfName="xxTotCnt_31" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('31')\" onkeypress=\"setSfxKeyTxt('31')\"" ><ezf:label name="xxTotCnt_31" ezfName="xxTotCnt_31" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_32)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_32" ezfName="xxTotCnt_32" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('32')\" onkeypress=\"setSfxKeyTxt('32')\"" ><ezf:label name="xxTotCnt_32" ezfName="xxTotCnt_32" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_33)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_33" ezfName="xxTotCnt_33" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('33')\" onkeypress=\"setSfxKeyTxt('33')\"" ><ezf:label name="xxTotCnt_33" ezfName="xxTotCnt_33" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_34)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_34" ezfName="xxTotCnt_34" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('34')\" onkeypress=\"setSfxKeyTxt('34')\"" ><ezf:label name="xxTotCnt_34" ezfName="xxTotCnt_34" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_35)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_35" ezfName="xxTotCnt_35" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('35')\" onkeypress=\"setSfxKeyTxt('35')\"" ><ezf:label name="xxTotCnt_35" ezfName="xxTotCnt_35" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_36)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_36" ezfName="xxTotCnt_36" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('36')\" onkeypress=\"setSfxKeyTxt('36')\"" ><ezf:label name="xxTotCnt_36" ezfName="xxTotCnt_36" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_37)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_37" ezfName="xxTotCnt_37" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('37')\" onkeypress=\"setSfxKeyTxt('37')\"" ><ezf:label name="xxTotCnt_37" ezfName="xxTotCnt_37" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_38)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_38" ezfName="xxTotCnt_38" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('38')\" onkeypress=\"setSfxKeyTxt('38')\"" ><ezf:label name="xxTotCnt_38" ezfName="xxTotCnt_38" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_39)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_39" ezfName="xxTotCnt_39" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('39')\" onkeypress=\"setSfxKeyTxt('39')\"" ><ezf:label name="xxTotCnt_39" ezfName="xxTotCnt_39" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_40)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_40" ezfName="xxTotCnt_40" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('40')\" onkeypress=\"setSfxKeyTxt('40')\"" ><ezf:label name="xxTotCnt_40" ezfName="xxTotCnt_40" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_41)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_41" ezfName="xxTotCnt_41" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('41')\" onkeypress=\"setSfxKeyTxt('41')\"" ><ezf:label name="xxTotCnt_41" ezfName="xxTotCnt_41" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_42)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_42" ezfName="xxTotCnt_42" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('42')\" onkeypress=\"setSfxKeyTxt('42')\"" ><ezf:label name="xxTotCnt_42" ezfName="xxTotCnt_42" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_43)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_43" ezfName="xxTotCnt_43" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('43')\" onkeypress=\"setSfxKeyTxt('43')\"" ><ezf:label name="xxTotCnt_43" ezfName="xxTotCnt_43" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_44)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_44" ezfName="xxTotCnt_44" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('44')\" onkeypress=\"setSfxKeyTxt('44')\"" ><ezf:label name="xxTotCnt_44" ezfName="xxTotCnt_44" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_45)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_45" ezfName="xxTotCnt_45" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('45')\" onkeypress=\"setSfxKeyTxt('45')\"" ><ezf:label name="xxTotCnt_45" ezfName="xxTotCnt_45" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_46)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_46" ezfName="xxTotCnt_46" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('46')\" onkeypress=\"setSfxKeyTxt('46')\"" ><ezf:label name="xxTotCnt_46" ezfName="xxTotCnt_46" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_47)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_47" ezfName="xxTotCnt_47" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('47')\" onkeypress=\"setSfxKeyTxt('47')\"" ><ezf:label name="xxTotCnt_47" ezfName="xxTotCnt_47" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_48)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_48" ezfName="xxTotCnt_48" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('48')\" onkeypress=\"setSfxKeyTxt('48')\"" ><ezf:label name="xxTotCnt_48" ezfName="xxTotCnt_48" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
													</ezf:row>
													<% i = 0; %>
													<ezf:row ezfHyo="A">
														<% if (ZYPCommonFunc.hasValue(bMsg.A.no(0).xxScrItem130Txt_49)) { %>
															<% if (i == 0) { %>
																<tr height="22">
															<% } %>
															<td width="150" align="right"><ezf:anchor name="xxTotCnt_49" ezfName="xxTotCnt_49" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select" otherEvent1=" onmousedown=\"setSfxKeyTxt('49')\" onkeypress=\"setSfxKeyTxt('49')\"" ><ezf:label name="xxTotCnt_49" ezfName="xxTotCnt_49" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"10\""/></ezf:anchor>
															<% i++; %>
															<% if (i == bMsg.A.getValidCount()) { %>
																</tr>
															<% } %>
														<% } %>
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
			</td>
		</tr>
	</table>
</center>
<script type="text/javascript">
function setSfxKeyTxt(sfxKeyTxt) {
	document.getElementById ("xxSfxKeyTxt").value = sfxKeyTxt;
	return true;
}
</script>

<%-- **** Task specific area ends here **** --%>
