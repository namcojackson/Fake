<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20140222030201 --%>
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
			<input type="hidden" name="pageID" value="ZZKL0010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Create Map File">
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			<!--
			<div class="pTab_HEAD">
				<ul>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="96%">
								<div>
									<li title="Record Monitor"				 class="pTab_ON" ><a href="#">Record Monitor</a></li>
									<li title="Mainte Screen"			 class="pTab_OFF"><a href="#">Mainte Screen</a></li>
									<li title="Config" class="pTab_OFF"><a href="#">Config</a></li>
								</div>
							</td>
						</tr>
					</table>
				</ul>
			</div>
			-->
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<tr>
						<td>
							<!-- ********* 1 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="11">
											<col width="80">
											<col width="200">
											<col width="11">
											<col width="80">
											<col width="200">
											<col width="11">
											<col width="80">
											<col width="200">

											<tr>
												<td/>
												<td class="stab">eventName</td>
												<td>
													<ezf:inputText name="xxScrItem20Txt" ezfName="xxScrItem20Txt" value="getmdse" otherAttr=" size=\"10\" maxlength=\"10\""/>
													<ezf:inputButton name="ChkNm" value="ChkNm" htmlClass="pBtn6" />
												</td>
												<td class="stab">
													<ezf:inputButton name="SearchMap" value="SearchMap" htmlClass="pBtn6" /></td>
												<td/>
												<td/>

												<td/>
												<td/>
												<td/>
											</tr>
											<tr>
												<td/>
												<td class="stab">APIName</td>
												<td><ezf:inputText name="xxScrItem20Txt_A" ezfName="xxScrItem20Txt_A" value="ZZZC015101" otherAttr=" size=\"20\" maxlength=\"20\""/></td>
												<td/>
												<td class="stab">MethodName</td>
												<td><ezf:inputText name="xxScrItem30Txt" ezfName="xxScrItem30Txt" value="execute" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
												<td/>
												<td class="stab">Argument</td>
												<td><ezf:inputText name="xxScrItem130Txt" ezfName="xxScrItem130Txt" value="ZZZC015101PMsg" otherAttr=" size=\"30\" maxlength=\"130\""/></td>
											</tr>
											<tr>
												<td/>
												<td class="stab">Description</td>
												<td colspan="6">
													<ezf:inputText name="xxScrItem130Txt_A" ezfName="xxScrItem130Txt_A" value="testtest" otherAttr=" size=\"81\" maxlength=\"130\""/>
												</td>
												<td><ezf:inputButton name="View" value="View" htmlClass="pBtn6" /></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- ********* 1 ********* -->
						</td>
					</tr>
				</table>
<!-- **************************************** HEADER **************************************** -->
				<hr>
<!-- **************************************** DETAIL **************************************** -->
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td valign="top">
						<!-- ********* PAGENATION ********* -->
							<table width="100%">
								<tr align="right">
									<td>
										<!--
										<table border="1" cellpadding="1" cellspacing="0">
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
												<td class="stab">Showing</td>
												<td class="pOut">1</td>
												<td class="stab">to</td>
												<td class="pOut">40</td>
												<td class="stab">of</td>
												<td class="pOut">200</td>
												<td>&nbsp;</td>
												<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" disabled></td>
												<td></td>
												<td><input type="button" class="pBtn3" value="Next" name="PageNext"></td>
											</tr>
										</table>
										-->
										<%--
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="A" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
										</jsp:include>
										--%>
									</td>
								</tr>
							</table>
						<!-- ********* PAGENATION ********* -->
						<!-- ********* TITLE ********* -->
							<div style="overflow-y:hidden; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="30"  align="center">
									<col width="250"  align="center">
									<col width="250"  align="center">
									<col width="82"   align="center">
									<col width="82"  align="center">
									<col width="82"  align="center">
									<col width="82"  align="center">

									<tr>
										<td class="pClothBs">No.</td>
										<td class="pClothBs">dispName</td>
										<td class="pClothBs">parentDispName</td>
										<td class="pClothBs">ignore</td>
										<td class="pClothBs">chTmZone</td>
										<td class="pClothBs">digit/size</td>
										<td class="pClothBs">type</td>
									</tr>
								</table>
							</div>
						<!-- ********* TITLE ********* -->
						<!-- ********* DATA ********* -->
						<!--
							<div style="overflow-y:scroll; height:354; overflow-x:hidden;width:1051;" onscroll="synchroRightScroll();">
							-->
							<div style="overflow-y:scroll; height:434; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0" id="A">
									<col width="30"  align="center">
									<col width="250">
									<col width="250">
									<col width="82">
									<col width="82">
									<col width="82">
									<col width="82">

									<tbody>
									
										<%@ page import="business.servlet.ZZKL0010.ZZKL0010BMsg" %>
										<% ZZKL0010BMsg bMsg = (ZZKL0010BMsg) databean.getEZDBMsg(); %>
										<% String msgName = ""; %>
										<% for (int i = 0; i < bMsg.A.getValidCount(); i++) { %>
											<% if (!msgName.equals(bMsg.A.no(i).xxScrItem30Txt_A1.getValue())) { %>
												<% msgName = bMsg.A.no(i).xxScrItem30Txt_A1.getValue(); %>
												<tr height="16">
													<td colspan="7" align="center">
														<b sytle="font-family: Impact">
															<ezf:label name="xxScrItem30Txt_A1" ezfName="xxScrItem30Txt_A1" ezfHyo="A" ezfArrayNo="<%= i %>" />
														</b>
													</td>
												</tr>
											<% } %>
											<tr height="27">
												<td>
													<%= i %>
												</td>
												<td>
													<ezf:label name="xxScrItem54Txt_A1" ezfName="xxScrItem54Txt_A1" ezfHyo="A" ezfArrayNo="<%= i %>" />
												</td>
												<td><ezf:label name="xxScrItem54Txt_A2" ezfName="xxScrItem54Txt_A2" ezfHyo="A" ezfArrayNo="<%= i %>" /></td>
												<td align="center"><ezf:inputCheckBox name="xxIntfcChkFlg_A1" ezfName="xxIntfcChkFlg_A1" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" /></input></td>
												<td align="center"><ezf:inputCheckBox name="xxIntfcChkFlg_A2" ezfName="xxIntfcChkFlg_A2" value="Y" ezfHyo="A" ezfArrayNo="<%= i %>" /></input></td>
												<td><ezf:label name="xxScrItem10Txt_A3" ezfName="xxScrItem10Txt_A3" ezfHyo="A" ezfArrayNo="<%= i %>" /></td>
												<td><ezf:label name="xxScrItem10Txt_A4" ezfName="xxScrItem10Txt_A4" ezfHyo="A" ezfArrayNo="<%= i %>" /></td>
											</tr>
										<% } %>
									</tbody>
								</table>
							</div>
						<!-- ********* DATA ********* -->
						</td>
					</tr>
				</table>
<!-- **************************************** DETAIL **************************************** -->
				</div>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
