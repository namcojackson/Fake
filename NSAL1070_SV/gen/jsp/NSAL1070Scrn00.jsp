<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20180531110900 --%>
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
			<input type="hidden" name="pageID" value="NSAL1070Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Overage Pricing Popup">

<%@ page import="business.servlet.NSAL1070.NSAL1070BMsg" %>
<% 
	NSAL1070BMsg bMsg = (NSAL1070BMsg)databean.getEZDBMsg(); 
%>

<center>
	<table border="0" cellspacing="0" cellpadding="0"  align="center">
		<tr>
		</tr>
<!-- <%-- ######################################## FROM (COMMON)DETAIL ######################################## --%> -->
		<tr>
			<td align="center">
				<% int idx = 0; %>
				<table>
					<tr>
						<td>
			                <table border="1" cellpadding="1" cellspacing="0" width="">
			                    <col width="310" align="center">   <!-- Billing Counter -->
			                    <col width="90" align="center">   <!-- Old Allowance -->
			                    <col width="80" align="center">   <!-- Old Price -->
			                    <col width="40" align="center">    <!-- % -->
			                    <col width="90" align="center">   <!-- Allowance -->
			                    <col width="80" align="center">   <!-- Price -->
			                    <tr>
			                    	<td class="pClothBs" colspan="3">Old Pricing</td>
			                    	<td class="pClothBs" rowspan="3">%</td>
			                    	<td class="pClothBs" colspan="2">New Pricing</td>
			                    </tr>
			                    <tr>
			                    	<td class="pClothBs">Billing Counter</td>
			                    	<td class="pClothBs" colspan="2">Pricing</td>
			                    	<td class="pClothBs" colspan="2">Pricing</td>
			                    </tr>
			                    <tr>
			                    	<td class="pClothBs">&nbsp;</td>
			                    	<td class="pClothBs">Allowance</td>
			                    	<td class="pClothBs">Price</td>
			                    	<td class="pClothBs">Allowance</td>
			                    	<td class="pClothBs">Price</td>
			                    </tr>
			              	</table>
			                <div style="overflow-y:scroll; height:480; overflow-x:none; width:735">
			                    <table id="A" border="1" cellpadding="1" cellspacing="0" width="">
				                    <col width="310" align="left">    <!-- Billing Counter -->
				                    <col width="90" align="right">   <!-- Old Allowance -->
				                    <col width="80" align="right">   <!-- Old Price -->
				                    <col width="40" align="right">   <!-- % -->
				                    <col width="90" align="right">   <!-- Allowance -->
				                    <col width="80" align="right">   <!-- Price -->
			                        <ezf:row ezfHyo="A">
			                        	<tr id="id_row${EZF_ROW_NUMBER}" height="28">
			                                <!-- Billing Counter -->
			                                <td>
												<% if (idx == 0 || !bMsg.A.no(idx).mtrLbDescTxt_A1.getValue().equals(bMsg.A.no(idx-1).mtrLbDescTxt_A1.getValue())) { %>
													<ezf:label name="mtrLbDescTxt_A1" ezfName="mtrLbDescTxt_A1" ezfHyo="A" ezfArrayNo="0" />
												<% }else { %> &nbsp; <% } %>
											</td>
			                                <!-- Old Allowance -->
											<td><ezf:label name="xsMtrCopyQty_A1" ezfName="xsMtrCopyQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
			                                <!-- Old Price -->
											<td><ezf:label name="xsMtrAmtRate_A1" ezfName="xsMtrAmtRate_A1" ezfHyo="A" ezfArrayNo="0" /></td>
			                                <!-- % -->
											<td><ezf:label name="mtrPrcUpRatio_A1" ezfName="mtrPrcUpRatio_A1" ezfHyo="A" ezfArrayNo="0" otherAttr=" size=\"5\""/></td>
			                                <!-- Allowance -->
											<td><ezf:label name="newXsCopyQty_A1" ezfName="newXsCopyQty_A1" ezfHyo="A" ezfArrayNo="0" /></td>
			                                <!-- Price -->
											<td><ezf:label name="newXsMtrAmtRate_A1" ezfName="newXsMtrAmtRate_A1" ezfHyo="A" ezfArrayNo="0" /></td>
											<% idx++; %>
			                        	</tr>
			                        </ezf:row>
			                   	</table>
			                </div>
			              	
						</td>
					</tr>
				</table>
			</td>
		</tr>
<!-- <%-- ######################################## TO (COMMON)DETAIL ########################################## --%> -->
	</table>
</center>


			

<%-- **** Task specific area ends here **** --%>
