<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160219073029 --%>
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
			<input type="hidden" name="pageID" value="NSAL0770Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Change Status Audit Trail"> 
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
<%-- ######################################## Header ######################################## --%>
				<table border="0" width="99%" align="center">
					<col width="897">
					<tr>
						<td  align="right">
							<ezf:skip>
									<table border="0" cellpadding="1" cellspacing="0">
										<col >
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="16"  align="center">
										<col width="40"  align="right">
										<col width="10">
										<col width="0">
										<col width="1">
										<col width="0">
										<tr>
											<td class="stab">Showing</td>
											<td class="pOut">1</td>
											<td class="stab">to</td>
											<td class="pOut">3</td>
											<td class="stab">of</td>
											<td class="pOut">1000</td>
											<td>&nbsp;</td>
											<td><input type="button" class="pBtn3" value="Prev" name="PagePrev" onclick="sendServer(this)" disabled></td>
											<td></td>
											<td><input type="button" class="pBtn3" value="Next" name="PageNext" onclick="sendServer(this)"></td>
										</tr>
									</table>
							</ezf:skip>

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
<%-- ######################################## DETAIL #################################### --%>
				<table border="0" cellpadding="0" cellspacing="0" width="99%">
					<col align="left" valign="top">
					<tr>
						<td>
							<div id="Top" style="width:990; text-align:center; overflow-x:hidden; overflow-y:hidden;">
								<table id="Top" border="1" cellpadding="1" cellspacing="0" height="23" >
									<col width="104"  align="center">          <!-- From Status           -->
									<col width="104"  align="center">          <!-- To Status             -->
									<col width="128"  align="center">          <!-- Reason                -->
									<col width="281"  align="center">          <!-- Notes                 -->
									<col width="168"  align="center">          <!-- Update By             -->
									<col width="179"   align="center">          <!-- Last Update Date      -->
									<tr>
										<td class="pClothBs">From Status</td>
										<td class="pClothBs">To Status</td>
										<td class="pClothBs">Reason</td>
										<td class="pClothBs">Notes</td>
										<td class="pClothBs">Update By</td>
										<td class="pClothBs">Last Update</td>
									</tr>
								</table>
							</div>
							<div id="Detail" style="width:1007; overflow-x:hidden; overflow-y:scroll; height:446;">
								<table id="A" border="1" cellpadding="1" cellspacing="0" height="23">
									<col width="104"  align="center">          <!-- From Status           -->
									<col width="104"  align="center">          <!-- To Status             -->
									<col width="128"  align="center">          <!-- Reason                -->
									<col width="281"  align="center">          <!-- Notes                 -->
									<col width="168"  align="center">          <!-- Update By             -->
									<col width="179"   align="center">         <!-- Last Update Date      -->
									<ezf:row ezfHyo="A">
										<tr height="23">
												<td><ezf:inputText name="dsContrStsNm_A1" ezfName="dsContrStsNm_A1" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
												<td><ezf:inputText name="dsContrStsNm_A2" ezfName="dsContrStsNm_A2" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"12\" maxlength=\"30\""/></td>
												<td><ezf:inputText name="dsContrTrkRsnDescTxt" ezfName="dsContrTrkRsnDescTxt" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"15\" maxlength=\"50\""/></td>
												<td><ezf:inputText name="stsMemoTxt" ezfName="stsMemoTxt" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"38\" maxlength=\"1000\""/></td>
												<td><ezf:inputText name="stsMemoUpdFullPsnNm" ezfName="stsMemoUpdFullPsnNm" value="1---------2---------3---------" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
												<td><ezf:label name="xxDtTm" ezfName="xxDtTm" ezfHyo="A" ezfArrayNo="0" /></td>
										</tr>
									<ezf:skip>
									</ezf:skip>
									</ezf:row>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
