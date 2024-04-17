<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160226115422 --%>
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
			<input type="hidden" name="pageID" value="NSAL1060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Meter Reading Popup">

<%-- +++++ [START] Programming JSP for Dynamic Columns Table +++++ --%>
<%@ page import="business.servlet.NSAL1060.NSAL1060BMsg" %>
<%@ page import="com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc" %>
<% 
	NSAL1060BMsg bMsg = (NSAL1060BMsg)databean.getEZDBMsg(); 
%>

<center>
	<table  border="0">
		<tr>
			<td>
				<table style="margin-top: 5px;" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td>
<!-- <%-- ######################################## FROM (COMMON)HEADER ######################################## --%> -->
							<table>
								<col align="center" width="5%">
								<col align="left">

							   <tr>
							   		<td>Serial#</td>
									<td><ezf:inputText name="serNum" ezfName="serNum" /></td>
							   </tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
<!-- <%-- ######################################## TO (COMMON)HEADER ########################################## --%> -->
<!-- <%-- ######################################## FROM (COMMON)DETAIL ######################################## --%> -->
		<tr>
			<td align="center">
				<% int idx = 0; %>
				<table>
					<tr>
						<td>
			                <table border="1" cellpadding="1" cellspacing="0" width="">
			                    <col width="50" align="center">  <!-- button -->
			                    <col width="320" align="center">  <!-- Counter -->
			                    <col width="150" align="center">  <!-- Read Date -->
			                    <col width="100" align="center">  <!-- Read -->
			                    <tr>
			                    	<td class="pClothBs">&nbsp;</td>
			                    	<td class="pClothBs">Counter</td>
			                    	<td class="pClothBs">Read Date</td>
			                    	<td class="pClothBs">Read</td>
			                    </tr>
			              	</table>
			                <div style="overflow-y:scroll; height:480; overflow-x:none; width:655">
			                    <table id="A" border="1" cellpadding="1" cellspacing="0" width="">
			                        <col width="50"  align="center">  <!-- button -->
			                        <col width="320"  align="left">  <!-- Counter -->
			                        <col width="150"  align="center">  <!-- Read Date -->
			                        <col width="100"  align="right">  <!-- Read -->
			                        <ezf:row ezfHyo="A">
			                        	<tr id="id_row${EZF_ROW_NUMBER}" height="28">
			                                <!-- button -->
			                                <td>
												<% if (ZYPCommonFunc.hasValue(bMsg.A.no(idx).mtrReadDt_A1)) { %>
			                                		<ezf:inputButton name="SelectMeterRead" value="S" ezfHyo="A" ezfArrayNo="0" htmlClass="pBtn0" otherAttr=" id=\"SelectMeterRead#{EZF_ROW_NUMBER}\" style=\"width:25\" tabindex=\"-1\""/>
												<% }else { %> &nbsp; <% } %>
			                                </td>
			                                <!-- Counter -->
											<td><ezf:label name="mtrLbDescTxt_A1" ezfName="mtrLbDescTxt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
			                                <!-- Read Date -->
											<td><ezf:label name="mtrReadDt_A1" ezfName="mtrReadDt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
			                                <!-- Read -->
											<td><ezf:label name="readMtrCnt_A1" ezfName="readMtrCnt_A1" ezfHyo="A" ezfArrayNo="0" /></td>
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
	</table>
<!-- <%-- ######################################## TO (COMMON)DETAIL ########################################## --%> -->
</center>


<%-- **** Task specific area ends here **** --%>
