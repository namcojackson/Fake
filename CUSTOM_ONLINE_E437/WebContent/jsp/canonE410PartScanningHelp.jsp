<%@ page import="com.canon.apps.e437.*" %>
<%@ page import="java.math.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="oracle.apps.jtf.base.resources.*" %>
<%@ page import="oracle.apps.fnd.common.*" %>




<%--
 +===========================================================================+
 |                                                                           |
 +===========================================================================+
 |  FILENAME                                                                 |
 |    <canonE410PartScanningOrg.jsp>                                     	 |
 |                                                                           |
 |  DESCRIPTION                                                              |
 |    <Organization Definition/selection page>        						 |
 |                                                                           |
 |  NOTES                                                                    |
 |                                                                           |
 |  DEPENDENCIES                                                             |
 |                                                                           |
 |  HISTORY                                                                  |
 |                                                                           |
 |                                                                           |
 +===========================================================================+
--%>

<%String dataFormat = "DD-MON-RRRR";%>

<html>
	<head>
		<title>Canon Parts Scanning Help</title>
		<link rel="stylesheet" href="../css/CanonE410PartScanning.css" type="text/css">
	</head>
	
<script src="../js/jtfdsnfb.js" language="javascript"></script>
<script src="../js/jtfdtfrm.js" language="javascript"></script>
<script language="javascript">
NLSformat = 'DD-MON-RRRR';
function disableDefault()
	{
		var keyCode = event.keyCode;
		//alert(keyCode);
		//if(keyCode == 122){
		//event.returnValue = false;}
		if(keyCode == 0){
		event.returnValue = false;}
		return false;
	}
</script>

<BODY onhelp="disableDefault();">
<fieldset>
   <a><b> <legend>Parts Scanning Help</legend></b><a>
<br>
<table width="100%" border="0" >
		<tr><td class="hd"><b>Help (F1)</td><td> <img src="../images/canonE410Fnc.JPG" border = "0" alt="" height="15"> + <img src="../images/canonE410p1.JPG" border = "0" alt="" height="15"></b></td></tr>
		<tr><td class="hd"><b>Main Menu (F2)</td><td> <img src="../images/canonE410Fnc.JPG" border = "0" alt="" height="15"> + <img src="../images/canonE410p2.JPG" border = "0" alt="" height="15"></b></td></tr>
		<tr><td class="hd"><b>Cancel (F3)</td><td> <img src="../images/canonE410Fnc.JPG" border = "0" alt="" height="15"> + <img src="../images/canonE410p3.JPG" border = "0" alt="" height="15"></b></td></tr>
</table>
</br>
</fieldset>
</BODY>