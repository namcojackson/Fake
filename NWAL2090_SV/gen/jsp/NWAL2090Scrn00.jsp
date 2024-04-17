<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20160107135811 --%>
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
			<input type="hidden" name="pageID" value="NWAL2090Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Change Order Modification">

				<center>
					<table style="width:600px; table-layout:fixed;" cellspacing="1">
						<col width="120">
						<col width="480">
						<tr height="28">
							<td>Change Order #:</td>
							<td><ezf:inputText name="cpoOrdNum" ezfName="cpoOrdNum" value="ORD001" otherAttr=" size=\"28\" maxlength=\"6\" ezftoupper=\"\""/></td>
						</tr>
						<tr height="28">
							<td>* Reason Code:</td>
							<td>
								<ezf:select name="chngRsnTpCd" ezfName="chngRsnTpCd" ezfCodeName="chngRsnTpCd_CD" ezfDispName="chngRsnTpNm_DI" otherAttr=" style=\"width : 220px;\""/>
							</td>
						</tr>
						<tr>
							<td align="top">* Comments:</td>
							<td><ezf:textArea name="xxComnScrColValTxt" ezfName="xxComnScrColValTxt" otherAttr=" rows=\"23\" cols=\"70\" maxlength=\"1600\""/></td>
						</tr>
						<tr height="28">
							<td colspan = "2" align="center">
								<ezf:inputButton name="CMN_OK" value="OK" htmlClass="pBtn4" />	
								&nbsp;
								<ezf:inputButton name="CMN_Cancel" value="Cancel" htmlClass="pBtn4" />
							</td>
						</tr>
					</table>
				</center>

<%-- **** Task specific area ends here **** --%>
