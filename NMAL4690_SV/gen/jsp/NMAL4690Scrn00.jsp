<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100603002451 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="parts.servletcommon.*" %>
<%@ page import="parts.common.*" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%-- I18N START --%>
<%@ page import="parts.i18n.*" %>

<%	pageContext.setAttribute("ezdi18nlocale", EZDI18NContext.getInstance().getI18NAccessor().getLocale()); %>
<fmt:setLocale value="${ezdi18nlocale}" scope="request" />
<fmt:setBundle basename="I18N_NMAL4690Scrn00" var="I18N_SCREEN_ID" scope="request" />
<fmt:setBundle basename="I18N" var="I18N_DEFAULT" scope="request" />
<%-- I18N END --%>

<%-- **** Use Data Bean Name Setting Area(class name including package) **** --%>
<%
 //Acquisition of the data bean used on this page
 EZDCommonDataBean databean = (EZDCommonDataBean)session.getAttribute(request.getParameter("beanId"));
 //<ezf:xxx>Prepare usage for custom tag
 pageContext.setAttribute(EZDTaglibCommon.DATABEAN_KEY, databean);
%>

<%-- **** Task specific area starts here **** --%>

			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="NMAL4690Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="<fmt:message key="i18n.NMAL4690Scrn00.title" bundle="${I18N_SCREEN_ID}">Customer Inquiry Attachment</fmt:message>">

<center>
	<table width="100%">
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="4">
					<tr>
						<td class="stab">
							<table border="0" cellpadding="1" cellspacing="0">
								<tr>
									<td class="stab"><fmt:message key="i18n.NMAL4690Scrn00.label.1" bundle="${I18N_SCREEN_ID}">Company_Name</fmt:message></td>
									<td><ezf:inputText name="cmpyNm" ezfName="cmpyNm" value="XXXXXXXXX1XXXXXXXXX2XXXXXXXXX3" otherAttr=" size=\"60\" maxlength=\"60\" tabindex=\"4\" ezftoupper=\"\""/></td>
								</tr>
							</table>
						</td>
						<td align="right" width="560">	
							<table border="0" cellpadding="0" width="100%">
							    <tr align="right">
							        <td>
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
						</td>
					</tr>
				</table>
				<table border="1" cellpadding="1" cellspacing="0" id="A">
					<col width="65">
					<col width="240">
					<col width="145">								
 					<col width="100">
					<col width="140">
					<col width="80">
					<col width="70">
					<tr>
						<td class="pClothBs" align="center">&nbsp;</td>
						<td class="pClothBs" align="center"><fmt:message key="i18n.NMAL4690Scrn00.label.2" bundle="${I18N_SCREEN_ID}">File Name</fmt:message></td>
						<td class="pClothBs" align="center"><fmt:message key="i18n.NMAL4690Scrn00.label.3" bundle="${I18N_SCREEN_ID}">Activity</fmt:message></td>
 						<td class="pClothBs" align="center"><fmt:message key="i18n.NMAL4690Scrn00.label.4" bundle="${I18N_SCREEN_ID}">Uploaded Process</fmt:message></td>
						<td class="pClothBs" align="center"><fmt:message key="i18n.NMAL4690Scrn00.label.5" bundle="${I18N_SCREEN_ID}">Uploaded Date</fmt:message></td>
						<td class="pClothBs" align="center"><fmt:message key="i18n.NMAL4690Scrn00.label.6" bundle="${I18N_SCREEN_ID}">Size</fmt:message></td>
						<td class="pClothBs" align="center"><fmt:message key="i18n.NMAL4690Scrn00.label.7" bundle="${I18N_SCREEN_ID}">Description</fmt:message></td>
					</tr>
					<ezf:row ezfHyo="A">
					<tr class="pEvenNumberBGcolor">
						<td><ezf:inputButton name="SelectBtn" value="Select" ezfHyo="A" ezfArrayNo="0" /></td>
						<td><ezf:inputText name="mstrAttDataNm_A" ezfName="mstrAttDataNm_A" value="Cust1_Map.jpg" ezfHyo="A" ezfArrayNo="0" htmlClass="pOut" otherAttr=" size=\"33\" maxlength=\"256\""/></td>
						<td><ezf:inputText name="mstrActvNm_A" ezfName="mstrActvNm_A" value="Letter of Acknowledgement" ezfHyo="A" ezfArrayNo="0" htmlClass="pOut" otherAttr=" size=\"20\" maxlength=\"100\""/></td>									
 						<td><ezf:inputText name="xxCondNm_A" ezfName="xxCondNm_A" value="New Cust/Loc" ezfHyo="A" ezfArrayNo="0" htmlClass="pOut" otherAttr=" size=\"20\" maxlength=\"100\""/></td>
						<td><ezf:inputText name="xxAllPtyAddr_A" ezfName="xxAllPtyAddr_A" value="04/01/2010 19:30:15" ezfHyo="A" ezfArrayNo="0" htmlClass="pOut" otherAttr=" size=\"20\" maxlength=\"30\""/></td>
						<td><ezf:inputText name="mstrAttDataVol_A" ezfName="mstrAttDataVol_A" value="1 byte" ezfHyo="A" ezfArrayNo="0" htmlClass="pOut" otherAttr=" size=\"10\" maxlength=\"30\""/></td>
						<td><ezf:inputText name="mstrAttDataDescTxt_A" ezfName="mstrAttDataDescTxt_A" value="Customer location map 1" ezfHyo="A" ezfArrayNo="0" htmlClass="pOut" otherAttr=" size=\"20\" maxlength=\"500\""/></td>
					</tr>
					</ezf:row>
					<ezf:skip>
					</ezf:skip>
				</table>
			</td>
		</tr>
	</table>
</center>

<%-- **** Task specific area ends here **** --%>
