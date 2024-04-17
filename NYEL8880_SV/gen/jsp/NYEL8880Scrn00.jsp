<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160913202058 --%>
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
			<input type="hidden" name="pageID" value="NYEL8880Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Assigner Search">
			
							<table width="100%" border="0" cellpadding="3" cellspacing="1" >
								<col align="right">
								<col>
								<col width="5">
								<col align="right">
								<col>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="stab">Assigner Code</td>
										<td>
											<ezf:inputText name="usrNm" ezfName="usrNm" otherAttr=" size=\"40\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
										<td></td>
										<td class="stab">Last Name (*)</td>
										<td>
											<ezf:inputText name="lastNm" ezfName="lastNm" otherAttr=" size=\"40\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
									</tr>
									<tr>
										<td class="stab">First Name (*)</td>
										<td>
											<ezf:inputText name="firstNm" ezfName="firstNm" otherAttr=" size=\"40\" maxlength=\"30\" ezftoupper=\"\""/>
										</td>
										<td>
										</td>
									</tr>
									<tr>
										<td colspan="5" align="right">
											<ezf:inputButton name="Search" value="Search" htmlClass="pBtn7" />
										</td>
									</tr>

							</table>

							<hr size="1">
							
							<table align="center" width="100%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="7%">
								<col width="72%">
								<tr>
									<td align="right">
										<%-- Pagenation --%>
										<table width="100%">
											<%--
											<table border="0" cellpadding="1" cellspacing="0">
												<col width="54"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="16"  align="center">
												<col width="40"  align="right">
												<col width="10">
												<col>
												<col width="10">
												<col width="50">
												<tr>
													<td class="stab">Showing</td>
													<td class="pOut">1</td>
													<td class="stab">to</td>
													<td class="pOut">3</td>
													<td class="stab">of</td>
													<td class="pOut">3</td>
													<td>&nbsp;</td>
													<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn3" /></td>
													<td></td>
													<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn3" /></td>
												</tr>
											</table>
											--%>
											<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
												<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
												<jsp:param name="TableNm"     value="A" />
												<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
												<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
												<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
											</jsp:include>
										</table>
									</td>
								</tr>
							</table>

							<table align="center" width="100%" cellpadding="0" cellspacing="0" border="0" >
							<tr>
							<td>
								<table border="1" cellspacing="0" cellpadding="1" style="text-align:center;">
									<col width="280">	<!-- Assigner -->
									<col width="290">	<!-- Last Nm -->
									<col width="220">	<!-- First Nm -->
									<tr>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'usrNm_A')">Assigner<img id="sortIMG.usrNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'lastNm_A')">Last Name<img id="sortIMG.lastNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort('A', 'firstNm_A')">First Name<img id="sortIMG.firstNm_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
								<div  id="rightTbl" style="width:1000px; height:380px; display:block; overflow-y:scroll; margin:0px; padding:0px;">
									<table id="A" border="1" cellspacing="0" cellpadding="1">
										<col width="280" align="left">		<!-- Assign From -->
										<col width="290" align="left">		<!-- Last Name -->
										<col width="220" align="left">		<!-- First Name -->
										<tbody>
											<ezf:row ezfHyo="A">
												<tr height="28px">
													<td>
														<ezf:anchor name="usrNm_A" ezfName="usrNm_A" ezfHyo="A" ezfArrayNo="0" ezfEmulateBtn="1" ezfGuard="Select_UsrNm" >
															<ezf:label name="usrNm_A" ezfName="usrNm_A" ezfHyo="A" ezfArrayNo="0" />
														</ezf:anchor>
													</td>
													<td>
														<ezf:label name="lastNm_A" ezfName="lastNm_A" ezfHyo="A" ezfArrayNo="0" />
													</td>
													<td>
														<ezf:label name="fistNm_A" ezfName="firstNm_A" ezfHyo="A" ezfArrayNo="0" />
													</td>
												</tr>
											</ezf:row>
										</tbody>
									</table>
								</div>
								</td>
								</tr>
							</table>


<%-- **** Task specific area ends here **** --%>
