<%@ page import="java.math.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="oracle.apps.fnd.common.*" %>
<%@ page import="oracle.apps.jtf.base.session.*" %>
<%@ page import="oracle.apps.jtf.aom.transaction.*" %>

<%--
 +===========================================================================+
 |                                                                           |
 +===========================================================================+
 |  FILENAME                                                                 |
 |    canonE437Error.jsp                                              		 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |    Enables performing physical inventory counts from handheld device    	 |
 |                                                                           |
 |  NOTES                                                                    |
 |                                                                           |
 |  DEPENDENCIES                                                             |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                        	                 |
 +===========================================================================+
--%>

<%
	String errorMsg = request.getParameter("errorMsg");
%>
<html>
	<head>
		<title>Physical Inventory</title>
		<link rel="stylesheet" href="../css/CanonE410PartScanning.css" type="text/css">
	</head>
	
	<body onhelp="return false;" onkeydown="whichButton(event);" onload="focusOnLoad();">
		<form name="physicalInventory">
			<fieldset>
				<div name="errorMsg" id="errorMsg"><b> <legend><%=errorMsg%></legend></b></div>
			</fieldset>
		</form>
	</body>
</html>
