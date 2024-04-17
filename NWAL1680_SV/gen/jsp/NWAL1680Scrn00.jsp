<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160219092355 --%>
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
			<input type="hidden" name="pageID" value="NWAL1680Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Order Summary Popup">
			
<center>
	<table border="0" cellpadding="1" cellspacing="0" width="100%">
		<tr>
			<td>
				<div class="pTab_BODY">
					<table border="0" cellpadding="1" cellspacing="0" height = "30" align="center">
						<col width="80" align="left">
						<col width="180" align="left">
						<col width="120" align="left">
						<col width="180" align="left">
						<tr>
							<td class="stab">Order Number</td>
							<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" otherAttr=" size=\"20\""/></td>
							<td class="stab">Ship To Customer Name</td>
							<td><ezf:inputText name="shipToCustAcctNm" ezfName="shipToCustAcctNm" otherAttr=" size=\"20\""/></td>
						</tr>
					</table>
					<br>
					<table border="1" rules ="none" cellpadding="1" cellspacing="0" height = "10" align="center">
						<col width="332" align="left">
						<tr>
							<td class="pClothBs">Summary Counts By Order</td>
						</tr>
					</table>
					<table border="1" rules ="none" cellpadding="1" cellspacing="0" height = "120" align="center">
						<col width="180" align="left">
						<col width="150" align="left">
						<tr>
							<td class="stab">Total Configurations</td>
							<td><ezf:inputText name="xxDtlCnt_LC" ezfName="xxDtlCnt_LC" otherAttr=" size=\"20\""/></td>
						</tr>
						<tr>
							<td class="stab">Total Machines</td>
							<td><ezf:inputText name="xxDtlCnt_LM" ezfName="xxDtlCnt_LM" otherAttr=" size=\"20\""/></td>
						</tr>
						<tr>
							<td class="stab">Total RMA Configurations</td>
							<td><ezf:inputText name="xxDtlCnt_RC" ezfName="xxDtlCnt_RC" otherAttr=" size=\"20\""/></td>
						</tr>
							<td class="stab">Total RMA Machines</td>
							<td><ezf:inputText name="xxDtlCnt_RM" ezfName="xxDtlCnt_RM" otherAttr=" size=\"20\""/></td>
						</tr>
							<td class="stab">Total Shells</td>
							<td><ezf:inputText name="xxDtlCnt_SC" ezfName="xxDtlCnt_SC" otherAttr=" size=\"20\""/></td>
						</tr>
						<tr>
							<td class="stab">Total Machines Covered by Shell</td>
							<td><ezf:inputText name="xxDtlCnt_SM" ezfName="xxDtlCnt_SM" otherAttr=" size=\"20\""/></td>
						</tr>
					</table>
					<br>
					<table border="1" rules ="none" cellpadding="1" cellspacing="0" height = "10" align="center">
						<col width="611" align="left">
						<tr>
							<td class="pClothBs">Summary Counts By Model</td>
						</tr>
					</table>
					<div id="topRightTBL">
						<table border="1" cellpadding="1" cellspacing="0" height = "10" align="center">
							<col width="150" align="center">
							<col width="150" align="center">
							<col width="150" align="center">
							<col width="150" align="center">
							<tr>
								<td class="pClothBs">Model</td>
								<td class="pClothBs">Total Configurations</td>
								<td class="pClothBs">Total RMAs</td>
								<td class="pClothBs">Total Shells</td>
						</table>
					</div>
					<div id="RightTBL" style="margin-right:71px; overflow-y:scroll; height:252;">
						<table border="1" cellpadding="1" cellspacing="0" align="right" id="A">
							<col width="150" align="left">
							<col width="150" align="left">
							<col width="150" align="left">
							<col width="150" align="left">
							<ezf:row ezfHyo="A">
								<tr height="28">
									<td><ezf:inputText name="mdlDescTxt_A" ezfName="mdlDescTxt_A" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
									<td><ezf:inputText name="xxDtlCnt_AL" ezfName="xxDtlCnt_AL" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
									<td><ezf:inputText name="xxDtlCnt_AR" ezfName="xxDtlCnt_AR" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
									<td><ezf:inputText name="xxDtlCnt_AS" ezfName="xxDtlCnt_AS" value="WWWWWWWWW1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"20\""/></td>
								</tr>
							</ezf:row>
							
							<ezf:skip>
								<tr height="28">
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW2</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW3</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW4</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW5</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW6</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW7</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW8</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW9</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW0</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW2</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW3</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW4</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
								<tr height="28">
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
									<td><label>WWWWWWWWW1</label></td>
								</tr>
							</ezf:skip>
						</table>
					</div>
					<table border="1" cellpadding="1" cellspacing="0" align="center">
						<col width="150" align="center">
						<col width="150" align="left">
						<col width="150" align="left">
						<col width="150" align="left">
						<tr height="28">
							<td><label>Total</label></td>
							<td><ezf:inputText name="xxDtlCnt_TL" ezfName="xxDtlCnt_TL" value="1234567890" otherAttr=" size=\"20\""/></td>
							<td><ezf:inputText name="xxDtlCnt_TR" ezfName="xxDtlCnt_TR" value="1234567890" otherAttr=" size=\"20\""/></td>
							<td><ezf:inputText name="xxDtlCnt_TS" ezfName="xxDtlCnt_TS" value="1234567890" otherAttr=" size=\"20\""/></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</center>
			


<%-- **** Task specific area ends here **** --%>
