<!-- $Header: canon_E193_csErrorPage.jsp $ -->
<%--========================================================================
 |
 | FILE 
 |  canon_E193_csErrorPage.jsp - Error Page.
 |   
 | DESCRIPTION
 |   Authenticates user and stores user information into existing session
 |
 | AUTHOR
 |  Dipti Shedji 
 |
 | CREATION DATE
 |  08/07/2005
 |
 | HISTORY
 | DATE     WHO         WHY
 | 18-Dec-06   Kireet K Bollam   ITG# 73987 CBS Consolidation Changes
 |
 +=======================================================================--%>

<%@page language="java"%>
<%@include file="canon_E193_csStdTxt.jsp" %>
<% String ctxPath = request.getContextPath(); %>

<%
String strCss = "canon_E193_csStyleSheet.css";
String strBodyClass = "popupBody";
String strIsoLangCode = "EN-US";//oracle.apps.jtf.util.HtmlUtil.getHtmlLanguageCode();
String strPageName  = "Error Page"; 
String strPageFrom  = ""; 
// String strPath  = session.getAttribute("pathName"); 
int iBodyTabWidth = 900; 

try
{
%>

<HTML lang="<%=strIsoLangCode%>">
<HEAD>
  <TITLE>Customer Inquiry</TITLE>  
  <SCRIPT LANGUAGE="JavaScript" SRC="<%=ctxPath%>/e193/js/canon_E193_csCButton.js"></script>
  <link rel=stylesheet href="<%=ctxPath%>/e193/css/styles.css">
  <link rel=stylesheet href="<%=ctxPath%>/e193/css/canon_E193_csStyleSheet.css">
</HEAD>
<body>

<% 
   
   String strErrMessage = request.getParameter("err");
   String strIsSessionMsg = request.getParameter("isSessionMsg");
   int flag = 0;
   
   if(strErrMessage == null) {
     	strErrMessage = "Contact System Administrator";
   } else if("ActalInvViewErr".equalsIgnoreCase(strErrMessage)) {
		flag = 1;
		strErrMessage = "Actual invoice is not available on 170 System";
   /* ITG# 73987 - Begin */
   //Kireet K Bollam
   } else if("RegionCodeIncorrectErr".equalsIgnoreCase(strErrMessage)) {
		flag = 1;
		strErrMessage = strRegionCodeIncorrect; 
   }
   /* ITG# 73987 - End */

%>  

 <script>
	 function action_func1()
	 {
	 	if(window.opener != null) {
	 		window.opener.location.href = "AppsLocalLogin.jsp";
	 		self.close();
		}else{
			document.errorPageForm.action = "AppsLocalLogin.jsp";
			document.errorPageForm.submit();
		}
	 }
 </script>
 <table class="request-service" style="align:center;" cellspacing="1" >
	<TR>
		<TD> 
		   <BR>
			<table width=900 cellpadding=0 cellspacing=0 border=0>
				<tr>
					<td style="background-color:#FFFFFF;padding:12px 10px 0px 10px;"><img src="<%=ctxPath%>/common/images/csa_logo.jpg" alt="Canon Solutions America" title="Canon Solutions America" WIDTH="210" HEIGHT="98"></TD> 
				</tr>
			</table>
		</TD>
	</TR>
	<TR>
		<TD width="900" height="23"> 
			<table width="900" border=0 cellpadding=0 cellspacing=0>
				<tr> 
			  		<td width="703" valign="middle" height="23" align="left" background="<%=ctxPath%>/e193/images/canon_E193_csNavbar.jpg"></td>
		  	 		<TD width="197" valign="middle" align="left" background="<%=ctxPath%>/e193/images/canon_E193_csNavbar01.jpg"></TD>
	        	</tr>
     	 	</table>
		</td>
	</TR>	
</table>

	<form name="errorPageForm" method="post">
	 <table class="request-service" style="align:center;" cellpadding="1" cellspacing="0" border="0">
	<tr><td height="16"></td></tr>
	<tr>
		<td>
			<font class="promptReadOnly"><b><%=strErrMessage%></b> </font>
		</td>
	</tr>
	<tr><td height="16"></td></tr>
	<tr>
		<td>
			<%
				if (flag == 1) {
			%>
				<a class="btn_red" href="javascript:window.close();">Close</a> 			
			<%
				}else if(strIsSessionMsg == null) {
			%>
				<a class="btn_red" href="javascript:history.back();">Back</a> 	
				<a class="btn_red" href="javascript:window.close();">Close</a> 					
			<%
				}else{
			%>
				<a class="btn_red" href="javascript:action_func1();">Login</a> 					
			<%
				}
			%>
		</td>
	</tr>
	<tr><td height="16"></td></tr>
	</table>
	</form>
<%
}
catch (Exception eExp) {
	response.sendRedirect("AppsLocalLogin.jsp");
}
%>
</body>
</html>
