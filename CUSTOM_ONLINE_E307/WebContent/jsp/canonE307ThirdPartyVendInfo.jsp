
<%@ page language="java" import="java.sql.*" %>
<%@ page language="java" import="java.text.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="com.canon.common.CanonCommonUtil"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,IE=8" />
    <meta charset="utf-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<style>
	 .ui-dialog .ui-dialog-title{
	 	float: none;
	 }
.supplies-table {
	width: 100%;
	
	margin-bottom: 5px;
	}
.supplies-table tr:nth-child(even) {
	background-color: #FFFFFF;
	}
.supplies-table tr:nth-child(odd) {
	background-color: #ebebeb;
	}
.supplies-table th, .supplies-table td {
	padding: 13px;
	border: none;
	}
.supplies-table th {
	font-weight: bold;
	text-align: center;
	background: #003b4e;
	color: #FFFFFF;
	font-size: 14px;	
	}
.supplies-table td {
	text-align: left;
	font-family: 'OpticSansBook', sans-serif;
	font-size: 13px;	
	}
.supplies-table input[type=text] {
	font-family: 'OpticSansBook', sans-serif;
	font-size: 13px;
	border: 1px #cccccc solid;
  	width: 132px;
  	height: 26px;
  	background: #FFFFFF;
  	margin-right: 0px;
	}
.supplies-table th a:link, .supplies-table th a:visited, .supplies-table th a:active {
	color: #FFFFFF;
	text-decoration: none !important;
    }
.supplies-table th a:hover, .supplies-table th a:visited:hover {
	text-decoration: underline !important;
	}	
	
	.btn:hover {
	background: -webkit-linear-gradient(#ff0000, #cc0000);
	background: -moz-linear-gradient(center top , #ff0000 5%, #cc0000 100%) repeat scroll 0 0 white;
	background: -ms-linear-gradient(#ff0000, #cc0000);
	background: linear-gradient(#ff0000, #cc0000);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#ff0000', endColorstr='#cc0000');
	background-color: #cc0000;
	text-decoration: none !important;
	}
	
.btn {
		font-size: 12px !important;
	color: #FFFFFF !important;
	background: -webkit-linear-gradient(#cc0000, #8e191c);
	background: -moz-linear-gradient(center top , #cc0000 5%, #8e191c 100%) repeat scroll 0 0 white;
	background: -ms-linear-gradient(#cc0000, #8e191c);
	background: linear-gradient(#cc0000, #8e191c);
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#cc0000', endColorstr='#8e191c');
	background-color: #8e191c;
	border: none;
	cursor: pointer;
	padding: 5px 12px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	text-decoration: none !important;
	}	
</style>

<%
	CanonCommonUtil utl = new CanonCommonUtil();
	String strVendName = request.getParameter("vendName");
	String strVendContact = request.getParameter("vendContact");
	String strVendPhone  = request.getParameter("vendPhone");
	String strVendEmailAddrs  = request.getParameter("vendEmailAddrs");
	String strCovidVacInfo = utl.checkStrNull(request.getParameter("covidVacMsg"));
	
	
	System.out.println("Inside Vend Info : " + strVendName+", "+strVendContact+", "+strVendPhone+", "+strVendEmailAddrs+", "+strCovidVacInfo);

	

    
 %>
 <table class="supplies-table" style="width:98%;height:90%;border:1px solid #CCC;""  cellpadding="1" cellspacing="1">
 	<tr><th colspan=2>Vendor Information</th></tr>
	 <tr><td style='width:40%;'>Name			:</td><td><%=utl.checkStrNull(strVendName) %></td></tr>
	 <tr><td style='width:40%;'>Contact		    :</td><td><%=utl.checkStrNull(strVendContact) %></td></tr>
	 <tr><td style='width:40%;'>Phone#			:</td><td><%=utl.checkStrNull(strVendPhone) %></td></tr> 
	 <tr><td style='width:40%;'>Email Address	:</td><td><%=utl.checkStrNull(strVendEmailAddrs) %></td></tr> 
	 
	 <%
	 if(strCovidVacInfo!=null && strCovidVacInfo.length()>0){
	 %>
	 <tr><td colspan=2 style="color: red;font-size: 15px;font-weight: bold;" align="center"><%=strCovidVacInfo%></td></tr> 
	 <%} %>
 	<!--  <tr class="trEmpty" rowspan=2><td>&nbsp;</td></tr>  -->
	</table> 

   <table style="width: 98%;align:center;background-color:none;">
	  <tr align="left" style="background-color:none;">
	  	<td style='width:45%;'>&nbsp;</td>
	 	  <td><a href="javascript:window.close()" class="btn" style="white-space: nowrap;">OK</a></td>
	 </tr>
   </table>
</html>
 