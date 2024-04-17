<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.canon.cusa.s21.framework.generictable.fw.S21NEContainerDataBean" %>
<%@ page import="com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder" %>
<%@ page import="com.canon.cusa.s21.framework.security.context.S21SecurityContext" %>
<%@ page import="com.canon.cusa.s21.framework.security.authentication.details.S21IdentityDetails" %>
<%@ page import="parts.common.EZDMessageInfo" %>
<%@ page import="parts.common.*" %>
<%@ page import="parts.servletcommon.*" %>
<%-- JSP initialize --%>
<%
	S21NEContainerDataBean bean = (S21NEContainerDataBean) request.getAttribute("s21nevo_kim");
	S21SecurityContext context = S21SecurityContextHolder.getContext();
	S21IdentityDetails details = context.getAuthentication().getIdentityDetails();
	request.setAttribute("userDetails", details);
	
	//[MOD]T.Tsuji START
	String bodyCol = EZDSystemEnv.getString("S21.mode.color");
	if (bodyCol == null && !"".equals(bodyCol)) {
	    bodyCol = "#fafafa";
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
<script type="text/javascript" src="./js/common/common.js"></script>
<title>Generictable Maintenance</title>
<script type="text/javascript">
<!--
history.forward();
screen.updateInterval=5000;

function funcS21NEPost(code) {
    document.F1.S21NE_REQ_ID.value = code;
    funcSubmitUrlenc();
}

function funcS21NEPostScreenChange(screen, code) {
    document.F1.S21NE_SCREEN_ID.value = screen;
    document.F1.S21NE_REQ_ID.value = code;
    funcSubmitUrlenc();
}

function funcSubmitUrlenc() {
    document.F1.enctype="application/x-www-form-urlencoded";
    document.F1.encoding ="application/x-www-form-urlencoded"; // IE Problem

	focusElement = document.activeElement;
    focusElement.disabled = true;

	try {
    	document.F1.submit();
    } catch(e) {
    	if (e.number == -2147024891) {
	    	alert(e.description);
		}
	    focusElement.disabled = false;
    }
}

function helpOpen() {
}

function returnMenu() {
	document.open();
	var initMenuUrl = "<c:out value='${initParam.returnMenuUrl}'/>";
	document.write("<meta http-equiv=\'refresh\' content=\'0\;url=" + initMenuUrl +"\'>");
	document.close();
}
// -->
</script>
</head>
<body onload="funcOnload();screen.updateInterval=0;initCodeMaintenance(event)">
<form name="F1" action="S21GenericTable" enctype="application/x-www-form-urlencoded" method="post">

			<!-- from EZD Base start -->

			<div style="width:1142px;height:25px; overflow:hidden;" >
				<!--  header(upper row) -->

				<table border="0" cellspacing="0" cellpadding="2" class="cHeadCloth" width="1142" height=24>
					<tr>
						<td style="width:7px;">
							&nbsp;
						</td>
						<td style="width:40px; align:left;">
							<img src="./img/menubuttondisable.png">
						</td>

						<td style="width:10px;" align="right">
						<%
							String isEdge = "false";
							Cookie[] cookie = request.getCookies();
							if (cookie != null) {
								for (int i = 0; i < cookie.length; i++) {
									if (cookie[i].getName().equals("isedge")) {
										isEdge = cookie[i].getValue();
									}
								}
							}
							if (isEdge.equals("true")) {
						%>
							<img id="zoom_mode" src="./img/zoom_mode.png" border="0" style="display:none;">
						<%
							} else {
						%>
							<a href="#" onClick="resetZoom()"><img id="zoom_mode" src="./img/zoom_mode.png" border="0"></a>
						<%
							}
						%>
						</td>
						<td class="cDispid" style="width:90px;">
							<%= EZDSystemEnv.getString("S21.build.version","").replaceFirst("^(.{0,20}).*","$1") %>
						</td>
						<td class="cDispid" style="width:80px;">
							<div id="pageIDArea"><c:out value='${s21nevo_kim.ezScreenID}'/></div>
						</td>
						<td class="cDispname" style="width:220px;">
							<div id="pageNameArea"><c:out value='${s21nevo_kim.screenName}'/></div>
						</td>
						
						<td class="cUser" style="width:70px;">
							<div id="userID"><c:out value='${userDetails.UID}'/></div>
						</td>
						<td class="cUser" style="width:130px;">
							<div id="userName"><c:out value='${userDetails.userName}'/></div>
						</td>

						<td class="cDate" style="width:50px;" align="right">
							<c:out value='${s21nevo_kim.serverDate}'/>
						</td>
						<td class="cTime" style="width:50px;" align="right">
							<c:out value='${s21nevo_kim.serverTime}'/>
						</td>
						<td style="width:40px;" align="right">
							<a href="#" onClick="helpOpen()"><img src="./img/helpbutton.png" border="0" onmouseover="this.src='./img/helpbuttonmouseover.png'" onmouseout="this.src='./img/helpbutton.png'"></a>
						</td>
					</tr>
				</table>

				<!--  Header display part end  -->
			</div>

			<!-- <div style="position:relative;left:4px;width:1133px;height:607px;background:#fafafa;">-->
			<div style="position:relative;left:4px;width:1133px;height:607px;background:<%= bodyCol %>;">

			<!-- from EZD Base end  -->

			<jsp:include page="<%=bean.getTransition()%>" flush="true"/>

			</div>

			<!-- from EZD status start -->
			<%
				String cls;
				String msgcd;
				String msgfield;

				if (bean != null && bean.getMsgInfo() != null){
					int msgType = bean.getMsgInfo().getMessageType();
					if(msgType == EZDMessageInfo.MSGTYPE_ERROR){
						cls = "cMsgI";
					}else if(msgType == EZDMessageInfo.MSGTYPE_WARNING){
						cls = "cMsgK";
					}else if(msgType == EZDMessageInfo.MSGTYPE_INFORMATION){
						cls = "cMsgS";
					}else{
						cls = "cMsgS";
					}

					msgcd = bean.getMsgInfo().getCode();
					msgfield = bean.getMsgInfo().getMessage();
					
					if (null == msgcd || msgcd.trim().length() == 0) {
						msgcd = "";
					}
					
					if (null == msgfield || msgfield.trim().length() == 0) {
						msgfield = "";
					}
		
				}else{
					cls = "";
					msgcd = "";
					msgfield = "";
				}

				request.setAttribute("msgId", msgcd);
				request.setAttribute("msg", msgfield);
			%>

			<!-- <table border=0 cellspacing=0 width="1133px" height="22px" style="margin-left:4px;margin-right:4px;background:#fafafa;">-->
			<table border=0 cellspacing=0 width="1133px" height="22px" style="margin-left:4px;margin-right:4px;background:<%= bodyCol %>;">
				<tr>
					<td align="center" valign="middle">
						<table border=1 cellspacing=0 width="80%" height="20px" class="cMsg">
						   <tr height="20px">
								<td class="cMsgCloth" width="15%"> 
									<span id="msgCode" class="<%= cls %>"><c:out value='${msgId}'/></span>
								</td> 
								<td class="cMsgCloth" width="85%"> 
									<span id="msgfield" class="<%= cls %>"><c:out value='${msg}'/></span>
								</td>
						   </tr>
						</table>
					</td>
				</tr>
			</table>

			<table width="100%" height="28px" border="0" cellpadding="1" cellspacing="0" style="background-image:url('./img/commonbuttonbackground.png'); background-repeat:repeat-x;">
			<tr style="text-align:center;align:center;vertical-align:middle;" height="28px">
			<td>			
			</td>
			</tr>
			</table>

			<!-- from EZD status end -->

		<!-- Common Hidden Information start -->

		<!-- Common Hidden Information end -->

</form>
</body>


</html>