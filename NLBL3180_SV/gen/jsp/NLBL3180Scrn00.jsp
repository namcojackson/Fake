<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20211011091909 --%>
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
			<input type="hidden" name="pageID" value="NLBL3180Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Ship Detail Popup">

<center>
	<table>
		<tr>
			<td>
<%-- ######################################## HEADER ######################################## --%>
				<table>
					<col width="30">  <!-- Label -->
					<col width="200"> <!-- Textbox -->
					<col width="20">  <!-- Spacing -->
					<col width="30">  <!-- Label -->
					<col width="200"> <!-- Textbox -->

					<tr>
						<td class="stab">SO#</td>
						<td>
							<ezf:inputText name="soNum_H1" ezfName="soNum_H1" value="SHN00000" otherAttr=" maxlength=\"8\" ezftoupper=\"\" style=\"width:100%\""/>
						</td>
						<td></td>
						<td class="stab">MNX#</td>
						<td>
							<ezf:inputText name="bolVchNum_H1" ezfName="bolVchNum_H1" value="1234567890" otherAttr=" maxlength=\"50\" ezftoupper=\"\" style=\"width:100%\""/>
						</td>
				</table>
				<hr>
<%-- ######################################## DETAIL ######################################## --%>
				<table border="1" cellpadding="1" cellspacing="0" id="A">
					<col width="30"  align="center">
					<col width="220" align="center">
					<col width="220" align="center">

					<tr>
						<td class="pClothBs">#</td>
						<td class="pClothBs">Milestone</td>
						<td class="pClothBs">Date/Time</td>
					</tr>
				</table>
				<div style="overflow-x:none; overflow-y:scroll; height:350;">
					<table border="1" cellpadding="1" cellspacing="0">
						<col width="30"  align="center">
						<col width="220" align="center">
						<col width="220" align="center">

						<tbody>
						<tr>
							<td>
								<ezf:inputText name="xxScrItem30Txt_DA" ezfName="xxScrItem30Txt_DA" value="A" otherAttr=" maxlength=\"2\" style=\"width:100%\""/>
							</td>
							<td>
								<ezf:inputText name="xxScrItem50Txt_DA" ezfName="xxScrItem50Txt_DA" value="Original ETD" otherAttr=" maxlength=\"80\" style=\"width:100%\""/>
							</td>
							<td>
								<ezf:inputText name="rqstQuoteDelyTxt_DA" ezfName="rqstQuoteDelyTxt_DA" otherAttr=" maxlength=\"16\" style=\"width:100%\""/>
							</td>
						</tr>
						<tr>
							<td>
								<ezf:inputText name="xxScrItem30Txt_DB" ezfName="xxScrItem30Txt_DB" value="B" otherAttr=" maxlength=\"2\" style=\"width:100%\""/>
							</td>
							<td>
								<ezf:inputText name="xxScrItem50Txt_DB" ezfName="xxScrItem50Txt_DB" value="QTD Change" otherAttr=" maxlength=\"80\" style=\"width:100%\""/>
							</td>
							<td>
								<ezf:inputText name="quoteDelyTxt_DB" ezfName="quoteDelyTxt_DB" otherAttr=" maxlength=\"16\" style=\"width:100%\""/>
							</td>
						</tr>
						<%@ page import="business.servlet.NLBL3180.NLBL3180BMsg" %>
						<% NLBL3180BMsg scrnMsg = (NLBL3180BMsg) databean.getEZDBMsg(); %>
						<% if (scrnMsg.A.getValidCount() > 0) { %>
						<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr> <!-- Spacing -->
						<% } %>
						<ezf:row ezfHyo="A">
							<tr>
								<td>
									<ezf:inputText name="unitId_A1" ezfName="unitId_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"2\" style=\"width:100%; text-align:left\""/>
								</td>
								<td>
									<ezf:inputText name="descHistTxt_A1" ezfName="descHistTxt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"80\" style=\"width:100%\""/>
								</td>
								<td>
									<ezf:inputText name="xxScrItem30Txt_A1" ezfName="xxScrItem30Txt_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" maxlength=\"16\" style=\"width:100%\""/>
								</td>
							</tr>
						</ezf:row>
						<ezf:skip></ezf:skip>
						</tbody>
					</table>
<%-- ######################################## Footer ######################################## --%>
				</div>
			</td>
		</tr>
	</table>
</center>


<%-- **** Task specific area ends here **** --%>
