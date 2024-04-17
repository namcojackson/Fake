<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20130320013617 --%>
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
			<input type="hidden" name="pageID" value="ZZIL0110Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Internal&nbsp;Interface&nbsp;Master&nbsp;Maintenance&nbsp;Screen">
			
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
			<!-- include S21BusinessProcessTAB -->
			<jsp:include page="../tab/S21BusinessProcessTAB.jsp"/>
			
			<div class="pTab_BODY">
<!-- **************************************** HEADER **************************************** -->
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<tr>
						<td>
							<!-- ********* 1 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="150">
								<col width="280">
								<col width="11">
								<col width="100">
								<col width="240">
								<col width="5">
								<tr>
									<td class="stab">Internal Interface ID(*)</td>
									<td><ezf:inputText name="itrlIntfcId" ezfName="itrlIntfcId" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td/>
									<td class="stab">Config Name</td>
									<td>
										<ezf:select name="itrlIntfcTrxConfigId_PS" ezfName="itrlIntfcTrxConfigId_PS" ezfCodeName="itrlIntfcTrxConfigId_PC" ezfDispName="itrlIntfcTrxConfigNm_PC" />
									</td>
									<td/>
								</tr>
							</table>
							<!-- ********* 1 ********* -->
							<!-- ********* 2 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="750">
								<col width="20">
								<col width="5">
								<tr>
									<td/>
									<td><ezf:inputButton name="Search" value="Search" htmlClass="pBtn6" /></td>
									<td/>
								</tr>
							</table>
							<!-- ********* 2 ********* -->
						</td>
					</tr>
				</table>
<!-- **************************************** HEADER **************************************** -->
				<hr />
<!-- **************************************** DETAIL **************************************** -->
				<table border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td valign="top">
							<!-- ********* PAGENATION ********* -->
							<table width="100%">
								<tr>
									<col width="70">
									<col width="750">
									<td><ezf:inputButton name="Select" value="Add I/F" htmlClass="pBtn6" /></td>
									<td align="right">
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
							<!-- ********* PAGENATION ********* -->
							<!-- ********* TITLE ********* -->
							<div style="overflow-y:hidden; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="76"  align="center">
									<col width="215"  align="center">
									<col width="215">
									<col width="170">
									<col width="170">
									<tr>
										<td class="pClothBs" rowspan="2"></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'itrlIntfcId_A' )">Internal Interface ID<img id="sortIMG.itrlIntfcId_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxIntfcInterCntNum_A' )">Number of Interface table<img id="sortIMG.xxIntfcInterCntNum_A" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_AC' )">Registered Time<img id="sortIMG.xxDtTm_AC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'A', 'xxDtTm_AU' )">Update Time<img id="sortIMG.xxDtTm_AU" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							<!-- ********* TITLE ********* -->
							<!-- ********* DATA ********* -->
							<div style="overflow-y:scroll; height:354; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0" id="A">
									<col width="76">
									<col width="215">
									<col width="215">
									<col width="170">
									<col width="170">
									<tbody>
										<ezf:row ezfHyo="A">
											<tr height="27">
												<td><ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn6" /></td>
												<td><ezf:label name="itrlIntfcId_A" ezfName="itrlIntfcId_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxIntfcInterCntNum_A" ezfName="xxIntfcInterCntNum_A" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_AC" ezfName="xxDtTm_AC" ezfHyo="A" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_AU" ezfName="xxDtTm_AU" ezfHyo="A" ezfArrayNo="0" /></td>
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
