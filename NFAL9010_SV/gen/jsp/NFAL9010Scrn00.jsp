<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160406005510 --%>
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

<table width="95%" align="center" border="0">
	<tr>
		<td align="center">
			<table width="700px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80"></td>
					<td width="140"></td>
					<td width="100"></td>
					<td width=""></td>
				</tr>
				<tr height="20px">
					<td class="stab" align="left">Account Code</td>
					<td>
						<ezf:inputText name="coaAcctCd" ezfName="coaAcctCd" value="7250" otherAttr=" size=\"19\" maxlength=\"8\" ezftoupper=\"\""/>
					</td>
					<td class="stab" align="right">Description&nbsp;&nbsp;&nbsp;</td>
					<td>
						<ezf:inputText name="coaAcctNm" ezfName="coaAcctNm" value="Cost" otherAttr=" size=\"35\" maxlength=\"30\" ezftoupper=\"\""/>
					</td>
				</tr>
				<tr height="20px">
					<td class="stab" align="left">Trial Balance</td>
					<td align="left">
						<ezf:select name="trialBalTpCd_3" ezfName="trialBalTpCd_3" ezfBlank="1" ezfCodeName="trialBalTpCd_1" ezfDispName="trialBalTpNm_2" otherAttr=" style=\"width:140px;\""/>
					</td>
					<td colspan="2" align="right">
						<ezf:inputButton name="Search" value="Search" htmlClass="pBtn5" otherAttr=" style=\"width:100px;height:20px;font-size:9pt;\""/>
					</td>
				</tr>
			</table>

			<hr size="1" noshade>
			<%-- Pagenation --%>
			<table width="733px">
				<tr align="right">
					<td>
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
							<col width="1">
							<col>
							<tr>
								<td class="stab">Showing</td>
								<td class="pOut">1</td>
								<td class="stab">to</td>
								<td class="pOut">20</td>
								<td class="stab">of</td>
								<td class="pOut">200</td>
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

					</td>
				</tr>
			</table>
			<table width="733px" cellpadding="1" cellspacing="0" border="1" align="center">
				<col width="65">
				<col width="80">
				<col width="480">
				<col width="">
				<tr align="center">
					<td class="pClothBs">&nbsp;</td>
					<td class="pClothBs">AcctCode</td>
					<td class="pClothBs">Description</td>
					<td class="pClothBs">Trial Balance</td>
				</tr>
			</table>

			<table width="733px" cellpadding="1" cellspacing="0" border="1" align="center" id="A">
				<col width="65">
				<col width="80">
				<col width="480">
				<col width="">
				<tbody>
					<ezf:row ezfHyo="A">
						<tr height="20px">
							<td align="center">
								<ezf:inputButton name="Select" value="Select" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn4" />
							</td>
							<td align="left" class="stab">
								<ezf:label name="coaAcctCd_A" ezfName="coaAcctCd_A" ezfHyo="A" ezfArrayNo="0" />
							</td>
							<td align="left" class="stab">
								<ezf:inputText name="coaAcctNm_A" ezfName="coaAcctNm_A" value="Cost Variance" ezfHyo="A" ezfArrayNo="0" otherAttr=" style=\"height:19px;width:440px;font-size:9pt;\""/>
							</td>
							<td align="left" class="stab">
								<ezf:label name="trialBalTpNm_A" ezfName="trialBalTpNm_A" ezfHyo="A" ezfArrayNo="0" />
							</td>
						</tr>
					</ezf:row>
					<ezf:skip>

					</ezf:skip>
				</tbody>
			</table>
			
		</td>
	</tr>
</table>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NFAL9010Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Account Code Search">


<%-- **** Task specific area ends here **** --%>
