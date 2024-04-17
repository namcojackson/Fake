<%-- This file was generated from the JSP conversion tool and 4.0.0 automatically. --%>
<%-- Generated on:20100203064202 --%>
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

<style type="text/css">
<!--
td.head1{font-size:10.0pt;font-family:'Arial',sans-serif;color:#929292;font-weight:bold;language:en-US;}
td.head2{font-size:9.0pt;font-family:'Arial',sans-serif;color:#929292;language:en-US;}
td.body1{font-size:11.0pt;font-family:'Arial',sans-serif;color:#AC0001;text-decoration: none; font-weight:bold;language:en-US;}
td.body2{font-size:8.0pt;font-family:'Arial',sans-serif;color:#929292;font-weight:bold;}
td.body3{font-size:8.0pt;font-family:'Arial',sans-serif;color:#929292;}
-->
</style>
			<!-- Set Page ID  -->
			<input type="hidden" name="pageID" value="ZZSL0000Scrn00">
			<!-- Set Page Name -->
			<input type="hidden" name="pageName" value="LoginScreen">
			
<table width="100%">
	<tr>
		<td width="334"><img style='margin-top:23px;margin-left: 28px;margin-bottom: 30px;'
			src="img/menu/canon.gif" alt="logo"></td>
	</tr>
	<tr>
		<td>
		<span style='padding: 0px;'><img width=282 height=480 src="img/spacer.gif"></span>
		</td>
		<td style='vertical-align:middle; text-align: left;'>
	<div>
	<br><br><br><br><br>
	</div>
		
		<div style="width:50%; height:100%;text-align:left; margin-left:50px; margin-top: 30px;" align="left">
 
		<table align="left" width="95%"
			style="border-collapse:collapse; border-color:#8DAEAE; border-width: 1px; border-style:solid;vertical-align: bottom;"
			border="0" height="210">
			<tr>
				<td align="center" width="100%"  style="background-color: #c60c30; color: #FFFFFF;	font-family: Impact;font-size: 18pt;vertical-align: top;padding-bottom: 5px;">Canon  Single  Sign-on  Login</td>
			</tr>
			<tr bgcolor="#F5F5F5"
				style="background-image: url('img/spacer.gif');background-repeat: repeat-x;">
				<td>
				<table width="100%" align="center">
 
					<tr>
						<td></td>
						<td style="font-size:11.0pt; font-family:'Arial',sans-serif;font-weight: bold;color: #4d4d4d;text-align: right;padding-bottom: 10px;">User ID :&nbsp;&nbsp;</td>
						<td><ezf:inputText name="loginID" ezfName="loginID" otherAttr=" size=\"17\" ezftoupper=\"\""/></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size:11.0pt; font-family:'Arial',sans-serif;font-weight: bold;color: #4d4d4d;text-align: right;padding-bottom: 10px;">Password :&nbsp;&nbsp;</td>
						<td><ezf:inputPassword name="loginPW" ezfName="loginPW" otherAttr=" size=\"17\""/></td>
					</tr>
				</table>
 
				<table width="100%" style="background:#F5F5F5" align="center">
 
					<tr>
						<td align="center" style="padding-bottom: 10px;">
						<ezf:inputButton name="FORWARD" value="LOGIN" htmlClass="pBtn7" />
						</td>

 
					</tr>
 
 
					<tr>
						<td align="center"><SPAN
							style="text-align:center; FONT-SIZE: 10pt; COLOR: #4d4d4d;  FONT-FAMILY: Arial; language: en-US">Forgot
						Password ?</SPAN></td>
					</tr>
					<tr>
						<td align="center"><SPAN
							style="text-align:center; FONT-SIZE: 10pt; COLOR: #4d4d4d;  FONT-FAMILY: Arial; language: en-US">Change
						Password</SPAN></td>
					</tr>
				</table>
			  </td>
			 </tr>
			</table>
			
		   </div>
		  </td>
		 </tr>
		 <tr>
		<td></td>
		<td></td>
	    </tr>
    </table>
    <table>
	<tr>
		<td>
			<span style="font-family: Arial; font-size: 8pt; margin-left: 460px;">
				c2009 Canon U.S.A.,Inc. All Rights Reserved. Reproduction in whole or in part without permission is prohibited. 
			</span>
		</td>
	</tr>
</table>

            </div>
			<ezf:inputHidden name="ezBusinessID" ezfName="ezBusinessID" />
	<style type="text/css">
	<!--
	body {
	background: #ffffff
	}
	-->
	</style>

<%-- **** Task specific area ends here **** --%>
