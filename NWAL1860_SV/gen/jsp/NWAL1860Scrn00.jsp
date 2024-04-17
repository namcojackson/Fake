<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160203113233 --%>
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




<table width="90%" align="center">
	<tr>
		<td>
			<table height="400">
				<tr>
					<td width="200"></td>
					<td width="80"></td>
					<td width="20"></td>
					<td width="80"></td>
				</tr>
				<tr>
					<td class="stab" align="left">Line#s<br>comma-delimited</td>
					<td colspan = "3"><ezf:inputText name="xxTrxLineNumListTxt" ezfName="xxTrxLineNumListTxt" value="1,4,5,6,7,8,910" otherAttr=" size=\"32\" maxlength=\"100\" ezftoupper=\"\""/></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="stab" align="left">Each Deliveriy Qty</td>
					<td><ezf:inputText name="ordQty" ezfName="ordQty" value="1" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="stab" align="left">Shipping Interval</td>
					<td colspan = "3">
						<ezf:select name="shpgIntvlCd" ezfName="shpgIntvlCd" ezfCodeName="shpgIntvlCd_P" ezfDispName="shpgIntvlDescTxt_P" otherAttr=" style=\"width:150px\""/>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="stab" align="left">Delivery Month Day</td>
					<td>
						<ezf:select name="xxDay" ezfName="xxDay" ezfCodeName="xxDay_P1" ezfDispName="xxDay_P2" otherAttr=" style=\"width:50px\""/>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="stab" align="left">Start Deliveries Months/Year</td>
					<td>
						<ezf:select name="xxMthDt_ST" ezfName="xxMthDt_ST" ezfCodeName="xxMthDt_P" ezfDispName="xxMthDplyDescTxt_P" otherAttr=" style=\"width:100px\""/>
					</td>
					<td class="stab" align="center">/</td>
					<td>
						<ezf:select name="xxYrDt_ST" ezfName="xxYrDt_ST" ezfCodeName="xxYrDt_P1" ezfDispName="xxYrDt_P2" otherAttr=" style=\"width:100px\""/>
					</td>
				</tr>
				<tr>
					<td class="stab" align="left">End Deliveries Months/Year</td>
					<td>
						<ezf:select name="xxMthDt_EN" ezfName="xxMthDt_EN" ezfCodeName="xxMthDt_P" ezfDispName="xxMthDplyDescTxt_P" otherAttr=" style=\"width:100px\""/>
					</td>
					<td class="stab" align="center">/</td>
					<td>
						<ezf:select name="xxYrDt_EN" ezfName="xxYrDt_EN" ezfCodeName="xxYrDt_P1" ezfDispName="xxYrDt_P2" otherAttr=" style=\"width:100px\""/>
					</td>
				</tr>
				<tr>
					<td class="stab" align="left">Valid Date</td>
					<td><ezf:inputText name="schdAgmtVldFromDt" ezfName="schdAgmtVldFromDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
					<td class="stab" align="center">/</td>
					<td><ezf:inputText name="schdAgmtVldThruDt" ezfName="schdAgmtVldThruDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
				</tr>
				<tr>
					<td class="stab" align="left">Planed Max Date</td>
					<td><ezf:inputText name="pddDt" ezfName="pddDt" value="01/01/2015" otherAttr=" size=\"10\" maxlength=\"10\" ezftoupper=\"\""/></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>



			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NWAL1860Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Schedule Line Entry Assist Popup">
		
			

<%-- **** Task specific area ends here **** --%>
