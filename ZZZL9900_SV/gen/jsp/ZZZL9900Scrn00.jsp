<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20090310095831 --%>
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
			<input type="hidden" name="pageID" value="ZZZL9900Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="Simple Menu Screen">

<center>

	<table width="100%" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<!-- Main Screen Test -->
			<td width="50%" align="center" valign="top">
			<fieldset>
				<legend><font size="3" style="font-weight:bold">Main Screen Test</font></legend>

				<table border="0" >
					<tr>
						<td>
							<table border="1" cellpadding="1" cellspacing="0">
								<tr>
									<td class="stab">Business Application ID</td>
									<td><ezf:inputText name="ezBusinessID_01" ezfName="ezBusinessID_01" otherAttr=" size=\"8\""/></td>
								</tr>
								<tr>
									<td class="stab">User ID</td>
									<td><ezf:inputText name="ezUserID_01" ezfName="ezUserID_01" otherAttr=" size=\"17\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab">UserName</td>
									<td><ezf:inputText name="ezUserName_01" ezfName="ezUserName_01" otherAttr=" size=\"17\""/></td>
								</tr>
								<tr>
									<td class="stab">Password</td>
									<td><ezf:inputText name="ezPassword_01" ezfName="ezPassword_01" otherAttr=" size=\"17\""/></td>
								</tr>
								<tr>
									<td class="stab">Screen ID (transition from)</td>
									<td><ezf:inputText name="ezScreenID_01" ezfName="ezScreenID_01" value="ZZZL9900Scrn00" otherAttr=" size=\"14\" maxlength=\"14\""/></td>
								</tr>
							</table>
						<td>
					</tr>
				</table>
				
				<br>

				<table border="0" cellpadding="1" cellspacing="0">
					<col width="100">
					<col width="">
					<col width="100">
					<tr>
						<td><ezf:inputButton name="SetMainParameter" value="Set Stub Parameter" htmlClass="pBtn11" /></td>
						<td>&nbsp;</td>
						<td id="TD_OnBusiness"><ezf:inputButton name="OnBusiness" value="OnBusiness" htmlClass="pBtn11" /></td>
					</tr>
				</table>				
			</fieldset>
			</td>

			<!-- Popup Screen Test -->
			<td width="50%" align="center" valign="top">
			<fieldset>
				<legend><font size="3" style="font-weight:bold" color="red">Popup Screen Test</font></legend>
				
				<table border="0" >
					<tr>
						<td>
							<table border="1" cellpadding="1" cellspacing="0">
								<tr>
									<td class="stab">Business Application ID</td>
									<td><ezf:inputText name="ezBusinessID_02" ezfName="ezBusinessID_02" otherAttr=" size=\"8\""/></td>
								</tr>
								<tr>
									<td class="stab">User ID</td>
									<td><ezf:inputText name="ezUserID_02" ezfName="ezUserID_02" otherAttr=" size=\"17\" ezftoupper=\"\""/></td>
								</tr>
								<tr>
									<td class="stab">UserName</td>
									<td><ezf:inputText name="ezUserName_02" ezfName="ezUserName_02" otherAttr=" size=\"17\""/></td>
								</tr>
								<tr>
									<td class="stab">Password</td>
									<td><ezf:inputText name="ezPassword_02" ezfName="ezPassword_02" otherAttr=" size=\"17\""/></td>
								</tr>
								<tr>
									<td class="stab">Screen ID (transition from)</td>
									<td><ezf:inputText name="ezScreenID_02" ezfName="ezScreenID_02" value="ZZZL9900Scrn00" otherAttr=" size=\"14\" maxlength=\"14\""/></td>
								</tr>
							</table>
						<td>
					</tr>
				</table>
				
				<br>
				
				<table border="0" cellpadding="1" cellspacing="0">
					<col width="100">
					<col width="">
					<col width="100">
					<tr>
						<td><ezf:inputButton name="SetPopupParameter" value="Set Stub Parameter" htmlClass="pBtn11" /></td>
						<td>&nbsp;</td>
						<td id="TD_OpenPopup"><ezf:inputButton name="OpenPopup" value="Open Popup" htmlClass="pBtn11" /></td>
					</tr>
				</table>
			</fieldset>
			</td>
		</tr>
	</table>
	
	<br>
	
	<!-- Stub Parameter -->
	<table width="100%" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<td width="100%" align="center" valign="top">
			<fieldset>
				<legend><font size="3" style="font-weight:bold" color="green">Stub Parameter</font></legend>

				<table border="1" cellpadding="1" cellspacing="0" width="100%">
					<tr>
						<td class="pClothBs" align="center">Stub Data Directory</td>
					</tr>
					<tr style="background-color:#FFFFFF">
						<td><%= session.getAttribute( "stub_dir" ) %></td>
					</tr>
				</table>
				
				<table border="1" cellpadding="1" cellspacing="0" width="100%">
					<tr>
						<td class="pClothBs" align="center">Stub Data</td>
					</tr>
				</table>
				
				<div style="overflow:scroll; width:1000; height:250; background-color:#FFFFFF">
					<table border="0" cellpadding="1" cellspacing="0" width="100%">
						<tbody>
						<tr>
							<td>
								<% Object[] objs = (Object[])session.getAttribute( "Stub_Parameters" ); %>
								<% if( objs != null ) { %>
									<% for( int i = 0; i < objs.length; i++ ) { %>
										<font style="font-weight:bold">+ param[<%=i%>]</font><br>
										&nbsp;&nbsp;class = <%= objs[i].getClass().getName() %><br>
										
										<% if( objs[i] instanceof EZDBItem ) { %>
											<% if( objs[i] instanceof EZDBStringItem ) { %>
												&nbsp;&nbsp;value = [<%= ((EZDBStringItem)objs[i]).getValue() %>]<br>
											<% } else if( objs[i] instanceof EZDBBigDecimalItem ) { %>
												&nbsp;&nbsp;value = [<%= ((EZDBBigDecimalItem)objs[i]).getValue() %>]<br>
											<% } else if( objs[i] instanceof EZDBDateItem ) { %>
												&nbsp;&nbsp;value = [<%= ((EZDBDateItem)objs[i]).getValue() %>]<br>
											<% } %>
											
										<% } else { %>
										<% } %>
									<% } %>
								<% } %>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>

<script type="text/javascript">
<!--
	html_OnBusiness = '<input type="button" name="OnBusiness" value="OnBusiness" class="pBtn11" onclick="document.getElementsByName( \'pageID\' )[0].value = document.getElementsByName( \'ezScreenID_01\' )[0].value;sendServer(this);">';
	document.getElementById( 'TD_OnBusiness' ).innerHTML = html_OnBusiness; 
	
	html_OnBusiness = '<input type="button" name="OpenPopup" value="Open Popup" class="pBtn11" onclick="document.getElementsByName( \'pageID\' )[0].value = document.getElementsByName( \'ezScreenID_02\' )[0].value;sendServer(this);">';
	document.getElementById( 'TD_OpenPopup' ).innerHTML = html_OnBusiness; 
//-->
</script>
	
</center>

<%-- **** Task specific area ends here **** --%>
