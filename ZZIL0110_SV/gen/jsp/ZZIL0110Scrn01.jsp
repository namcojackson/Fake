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
			<input type="hidden" name="pageID" value="ZZIL0110Scrn01">
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
									<td class="stab">Internal Interface ID</td>
									<td><ezf:inputText name="itrlIntfcId_B1" ezfName="itrlIntfcId_B1" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\" ezftoupper=\"\""/></td>
									<td/>
									<td>
										<ezf:select name="itrlIntfcTrxConfigId_BS" ezfName="itrlIntfcTrxConfigId_BS" ezfBlank="1" ezfCodeName="itrlIntfcTrxConfigId_BC" ezfDispName="itrlIntfcTrxConfigNm_BC" />
									</td>
								</tr>
							</table>
							<!-- ********* 1 ********* -->
							<!-- ********* 2 ********* -->
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="150">
								<col width="280">
								<col width="11">
								<col width="100">
								<col width="240">
								<col width="5">
								<tr>
									<td class="stab">Internal Interface Table</td>
									<td><ezf:inputText name="itrlIntfcTrxTblId_B1" ezfName="itrlIntfcTrxTblId_B1" value="123456789012345678901234567890" otherAttr=" size=\"30\" maxlength=\"30\""/></td>
									<td/>
									<td/>
									<td><ezf:inputButton name="Add" value="Add" htmlClass="pBtn6" /></td>
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
									<td align="right">
										<jsp:include page="../tablePagenation/S21TablePagenation.jsp">
											<jsp:param name="beanId"      value='<%= request.getParameter( "beanId" ) %>' />
											<jsp:param name="TableNm"     value="B" />
											<jsp:param name="ShowingFrom" value="xxPageShowFromNum_B" />
											<jsp:param name="ShowingTo"   value="xxPageShowToNum_B" />
											<jsp:param name="ShowingOf"   value="xxPageShowOfNum_B" />
										</jsp:include>
									</td>
								</tr>
							</table>
							<!-- ********* PAGENATION ********* -->
							<!-- ********* TITLE ********* -->
							<div style="overflow-y:hidden; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0">
									<col width="22"  align="center">
									<col width="215"  align="center">
									<col width="194">
									<col width="194">
									<tr>
										<td class="pClothBs" rowspan="2"></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'itrlIntfcTblId_B' )">Internal Interface Table<img id="sortIMG.itrlIntfcTblId_B" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxDtTm_BC' )">Registered Time<img id="sortIMG.xxDtTm_BC" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
										<td class="pClothBs" rowspan="2"><a href="#" class="pSortCol" onclick="columnSort( 'B', 'xxDtTm_BU' )">Update Time<img id="sortIMG.xxDtTm_BU" border="0" src="./img/tableSort/asc.gif" style="{visibility:hidden}"></a></td>
									</tr>
								</table>
							</div>
							<!-- ********* TITLE ********* -->
							<!-- ********* DATA ********* -->
							<div style="overflow-y:scroll; height:354; overflow-x:hidden;">
								<table border="1" cellpadding="1" cellspacing="0" id="B">
									<col width="22">
									<col width="215">
									<col width="194">
									<col width="194">
									<tbody>
										<ezf:row ezfHyo="B">
											<tr height="27">
												<td><ezf:inputCheckBox name="xxChkBox_B" ezfName="xxChkBox_B" value="Y" ezfHyo="B" ezfArrayNo="0" /></td>
												<td><ezf:label name="itrlIntfcTblId_B" ezfName="itrlIntfcTblId_B" ezfHyo="B" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_BC" ezfName="xxDtTm_BC" ezfHyo="B" ezfArrayNo="0" />&nbsp;<ezf:label name="xxIntfcUpdTz_BC" ezfName="xxIntfcUpdTz_BC" ezfHyo="B" ezfArrayNo="0" /></td>
												<td><ezf:label name="xxDtTm_BU" ezfName="xxDtTm_BU" ezfHyo="B" ezfArrayNo="0" />&nbsp;<ezf:label name="xxIntfcUpdTz_BU" ezfName="xxIntfcUpdTz_BU" ezfHyo="B" ezfArrayNo="0" /></td>
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
