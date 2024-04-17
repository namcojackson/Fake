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
			<input type="hidden" name="pageID" value="ZZKL0010Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Search Map">
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
				<table border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td>
							<!-- ********* 1 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="1" cellspacing="0">
											<col width="50">
											<col width="80">
											<col width="110">
											<col width="120">
											
											<tr>
												<td/>
												<td class="stab">eventName</td>
												<td><ezf:inputText name="xxScrItem10Txt_B1" ezfName="xxScrItem10Txt_B1" value="ZZKC000010" otherAttr=" size=\"10\" maxlength=\"10\""/></td>
												<td class="stab"><ezf:inputButton name="ReSearch" value="ReSearch" htmlClass="pBtn6" /></td>
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
							<%--
							<table width="100%">
								<tr align="right">
									<td>
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="B" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum" />
										</jsp:include>
									</td>
								</tr>
							</table>
							--%>
							<!-- ********* PAGENATION ********* -->
							<!-- ********* TITLE ********* -->
							<div style="overflow-y:hidden; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="76">
									<col width="150">
									<col width="150">
									<col width="210">
									<col width="400">

									<tbody>
										<tr>
										<td class="pClothBs"></td>
										<td class="pClothBs">EventName</td>
										<td class="pClothBs">APIName</td>
										<td class="pClothBs">methodName</td>
										<td class="pClothBs">Arguments</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- ********* TITLE ********* -->
							<!-- ********* DATA ********* -->
							<div style="overflow-y:scroll; height:434; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0" id="B">
									<col width="76">
									<col width="150">
									<col width="150">
									<col width="210">
									<col width="400">
									<tbody>
										<ezf:row ezfHyo="B">
										<tr height="27">
											<td><ezf:inputButton name="Select" value="Select" ezfHyo="B" ezfArrayNo="0" htmlClass="pBtn6" /></td>
											<td><ezf:label name="xxScrItem30Txt_B1" ezfName="xxScrItem30Txt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxScrItem20Txt_B1" ezfName="xxScrItem20Txt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxScrItem30Txt_B2" ezfName="xxScrItem30Txt_B2" ezfHyo="B" ezfArrayNo="0" /></td>
											<td><ezf:label name="xxScrItem130Txt_B1" ezfName="xxScrItem130Txt_B1" ezfHyo="B" ezfArrayNo="0" /></td>
										</tr>
										</ezf:row>
										<ezf:skip>
										</ezf:skip>
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
