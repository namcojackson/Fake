<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160525000754 --%>
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
			<input type="hidden" name="pageID" value="NWAL1640Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Buyout Details">
			
<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table border="0" cellpadding="1" cellspacing="0" align="center">
					<tr height="30">
						<td style="font-size:12px;"><u>LEASE BUYOUT</u></td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="50">
								<col width="100">
								<col width="10">
								<col width="50">
								<col width="100">
								<tr>
									<td>Order#</td>
									<td class="pOut"><ezf:label name="cpoOrdNum" ezfName="cpoOrdNum" /></td>
									<td>&nbsp;</td>
									<td>Line#</td>
									<td class="pOut"><ezf:label name="xxDtlLineNum" ezfName="xxDtlLineNum" /></td>
								</tr>
							</table>
							<hr>
							<table border="0" cellpadding="1" cellspacing="0">
								<col width="100">
								<col width="300">
								<tr>
									<td class="stab">Supplier Type</td>
									<td>
										<ezf:select name="splyTpCd" ezfName="splyTpCd" ezfBlank="1" ezfCodeName="splyTpCd_CD" ezfDispName="splyTpNm_DP" otherEvent1=" onchange=\"sendServer('Change_SplyTpCd')\"" otherAttr=" style=\"width: 215px\""/>
									</td>
								</tr>
								<tr>
									<td class="stab"><ezf:anchor name="prntVndNm_LK" ezfName="prntVndNm_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_AcctCust" otherAttr=" ezfanchortext=\"\"">Name</ezf:anchor></td>
									<td><ezf:inputText name="prntVndNm" ezfName="prntVndNm" value="CHICAGO BULLS" otherAttr=" size=\"30\" maxlength=\"50\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab">Address 1</td>
									<td><ezf:inputText name="splyFirstAddr" ezfName="splyFirstAddr" value="55 MADISON AVE. SUITE 100" otherAttr=" size=\"30\" maxlength=\"60\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab"><ezf:anchor name="splyCtyAddr" ezfName="splyCtyAddr" ezfEmulateBtn="1" ezfGuard="OpenWin_City" >City</ezf:anchor></td>
									<td><ezf:inputText name="splyCtyAddr" ezfName="splyCtyAddr" value="CHICAGO" otherAttr=" size=\"30\" maxlength=\"25\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab"><ezf:anchor name="splyStCd_LK" ezfName="splyStCd_LK" ezfEmulateBtn="1" ezfGuard="OpenWin_State" otherAttr=" ezfanchortext=\"\"">State</ezf:anchor>
									<td>
										<ezf:inputText name="splyStCd" ezfName="splyStCd" value="IL" otherAttr=" size=\"2\" maxlength=\"2\" ezftoupper=\"\""/>
										<ezf:inputText name="stNm" ezfName="stNm" value="Illinois" otherAttr=" size=\"26\" maxlength=\"25\" ezftoupper=\"\""/>
									</td>
								</tr>
								<tr>
									<td class="stab"><ezf:anchor name="splyPostCd" ezfName="splyPostCd" ezfEmulateBtn="1" ezfGuard="OpenWin_Post" >Postal</ezf:anchor></td>
									<td><ezf:inputText name="splyPostCd" ezfName="splyPostCd" value="08831" otherAttr=" size=\"30\" maxlength=\"15\" ezftoupper=\"\""/><ezf:inputButton name="GetAddress" value="Get" htmlClass="pBtn4" otherAttr=" style=\"margin-left:5px;\""/></td>
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
