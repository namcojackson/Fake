<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20220614114244 --%>
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
			<input type="hidden" name="pageID" value="NWCL0060Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Mail Entry">

<center>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<col width="100">
		<tr>
			<td>&nbsp;</td>
			<td>
				<table border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td>
							<table style="table-layout:fixed" border="0" cellpadding="0" cellspacing="0">
								<col width="121">
								<col width="25">
								<col width="205">
								<col width="25">
								<col width="205">
								<tr>
									<td rowspan="2" class="stab" valign="top">From Address</td>
									<td rowspan="2" valign="top"><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="1" /></td>
									<td rowspan="2" class="stab" valign="top">
										Your Email Address<br>
										<ezf:label name="fromEmlAddr_H1" ezfName="fromEmlAddr_H1" />
									</td>
									<td rowspan="2" valign="top"><ezf:inputRadio name="xxRadioBtn_H1" ezfName="xxRadioBtn_H1" value="2" /></input></td>
									<td rowspan="2" class="stab" valign="top">
										Business Area Email Address<br>
										<ezf:label name="fromEmlAddr_H2" ezfName="fromEmlAddr_H2" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="stab">To Address</td>
					</tr>
					<tr>
						<td><ezf:inputText name="emlAddr_H1" ezfName="emlAddr_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"80\" maxlength=\"240\""/></td>
					</tr>
					<tr>
						<td class="stab">Subject</td>
					</tr>
					<tr>
						<td><ezf:inputText name="mlSubjTxt_H1" ezfName="mlSubjTxt_H1" value="WWWWWWWWW1WWWWWWWWW2WWWWWWWWW3WWWWWWWWW4WWWWWWWWW5" otherAttr=" size=\"80\" maxlength=\"200\""/></td>
					</tr>
					<tr>
						<td class="stab">Attachment</td>
					</tr>
					<tr>
						<td>
							<ezf:textArea name="xxArMlBodyTmplTxt_H1" ezfName="xxArMlBodyTmplTxt_H1" otherAttr=" cols=\"80\" rows=\"13\""/>
						</td>
					</tr>
					<tr>
						<td class="stab">Comments</td>
					</tr>
					<tr>
						<td>
							<ezf:textArea name="xxMlCmntTxt_H1" ezfName="xxMlCmntTxt_H1" otherAttr=" cols=\"80\" rows=\"12\""/>
						</td>
					</tr>
					<tr>
						<td align="right"><ezf:inputButton name="Cancel" value="Cancel" htmlClass="pBtn6" otherAttr=" id=\"Cancel\""/>&nbsp;<ezf:inputButton name="SendMail" value="Send Email" htmlClass="pBtn6" otherAttr=" id=\"SendMail\""/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</center>




<%-- **** Task specific area ends here **** --%>
