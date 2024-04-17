<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20161222133512 --%>
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
			<input type="hidden" name="pageID" value="ZZVL0020Scrn01">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Shared My Process Maintenance">

<center>
	<table width="100%">
		<tr><td>
			<table witdh="90%" align="center">
			<tr>
				<td>
					<table border="0">
						<col width="36px">
						<col width="192px">
						<col width="791px">
						<tr>
							<td class="stab">Role ID:</td>
							<td class="pOut"><ezf:label name="roleNm_2" ezfName="roleNm_2" /></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					
				<td>
			</tr>
			<tr>
				<td height="20">&nbsp;</td>
			</tr>
			<tr><td><hr></td></tr>
			<tr>
				<td height="5">&nbsp;</td>
			</tr>
			</table>
		</td></tr>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<table width="95%" height="2" border="0">
					<td width="5%" align="center" valign="center">
						<table border="0" >
							<tr>
								<td>&nbsp;</td>
							<tr>
						</table>
					</td>
					<!-- Active Process -->
					<td width="40%" align="center" valign="top">
					<fieldset>
						<legend>&nbsp;<font size="2" color="black">Active Process</font>&nbsp;</legend>
						<table border="0" >
							<tr>
								<td>
									<%-- Pagenation --%>
									<table width="100%">
										<tr align="right">
											<td>
												<!--
												<table border="0" cellpadding="1" cellspacing="0">
													<col width="54"  align="center">
													<col width="30"  align="right">
													<col width="16"  align="center">
													<col width="30"  align="right">
													<col width="16"  align="center">
													<col width="30"  align="right">
													<col width="10">
													<col>
													<col width="1">
													<col>
													<tr>
														<td class="stab">Showing</td>
														<td class="pOut">1</td>
														<td class="stab">to</td>
														<td class="pOut">10</td>
														<td class="stab">of</td>
														<td class="pOut">200</td>
														<td>&nbsp;</td>
														<td><ezf:inputButton name="PagePrev" value="Prev" htmlClass="pBtn2" /></td>
														<td></td>
														<td><ezf:inputButton name="PageNext" value="Next" htmlClass="pBtn2" /></td>
													</tr>
												</table>
												-->
												<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
													<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
													<jsp:param name="TableNm"     value="A" />
													<jsp:param name="ShowingFrom" value="xxPageShowFromNum_A1" />
													<jsp:param name="ShowingTo"   value="xxPageShowToNum_A1" />
													<jsp:param name="ShowingOf"   value="xxPageShowOfNum_A1" />
												</jsp:include>
											</td>
										</tr>
									</table>
									<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
										<col width="45" align="center">
										<col width="200" align="center">
										<col width="150" align="center">
										<tr height="24">
											<td class="pClothBs">&nbsp;</td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'menuProcNm_A1' )">Process<img id="sortIMG.menuProcNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
											<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'upTabNm_A1' )">TAB Description<img id="sortIMG.upTabNm_A1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										</tr>
									</table>
									<div id="leftTBL" style="overflow-y:scroll; height:387; width:410;">
									
										<table id="A" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
											<col width="45" align="center">
											<col width="200" align="left">
											<col width="150" align="left">
											<ezf:row ezfHyo="A">
											<tr height="24">
												<td ><ezf:inputCheckBox name="xxChkBox_A1" ezfName="xxChkBox_A1" value="Y" ezfHyo="A" ezfArrayNo="0" /></td>
												<td ><ezf:label name="menuProcNm_A1" ezfName="menuProcNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
												<td title="<ezf:label name="bizAppNm_A1" ezfName="bizAppNm_A1" ezfHyo="A" ezfArrayNo="0" />"><ezf:label name="upTabNm_A1" ezfName="upTabNm_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											</tr>
											</ezf:row>
											<ezf:skip>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Entry</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Upload</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Search</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Hold Rlse</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Exprt Lic</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>CR Hld Rlse</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>TopStop Rlse</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>SalesSum</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Stat</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>SalesSum</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Loan/Trial</label></td>
												<td ><label ezfout>Req Entry</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Loan/Trial</label></td>
												<td ><label ezfout>Asset Mgt</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Loan/Trial</label></td>
												<td ><label ezfout>Dispn Ord</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Loan/Trial</label></td>
												<td ><label ezfout>Loan to Dspl</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>Struct Setup</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>Scty Setup</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>Struct Setup</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>Seg Ntre Set</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>Thrshld Set</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>TP STP Maint</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>In-bound</label></td>
												<td ><label ezfout>Invoice</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>In-bound</label></td>
												<td ><label ezfout>WH Chng</label></td>
											</tr>
											</ezf:skip>
										</table>
									</div>
								<td>
							</tr>
						</table>
						<br>
					</fieldset>
					</td>
					<td width="10%" align="center" valign="center">
						<table border="0" >
							<tr>
								<td><ezf:inputButton name="Right" value="&gt;" htmlClass="pBtn3" /></td>
							<tr>
							<tr>
								<td>&nbsp;</td>
							<tr>
							<tr>
								<td><ezf:inputButton name="Left" value="&lt;" htmlClass="pBtn3" /></td>
							<tr>
						</table>
					</td>
					<!-- My Process -->
					<td width="40%" align="center" valign="top">
					<fieldset>
						<legend>&nbsp;<font size="2" color="black">My Process</font>&nbsp;</legend>
						<table border="0" >
							<tr>
								<td>
									<table width="100%">
										<tr align="right" height="24">
											<td>
												<table border="0" cellpadding="1" cellspacing="0">
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<table style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0" >
										<col width="45" align="center">
										<col width="200" align="center">
										<col width="150" align="center">
										<tr height="24">
											<td class="pClothBs">&nbsp;</td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'menuProcNm_B1' )">Process<img id="sortIMG.menuProcNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
												<td class="pClothBs"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'upTabNm_B1' )">TAB Description<img id="sortIMG.upTabNm_B1" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>

										</tr>
									</table>
									<div id="rightTBL" style="overflow-y:scroll; height:387; width:410;">
										<table id="B" style="table-layout:fixed" border="1" cellpadding="1" cellspacing="0">
											<col width="45" align="center">
											<col width="200" align="left">
											<col width="150" align="left">
											<ezf:row ezfHyo="B">
											<tr height="24">
												<td ><ezf:inputCheckBox name="xxChkBox_B1" ezfName="xxChkBox_B1" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
												<td ><ezf:label name="menuProcNm_B1" ezfName="menuProcNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
												<td title="<ezf:label name="bizAppNm_B1" ezfName="bizAppNm_B1" ezfHyo="B" ezfArrayNo="0" />"><ezf:label name="upTabNm_B1" ezfName="upTabNm_B1" ezfHyo="B" ezfArrayNo="0" /></td>
											</tr>
											</ezf:row>
											<ezf:skip>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Entry</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Upload</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Ord Search</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Hold Rlse</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>Exprt Lic</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Order Management</label></td>
												<td ><label ezfout>CR Hld Rlse</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Loan/Trial</label></td>
												<td ><label ezfout>Loan to Dspl</label></td>
											</tr>
											<tr height="24">
												<td ><input type="checkbox"></td>
												<td ><label ezfout>Set Up Distribution</label></td>
												<td ><label ezfout>Struct Setup</label></td>
											</tr>
											</ezf:skip>
										</table>
									</div>
								<td>
							</tr>
						</table>
						<br>
					</fieldset>
					</td>
					<td width="5%" align="center" valign="center">
						<table border="0" >
							<tr>
								<td>&nbsp;</td>
							<tr>
						</table>
					</td>
				</table>
			</tr>
		</table>
<!--
	</div>
-->
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
