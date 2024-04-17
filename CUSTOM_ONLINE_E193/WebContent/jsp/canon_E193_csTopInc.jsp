<!-- $Header: canon_E193_csTopInc.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |	canon_E193_csTopInc.jsp - Initializes variables.
 |   
 | DESCRIPTION
 |   Contains standard includes, Initializes variables and has starting body tag.
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	07/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 +=======================================================================--%>
<%@page language="java"%>
<%@page import="java.net.URLEncoder"%>

<%
System.out.println("in top inc ");
String strCss = "canon_E193_csStyleSheet.css";
String strBodyClass = "popupBody";
String strIsoLangCode = "EN-US";//oracle.apps.jtf.util.HtmlUtil.getHtmlLanguageCode();
String strPageName  = "";
boolean isActivePage = false;
String strSubMenuPageName  = ""; 
String strPageFrom  = ""; 
// String strPath  = session.getAttribute("pathName"); 
int iBodyTabWidth = 900; 
System.out.println("strCss is" + strCss);
try
{
%>
<%@ include file="canon_E193_csCheckSessionInc.jsp" %>  
<HTML lang="<%=strIsoLangCode%>">
<HEAD>

  <% String ctxPath = request.getContextPath(); 
 	 System.out.println("ctxPath cust care : " + ctxPath);
  %>
  <TITLE>Canon Customer Care</TITLE>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">    
  <link href="<%=ctxPath%>/common/jquery/jquery-ui.css" rel='stylesheet' type='text/css' />
  <%-- <script src="<%=ctxPath%>/common/jquery/jquery-1.10.2.min.js" type='text/javascript'></script>   		
  <script src="<%=ctxPath%>/common/jquery/jquery-ui-1.10.3.custom.min.js" type='text/javascript'></script> --%>
   <script src="<%=ctxPath%>/common/jquery/jquery-3.5.1.min.js" type='text/javascript'></script>
   <script src="<%=ctxPath%>/common/jquery/jquery-ui.min.js" type='text/javascript'></script>
    <script src="<%=ctxPath%>/e193/js/canon_E193PrintPdf.js" type='text/javascript'></script>
    <script src='<%=ctxPath%>/common/jquery/jquery.blockUI2.js' type='text/javascript'></script>	
	<script src='<%=ctxPath%>/common/jquery/jquery.timepicker.js' type='text/javascript' ></script>
    <link href='<%=ctxPath%>/common/jquery/jquery.timepicker.css' rel="stylesheet" type="text/css">
    <script type="text/javascript" src='<%=ctxPath%>/common/jquery/autoNumeric-1.6.2.js'></script>	
    <script src='<%=ctxPath%>/common/jquery/jquery-ui-timepicker-addon.js' type='text/javascript'></script>
    <script src='<%=ctxPath%>/common/jquery/ui.core.js' type='text/javascript'></script>
	<script	src='<%=ctxPath%>/common/jquery/jquery.placeholder.min.js' type="text/javascript"></script>
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/styles.css" type="text/css">
	<link rel="stylesheet" href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css" type="text/css">
	
	<script src="<%=ctxPath%>/e193/js/canon_E193_csCButton.js" type='text/javascript'></script>   		
   <script src="<%=ctxPath%>/e193/js/canon_E193_csHomeVald.js" type='text/javascript'></script> 

</HEAD>
<body>