<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20171218113758 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0050Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="API Inbound Target I/F Look Up">
<center>
	<table width="100%">
		<tr>
			<td>
				<table align="center">
					<tr>
						<td>
							<table>
								<col align="right">
								<col>
								<col width="5">
								<col>
								<tr>
									<td class="stub">
										<label>Interface ID(*)</label>
									</td>
									<td>
										<ezf:inputText name="intfcId" ezfName="intfcId" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/>
									</td>
									<td></td>
									<td>
										<ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td><hr></td></tr>
					<tr>
						<td>
							<table align="center">
								<tr>
									<td>
										<table width="100%">
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
								<tr>
									<td>
										<table border="1" cellpadding="1" cellspacing="0" width="730">
											<col width="72" align="center"> 
											<col width="240" align="center">
											<col width="400" align="center">
											<tr> 
												<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs">Interface ID</td>
												<td class="pClothBs">Description</td>
											</tr> 
										</table>
										<div style="overflow:auto; height:410; width:747;">
											<table border="1" cellpadding="1" cellspacing="0" id="A" width="730"> 
												<col width="72" align="center"> 
												<col width="240" align="left"> 
												<col width="400" align="left"> 
												<tbody>
												<ezf:row ezfHyo="A">
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
													<td><ezf:label name="intfcId_A" ezfName="intfcId_A" ezfHyo="A" ezfArrayNo="0" /></td>
													<td><ezf:label name="intfcIdDescTxt_A" ezfName="intfcIdDescTxt_A" ezfHyo="A" ezfArrayNo="0" /></td>
												</tr>
												</ezf:row>
												<ezf:skip>
												<tr id="id_row{EZF_ROW_NUMBER}">
													<td><input type="button" class="pBtn6" name="Select" value="Select"></td>
													<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCC</label></td>
													<td><label ezfout>AAAAAAAAAABBBBBBBBBBCCCCCCCCCDDDDDDDDDDEEEEEEEEEE</label></td>
												</tr>
												</ezf:skip>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
